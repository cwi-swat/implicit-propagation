package ordinary.binding;


public class Add extends ordinary.arith.Add implements ArithExpr {

	public Add(IEvalBinding l, IEvalBinding r) {
		super(l, r);
	}

}
