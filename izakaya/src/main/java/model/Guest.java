package model;

public class Guest {
	
	private String table ;
	private int number ;
	
	public Guest(){}
	
	public Guest(String table,int number) {
		this.table = table;
		this.number = number;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
