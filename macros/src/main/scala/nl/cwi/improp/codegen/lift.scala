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
     
     // Get the annotation arguments as trees
     val (algAST: Tree, srcFunAST: Tree, baseAlgAST: Tree, tgtFunAST: Tree) 
       = c.macroApplication match{
         case q"new lift[$a, $from, $baseA, $to].macroTransform($_)" => 
           (a, from, baseA, to)
         case _ => 
           c.abort(c.enclosingPosition, "Invalid type parameters")
     }
     
     // Get the types of the annotation arguments
      val (algType: Type, srcType: Type, baseImplType: Type, tgtType: Type) = 
        Checker.typeCheck(c)(algAST, srcFunAST, baseAlgAST, tgtFunAST)
    
      // Create importer that allows to create intermediate representations
      // of traits and function types
      val (alg: Trait, srcFun: FunType, baseImpl: Trait, tgtFun: FunType)
        = InternalImporter.importTypes(c)(algType, srcType, baseImplType, tgtType)
      
     // At least one annottee must be a trait
     annottees.map(_.tree) match {
      case (t@q"$mods trait $name") ::Nil => {
        
        // This is the code that triggers the lifting on the intermediate representations
        val lifted: Trait = alg.liftTraitTo(name.decoded, srcFun, tgtFun, "base"+alg.name)
        
        // Serialize the lifted trait and return it as the result of the transformation
        val result: Expr[Any] = Render.serialize(c)(mods, lifted, alg, baseImpl, srcFun)
        result 
      }
      case _ => c.abort(c.enclosingPosition, "Invalid annottee")
     }
  }
}

object Checker{
  
    def typeCheck(c: whitebox.Context)(t1: c.Tree, t2: c.Tree, t3: c.Tree, t4: c.Tree): (c.Type, c.Type, c.Type, c.Type) 
      = { import c.universe._;
            (c.typeCheck(q"(??? : $t1)").tpe.map(_.normalize),
             c.typeCheck(q"(??? : $t2)").tpe.map(_.normalize),
             c.typeCheck(q"(??? : $t3)").tpe.map(_.normalize),
             c.typeCheck(q"(??? : $t4)").tpe.map(_.normalize))
        }  
  
}

object InternalImporter{
      def importTypes(c: whitebox.Context)(algType: c.Type, srcType: c.Type, baseType: c.Type, tgtType: c.Type): (Trait, FunType, Trait, FunType) 
      = { 
          val importer = new IImporter[c.universe.type](c.universe)
          ( importer.importTrait(algType),
            importer.importType(srcType).asInstanceOf[FunType],
            importer.importTrait(baseType),
            importer.importType(tgtType).asInstanceOf[FunType])    
        }  
  
}

object Render{
   def render(c: whitebox.Context)(mods: c.Modifiers, lifted: Trait, alg: Trait, baseAlg: Trait, srcFun: FunType): String = {
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
  
  def serialize(c: whitebox.Context)(mods:c.Modifiers, lifted: Trait, alg: Trait, baseAlg: Trait, srcFun: FunType)
  : c.Expr[Any]
    = { // println(render(c)(mods, lifted, alg, baseAlg, srcFun));
        c.Expr[Any](c.parse(render(c)(mods, lifted, alg, baseAlg, srcFun)));
     }
   
}