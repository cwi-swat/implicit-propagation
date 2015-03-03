package funcons.core.experiments;

public interface PreferOverAlg<P,To> extends ElseAlg<To>, AbsAlg<To, P> {
	default P preferOver(P p1, P p2){
		return abs(else_(getAbstracted(p1), getAbstracted(p2)));
	}
}
