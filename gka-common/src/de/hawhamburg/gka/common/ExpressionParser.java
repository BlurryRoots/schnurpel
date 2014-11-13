package de.hawhamburg.gka.common;

public
class ExpressionParser {
	private static
	enum State {
		NameA,
		NameB,
		EdgeType,
		EdgeName,
		EdgeWeight,
		Finish,
		Error
	}
	
	private
	String expression;
	private
	int currentPosition;
	private
	int length;
	
	private
	String nodeA;
	private
	String nodeB;
	
	private
	int direction;
	private
	String edgeName;
	private
	String edgeWeigth;
	
	public
	ExpressionParser (String expression) {
		this.expression = expression;
		this.currentPosition = 0;
		this.length = this.expression.length ();
		
		this.nodeA = "";
		this.nodeB = "";
		this.edgeName = "";
		this.edgeWeigth = "";
		
		this.direction = 0;
		
		this.parse ();
	}
	
	public
	String getSource () {
		return this.nodeA;
	}
	
	public
	boolean hasTarget () {
		return ! this.nodeB.isEmpty ();
	}
	
	public
	String getTarget () {
		return this.nodeB;
	}
	
	public
	boolean hasEdgeName () {
		return ! this.edgeName.isEmpty ();
	}
	
	public
	String getEdgeName () {
		return this.edgeName;
	}
	
	public
	boolean hasEdgeWeight () {
		return ! this.edgeWeigth.isEmpty ();
	}
	
	public
	int getEdgeWeight () {
		int value;
		
		try {
			value = Integer.valueOf (this.edgeWeigth);
		}
		catch (NumberFormatException ex) {
			value = 0;
		}
		
		return value;
	}
	
	public
	boolean hasDirection () {
		return this.direction == 1;
	}
	
	private
	boolean isEOF () {
		return this.currentPosition == this.length;
	}
	
	private
	char getCurrent () {
		return this.expression.charAt (this.currentPosition);
	}
	
	private
	boolean hasLookahead () {
		return this.currentPosition < (this.length - 1);
	}
	
	private
	char getLookahead () {
		return this.expression.charAt (this.currentPosition + 1);		
	}
	
	private
	boolean eatWhitespace () {
		boolean yumyum = false;
		
		if (this.isEOF ()) {
			throw new RuntimeException ("Something fishy going on here!");
		}
		
		char c = this.getCurrent ();
		if (Character.isWhitespace (c)) {
			++this.currentPosition;
			
			if (this.isEOF ()) {
				System.err.println ("This is bad!");
				throw new RuntimeException ("I ate to much!");
			}
			
			yumyum = true;
		}
		
		return yumyum;
	}
	
	private
	void parse () {
		State state = State.NameA;
		boolean run = true;
		
		while (run) {
			switch (state) {
				case Error:
					throw new RuntimeException ("ExpressionParser error!");
				case NameA:
					state = this.readNodeA ();
					break;
				case EdgeType:
					state = this.readEdgeType ();
					break;
				case NameB:
					state = this.readNodeB ();
					break;
				case EdgeName:
					state = this.readEdgeName ();
					break;
				case EdgeWeight:
					state = this.readEdgeWeight ();
					break;
				case Finish:
					run = false;
					break;
				
				default:
					throw new RuntimeException ("Cthulu will eat you alive!");
			}
			
			++this.currentPosition;
		}
	}
	
	private
	State readNodeA () {
		if (this.isEOF ()) {
			return State.Finish;
		}
		
		char c = this.getCurrent ();
		
		if ('-' == c) {			
			return this.readEdgeType ();
		}
		
		if (Character.isWhitespace (c)) {
			++this.currentPosition;
			while (this.eatWhitespace ()) {}
			
			if (this.isEOF ()) {
				return State.Finish;
			}
			
			return this.readEdgeType ();
		}
		
		this.nodeA += c;		
		
		return State.NameA;
	}
	
	private
	State readEdgeType () {
		char c = this.getCurrent ();
		
		if (! this.hasLookahead ()) {
			throw new RuntimeException ("NO LOOKAHEAD! WOOT!?");
		}
		char la = this.getLookahead ();
		
		if ('-' == c && la == '>') {
			this.direction = 1;
		}
		else if ('-' == c && la == '-') {
			this.direction = 2;
		}
		else {
			System.err.println ("Syntax error! Unknown edge type.");
			return State.Error;
		}
		
		// eat both edge sign
		this.currentPosition += 2;
		while (this.eatWhitespace ()) {}
		
		return this.readNodeB ();
	}
	
	private
	State readNodeB () {		
		if (this.isEOF ()) {
			return State.Finish;
		}
		
		char c = this.getCurrent ();
		
		if (Character.isWhitespace (c)) {
			++this.currentPosition;
			while (this.eatWhitespace ()) {}
			
			c = this.getCurrent ();
			
			if ('(' == c) {
				return State.EdgeName;
			}
			
			if (':' == c) {
				++this.currentPosition;
				while (this.eatWhitespace ()) {}
				
				if (this.isEOF ()) {
					System.err.println ("Missing edge weight!");
					return State.Error;
				}
				
				return this.readEdgeWeight ();
			}
			
			if (this.isEOF ()) {
				return State.Finish;
			}
			
			System.err.println ("No whitespaces in name allowed!");
			return State.Error;
		}
				
		this.nodeB += this.getCurrent ();		
	
		return State.NameB;
	}
	
	private
	State readEdgeName () {		
		if (this.isEOF ()) {
			System.err.println ("Missing closing bracked in edge name!");
			return State.Error;
		}
		
		char c = this.getCurrent ();	
				
		if (')' == c) {
			++this.currentPosition;
			
			if (this.isEOF ()) {
				return State.Finish;
			}
			
			while (this.eatWhitespace ()) {}
			
			if (this.isEOF ()) {
				return State.Finish;
			}
			
			c = this.getCurrent ();
			if (':' == c) {
				++this.currentPosition;
				while (this.eatWhitespace ()) {}
				
				if (this.isEOF ()) {
					System.err.println ("Missing edge weight!");
					return State.Error;
				}

				return this.readEdgeWeight ();
			}
			
			System.err.println ("WEIRD");
			return State.Error;
		}
		
		this.edgeName += this.getCurrent ();
		
		return State.EdgeName;
	}
	
	private
	State readEdgeWeight () {
		if (this.isEOF ()) {
			return State.Finish;
		}
		
		if (Character.isDigit (this.getCurrent ())) {
			this.edgeWeigth += this.getCurrent ();
			
			return State.EdgeWeight;
		}
		
		System.err.println ("Number expected!");
		
		return State.Error;
	}
}
