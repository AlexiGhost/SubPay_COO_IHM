package visual.manager;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.product.composants.Sauce;

public class ControllerPromotion {

    @FXML
    private ImageView ComponentImage;

    @FXML
    private CheckBox CHK_New;

    @FXML
    private TextField TF_Libelle;

    @FXML
    private TextField TF_PhotoPath;
    
    @FXML
    private CheckBox CHK_Available;

    @FXML
    void goToAccueil(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("02_Accueil.fxml")));
		visual.ControllerManager.setScene(acteur, "SUBPAY - Accueil");
    }
    
    @FXML
    void goToAuthentification(ActionEvent event) throws IOException {
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml")));
		visual.ControllerManager.setScene(acteur, "SUBPAY - Authentification");
    }

    @FXML
    void save(ActionEvent event) throws IOException {
    	Sauce sauce = new Sauce(TF_Libelle.getText(), TF_PhotoPath.getText());
    	sauce.setAvailability(CHK_Available.selectedProperty().get());
    	//TODO set NEW
    	goToAccueil(new ActionEvent());
    }

}
