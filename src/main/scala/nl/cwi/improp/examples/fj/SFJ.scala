package nl.cwi.improp.examples.fj

import scala.collection.mutable.MutableList

trait SetField[E] { def setField(e: E, f: String, e1: E): E }
trait Seq[E]{ def seq(e1: E, e2: E): E }

trait EvCtSt2ObjNew extends New[EvCtSt2Obj]{
 
  override def newObj(n: String, args: List[EvCtSt2Obj])  =
    (ct, store) => {
       val v: List[Obj] = args.map { a => a(ct, store)}
       val o = new Obj(n, v)
       val f = findFields(ct, n).map(_._2) // field names
       val fToV = f.zip(v).groupBy(_._1).map { case (k,(_,v)::vs) => (k, v.hashCode())}
       store += (o.hashCode() -> (n, fToV))
       o
    }
}

trait EvCtSt2ObjSetField extends SetField[EvCtSt2Obj]{
   override def setField(e: EvCtSt2Obj, f: String, e1: EvCtSt2Obj) =
    (ct, store) => {
      val v1 = e(ct, store)  
      val v2 = e1(ct, store)
      val (n, fs) = store(v1.hashCode())
      store += (v1.hashCode() -> (n, fs + (f -> v2.hashCode())))
      v2
    }
   
}

trait Ev2ObjSeq extends Seq[Ev2Obj]{
  override def seq(e1: Ev2Obj, e2: Ev2Obj) = 
      () => {
        e1()
        e2()
      }
}


trait EvSlfCtESt2ObjRef extends Ref[EvSlfCtESt2Obj] {
    override def ref(x: Str) = x match {
      case "this"=> (thiz, ct, _, sto) => getObj(thiz.hashCode(), sto, ct)
      case _     => (_, ct, env, sto) => getObj(env(x).hashCode(), sto, ct)
    }    
  }


