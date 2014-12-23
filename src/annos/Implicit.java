package annos;


/*
 * @Implicit(name="generated.EvalArithWithBinding", base=EvalArith.class)
interface IEvalArithWithBinding extends ArithAlg<IEvalBinding> { }
 */
public @interface Implicit {
	String output();
	Class<?> base();
	Class<?> op();
}
