package visual.manager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import model.product.ComponentManagement;
import model.product.composants.Promotion;
import model.product.composants.Recipe;

public class ControllerPromotion implements Initializable{

    @FXML private CheckBox CHK_Auth;

    @FXML private TextField TF_Libelle;
    @FXML private TextField TF_PhotoPath;
    @FXML private TextField TF_Reduction;
    
    @FXML private DatePicker DF_EndDate;

    @FXML private ComboBox<String> CB_Categorie;
    private ObservableList<String> categoryList = FXCollections.observableArrayList("", "Mouais", "Bof", "Ca passe");
    
    @FXML private ComboBox<String> CB_Recipe;
    private ObservableList<String> recipeList = FXCollections.observableArrayList();
    
    private String promoName = ControllerAccueil.getSelectedItem();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (Recipe recipe : ComponentManagement.getRecipes()) {
			recipeList.add(recipe.getName());
		}
    	CB_Recipe.setItems(recipeList);
    	CB_Categorie.setItems(categoryList);
    	if(promoName != ""){
    		Promotion promo = ComponentManagement.getPromotion(promoName);
			TF_Libelle.setText(promoName);
			TF_Reduction.setText(Double.toString(promo.getPercentage()));
			CB_Categorie.setValue(promo.getCategory());
			CB_Recipe.setValue(promo.getRecipe());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    String date = promo.getDate().substring(0, 2)+"-"+promo.getDate().substring(2, 4)+"-"+promo.getDate().substring(4);
			LocalDate localDate = LocalDate.parse(date, formatter);
			DF_EndDate.setValue(localDate);
			CHK_Auth.selectedProperty().set(promo.getAuthCustomer());
    	}
	}
    
    @FXML void clearRecipeCombo(){ //executé lors de la selection d'une categorie
    	//TODO trouver la source de l'erreur (1er essai uniquement)
    	if(!CB_Recipe.selectionModelProperty().getValue().getSelectedItem().equals("")) CB_Recipe.setValue("");
    }
    
    @FXML
    void clearCategorieCombo(){ //executé lors de la selection d'une recette
    	if(!CB_Categorie.selectionModelProperty().getValue().getSelectedItem().equals("")) CB_Categorie.setValue("");
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
    //recuperation des données
    	//nom
    	String name = TF_Libelle.getText();
    	if(name == "") {TF_Libelle.setPromptText("Libelle necessaire"); return;}
    	//%age
    	double percentage = Double.valueOf(TF_Reduction.getText());
    	if(percentage > 100 || percentage <= 0) {TF_Reduction.setStyle("-fx-text-inner-color: red;"); return;}
    	//date
    	String date = "";
    	Integer jour = DF_EndDate.getValue().getDayOfMonth();
    	if (jour < 10) date += "0"+jour.toString();
    	else date = jour.toString();
    	Integer mois = DF_EndDate.getValue().getMonth().getValue();
    	if (mois < 10) date += "0"+mois.toString();
    	else date += mois.toString();
    	Integer annee = DF_EndDate.getValue().getYear();
    	date += annee.toString();
    	
    	boolean auth = CHK_Auth.selectedProperty().get();
    	String category = CB_Categorie.selectionModelProperty().getValue().getSelectedItem();
    	String recipe = CB_Recipe.selectionModelProperty().getValue().getSelectedItem();
    	if(category == "" && recipeList.isEmpty()) return;
    	Promotion promo = new Promotion(name, percentage, auth);
    	promo.setRecipe(recipe);
    	promo.setCategory(category);
    	promo.setDate(date);
    //ajout dans le système
    	if(promo.getName() == ""){TF_Libelle.setPromptText("Veuillez donner un nom"); return;}
    	if(promoName != ""){
    		Promotion oldPromotions = ComponentManagement.getPromotion(promoName);
    		ComponentManagement.getPromotions().remove(oldPromotions);
    	}
    	ComponentManagement.getPromotions().add(promo);
    	ComponentManagement.exportComponent("component.xml");
    	goToAccueil(new ActionEvent());
    }
}
