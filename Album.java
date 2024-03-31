
public class Album extends Media implements Comparable<Object>{

	private String artist;
	private String songs;
	public Album(String title, String code, int numberOfCopiesAvailable, String artist, String songs) {
		super(title, code, numberOfCopiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getSongs() {
		return songs;
	}
	public void setSongs(String songs) {
		this.songs = songs;
	}
	
	@Override
	public String toString() {
		return "Album:"+super.getTitle()+","+super.getCode()+","+super.getNumberOfCopiesAvailable()+","+this.artist+","+this.songs;
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Media)
			return this.getTitle().compareTo(((Album)o).getTitle());
		return 0;
	
	}

	
	

}
