package visual.customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.ComponentManagement;
import model.product.Menu;
import model.product.Product;

public class MenuController implements Initializable {
	private static boolean choice;
	private static Product product;
	private static Menu menu;
	
	@FXML
    private Text price;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		price.setText("+ "+Menu.getMenuPrice()+"€");
		price.setFont(new Font("Arial Black", 28));
	}
	
	public void goToSizeWithMenu(){
		choice = true; //On enregistre le choix du menu
		menu = new Menu(); //On crée le menu et son produit
		menu.setProduct(new Product());
		menu.setDessert(ComponentManagement.getDesserts().get(0));
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
