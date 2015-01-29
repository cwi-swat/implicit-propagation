package objs;

import modules.arith.ArithAlg;
import modules.arith.EvalArith;
import modules.arith.IEvalBase;
import modules.arith.Int;
import modules.binding.Env;
import modules.binding.EvalLet;
import modules.binding.IEvalBinding;
import modules.values.Value;


class Interpreter implements EvalArithWithBinding, EvalLet {

	@Override
	public ArithAlg<IEvalBase> alg() {
		return new EvalArith() { };
	}
	
}

public class TestWithBinding {
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
		Interpreter x = new Interpreter();
		test(x.var("x")); // 4
		test(x.let("x", x.var("x"), x.var("x"))); // 4 
		test(x.add(x.lit(1), x.var("x"))); // 5
		test(x.add(x.lit(1), x.lit(2))); // 3
		test(x.let("x", x.lit(3), x.add(x.lit(1), x.var("x")))); // 4
	}
	
}
