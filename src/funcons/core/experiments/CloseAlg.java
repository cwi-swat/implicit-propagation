package funcons.core.experiments;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnv;

/*
 public interface CloseAlg extends AbsAlg<IEvalBasicEnv, IEvalEnv>,
 ClosureAlg<IEvalBasicEnv,IEvalEnv>{
 default IEvalEnv close(Abs<IEvalEnv,IEvalEnv> x){
 return 
 rho ->
 abs(closure(x, ()->rho)).eval(rho);
 }
 }
 */

public interface CloseAlg<F> {
	F close(F f);
}

interface IEvalEnvRetSame extends Abstraction<Env<Value>, IEvalEnv> {
	IEvalEnv eval(Env<Value> env);
}



/*
 * interface CloseAlgConcrete extends AbsAlg<Env<Value>,IEvalEnv,
 * IEvalEnvRetSame, ParameterlessAbstraction<IEvalEnvRetSame>>,
 * ClosureAlg<IEvalEnvRetSame, Declaration>, CloseAlg<IEvalEnvRetSame>{ default
 * IEvalEnvRetSame close(IEvalEnvRetSame x){ return (rho)->{ return
 * abs(closure(x, new EnvDeclaration(rho))); }; } }
 */