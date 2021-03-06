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
import model.product.composants.Garnish;

public class GarnishController implements Initializable {
	private static List<Garnish> garnishList = new ArrayList<Garnish>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
    @FXML
    private TilePane garnishTile;
	
	
	public static List<Garnish> getGarnishList() {
		return garnishList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayGarnish();
	}
	
	public void displayGarnish() {
		for (Garnish garnish : garnishList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getName().equals(garnish.getName()))
				bordure.setStroke(Color.RED);
			
			//Group
			Group gar = new Group();
			gar.setTranslateX(20 * X);
			gar.setTranslateY(10 + Y);
			gar.setFocusTraversable(true);
			gar.setOnMouseClicked(MouseEvent -> myGarnishes(bordure, garnish));
			
			//Titre garniture
			Text title = new Text(garnish.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ garnish.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			gar.getChildren().add(img);
			gar.getChildren().add(title);
			gar.getChildren().add(bordure);
			
			//V�rif disponibilit�
			if(!garnish.getAvailability()) {
				bordure.setOpacity(0.3);
				bordure.setFill(Color.YELLOWGREEN);
				title.setOpacity(0.3);
				img.setOpacity(0.3);
				Text succes = new Text("Victime de\nson succ�s");
				succes.setFont(new Font("Arial Black", 14));
				succes.setFill(Color.BLACK);
				succes.setOpacity(1);
				succes.setLayoutX(27);
				succes.setLayoutY(25);
				gar.getChildren().add(succes);
			}
			
			//V�rif disponibilit�
			if(!garnish.getAvailability()) {
				bordure.setOpacity(0.3);
				bordure.setFill(Color.YELLOWGREEN);
				title.setOpacity(0.3);
				img.setOpacity(0.3);
				Text succes = new Text("Victime de\nson succ�s");
				succes.setFont(new Font("Arial Black", 14));
				succes.setFill(Color.BLACK);
				succes.setOpacity(1);
				succes.setLayoutX(27);
				succes.setLayoutY(25);
				gar.getChildren().add(succes);
			}
			
			//Si c'est une nouveaut�
			if(garnish.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				gar.getChildren().add(nouveau);
			}
			
			//Si c'est un client authentifi�
			ImageView pref;
			if(HelloController.getOrder().getAuthCustomer()) {
				if(SignUpController.getAuthCusto().getFavoriteGarnish().size() != 0) {
					int i = 0;
					while(!SignUpController.getAuthCusto().getFavoriteGarnish().get(i).getName().equals(garnish.getName()) && i < SignUpController.getAuthCusto().getFavoriteGarnish().size() - 1)
						i++;
					if(SignUpController.getAuthCusto().getFavoriteGarnish().get(i).getName().equals(garnish.getName()))
						pref = new ImageView(new Image(new File("src/visual/images/coeurorange.png").toURI().toString()));
					else
						pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				}
				else {
					pref = new ImageView(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
				}
				
				pref.setTranslateX(110);
				pref.setTranslateY(70);
				pref.setFocusTraversable(true);
				pref.setOnMouseEntered(Event -> gar.setOnMouseClicked(null));
				pref.setOnMouseExited(Event -> gar.setOnMouseClicked(MouseEvent -> myGarnishes(bordure, garnish)));
				pref.setOnMouseClicked(Event -> {
					if(SignUpController.getAuthCusto().getFavoriteGarnish().indexOf(garnish) == -1) {
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
						SignUpController.getAuthCusto().addGarnish(garnish);
						CustomerManagement.exportCustomer("customer.xml");
					}
					else {
						pref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().delGarnish(garnish);
						CustomerManagement.exportCustomer("customer.xml");
					}
				});
				gar.getChildren().add(pref);
			}
			garnishTile.getChildren().add(gar);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static void myGarnishes(Rectangle rec, Garnish G) {
		if(G.getAvailability()) {
			if(rec.getStroke().equals(Color.LIGHTGREEN)) {
				rec.setStroke(Color.RED);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().addGarnish(G);
				else
					MenuController.getProduct().addGarnish(G);
			}
			else if(rec.getStroke().equals(Color.RED)) {
				rec.setStroke(Color.LIGHTGREEN);
				if(MenuController.getChoice())
					MenuController.getMenu().getProduct().delGarnish(G);
				else
					MenuController.getProduct().delGarnish(G);
			}
		}
	}	
	
	public void goToSauces(){
		X = 1;
		Y = 0;
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("010 Sauces.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void goToRecettes(){
		X = 1;
		Y = 0;
		if(MenuController.getChoice())
			MenuController.getMenu().getProduct().getGarnishs().clear();
		else
			MenuController.getProduct().getGarnishs().clear();														
		Group acteur = new Group();
		if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getClass().getName().equals("model.product.composants.Recipe")){
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("008 Recettes.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("008 Recettes.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Recettes");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void goToHome(){
		Group acteur = new Group();
		if(HelloController.getOrder().getAuthCustomer()){ //Si le client est authentifi�
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004.1 Accueil (authentifier).fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil Authentifi�");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{ //S'il n'y a pas de client authentifi�
			try {
				acteur.getChildren().add(FXMLLoader.load(getClass().getResource("004 Accueil.fxml")));
				visual.ControllerClient.setScene(acteur, "SUBPAY - Accueil");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
