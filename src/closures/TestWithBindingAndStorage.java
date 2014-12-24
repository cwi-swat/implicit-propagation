package closures;

import modules.arith.ArithAlg;
import modules.arith.EvalArith;
import modules.arith.IEvalBase;
import modules.arith.Int;
import modules.binding.Env;
import modules.binding.EvalLet;
import modules.binding.IEvalBinding;
import modules.binding.LetAlg;
import modules.sequence.EvalSeq;
import modules.sequence.SeqAlg;
import modules.storage.EvalStorage;
import modules.storage.IEvalStore;
import modules.storage.Store;
import modules.storage.StoreAlg;
import modules.values.Value;

class Interpreter2 implements EvalArithWithBindingAndStorage, 
	EvalLetWithStorage, EvalSeqWithBindingAndStorage, 
	EvalStorageWithBinding {


	@Override
	public StoreAlg<IEvalStore> storeAlg() {
		return new EvalStorage() { };
	}

	@Override
	public SeqAlg<IEvalBase> seqAlg() {
		return new EvalSeq() { };
	}

	@Override
	public LetAlg<IEvalBinding> letAlg() {
		return new EvalLet() { };
	}

	@Override
	public ArithAlg<IEvalBinding> alg() {
		return new EvalArithWithBinding() {
			
			@Override
			public ArithAlg<IEvalBase> alg() {
				return new EvalArith() { };
			}
			
		};
	}
	
}

public class TestWithBindingAndStorage {
	private static void test(IEvalBindingAndStorage exp) {
		try {
			Value x = exp.eval(new Env().bind("x", new Int(4)), new Store());
			System.out.println("x = " + x);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Interpreter2 x = new Interpreter2();
		test(x.var("x")); // 4
		test(x.let("x", x.var("x"), x.var("x"))); // 4 
		test(x.add(x.lit(1), x.var("x"))); // 5
		test(x.add(x.lit(1), x.lit(2))); // 3
		test(x.let("x", x.lit(3), x.add(x.lit(1), x.var("x")))); // 4
		test(x.let("x", x.create(), x.seq(x.update(x.var("x"), x.lit(23)), x.add(x.lit(1), x.deref(x.var("x")))))); // 24
	}
}
