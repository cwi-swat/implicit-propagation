package nl.cwi.improp.examples.fj.manualLiftings

import nl.cwi.improp.examples.fj.Env
import nl.cwi.improp.examples.fj.EvSlfCtESt2Clazz
import nl.cwi.improp.examples.fj.Program
import nl.cwi.improp.examples.fj.Ev2MethEv2ClazzClazzDef
import nl.cwi.improp.examples.fj.SetField
import nl.cwi.improp.examples.fj.EvCT2ObjField
import nl.cwi.improp.examples.fj.Ev2ClazzEvCt2ObjProgram
import nl.cwi.improp.examples.fj.EvCtSt2ObjNew
import nl.cwi.improp.examples.fj.EvCt2ObjCast
import nl.cwi.improp.examples.fj.ClazzDef
import nl.cwi.improp.examples.fj.EvSlfCtEnv2ObjCall
import nl.cwi.improp.examples.fj.EvSlfCtESt2Meth
import nl.cwi.improp.examples.fj.Ev2MethEv2ObjMethodDef
import nl.cwi.improp.examples.fj.Ev2ObjSeq
import nl.cwi.improp.examples.fj.Cast
import nl.cwi.improp.examples.fj.MethodDef
import nl.cwi.improp.examples.fj.EvCtSt2ObjSetField
import nl.cwi.improp.examples.fj.Sto
import nl.cwi.improp.examples.fj.EvSlfCtESt2Obj
import nl.cwi.improp.examples.fj.SFJ
import nl.cwi.improp.examples.fj.Str
import nl.cwi.improp.examples.fj.EvSlfCtESt2ObjRef
import nl.cwi.improp.examples.fj.CT
import nl.cwi.improp.examples.fj.Field
import nl.cwi.improp.examples.fj.Obj
import nl.cwi.improp.examples.fj.New
import nl.cwi.improp.examples.fj.Call
import nl.cwi.improp.examples.fj.Seq


//type EvSlfCtESt2Obj =  (Obj, CT, Env, Sto) => Obj


trait Ev2ClazzEvSlfCtESt2ObjProgram extends Program[EvSlfCtESt2Clazz, EvSlfCtESt2Obj]{
  private val base = new Ev2ClazzEvCt2ObjProgram{}
  
  override def prog(e: EvSlfCtESt2Obj, classes: Set[EvSlfCtESt2Clazz]) =
    (thiz, ct, env, sto) => base.prog((ct1) => e(thiz, ct1, env, sto) , 
        classes.map { c => {() => c(thiz, ct, env, sto)}})(ct)
}

trait EvSlfCtESt2MethEvSlfCtESt2ClazzClazzDef extends  ClazzDef[EvSlfCtESt2Meth, EvSlfCtESt2Clazz]{
  private val base = new Ev2MethEv2ClazzClazzDef{}
  
  override def clazz(name: Str, parent: Str, fldTypes: List[Str], fldNames: List[Str], 
      methods: List[EvSlfCtESt2Meth]) = 
    (thiz, ct, env, sto) =>
      base.clazz(name, parent, fldTypes, fldNames, methods.map { m => () => m(thiz, ct, env, sto)})()
}

trait Ev2MethEvSlfCtESt2ObjMethodDef extends MethodDef[EvSlfCtESt2Meth, EvSlfCtESt2Obj]{
  private val base = new Ev2MethEv2ObjMethodDef {}
  
  override def method(m: Str, retType: Str, paramTypes: List[Str], paramNames: List[Str],
      retExpr: EvSlfCtESt2Obj) = 
    (thiz, ct, env, sto) => base.method(m, retType, paramTypes, paramNames,
        (thiz1, ct1, env1) => retExpr(thiz1, ct1, env1, sto))()
}
      
trait EvSlfCtESt2ObjField extends Field[EvSlfCtESt2Obj] {
  private val base = new EvCT2ObjField{}
  
  override def field(e: EvSlfCtESt2Obj, f: Str) =
    (thiz, ct, env, sto)  => 
      base.field(
          (ct1) => e(thiz, ct1, env, sto), 
          f)(ct)
}

trait EvSlfCtESt2ObjCall extends Call[EvSlfCtESt2Obj]{
  private val base = new EvSlfCtEnv2ObjCall{}
  
  override def call(e: EvSlfCtESt2Obj, m: Str, args: List[EvSlfCtESt2Obj]) = 
    (thiz, ct, env, sto) =>
      base.call(
          (thiz1, ct1, env1) => e(thiz1, ct1, env1, sto), 
          m, 
          args.map { a => {(thiz1: Obj, ct1: CT, env1: Env) => a(thiz1, ct1, env1, sto)} })(thiz, ct, env)
}

trait EvSlfCtESt2ObjCast extends Cast[EvSlfCtESt2Obj]{
  private val base = new EvCt2ObjCast{}
  
  override def cast(c: Str, e: EvSlfCtESt2Obj) = 
    (thiz, ct, env, sto) =>
      base.cast(
          c,
          (ct1) => e(thiz, ct1, env, sto))(ct)
}

trait EvSlfCtESt2ObjNew extends New[EvSlfCtESt2Obj]{
  private val base = new EvCtSt2ObjNew{}
  
  override def newObj(n: Str, args: List[EvSlfCtESt2Obj]) = 
    (thiz, ct, env, sto) =>
      base.newObj(
          n, 
          args.map { a => {(ct1: CT, sto1: Sto) => a(thiz, ct1, env, sto1)} })(ct, sto)
}

trait EvSlfCtESt2ObjSetField extends SetField[EvSlfCtESt2Obj] {
  private val base = new EvCtSt2ObjSetField{}
  
  override def setField(e: EvSlfCtESt2Obj, f: Str, e1: EvSlfCtESt2Obj) =
    (thiz, ct, env, sto)  => 
      base.setField(
          (ct1, sto1) => e(thiz, ct1, env, sto1), 
          f,
          (ct1, sto1) => e1(thiz, ct1, env, sto1))(ct, sto)
}

trait EvSlfCtESt2ObjSeq extends Seq[EvSlfCtESt2Obj] {
  private val base = new Ev2ObjSeq{}
  
  override def seq(e1: EvSlfCtESt2Obj, e2: EvSlfCtESt2Obj) =
    (thiz, ct, env, sto)  => 
      base.seq(
          () => e1(thiz, ct, env, sto), 
          () => e2(thiz, ct, env, sto))()
}

trait SFJImpl extends Ev2ClazzEvSlfCtESt2ObjProgram with Ev2MethEvSlfCtESt2ObjMethodDef with EvSlfCtESt2MethEvSlfCtESt2ClazzClazzDef
  with EvSlfCtESt2ObjRef with EvSlfCtESt2ObjField with EvSlfCtESt2ObjCall with EvSlfCtESt2ObjCast
  with EvSlfCtESt2ObjNew with EvSlfCtESt2ObjSetField with EvSlfCtESt2ObjSeq
  with SFJ[EvSlfCtESt2Clazz, EvSlfCtESt2Meth, EvSlfCtESt2Obj]
  
