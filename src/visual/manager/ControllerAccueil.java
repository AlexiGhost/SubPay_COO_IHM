package visual.manager;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerAccueil extends Application {
	private Stage theStage;
	
	public void start(Stage stage) throws IOException {
		theStage = stage;
    	Parent acteur = (Parent) FXMLLoader.load(
    			getClass().getResource("Accueil.fxml")
    			);
    	
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
    	
    	theStage.setTitle("SUBPAY-Manager-Accueil");
        theStage.setScene(scene);
        theStage.show();
    }
	/**Launch the manager interface*/
    public static void initialize() {
    	launch();
    }
    
	public void SwitchScene(Stage stage, String s) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	
    	Parent acteur = (Parent) loader.load(getClass().getResourceAsStream(s));
    	
    	Scene scene = new Scene(acteur, 1280.0, 720.0);
    	
    	stage.setTitle("SUBPAY-Accueil");
        stage.setScene(scene);
        stage.show();
	}
	
	@FXML
	public void deconnexion() {
		
	}
	@FXML
	public void addRecipe() {
		
	}
	@FXML
	public void addGarnish() {
		
	}
	@FXML
	public void addBread() {
		
	}
	@FXML
	public void addSauce() {
		
	}
	@FXML
	public void addDrink() {
		
	}
	@FXML
	public void addDessert() {
		
	}
	@FXML
	public void addPromo() {
		
	}
	@FXML
	public void editRecipe() {
		
	}
	@FXML
	public void editGarnish() {
		
	}
	@FXML
	public void editBread() {
		
	}
	@FXML
	public void editSauce() {
		
	}
	@FXML
	public void editDrink() {
		
	}
	@FXML
	public void editDessert() {
		
	}
	@FXML
	public void editPromo() {
		
	}
	@FXML
	public void delRecipe() {
		
	}
	@FXML
	public void delGarnish() {
		
	}
	@FXML
	public void delBread() {
		
	}
	@FXML
	public void delSauce() {
		
	}
	@FXML
	public void delDrink() {
		
	}
	@FXML
	public void delDessert() {
		
	}
	@FXML
	public void delPromo() {
		
	}
}
