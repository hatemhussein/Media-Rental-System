
public class Movie extends Media implements Comparable<Object>{
	
	private String rating;

	public Movie(String title, String code, int numberOfCopiesAvailable, String rating) {
		super(title, code, numberOfCopiesAvailable);
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	

	@Override
	public String toString() {
		return "Movie:"+super.getTitle()+","+super.getCode()+","+super.getNumberOfCopiesAvailable()+","+this.rating;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Media)
			return this.getTitle().compareTo(((Movie)o).getTitle());
		return 0;
	}
	
	
	
	

}
