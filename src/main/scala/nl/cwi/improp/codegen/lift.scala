package nl.cwi.improp.codegen

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import nl.cwi.improp.codegen.Utils.FreshNameGenerator

class lift[ALG, FROM, TO, BASEALG] extends StaticAnnotation{
  def macroTransform(annottees: Any*) = macro lift.impl
}


object lift{
  
  def impl(c: whitebox.Context)(annottees: c.Expr[Any]*) = {
     import c.universe._
     
     val (alg_, srcF, addedTy, baseAlg): (Tree, Tree, Tree, Tree) = c.macroApplication match{
       case q"new lift[$a, $from, $to, $baseA].macroTransform($_)" => (a, from, to, baseA)
       //case q"new propagate[$a, $from, $to, $baseA](..$args).macroTransform($_)" => (a, from, to, baseA, "simple")
       case _ => c.abort(c.enclosingPosition, "Invalid type parameters")
     }
     
     val importer = new nl.cwi.improp.codegen.Importer[c.universe.type](c.universe)
    
     annottees.map(_.tree) match {
      //case (t@q"$mods trait $name[..$tparams] extends $parent { $self => ..$body }") ::Nil => {
      case (t@q"$mods trait $name") ::Nil => {
        val liftedName:TypeName = TypeName(name+"Lifted")
        val algType = c.typeCheck(q"(??? : $alg_)").tpe.map(_.normalize)
        val srcType = c.typeCheck(q"(??? : $srcF)").tpe.map(_.normalize)
        val baseType = c.typeCheck(q"(??? : $baseAlg)").tpe.map(_.normalize)
        val addedType = c.typeCheck(q"(??? : $addedTy)").tpe.map(_.normalize)
        val tempVar = c.freshName("temp")
        
        println("algType::::"+algType)
        println("baseType::::"+baseType)
        println("srcType::::"+srcType)
        println("addedTy::::"+addedType)
        
        implicit val nameGen: FreshNameGenerator = new FreshNameGenerator{
          override def generateFresh(name: String): String = c.fresh(name)
        }
        val addedTyp = importer.importType(addedType)
        val alg: Trait = importer.importTrait(algType)
        val lifted: Trait = alg.liftTrait(name.decoded, importer.importType(srcType).asInstanceOf[FunType], addedTyp,
            "base"+alg.name)
        val base: Trait = importer.importTrait(baseType)
        val srcFun: FunType = importer.importType(srcType).asInstanceOf[FunType]
        
        //val result = nl.cwi.improp.codegen.templates.txt.Simple.render(lifted, alg, base, srcFun, threading, tempVar, addedTyp).body
        val result = render(lifted, alg, base, srcFun, addedTyp)
        println(result)
        //c.Expr[Any](q"""$mods trait $liftedName""")
        c.Expr[Any](c.parse(result))
      }
      case _ => c.abort(c.enclosingPosition, "Invalid annottee")
      // Add validation and error handling here.
     }
  }

     
/*
 * @import nl.cwi.improp.codegen._

@(lifted: Trait, alg: Trait, baseAlg: Trait, from: FunType, threading: Boolean, tempVar: String, addedTy: Type)

trait @(lifted.name) extends @(lifted.extending.mkString(", ")){
  val base@(alg.name): @(alg.name)[@(from.toString)] = new @(baseAlg.name){}
  @if(threading){var @tempVar: @addedTy = _ } else{}
  @for(m <- lifted.methods) {
  def @(m.name)(@m.params.map{case (name, ty) => @name:@ty}.mkString(", ") ) : @m.rTy 
    = @m.expr
  }
  
}
 */

     
  def render(lifted: Trait, alg: Trait, baseAlg: Trait, srcFun: FunType, addedType: Type): String = {
    def liftedMethods = lifted.methods.map
      { m =>
        s"""
        |def ${m.name}(${m.params.map{case (name, ty) => s"${name}:${ty}"}.mkString(", ")}): ${m.rTy}
        |  = ${m.expr}
        |""".stripMargin
      }.mkString(" \n")
    s"""trait ${lifted.name} extends ${lifted.extending.mkString(", ")}{
        |
        |  val base${alg.name}: ${alg.name}[${srcFun.toString}] = new ${baseAlg.name}{}
        |
        |  ${liftedMethods}
        |}""".stripMargin
  }
  
}