package nl.cwi.improp.examples.fj.test

import junit.framework.TestCase
import org.junit.Test
import org.junit.Assert.assertEquals
import nl.cwi.improp.examples.fj.manualLiftings.SFJImpl
import nl.cwi.improp.examples.fj.generatedLiftings.SFJImplWithCodeGen
import nl.cwi.improp.examples.fj._

class FJTest extends TestCase {
 

  /*
   * EXAMPLE 1
   * ---------
   * 
   * This is an FJ program. FJ features creation of new objects, field accessing, method calling, 
   * object referencing and object casting
   * 
   * class A extends Object{
   *   Number number;
   *   A(Number number){
   *     super();
   *     this.number = number;
   *   }
   * }
   * 
   * class Number extends Object{
   *   Number(){
   *     super();
   *   }
   * }
   * 
   * class One extends Number{
   *   One(){
   *     super();
   *   }
   * }
   * 
   * class Two extends Number{
   *   Two(){
   *     super();
   *   }
   * }
   * 
   * // Entry-point expression (analogous to main method in regular Java)
   * 
   * (new A(new One()).number 
   * 
   */
  
  def ex1[L, M, E](alg: FJ[L, M, E]): E =
    alg.prog(
        alg.field(
            alg.newObj("A", List(alg.newObj("One", List()))), 
            "number"),
        Set(
          alg.clazz("A", "Object", List("Number"), List("number"), List()),
          alg.clazz("Number", "Object", List(), List(), List()),
          alg.clazz("One", "Number", List(), List(), List()),
          alg.clazz("Two", "Number", List(), List(), List())  
        ))
        
   @Test def test1 = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImpl{}
    val o = ex1(sfj)(null, null, Map(), st)
    assertEquals("new One()", o.toString())
  }
  
  @Test def test1gen = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImplWithCodeGen{}
    val o = ex1(sfj)(null, null, Map(), st)
    assertEquals("new One()", o.toString())
  }
        
  /*
   * EXAMPLE 2
   * ---------
   * 
   * This is a SFJ program. LJ extends SFJ with field assignment and sequencing,
   * thus it needs an extra semantic entity: the store
   * 
   * class A extends Object{
   *   Number number;
   *   A(Number number){
   *     super();
   *     this.number = number;
   *   }
   *   A setNumberToTwo(){
   *     this.number = new Two();
   *     return this;
   *   }
   * }
   * 
   * class Number extends Object{
   *   Number(){
   *     super();
   *   }
   * }
   * 
   * class One extends Number{
   *   One(){
   *     super();
   *   }
   * }
   * 
   * class Two extends Number{
   *   Two(){
   *     super();
   *   }
   * }
   * 
   * // Entry-point expression (analogous to main method in regular Java)
   * 
   * (new A(new One()).setNumberToTwo();
   * 
   */
  

  def ex2[L, M, E](alg: SFJ[L, M, E]): E =
    alg.prog(
        alg.call(alg.newObj("A", List(alg.newObj("One", List()))), "setNumberToTwo", List()),
        Set(
          alg.clazz("A", "Object", List("Number"), List("number"),
              List(alg.method("setNumberToTwo", "A", List(), List(), alg.seq(alg.setField(alg.ref("this"), "number", alg.newObj("Two", List())), alg.ref("this"))))),
          alg.clazz("Number", "Object", List(), List(), List()),
          alg.clazz("One", "Number", List(), List(), List()),
          alg.clazz("Two", "Number", List(), List(), List())  
        ))
  
  @Test def test2 = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImpl{}
    val o = ex2(sfj)(null, null, Map(), st)
    val ct = ex2(new CTBuilder)
    assertEquals("new A(new Two())", getObj(o.hashCode(), st, ct).toString())
  }
  
  @Test def test2gen = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImplWithCodeGen{}
    val o = ex2(sfj)(null, null, Map(), st)
    val ct = ex2(new CTBuilder)
    assertEquals("new A(new Two())", getObj(o.hashCode(), st, ct).toString())
  }
  
    /*
   * EXAMPLE  3
   * ---------
   * 
   * This is a SFJ program. SFJ extends FJ with field assignment and sequencing,
   * thus it needs an extra semantic entity: the store
   * 
   * class A extends Object{
   *   Number number;
   *   A(Number number){
   *     super();
   *     this.number = number;
   *   }
   *   A setNumberToTwo(){
   *     this.number = new Two();
   *     return this;
   *   }
   * }
   * 
   * class Number extends Object{
   *   Number(){
   *     super();
   *   }
   * }
   * 
   * class One extends Number{
   *   One(){
   *     super();
   *   }
   * }
   * 
   * class Two extends Number{
   *   Two(){
   *     super();
   *   }
   * }
   * 
   * // Entry-point expression (analogous to main method in regular Java)
   * 
   * ((new A(new One()).setNumberToTwo()).number;
   * 
   */
  
    def ex3[L, M, E](alg: SFJ[L, M, E]): E =
    alg.prog(
        alg.field(alg.call(alg.newObj("A", List(alg.newObj("One", List()))), "setNumberToTwo", List()), "number"),
        Set(
          alg.clazz("A", "Object", List("Number"), List("number"),
              List(alg.method("setNumberToTwo", "A", List(), List(), alg.seq(alg.setField(alg.ref("this"), "number", alg.newObj("Two", List())), alg.ref("this"))))),
          alg.clazz("Number", "Object", List(), List(), List()),
          alg.clazz("One", "Number", List(), List(), List()),
          alg.clazz("Two", "Number", List(), List(), List())  
        ))
  
  @Test def test3 = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImpl{}
    val o = ex3(sfj)(null, null, Map(), st)
    val ct = ex2(new CTBuilder)
    assertEquals("new Two()", getObj(o.hashCode(), st, ct).toString())
  }
       
 @Test def test3gen = {
    var st: Sto = scala.collection.mutable.Map()
    val sfj = new SFJImplWithCodeGen{}
    val o = ex3(sfj)(null, null, Map(), st)
    val ct = ex2(new CTBuilder)
    println(getObj(o.hashCode(), st, ct))
    assertEquals("new Two()", getObj(o.hashCode(), st, ct).toString())
  }
}