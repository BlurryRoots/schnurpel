package de.hawhamburg.gka.common;

import org.jgrapht.graph.DefaultEdge;

public
class CustomEdge
	extends DefaultEdge {

	private
	String name;
	private
	int cost;
	
	public
	CustomEdge (String name, int weight) {
		super ();
		this.name = name;
		this.cost = weight;
	}
	
	public
	CustomEdge (String name) {
		this (name, 1);
	}
	
	public
	CustomEdge (int weight) {
		this ("NONAME", weight);
	}
	
	public
	CustomEdge () {
		this (1);
	}
	
	public
	String getName () {
		return this.name;
	}
	
	public
	void setCost (int cost) {
		this.cost = cost;
	}
	
	public
	int getCost () {
		return this.cost;
	}
	
	public
	String getSource () {
		String s = (String)super.getSource ();
		return null != s ? s.toString () : null;
	}
	
	public
	String getTarget () {
		String s = (String)super.getTarget ();
		return null != s ? s.toString () : null;
	}
	
	@Override
	public
	String toString () {
		StringBuilder b = new StringBuilder ()
			.append ("(")
			.append (this.getSource ()).append (",")
			.append (this.getTarget ()).append (":")
			.append (this.name).append ("@")
			.append (this.getCost ())
			.append (")");
		
		return b.toString ();
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (null == o) {
			return false;
		}
		
		if (this == o) {
			return true;
		}
		
		if (! this.getClass().equals (o.getClass())) {
			return false;
		}
		
		CustomEdge other = (CustomEdge)o;
		boolean equalSource = null != this.getSource ()
			? this.getSource ().equals (other.getSource ())
			: null == other.getSource ();
		boolean equalTarget = null != this.getTarget ()
			? this.getTarget ().equals (other.getTarget ())
			: null == other.getTarget ();
			
		return
			equalSource && equalTarget &&
			this.getName ().equals (other.getName ()) &&
			this.getCost () == other.getCost ();
	}
	
	@Override
	public int hashCode()
	{
		int result = 42;
		
		if (null != super.getSource ()) {
			result += this.getSource ().hashCode ();			
		}
		if (null != super.getTarget ()) {
			result += this.getTarget ().hashCode ();
		}
		result += this.getCost ();
		result += this.getName ().hashCode ();
		
		return result;
	}
}
