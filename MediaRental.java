import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.lang.model.type.UnknownTypeException;



public class MediaRental implements MediaRentalInt {


	ArrayList<Customer> customers;
	ArrayList<Media> medias;
	private int limitedValue;

	
	public MediaRental(){
		super();
		
		this.customers = new ArrayList<>();
		this.medias = new ArrayList<>();
		this.limitedValue = 2;
		
	}
	public MediaRental(ArrayList<Customer> customers, ArrayList<Media> medias, int limitedValue) {
		super();
		this.customers = new ArrayList<>();
		this.medias = new ArrayList<>();
		this.limitedValue = 2;
	}


	@Override
	public void addCustomer(String name, String id, String address, String mobileNumber, String plan) throws IOException {
		customers.add(new Customer(name, id, address, mobileNumber, plan));
		getAllCustomerInfo();
	}

	@Override
	public void addMovies(String title, String code, int copiesAvailable, String rating) throws IOException {
		medias.add(new Movie(title, code, copiesAvailable, rating));
		getAllMediaInfo();
	}

	@Override
	public void addGame(String title, String code, int copiesAvailable, double weight) throws IOException {
		medias.add(new Game(title, code, copiesAvailable, weight));
		getAllMediaInfo();
	}

	@Override
	public void addAlbum(String title, String code, int copiesAvailable, String artist, String songs) throws IOException {
		medias.add(new Album(title, code, copiesAvailable, artist, songs));
		getAllMediaInfo();
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		this.limitedValue = value;
	}

	@Override
	public String getAllCustomerInfo() throws IOException {
		File fIn1 = new File("cos.txt");
		FileWriter fc = new FileWriter(fIn1);
		Collections.sort(customers);
		String info = "";
		for(int i=0;i<customers.size();i++) {
			info += customers.get(i).toString()+"\n";
		}
		fc.write(info);
		fc.close();
		return info;
	}

	@Override
	public String getAllMediaInfo() throws IOException {
		
		File fIn2 = new File("med.txt");
		FileWriter fm = new FileWriter(fIn2);
		//Collections.sort(medias);
		String info  = "";
		for(int i=0;i<medias.size();i++) {
			info += medias.get(i).toString() +"\n";
		}
		fm.write(info);
		fm.close();
		return info;
	}

	@Override
	public boolean addToCart(String id, String code) throws Exception {
		int flag = 0;
		try{
			for(int i=0;i<customers.size();i++) {
				if(id.equals(customers.get(i).getId())) {
					flag=i;
					break;
				}
		}
		
			for(int i=0;i<customers.get(flag).interestedIn.size();i++) {
				if(code.equals(customers.get(flag).interestedIn.get(i))) {
					return false;
				}
			}
		}
		catch(Exception e) {
			throw new Exception("ERROR CUSTOMER NAME DOESN'T EXIST ");
		}
		customers.get(flag).interestedIn.add(code);
		getAllCartInfo();
		return true;
	}
	public String getAllCartInfo() throws IOException {
		File fIn3 = new File("cart.txt");
		FileWriter fCart = new FileWriter(fIn3);
		String cartInfo="";
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).interestedIn.toString() == "[]") continue;
				cartInfo+=customers.get(i).getId()+","+ customers.get(i).interestedIn+"\n";
			
			
		}
		fCart.write(cartInfo);
		fCart.close();
		return cartInfo;
	}

	@Override
	public boolean removeFromCart(String customerName, String mediaTitle) {
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getName().compareTo(customerName) == 0) {
				customers.get(i).interestedIn.remove(mediaTitle);
				return true;
			}
		}
		return false;
	}

	@Override
	public String processRequest(String id, String code) throws Exception {
		String returnedString = "";int flag1,flag2;
		try {
			for(int i=0;i<customers.size();i++) {
				if(id.equals(customers.get(i).getId())) {
					flag1=i;
					break;
				}
			}
			for(int j=0;j<medias.size();j++) {
				if(code.equals(medias.get(j).getCode())) {
					flag2=j;
					break;
				}
			}
		
		}
		catch(Exception e) {
			throw new Exception("!! ERROR !! Information doesn't exist at all");
		}
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getPlan().equals("LIMITED") && customers.get(i).getId().equals(id)) {
				for(int j=0;j<medias.size();j++) {
					if(customers.get(i).interestedIn.size() <= customers.get(i).limitedPlan && customers.get(i).interestedIn.size() <= medias.get(j).getNumberOfCopiesAvailable() && medias.get(j).getCode().equals(code)) {
						customers.get(i).rented.add(medias.get(j).getCode());
						customers.get(i).interestedIn.remove(medias.get(j).getCode());
					   //String justTemp = medias.get(j).getTitle();
					   medias.get(j).setNumberOfCopiesAvailable(false);
					   returnedString += "Sending: [" + medias.get(j).getCode() + "] to [" + customers.get(i).getId() + "]"+"\n";
					   getAllRentedInfo();
					   return returnedString;
					}
				}
			}
		}
	
		for(int k=0;k<customers.size();k++) {
			if(customers.get(k).getPlan().equals("UNLIMITED") && customers.get(k).getId().equals(id)) {
				for(int j=0;j<medias.size();j++) {
					if(customers.get(k).interestedIn.size() <= medias.get(j).getNumberOfCopiesAvailable() && medias.get(j).getCode().equals(code)) {
						customers.get(k).rented.add(medias.get(j).getCode());
						customers.get(k).interestedIn.remove(medias.get(j).getCode());
					   String justTemp = medias.get(j).getTitle();
					   medias.get(j).setNumberOfCopiesAvailable(false);
					   returnedString += "Sending: [" + medias.get(j).getCode() + "] to [" + customers.get(k).getId() + "]"+"\n";
					   getAllRentedInfo();
					   return returnedString;
					}
				}
			}
		}
		//getAllRentedInfo();
		return returnedString;
	
	}
	public String getAllRentedInfo() throws IOException {
		File fIn4 = new File("rented.txt");
		FileWriter fRented = new FileWriter(fIn4);
		String rentedInfo="";
		for(int i=0;i<customers.size();i++) {
			
			if(customers.get(i).rented.toString() =="[]") continue;
			rentedInfo+=customers.get(i).getId()+","+ customers.get(i).rented+"\n";
			
		}
		fRented.write(rentedInfo);
		fRented.close();
		return rentedInfo;
	}

	@Override
	public boolean returnMedia(String id, String code){
		
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getId().equals(id)) {
				for(int j=0;i<customers.get(j).rented.size();j++) {
					if(customers.get(j).rented.get(j).equals(code)) {
						customers.get(j).rented.remove(code);
						for(int k=0;k<medias.size();k++) {
							if(medias.get(k).getCode().equals(code)) {
								medias.get(k).setNumberOfCopiesAvailable(true);
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> sortedMedia = new ArrayList<>();
		//int tit=0,rat=0,art=0,song=0;
		for(int i=0;i<medias.size();i++) {
			int tit=0,rat=0,art=0,song=0,sum=0;
			if(medias.get(i) instanceof Movie) {
				Movie movie = (Movie) medias.get(i);
			    if(title == null || medias.get(i).getTitle().equals(title)) {
			    	tit++;
			    }
			    if(rating == null || movie.getRating().equals(rating)) {
			    	rat++;
			    }
			    if(artist == null) art++;
			    if(songs == null) song++;
			}
			else if(medias.get(i) instanceof Album) {
				Album album = (Album) medias.get(i);
				if(title == null || medias.get(i).getTitle().equals(title)) {
			    	tit++;
			    }
			    if(artist == null || album.getArtist().equals(artist)) {
			    	art++;
			    }
			    if(songs == null || album.getSongs().equals(songs)) song++;
			    if(rating == null) rat++;
			}
			sum = tit+rat+art+song;
			if(sum == 4) {
				sortedMedia.add(medias.get(i).getTitle());
			}
			
		}
		
		Collections.sort(sortedMedia);
		return sortedMedia;
	}

}