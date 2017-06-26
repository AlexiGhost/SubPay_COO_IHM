package visual.customer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CustomerManagement;
import model.product.*;
import model.product.composants.Bread;

public class DrinkController implements Initializable {
	private static List<Drink> drinkList = new ArrayList<Drink>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
	private static ImageView	myPref = new ImageView();
	
	@FXML
    private TilePane drinkTile;
	
	@FXML
    private Slider SL_IceCube;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayDrinks();
	}
	
	public void displayDrinks() {
		for (Drink drink : drinkList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			if(HomeController.getNewPromo() && HomeController.getSelectedComponent() != null && HomeController.getSelectedComponent().getClass().getName().equals("model.product.composants.Recipe")){
				if(HomeController.getSelectedComponent().getName().equals(drink.getName()))
					bordure.setStroke(Color.RED);
			}
			
			//Group
			Group d = new Group();
			d.setTranslateX(20 * X);
			d.setTranslateY(10 + Y);
			d.setFocusTraversable(true);
			d.setOnMouseClicked(MouseEvent -> goToHomeWithOrder(drink));
			
			//Titre garniture
			Text title = new Text(drink.getName());
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ drink.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);
			
			//MAJ garnishTile
			d.getChildren().add(img);
			d.getChildren().add(title);
			d.getChildren().add(bordure);
			
			//Vérif disponibilité
			if(!drink.getAvailability()) {
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
				d.getChildren().add(succes);
			}
			//Si c'est une nouveauté
			if(drink.getNew()) {
				Text nouveau = new Text("Nouveau !");
				nouveau.setFont(new Font("Arial Black", 11));
				nouveau.setFill(Color.DARKRED);
				nouveau.setLayoutX(90);
				nouveau.setLayoutY(17);
				nouveau.setRotate(45);
				d.getChildren().add(nouveau);
			}
			
			//Si c'est un client authentifié
			ImageView pref;
			if(HelloController.getOrder().getAuthCustomer()) {
				if(SignUpController.getAuthCusto().getFavoriteDrink() != null) {
					if(SignUpController.getAuthCusto().getFavoriteDrink().equals(drink)){
						SignUpController.getAuthCusto().setFavoriteDrink(drink);
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
				pref.setOnMouseEntered(Event -> d.setOnMouseClicked(null));
				pref.setOnMouseExited(Event -> d.setOnMouseClicked(MouseEvent -> goToHomeWithOrder(drink)));
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
						SignUpController.getAuthCusto().setFavoriteDrink(drink);
						CustomerManagement.exportCustomer("customer.xml");
						myPref = pref;
					}
					else {
						pref.setImage(new Image(new File("src/visual/images/coeurgris.png").toURI().toString()));
						SignUpController.getAuthCusto().setFavoriteDrink(new Drink());
						CustomerManagement.exportCustomer("customer.xml");
						myPref = new ImageView();
					}
				});
				d.getChildren().add(pref);
			}
			drinkTile.getChildren().add(d);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
	}
	
	public static List<Drink> getDrinkList() {
		return drinkList;
	}
	
	
	public void goToSauces() {
		if(MenuController.getChoice())
			MenuController.getMenu().getProduct().getSauces().clear();
		else
			MenuController.getProduct().getSauces().clear();	
		Group acteur = new Group();
		try {
			acteur.getChildren().add(FXMLLoader.load(getClass().getResource("010 Sauces.fxml")));
			visual.ControllerClient.setScene(acteur, "SUBPAY - Sauces"); 
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public void goToHomeWithOrder(Drink d){
		Stage glaçons = new Stage();
		glaçons.setWidth(350);
		glaçons.setHeight(300);
		Group affichage = new Group();
		
		Text question = new Text("Choisissez la quantité de glaçons");
		question.setX(20);
		question.setY(100);
		question.setFont(new Font("Arial Black", 16));
		
		Slider jauge = new Slider();
		jauge.setTranslateX(110);
		jauge.setTranslateY(150);
		jauge.setMax(2);
		jauge.setMin(0);
		jauge.setMajorTickUnit(2);
		jauge.setMinorTickCount(1);
		jauge.setBlockIncrement(1);
		jauge.setSnapToTicks(true);
		
		Button bouton = new Button();
		bouton.setText("Valider");
		bouton.setFont(new Font("Arial Black", 16));
		bouton.setTranslateX(120);
		bouton.setTranslateY(200);
		bouton.setOnMouseClicked(Event -> {glaçons.close(); finChoixBoisson(d, jauge);});
		
		affichage.getChildren().add(question);
		affichage.getChildren().add(jauge);
		affichage.getChildren().add(bouton);
		Scene gla = new Scene(affichage);
		glaçons.setTitle("SUBPAY - Boisson");
		glaçons.setScene(gla);
		glaçons.show();
	}
	
	public void finChoixBoisson(Drink d, Slider s) {
		//On ajoute la boisson au menu
		MenuController.getMenu().setDrink(d);
		//On ajoute le nb de glacons au menu
		MenuController.getMenu().setIceCubeNb(Math.toIntExact(Math.round(s.getValue())));
		//On ajoute le menu a la commande
		HelloController.getOrder().addMenu(MenuController.getMenu());
		javax.swing.JOptionPane.showMessageDialog(null, "Commande Validée"); 
		goToHome();
	}
	

}
