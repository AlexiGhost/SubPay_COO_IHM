

import model.product.ComponentManagement;
import visual.*;

public class Main {

	public static void main(String[] args) {
		//Management.importComponent("component.xml");
		ControllerClient.initialize(args);
		//ControllerManager.initialize(args);
		testExport();
	}
	//Ces actions sont effectués par le manager (indirectement via bouton)
	public static void testExport(){ 
		ComponentManagement.addBread("Avoine", "C:\\...");
		ComponentManagement.addBread("Mie", "C:\\...");
		ComponentManagement.getBread("Mie").addAllergen("Levure");
		ComponentManagement.addBread("Olive", "C:\\...");
		ComponentManagement.addSauce("Andalouse", "C:\\...");
		ComponentManagement.addPromotion("mercredi", 5.5, false);
		ComponentManagement.addPromotion("auth", 1, true);
		ComponentManagement.addRecipe("poulet", "mouais", 214, "C:\\...");
		ComponentManagement.getPromotion("mercredi").addRecipe("poulet");
		ComponentManagement.getPromotion("mercredi").addRecipe("kebab");
		ComponentManagement.addGarnish("tomate", "titi");
		ComponentManagement.addGarnish("basilic", "titi");
		ComponentManagement.addGarnish("cheddar", "titi");
		ComponentManagement.addDrink("cola cola", "toto");
		ComponentManagement.addDessert("brownie", "truc marron");
		ComponentManagement.exportComponent("component.xml");
	}
}
