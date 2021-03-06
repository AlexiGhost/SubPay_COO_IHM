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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.product.ComponentManagement;
import model.product.Dessert;

public class ControllerDessert implements Initializable{

    @FXML private ImageView ComponentImage;
    @FXML private CheckBox CHK_New;
    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private CheckBox CHK_Available;
    @FXML private ListView<String> L_Allergenes;    
    @FXML private ComboBox<String> CB_Allergenes;
    
    private ObservableList<String> allergenData = FXCollections.observableArrayList();
    private ObservableList<String> allergenList = FXCollections.observableArrayList();

    String dessertName = ControllerAccueil.getSelectedItem();
    String componentPath = "component.xml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	allergenList.setAll(ComponentManagement.getAllergens());
    	CB_Allergenes.setItems(allergenList);
    	if(dessertName != ""){
    		Dessert dessert = ComponentManagement.getDessert(dessertName);
			TF_Libelle.setText(dessertName);
			if(dessert.getPhoto() != null) {
				TF_PhotoPath.setText(dessert.getPhoto());
				String fileName = TF_PhotoPath.getText();
				Image image = new Image("file:src\\visual\\images\\"+fileName);
				ComponentImage.setImage(image);
			}
			CHK_Available.selectedProperty().set(dessert.getAvailability());
			CHK_New.selectedProperty().set(dessert.getNew());
			allergenData.setAll(dessert.getAllergens());
			L_Allergenes.setItems(allergenData);

    	}
    }
    
    @FXML
    void changeImage(ActionEvent event){
    	String fileName = TF_PhotoPath.getText();
    	Image image = new Image("file:src\\visual\\images\\"+fileName);
    	ComponentImage.setImage(image);
    }
    
    @FXML
    void ClearAllergenes(ActionEvent event) {
    	allergenData.clear();
    	L_Allergenes.setItems(allergenData);
    }

    @FXML
    void addAllergene(ActionEvent event) {
    	boolean exist = false;
    	for(String allergen : allergenData){
    		if(allergen == CB_Allergenes.selectionModelProperty().getValue().getSelectedItem()){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		allergenData.add(CB_Allergenes.selectionModelProperty().getValue().getSelectedItem());    		
    	}
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
    	Dessert dessert = new Dessert(TF_Libelle.getText(), TF_PhotoPath.getText());
    	dessert.setAvailability(CHK_Available.selectedProperty().get());
    	dessert.setNew(CHK_New.selectedProperty().get());
    	if(dessert.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
    	dessert.getAllergens().addAll(allergenData);
    	if(dessertName != ""){
    		Dessert oldDessert = ComponentManagement.getDessert(dessertName);
    		ComponentManagement.getDesserts().remove(oldDessert);
    	}
    	ComponentManagement.getDesserts().add(dessert);
    	ComponentManagement.exportComponent(componentPath);
    	goToAccueil(new ActionEvent());
    }

}
