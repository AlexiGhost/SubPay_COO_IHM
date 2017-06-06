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
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addDessert(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addDrink(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addGarnish(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addPromotion(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addRecipe(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void addSauce(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void delBread(ActionEvent event) {

    }

    @FXML
    void delDessert(ActionEvent event) {

    }

    @FXML
    void delDrink(ActionEvent event) {

    }

    @FXML
    void delGarnish(ActionEvent event) {

    }

    @FXML
    void delPromotion(ActionEvent event) {

    }

    @FXML
    void delRecipe(ActionEvent event) {

    }

    @FXML
    void delSauce(ActionEvent event) {

    }

    @FXML
    void editBread(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void editDessert(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void editDrink(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void editGarnish(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }
    
    @FXML
    void editPromotion(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("04_AddEdit_Promo.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void editRecipe(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void editSauce(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("03_AddEdit_GPSBD.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - GPSBD Editor");
    }

    @FXML
    void goToAuthentification(ActionEvent event) throws IOException{
    	Group acteur = new Group();
		acteur.getChildren().add(
		FXMLLoader.load(getClass().getResource("01_Authentification.fxml"))
		);
		visual.Controller.setScene(acteur, "SUBPAY - Authentification");
	}

}
