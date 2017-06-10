package visual.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.product.ComponentManagement;
import model.product.composants.Sauce;

public class ControllerSauce implements Initializable{

    @FXML private ImageView ComponentImage;
    @FXML private CheckBox CHK_New;
    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private CheckBox CHK_Available;

    private String sauceName = ControllerAccueil.getSelectedItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	if(sauceName != ""){
    		Sauce sauce = ComponentManagement.getSauce(sauceName);
			TF_Libelle.setText(sauceName);
			TF_PhotoPath.setText(sauce.getPhoto());
			CHK_Available.selectedProperty().set(sauce.getAvailability());
			CHK_New.selectedProperty().set(sauce.getNew());
    	}
    }
    
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
    	sauce.setNew(CHK_New.selectedProperty().get());
    	if(sauce.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
    	if(sauceName != ""){
    		Sauce oldSauce = ComponentManagement.getSauce(sauceName);
    		ComponentManagement.getSauces().remove(oldSauce);
    	}
    	ComponentManagement.getSauces().add(sauce);
    	ComponentManagement.exportComponent("component.xml");
    	goToAccueil(new ActionEvent());
    }

}
