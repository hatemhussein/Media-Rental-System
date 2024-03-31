import java.util.ArrayList;

public class Customer implements Comparable<Object>{
	private String name;
	private String id;
	private String address;
	private String mobileNumber;
	private String plan;
	ArrayList<String> interestedIn;
	ArrayList<String> rented;
	int limitedPlan;

	
	
	public Customer(String name, String id, String address, String mobileNumber, String plan) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.plan = plan;
		this.interestedIn = new ArrayList<>();
		this.rented = new ArrayList<>();
		if(plan == "LIMITED") {
			this.limitedPlan = 2;
		}
	}
	
	public Customer(String name, String id, String address, String mobileNumber) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public ArrayList<String> getInterestedIn() {
		return interestedIn;
	}
	public void setInterestedIn(ArrayList<String> interestedIn) {
		this.interestedIn = interestedIn;
	}
	public ArrayList<String> getRented() {
		return rented;
	}
	public void setRented(ArrayList<String> rented) {
		this.rented = rented;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

	@Override
	public String toString() {
		return name + "," + id + "," + address + "," + mobileNumber
				+ "," + plan ;
	}

	@Override
	public int compareTo(Object o) {
		
		return this.getName().compareTo(((Customer)o).getName());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			return this.getName() == ((Customer)obj).getName();
		}
		return false;
	}
	
	
	

}
