package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class SizeController {
	public void goToChoicePlateSandwich15(){
		if(MenuController.getChoice()) //On enregistre la taille dans le menu
			MenuController.getMenu().getProduct().setSize(15);
		else //Ou dans le produit directement
			MenuController.getProduct().setSize(15);
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("016 Format repas.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Format du repas");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToChoicePlateSandwich30(){
		if(MenuController.getChoice()) //On enregistre la taille dans le menu
			MenuController.getMenu().getProduct().setSize(30);
		else //Ou dans le produit directement
			MenuController.getProduct().setSize(30);
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("016 Format repas.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Format du repas");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToHome(){
		Group acteur = new Group();
		//Si le client est authentifié
		if(HelloController.getOrder().getAuthCustomer()){
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Authentifié");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//S'il n'y a pas de client authentifié
		else{
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void goToMenu(){
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("005 Menu.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
