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
    
    @FXML private ListView<String> L_Recipe;
    private ObservableList<String> recipeData = FXCollections.observableArrayList();
    
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
			CB_Categorie.setValue(promo.getCategory());
			recipeData.addAll(promo.getRecipes());
			L_Recipe.setItems(recipeData);
			recipeData.clear();
			//TODO "importer" date
			CHK_Auth.selectedProperty().set(promo.getAuthCustomer());
    	}
	}
    
    @FXML void addRecipe(ActionEvent event) {
    	String recipe = CB_Recipe.selectionModelProperty().getValue().getSelectedItem();
    	boolean exist = false;
    	for (String r : recipeData) {
			if (r.equals(recipe)) exist = true;
		}
    	if(!exist || recipeData.isEmpty()) recipeData.add(recipe);
    	if(recipeData != null) L_Recipe.setItems(recipeData);
    }

    @FXML
    void deleteRecipe(ActionEvent event) {
    	String recipe = CB_Recipe.selectionModelProperty().getValue().getSelectedItem();
    	recipeData.remove(recipe);
    	L_Recipe.setItems(recipeData);
    }
    
    @FXML
    void clearRecipe(ActionEvent event){
    	recipeData.clear();
    	L_Recipe.setItems(recipeData);
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
    	if(category == "" && recipeList.isEmpty()) return;
    	Promotion promo = new Promotion(name, percentage, auth);
    	promo.setRecipes(recipeList);
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
