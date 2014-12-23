package proxying;

import modules.arith.EvalArith;
import modules.arith.Int;
import modules.binding.Env;
import modules.binding.EvalLet;
import modules.binding.IEvalBinding;
import modules.values.Value;

public class Test {
	private static void test(IEvalBinding exp) {
		try {
			Value x = exp.eval(new Env().bind("x", new Int(4)));
			System.out.println("x = " + x);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EvalArithWithBinding arith = EvalArithWithBinding.make(new EvalArith() { });
		EvalLet bind = new EvalLet() { };
		test(bind.var("x")); // 4
		test(bind.let("x", bind.var("x"), bind.var("x"))); // 4 
		test(arith.add(arith.lit(1), bind.var("x"))); // 5
		test(arith.add(arith.lit(1), arith.lit(2))); // 3
		test(bind.let("x", arith.lit(3), arith.add(arith.lit(1), bind.var("x")))); // 4
		
	}
}
