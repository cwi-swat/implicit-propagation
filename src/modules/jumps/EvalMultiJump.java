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
		// Assumes labels are unique in pairs

		Map<String, Integer> table = new HashMap<>();
		for (int i = 0; i < pairs.length; i++) {
			table.put(pairs[i].a(), i);
			i++;
		}
		
		return () -> {
			int i = 0;
			Value v = new Null();
			while (i < pairs.length) {
				try {
					v = pairs[i].b().eval();
				}
				catch (Jump e) {
					if (!table.containsKey(e.getLabel())) {
						throw e;
					}
					i = table.get(e.getLabel());
					continue;
				}
				i++;
			}
			return v;
		};
	}
}
