package funcons.languages.experiments;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import funcons.entities.Env;
import funcons.entities.Int;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvValue;

public class TestAll {
	
	/*
	 let succ x = x + 1  
	 in succ(5)
	 */
	
	static <E, D, F, P, LB, SM> E e4(CLAlg<E, D, F, P, LB, SM> a){
		return a.simpleLet(
				a.simpleDecl(a.idPatt("succ"), 
							a.function(a.simpleMatch(a.idPatt("x"), a.add(a.boundValue("x"), a.constant(new Int(1)))))),
				a.apply(a.boundValue("succ"), a.constant(new Int(5))));
	
	}
	
	/*
	 let iszero = function 0 -> true | _ -> false
	 in iszero(31)
	*/

	/*
	static <E, D, F, P, LB, SM> E e7(CLAlg<E, D, F, P, LB, SM> a){
		I iszero = new I("iszero");
		return a.simpleLet(
				a.simpleDecl(
					a.idPatt("iszero"),
					a.function(
							a.compoundMatch(
								a.simpleMatch(a.constantPatt(new Int(0)), a.constant(new Char('t'))),
								a.simpleMatch(a.anyPatt(), a.constant(new Char('f'))))),
					a.apply(a.boundValue("iszero"), a.constant(v))
								
				
				a.patt(new IdPattern(iszero)),
				a.function(CompoundMatch.create(
							new SimpleSimpleMatch<E>(new ConstantPattern(new Int(0)), 
													   a.camlConstant(new Char('t'))),
							  new SimpleSimpleMatch<E>(new AnyPattern(),
									  				  a.camlConstant(new Char('f'))))),
				a.camlApply(a.camlI(iszero), a.camlConstant(new Int(31))));
	}

*/
	//@Test
	public void test1() {
		ConcreteCLAlg alg = new ConcreteCLAlg();
		assertEquals(new Int(6), e4(alg).eval(new Env<Value>(), new Int(42)));
	}
	
	@Test
	public void test2() {
		ConcreteCL2Alg alg = new ConcreteCL2Alg();
		assertEquals(new Int(6), e4(alg).eval(new Env<Value>(), new Int(42)));
	}
	
	//@Test
	public void test3() {
		ConcreteCL3Alg alg = new ConcreteCL3Alg();
		assertEquals(new Int(6), e4(alg).eval(new Env<Value>(), new Int(42)));
	}
	
	public static void main(String[] args){
		ConcreteCL2Alg alg = new ConcreteCL2Alg();
		Object o = alg.function(alg.simpleMatch(alg.idPatt("x"), alg.add(alg.boundValue("x"), alg.constant(new Int(1))))).eval(new Env<Value>(), new Int(42));
		System.out.println(o);
		System.out.println(((IEvalEnvValue) o).eval(new Env<Value>(), new Int(42)));
		System.out.println(
				alg.simpleLet(alg.simpleDecl
						(alg.idPatt("succ"), alg.function(alg.simpleMatch(alg.idPatt("x"),
														 alg.add(alg.boundValue("x"), alg.constant(new Int(1)))))),
						alg.apply(alg.boundValue("succ"), alg.constant(new Int(5)))).eval(new Env<Value>(), new Int(42)));
	}
}