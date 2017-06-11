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
import model.product.Drink;

public class ControllerDrink implements Initializable{

    @FXML private ImageView ComponentImage;
    @FXML private CheckBox CHK_New;
    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private CheckBox CHK_Available;
    @FXML private ListView<String> L_Allergenes;    
    @FXML private ComboBox<String> CB_Allergenes;
    
    private ObservableList<String> allergenData = FXCollections.observableArrayList();
    private ObservableList<String> allergenList = FXCollections.observableArrayList();

    String drinkName = ControllerAccueil.getSelectedItem();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	allergenList.setAll(ComponentManagement.getAllergens());
    	CB_Allergenes.setItems(allergenList);
    	if(drinkName != ""){
    		Drink drink = ComponentManagement.getDrink(drinkName);
			TF_Libelle.setText(drinkName);
			allergenData.setAll(drink.getAllergens());
			L_Allergenes.setItems(allergenData);
			if(drink.getPhoto() != null) TF_PhotoPath.setText(drink.getPhoto());
			CHK_Available.selectedProperty().set(drink.getAvailability());
			CHK_New.selectedProperty().set(drink.getNew());
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
    	Drink drink = new Drink(TF_Libelle.getText(), TF_PhotoPath.getText());
    	drink.setAvailability(CHK_Available.selectedProperty().get());
    	drink.setNew(CHK_New.selectedProperty().get());
    	if(drink.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
    	drink.getAllergens().addAll(allergenData);
    	if(drinkName != ""){
    		Drink oldDrink = ComponentManagement.getDrink(drinkName);
    		ComponentManagement.getDrinks().remove(oldDrink);
    	}
    	ComponentManagement.getDrinks().add(drink);
    	ComponentManagement.exportComponent("component.xml");
    	goToAccueil(new ActionEvent());
    }

}
