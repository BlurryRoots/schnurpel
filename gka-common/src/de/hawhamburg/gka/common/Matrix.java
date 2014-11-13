package de.hawhamburg.gka.common;

import java.util.ArrayList;

public class Matrix <T> {
	private
	ArrayList<ArrayList<T>> raw;
	
	public
	Matrix (int c, T value) {
		this.raw = new ArrayList<ArrayList<T>> (c);
		
		for (int i = 0; i < c; ++i) {
			ArrayList<T> column = new ArrayList<T>();				
			for (int j = 0; j < c; ++j) {
				column.add (value);			
			}
			
			raw.add(column);
		}
	}
	
	public
	void insert (int row, int column, T value) {
		this.raw.get (column).remove (row);
		this.raw.get (column).add (row, value);
	}
	
	public
	T retrieve (int row, int column) {
		return this.raw.get (column).get (row);
	}
	
	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder ();
		
		for (ArrayList<T> column : this.raw) {
			int columnIndex = 0;
			for (T value : column) {
				int rowIndex = 0;
				builder
					//.append (rowIndex)
					.append (value)
					.append (" | ");
			}
			builder.append ("\n");
		}
		
		return builder.toString ();
	}
}
