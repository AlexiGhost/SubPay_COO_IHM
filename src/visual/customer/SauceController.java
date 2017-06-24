package visual.customer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import model.CustomerManagement;
import model.product.Drink;
import model.product.composants.Bread;
import model.product.composants.Sauce;
import visual.customer.MenuController;

public class SauceController implements Initializable {
	private static List<Sauce> sauceList = new ArrayList<Sauce>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
	private static ImageView	myPref = new ImageView();
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
			
			//Si c'est une nouveauté
			if(sauce.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				sau.getChildren().add(nouveau);
			}
			
			//Si c'est un client authentifié
			ImageView pref;
			if(HelloController.getOrder().getAuthCustomer()) {
				if(SignUpController.getAuthCusto().getFavoriteSauce() != null) {
					if(SignUpController.getAuthCusto().getFavoriteSauce().equals(sauce)){
						SignUpController.getAuthCusto().setFavoriteSauce(sauce);
						pref = new ImageView(new Image(new File("src/visual/images/coeurorange.png").toURI().toString()));
						myPref = pref;
					}
					else
						pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				}
				else
					pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				
				pref.setTranslateX(110);
				pref.setTranslateY(70);
				pref.setFocusTraversable(true);
				pref.setOnMouseEntered(Event -> sau.setOnMouseClicked(null));
				pref.setOnMouseExited(Event -> sau.setOnMouseClicked(MouseEvent -> mySauces(bordure, sauce)));
				pref.setOnMouseClicked(Event -> {
					if(myPref != pref) {
						Timeline animation = new Timeline (
								new KeyFrame(Duration.millis(100), new KeyValue(pref.fitHeightProperty(), 20)),
								new KeyFrame(Duration.millis(100), new KeyValue(pref.fitWidthProperty(), 20)),
								new KeyFrame(Duration.millis(200), new KeyValue(pref.fitHeightProperty(), 40)),
								new KeyFrame(Duration.millis(200), new KeyValue(pref.fitWidthProperty(), 40)),
								new KeyFrame(Duration.millis(300), new KeyValue(pref.fitHeightProperty(), 20)),
								new KeyFrame(Duration.millis(300), new KeyValue(pref.fitWidthProperty(), 20)),
								new KeyFrame(Duration.millis(400), new KeyValue(pref.fitWidthProperty(), pref.getFitWidth())),
								new KeyFrame(Duration.millis(400), new KeyValue(pref.fitHeightProperty(), pref.getFitHeight()))
								);
						animation.play();
						pref.setImage(new Image(new File("src/visual/images/coeurorange.png").toURI().toString()));
						myPref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteSauce(sauce);
						CustomerManagement.exportCustomer("customer.xml");
						myPref = pref;
					}
					else {
						pref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteSauce(new Sauce());
						CustomerManagement.exportCustomer("customer.xml");
						myPref = new ImageView();
					}
				});
				sau.getChildren().add(pref);
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
    
    
    
	public void goToGarnitures() {
		X = 1;
		Y = 0;
		if(MenuController.getChoice())
			MenuController.getMenu().getProduct().getGarnishs().clear();
		else
			MenuController.getProduct().getGarnishs().clear();
		if(MenuController.getChoice())
			MenuController.getMenu().getProduct().getSauces().clear();
		else
			MenuController.getProduct().getSauces().clear();	
		Group acteur = new Group();
		try {
			acteur.getChildren().add(
			FXMLLoader.load(getClass().getResource("009 Garnitures.fxml"))
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public void next() {
		X = 1;
		Y = 0;
		if(MenuController.getChoice()){ 
			Group acteur = new Group();
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("011 Boissons (Menu).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Boissons");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(HelloController.getOrder().getAuthCustomer()){ //S'il n'y a pas de menu mais que le client est authentifie
			HelloController.getOrder().addProduct(MenuController.getProduct());
			Group acteur = new Group();
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Auth");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else{//S'il n'y a pas de menu et que le client n'est pas authentifie
			HelloController.getOrder().addProduct(MenuController.getProduct());
			Group acteur = new Group();
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
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
