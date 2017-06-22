package visual.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class AllergenController {
	private static String previousUI;
	private static String previousTitle;
	private static List<String> allergenList = new ArrayList<String>();
	
	public static List<String> getAllergenList() {
		return allergenList;
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
