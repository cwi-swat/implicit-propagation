package ordinary;

import modules.arith.Int;
import modules.binding.Env;
import modules.values.Value;
import ordinary.binding.Add;
import ordinary.binding.IEvalBinding;
import ordinary.binding.Let;
import ordinary.binding.Lit;
import ordinary.binding.Var;


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
		test(new Var("x")); // 4
		test(new Let("x", new Var("x"), new Var("x"))); // 4 
		test(new Add(new Lit(1), new Var("x"))); // 5
		test(new Add(new Lit(1), new Lit(2))); // 3
		test(new Let("x", new Lit(3), new Add(new Lit(1), new Var("x")))); // 4
	}
}
