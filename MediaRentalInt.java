import java.io.IOException;
import java.util.ArrayList;

public interface MediaRentalInt {
	void addCustomer(String name, String id, String address, String mobileNumber, String plan) throws IOException;
	void addMovies(String title, String code, int copiesAvailable, String rating) throws IOException;
	void addGame(String title, String code, int copiesAvailable, double weight) throws IOException;
	void addAlbum(String title, String code, int copiesAvailable, String artist, String songs) throws IOException;
	
	void setLimitedPlanLimit(int value);
	String getAllCustomerInfo() throws IOException;
	String getAllMediaInfo() throws IOException;
	
	boolean addToCart(String customerName, String mediaTitle) throws Exception;//returns false if the media is already exists
	boolean removeFromCart(String customerName, String mediaTitle);//returns false if the removal failed ( C.N NOT FOUND )
	
	String processRequest(String id, String code) throws Exception;
	boolean returnMedia(String customerName, String mediaTitle);
	ArrayList<String> searchMedia(String title, String rating, String artist, String songs);
	
	
	
	
	

}
