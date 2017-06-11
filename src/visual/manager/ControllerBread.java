package visual.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import model.product.ComponentManagement;
import model.product.composants.Bread;

public class ControllerBread implements Initializable{
	//TODO gerer allergenes

    @FXML private ImageView ComponentImage;
    @FXML private CheckBox CHK_New;
    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private CheckBox CHK_Available;
    @FXML private ListView<String> L_Allergenes;    
    @FXML private ComboBox<String> CB_Allergenes;
    
    private ObservableList<String> allergenData = FXCollections.observableArrayList();
    private ObservableList<String> allergenList = FXCollections.observableArrayList();

    private String breadName = ControllerAccueil.getSelectedItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	if(breadName != ""){
    		Bread bread = ComponentManagement.getBread(breadName);
			TF_Libelle.setText(breadName);
			//TODO ajouter la liste d'allergenes dans ComponentManagment
			//allergenList.setAll(ComponentManagement.getAllergens());
			CB_Allergenes.setItems(allergenList);
			if(bread != null){
				if(bread.getPhoto() != null) TF_PhotoPath.setText(bread.getPhoto());
				CHK_Available.selectedProperty().set(bread.getAvailability());
				CHK_New.selectedProperty().set(bread.getNew());
				allergenData.setAll(bread.getAllergens());
				L_Allergenes.setItems(allergenData);
			}
    	}
    }
    
    @FXML
    void ClearAllergenes(ActionEvent event) {
    	allergenData.clear();
    	L_Allergenes.setItems(allergenData);
    }

    @FXML
    void addAllergene(ActionEvent event) {
    	allergenData.add(CB_Allergenes.selectionModelProperty().getValue().getSelectedItem());
    	L_Allergenes.setItems(allergenData);
    }

    @FXML
    void delAllergene(ActionEvent event) {
    	allergenData.remove(L_Allergenes.selectionModelProperty().getValue().getSelectedItem());
    	L_Allergenes.setItems(allergenData);
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
    	Bread bread = new Bread(TF_Libelle.getText(), TF_PhotoPath.getText());
    	bread.setAvailability(CHK_Available.selectedProperty().get());
    	bread.setNew(CHK_New.selectedProperty().get());
    	if(bread.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
    	bread.getAllergens().addAll(allergenData);
    	if(breadName != ""){
    		Bread oldBread = ComponentManagement.getBread(breadName);
    		ComponentManagement.getBreads().remove(oldBread);
    	}
    	ComponentManagement.getBreads().add(bread);
    	ComponentManagement.exportComponent("component.xml");
    	goToAccueil(new ActionEvent());
    }

}
