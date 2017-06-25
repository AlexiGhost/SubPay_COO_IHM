package visual.customer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.CustomerManagement;
import model.product.Menu;
import model.product.composants.Promotion;
import model.product.composants.Recipe;
import model.product.composants.Sauce;

public class RecipeController  implements Initializable{
	
	private static ImageView	myPref = new ImageView();
	
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
    @FXML
    private Text promoBof;
    @FXML
    private Text promoMouais;
    @FXML
    private Text promoCaPasse;
    
    @FXML
    private Text priceBof;

    @FXML
    private Text priceMouais;

    @FXML
    private Text priceCaPasse;
	
	private  static List<Recipe> bofList = new ArrayList<Recipe>();
	private  static List<Recipe> mouaisList = new ArrayList<Recipe>();
	private  static List<Recipe> caPasseList = new ArrayList<Recipe>();
	private  static List<Promotion> promotionList = HomeController.getListPromo();
	
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
		//Incomplet, voir avec Alexi (promotionList.get(i).getCategory() = null !!)
		int i = 0;
		while(i < promotionList.size() - 1 && (!promotionList.get(i).getCategory().equals("Bof"))) {
			i++;
		}
		if(promotionList.get(i).getCategory().equals("Bof")) {
			System.out.println(promotionList.get(i).getCategory());
			promoBof = new Text("-" + String.valueOf(promotionList.get(i).getPercentage() + "% !"));
			promoBof.setFont(new Font("Arial Black", 40));
			promoBof.setFill(Color.DARKGOLDENROD);
		}
		displayRecipe(bofList, bofTile, bofScroll, priceBof);
		Text bof = new Text("BOF");
		bof.setFont(new Font("Arial Black", 100));
		bof.setFill(Color.SEAGREEN);
		bof.setOpacity(0.3);
		bof.setTranslateX(340);
		categorieBof.getChildren().add(bof);
		i = 0;
		while(i < promotionList.size() - 1 && (!promotionList.get(i).getCategory().equals("Mouais"))) {
			i++;
		}
		if(promotionList.get(i).getCategory().equals("Mouais")) {
			System.out.println(promotionList.get(i).getCategory());
			promoBof = new Text("-" + String.valueOf(promotionList.get(i).getPercentage() + "% !"));
			promoBof.setFont(new Font("Arial Black", 40));
			promoBof.setFill(Color.DARKGOLDENROD);
		}
		displayRecipe(mouaisList, mouaisTile, mouaisScroll, priceMouais);
		Text mouais = new Text("MOUAIS");
		mouais.setFont(new Font("Arial Black", 80));
		mouais.setFill(Color.SEAGREEN);
		mouais.setOpacity(0.3);
		mouais.setTranslateX(290);
		categorieMouais.getChildren().add(mouais);
		i = 0;
		while(i < promotionList.size() - 1 && (!promotionList.get(i).getCategory().equals("Ca Passe"))) {
			i++;
		}
		if(promotionList.get(i).getCategory().equals("Ca Passe")) {
			System.out.println(promotionList.get(i).getCategory());
			promoBof = new Text("-" + String.valueOf(promotionList.get(i).getPercentage() + "% !"));
			promoBof.setFont(new Font("Arial Black", 40));
			promoBof.setFill(Color.DARKGOLDENROD);
		}
		displayRecipe(caPasseList, caPasseTile, caPasseScroll, priceCaPasse);
		Text capasse = new Text("CA PASSE");
		capasse.setFont(new Font("Arial Black", 80));
		capasse.setFill(Color.SEAGREEN);
		capasse.setOpacity(0.3);
		capasse.setTranslateX(250);
		categorieCaPasse.getChildren().add(capasse);
	}

	public void displayRecipe(List<Recipe> listRecipe, TilePane tilePane, ScrollPane scrollPane, Text textPrice){
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		Recipe exRecipe = new Recipe();
		for (Recipe recipe : listRecipe) {
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(5.0);
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
			
			//Vérif disponibilité
			if(!recipe.getAvailability()) {
				bordure.setOpacity(0.3);
				bordure.setFill(Color.YELLOWGREEN);
				title.setOpacity(0.3);
				img.setOpacity(0.3);
				Text succes = new Text("Victime de\nson succès");
				succes.setFont(new Font("Arial Black", 14));
				succes.setFill(Color.BLACK);
				succes.setOpacity(1);
				succes.setLayoutX(27);
				succes.setLayoutY(25);
				r.getChildren().add(succes);
			}
			
			//Si c'est une nouveauté
			if(recipe.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				r.getChildren().add(nouveau);
			}
			
			
			//S'il y a une promotion
			int i = 0;
			while(i < promotionList.size() - 1 && (!promotionList.get(i).getRecipe().equals(recipe.getName()))) {
				i++;
			}
			if(promotionList.get(i).getRecipe().equals(recipe.getName())) {
				recipe.setPrice((recipe.getPrice()*(100-promotionList.get(i).getPercentage()))/100);
				Text promo = new Text("-" + String.valueOf(promotionList.get(i).getPercentage() + "% ! "+df.format(recipe.getPrice()) + "€"));
				promo.setFont(new Font("Arial Black", 16));
				promo.setFill(Color.DARKGOLDENROD);
				promo.setLayoutX(10);
				promo.setLayoutY(90);
				r.getChildren().add(promo);
			}else{
				exRecipe = recipe;
			}
			
			//MAJ promoTiled
			r.getChildren().add(title);
			r.getChildren().add(bordure);
			tilePane.getChildren().add(r);
			
			//Si c'est un client authentifié
			ImageView pref;
			if(HelloController.getOrder().getAuthCustomer()) {
				if(SignUpController.getAuthCusto().getFavoriteRecipe() != null) {
					if(SignUpController.getAuthCusto().getFavoriteRecipe().equals(recipe)){
						SignUpController.getAuthCusto().setFavoriteRecipe(recipe);
						pref = new ImageView(new Image(new File("src/visual/images/coeurorange.png").toURI().toString()));
						myPref = pref;
					}
					else
						pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				}
				else
					pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				
				pref.setTranslateX(110);
				pref.setTranslateY(70);
				pref.setFocusTraversable(true);
				pref.setOnMouseEntered(Event -> r.setOnMouseClicked(null));
				pref.setOnMouseExited(Event -> r.setOnMouseClicked(MouseEvent -> goToGarnish(recipe)));
				pref.setOnMouseClicked(Event -> {
					if(myPref != pref) {
						Timeline animation = new Timeline (
								new KeyFrame(Duration.millis(100), new KeyValue(pref.fitHeightProperty(), 20)),
								new KeyFrame(Duration.millis(100), new KeyValue(pref.fitWidthProperty(), 20)),
								new KeyFrame(Duration.millis(200), new KeyValue(pref.fitHeightProperty(), 40)),
								new KeyFrame(Duration.millis(200), new KeyValue(pref.fitWidthProperty(), 40)),
								new KeyFrame(Duration.millis(300), new KeyValue(pref.fitHeightProperty(), 20)),
								new KeyFrame(Duration.millis(300), new KeyValue(pref.fitWidthProperty(), 20)),
								new KeyFrame(Duration.millis(400), new KeyValue(pref.fitWidthProperty(), pref.getFitWidth())),
								new KeyFrame(Duration.millis(400), new KeyValue(pref.fitHeightProperty(), pref.getFitHeight()))
								);
						animation.play();
						pref.setImage(new Image(new File("src/visual/images/coeurorange.png").toURI().toString()));
						myPref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteRecipe(recipe);
						CustomerManagement.exportCustomer("customer.xml");
						myPref = pref;
					}
					else {
						pref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteRecipe(new Recipe());
						CustomerManagement.exportCustomer("customer.xml");
						myPref = new ImageView();
					}
				});
				r.getChildren().add(pref);
			}
		}
		
		textPrice.setText(exRecipe.getPrice()+"€");
		textPrice.setFont(new Font("Arial Black", 24));
		
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
	

