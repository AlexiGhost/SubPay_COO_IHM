package visual.customer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

public class ControllerAllergen {
	private static String previousUI;
	private static String previousTitle;
	
	public static void setPreviousUI(String previousUI) {
		ControllerAllergen.previousUI = previousUI;
	}
	public static void setPreviousTitle(String previousTitle) {
		ControllerAllergen.previousTitle = previousTitle;
	}
	
	public void goToPreviousUI() throws IOException {
			Group acteur = new Group();
			acteur.getChildren().add( 
			FXMLLoader.load(getClass().getResource(previousUI))
			);
			visual.ControllerClient.setScene(acteur, previousTitle);
	}
}
