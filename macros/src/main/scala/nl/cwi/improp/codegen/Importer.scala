package nl.cwi.improp.codegen

import scala.tools.nsc
import nsc.Global
import scala.collection.generic.FilterMonadic

class InternalImporter[U<:scala.reflect.api.Universe](global: U){
  
  def importTrait(ty: global.Type): Trait = {
      // Replace with precondition that ensures it is a trait
      assume(true)
      val ss = ty.etaExpand.typeParams.map { x => x.asType.fullName}
      val ms = ty.members.filter(_.isMethod).map(_.asMethod).filter(_.isAbstract).map { m => new Method(m.name.decoded, m.paramLists.head.map(p => (p.name.toString(), importType(p.typeSignature))).toList, importType(m.returnType), m.isAbstract, "???") }
      new Trait(ty.typeSymbol.name.toString(), 
        ty.etaExpand.typeParams.map(_.asType.fullName), List(), ms.toList)
  }
  
  def importType(ty: global.Type): Type = {
    if (ty.typeSymbol.asType.isAbstractType)
      new AbsType(ty.typeSymbol.fullName)
    else if (ty <:< global.typeOf[FilterMonadic[_,_]] &&ty.typeArgs.size == 1){
      new MappableType(ty.typeSymbol.fullName, importType(ty.typeArgs.head))
    }
    else
      ty.typeSymbol.name.decodedName.toString() match{
      case "Tuple2" => new TupleType(importType(ty.typeArgs.head), importType(ty.typeArgs.tail.head))
      case "Function0" => new FunType(List(), importType(ty.typeArgs.head))
      case "Function1" | "Function2" | "Function3" | "Function4" | "Function5" | "Function6"| "Function7" | "Function8" | "Function9" => {
        val parTys = ty.typeArgs.take(ty.typeArgs.size-1).map(importType(_))
        val retTy = importType(ty.typeArgs.last)
        val ft = new FunType(parTys, retTy)
        ft
      }
      case _ => new PrimitiveType(ty)
  }}
  
}