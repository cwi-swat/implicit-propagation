package nl.cwi.improp.examples.fj


class CTBuilder extends LJ[Clazz, Method, CT]{
  override def prog(e: CT, classes: Set[Clazz]) =
     classes.map {c => c.name -> c}.toMap
  
     override def clazz(name: String, extended: String, fieldTypes: List[String], fieldNames: List[String], 
      methods: List[Method]) = 
    new Clazz(name, extended, fieldTypes.zip(fieldNames), methods)
  
  override def method(m: String, retType: String, paramTypes: List[String], paramNames: List[String],
      retExpr: CT) = 
    new Method(m, null, paramTypes.zip(paramNames), null);
  
  override def setField(e: CT, f: String, e1: CT) = null
  override def seq(e1: CT, e2: CT) = null
  override def ref(x: String) = null
  override def newObj(c: String, args: List[CT])= null
  override def call(e:CT, m: String, args: List[CT]) = null
  override def field(e:CT, f: String) = null
  override def cast(to: String, e: CT) = null
}



