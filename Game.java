
public class Game extends Media implements Comparable<Object>{

	private double weight;//grams

	public Game(String title, String code, int numberOfCopiesAvailable, double weight) {
		super(title, code,  numberOfCopiesAvailable);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Media)
			return this.getTitle().compareTo(((Game)o).getTitle());
		return 0;
	
	}

	@Override
	public String toString() {
		return "Game:"+super.getTitle()+","+super.getCode()+","+super.getNumberOfCopiesAvailable()+","+this.weight;
	}
	

	

}
