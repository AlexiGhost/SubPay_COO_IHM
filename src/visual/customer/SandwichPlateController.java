package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class SandwichPlateController {

	public void goToBread(){
		//On enregistre le sandwich choisi
		if(MenuController.getChoice()) //Dans le menu s'il y en a un
			MenuController.getMenu().getProduct().setPlate(false);
		else //Ou dans le produit directement
			MenuController.getProduct().setPlate(false);
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("007 Pain.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Pain");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goToRecipe(){
		//On enregistre le plat choisi
		if(MenuController.getChoice()) //Dans le menu s'il y en a un
			MenuController.getMenu().getProduct().setPlate(true);
		else //Ou dans le produit directement
			MenuController.getProduct().setPlate(true);
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("008 Recettes.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
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
	
	public void goToSize(){
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("015 Taille.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Taille");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
