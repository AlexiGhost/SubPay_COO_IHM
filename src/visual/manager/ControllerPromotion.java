package visual.manager;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.*;

public class ControllerPromotion {

    @FXML
    private TextField TF_Libelle;

    @FXML
    private ComboBox<?> CB_Categorie;

    @FXML
    private ComboBox<?> CB_Recipe;

    @FXML
    private TextField TF_Reduction;

    @FXML
    private DatePicker DF_EndDate;

    @FXML
    private CheckBox CHK_New;

    @FXML
    private CheckBox CHK_Auth;

    @FXML
    private Button BTN_Save;

    @FXML
    private Button BTN_Retour;
    
    @FXML
    void goToAccueil(ActionEvent event) throws IOException {
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("02_Accueil.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - Accueil");
    }

    @FXML
    void save(ActionEvent event) {

    }
    
    @FXML
    void goToAuthentification(ActionEvent event) throws IOException {
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml")));
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
    }
}
