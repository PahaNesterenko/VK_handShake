package ua.pasha.VKHandShake;

public class User {
	
	private int id = 0;
	private String name = null;
	private String lastName = null;
	private boolean isDeactivated = false;
	
	public boolean isDeactivated() {
		return isDeactivated;
	}

	public void setDeactivated(boolean isDeactivated) {
		this.isDeactivated = isDeactivated;
	}

	public User(){ 
		
	}
	
	public User(int id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString(){
		return "user id - " + id + " name - " + name + " last name - " + lastName;
	}
	
}
