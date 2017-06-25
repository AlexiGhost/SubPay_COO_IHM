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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.CustomerManagement;
import model.product.composants.Bread;
import model.product.composants.Recipe;

public class BreadController implements Initializable {
	private static List<Bread> breadList = new ArrayList<Bread>();
	private	static int			X = 1;
	private	static int			Y = 0;
	private static ImageView	myPref = new ImageView();
	
	@FXML
    private TilePane breadTile;
	@FXML
    private Text title;
	
	public Text getTitle() {
		return title;
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayBreads();
	}
	
	public void displayBreads() {
		for (Bread bread : breadList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			
			//Group
			Group b = new Group();
			b.setTranslateX(20 * X);
			b.setTranslateY(10 + Y);
			b.setOnMouseClicked(MouseEvent -> goToRecipe(bread));
			
			//Titre Pain
			Text title = new Text(bread.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image Pain
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ bread.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ breadTile
			b.getChildren().add(img);
			b.getChildren().add(title);
			b.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!bread.getAvailability()) {
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
				b.getChildren().add(succes);
			}
			
			//Si c'est une nouveauté
			if(bread.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				b.getChildren().add(nouveau);
			}
			
			//Si c'est un client authentifié
			ImageView pref;
			if(HelloController.getOrder().getAuthCustomer()) {
				if(SignUpController.getAuthCusto().getFavoriteBread() != null) {
					if(SignUpController.getAuthCusto().getFavoriteBread().equals(bread)){
						SignUpController.getAuthCusto().setFavoriteBread(bread);
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
				pref.setOnMouseEntered(Event -> b.setOnMouseClicked(null));
				pref.setOnMouseExited(Event -> b.setOnMouseClicked(MouseEvent -> goToRecipe(bread)));
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
						SignUpController.getAuthCusto().setFavoriteBread(bread);
						CustomerManagement.exportCustomer("customer.xml");
						myPref = pref;
					}
					else {
						pref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteBread(new Bread());
						CustomerManagement.exportCustomer("customer.xml");
						myPref = new ImageView();
					}
				});
				b.getChildren().add(pref);
			}
			breadTile.getChildren().add(b);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static List<Bread> getBreadList() {
		return breadList;
	}
	
	public void goToHome(){
		X = 1;
		Y = 0;
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
	
	public void goToRecipe(Bread b) {
		X = 1;
		Y = 0;
		if(b.getAvailability()){
			if(MenuController.getChoice())
				MenuController.getMenu().getProduct().setBread(b);
			else
				MenuController.getProduct().setBread(b);
			Group acteur = new Group();
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getClass().getName().equals("model.product.composants.Recipe")){
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().setRecipe((Recipe) HomeController.getSelectedComponent());
				else
					MenuController.getProduct().setRecipe((Recipe) HomeController.getSelectedComponent());
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("009 Garnitures.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Garnitures");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					acteur.getChildren().add(FXMLLoader.load(getClass().getResource("008 Recettes.fxml")));
					visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void goToSandwichPLate(){
		X = 1;
		Y = 0;
		Group acteur = new Group();
		try {
			acteur.getChildren().add(
			FXMLLoader.load(getClass().getResource("016 Format repas.fxml")) 
			);
			visual.ControllerClient.setScene(acteur, "SUBPAY - Menu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
