
import java.awt.event.ItemListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Driver extends Application{
	static Scanner s = new Scanner(System.in); 
    static MediaRental mediaRentalSystem = new MediaRental();
    
	public static void main(String[] args) throws Exception {
		char[] arr = {'a','3','A','D'};
		Arrays.sort(arr);
		System.out.print(arr.toString() );
		Application.launch(args);
	
	}

	@Override
	public void start(Stage stage) throws Exception {
		//CUSTOMERS FILE READING
		File fIn1 = new File("cos.txt");
		if(!fIn1.exists()) {
			fIn1.createNewFile();
		}
		Scanner cs =new Scanner(fIn1);
		while(cs.hasNext()) {
			String n =cs.nextLine();
			String [] kk = n.split(",");
			String i =kk[0];
			String na =kk[1];
			String  a=kk[2];
			String mb =kk[3];
			String pl =kk[4];
			mediaRentalSystem.addCustomer(i, na, a, mb, pl);
		}
		cs.close();
		//MEDIA FILE READING
		File fIn2 = new File("med.txt");
		if(!fIn2.exists()) {
			fIn2.createNewFile();
		}
		Scanner med =new Scanner(fIn2);
		while(med.hasNext()) {
			String wholeLine =med.nextLine();
			String [] splittedLine = wholeLine.split(":");
			
			if(splittedLine[0].equals("Movie")) {
				String[] splittedLineMovie = splittedLine[1].split(",");
				String tit =splittedLineMovie[0];
				String code =splittedLineMovie[1];
				String  cop =splittedLineMovie[2];
				String rat =splittedLineMovie[3];
				
				mediaRentalSystem.addMovies(tit, code, Integer.parseInt(cop), rat);
			}
			if(splittedLine[0].equals("Album")) {
				String[] splittedLineAlbum = splittedLine[1].split(",");
				String tit =splittedLineAlbum[0];
				String code =splittedLineAlbum[1];
				String  cop =splittedLineAlbum[2];
				String art =splittedLineAlbum[3];
				String son =splittedLineAlbum[4];
				mediaRentalSystem.addAlbum(tit, code, Integer.parseInt(cop), art, son);
			}
			if(splittedLine[0].equals("Game")){
				String[] splittedLineGame = splittedLine[1].split(",");
				String tit =splittedLineGame[0];
				String code =splittedLineGame[1];
				String  cop =splittedLineGame[2];
				String wei =splittedLineGame[3];
				mediaRentalSystem.addGame(tit, code, Integer.parseInt(cop), Double.parseDouble(wei));
			}

		}
		med.close();
		//CART FILE
				File fIn3 = new File("cart.txt");
				if(!fIn3.exists()) {
					fIn3.createNewFile();
				}
				Scanner cart =new Scanner(fIn3);
				while(cart.hasNext()) {
					String cartWholeline =cart.nextLine();
					String [] cartSplittedLine1 = cartWholeline.split(",");
					String [] cartSplittedLine2 = cartSplittedLine1[1].split("\\[");
					String [] cartSplittedLine3 = cartSplittedLine2[1].split("\\]");
					mediaRentalSystem.addToCart(cartSplittedLine1[0], cartSplittedLine3[0]);
				}
				cart.close();
	    //RENTED FILE
				File fIn4 = new File("rented.txt");
				if(!fIn4.exists()) {
					fIn4.createNewFile();
				}
				Scanner rented =new Scanner(fIn4);
				while(rented.hasNext()) {
					String rentedWholeline =rented.nextLine();
					String [] rentedSplittedLine1 = rentedWholeline.split(",");
					String [] rentedSplittedLine2 = rentedSplittedLine1[1].split("\\[");
					String [] rentedSplittedLine3 = rentedSplittedLine2[1].split("\\]");
					mediaRentalSystem.processRequest(rentedSplittedLine1[0], rentedSplittedLine3[0]);
				}
				rented.close();
		//START WINDOW
		GridPane bp = new GridPane();
		ImageView imageView1 = new ImageView(
			      new Image("file:///C:/Users/Hp/Downloads/officeCustomer.png"));
		ImageView imageView2 = new ImageView(
			      new Image("file:///C:/Users/Hp/Downloads/moviesIcon.png"));
		ImageView imageView3 = new ImageView(
			      new Image("file:///C:/Users/Hp/Downloads/borrow-icon-png-3%20(33).png"));

		Button bc = new Button("CUSTOMER",imageView1);
		bc.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		Button bm = new Button("  MEDIA      ", imageView2);
		bm.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		Button br = new Button("    RENT       ", imageView3);
		br.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		VBox vb = new VBox(100);
		vb.getChildren().addAll(bc, bm, br);
		bp.add(vb, 0, 0);
		vb.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 20;");
		bp.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 20;");
		ImageView imageViewStart = new ImageView(
			      new Image("file:///C:/Users/Hp/Pictures/StartPicJavaProj.jpg"));		
		
		Label lStart = new Label("                   RENTAL MEDIA SYSTEM                   ");
		lStart.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		lStart.setStyle("-fx-background-color: lightgrey;");
		lStart.setTextAlignment(TextAlignment.CENTER);
		VBox vb2 = new VBox(100);
		vb2.getChildren().addAll(imageViewStart,lStart);
		bp.add(vb2, 1, 0);
        bp.setHgap(300);
        Scene scene = new Scene(bp, 1530, 800);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("THE FINAL PROJECT");
		stage.show();
        // CUSTOMER'S START WINDOW
		VBox vbCW = new VBox(30); 
		RadioButton bCW1 = new RadioButton("ADD CUSTOMER");bCW1.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		RadioButton bCW2 = new RadioButton("DELETE CUSTOMER");bCW2.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		RadioButton bCW3 = new RadioButton("UPDATE INFO ABOUT A CERTAIN CUSTOMER");bCW3.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		RadioButton bCW4 = new RadioButton("SEARCH FOR A CUSTOMER BY ID ");bCW4.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		RadioButton bCW5 = new RadioButton("RETURN TO MAIN PAGE");bCW5.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 30));
		ToggleGroup tg = new ToggleGroup();
		bCW1.setToggleGroup(tg);bCW2.setToggleGroup(tg);bCW3.setToggleGroup(tg);bCW4.setToggleGroup(tg);bCW5.setToggleGroup(tg);
		vbCW.getChildren().addAll(bCW1,bCW2,bCW3,bCW4,bCW5);
		vbCW.setAlignment(Pos.CENTER);
		vbCW.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene scene2 = new Scene(vbCW, 1600, 800);
		//ADDING CUSTOMERS
		GridPane gpCA = new GridPane();
		Label lCA1 = new Label("CUSTOMER ID:");
		Label lCA2 = new Label("CUSTOMER NAME:");
		Label lCA3 = new Label("CUSTOMER ADDRESS:");
		Label lCA4 = new Label("CUSTOMER MOBILE:");
		Label lCA5 = new Label("CUSTOMER PLAN:");
		String[] plansAvailable = {"LIMITED","UNLIMITED"};
		ComboBox<String> cbCAplansAvailable = new ComboBox<>(FXCollections.observableArrayList(plansAvailable));
		cbCAplansAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
		lCA1.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCA2.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCA3.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCA4.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCA5.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		TextField tfCA1 = new TextField();
		TextField tfCA2 = new TextField();
		TextField tfCA3 = new TextField();
		TextField tfCA4 = new TextField();
		gpCA.add(lCA1, 0, 0);
		gpCA.add(lCA2, 0, 1);
		gpCA.add(lCA3, 0, 2);
		gpCA.add(lCA4, 0, 3);
		gpCA.add(lCA5, 0, 4);
		gpCA.add(tfCA1, 1, 0);
		gpCA.add(tfCA2, 1, 1);
		gpCA.add(tfCA3, 1, 2);
		gpCA.add(tfCA4, 1, 3);
		gpCA.add(cbCAplansAvailable, 1, 4);
		gpCA.setHgap(50);gpCA.setVgap(50);
		gpCA.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		
		HBox hbCA = new HBox(200);
		ImageView ivCAadd  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addingIconF.png"));
		ImageView ivCback  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
		Button bCAadd = new Button("ADD",ivCAadd);Button bCAback = new Button("BACK",ivCback);
		hbCA.setAlignment(Pos.BOTTOM_CENTER);
		hbCA.getChildren().addAll(bCAadd,bCAback);
		
		BorderPane bpCA = new BorderPane();
		ImageView ivCAstartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addingCustf.png"));
		bpCA.setLeft(gpCA);bpCA.setBottom(hbCA);bpCA.setCenter(ivCAstartPic);
		bpCA.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene scene3 = new Scene(bpCA, 1530, 800);
		
		// DELETING CUSTOMER
		GridPane gpCD = new GridPane();
		Label lCD1 = new Label("CUSTOMER ID:");
		Label lCD2 = new Label("CUSTOMER NAME:");
		Label lCD3 = new Label("CUSTOMER ADDRESS:");
		Label lCD4 = new Label("CUSTOMER MOBILE:");
		Label lCD5 = new Label("CUSTOMER PLAN:");
		ComboBox<String> cbCDplansAvailable = new ComboBox<>(FXCollections.observableArrayList(plansAvailable));
		cbCDplansAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
		lCD1.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCD2.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCD3.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCD4.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		lCD5.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		TextField tfCD1 = new TextField();
		TextField tfCD2 = new TextField();
		TextField tfCD3 = new TextField();
		TextField tfCD4 = new TextField();
		gpCD.add(lCD1, 0, 0);
		gpCD.add(lCD2, 0, 1);
		gpCD.add(lCD3, 0, 2);
		gpCD.add(lCD4, 0, 3);
		gpCD.add(lCD5, 0, 4);
		gpCD.add(tfCD1, 1, 0);
		gpCD.add(tfCD2, 1, 1);
		gpCD.add(tfCD3, 1, 2);
		gpCD.add(tfCD4, 1, 3);
		gpCD.add(cbCDplansAvailable, 1, 4);
		gpCD.setHgap(50);gpCD.setVgap(50);
		gpCD.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		
		HBox hbCD = new HBox(50);
		ImageView ivCDfindButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
		ImageView ivCDdeleteButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/garbageIconF.png"));
		ImageView ivCDbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
		Button bCDfind = new Button("FIND",ivCDfindButton);Button bCDdelete = new Button("DELETE",ivCDdeleteButton);Button bCDback = new Button("BACK",ivCDbackButton);
		hbCD.setAlignment(Pos.BOTTOM_CENTER);
		hbCD.getChildren().addAll(bCDfind,bCDdelete,bCDback);
		
		BorderPane bpCD = new BorderPane();
		ImageView ivCDstartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/deleteCff.png"));
		bpCD.setLeft(gpCD);bpCD.setBottom(hbCD);bpCD.setCenter(ivCDstartPic);
		bpCD.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene sceneDeleteCustomer = new Scene(bpCD, 1600, 800);
		//UPDATING A CUSTOMER
		GridPane gpCU = new GridPane();
		Label lCU1 = new Label("CUSTOMER ID:");
		Label lCU2 = new Label("CUSTOMER NAME:");
		Label lCU3 = new Label("CUSTOMER ADDRESS:");
		Label lCU4 = new Label("CUSTOMER MOBILE:");
		Label lCU5 = new Label("CUSTOMER PLAN:");
		ComboBox<String> cbCUplansAvailable = new ComboBox<>(FXCollections.observableArrayList(plansAvailable));
		ComboBox<String> cbCUplansAvailable2 = new ComboBox<>(FXCollections.observableArrayList(plansAvailable));
		cbCUplansAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
		cbCUplansAvailable2.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
		Label lCUmessage = new Label("~ CUSTOMER FOUND !! Choose what u wanna Update ~");
		lCUmessage.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 20));
		CheckBox cbCUaddr = new CheckBox("UPDATING CUSTOMER ADDR.");
		CheckBox cbCUmob = new CheckBox("UPDATING CUSTOMER MOBILR");
		CheckBox cbCUplan = new CheckBox("UPDATING CUSTOMER PLAN");
		cbCUaddr.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 14));
		cbCUmob.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 14));
		cbCUplan.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 14));
		lCU1.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		lCU2.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		lCU3.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		lCU4.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		lCU5.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		TextField tfCU1 = new TextField();
		TextField tfCU2 = new TextField();
		TextField tfCU3 = new TextField();
		TextField tfCU4 = new TextField();
		TextField tfCU5 = new TextField();
		TextField tfCU6 = new TextField();
		TextField tfCU7 = new TextField();
		gpCU.add(lCU1, 0, 0);
		gpCU.add(lCU2, 0, 1);
		gpCU.add(lCU3, 0, 2);
		gpCU.add(lCU4, 0, 3);
		gpCU.add(lCU5, 0, 4);
		gpCU.add(tfCU1, 1, 0);
		gpCU.add(tfCU2, 1, 1);
		gpCU.add(tfCU3, 1, 2);
		gpCU.add(tfCU4, 1, 3);
		gpCU.add(cbCUplansAvailable, 1, 4);
		gpCU.setHgap(30);gpCU.setVgap(30);
		gpCU.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		
		HBox hbCU = new HBox(50);
		ImageView ivCUfindButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
		ImageView ivCUupdateButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/updateIconF.png"));
		ImageView ivCUbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
		Button bCUfind = new Button("FIND",ivCUfindButton);Button bCUupdate = new Button("UPDATE",ivCUupdateButton);Button bCUback = new Button("BACK",ivCUbackButton);
		hbCU.setAlignment(Pos.BOTTOM_CENTER);
		hbCU.getChildren().addAll(bCUfind,bCUupdate,bCUback);
		
		BorderPane bpCU = new BorderPane();
		ImageView ivCUstartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/updateCustomerff.png"));
		bpCU.setLeft(gpCU);bpCU.setBottom(hbCU);bpCU.setCenter(ivCUstartPic);
		bpCU.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene sceneUpdateCustomer = new Scene(bpCU, 1600, 800);
		
		//SEARCHING A CUSTOMER
		GridPane gpCS = new GridPane();
		Label lCS = new Label("CUSTOMER ID:");
		lCS.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		Label lCSmessage = new Label("!! CUSTOMER FOUND !!");
		lCSmessage.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 17));
		TextField tfCS = new TextField();
		TextArea taCS = new TextArea();
		taCS.setFont(Font.font("Times New Roman", 
			      FontWeight.NORMAL, FontPosture.REGULAR, 17));
		gpCS.add(lCS, 0, 1);
		gpCS.add(tfCS, 0, 2);
		gpCS.add(taCS, 0, 4);
		gpCS.setHgap(10);gpCS.setVgap(50);
		gpCS.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		
		HBox hbCS = new HBox(50);
		ImageView ivCSsearchButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
		ImageView ivCSbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
		Button bCSsearch = new Button("SEARCH",ivCSsearchButton);Button bCSback = new Button("BACK",ivCSbackButton);
		hbCS.setAlignment(Pos.BOTTOM_CENTER);
		hbCS.getChildren().addAll(bCSsearch,bCSback);
		
		BorderPane bpCS = new BorderPane();
		ImageView ivCSstartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchCustf.png"));
		bpCS.setLeft(gpCS);bpCS.setBottom(hbCS);bpCS.setCenter(ivCSstartPic);
		bpCS.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene sceneSearchCustomer = new Scene(bpCS, 1600, 800);
        
		// CUSTOMER OPTIONS AND FILLING INFO
		bc.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
		      public void handle(ActionEvent e) {
    		stage.setScene(scene2);
    		
    		//ADDING A CUSTOMER WINDOW
    	bCW1.setOnAction(new EventHandler<ActionEvent>() {
      @Override 
      public void handle(ActionEvent e) {
    	  
    	  stage.setScene(scene3);
    	  stage.show();

    	  bCAadd.setOnAction(new EventHandler<ActionEvent>() {
    	      @Override 
    	      public void handle(ActionEvent e) {
    	    	  int selectedIndex = cbCAplansAvailable.getSelectionModel().getSelectedIndex();
      		    Object selectedItem = cbCAplansAvailable.getSelectionModel().getSelectedItem();
      		    if(selectedItem.equals("LIMITED")) {
      		    	try {
    					mediaRentalSystem.addCustomer(tfCA2.getText(), tfCA1.getText() , tfCA3.getText(), tfCA4.getText(),"LIMITED");
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
      		    }
      		    if(selectedItem.equals("UNLIMITED")){
      		    	try {
    					mediaRentalSystem.addCustomer(tfCA2.getText(), tfCA1.getText() , tfCA3.getText(), tfCA4.getText(),"UNLIMITED");
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
      		    }
    	      }
    	     
    	    });
    	  bCAback.setOnAction(new EventHandler<ActionEvent>() {
    	      @Override 
    	      public void handle(ActionEvent e) {
    	    	  stage.setScene(scene2);
    	    	  stage.show();
    	    	  
    	      }
    	    });
    	  
      
      
      }
    });
    	//FOR DELETING A CUSTOMER WINDOW
    	bCW2.setOnAction(new EventHandler<ActionEvent>() {
    		int haha;  
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  
    	    	  stage.setScene(sceneDeleteCustomer);
    	    	  stage.show();
    	    	   
    	    	  bCDfind.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  int selectedIndex = cbCDplansAvailable.getSelectionModel().getSelectedIndex();
    	        		    Object selectedItem = cbCDplansAvailable.getSelectionModel().getSelectedItem();
    	        		    if(selectedItem.equals("LIMITED")) {
    	        		    	Customer  customerSearch = new Customer(tfCD2.getText(), tfCD1.getText() , tfCD3.getText(), tfCD4.getText(),"LIMITED");
    	        		    	for(int i=0;i<mediaRentalSystem.customers.size();i++) {
    	    	    	    		  if(mediaRentalSystem.customers.get(i).toString().equals(customerSearch.toString())) {
    	    	    	    			  haha=i;
    	    	    	    		  }
    	    	    	    	  }
    	        		    }
    	        		    if(selectedItem.equals("UNLIMITED")) {
    	        		    	Customer  customerSearch = new Customer(tfCD2.getText(), tfCD1.getText() , tfCD3.getText(), tfCD4.getText(),"UNLIMITED");
    	        		    	for(int i=0;i<mediaRentalSystem.customers.size();i++) {
    	    	    	    		  if(mediaRentalSystem.customers.get(i).toString().equals(customerSearch.toString())) {
    	    	    	    			  haha=i;
    	    	    	    		  }
    	    	    	    	  }
    	        		    }
    	    	      }
    	    	    });
    	    	  bCDdelete.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  mediaRentalSystem.customers.remove(haha);
    	    	    	  try {
							mediaRentalSystem.getAllCustomerInfo();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    	    	  
    	    	      }
    	    	    });
    	    	  bCDback.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  stage.setScene(scene2);
    	    	    	  stage.show();
    	    	    	  
    	    	      }
    	    	    });
    	      
    	      
    	      
    	      }
    	    });
		//FOR UPDATING INFO ABOUT A CUSTOMER
    	bCW3.setOnAction(new EventHandler<ActionEvent>() {
    		int haha2;  
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  
    	    	  stage.setScene(sceneUpdateCustomer);
    	    	  stage.show();
    	    	 
    	    	  bCUfind.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	
    	    	    	int selectedIndex = cbCUplansAvailable.getSelectionModel().getSelectedIndex();
	        		    Object selectedItemUpdating = cbCUplansAvailable.getSelectionModel().getSelectedItem();
	        		    
	        		    if(selectedItemUpdating.equals("LIMITED")) {
	        		    	Customer  customerSearch2 = new Customer(tfCU2.getText(), tfCU1.getText() , tfCU3.getText(), tfCU4.getText(),"LIMITED");
	        		    	for(int i=0;i<mediaRentalSystem.customers.size();i++) {
	    	    	    		  if(mediaRentalSystem.customers.get(i).toString().equals(customerSearch2.toString())) {
	    	    	    			  haha2=i;
	    	    	    			  gpCU.add(lCUmessage, 0, 5);
	    	    	    			  gpCU.add(cbCUaddr, 0, 6);
	    	    	    			  gpCU.add(cbCUmob, 0, 7);
	    	    	    			  gpCU.add(cbCUplan, 0, 8);
	    	    	    			  cbCUaddr.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(tfCU6, 1, 6);
	    	    	    				  }
	    	    	    			  });
	    	    	    			  cbCUmob.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(tfCU7, 1, 7);
	    	    	    				  }
	    	    	    			  });
	    	    	    			  cbCUplan.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(cbCUplansAvailable2, 1, 8);
	    	    	    				  }
	    	    	    			  });
	    	    	    		  }
	    	    	    	  }
	        		    }
	        		    if(selectedItemUpdating.equals("UNLIMITED")) {
	        		    	Customer  customerSearch2 = new Customer(tfCU2.getText(), tfCU1.getText() , tfCU3.getText(), tfCU4.getText(),"UNLIMITED");
	        		    	for(int i=0;i<mediaRentalSystem.customers.size();i++) {
	    	    	    		  if(mediaRentalSystem.customers.get(i).toString().equals(customerSearch2.toString())) {
	    	    	    			  haha2=i;
	    	    	    			  gpCU.add(lCUmessage, 0, 5);
	    	    	    			  gpCU.add(cbCUaddr, 0, 6);
	    	    	    			  gpCU.add(cbCUmob, 0, 7);
	    	    	    			  gpCU.add(cbCUplan, 0, 8);
	    	    	    			  cbCUaddr.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(tfCU6, 1, 6);
	    	    	    				  }
	    	    	    			  });
	    	    	    			  cbCUmob.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(tfCU7, 1, 7);
	    	    	    				  }
	    	    	    			  });
	    	    	    			  cbCUplan.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    				  @Override 
	    	    	    	    	      public void handle(ActionEvent e) {
	    	    	    					  gpCU.add(cbCUplansAvailable2, 1, 8);
	    	    	    				  }
	    	    	    			  });
	    	    	    		  }
	    	    	    	  }
	        		    } 
    	    	    	  
    	    	      }
    	    	    });
    	    	  bCUupdate.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  if(tfCU6.getText()==null || tfCU6.getText()=="") {
    	    	    		  mediaRentalSystem.customers.get(haha2).setAddress(tfCU3.getText());
    	    	    	  }
    	    	    	  else {
    	    	    		  mediaRentalSystem.customers.get(haha2).setAddress(tfCU6.getText());
    	    	    	  }
    	    	    	  if(tfCU7.getText()==null || tfCU7.getText()=="") {
    	    	    		  mediaRentalSystem.customers.get(haha2).setMobileNumber(tfCU4.getText());
    	    	    	  }
    	    	    	  else {
    	    	    		  mediaRentalSystem.customers.get(haha2).setMobileNumber(tfCU7.getText()); 
    	    	    	  }
    	    	    	  
    	    	    	  int selectedIndex = cbCUplansAvailable2.getSelectionModel().getSelectedIndex();
  	        		    Object selectedItemUpdating2 = cbCUplansAvailable2.getSelectionModel().getSelectedItem();
  	        		    if(!cbCUplansAvailable2.getSelectionModel().isEmpty() && selectedItemUpdating2.equals("LIMITED")) {
  	        		    	mediaRentalSystem.customers.get(haha2).setPlan("LIMITED");
  	        		    }
  	        		    if(!cbCUplansAvailable2.getSelectionModel().isEmpty() && selectedItemUpdating2.equals("UNLIMITED")) {
  	        		    	mediaRentalSystem.customers.get(haha2).setPlan("UNLIMITED");
  	        		    }
    	    	    	  try {
							mediaRentalSystem.getAllCustomerInfo();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    	    	    	  //System.out.println(mediaRentalSystem.customers.get(haha2).toString());
    	    	      }
    	    	    });
    	    	  bCUback.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  stage.setScene(scene2);
    	    	    	  stage.show();
    	    	    	  
    	    	      }
    	    	    });
    	      }
    	    });
        //FOR SEARCHING A CUSTOMER WINDOW
    	bCW4.setOnAction(new EventHandler<ActionEvent>() {
    		int haha3;  
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  
    	    	  stage.setScene(sceneSearchCustomer);
    	    	  stage.show();
    	    	   
    	    	  bCSsearch.setOnAction(new EventHandler<ActionEvent>() {
    	    		  
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	//Customer  customerSearch2 = new Customer(tfCU2.getText(), tfCU1.getText() , tfCU3.getText(), tfCU4.getText(),tfCU5.getText());
    	    	    	int tempCounter=0;
    	    	    	for(int i=0;i<mediaRentalSystem.customers.size();i++) {
    	    	    		  if(tfCS.getText().equals(mediaRentalSystem.customers.get(i).getId())) {
    	    	    			  tempCounter++;
    	    	    			  haha3=i;
    	    	    			  break;
    	    	    		  }
    	    	    	  }
    	    	    	if(tempCounter!=0) {
    	    	    		String m = "\n~ CUSTOMER FOUND ~\n"+mediaRentalSystem.customers.get(haha3).toString()+"\n";
    	    	    		taCS.appendText(m);
    	    	    	}
    	    	    	else {
    	    	    	    String n = "\n~ CUSTOMER NOT FOUND ~\n";
    	    	    	    taCS.appendText(n);
    	    	    	}

    	    	    	
    	    	      }
    	    	    });
    	    	  bCSback.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  stage.setScene(scene2);
    	    	    	  stage.show();
    	    	    	  
    	    	      }
    	    	    });
    	      
    	      
    	      
    	      }
    	    });
		//FOR RETURNING TO MAIN PAGE
    	bCW5.setOnAction(new EventHandler<ActionEvent>() {
    		 
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  stage.setScene(scene);
    	    	  stage.setMaximized(true);
    	    	  stage.show();
    	      }
    	    });
    	
			}
		});
		
		// MEDIA START WINDOW
		VBox vbMW = new VBox(30);
		RadioButton bMW1 = new RadioButton("ADD MEDIA");
		bMW1.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		RadioButton bMW2 = new RadioButton("DELETE MEDIA");
		bMW2.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		RadioButton bMW3 = new RadioButton("UPDATE INFO ABOUT A CERTAIN MEDIA");
		bMW3.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		RadioButton bMW4 = new RadioButton("SEARCH FOR A MEDIA BY CODE ");
		bMW4.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		RadioButton bMW5 = new RadioButton("PRINT ALL MEDIA INFORMATION ");
		bMW5.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		RadioButton bMW6 = new RadioButton("RETURN TO MAIN PAGE");
		bMW6.setFont(Font.font("Times New Roman", 
			      FontWeight.BOLD, FontPosture.ITALIC, 28));
		ToggleGroup tg2 = new ToggleGroup();
		bMW1.setToggleGroup(tg2);bMW2.setToggleGroup(tg2);bMW3.setToggleGroup(tg2);bMW4.setToggleGroup(tg2);bMW5.setToggleGroup(tg2);bMW6.setToggleGroup(tg2);
		vbMW.setAlignment(Pos.CENTER);
		vbMW.getChildren().addAll(bMW1,bMW2,bMW3,bMW4,bMW5,bMW6);
		vbMW.setStyle("-fx-background-color: azure; -fx-padding: 10; -fx-font-size: 20;");
		Scene mediaStartScene = new Scene(vbMW, 1600, 800);
		
		//ADD MEDIA
		//**TYPE OF MEDIA**
		String[] typesOfMedia = {"MOVIE","ALBUM","GAME"};
		ComboBox<String> cbMtype = new ComboBox<>(FXCollections.observableArrayList(typesOfMedia));
		cbMtype.setStyle("-fx-background-color: lavender; -fx-padding: 15; -fx-spacing: 10;");
        Label lMtype = new Label("~ CHOOSE THE TYPE OF THE MEDIA U WANNA ADD ~");
        lMtype.setFont(Font.font("Cambria", 32));
        lMtype.setStyle("-fx-background-color: lavender;-fx-padding: 9;-fx-spacing: 10;");
        Button readTheType = new Button("NEXT");readTheType.setFont(Font.font("Cambria", 20));
        VBox vbMtype = new VBox(50);
        vbMtype.getChildren().addAll(lMtype,cbMtype,readTheType);
        vbMtype.setAlignment(Pos.CENTER);
        vbMtype.setStyle("-fx-background-color: azure;-fx-padding: 15;-fx-spacing: 10;");
        Scene theTypeOfMediaScene = new Scene(vbMtype,1600,800);
        
        //**ADDING THE MOVIE**
				GridPane gpMAmovie = new GridPane();
				Label lMA1movie = new Label("MOVIE TITLE:");
				lMA1movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA2movie = new Label("MOVIE CODE:");
				lMA2movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA3movie = new Label("MOVIE COPIES:");
				lMA3movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA4movie = new Label("MOVIE RATING:");
				lMA4movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMA1movie = new TextField();
				TextField tfMA2movie = new TextField();
				TextField tfMA3movie = new TextField();
				String[] ratingsAvailable = {"DR","HR","AC"};
				ComboBox<String> cbMAratingsAvailable = new ComboBox<>(FXCollections.observableArrayList(ratingsAvailable));
				cbMAratingsAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
				gpMAmovie.add(lMA1movie, 0, 0);
				gpMAmovie.add(lMA2movie, 0, 1);
				gpMAmovie.add(lMA3movie, 0, 2);
				gpMAmovie.add(lMA4movie, 0, 3);
				gpMAmovie.add(tfMA1movie, 1, 0);
				gpMAmovie.add(tfMA2movie, 1, 1);
				gpMAmovie.add(tfMA3movie, 1, 2);
				gpMAmovie.add(cbMAratingsAvailable, 1, 3);
				gpMAmovie.setHgap(50);gpMAmovie.setVgap(50);
				gpMAmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMAmovie = new HBox(50);
				hbMAmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				ImageView ivMAmovieAddButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addingMediaIcon.png"));
				ImageView ivMAmovieBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMAaddmovie = new Button("ADD",ivMAmovieAddButton);Button bMAbackmovie = new Button("BACK",ivMAmovieBackButton);
				hbMAmovie.setAlignment(Pos.BOTTOM_CENTER);
				hbMAmovie.getChildren().addAll(bMAaddmovie,bMAbackmovie);
				
				BorderPane bpMAmovie = new BorderPane();
				VBox vbForImages = new VBox();
				ImageView ivMAmovieStartPic1  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addMovief.png"));
				ImageView ivMAmovieStartPic2  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addMovie2f.png"));
				vbForImages.getChildren().addAll(ivMAmovieStartPic1,ivMAmovieStartPic2);
				bpMAmovie.setLeft(gpMAmovie);bpMAmovie.setBottom(hbMAmovie);bpMAmovie.setRight(vbForImages);
				bpMAmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene movieAddScene = new Scene(bpMAmovie, 1530, 800);
				
				//**ADDING THE ALBUM**
				GridPane gpMAalbum = new GridPane();
				Label lMA1album = new Label("ALBUM TITLE:");
				lMA1album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA2album = new Label("ALBUM CODE:");
				lMA2album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA3album = new Label("ALBUM COPIES:");
				lMA3album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA4album = new Label("ALBUM ARTIST:");
				lMA4album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA5album = new Label("ALBUM SONGS:");
				lMA5album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMA1album = new TextField();
				TextField tfMA2album = new TextField();
				TextField tfMA3album = new TextField();
				TextField tfMA4album = new TextField();
				TextField tfMA5album = new TextField();
				gpMAalbum.add(lMA1album, 0, 0);
				gpMAalbum.add(lMA2album, 0, 1);
				gpMAalbum.add(lMA3album, 0, 2);
				gpMAalbum.add(lMA4album, 0, 3);
				gpMAalbum.add(lMA5album, 0, 4);
				gpMAalbum.add(tfMA1album, 1, 0);
				gpMAalbum.add(tfMA2album, 1, 1);
				gpMAalbum.add(tfMA3album, 1, 2);
				gpMAalbum.add(tfMA4album, 1, 3);
				gpMAalbum.add(tfMA5album, 1, 4);
				gpMAalbum.setHgap(50);gpMAalbum.setVgap(50);
				gpMAalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMAalbum = new HBox(50);
				ImageView ivMAalbumAddButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addingMediaIcon.png"));
				ImageView ivMAalbumBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMAaddalbum = new Button("ADD",ivMAalbumAddButton);Button bMAbackalbum = new Button("BACK",ivMAalbumBackButton);
				hbMAalbum.setAlignment(Pos.BOTTOM_CENTER);
				hbMAalbum.getChildren().addAll(bMAaddalbum,bMAbackalbum);
				
				BorderPane bpMAalbum = new BorderPane();
				ImageView ivMAalbumStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/albumsT.jpg"));
				bpMAalbum.setLeft(gpMAalbum);bpMAalbum.setBottom(hbMAalbum);bpMAalbum.setCenter(ivMAalbumStartPic);
				bpMAalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene albumAddScene = new Scene(bpMAalbum, 1600, 800);
				
				//**ADDING THE GAME**
				GridPane gpMAgame = new GridPane();
				Label lMA1game = new Label("GAME TITLE:");lMA1game.setStyle("-fx-background-color: azure");
				lMA1game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA2game = new Label("GAME CODE:");
				lMA2game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA3game = new Label("GAME COPIES:");
				lMA3game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMA4game = new Label("GAME WEIGHT:");
				lMA4game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMA1game = new TextField();
				TextField tfMA2game = new TextField();
				TextField tfMA3game = new TextField();
				TextField tfMA4game = new TextField();
				gpMAgame.add(lMA1game, 0, 0);
				gpMAgame.add(lMA2game, 0, 1);
				gpMAgame.add(lMA3game, 0, 2);
				gpMAgame.add(lMA4game, 0, 3);
				gpMAgame.add(tfMA1game, 1, 0);
				gpMAgame.add(tfMA2game, 1, 1);
				gpMAgame.add(tfMA3game, 1, 2);
				gpMAgame.add(tfMA4game, 1, 3);
				gpMAgame.setHgap(50);gpMAgame.setVgap(50);
				gpMAgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMAgame = new HBox(50);
				ImageView ivMAgameAddButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addingMediaIcon.png"));
				ImageView ivMAgameBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMAaddgame = new Button("ADD",ivMAgameAddButton);Button bMAbackgame = new Button("BACK",ivMAgameBackButton);
				hbMAgame.setAlignment(Pos.BOTTOM_CENTER);
				hbMAgame.getChildren().addAll(bMAaddgame,bMAbackgame);
				
				BorderPane bpMAgame = new BorderPane();
				ImageView ivMGameStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/gamingAddd.png"));
				bpMAgame.setLeft(gpMAgame);bpMAgame.setBottom(hbMAgame);bpMAgame.setCenter(ivMGameStartPic);
				bpMAgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene gameAddScene = new Scene(bpMAgame, 1600, 800);
				
				//DELETE MEDIA 
				//**DELETING THE MOVIE**
				GridPane gpMDmovie = new GridPane();
				Label lMD1movie = new Label("MOVIE TITLE:");
				lMD1movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD2movie = new Label("MOVIE CODE:");
				lMD2movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD3movie = new Label("MOVIE COPIES:");
				lMD3movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD4movie = new Label("MOVIE RATING:");
				lMD4movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMD1movie = new TextField();
				TextField tfMD2movie = new TextField();
				TextField tfMD3movie = new TextField();
				ComboBox<String> cbMDratingsAvailable = new ComboBox<>(FXCollections.observableArrayList(ratingsAvailable));
				cbMDratingsAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
				gpMDmovie.add(lMD1movie, 0, 0);
				gpMDmovie.add(lMD2movie, 0, 1);
				gpMDmovie.add(lMD3movie, 0, 2);
				gpMDmovie.add(lMD4movie, 0, 3);
				gpMDmovie.add(tfMD1movie, 1, 0);
				gpMDmovie.add(tfMD2movie, 1, 1);
				gpMDmovie.add(tfMD3movie, 1, 2);
				gpMDmovie.add(cbMDratingsAvailable, 1, 3);
				gpMDmovie.setHgap(50);gpMDmovie.setVgap(50);
				gpMDmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMDmovie = new HBox(50);
				ImageView ivMDmovieDeleteButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/garbageIconF.png"));
				ImageView ivMDmovieBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMDdeletemovie = new Button("DELETE",ivMDmovieDeleteButton);Button bMDbackmovie = new Button("BACK",ivMDmovieBackButton);
				hbMDmovie.setAlignment(Pos.BOTTOM_CENTER);
				hbMDmovie.getChildren().addAll(bMDdeletemovie,bMDbackmovie);
				
				BorderPane bpMDmovie = new BorderPane();
				ImageView ivMDmovieStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/dMf.jpg"));
				bpMDmovie.setLeft(gpMDmovie);bpMDmovie.setBottom(hbMDmovie);bpMDmovie.setCenter(ivMDmovieStartPic);
				bpMDmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene movieDeleteScene = new Scene(bpMDmovie, 1600, 800);
				
				//**DELETING THE ALBUM**
				GridPane gpMDalbum = new GridPane();
				Label lMD1album = new Label("ALBUM TITLE:");
				lMD1album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD2album = new Label("ALBUM CODE:");
				lMD2album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD3album = new Label("ALBUM COPIES:");
				lMD3album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD4album = new Label("ALBUM ARTIST:");
				lMD4album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD5album = new Label("ALBUM SONGS:");
				lMD5album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMD1album = new TextField();
				TextField tfMD2album = new TextField();
				TextField tfMD3album = new TextField();
				TextField tfMD4album = new TextField();
				TextField tfMD5album = new TextField();
				gpMDalbum.add(lMD1album, 0, 0);
				gpMDalbum.add(lMD2album, 0, 1);
				gpMDalbum.add(lMD3album, 0, 2);
				gpMDalbum.add(lMD4album, 0, 3);
				gpMDalbum.add(lMD5album, 0, 4);
				gpMDalbum.add(tfMD1album, 1, 0);
				gpMDalbum.add(tfMD2album, 1, 1);
				gpMDalbum.add(tfMD3album, 1, 2);
				gpMDalbum.add(tfMD4album, 1, 3);
				gpMDalbum.add(tfMD5album, 1, 4);
				gpMDalbum.setHgap(50);gpMDalbum.setVgap(50);
				gpMDalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMDalbum = new HBox(50);
				ImageView ivMDalbumDeleteButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/garbageIconF.png"));
				ImageView ivMDalbumBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMDdeleteAlbum = new Button("DELETE",ivMDalbumDeleteButton);Button bMDbackAlbum = new Button("BACK",ivMDalbumBackButton);
				hbMDalbum.setAlignment(Pos.BOTTOM_CENTER);
				hbMDalbum.getChildren().addAll(bMDdeleteAlbum,bMDbackAlbum);
				
				BorderPane bpMDalbum = new BorderPane();
				ImageView ivMDalbumStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/EminemAlbums.jpg"));
				bpMDalbum.setLeft(gpMDalbum);bpMDalbum.setBottom(hbMDalbum);bpMDalbum.setCenter(ivMDalbumStartPic);
				bpMDalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene albumDeleteScene = new Scene(bpMDalbum, 1600, 800);
				
				//**DELETING THE GAME**
				GridPane gpMDgame = new GridPane();
				Label lMD1game = new Label("GAME TITLE:");
				lMD1game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD2game = new Label("GAME CODE:");
				lMD2game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD3game = new Label("GAME COPIES:");
				lMD3game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMD4game = new Label("GAME WEIGHT:");
				lMD4game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				TextField tfMD1game = new TextField();
				TextField tfMD2game = new TextField();
				TextField tfMD3game = new TextField();
				TextField tfMD4game = new TextField();
				gpMDgame.add(lMD1game, 0, 0);
				gpMDgame.add(lMD2game, 0, 1);
				gpMDgame.add(lMD3game, 0, 2);
				gpMDgame.add(lMD4game, 0, 3);
				gpMDgame.add(tfMD1game, 1, 0);
				gpMDgame.add(tfMD2game, 1, 1);
				gpMDgame.add(tfMD3game, 1, 2);
				gpMDgame.add(tfMD4game, 1, 3);
				gpMDgame.setHgap(50);gpMDgame.setVgap(50);
				gpMDgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMDgame = new HBox(50);
				ImageView ivMDgameDeleteButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/garbageIconF.png"));
				ImageView ivMDgameBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMDdeleteGame = new Button("DELETE",ivMDgameDeleteButton);Button bMDbackGame = new Button("BACK",ivMDgameBackButton);
				hbMDgame.setAlignment(Pos.BOTTOM_CENTER);
				hbMDgame.getChildren().addAll(bMDdeleteGame,bMDbackGame);
				
				BorderPane bpMDgame = new BorderPane();
				ImageView ivMDgameStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/gta.jpg"));
				bpMDgame.setLeft(gpMDgame);bpMDgame.setBottom(hbMDgame);bpMDgame.setCenter(ivMDgameStartPic);
				bpMDgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene gameDeleteScene = new Scene(bpMDgame, 1600, 800);
				
				//UPDATING MEDIA
				//**UPDATE MOVIE**
				GridPane gpMUmovie = new GridPane();
				Label lMU1movie = new Label("MOVIE TITLE:");
				lMU1movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMU2movie = new Label("MOVIE CODE:");
				lMU2movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMU3movie = new Label("MOVIE COPIES:");
				lMU3movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMU4movie = new Label("MOVIE RATING:");
				lMU4movie.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				Label lMUmessage = new Label("~ MOVIE FOUND !! Choose what u wanna Update ~");
				lMUmessage.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				CheckBox cbMUtit = new CheckBox("UPDATING MEDIA TITLE");
				cbMUtit.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUcode = new CheckBox("UPDATING MEDIA CODE");
				cbMUcode.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUcop = new CheckBox("UPDATING MEDIA COPIES");
				cbMUcop.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				TextField tfMU1movie = new TextField();
				TextField tfMU2movie = new TextField();
				TextField tfMU3movie = new TextField();
				TextField tfMU4movie = new TextField();
				TextField tfMU5movie = new TextField();
				TextField tfMU6movie = new TextField();
				TextField tfMU7movie = new TextField();
				TextField tfMU8movie = new TextField();
				ComboBox<String> cbMUratingsAvailable = new ComboBox<>(FXCollections.observableArrayList(ratingsAvailable));
				cbMUratingsAvailable.setStyle("-fx-background-color: silver; -fx-font-size: 25;");
				gpMUmovie.add(lMU1movie, 0, 0);
				gpMUmovie.add(lMU2movie, 0, 1);
				gpMUmovie.add(lMU3movie, 0, 2);
				gpMUmovie.add(lMU4movie, 0, 3);
				gpMUmovie.add(tfMU1movie, 1, 0);
				gpMUmovie.add(tfMU2movie, 1, 1);
				gpMUmovie.add(tfMU3movie, 1, 2);
				gpMUmovie.add(cbMUratingsAvailable, 1, 3);
				gpMUmovie.setHgap(50);gpMUmovie.setVgap(50);
				gpMUmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMUmovie = new HBox(50);
				ImageView ivMUmovieFindButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
				ImageView ivMUmovieUpdateButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/updateIconF.png"));
				ImageView ivMUmovieBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMUfindmovie = new Button("FIND",ivMUmovieFindButton);Button bMUupdatemovie = new Button("UPDATE",ivMUmovieUpdateButton);Button bMUbackmovie = new Button("BACK",ivMUmovieBackButton);
				hbMUmovie.setAlignment(Pos.BOTTOM_CENTER);
				hbMUmovie.getChildren().addAll(bMUfindmovie,bMUupdatemovie,bMUbackmovie);
				
				BorderPane bpMUmovie = new BorderPane();
				ImageView ivMUmovieStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/familyMovies.png"));
				bpMUmovie.setLeft(gpMUmovie);bpMUmovie.setBottom(hbMUmovie);bpMUmovie.setCenter(ivMUmovieStartPic);
				bpMUmovie.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene sceneUpdateMovie = new Scene(bpMUmovie, 1600, 800);
				
				//**UPDATE Album**
				GridPane gpMUalbum = new GridPane();
				Label lMU1album = new Label("ALBUM TITLE:");
				lMU1album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU2album = new Label("ALBUM CODE:");
				lMU2album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU3album = new Label("ALBUM COPIES:");
				lMU3album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU4album = new Label("ALBUM ARTIST:");
				lMU4album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU5album = new Label("ALBUM SONGS:");
				lMU5album.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUalbumtit = new CheckBox("UPDATING MEDIA TITLE");
				cbMUalbumtit.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUalbumcode = new CheckBox("UPDATING MEDIA CODE");
				cbMUalbumcode.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUalbumcop = new CheckBox("UPDATING MEDIA COPIES");
				cbMUalbumcop.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label UpdatingAlbumMessage = new Label("~ MEDIA FOUND !! CHOOSE WHAT U WANNA UPDATE ~");
				UpdatingAlbumMessage.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 16));
				TextField tfMU1album = new TextField();
				TextField tfMU2album = new TextField();
				TextField tfMU3album = new TextField();
				TextField tfMU4album = new TextField();
				TextField tfMU5album = new TextField();
				TextField tfMU6album = new TextField();
				TextField tfMU7album = new TextField();
				TextField tfMU8album = new TextField();
				gpMUalbum.add(lMU1album, 0, 0);
				gpMUalbum.add(lMU2album, 0, 1);
				gpMUalbum.add(lMU3album, 0, 2);
				gpMUalbum.add(lMU4album, 0, 3);
				gpMUalbum.add(lMU5album, 0, 4);
				gpMUalbum.add(tfMU1album, 1, 0);
				gpMUalbum.add(tfMU2album, 1, 1);
				gpMUalbum.add(tfMU3album, 1, 2);
				gpMUalbum.add(tfMU4album, 1, 3);
				gpMUalbum.add(tfMU5album, 1, 4);
				gpMUalbum.setHgap(50);gpMUalbum.setVgap(30);
				gpMUalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				HBox hbMUalbum = new HBox(50);
				ImageView ivMUalbumFindButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
				ImageView ivMUalbumUpdateButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/updateIconF.png"));
				ImageView ivMUalbumBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMUfindalbum = new Button("FIND",ivMUalbumFindButton);Button bMUupdatealbum = new Button("UPDATE",ivMUalbumUpdateButton);Button bMUbackalbum = new Button("BACK",ivMUalbumBackButton);
				hbMUalbum.setAlignment(Pos.BOTTOM_CENTER);
				hbMUalbum.getChildren().addAll(bMUfindalbum,bMUupdatealbum,bMUbackalbum);
				
				BorderPane bpMUalbum = new BorderPane();
				ImageView ivMUalbumStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/synaptikAlbumf.jpg"));
				bpMUalbum.setLeft(gpMUalbum);bpMUalbum.setBottom(hbMUalbum);bpMUalbum.setCenter(ivMUalbumStartPic);
				bpMUalbum.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene sceneUpdateAlbum = new Scene(bpMUalbum, 1600, 800);
				
				//**UPDATE GAME**
				GridPane gpMUgame = new GridPane();
				Label lMU1game = new Label("GAME TITLE:");
				lMU1game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU2game = new Label("GAME CODE:");
				lMU2game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU3game = new Label("GAME COPIES:");
				lMU3game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label lMU4game = new Label("GAME WEIGHT:");
				lMU4game.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				
				CheckBox cbMUgametit = new CheckBox("UPDATING MEDIA TITLE");
				cbMUgametit.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUgamecode = new CheckBox("UPDATING MEDIA CODE");
				cbMUgamecode.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				CheckBox cbMUgamecop = new CheckBox("UPDATING MEDIA COPIES");
				cbMUgamecop.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 14));
				Label UpdatingGameMessage = new Label("~ MEDIA FOUND !! CHOOSE WHAT U WANNA UPDATE ~");
				UpdatingGameMessage.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 16));
				TextField tfMU1game = new TextField();
				TextField tfMU2game = new TextField();
				TextField tfMU3game = new TextField();
				TextField tfMU4game = new TextField();
				TextField tfMU5game = new TextField();
				TextField tfMU6game = new TextField();
				TextField tfMU7game = new TextField();
				gpMUgame.add(lMU1game, 0, 0);
				gpMUgame.add(lMU2game, 0, 1);
				gpMUgame.add(lMU3game, 0, 2);
				gpMUgame.add(lMU4game, 0, 3);
				gpMUgame.add(tfMU1game, 1, 0);
				gpMUgame.add(tfMU2game, 1, 1);
				gpMUgame.add(tfMU3game, 1, 2);
				gpMUgame.add(tfMU4game, 1, 3);
				gpMUgame.setHgap(50);gpMUgame.setVgap(50);
				gpMUgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbMUgame = new HBox(50);
				ImageView ivMUgameFindButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
				ImageView ivMUgameUpdateButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/updateIconF.png"));
				ImageView ivMUgameBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMUfindGame = new Button("FIND",ivMUgameFindButton);Button bMUupdateGame = new Button("UPDATE",ivMUgameUpdateButton);Button bMUbackGame = new Button("BACK",ivMUgameBackButton);
				hbMUgame.setAlignment(Pos.BOTTOM_CENTER);
				hbMUgame.getChildren().addAll(bMUfindGame,bMUupdateGame,bMUbackGame);
				
				BorderPane bpMUgame = new BorderPane();
				ImageView ivMUgameStartPic  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/FORTNITEPOSTER.jpg"));
				bpMUgame.setLeft(gpMUgame);bpMUgame.setBottom(hbMUgame);bpMUgame.setCenter(ivMUgameStartPic);
				bpMUgame.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene sceneUpdateGame = new Scene(bpMUgame, 1600, 800);
				
				//SEARCHING A MEDIA WINDOW
				GridPane gpMS = new GridPane();
				Label lMS = new Label("MEDIA CODE:");
				lMS.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 16));
				TextField tfMS = new TextField();
				TextArea taMS = new TextArea();
				taMS.setFont(Font.font("Times New Roman", 
					       FontPosture.ITALIC, 20));
				gpMS.add(lMS, 0, 0);
				gpMS.add(tfMS, 0, 1);
				gpMS.add(taMS, 0, 2);
				gpMS.setHgap(30);gpMS.setVgap(50);
				gpMS.setStyle("-fx-background-color: ghostwhite;-fx-padding: 15;-fx-spacing: 10;");
				HBox hbMS = new HBox(50);
				ImageView ivMSsearchButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/searchingIconBestOneF.png"));
				ImageView ivMSbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bMSsearch = new Button("SEARCH",ivMSsearchButton);Button bMSback = new Button("BACK",ivMSbackButton);
				hbMS.setAlignment(Pos.BOTTOM_CENTER);
				hbMS.getChildren().addAll(bMSsearch,bMSback);
				BorderPane bpMS = new BorderPane();
				ImageView ivMS  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/GOTPOSTER.jpg"));
				gpMS.add(ivMS, 2, 2);
				bpMS.setLeft(gpMS);bpMS.setBottom(hbMS);
				bpMS.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				Scene sceneSearchMedia = new Scene(bpMS, 1600, 800);
				
				//PRINTING INFO MEDIA WINDOW
				TextArea mediaInfo = new TextArea();
				mediaInfo.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-font-size: 20;");
				StackPane spPMI = new StackPane();
				spPMI.setStyle("-fx-background-color: azure;-fx-padding: 15;-fx-spacing: 10;");
				spPMI.getChildren().add(mediaInfo);
				BorderPane bpPMI = new BorderPane();
				Button bPMIback = new Button("BACK");
				bPMIback.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 24));
				bpPMI.setCenter(spPMI);bpPMI.setBottom(bPMIback);bPMIback.setAlignment(Pos.TOP_RIGHT);
				Scene scenePrintMediaInfo = new Scene(bpPMI,1530,800);
				// MEDIA OPTIONS AND FILLING INFO
		bm.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
		      public void handle(ActionEvent e) {
    		stage.setScene(mediaStartScene);
    		//ADDING A MEDIA WINDOW
    	bMW1.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override 
      public void handle(ActionEvent e) {

    	  stage.setScene(theTypeOfMediaScene);
    	  stage.show();
    	   
    	  readTheType.setOnAction(new EventHandler<ActionEvent>() {
    		  
    		  @Override 
    	      public void handle(ActionEvent e) {
    			  
    			  int selectedIndex = cbMtype.getSelectionModel().getSelectedIndex();
        		    Object selectedItem = cbMtype.getSelectionModel().getSelectedItem();
				if(selectedItem.equals("MOVIE")) {
					stage.setScene(movieAddScene);
			    	  stage.show();
			    	  bMAaddmovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Object addingSelectedRating = cbMAratingsAvailable.getSelectionModel().getSelectedItem();
		        	    	  if(addingSelectedRating.equals("DR")) {
								try {
									mediaRentalSystem.addMovies(tfMA1movie.getText(),tfMA2movie.getText() , Integer.parseInt(tfMA3movie.getText()),"DR" );
								} catch (NumberFormatException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								}
		        	    	  else if(addingSelectedRating.equals("HR")) {
									try {
										mediaRentalSystem.addMovies(tfMA1movie.getText(),tfMA2movie.getText() , Integer.parseInt(tfMA3movie.getText()),"HR" );
									} catch (NumberFormatException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
		        	    	  else if(addingSelectedRating.equals("AC")) {
									try {
										mediaRentalSystem.addMovies(tfMA1movie.getText(),tfMA2movie.getText() , Integer.parseInt(tfMA3movie.getText()),"AC" );
									} catch (NumberFormatException | IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
		        	    	  
		        	      }
		        	    });
		        	  bMAbackmovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
    	    	  }
				else if(selectedItem.equals("ALBUM")) {
					stage.setScene(albumAddScene);
			    	  stage.show();
			    	  bMAaddalbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  
								try {
									mediaRentalSystem.addAlbum(tfMA1album.getText(),tfMA2album.getText() , Integer.parseInt(tfMA3album.getText()),tfMA4album.getText(),tfMA5album.getText() );
								} catch (NumberFormatException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
		        
		        	      }
		        	    });
		        	  bMAbackalbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}
				else if(selectedItem.equals("GAME")){
					stage.setScene(gameAddScene);
			    	  stage.show();
			    	  bMAaddgame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  
								try {
									mediaRentalSystem.addGame(tfMA1game.getText(),tfMA2game.getText() , Integer.parseInt(tfMA3game.getText()), Double.parseDouble(tfMA4game.getText()));
								} catch (NumberFormatException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	      }
		        	    });
		        	  bMAbackgame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}

    	      }
    		  
    	    });
    	        
      
      }
    });
    	//FOR DELETING A MEDIA WINDOW
    	bMW2.setOnAction(new EventHandler<ActionEvent>() {

    		@Override 
      public void handle(ActionEvent e) {

    	  stage.setScene(theTypeOfMediaScene);
    	  stage.show();
    	   
    	  readTheType.setOnAction(new EventHandler<ActionEvent>() {
    		  int h;
    		  @Override 
    	      public void handle(ActionEvent e) {
    			  
    			  int selectedIndex = cbMtype.getSelectionModel().getSelectedIndex();
        		    Object selectedItem = cbMtype.getSelectionModel().getSelectedItem();
				if(selectedItem.equals("MOVIE")) {
					stage.setScene(movieDeleteScene);
			    	  stage.show();
			    	  bMDdeletemovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Object deletingSelectedRating = cbMDratingsAvailable.getSelectionModel().getSelectedItem();
		        	    	  if(deletingSelectedRating.equals("DR")) {
		        	    	  Movie m = new Movie(tfMD1movie.getText(),tfMD2movie.getText() , Integer.parseInt(tfMD3movie.getText()),"DR");
		        	    	  for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		        	    		  if(m.toString().equals(mediaRentalSystem.medias.get(i).toString())) {
		        	    			  h=i;
		        	    		  }
		        	    	  }
		        	    	  mediaRentalSystem.medias.remove(h);
		        	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	      }
		        	    	  else if(deletingSelectedRating.equals("HR")) {
			        	    	  Movie m = new Movie(tfMD1movie.getText(),tfMD2movie.getText() , Integer.parseInt(tfMD3movie.getText()),"HR");
			        	    	  for(int i=0;i<mediaRentalSystem.medias.size();i++) {
			        	    		  if(m.toString().equals(mediaRentalSystem.medias.get(i).toString())) {
			        	    			  h=i;
			        	    		  }
			        	    	  }
			        	    	  mediaRentalSystem.medias.remove(h);
			        	    	  try {
										mediaRentalSystem.getAllMediaInfo();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			        	      }
		        	    	  else if(deletingSelectedRating.equals("AC")) {
			        	    	  Movie m = new Movie(tfMD1movie.getText(),tfMD2movie.getText() , Integer.parseInt(tfMD3movie.getText()),"AC");
			        	    	  for(int i=0;i<mediaRentalSystem.medias.size();i++) {
			        	    		  if(m.toString().equals(mediaRentalSystem.medias.get(i).toString())) {
			        	    			  h=i;
			        	    		  }
			        	    	  }
			        	    	  mediaRentalSystem.medias.remove(h);
			        	    	  try {
										mediaRentalSystem.getAllMediaInfo();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			        	      }
		        	      }
		        	    });
		        	  bMDbackmovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
    	    	  }
				else if(selectedItem.equals("ALBUM")) {
					stage.setScene(albumDeleteScene);
			    	  stage.show();
			    	  bMDdeleteAlbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Album a = new Album(tfMD1album.getText(),tfMD2album.getText() , Integer.parseInt(tfMD3album.getText()),tfMD4album.getText(),tfMD5album.getText());
		        	    	  for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		        	    		  if(a.toString().compareTo(mediaRentalSystem.medias.get(i).toString())==0) {
		        	    			  h=i;
		        	    		  }
		        	    	  }
		        	    	  mediaRentalSystem.medias.remove(h);
		        	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	      }
		        	    });
		        	  bMDbackAlbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}
				else if(selectedItem.equals("GAME")){
					stage.setScene(gameDeleteScene);
			    	  stage.show();
			    	  bMDdeleteGame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Game g = new Game(tfMD1game.getText(),tfMD2game.getText() , Integer.parseInt(tfMD3game.getText()), Double.parseDouble(tfMD4game.getText()));
		        	    	  for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		        	    		  if(g.toString().compareTo(mediaRentalSystem.medias.get(i).toString())==0) {
		        	    			  h=i;
		        	    		  }
		        	    	  }
		        	    	  mediaRentalSystem.medias.remove(h);
		        	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	      }
		        	    });
		        	  bMDbackGame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}

    	      }
    		  
    	    });

      }
    	    });
		//FOR UPDATING INFO ABOUT A MEDIA WINDOW
    	bMW3.setOnAction(new EventHandler<ActionEvent>() {

    		@Override 
      public void handle(ActionEvent e) {

    	  stage.setScene(theTypeOfMediaScene);
    	  stage.show();
    	   
    	  readTheType.setOnAction(new EventHandler<ActionEvent>() {
    		  int haha3;
    		  @Override 
    	      public void handle(ActionEvent e) {
    			  
    			  int selectedIndex2 = cbMtype.getSelectionModel().getSelectedIndex();
        		    Object selectedItem2 = cbMtype.getSelectionModel().getSelectedItem();
				if(selectedItem2.equals("MOVIE")) {
					stage.setScene(sceneUpdateMovie);
			    	  stage.show();
			    	  bMUfindmovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Object updatingSelectedRating = cbMUratingsAvailable.getSelectionModel().getSelectedItem();
		        	    	  if(updatingSelectedRating.equals("DR")) {
		        	    	  Movie  movieSerach = new Movie(tfMU1movie.getText(), tfMU2movie.getText() ,Integer.parseInt(tfMU3movie.getText()),"DR");
		    	    	    	 
		    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		    	    	    		  if(mediaRentalSystem.medias.get(i).toString().compareTo(movieSerach.toString())==0) {
		    	    	    			  haha3=i;
		    	    	    			  gpMUmovie.add(lMUmessage, 0, 5);
		    	    	    			  gpMUmovie.add(cbMUtit, 0, 6);
		    	    	    			  gpMUmovie.add(cbMUcode, 0, 7);
		    	    	    			  gpMUmovie.add(cbMUcop, 0, 8);
		    	    	    			
		    	    	    			  cbMUtit.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUmovie.add(tfMU5movie, 1, 6);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUcode.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUmovie.add(tfMU6movie, 1, 7);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUcop.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUmovie.add(tfMU7movie, 1, 8);
		    	    	    				  }
		    	    	    			  });
		    	    	    		  }
		    	    	    	}
		        	      }
		        	    	  else if(updatingSelectedRating.equals("HR")) {
			        	    	  Movie  movieSerach = new Movie(tfMU1movie.getText(), tfMU2movie.getText() ,Integer.parseInt(tfMU3movie.getText()),"HR");
			    	    	    	 
			    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
			    	    	    		  if(mediaRentalSystem.medias.get(i).toString().compareTo(movieSerach.toString())==0) {
			    	    	    			  haha3=i;
			    	    	    			  gpMUmovie.add(lMUmessage, 0, 5);
			    	    	    			  gpMUmovie.add(cbMUtit, 0, 6);
			    	    	    			  gpMUmovie.add(cbMUcode, 0, 7);
			    	    	    			  gpMUmovie.add(cbMUcop, 0, 8);
			    	    	    			
			    	    	    			  cbMUtit.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU5movie, 1, 6);
			    	    	    				  }
			    	    	    			  });
			    	    	    			  cbMUcode.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU6movie, 1, 7);
			    	    	    				  }
			    	    	    			  });
			    	    	    			  cbMUcop.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU7movie, 1, 8);
			    	    	    				  }
			    	    	    			  });
			    	    	    		  }
			    	    	    	}
			        	      }
		        	    	  else if(updatingSelectedRating.equals("AC")) {
			        	    	  Movie  movieSerach = new Movie(tfMU1movie.getText(), tfMU2movie.getText() ,Integer.parseInt(tfMU3movie.getText()),"AC");
			    	    	    	 
			    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
			    	    	    		  if(mediaRentalSystem.medias.get(i).toString().compareTo(movieSerach.toString())==0) {
			    	    	    			  haha3=i;
			    	    	    			  gpMUmovie.add(lMUmessage, 0, 5);
			    	    	    			  gpMUmovie.add(cbMUtit, 0, 6);
			    	    	    			  gpMUmovie.add(cbMUcode, 0, 7);
			    	    	    			  gpMUmovie.add(cbMUcop, 0, 8);
			    	    	    			
			    	    	    			  cbMUtit.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU5movie, 1, 6);
			    	    	    				  }
			    	    	    			  });
			    	    	    			  cbMUcode.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU6movie, 1, 7);
			    	    	    				  }
			    	    	    			  });
			    	    	    			  cbMUcop.setOnAction(new EventHandler<ActionEvent>() {
			    	    	    				  @Override 
			    	    	    	    	      public void handle(ActionEvent e) {
			    	    	    					  gpMUmovie.add(tfMU7movie, 1, 8);
			    	    	    				  }
			    	    	    			  });
			    	    	    		  }
			    	    	    	}
			        	      }
		        	      }
		        	    });
		        	  bMUupdatemovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  mediaRentalSystem.medias.get(haha3).setTitle(tfMU5movie.getText());
	    	    	    	  mediaRentalSystem.medias.get(haha3).setCode(tfMU6movie.getText());
                              mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(Integer.parseInt(tfMU7movie.getText()));

	    	    	    	  
	    	    	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	    	  
		        	      }
		        	    });
		        	  bMUbackmovie.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
    	    	  }
				else if(selectedItem2.equals("ALBUM")) {
					stage.setScene(sceneUpdateAlbum);
			    	  stage.show();
			    	  bMUfindalbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Album updateAlbum = new Album(tfMU1album.getText(),tfMU2album.getText() , Integer.parseInt(tfMU3album.getText()),tfMU4album.getText(),tfMU5album.getText());		    	    	    	 
		    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		    	    	    		  if(mediaRentalSystem.medias.get(i).toString().compareTo(updateAlbum.toString())==0) {
		    	    	    			  haha3=i;
		    	    	    			  gpMUalbum.add(lMUmessage, 0, 6);
		    	    	    			  gpMUalbum.add(cbMUalbumtit, 0, 7);
		    	    	    			  gpMUalbum.add(cbMUalbumcode, 0, 8);
		    	    	    			  gpMUalbum.add(cbMUalbumcop, 0, 9);
		    	    	    			
		    	    	    			  cbMUalbumtit.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUalbum.add(tfMU6album, 1, 7);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUalbumcode.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUalbum.add(tfMU7album, 1, 8);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUalbumcop.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUalbum.add(tfMU8album, 1, 9);
		    	    	    				  }
		    	    	    			  });
		    	    	    		  }
		    	    	    	}
		       	    	  
		        	      }
		        	    });
		        	  bMUupdatealbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  if(tfMU6album.getText()==null || tfMU6album.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setTitle(tfMU1album.getText());
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setTitle(tfMU6album.getText());
		        	    	  }
		        	    	  if(tfMU7album.getText()==null || tfMU7album.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setCode(tfMU2album.getText());
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setCode(tfMU7album.getText());
		        	    	  }
		        	    	  if(tfMU8album.getText()==null || tfMU8album.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(Integer.parseInt(tfMU3album.getText()));
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(Integer.parseInt(tfMU8album.getText()));
		        	    	  }

	    	    	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	    	  
		        	      }
		        	    });
		        	  bMUbackalbum.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}
				else if(selectedItem2.equals("GAME")){
					stage.setScene(sceneUpdateGame);
			    	  stage.show();
			    	  bMUfindGame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  Game updateGame = new Game(tfMU1game.getText(),tfMU2game.getText() , Integer.parseInt(tfMU3game.getText()), Double.parseDouble(tfMU4game.getText()));
		    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
		    	    	    		  if(mediaRentalSystem.medias.get(i).toString().compareTo(updateGame.toString())==0) {
		    	    	    			  haha3=i;
		    	    	    			  gpMUgame.add(UpdatingGameMessage, 0, 5);
		    	    	    			  gpMUgame.add(cbMUgametit, 0, 6);
		    	    	    			  gpMUgame.add(cbMUgamecode, 0, 7);
		    	    	    			  gpMUgame.add(cbMUgamecop, 0, 8);
		    	    	    			
		    	    	    			  cbMUgametit.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUgame.add(tfMU5game, 1, 6);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUgamecode.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUgame.add(tfMU6game, 1, 7);
		    	    	    				  }
		    	    	    			  });
		    	    	    			  cbMUgamecop.setOnAction(new EventHandler<ActionEvent>() {
		    	    	    				  @Override 
		    	    	    	    	      public void handle(ActionEvent e) {
		    	    	    					  gpMUgame.add(tfMU7game, 1, 8);
		    	    	    				  }
		    	    	    			  });
		    	    	    		  }
		    	    	    	}
		        	      }
		        	    });
			    	  bMUupdateGame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  mediaRentalSystem.medias.get(haha3).setTitle(tfMU5game.getText());
	    	    	    	  mediaRentalSystem.medias.get(haha3).setCode(tfMU6game.getText());
	    	    	    	  for(int i=0;i<Integer.parseInt(tfMU7game.getText());i++) {
	    	    	    		  mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(true);
	    	    	    	  }
	    	    	    	  if(tfMU5game.getText()==null || tfMU5game.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setTitle(tfMU1game.getText());
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setTitle(tfMU5game.getText());
		        	    	  }
		        	    	  if(tfMU6game.getText()==null || tfMU6game.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setCode(tfMU2game.getText());
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setCode(tfMU6game.getText());
		        	    	  }
		        	    	  if(tfMU7game.getText()==null || tfMU7game.getText()=="") {
		        	    		  mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(Integer.parseInt(tfMU3game.getText()));
		        	    	  }
		        	    	  else {
		        	    		  mediaRentalSystem.medias.get(haha3).setNumberOfCopiesAvailable(Integer.parseInt(tfMU7game.getText()));
		        	    	  }
	    	    	    	  try {
									mediaRentalSystem.getAllMediaInfo();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		        	    	  
		        	      }
		        	    });
			    	  bMUbackGame.setOnAction(new EventHandler<ActionEvent>() {
		        	      @Override 
		        	      public void handle(ActionEvent e) {
		        	    	  stage.setScene(mediaStartScene);
		        	    	  stage.show();
		        	    	  
		        	      }
		        	    });
				}

    	      }
    		  
    	    });

      }
    	    });
        //FOR SEARCHING A MEDIA WINDOW 
    	bMW4.setOnAction(new EventHandler<ActionEvent>() {
    		String newLine = "\n";
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  
    	    	  stage.setScene(sceneSearchMedia);
    	    	  stage.show();
    	    	   
    	    	  bMSsearch.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	//Media  mediaSearch = new Media(tfCU2.getText(), tfCU1.getText() , tfCU3.getText(), tfCU4.getText(),tfCU5.getText());
    	    	    	 int justCounter =0;
    	    	    	for(int i=0;i<mediaRentalSystem.medias.size();i++) {
    	    	    		  if(tfMS.getText().equals(mediaRentalSystem.medias.get(i).getCode())) {
    	    	    			  
    	    	    			  String foundMessage = "!! MEDIA FOUND !!\n"+mediaRentalSystem.medias.get(i).toString()+"\n--------------------------";
    	    	    			  taMS.appendText(foundMessage);
    	    	    			  justCounter++;
    	    	    			  
    	    	    		  }
    	    	    	  }
    	    	    	if(justCounter==0) {
    	    	    		String foundMessage = "!! MEDIA NOT FOUND !!\n"+"\n--------------------------";
                            taMS.appendText(foundMessage);
    	    	    	}
    	    	    	
    	    	    	taMS.appendText(newLine);
    	    	      }
    	    	    });
    	    	  bMSback.setOnAction(new EventHandler<ActionEvent>() {
    	    	      @Override 
    	    	      public void handle(ActionEvent e) {
    	    	    	  stage.setScene(mediaStartScene);
    	    	    	  stage.show();
    	    	    	  
    	    	      }
    	    	    });
    	      }
    	    });
		//FOR PRINTING ALL THE INFO ABOUT THE MEDIA
    	bMW5.setOnAction(new EventHandler<ActionEvent>() {
   		 
    		@Override 
    	      public void handle(ActionEvent e) {
    			try {
					mediaInfo.setText(mediaRentalSystem.getAllMediaInfo());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	  stage.setScene(scenePrintMediaInfo);
    	    	  stage.show();
    	    	  bPMIback.setOnAction(new EventHandler<ActionEvent>() {
    	     		 
    	      		@Override 
    	      	      public void handle(ActionEvent e) {
    	      	    	  stage.setScene(mediaStartScene);
    	      	    	  stage.show();
    	      	      }
    	      	    });
    	      }
    	    });
    	//FOR RETURNING TO MAIN PAGE
    	bMW6.setOnAction(new EventHandler<ActionEvent>() {
    		 
    		@Override 
    	      public void handle(ActionEvent e) {
    	    	  stage.setScene(scene);
    	    	  stage.setMaximized(true);
    	    	  stage.show();
    	      }
    	    });
			}
		});
		
		// RENT START WINDOW
				VBox vbRW = new VBox(30); 
				RadioButton bRW1 = new RadioButton("RENT FORM");
				bRW1.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 30));
				RadioButton bRW2 = new RadioButton("PRINT THE INTERESTED MEDIA BY A CUSTOMER");
				bRW2.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 30));
				RadioButton bRW3 = new RadioButton("PRINT THE RENTED MEDIA BY A CUSTOMER");
				bRW3.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 30));
				RadioButton bRW4 = new RadioButton("RETURN RENTED MEDIA");
				bRW4.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 30));
				RadioButton bRW5 = new RadioButton("RETURN TO MAIN PAGE");
				bRW5.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 30));
				ToggleGroup tg3 = new ToggleGroup();
				bRW1.setToggleGroup(tg3);bRW2.setToggleGroup(tg3);bRW3.setToggleGroup(tg3);bRW4.setToggleGroup(tg3);bRW5.setToggleGroup(tg3);
				vbRW.getChildren().addAll(bRW1,bRW2,bRW3,bRW4,bRW5);
				vbRW.setAlignment(Pos.CENTER);
				vbRW.setStyle("-fx-background-color: honeydew; -fx-padding: 10; -fx-font-size: 20;");
				Scene RentStartWindowScene = new Scene(vbRW, 1600, 800);
		// RENT FORM WINDOW
		
				GridPane gpRform = new GridPane();
				Label lRform1 = new Label("CUSTOMER ID:");
				lRform1.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRform1.setStyle("-fx-background-color: lightblue;");
				Label lRform2 = new Label("MEDIA CODE:");
				lRform2.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRform2.setStyle("-fx-background-color: lightblue;");
				Label lRform3 = new Label("RENTED DATE:");
				lRform3.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRform3.setStyle("-fx-background-color: lightblue;");
				TextField tfRform1 = new TextField();
				TextField tfRform2 = new TextField();
				TextField tfRform3 = new TextField();
				TextArea taRform1 = new TextArea();
				TextArea taRform2 = new TextArea();
				gpRform.add(lRform1, 0, 0);
				gpRform.add(taRform1, 3, 1);
				gpRform.add(lRform2, 0, 2);
				gpRform.add(taRform2, 3, 3);
				gpRform.add(lRform3, 0, 4);
				gpRform.add(tfRform1, 1, 0);
				gpRform.add(tfRform2, 1, 2);
				gpRform.add(tfRform3, 1, 4);
				gpRform.setHgap(50);gpRform.setVgap(50);
				gpRform.setStyle("-fx-background-color: honeydew; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbRform = new HBox(50);
				ImageView ivRaddToCartButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/addToCartIcon.png"));
				ImageView ivRprocessReqButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/processReqIcon.png"));
				ImageView ivRbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bRformAddToCart = new Button("ADD TO CART",ivRaddToCartButton);Button bRformProcessCart = new Button("PROCESS CART",ivRprocessReqButton);Button bRformBack = new Button("BACK",ivRbackButton);
				hbRform.setAlignment(Pos.BOTTOM_CENTER);
				hbRform.getChildren().addAll(bRformAddToCart,bRformProcessCart,bRformBack);
				
				BorderPane bpRform = new BorderPane();
				bpRform.setCenter(gpRform);bpRform.setBottom(hbRform);
				bpRform.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-font-size: 20;");
				Scene RentFormScene = new Scene(bpRform, 1530, 800);
				
				// PRINT THE INTERESTED IN MEDIA WINDOW 
				GridPane gpRprintCart = new GridPane();
				Label lRprintCart = new Label("CUSTOMER ID:");
				lRprintCart.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRprintCart.setStyle("-fx-background-color: lightblue;");
				TextField tfRprintCart = new TextField();
				TextArea taRprintCart = new TextArea();
				gpRprintCart.add(lRprintCart, 0, 0);
				gpRprintCart.add(tfRprintCart, 0, 1);
				gpRprintCart.add(taRprintCart, 0, 2);
				gpRprintCart.setHgap(50);gpRprintCart.setVgap(50);
				gpRprintCart.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbRprintCart = new HBox(50);
				ImageView ivRprintCartButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/printingIcon.png"));
				ImageView ivRprintCartbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bRprintCartPrint = new Button("PRINT",ivRprintCartButton);Button bRprintCartBack = new Button("BACK",ivRprintCartbackButton);
				
				hbRprintCart.setAlignment(Pos.BOTTOM_CENTER);
				hbRprintCart.getChildren().addAll(bRprintCartPrint,bRprintCartBack);
				
				BorderPane bpRprintCart = new BorderPane();
				ImageView ivRprintCartStartPic = new ImageView(new Image("file:///C:/Users/Hp/Downloads/cartf.jpg"));
				gpRprintCart.add(ivRprintCartStartPic, 4, 2);
				bpRprintCart.setCenter(gpRprintCart);bpRprintCart.setBottom(hbRprintCart);
				bpRprintCart.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-font-size: 20;");
				Scene PrintCartScene = new Scene(bpRprintCart, 1530, 800);
                
				// PRINT THE RENTED MEDIA WINDOW
				GridPane gpRprintRented = new GridPane();
				Label lRprintRented = new Label("CUSTOMER ID:");
				lRprintRented.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRprintRented.setStyle("-fx-background-color: lightblue;");
				TextField tfRprintRented = new TextField();
				TextArea taRprintRented = new TextArea();
				gpRprintRented.add(lRprintRented, 0, 0);
				gpRprintRented.add(tfRprintRented, 0, 1);
				gpRprintRented.add(taRprintRented, 0, 2);
				gpRprintRented.setHgap(50);gpRprintRented.setVgap(50);
				gpRprintRented.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbRprintRented = new HBox(50);
				ImageView ivRprintRentedButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/printingIcon.png"));
				ImageView ivRprintRentedbackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bRprintRentedPrint = new Button("PRINT",ivRprintRentedButton);Button bRprintRentedBack = new Button("BACK",ivRprintRentedbackButton);
				hbRprintRented.setAlignment(Pos.BOTTOM_CENTER);
				hbRprintRented.getChildren().addAll(bRprintRentedPrint,bRprintRentedBack);
				
				BorderPane bpRprintRented = new BorderPane();
				ImageView ivRprintRentedStartPic = new ImageView(new Image("file:///C:/Users/Hp/Downloads/rentedf.png"));
				bpRprintRented.setCenter(gpRprintRented);bpRprintRented.setBottom(hbRprintRented);
				gpRprintRented.add(ivRprintRentedStartPic, 4, 2);
				bpRprintRented.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-font-size: 20;");
				Scene PrintRentedScene = new Scene(bpRprintRented, 1530, 800);
                
				// RETURN THE RENTED MEDIA WINDOW
				GridPane gpRreturnRented = new GridPane();
				Label lRreturnRented1 = new Label("CUSTOMER ID:");
				lRreturnRented1.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRreturnRented1.setStyle("-fx-background-color: lightblue;");
				Label lRreturnRented2 = new Label("MEDIA CODE:");
				lRreturnRented2.setFont(Font.font("Times New Roman", 
					      FontWeight.BOLD, FontPosture.ITALIC, 17));
				lRreturnRented2.setStyle("-fx-background-color: lightblue;");
				TextField tfRreturnRented1 = new TextField();
				TextField tfRreturnRented2 = new TextField();
				TextArea taRreturnRented = new TextArea();
				
				gpRreturnRented.add(lRreturnRented1, 0, 0);
				gpRreturnRented.add(tfRreturnRented1, 0, 1);
				gpRreturnRented.add(lRreturnRented2, 0, 2);
				gpRreturnRented.add(tfRreturnRented2, 0, 3);
				gpRreturnRented.add(taRreturnRented, 0, 4);
				gpRreturnRented.setHgap(50);gpRreturnRented.setVgap(50);
				gpRreturnRented.setStyle("-fx-background-color: ghostwhite; -fx-padding: 10; -fx-font-size: 20;");
				
				HBox hbRreturnRented = new HBox(50);
				ImageView ivRreturnMediaButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/returnMediaIcon.png"));
				ImageView ivRreturnMediaBackButton  = new ImageView(new Image("file:///C:/Users/Hp/Downloads/backIconnnF.png"));
				Button bRreturnRentedReturn = new Button("RETURN MEDIA RENTED",ivRreturnMediaButton);Button bRreturnRentedBack = new Button("BACK",ivRreturnMediaBackButton);
				hbRreturnRented.setAlignment(Pos.BOTTOM_CENTER);
				hbRreturnRented.getChildren().addAll(bRreturnRentedReturn,bRreturnRentedBack);
				
				BorderPane bpRreturnRented = new BorderPane();
				ImageView ivRreturnRentedStartPic = new ImageView(new Image("file:///C:/Users/Hp/Downloads/borrowingIcon.png"));
				gpRreturnRented.add(ivRreturnRentedStartPic, 4, 4);
				bpRreturnRented.setCenter(gpRreturnRented);bpRreturnRented.setBottom(hbRreturnRented);
				bpRreturnRented.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-font-size: 20;");
				Scene ReturnRentedScene = new Scene(bpRreturnRented, 1530, 800);
				// RENT OPTIONS AND FILLING INFO
		br.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
	   	      public void handle(ActionEvent e) {
			  stage.setScene(RentStartWindowScene);
 	    	  stage.setMaximized(true);
 	    	  stage.show();
		bRW1.setOnAction(new EventHandler<ActionEvent>() {
   		 
   		@Override 
   	      public void handle(ActionEvent e) {
   			stage.setScene(RentFormScene);
	    	stage.show();
	    	bRformAddToCart.setOnAction(new EventHandler<ActionEvent>() {
   		 
   		@Override 
   	      public void handle(ActionEvent e) {
   			int counter1 = 0,counter2 = 0;
   			String newLine="\n";
   			for(int i=0;i<mediaRentalSystem.customers.size();i++) {
   				if(tfRform1.getText().equals(mediaRentalSystem.customers.get(i).getId())) {
   					taRform1.appendText(mediaRentalSystem.customers.get(i).toString());taRform1.appendText(newLine);
   					counter1++;
   				}
   			}
   			for(int i=0;i<mediaRentalSystem.medias.size();i++) {
   				if(tfRform2.getText().equals(mediaRentalSystem.medias.get(i).getCode())) {
   					taRform2.appendText(mediaRentalSystem.medias.get(i).toString());taRform2.appendText(newLine);
   					counter2++;
   				}
   			}
   			
   			if(counter1+counter2 == 2 ) {
   				try {
   					String tempMessage  = "!! ADDING TO CART DONE !!";
   					taRform2.appendText(tempMessage);
   					mediaRentalSystem.addToCart(tfRform1.getText(), tfRform2.getText());
   					Date s = new Date();
   		   			String str = new String(s.toString());
   		   			tfRform3.setText(str);
   				} catch (Exception e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
   			}
   			else {
   				if(counter1 == 0) {
   					String tempMessageCust  = "!! THIS CUSTOMER DOESN'T EXIST AT ALL !!";
					taRform1.appendText(tempMessageCust);
   				}
   				if(counter2 == 0) {
   					String tempMessageMed  = "!! THIS MEDIA DOESN'T EXIST AT ALL !!";
					taRform2.appendText(tempMessageMed);
   				}
   				
   			}
   			taRform2.appendText(newLine);
   			taRform1.appendText(newLine);
   	      }
   	    });
	    	bRformProcessCart.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			
		    	int counter1=0;int counter2=0;String newLine = "\n";
   			
   			for(int i=0;i<mediaRentalSystem.customers.size();i++) {
   				if(tfRform1.getText().equals(mediaRentalSystem.customers.get(i).getId())) {
   					taRform1.appendText(mediaRentalSystem.customers.get(i).toString());taRform1.appendText(newLine);
   					counter1++;
   				}
   			}
   			for(int i=0;i<mediaRentalSystem.medias.size();i++) {
   				if(tfRform2.getText().equals(mediaRentalSystem.medias.get(i).getCode())) {
   					taRform2.appendText(mediaRentalSystem.medias.get(i).toString());taRform2.appendText(newLine);
   					counter2++;
   				}
   			}
   			
   			if(counter1+counter2 == 2 ) {
   				try {
   					String tempMessage = "!! PROCESSING CART DONE !!";
   					taRform2.appendText(tempMessage);
   					String n = mediaRentalSystem.processRequest(tfRform1.getText(),tfRform2.getText());
   					mediaRentalSystem.getAllCartInfo();
   				    System.out.println(n);
   					//mediaRentalSystem.processRequest(tfRform1.getText(),tfRform2.getText());
   					Date s = new Date();
   		   			String str = new String(s.toString());
   		   			tfRform3.setText(str);
   				} catch (Exception e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
   			}
   			else {
   				if(counter1 == 0) {
   					String tempMessageCust  = "!! THIS CUSTOMER DOESN'T EXIST AT ALL !!";
					taRform1.appendText(tempMessageCust);
   				}
   				if(counter2 == 0) {
   					String tempMessageMed  = "!! THIS MEDIA DOESN'T EXIST AT ALL !!";
					taRform2.appendText(tempMessageMed);
   				}
   				
   			}
   			taRform2.appendText(newLine);
   			taRform1.appendText(newLine);

	   	      }
	   	    });
	    	bRformBack.setOnAction(new EventHandler<ActionEvent>() {
		   		 
		   		@Override 
		   	      public void handle(ActionEvent e) {
		   			stage.setScene(RentStartWindowScene);
			    	stage.show();
			    	
			      }
		   	    });
	    	
	    	
   	      }
   	    });
		bRW2.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(PrintCartScene);
		    	stage.show();
		    	bRprintCartPrint.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			int counter=0;
	   			for(int i=0;i<mediaRentalSystem.customers.size();i++) {
	   				if(tfRprintCart.getText().equals(mediaRentalSystem.customers.get(i).getId())) {
	   					taRprintCart.appendText(mediaRentalSystem.customers.get(i).interestedIn.toString());counter++;
	   				}
	   			}
	   			if(counter==0) {
	   				String noSuchCustomer  = "No Such Customer Matched";
	   				taRprintCart.appendText(noSuchCustomer);
	   			}
	   			
		    	
	   	      }
	   	    });
		    	bRprintCartBack.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(RentStartWindowScene);
		    	stage.show();
		    	
		      }
	   	    });
		    	
	   	      }
	   	    });
		bRW3.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(PrintRentedScene);
		    	stage.show();
		    	
		    	bRprintRentedPrint.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			int counter=0;
	   			for(int i=0;i<mediaRentalSystem.customers.size();i++) {
	   				if(tfRprintRented.getText().equals(mediaRentalSystem.customers.get(i).getId())) {
	   					taRprintRented.appendText(mediaRentalSystem.customers.get(i).rented.toString());counter++;
	   				}
	   			}
	   			if(counter==0) {
	   				String noSuchCustomer  = "No Such Customer Matched";
	   				taRprintRented.appendText(noSuchCustomer);
	   			}
	   			
		    	
	   	      }
	   	    });
		    	bRprintRentedBack.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(RentStartWindowScene);
		    	stage.show();
		    	
		      }
	   	    });
		    	
	   	      }
	   	    });
		bRW4.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(ReturnRentedScene);
		    	stage.show();
		    	
		    	
		    	bRreturnRentedReturn.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			if(mediaRentalSystem.returnMedia(tfRreturnRented1.getText(),tfRreturnRented1.getText())) {
	   				String ReturnMessage = "!! Return Media Rented Done !!\n";
		   			taRreturnRented.appendText(ReturnMessage);
	   			}
	   			else {
	   				String ReturnMessage = "!! Return Media Rented FAILED !!\n";
		   			taRreturnRented.appendText(ReturnMessage);
	   			}
	   	      }
	   	    });
		    	bRreturnRentedBack.setOnAction(new EventHandler<ActionEvent>() {
			   		 
			   		@Override 
			   	      public void handle(ActionEvent e) {
			   			stage.setScene(RentStartWindowScene);
				    	stage.show();
				    	
				    	
			   	      }
			   	    });
		    	
		    	
	   	      }
	   	    });
		bRW5.setOnAction(new EventHandler<ActionEvent>() {
	   		 
	   		@Override 
	   	      public void handle(ActionEvent e) {
	   			stage.setScene(scene);
		    	stage.show();
		    	
	   	      }
	   	    });	
			}
			
		});		
		
	}

}
