package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.composants.Sauce;
import visual.customer.MenuController;

public class SauceController implements Initializable {
	private static List<Sauce> sauceList = new ArrayList<Sauce>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
    @FXML
    private TilePane sauceTile;

    @FXML
    private Button suivant;

    @FXML
    private Button retour;
    
	public static List<Sauce> getSauceList() {
		return sauceList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displaySauce();
	}
	
	public void displaySauce() {
		for (Sauce sauce : sauceList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getName().equals(sauce.getName()))
				bordure.setStroke(Color.RED);
			
			//Group
			Group sau = new Group();
			sau.setTranslateX(20 * X);
			sau.setTranslateY(10 + Y);
			sau.setFocusTraversable(true);
			sau.setOnMouseClicked(MouseEvent -> mySauces(bordure, sauce));
			
			//Titre garniture
			Text title = new Text(sauce.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ sauce.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			sau.getChildren().add(img);
			sau.getChildren().add(title);
			sau.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!sauce.getAvailability()) {
				bordure.setOpacity(0.3);
				bordure.setFill(Color.YELLOWGREEN);
				title.setOpacity(0.3);
				img.setOpacity(0.3);
				Text succes = new Text("Victime de\nson succès");
				succes.setFont(new Font("Arial Black", 14));
				succes.setFill(Color.BLACK);
				succes.setOpacity(1);
				succes.setLayoutX(27);
				succes.setLayoutY(25);
				sau.getChildren().add(succes);
			}
			sauceTile.getChildren().add(sau);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static void mySauces(Rectangle rec, Sauce S) {
		if(S.getAvailability()) {
			if(rec.getStroke().equals(Color.LIGHTGREEN)) {
				rec.setStroke(Color.RED);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().addSauce(S);
				else
					MenuController.getProduct().addSauce(S);
			}
			else if(rec.getStroke().equals(Color.RED)) {
				rec.setStroke(Color.LIGHTGREEN);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().delSauce(S);
				else
					MenuController.getProduct().delSauce(S);
			}
		}
	}	
    
    
    
	public void goToGarnitures() throws IOException {
		X = 1;
		Y = 0;
		GarnishController.getGarnishList().clear(); //Pour supprimer tout ce que le client avait choisit
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("009 Garnitures.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); 
		}
	
	public void next() throws IOException {
		X = 1;
		Y = 0;
		if(MenuController.getChoice() && !(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getClass().getName().equals("model.product.Drink"))){ 
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("011 Boissons (Menu).fxml")) 
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Boissons");
		}
		else if(HelloController.getOrder().getAuthCustomer()){
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
		}
		else{
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource("004 Accueil.fxml")) 
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
		}
	}
	
	public void goToHome(){
		Group acteur = new Group();
		if(HelloController.getOrder().getAuthCustomer()){ //Si le client est authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Authentifié");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{ //S'il n'y a pas de client authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
