package nl.cwi.improp.examples

import scala.collection._

package object fj {
  type Env = Map[Str, Obj]
  type CT = Map[Str, Clazz]
  type Sto = mutable.Map[Int, (Str, Map[Str, Int])]
  type Str = String
  type Ev2Obj = () => Obj
  type Ev2Meth = () => Method
  type Ev2Clazz = () => Clazz
  type EvSlfE2Obj = (Obj, Env) => Obj
  type EvSlfCtE2Obj = (Obj, CT, Env) => Obj
  type EvCtE2Obj = (CT, Env) => Obj
  type EvCt2Obj = CT => Obj
  type EvSt2Obj = Sto => Obj
  type EvCtSt2Obj = (CT, Sto) => Obj
  type EvSlfCtESt2Obj =  (Obj, CT, Env, Sto) => Obj
  type EvSlfCtESt2Meth = (Obj, CT, Env, Sto) => Method
  type EvSlfCtESt2Clazz = (Obj, CT, Env, Sto) => Clazz
  
  def findFields(ct: CT, clName: String): List[(String, String)] =
         clName match {
           case "Object" => List()
           case _ => ct(clName).fields ++ findFields(ct, ct(clName).parent)
         }
  
  def findMBody(ct: CT, m: Str, clName: Str): (List[Str], EvSlfCtE2Obj) = {
        val method = ct(clName).methods.find { met => met.name == m } 
        if (method.isDefined)
           (method.get.params.map {case (_, name) => name}, method.get.retExpr)
        else
          findMBody(ct, m, ct(clName).parent);
  }
  
  def isSubtype(ct: CT, c: Str, d: Str): Boolean = c match{
        case `d` => true
        case "Object" =>  false
        case _ => isSubtype(ct, ct(c).parent, d)
      }
  
  def getObj(o: Int, s: Sto, ct:CT): Obj = {
     val (typ, flds) = s(o)
     val fields = findFields(ct, typ).map {case (ty, name) => flds(name) }
     new Obj(typ, fields.map(hc => getObj(hc, s, ct))){
       override def hashCode() = o.hashCode()
     }
   }
  
}

