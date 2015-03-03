package funcons.core.experiments;

public class TestOO {
	public static void main(String[] args){
		B_ a = new B_();
		System.out.println(new Tester().go(a));
	}

}

class A_{
	
}

class B_ extends A_{
	
}

class C_ extends A_{
	
}

class Tester{
	String go(A_ a){
		return "A";
	}
	String go(B_ b){
		return "B";
	}
	String go(C_ c){
		return "C";
	}
}