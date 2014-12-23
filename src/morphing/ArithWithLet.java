package morphing;

import modules.binding.Env;
import modules.binding.EvalLet;
import modules.binding.IEvalBinding;
import modules.values.Int;
import modules.values.Value;

public class ArithWithLet implements EvalArithWithBindingManual, EvalLet {
	
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
		ArithWithLet awl = new ArithWithLet();
		test(awl.var("x")); // 4
		test(awl.let("x", awl.var("x"), awl.var("x"))); // 4 
		test(awl.add(awl.lit(1), awl.var("x"))); // 5
		test(awl.add(awl.lit(1), awl.lit(2))); // 3
		test(awl.let("x", awl.lit(3), awl.add(awl.lit(1), awl.var("x")))); // 4
		
	}
}
