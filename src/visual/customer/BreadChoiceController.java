package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import model.product.Menu;

public class BreadChoiceController {
	
	public void goToBread(){
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("007 Pain.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Pain");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToNext(){
		if(MenuController.getChoice()){ //S'il y a un menu
			Group acteur = new Group();
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("011 Boissons (menu).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Boissons");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(HelloController.getOrder().getAuthCustomer()){ //S'il n'y a pas de menu mais que le client est authentifie
			HelloController.getOrder().addProduct(MenuController.getProduct());
			javax.swing.JOptionPane.showMessageDialog(null, "Commande Validée"); 
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
			javax.swing.JOptionPane.showMessageDialog(null, "Commande Validée"); 
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
