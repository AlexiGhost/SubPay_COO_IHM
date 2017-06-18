package visual.customer;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.product.Menu;
import model.product.Product;

public class MenuController {
	private static boolean choice;
	private static Product product;
	private static Menu menu;
	
	public void goToSizeWithMenu(){
		choice = true; //On enregistre le choix du menu
		menu = new Menu(); //On crée le menu et son produit
		menu.setProduct(new Product());
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("015 Taille.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Taille"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToSizeWithoutMenu(){
		product = new Product(); //On crée le produit
		choice = false; //On enregistre le choix du produit uniquement
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("015 Taille.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Taille");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static Product getProduct() {
		return product;
	}
	
	public static Menu getMenu() {
		return menu;
	}
	
	public static boolean getChoice() {
		return choice;
	}
}
