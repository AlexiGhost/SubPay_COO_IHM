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

public class ControllerClient extends Application {
	private static 				Stage 			theStage;
	
	public static void main(String[] args) {
		ComponentManagement.importComponent("component.xml");
		CustomerManagement.importCustomer("customer.xml");
		Order.setOldNb(0);
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
}

//comm de debug, a supprimer