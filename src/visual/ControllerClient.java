package visual;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CustomerManagement;
import model.product.ComponentManagement;
import model.product.Order;
import model.product.composants.Recipe;
import visual.customer.ControllerAccueil;
import visual.customer.ControllerGarnitures;
import visual.customer.ControllerRecettes;
import visual.customer.ControllerSauces;

public class ControllerClient extends Application {
	private static 				Stage 			theStage;
	
	public static void main(String[] args) throws Exception {
		ComponentManagement.importComponent("component.xml");
		CustomerManagement.importCustomer("customer.xml");
		Order.setOldNb(0);
		listCompleting();
		launch();
	}
	
	public void start(Stage stage) throws IOException {
		theStage = stage;
    	Group acteur = new Group();
	    acteur.getChildren().add(
	    FXMLLoader.load(getClass().getResource("customer/001 Bonjour.fxml"))
	    );
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
        theStage.setScene(scene);
        theStage.show();
    }
	
    public static void initialize() {
    	launch();
    }
    
	public static void setScene(Group acteur, String titre) throws IOException {
    	Scene scene = new Scene(acteur);
    	
    	theStage.setTitle(titre);
        theStage.setScene(scene);
        theStage.show();
	}
	public static void listCompleting(){
		//Page Accueil
		ControllerAccueil.getListPromo().addAll(ComponentManagement.getPromotions());
		ControllerAccueil.getListNew().addAll(ComponentManagement.getNews());
		
		//Page Recipe
		for (Recipe recipe : ComponentManagement.getRecipes()) {
			if(recipe.getCategory().equals("Bof")){
				ControllerRecettes.getBofList().add(recipe);
			}else if (recipe.getCategory().equals("Mouais")){
				ControllerRecettes.getMouaisList().add(recipe);
			}else{
				ControllerRecettes.getCaPasseList().add(recipe);
			}
		}
		
		//Page Garniture
		ControllerGarnitures.getGarnishList().addAll(ComponentManagement.getGarnishs());
		//Page Sauce
		ControllerSauces.getSauceList().addAll(ComponentManagement.getSauces());
	}
}

//comm de debug, a supprimer