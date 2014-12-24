package desugaring;

import desugaring.lifted.AddStoreToBinding;
import desugaring.lifted.LiftEvalComp;
import desugaring.lifted.LiftEvalCond;
import desugaring.lifted.LiftEvalLet;
import desugaring.lifted.LiftEvalSeq;
import desugaring.lifted.LiftEvalSkip;
import desugaring.lifted.LiftEvalStorage;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;
import modules.binding.LetAlg;
import modules.comparison.ComparisonAlg;
import modules.conditional.ConditionalAlg;
import modules.sequence.SeqAlg;
import modules.skip.SkipAlg;
import modules.storage.IEvalStore;
import modules.storage.StoreAlg;
import modules.whiledo.EvalWhile;

public class Eval implements LiftEvalComp, LiftEvalCond, LiftEvalLet, LiftEvalSkip, LiftEvalSeq, LiftEvalStorage {

	@Override
	public StoreAlg<IEvalStore> storeAlg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeqAlg<IEvalBase> seqAlg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkipAlg<IEvalBase> skipAlg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LetAlg<IEvalBinding> letAlg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConditionalAlg<IEvalBase> condAlg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComparisonAlg<IEvalBase> compAlg() {
		// TODO Auto-generated method stub
		return null;
	}

}
