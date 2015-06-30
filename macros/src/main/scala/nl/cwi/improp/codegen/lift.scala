package nl.cwi.improp.codegen

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import nl.cwi.improp.codegen.Utils.FreshNameGenerator

class lift[ALG, FROM, BASEALG, TO] extends StaticAnnotation{
  def macroTransform(annottees: Any*) = macro lift.impl
}


object lift{
  
  def impl(c: whitebox.Context)(annottees: c.Expr[Any]*) = {
     import c.universe._
     
     val (alg_, srcF, baseAlg, tgtTy): (Tree, Tree, Tree, Tree) = c.macroApplication match{
       case q"new lift[$a, $from, $baseA, $to].macroTransform($_)" => (a, from, baseA, to)
       case _ => c.abort(c.enclosingPosition, "Invalid type parameters")
     }
     
     val importer = new nl.cwi.improp.codegen.Importer[c.universe.type](c.universe)
    
     annottees.map(_.tree) match {
      case (t@q"$mods trait $name") ::Nil => {
        val liftedName:TypeName = TypeName(name+"Lifted")
        val algType = c.typeCheck(q"(??? : $alg_)").tpe.map(_.normalize)
        val srcType = c.typeCheck(q"(??? : $srcF)").tpe.map(_.normalize)
        val baseType = c.typeCheck(q"(??? : $baseAlg)").tpe.map(_.normalize)
        val tgtType = c.typeCheck(q"(??? : $tgtTy)").tpe.map(_.normalize)
        val tempVar = c.freshName("temp")
        
        implicit val nameGen: FreshNameGenerator = new FreshNameGenerator{
          override def generateFresh(name: String): String = c.fresh(name)
        }
        val alg: Trait = importer.importTrait(algType)
        val srcFun: FunType = importer.importType(srcType).asInstanceOf[FunType]
        val tgtFun : FunType = importer.importType(tgtType).asInstanceOf[FunType]
        val lifted: Trait = alg.liftTraitTo(name.decoded, srcFun, tgtFun, "base"+alg.name)
        val base: Trait = importer.importTrait(baseType)
        
        val result = render(lifted, alg, base, srcFun)
        c.Expr[Any](c.parse(result))
      }
      case _ => c.abort(c.enclosingPosition, "Invalid annottee")
     }
  }
     
  def render(lifted: Trait, alg: Trait, baseAlg: Trait, srcFun: FunType): String = {
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