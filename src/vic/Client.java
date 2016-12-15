package vic;

public class Client {
	
	private static int currentId = 0;
	private final int id;
	private final String lastName;
	private final String firstName;
	private final String address;
	
	public Client (String lastName, String firstName, String address){
		this.id = currentId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		currentId ++;
	}

	public int getId(){
		return this.id;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String toString(){
		return getFirstName()+" "+getLastName();
	}
	
	public String getFullString(){
		return String.valueOf(getId())+" "+toString()+" ("+getAddress()+")";
	}
	
}
