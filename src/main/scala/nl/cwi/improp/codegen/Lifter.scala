package nl.cwi.improp.codegen

import Aliases._
import Utils._
import scala.reflect.macros.whitebox.Context
import scala.language.experimental.macros

class Type
case class TraitType(val t: Trait) extends Type
case class PrimitiveType(val ty: Any) extends Type{
  override def toString() = ty.toString()
}
case class FunType(val ptys: List[Type], val rty: Type) extends Type{
  override def toString() = "(" + ptys.map(_.toString()).mkString(", ") +") => "+ rty.toString()
}
case class AbsType(val tpar: String) extends Type{
  override def toString() = tpar
}
case class TupleType(val l: Type, val r: Type) extends Type{
  override def toString() = s"""(${l.toString()}, ${r.toString()})"""
}
case class MappableType(val containerTy: String, val containedTy: Type) extends Type{
  override def toString() = s"${containerTy}[${containedTy}]"
}

case class Method(val name: String, val params: List[Param], val rTy: Type, val isAbstract: Boolean, val expr: String){
  
  def lift(carrierSymbol: String, srcF: FunType, addedParTy: Type, baseAlgName: String, absTy: String): Method = { 
    val tgtFun: FunType = new FunType(addedParTy::srcF.ptys, srcF.rty)
    val tgtParams = params.map { case (pName, pTy) => (pName, replaceType(carrierSymbol, pTy, tgtFun))}
    val tgtRType = replaceType(carrierSymbol, rTy, tgtFun)
    val freshParamName = "y"
    val freshParams = srcF.ptys.zipWithIndex.map {case (ty,idx) => ("x"+idx, ty)}
    val loweredArgs = params.map { p => lower(p, freshParams.toList, freshParamName, absTy) }
    val body = liftExpr(baseAlgName+"."+name+"("+loweredArgs.mkString(", ")+")", freshParams, (freshParamName, addedParTy))
    new Method(name, tgtParams, tgtRType, false, body)
  }
}


object Utils{
  
   trait FreshNameGenerator{
     def generateFresh(name: String): String
   }

   def replaceType(absTyName: String, srcTy: Type, tgtTy: Type): Type = srcTy match{
     case AbsType(name) => if (name==absTyName) tgtTy else srcTy
     case MappableType(mappName, AbsType(name)) => if (name==absTyName) new MappableType(mappName, tgtTy) else srcTy
     case _ => srcTy
   }
   
  def lower(p: Param, srcParams: List[Param], tgtParamName: String, absTy: String) = p match {
     case (pName, MappableType(mappableTy, AbsType(_))) => {
       pName+".map { el => "+"("+ srcParams.map{ case (n,t) => n+": "+t  }.mkString(", ") +") => el("+ (tgtParamName::srcParams.map(_._1)).mkString(", ")+") }"
     }
     case (pName, AbsType(_))=>{
       "("+ srcParams.map{ case (n,t) => n+": "+t  }.mkString(", ") +") => "+pName+"("+ (tgtParamName::srcParams.map(_._1)).mkString(", ")+")"
       }
     case (pName, _) => pName 
   }

         
   def liftExpr(expr: String, srcParams: List[Param], tgtParam: Param): String = 
     "("+(tgtParam::srcParams).map{ case (n, t) => n+": "+t}.mkString(", ")+") => "+expr+"("+srcParams.map(_._1).mkString(", ")+")"
}

case class Trait(val name: String, val typePars: List[String], val extending: List[TraitRef], val methods: List[Method]){
  def isTraitAbstract(): Boolean
    = this.methods.map(_.isAbstract).foldLeft(true)(_&&_)
  def liftTrait(newName: String, srcF: FunType, addedParTy: Type, baseAlgName: String)(implicit freshNameGen: FreshNameGenerator): Trait =
    if (isTraitAbstract())
      this match {
        case Trait(name, typePar, extending, methods) =>
          if (typePar.size==1){
            val tgtF: FunType = new FunType(addedParTy::srcF.ptys, srcF.rty)
            val ms: List[Method] =
              methods.map(_.lift(typePar.head, srcF, addedParTy, baseAlgName, typePar.head))
            new Trait(newName, List(), List(new TraitRef(this, List(tgtF))), ms)
          }
          else
            throw new Exception("The carrier type has to be unique")
      }
    else
      throw new Exception("Trait algebra has to be abstract")
  
}

case class TraitRef(var tr: Trait, val typeArgs: List[Type]){
  override def toString() = tr.name+ (if (typeArgs.isEmpty) "" else "["+typeArgs.map(_.toString).mkString(", ")+"]")
}

object Aliases{
  type Param = (String, Type) 
}  


