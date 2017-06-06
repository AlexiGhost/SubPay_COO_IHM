package visual.manager;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class ControllerDrink {

    @FXML
    private ImageView ComponentImage;

    @FXML
    private CheckBox CHK_New;

    @FXML
    private TextField TF_Libelle;

    @FXML
    private CheckBox CHK_Available;

    @FXML
    void goToAccueil(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("02_Accueil.fxml")));
		visual.Controller.setScene(acteur, "SUBPAY - Accueil");
    }
    
    @FXML
    void goToAuthentification(ActionEvent event) throws IOException {
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml")));
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
    }

    @FXML
    void parcourir(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) throws IOException {
    	
    	goToAccueil(new ActionEvent());
    }

}
