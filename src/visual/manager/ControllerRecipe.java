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
import model.product.composants.Recipe;

public class ControllerRecipe implements Initializable{

    @FXML private ImageView ComponentImage;
    @FXML private CheckBox CHK_New;
    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private CheckBox CHK_Available;
    @FXML private ComboBox<String> CMB_Categorie;
    @FXML private ListView<String> L_Allergenes;    
    @FXML private ComboBox<String> CB_Allergenes;
    
    private ObservableList<String> categoryData = FXCollections.observableArrayList("Mouais", "Bof", "Ca passe");
    private ObservableList<String> allergenData = FXCollections.observableArrayList();
    private ObservableList<String> allergenList = FXCollections.observableArrayList();
    
    
    private String recipeName = ControllerAccueil.getSelectedItem();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	CMB_Categorie.setItems(categoryData);
    	CMB_Categorie.setValue("Mouais");
    	allergenList.setAll(ComponentManagement.getAllergens());
    	CB_Allergenes.setItems(allergenList);
    	if(recipeName != ""){
    		Recipe recipe = ComponentManagement.getRecipe(recipeName);
			TF_Libelle.setText(recipeName);
			if(recipe != null) {
				if(recipe.getPhoto() != null) TF_PhotoPath.setText(recipe.getPhoto());
				CHK_Available.selectedProperty().set(recipe.getAvailability());
				CHK_New.selectedProperty().set(recipe.getNew());
				CMB_Categorie.setValue(recipe.getCategory());
				allergenData.setAll(recipe.getAllergens());
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
    	double price = 0;
    	switch (CMB_Categorie.getSelectionModel().getSelectedItem().toString()) {
    	case "Mouais":
    		price = 7;
    		break;
    	case "Bof":
    		price = 6;
    		break;
    	case "Ca passe":
    		price = 5.5;
    		break;
    	default:
    		break;
    	}
    	if(!TF_Libelle.getText().isEmpty()){
    		Recipe recipe = new Recipe(TF_Libelle.getText(), TF_PhotoPath.getText(), price, CMB_Categorie.getSelectionModel().getSelectedItem());
    		if(recipe.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
        	recipe.setNew(CHK_New.selectedProperty().get());
        	recipe.setAvailability(CHK_Available.selectedProperty().get());
        	recipe.setCategory(CMB_Categorie.getSelectionModel().getSelectedItem());
        	recipe.getAllergens().addAll(allergenData);
    		if(recipeName != ""){
        		Recipe oldRecipe = ComponentManagement.getRecipe(recipeName);
        		ComponentManagement.getRecipes().remove(oldRecipe);
        	}
    		ComponentManagement.addRecipe(recipe);
            ComponentManagement.exportComponent("component.xml");
            goToAccueil(new ActionEvent());
    	} else TF_Libelle.setPromptText("Vous n'avez pas donnée de libellé");	
    }
}
