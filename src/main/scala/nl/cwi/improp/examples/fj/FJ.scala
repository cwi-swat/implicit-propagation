package nl.cwi.improp.examples.fj

import scala.collection.mutable.MutableList
import scala.util.control.Breaks._

class Obj(val clazz: Str, val args: List[Obj]){
  override def toString(): String =
    "new "+clazz+"("+(args.map (_.toString())).fold("")((acc, cur) => acc+ (if (acc=="") "" else ", ") +cur) +")"
}
class Method(val name: Str, val retType: Str, 
      val params: List[(Str, Str)], val retExpr: EvSlfCtE2Obj)
class Clazz(val name: Str, val parent: Str, val fields: List[(Str,Str)], val methods: List[Method]) 

trait Program[L, E]{ def prog(e: E, classes: Set[L]): E }
trait ClazzDef[M,L]{ def clazz(name: Str, parent: Str, fldTypes: List[Str], fldNames: List[Str], methods: List[M]): L }
trait MethodDef[M,E]{ def method(m: Str, retType: Str, paramTypes: List[Str], paramNames: List[Str], retExpr: E): M}
trait Ref[E]{ def ref(x: Str): E }
trait Field[E]{ def field(e:E, f: Str): E }
trait Call[E]{ def call(e:E, m: Str, es: List[E]): E }
trait New[E]{ def newObj(c: Str, es: List[E]): E }
trait Cast[E]{ def cast(c: Str, e: E): E }

trait FJ[L,M,E] extends Program[L, E] with ClazzDef[M, L] with MethodDef[M, E]
  with Ref[E] with Field[E] with Call[E] with Cast[E] with New[E]

trait LJ[L,M,E] extends FJ[L,M,E] with SetField[E] with Seq[E]

trait Ev2ClazzEvCt2ObjProgram extends Program[Ev2Clazz, EvCt2Obj]{
  override def prog(e: EvCt2Obj, classes: Set[Ev2Clazz]) =
    (_) => e(classes.map {c => c()}.map {c => c.name -> c}.toMap)
}

trait Ev2MethEv2ClazzClazzDef extends  ClazzDef[Ev2Meth, Ev2Clazz]{
  override def clazz(name: Str, parent: Str, fldTypes: List[Str], fldNames: List[Str], 
      methods: List[Ev2Meth]) = 
    () =>
      new Clazz(name, parent, fldTypes.zip(fldNames), methods.map(_()))
}

trait Ev2MethEv2ObjMethodDef extends MethodDef[Ev2Meth, EvSlfCtE2Obj]{
  override def method(m: Str, retType: Str, paramTypes: List[Str], paramNames: List[Str],
      retExpr: EvSlfCtE2Obj) = 
    () =>
      new Method(m, retType, paramTypes.zip(paramNames), retExpr)
}

trait EvSlfE2ObjRef extends Ref[EvSlfE2Obj] {
    override def ref(x: Str) = x match {
      case "this"=> (thiz, _) => thiz
      case _     => (_, env) => env(x)
    }    
  }

trait EvCT2ObjField extends Field[EvCt2Obj] {
  override def field(e: EvCt2Obj , f: Str) =
     ct =>{
      val c = e(ct);
      val fields = findFields(ct, c.clazz)
      val i = (0 until fields.length).toList.zip(fields).find(t => t._2._2 == f).get._1
      c.args(i)
     }   
}

trait EvSlfCtEnv2ObjCall extends Call[EvSlfCtE2Obj]{
  override def call(e: EvSlfCtE2Obj, m: Str, args: List[EvSlfCtE2Obj]) =
    (thiz, ct, env) => {
      val c = e(thiz, ct, env)
      val mBody = findMBody(ct, m, c.clazz)
      val (x_, e0) = mBody
      e0(c, 
          ct, args.zip(x_).foldLeft(Map[Str,Obj]())
          {case (acc, (arg, pName)) => acc +(pName -> arg(thiz, ct, env))}) 
    }
}

trait Ev2ObjNew extends New[Ev2Obj]{
  override def newObj(c: Str, args: List[Ev2Obj]) =
    () => new Obj(c, args.map(f=> f()))
}

trait EvCt2ObjCast extends Cast[EvCt2Obj]{
  def cast(c: Str, e: EvCt2Obj) =
    (ct) =>{
      val o = e(ct)
      if (isSubtype(ct, o.clazz, c))
        o
      else 
        throw new RuntimeException("[FJ] Class cast exception.");
    }
}



