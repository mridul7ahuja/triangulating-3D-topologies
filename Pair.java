
public class Pair<A,B> {
	A one; 
	B two;
	public Pair(A one, B two) {
		this.one = one;
		this.two = two;
	}
	public A key() {
		return one;
	}
	public B elem() {
		return two;
	}

}
