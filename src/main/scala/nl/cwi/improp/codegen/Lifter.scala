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
    val functionCall = s"(${freshParams.mkString(", ")}) => ${body}"
    new Method(name, tgtParams, tgtRType, false, functionCall)
  }
  def liftTo(carrierSymbol: String, srcF: FunType, tgtFun: FunType, baseAlgName: String): Method = { 
    val tgtParams = params.map { case (pName, pTy) => (pName, replaceType(carrierSymbol, pTy, tgtFun))}
    val tgtRType = replaceType(carrierSymbol, rTy, tgtFun)
    val freshParamNames = tgtFun.ptys.zipWithIndex.map { case (_, idx) => "y"+idx}
    val loweredArgs = params.map {p => lowerTo(p, 
        srcF.ptys, 
        freshParamNames,
        tgtFun.ptys, carrierSymbol) }
    val body = liftExprTo(baseAlgName+"."+name+"("+loweredArgs.mkString(", ")+")", srcF.ptys, freshParamNames, tgtFun.ptys)
    val functionCall = s"(${freshParamNames.mkString(", ")}) => ${body}"
    new Method(name, tgtParams, tgtRType, false, functionCall)
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

   def argsAreCurried(srcTypes: List[Type], tgtTypes: List[Type]): List[Boolean] = tgtTypes match{
     case List() => List()
     case tt :: tts => 
       if (srcTypes.contains(tt))
         true :: argsAreCurried(srcTypes diff List(tt), tgtTypes)
       else 
         false :: argsAreCurried(srcTypes, tts)
   }
   
   def lowerTo(p: Param, srcParamTypes: List[Type], tgtParamNames: List[String], tgtParamTypes: List[Type], absTy: String): String = {
     val areCurried = argsAreCurried(srcParamTypes, tgtParamTypes)
     val curriedArgs = areCurried.zip(tgtParamNames).map 
       { case (isCurried, name) => if (isCurried) "_" else name}
     lowerArg(p, absTy, curriedArgs, srcParamTypes.isEmpty)
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
  
 def lowerArg(param: Param, absTy: String, curriedArgs: List[String], parameterless: Boolean): String = param match{
   case (pName, MappableType(mappableTy, AbsType(aName))) => {
       if (aName == absTy){
         if (parameterless)
           s"${pName}.map { el => {() => el(${curriedArgs.mkString(", ")}) }}"
         else
           s"${pName}.map { el => el(${curriedArgs.mkString(", ")}) }"   
       }
       else
         throw new Exception("The carrier type of the abstract algebra must be unique")
     }  
   case (pname, at@(AbsType(aName))) =>
       if (aName == absTy){
         if (parameterless)
           s"() => ${pname}(${curriedArgs.mkString(", ")})"
         else
           s"${pname}(${curriedArgs.mkString(", ")})"
       }
       else
         throw new Exception("The carrier type of the abstract algebra must be unique")
    case (pname, _) => pname   
       
   }
           
   def liftExpr(expr: String, srcParams: List[Param], tgtParam: Param): String = 
     "("+(tgtParam::srcParams).map{ case (n, t) => n+": "+t}.mkString(", ")+") => "+expr+"("+srcParams.map(_._1).mkString(", ")+")"


   def liftExprTo(expr: String, srcTypes: List[Type], tgtNames: List[String], tgtTypes: List[Type]): String = {
    val srcArgs = computeSrcArgs(srcTypes, tgtNames.zip(tgtTypes))
    s"${expr}(${srcArgs.mkString(", ")})"
  }
       
  def computeSrcArgs(srcTypes: List[Type], tgtParams: List[Param]): List[String] = tgtParams match{
    case List() => List()
    case _ => 
      srcTypes match{ 
        case srcTy :: srcTys => { 
          tgtParams.find(_._2 == srcTy) match{
            case Some(found@(name, _)) => name :: computeSrcArgs(srcTys, tgtParams diff List(found))
            case None => throw new Exception("Wrong signatures")
          }
        }
        case _ => List()
      }
  }
  
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
  
   def liftTraitTo(newName: String, srcF: FunType, tgtF: FunType, baseAlgName: String)(implicit freshNameGen: FreshNameGenerator): Trait =
    if (isTraitAbstract())
      this match {
        case Trait(name, typePar, extending, methods) =>
          if (typePar.size==1){
            val ms: List[Method] =
              methods.map(_.liftTo(typePar.head, srcF, tgtF, baseAlgName))
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

