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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.product.composants.Composant;
import model.product.composants.Promotion;

public class ControllerAccueil implements Initializable {

	private static 	List<Promotion>	ListPromo = new ArrayList<Promotion>();
	private static 	List<Composant>	ListNew = new ArrayList<Composant>();
	private static 	boolean 		newPromo = false;
	
    @FXML
    private 		TilePane 				promoTiled;
    @FXML
    private 		TilePane 				newTiled;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		affichePromo();
	}
	
	public static List<Composant> getListNew() {
		return ListNew;
	}
	public static List<Promotion> getListPromo() {
		return ListPromo;
	}
	
	public void affichePromo() {
		for (Promotion promotion : ListPromo) {
			//Group
			Group promo = new Group();
			promo.setFocusTraversable(true);
			promo.setOnMouseClicked(MouseEvent -> {
				try {
					newPromoChosen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Titre promotion
			Text title = new Text(promotion.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Pourcentage
			Text percent = new Text("-" + String.valueOf(promotion.getPercentage()) + " % \n");
			percent.setFont(new Font("Arial Black", 30));
			percent.setFill(Color.FORESTGREEN);
			percent.setLayoutY(30);
			percent.setLayoutX(20);
			
			//Descriptif
			Text comment = new Text("sur " + promotion.getCategory() + promotion.getRecipe() + " !");
			comment.setLayoutX(8);
			comment.setLayoutY(50);
			
			//Exclu Client Authentifié
			if(promotion.getAuthCustomer()) {
				Text client = new Text("Connectez-vous \npour en profiter !");
				client.setFill(Color.SANDYBROWN);
				client.setLayoutX(30);
				client.setLayoutY(70);
				client.setFont(Font.font("Arial", FontWeight.BOLD, 12));
				promo.getChildren().add(client);
			}
			
			//MAJ promoTiled
			promo.getChildren().add(comment);
			promo.getChildren().add(percent);
			promo.getChildren().add(title);
			promo.getChildren().add(bordure);
			promoTiled.getChildren().add(promo);
		}
		
		for (Composant nouveaute : ListNew) {
			//Group
			Group nouveau = new Group();
			nouveau.setFocusTraversable(true);
			nouveau.setOnMouseClicked(MouseEvent -> {
				try {
					newPromoChosen();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 150);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Titre nouveauté
			Text title = new Text(nouveaute.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image nouveauté
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+nouveaute.getPhoto()));
			img.setFitHeight(150);
			img.setFitWidth(150);
			nouveau.getChildren().add(img);
			
			//MAJ promoTiled
			nouveau.getChildren().add(title);
			nouveau.getChildren().add(bordure);
			newTiled.getChildren().add(nouveau);
		}
	}
	
	public void newPromoChosen() throws IOException{
		newPromo = true;
		goToMenu();
	}
	
	public void goToConnexion() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("002 Connexion.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Connexion"); 
	}
	
	public void goToInscription() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("003 Inscription.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Inscription");
	}
	
	public void goToMenu() throws IOException { 
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("005 Menu.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
	}
		
	public void goToPayer() throws IOException {
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("012 Payer.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Paiement");
	}
	public void goToAllergen() throws IOException {
		ControllerAllergen.setPreviousUI("004 Accueil.fxml");
		ControllerAllergen.setPreviousTitle("SUBPAY - Accueil");
		Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("006 Allergies.fxml"))
		);
		visual.ControllerClient.setScene(acteur, "SUBPAY - Allergènes");
	}
}
