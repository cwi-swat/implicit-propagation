package desugaring.lifted;

import modules.arith.IEvalBase;
import modules.comparison.ComparisonAlg;
import desugaring.IEvalBindingAndStorage;

public interface LiftEvalComp extends ComparisonAlg<IEvalBindingAndStorage>, AddEnvToBase, AddStoreToBinding {
	ComparisonAlg<IEvalBase> compAlg();
	
	@Override
	default IEvalBindingAndStorage lt(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().lt(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}

	@Override
	default IEvalBindingAndStorage gt(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().gt(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}

	@Override
	default IEvalBindingAndStorage leq(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().leq(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}

	@Override
	default IEvalBindingAndStorage geq(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().geq(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}

	@Override
	default IEvalBindingAndStorage eq(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().eq(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}

	@Override
	default IEvalBindingAndStorage neq(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return addStoreToBinding(addEnvToBase(compAlg().neq(removeEnvToBase(removeStoreToBinding(l)), removeEnvToBase(removeStoreToBinding(r))))); 
	}
}
