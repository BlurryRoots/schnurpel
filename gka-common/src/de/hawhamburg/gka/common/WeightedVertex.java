package de.hawhamburg.gka.common;

public 
class WeightedVertex {
	public final
	String name;
	public final
	WeightedVertex parent;
	public final
	int cost;
	
	public
	WeightedVertex (String name, WeightedVertex parent, int cost) {
		this.name = name;
		this.parent = parent;
		this.cost = cost;
	}
	
	@Override
	public
	boolean equals (Object obj) {
		if (null == obj) {
			return false;
		}
		
		if (! obj.getClass ().equals (this.getClass ())) {
			return false;
		}
		
		WeightedVertex other = (WeightedVertex) obj;
		
		return 
			this.name.equals (other.name) &&
			this.cost == other.cost;
	}
	

	private final static
	int HASH_PRIME = 331;
	
	@Override
	public
	int hashCode () {
		int result = HASH_PRIME;
		
		result += this.name.hashCode ();
		
		if (null != this.parent) {
			result += this.parent.hashCode ();
		}
		
		result += this.cost;
		
		return result;
	}
}
