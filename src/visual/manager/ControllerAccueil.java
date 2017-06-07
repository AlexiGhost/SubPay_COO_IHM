package visual.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import model.product.*;
import model.product.composants.*;

public class ControllerAccueil implements Initializable{
    
	//ATTRIBUTES
	
    @FXML
    private ListView<String> L_Recipe;
    @FXML
    private ListView<String> L_Bread;
    @FXML
    private ListView<String> L_Garnish;
    @FXML
    private ListView<String> L_Sauce;
    @FXML
    private ListView<String> L_Drink;
    @FXML
    private ListView<String> L_Dessert;

    @FXML
    private TableView<Promotion> T_Promotion;
    @FXML
    private TableColumn<?, ?> TC_PromotionName;
    @FXML
    private TableColumn<?, ?> TC_PromotionDate;
    @FXML
    private TableColumn<?, ?> TC_PromotionCategory;
    @FXML
    private TableColumn<?, ?> TC_PromotionRecipe;
    
    private ObservableList<String> recipeData = FXCollections.observableArrayList();
    private ObservableList<String> breadData = FXCollections.observableArrayList();
    private ObservableList<String> garnishData = FXCollections.observableArrayList();
    private ObservableList<String> sauceData = FXCollections.observableArrayList();
    private ObservableList<String> drinkData = FXCollections.observableArrayList();
    private ObservableList<String> dessertData = FXCollections.observableArrayList();
    private ObservableList<Promotion> promoData = FXCollections.observableArrayList();
    //METHODS
   
    @Override
    public void initialize(URL location, ResourceBundle rb){
    	for (Recipe recipe : ComponentManagement.getRecipes()) {
			recipeData.add(recipe.getName());
		}
    	L_Recipe.setItems(recipeData);
    	
    	for (Bread bread : ComponentManagement.getBreads()) {
			breadData.add(bread.getName());
		}
    	L_Bread.setItems(breadData);
    	
    	for (Garnish garnish : ComponentManagement.getGarnishs()) {
			garnishData.add(garnish.getName());
		}
    	L_Garnish.setItems(garnishData);
    	
    	for (Sauce sauce : ComponentManagement.getSauces()) {
			sauceData.add(sauce.getName());
		}
    	L_Sauce.setItems(sauceData);
    	
    	for (Drink Drink : ComponentManagement.getDrinks()) {
			drinkData.add(Drink.getName());
		}
    	L_Drink.setItems(drinkData);
    	
    	for (Dessert dessert : ComponentManagement.getDesserts()) {
			dessertData.add(dessert.getName());
		}
    	L_Dessert.setItems(dessertData);
    	
    	//TODO Table promotion
    }
    
    @FXML
    void addBread(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_Bread.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Bread Editor");
    }

    @FXML
    void addDessert(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("08_Dessert.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Dessert Editor");
    }

    @FXML
    void addDrink(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("07_Drink.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Drink Editor");
    }

    @FXML
    void addGarnish(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("05_Garnish.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Garnish Editor");
    }

    @FXML
    void addPromotion(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("09_Promotion.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Promotion Editor");
    }

    @FXML
    void addRecipe(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_Recipe.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Recipe Editor");
    }

    @FXML
    void addSauce(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("06_Sauce.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Sauce Editor");
    }

    @FXML
    void delBread(ActionEvent event) {
    	String name = L_Bread.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){
	    	ComponentManagement.delBread(name);
	    	breadData.remove(name);
	    	L_Bread.setItems(breadData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delDessert(ActionEvent event) {
    	String name = L_Dessert.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){
	    	ComponentManagement.delDessert(name);
	    	dessertData.remove(name);
	    	L_Dessert.setItems(dessertData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delDrink(ActionEvent event) {
    	String name = L_Drink.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){    	
	    	ComponentManagement.delDrink(name);
	    	drinkData.remove(name);
	    	L_Drink.setItems(drinkData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delGarnish(ActionEvent event) {
    	String name = L_Garnish.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){
    		ComponentManagement.delGarnish(name);
    		garnishData.remove(name);
        	L_Garnish.setItems(garnishData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delPromotion(ActionEvent event) {
    	//TODO del promo
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delRecipe(ActionEvent event) {
    	String name = L_Recipe.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){
	    	ComponentManagement.delRecipe(name);
	    	recipeData.remove(name);
	    	L_Recipe.setItems(recipeData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void delSauce(ActionEvent event) {
    	String name = L_Sauce.getSelectionModel().getSelectedItem().toString();
    	int dialogResult = JOptionPane.showConfirmDialog (null, "Vous êtes sur le point de supprimer '"+name+"'","Warning", JOptionPane.YES_NO_OPTION);
    	if(dialogResult == JOptionPane.YES_OPTION){
    		ComponentManagement.delSauce(name);
        	sauceData.remove(name);
        	L_Sauce.setItems(sauceData);
    	}
    	ComponentManagement.exportComponent("component.xml");
    }

    @FXML
    void editBread(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_Bread.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Bread Editor");
    }

    @FXML
    void editDessert(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("08_Dessert.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Dessert Editor");
    }

    @FXML
    void editDrink(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("07_Drink.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Drink Editor");
    }

    @FXML
    void editGarnish(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("05_Garnish.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Garnish Editor");
    }
    
    @FXML
    void editPromotion(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("09_Promotion.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Promotion Editor");
    }

    @FXML
    void editRecipe(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_Recipe.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Recipe Editor");
    }

    @FXML
    void editSauce(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("06_Sauce.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Sauce Editor");
    }

    @FXML
    void goToAuthentification(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml"))
		);
		visual.ControllerManager.setScene(acteur, "SUBPAY - Authentification");
	}

}
