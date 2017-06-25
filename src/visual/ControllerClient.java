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
import visual.customer.HomeController;
import visual.customer.AllergenController;
import visual.customer.AuthHomeController;
import visual.customer.BreadController;
import visual.customer.DrinkController;
import visual.customer.GarnishController;
import visual.customer.RecipeController;
import visual.customer.SauceController;

public class ControllerClient extends Application {
	private static 				Stage 			theStage;
	
	public static void main(String[] args) throws Exception {
		ComponentManagement.importComponent("component.xml");
		CustomerManagement.importCustomer("customer.xml");
		//CustomerManagement.importCustomerOrders("order.xml");
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
    	theStage.setTitle("Bienvenue à SubPay");
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
		HomeController.getListPromo().addAll(ComponentManagement.getPromotions());
		HomeController.getListNew().addAll(ComponentManagement.getNews());
		
		//Page Accueil Auth
		AuthHomeController.getListPromo().addAll(ComponentManagement.getPromotions());
		AuthHomeController.getListNew().addAll(ComponentManagement.getNews());
		
		//Page Recipe
		for (Recipe recipe : ComponentManagement.getRecipes()) {
			if(recipe.getCategory().equals("Bof")){
				RecipeController.getBofList().add(recipe);
			}else if (recipe.getCategory().equals("Mouais")){
				RecipeController.getMouaisList().add(recipe);
			}else{
				RecipeController.getCaPasseList().add(recipe);
			}
		}
		
		//Home
		HomeController.getListRecipe().addAll(ComponentManagement.getRecipes());
		//Page Garniture
		GarnishController.getGarnishList().addAll(ComponentManagement.getGarnishs());
		//Page Sauce
		SauceController.getSauceList().addAll(ComponentManagement.getSauces());
		//Page Boissons
		DrinkController.getDrinkList().addAll(ComponentManagement.getDrinks());
		//Page Pains
		BreadController.getBreadList().addAll(ComponentManagement.getBreads());
		//Page Allergenes
		AllergenController.getAllergenList().addAll(ComponentManagement.getAllergens());
	}
}

//comm de debug, a supprimer