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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.composants.Garnish;

public class GarnishController implements Initializable {
	private static List<Garnish> garnishList = new ArrayList<Garnish>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
    @FXML
    private TilePane garnishTile;
	
	
	public static List<Garnish> getGarnishList() {
		return garnishList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayGarnish();
	}
	
	public void displayGarnish() {
		for (Garnish garnish : garnishList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getName().equals(garnish.getName()))
				bordure.setStroke(Color.RED);
			
			//Group
			Group gar = new Group();
			gar.setTranslateX(20 * X);
			gar.setTranslateY(10 + Y);
			gar.setFocusTraversable(true);
			gar.setOnMouseClicked(MouseEvent -> myGarnishes(bordure, garnish));
			
			//Titre garniture
			Text title = new Text(garnish.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ garnish.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			gar.getChildren().add(img);
			gar.getChildren().add(title);
			gar.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!garnish.getAvailability()) {
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
				gar.getChildren().add(succes);
			}
			
			//Si c'est une nouveauté
			if(garnish.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				gar.getChildren().add(nouveau);
			}
			garnishTile.getChildren().add(gar);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static void myGarnishes(Rectangle rec, Garnish G) {
		if(G.getAvailability()) {
			if(rec.getStroke().equals(Color.LIGHTGREEN)) {
				rec.setStroke(Color.RED);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().addGarnish(G);
				else
					MenuController.getProduct().addGarnish(G);
			}
			else if(rec.getStroke().equals(Color.RED)) {
				rec.setStroke(Color.LIGHTGREEN);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().delGarnish(G);
				else
					MenuController.getProduct().delGarnish(G);
			}
		}
	}	
	
	public void goToSauces() throws IOException {
		X = 1;
		Y = 0;
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("010 Sauces.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces");
																
	}
	public void goToRecettes() throws IOException {
		X = 1;
		Y = 0;
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("008 Recettes.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
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
