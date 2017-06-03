package visual;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* 			Classe Controller
 * Unique valeur changeable : user (attribut)
 * MERCI DE NE PAS TOUCHER AU RESTE 
 * */
public class Controller extends Application { //Surtout ne pas passer sous ABSTRACT, ça fait tout planter
	private static 				Stage 			theStage;
	private static final		String			user = "Manager"; //A modifier selon l'interface qu'on souhaite visionner
	
	public void start(Stage stage) throws IOException {
		theStage = stage;
    	Group acteur = new Group();
    	if(user.equals("Client")) {
	    	acteur.getChildren().add(
	    			FXMLLoader.load(getClass().getResource("customer/001 Bonjour.fxml"))
	    			);
	    	//theStage.setTitle([AREMPLIR]); ------> Titre de la fenêtre (côté client), Myriam, je te laisse choisir
    	}
    	else {
    		acteur.getChildren().add(
	    			FXMLLoader.load(getClass().getResource("manager/01_Authentification.fxml")));
    		theStage.setTitle("SUBPAY - Authentification");
    	}
    	
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
