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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.CustomerManagement;
import model.product.composants.Sauce;

public class AllergenController implements Initializable {
	private static String previousUI;
	private static String previousTitle;
	private static List<String> allergenList = new ArrayList<String>();
	private	static int			 X = 1;
	private	static int			 Y = 0;
	
	@FXML
	private TilePane allergenTilePane;

	
	public static List<String> getAllergenList() {
		return allergenList;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		X=1;
		Y=0;
		displayAllergen();
	}
	
	private void displayAllergen() {
for (String allergen : allergenList) {
			
			//Bordures
			Rectangle bordure = new Rectangle(0, -15, 150, 120);
			bordure.setFill(Color.TRANSPARENT);
			bordure.setStroke(Color.LIGHTGREEN);
			bordure.setStrokeWidth(4.0);
			//Afficher en rouge les allergens enregistres par le client authentifie
			if(HelloController.getOrder().getAuthCustomer()){
				for(String allergenAuthCusto : SignUpController.getAuthCusto().getAllergens()){
					HelloController.getAllergenList().add(allergenAuthCusto);
				}
			}
			for (String allergenSaved : HelloController.getAllergenList()) {
				if(allergenSaved.equals(allergen))
					bordure.setStroke(Color.RED);
			}
			
			//Group
			Group r = new Group();
			r.setTranslateX(20 * X);
			r.setTranslateY(10 + Y);
			r.setFocusTraversable(true);
			r.setOnMouseClicked(MouseEvent -> myAllergen(bordure, allergen));
			
			//Titre garniture
			Text title = new Text(allergen);
			title.setFont(new Font("Arial Black", 13));
			title.setLayoutX(3);
			
			//Image garniture
			//if(allergen.equals(arg0))
			/*ImageView img = new ImageView(new Image("file:src\\visual\\images\\"+ sauce.getPhoto()));
			img.setFitHeight(107);
			img.setFitWidth(150);*/
			
			//MAJ garnishTile
			//r.getChildren().add(img);
			r.getChildren().add(title);
			r.getChildren().add(bordure);
			
			allergenTilePane.getChildren().add(r);
			
			if(X == 5) {
				X = 1;
				Y += 20;
			}
			else
				X++;
		}
		for(int i = 0; i<5; i++){
			Group r = new Group();
			allergenTilePane.getChildren().add(r);
		}
	}

	private void myAllergen(Rectangle rec, String allergen) {
		if(rec.getStroke().equals(Color.LIGHTGREEN)) {
			rec.setStroke(Color.RED);
			HelloController.getAllergenList().add(allergen);
			if(HelloController.getOrder().getAuthCustomer()){
				SignUpController.getAuthCusto().getAllergens().add(allergen);
				CustomerManagement.exportCustomer("customer.xml");
			}
		}
		else if(rec.getStroke().equals(Color.RED)) {
			rec.setStroke(Color.LIGHTGREEN);
			HelloController.getAllergenList().remove(allergen);
			if(HelloController.getOrder().getAuthCustomer()){
				SignUpController.getAuthCusto().getAllergens().remove(allergen);
				CustomerManagement.exportCustomer("customer.xml");
			}
		}
	}

	public static void setPreviousUI(String previousUI) {
		AllergenController.previousUI = previousUI;
	}
	public static void setPreviousTitle(String previousTitle) {
		AllergenController.previousTitle = previousTitle;
	}
	
	public void goToPreviousUI() throws IOException {
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource(previousUI))
			);
			visual.ControllerClient.setScene(acteur, previousTitle);
	}
}
