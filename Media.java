
public abstract class Media implements Comparable<Object>{
	private String title;
	private String code;
	private int numberOfCopiesAvailable;
	
	public Media(String title, String code, int numberOfCopiesAvailable) {
		super();
		this.title = title;
		this.code = code;
		this.numberOfCopiesAvailable = numberOfCopiesAvailable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfCopiesAvailable() {
		return numberOfCopiesAvailable;
	}

	public void setNumberOfCopiesAvailable(boolean b) {
		if(b == true) {
			numberOfCopiesAvailable++;
		}
		else
			numberOfCopiesAvailable--;
	}
	public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
		
			this.numberOfCopiesAvailable = numberOfCopiesAvailable;
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public abstract int compareTo(Object o);
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Media) {
			return this.getTitle() == ((Media)obj).getTitle();
		}
		return false;
	}

	

}
