package funcons.examples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import funcons.entities.Bool;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Store;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvStoreVal;
import funcons.languages.Language22Alg;
import funcons.languages.SimpleLangDelegationAlg;
import funcons.languages.impl.ConcreteLanguage22Alg;
import funcons.languages.impl.ConcreteSimpleLangDelegationAlg;

public class TestAll {
	
	<E> E e1(SimpleLangDelegationAlg<E> a){
		return a.ifTrue(a.constant(new Bool(true)), a.constant(new Int(3)), a.constant(new Int(2)));
	}
	
	@SuppressWarnings("unchecked")
	/*
		
	*/
	<E> E e2(Language22Alg<E,E> a){
		I x = new I("x");
		return 
				a.l22Seq(
					a.l22ExpSemiColon(a.l22Assign(x, a.l22Constant(new Int(5)))),
					a.l22I(x));
	};

	@Test
	public void test1(){
		SimpleLangDelegationAlg<IEvalBasic> a = new ConcreteSimpleLangDelegationAlg();
		assertEquals(3, ((Int) e1(a).eval()).getValue());
	}
	
	@Test
	public void test2(){
		Language22Alg<IEvalEnvStoreVal, IEvalEnvStoreVal> a = new ConcreteLanguage22Alg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		assertEquals(5, ((Int) e2(a).eval(e, s, v)).getValue());
	}
	


}
