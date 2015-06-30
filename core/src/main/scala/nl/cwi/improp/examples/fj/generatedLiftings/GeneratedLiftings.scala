package nl.cwi.improp.examples.fj.generatedLiftings

import nl.cwi.improp.examples.fj._
import nl.cwi.improp.codegen.lift
import nl.cwi.improp.examples.fj.manualLiftings.Ev2MethEvSlfCtESt2ObjMethodDef
import nl.cwi.improp.examples.fj.manualLiftings.Ev2ClazzEvSlfCtESt2ObjProgram
import nl.cwi.improp.examples.fj.manualLiftings.EvSlfCtESt2MethEvSlfCtESt2ClazzClazzDef

@lift[Field[_], CT => Obj, EvCT2ObjField, (Obj, CT, Env, Sto) => Obj]      
trait EvSlfCtESt2ObjField 

@lift[Call[_], (Obj, CT, Env) => Obj, EvSlfCtEnv2ObjCall, (Obj, CT, Env, Sto) => Obj]    
trait EvSlfCtESt2ObjCall
 
@lift[Cast[_], CT => Obj, EvCt2ObjCast, (Obj, CT, Env, Sto) => Obj]    
trait EvSlfCtESt2ObjCast

@lift[New[_], (CT, Sto) => Obj, EvCtSt2ObjNew, (Obj, CT, Env, Sto) => Obj]    
trait EvSlfCtESt2ObjNew 

@lift[SetField[_], (CT, Sto) => Obj, EvCtSt2ObjSetField, (Obj, CT, Env, Sto) => Obj]    
trait EvSlfCtESt2ObjSetField

@lift[Seq[_], () => Obj, Ev2ObjSeq, (Obj, CT, Env, Sto) => Obj]    
trait EvSlfCtESt2ObjSeq

trait SFJImplWithCodeGen extends Ev2ClazzEvSlfCtESt2ObjProgram 
  with Ev2MethEvSlfCtESt2ObjMethodDef with EvSlfCtESt2MethEvSlfCtESt2ClazzClazzDef
  with EvSlfCtESt2ObjRef with EvSlfCtESt2ObjField with EvSlfCtESt2ObjCall with EvSlfCtESt2ObjCast
  with EvSlfCtESt2ObjNew with EvSlfCtESt2ObjSetField with EvSlfCtESt2ObjSeq
  with SFJ[EvSlfCtESt2Clazz, EvSlfCtESt2Meth, EvSlfCtESt2Obj]
