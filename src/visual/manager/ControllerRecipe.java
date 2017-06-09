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
    private ComboBox<String> CMB_Categorie;
    
    private ObservableList<String> categoryData = FXCollections.observableArrayList("Mouais", "Bof", "Ca passe");
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	for (String string : categoryData) {
			System.out.println(string);
		}
    	CMB_Categorie.setItems(categoryData);
    	CMB_Categorie.setValue("Mouais");
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
    		Recipe recipe = new Recipe(TF_Libelle.getText(), TF_PhotoPath.getText(), price, CMB_Categorie.getSelectionModel().getSelectedItem().toString());
            ComponentManagement.addRecipe(recipe.getName(), recipe.getCategory(), recipe.getPrice(), recipe.getPhoto());
            ComponentManagement.exportComponent("component.xml");
            goToAccueil(new ActionEvent());
    	} else TF_Libelle.setPromptText("Vous n'avez pas donnée de libellé");	
    }
}
