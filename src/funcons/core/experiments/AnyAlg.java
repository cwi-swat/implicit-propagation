package funcons.core.experiments;

public interface AnyAlg<To,X> extends AbsAlg<To, X>,EmptyEnvAlg<To>{
	default X any(){
		return  abs(emptyEnv());
	}
}