package de.hawhamburg.gka.common;

public class Pair {
	public final
	String a;
	public final
	String b;
	
	public
	Pair (String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public
	boolean equals (Object obj) {
		if (null == obj ||
			! obj.getClass ().equals (this.getClass ())) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		Pair other = (Pair) obj;
		return 
			this.a.equals (other.a) &&
			this.b.equals (other.b);
	}
	
	@Override
	public
	int hashCode () {
		int value = 41;
		
		value += this.a.hashCode ();
		value += this.b.hashCode ();
		
		return value;
	}
}
