package visual.customer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.product.composants.Recipe;

public class RecipeController  implements Initializable{
	
    @FXML
    private TilePane bofTile;
    @FXML
    private ScrollPane bofScroll;
    @FXML
    private TilePane categorieBof;
    
    @FXML
    private TilePane mouaisTile;
    @FXML
    private ScrollPane mouaisScroll;
    @FXML
    private TilePane categorieMouais;

    @FXML
    private TilePane caPasseTile;
    @FXML
    private ScrollPane caPasseScroll;
    @FXML
    private TilePane categorieCaPasse;
    
	
	private  static List<Recipe> bofList = new ArrayList<Recipe>();
	private  static List<Recipe> mouaisList = new ArrayList<Recipe>();
	private  static List<Recipe> caPasseList = new ArrayList<Recipe>();
	
	public static List<Recipe> getBofList() {
		return bofList;
	}
	public static List<Recipe> getMouaisList() {
		return mouaisList;
	}
	public static List<Recipe> getCaPasseList() {
		return caPasseList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayRecipe(bofList, bofTile, bofScroll);
		Text bof = new Text("BOF");
		bof.setFont(new Font("Arial Black", 100));
		bof.setFill(Color.SEAGREEN);
		bof.setOpacity(0.3);
		bof.setTranslateX(340);
		categorieBof.getChildren().add(bof);
		displayRecipe(mouaisList, mouaisTile, mouaisScroll);
		Text mouais = new Text("MOUAIS");
		mouais.setFont(new Font("Arial Black", 80));
		mouais.setFill(Color.SEAGREEN);
		mouais.setOpacity(0.3);
		mouais.setTranslateX(290);
		categorieMouais.getChildren().add(mouais);
		displayRecipe(caPasseList, caPasseTile, caPasseScroll);
		Text capasse = new Text("CA PASSE");
		capasse.setFont(new Font("Arial Black", 80));
		capasse.setFill(Color.SEAGREEN);
		capasse.setOpacity(0.3);
		capasse.setTranslateX(250);
		categorieCaPasse.getChildren().add(capasse);
	}
	
	public void displayRecipe(List<Recipe> listRecipe, TilePane tilePane, ScrollPane scrollPane){
		for (Recipe recipe : listRecipe) {
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			//Affiche un contour rouge pour les catégories sélectionnées  dans nouveautés ou promotions
			if(HomeController.getNewPromo()){
				if(HomeController.getSelectedCategorie() != null && recipe.getCategory().equals(HomeController.getSelectedCategorie()))
						scrollPane.setStyle("-fx-border-color:red;");
			}
			
			//Group
			Group r = new Group();
			r.setFocusTraversable(true);
			r.setOnMouseClicked(MouseEvent -> goToGarnish(recipe));
			
			//Titre nouveauté
			Text title = new Text(recipe.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image nouveauté
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+recipe.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			r.getChildren().add(img);
			
			//MAJ promoTiled
			r.getChildren().add(title);
			r.getChildren().add(bordure);
			tilePane.getChildren().add(r);
		}
		
	}	

	public void goToBread(){ 
		Group acteur = new Group();
		if(MenuController.getChoice()){
			if(MenuController.getMenu().getProduct().getPlate()){
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("016 Format repas.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Format du repas");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("007 Pain.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Pain");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}else{
			if(MenuController.getProduct().getPlate()){
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("016 Format repas.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Format du repas");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("007 Pain.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Pain");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void goToGarnish(Recipe r){ 
		if(r.getAvailability()){
			if(MenuController.getChoice())
				MenuController.getMenu().getProduct().setRecipe(r);
			else
				MenuController.getProduct().setRecipe(r);
			Group acteur = new Group();
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("009 Garnitures.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures"); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void goToHome(){
		Group acteur = new Group();
		if(HelloController.getOrder().getAuthCustomer()){ //Si le client est authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Authentifié");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{ //S'il n'y a pas de client authentifié
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void bofToBack() {
		categorieMouais.toFront();
		categorieCaPasse.toFront();
		
		categorieBof.toBack();
		bofTile.setOnMouseExited(Event -> {
			categorieBof.toFront();
		});
	}
	
	public void mouaisToBack() {
		categorieBof.toFront();
		categorieCaPasse.toFront();
		
		categorieMouais.toBack();
		mouaisTile.setOnMouseExited(Event -> {
			categorieMouais.toFront();
		});
	}
	
	public void caPasseToBack() {
		categorieMouais.toFront();
		categorieBof.toFront();
		
		categorieCaPasse.toBack();
		caPasseTile.setOnMouseExited(Event -> {
			categorieCaPasse.toFront();
		});
	}
	
}
	

