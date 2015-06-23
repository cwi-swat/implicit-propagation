package funcons.examples;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import funcons.entities.Bool;
import funcons.entities.Char;
import funcons.entities.Env;
import funcons.entities.Failure;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Store;
import funcons.entities.Value;
import funcons.entities.experiments.AnyPattern;
import funcons.entities.experiments.CompoundMatch;
import funcons.entities.experiments.ConstantPattern;
import funcons.entities.experiments.IdPattern;
import funcons.entities.experiments.SimpleSimpleMatch;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvStoreVal;
import funcons.evaluators.IEvalEnvStoreValFail;
import funcons.evaluators.IEvalEnvStoreValFailRetEnv;
import funcons.evaluators.IEvalEnvStoreValMulti;
import funcons.evaluators.IEvalEnvStoreValMultiRetEnv;
import funcons.evaluators.IEvalEnvStoreValRetEnv;
import funcons.languages.CamlLightAlg;
import funcons.languages.CamlLightConcreteAlg;
import funcons.languages.CamlLightConcreteAlgWithFailure;
import funcons.languages.CamlLightConcreteMultiAlg;
import funcons.languages.Language22Alg;
import funcons.languages.SimpleLangDelegationAlg;
import funcons.languages.impl.ConcreteLanguage22Alg;
import funcons.languages.impl.ConcreteSimpleLangDelegationAlg;
import funcons.util.Quadruple;
import funcons.util.Triple;

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

	<E,D,P, F> E e3(CamlLightAlg<E,D,P,F> a){
		I succ = new I("succ");
		I x = new I("x");
		return a.simpleLet(
				a.patt(new IdPattern(succ)),
				a.function(new SimpleSimpleMatch<E>(new IdPattern(x), 
													a.add(a.camlI(x), a.camlConstant(new Int(1))))),
				a.camlAdd(a.camlConstant(new Int(3)), a.camlConstant(new Int(2))));
				
				
	}
	/*
	 let succ x = x + 1  
	 in succ(5)
	 */
	<E,D,P,F> E e4(CamlLightAlg<E,D,P,F> a){
		I succ = new I("succ");
		I x = new I("x");
		return a.simpleLet(
				a.patt(new IdPattern(succ)),
				a.function(new SimpleSimpleMatch<E>(new IdPattern(x), 
													a.add(a.camlI(x), a.camlConstant(new Int(1))))),
				a.camlApply(a.camlI(succ), a.camlConstant(new Int(5))));
	}
	
	<E,D,P,F> E e5(CamlLightAlg<E,D,P,F> a){
		I x = new I("x");
		return a.simpleLet(
				a.patt(new IdPattern(x)),
				a.constant(new Int(7)),
				a.camlI(x));
	}
	
	<E,D,P,F> E e6(CamlLightAlg<E,D,P,F> a){
		I x = new I("x");
		return a.camlApply(a.function(new SimpleSimpleMatch<E>(new IdPattern(x), 
				a.add(a.camlI(x), a.camlConstant(new Int(1))))),
				a.camlConstant(new Int(5)));
	}
	/*
	 let iszero = function 0 -> true | _ -> false
	 in iszero(31)
	*/
	
	<E,D,P,F> E e7(CamlLightAlg<E,D,P,F> a){
		I iszero = new I("iszero");
		return a.simpleLet(
				a.patt(new IdPattern(iszero)),
				a.function(CompoundMatch.create(
							new SimpleSimpleMatch<E>(new ConstantPattern(new Int(0)), 
													   a.camlConstant(new Char('t'))),
							  new SimpleSimpleMatch<E>(new AnyPattern(),
									  				  a.camlConstant(new Char('f'))))),
				a.camlApply(a.camlI(iszero), a.camlConstant(new Int(31))));
	}
	
	/*
	 let iszero = function 0 -> true | _ -> false
	 in iszero(0)
	*/
	
	<E,D,P,F> E e8(CamlLightAlg<E,D,P,F> a){
		I iszero = new I("iszero");
		return a.simpleLet(
				a.patt(new IdPattern(iszero)),
				a.function(CompoundMatch.create(
							new SimpleSimpleMatch<E>(new ConstantPattern(new Int(0)), 
													   a.camlConstant(new Char('t'))),
							  new SimpleSimpleMatch<E>(new AnyPattern(),
									  				  a.camlConstant(new Char('f'))))),
				a.camlApply(a.camlI(iszero), a.camlConstant(new Int(0))));
	}
	
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
		assertEquals(5, ((Int) e2(a).eval(new Triple<Env<Value>,Store,Value>(e,s,v))).getValue());
	}
	
	@Test
	public void test3(){
		CamlLightAlg<IEvalEnvStoreVal, IEvalEnvStoreValRetEnv,IEvalEnvStoreValRetEnv, IEvalEnvStoreVal> a = new CamlLightConcreteAlg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Value result = e4(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v));
		System.out.println(result);
		assertEquals(6, ((Int) e4(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v))).getValue());
		//assertTrue(false);
	}
	
	@Test
	public void test4(){
		CamlLightAlg<IEvalEnvStoreVal, IEvalEnvStoreValRetEnv,IEvalEnvStoreValRetEnv, IEvalEnvStoreVal> a = new CamlLightConcreteAlg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Value result = e7(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v));
		System.out.println(result);
		assertEquals('f', ((Char) e7(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v))).getValue());
		//assertTrue(false);
	}
	
	@Test
	public void test5(){
		CamlLightAlg<IEvalEnvStoreVal, IEvalEnvStoreValRetEnv,IEvalEnvStoreValRetEnv, IEvalEnvStoreVal> a = new CamlLightConcreteAlg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Value result = e8(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v));
		System.out.println(result);
		assertEquals('t', ((Char) e8(a).eval(new Triple<Env<Value>, Store, Value>(e,s,v))).getValue());
		//assertTrue(false);
	}

	@Test
	public void test6(){
		CamlLightAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFailRetEnv,IEvalEnvStoreValFailRetEnv, IEvalEnvStoreValFail> a = new CamlLightConcreteAlgWithFailure(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Failure f = new Failure(false);
		Value result = e7(a).eval(new Quadruple<Env<Value>, Store, Value, Failure>(e,s,v,f));
		System.out.println(result);
		assertEquals('f', ((Char) e7(a).eval(new Quadruple<Env<Value>, Store, Value, Failure>(e,s,v,f))).getValue());
		//assertTrue(false);
	}
	
	@Test
	public void test7(){
		CamlLightAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFailRetEnv,IEvalEnvStoreValFailRetEnv, IEvalEnvStoreValFail> a = new CamlLightConcreteAlgWithFailure(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Failure f = new Failure(false);
		Value result = e8(a).eval(new Quadruple<Env<Value>, Store, Value, Failure>(e,s,v,f));
		System.out.println(result);
		assertEquals('t', ((Char) e8(a).eval(new Quadruple<Env<Value>, Store, Value, Failure>(e,s,v,f))).getValue());
		//assertTrue(false);
	}

	@Test
	public void test8(){
		CamlLightAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMultiRetEnv,IEvalEnvStoreValMultiRetEnv, IEvalEnvStoreValMulti> a = new CamlLightConcreteMultiAlg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Value result = e7(a).eval(e, s, v);
		System.out.println(result);
		assertEquals('f', ((Char) e7(a).eval(e, s , v)).getValue());
		//assertTrue(false);
	}
	
	@Test
	public void test9(){
		CamlLightAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMultiRetEnv,IEvalEnvStoreValMultiRetEnv, IEvalEnvStoreValMulti> a = new CamlLightConcreteMultiAlg(){};
		Env<Value> e = new Env<Value>();
		Store s = new Store();
		Value v = new Int(42);
		Value result = e8(a).eval(e, s, v);
		System.out.println(result);
		assertEquals('t', ((Char) e8(a).eval(e, s, v)).getValue());
		//assertTrue(false);
	}
}
