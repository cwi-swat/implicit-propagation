package modules.jumps;

import java.util.HashMap;
import java.util.Map;

import modules.arith.IEvalBase;
import modules.values.Value;
import modules.skip.Null;

public interface EvalMultiJump extends MultiJumpAlg<IEvalBase> {

	@Override
	default IEvalBase jump(String label) {
		return () -> { throw new Jump(label); };
	}
	
	@Override
	default IEvalBase labeled(Pair<String, IEvalBase> ...pairs) {
		Map<String, Integer> table = new HashMap<>();
		int i = 0;
		// Assumes labels are unique in pairs
		for (Pair<String, IEvalBase> p: pairs) {
			table.put(p.a(), i);
			i++;
		}
		return () -> {
			int j = 0;
			Value v = new Null();
			while (j < pairs.length) {
				try {
					v = pairs[j].b().eval();
				}
				catch (Jump e) {
					if (!table.containsKey(e.getLabel())) {
						throw e;
					}
					j = table.get(e.getLabel());
					continue;
				}
				j++;
			}
			return v;
		};
	}
}
