package coo.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import coo.product.composants.Bread;
import coo.product.composants.Garnish;
import coo.product.composants.Promotion;
import coo.product.composants.Recipe;
import coo.product.composants.Sauce;
/**Contain the list of components and methods for ADD/DEL/EDIT/IMPORT/EXPORT them*/
public class Management {
	private static List<Bread> breads = new ArrayList<Bread>();
	private static List<Garnish> garnishs = new ArrayList<Garnish>();
	private static List<Sauce> sauces = new ArrayList<Sauce>();
	private static List<Recipe> recipes = new ArrayList<Recipe>();
	private static List<Promotion> promotions = new ArrayList<Promotion>();
	private static List<Drink> drinks = new ArrayList<Drink>();
	private static List<Dessert> desserts = new ArrayList<Dessert>();

// ---------- Lists Management (ADD/DEL/EDIT) ----------
	//BreadList management
	/**Add a bread to the component list*/
	public static void addBread(String name, String photo){
		Bread bread = new Bread(name, photo);
		breads.add(bread);
	}
	/**Delete a specific bread to the component list*/
	public static void delBread(String name){
		for (Bread bread : breads) {
			if(bread.getName() == name){
				int index = breads.indexOf(bread);
				breads.remove(index);
			}
		}
	}
	//GarnishList management
	/**Add a garnish to the component list*/
	public static void addGarnish(String name, String photo){
		Garnish garnish = new Garnish(name, photo);
		garnishs.add(garnish);
	}
	/**Delete a specific garnish to the component list*/
	public static void delGarnish(String name){
		for (Garnish garnish : garnishs) {
			if(garnish.getName() == name){
				int index = garnishs.indexOf(garnish);
				garnishs.remove(index);
			}
		}
	}
	//SauceList management
	/**Add a sauce to the component list*/
	public static void addSauce(String name, String photo){
		Sauce sauce = new Sauce(name, photo);
		sauces.add(sauce);
	}
	/**Delete a specific sauce to the component list*/
	public static void delSauce(String name){
		for (Sauce sauce : sauces) {
			if(sauce.getName() == name){
				int index = sauces.indexOf(sauce);
				sauces.remove(index);
			}
		}
	}
	//RecipeList management
	/**Add a recipe to the component list*/
	public static void addRecipe(String name, String category, double price, String photo){
		Recipe recipe = new Recipe(name, photo, price, category);
		recipes.add(recipe);
	}
	/**Delete a specific recipe to the component list*/
	public static void delRecipe(String name){
		for (Recipe recipe : recipes) {
			if(recipe.getName() == name){
				int index = recipes.indexOf(recipe);
				recipes.remove(index);
			}
		}
	}
	//PromotionList management
	/**Add a promotion to the component list*/
	public static void addPromotion(String name, double percentage, boolean auth){
		Promotion promo = new Promotion(name, percentage, auth);
		promotions.add(promo);
	}
	/**Delete a specific promotion to the component list*/
	public static void delPromotion(String name){
		for (Promotion promo : promotions) {
			if(promo.getName() == name){
				int index = promotions.indexOf(promo);
				promotions.remove(index);
			}
		}
	}
	//DrinkList management
	/**Add a drink to the component list*/
	public static void addDrink(String name, String photo){
		Drink drink = new Drink(name, photo);
		drinks.add(drink);
	}
	/**Delete a specific drink to the component list*/
	public static void delDrink(String name){
		for (Drink drink : drinks) {
			if(drink.getName() == name){
				int index = drinks.indexOf(drink);
				drinks.remove(index);
			}
		}
	}
	//DessertList management
	/**Add a dessert to the component list*/
	public static void addDessert(String name, String photo){
		Dessert dessert = new Dessert(name, photo);
		desserts.add(dessert);
	}
	/**Delete a specific dessert to the component list*/
	public static void delDessert(String name){
		for (Dessert dessert : desserts) {
			if(dessert.getName() == name){
				int index = desserts.indexOf(dessert);
				desserts.remove(index);
			}
		}
	}
	
// ---------- GETTERS / SETTERS ----------
	//bread
	public static List<Bread> getBreads(){
		return breads;
	}
	public static Bread getBread(String name){
		for(Bread bread : breads){
			if(bread.getName() == name){
				return bread;
			}
		}
		return null;
	}
	//sauce
	public static List<Sauce> getSauces(){
		return sauces;
	}
	public static Sauce getSauce(String name){
		for(Sauce sauce : sauces){
			if(sauce.getName() == name){
				return sauce;
			}
		}
		return null;
	}
	//garnish
	public static List<Garnish> getGarnishs() {
		return garnishs;
	}
	public static Garnish getGarnish(String name){
		for(Garnish garnish : garnishs){
			if(garnish.getName() == name){
				return garnish;
			}
		}
		return null;
	}
	//recipe
	public static List<Recipe> getRecipes() {
		return recipes;
	}
	public static Recipe getRecipe(String name){
		for(Recipe recipe : recipes){
			if(recipe.getName() == name){
				return recipe;
			}
		}
		return null;
	}
	//promotion
	public static List<Promotion> getPromotions() {
		return promotions;
	}
	public static Promotion getPromotion(String name){
		for(Promotion promotion : promotions){
			if(promotion.getName() == name){
				return promotion;
			}
		}
		return null;
	}
	//drink
	public static List<Drink> getDrinks() {
		return drinks;
	}
	public static Drink getDrink(String name){
		for(Drink drink : drinks){
			if(drink.getName() == name){
				return drink;
			}
		}
		return null;
	}
	//dessert
	public static List<Dessert> getDesserts() {
		return desserts;
	}
	public static Dessert getDessert(String name){
		for(Dessert dessert : desserts){
			if(dessert.getName() == name){
				return dessert;
			}
		}
		return null;
	}

// ---------- IMPORT / EXPORT ----------
	
	/**Export components to a XML file*/
	public static void export(String xmlFile) {
		Element racine = new Element("Composants");
		Document composants = new Document(racine);
		String name;
		String photo;
		Boolean available;
		Double price;
		String category;
		Double percentage;
		Boolean authCustomer;
		List<String> list = null;
		try {
			//BREAD
			for(Bread bread : breads){
				//recuperation infos
				name = bread.getName();
				photo = bread.getPhoto();
				list = bread.getAllergens();
				available = bread.getAvailability();
				//creation d'une branche
				Element Ebread = new Element("bread");
				racine.addContent(Ebread);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Bread");
				Ebread.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Ebread.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Ebread.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Ebread.addContent(Eallergens);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Ebread.addContent(Eavailable);
			}
			//GARNISH
			for(Garnish garnish : garnishs){
				//recuperation infos
				name = garnish.getName();
				photo = garnish.getPhoto();
				list = garnish.getAllergens();
				available = garnish.getAvailability();
				//creation d'une branche
				Element Egarnish = new Element("garnish");
				racine.addContent(Egarnish);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Garnish");
				Egarnish.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Egarnish.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Egarnish.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Egarnish.addContent(Eallergens);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Egarnish.addContent(Eavailable);
			}
			//SAUCE
			for(Sauce sauce : sauces){
				//recuperation infos
				name = sauce.getName();
				photo = sauce.getPhoto();
				list = sauce.getAllergens();
				available = sauce.getAvailability();
				//creation d'une branche
				Element Esauce = new Element("sauce");
				racine.addContent(Esauce);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Sauce");
				Esauce.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Esauce.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Esauce.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Esauce.addContent(Eallergens);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Esauce.addContent(Eavailable);
			}
			//RECIPE
			for(Recipe recipe : recipes){
				//recuperation infos
				name = recipe.getName();
				photo = recipe.getPhoto();
				list = recipe.getAllergens();
				available = recipe.getAvailability();
				price = recipe.getPrice();
				category = recipe.getCategory();
				//creation d'une branche
				Element Erecipe = new Element("recipe");
				racine.addContent(Erecipe);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Recipe");
				Erecipe.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Erecipe.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Erecipe.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Erecipe.addContent(Eallergens);
				//ajout prix
				Element Eprice = new Element("price");
				Eprice.setText(price.toString());
				Erecipe.addContent(Eprice);
				//ajout categorie
				Element Ecategory = new Element("category");
				Ecategory.setText(category);
				Erecipe.addContent(Ecategory);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Erecipe.addContent(Eavailable);
			}
			//DRINK
			for(Drink drink : drinks){
				//recuperation infos
				name = drink.getName();
				photo = drink.getPhoto();
				list = drink.getAllergens();
				available = drink.getAvailability();
				//creation d'une branche
				Element Edrink = new Element("drink");
				racine.addContent(Edrink);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Drink");
				Edrink.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Edrink.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Edrink.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Edrink.addContent(Eallergens);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Edrink.addContent(Eavailable);
			}
			//DESSERT
			for(Dessert dessert : desserts){
				//recuperation infos
				name = dessert.getName();
				photo = dessert.getPhoto();
				list = dessert.getAllergens();
				available = dessert.getAvailability();
				//creation d'une branche
				Element Edessert = new Element("dessert");
				racine.addContent(Edessert);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Dessert");
				Edessert.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Edessert.addContent(Ename);
				//ajout photo
				Element Ephoto = new Element("photo");
				Ephoto.setText(photo);
				Edessert.addContent(Ephoto);
				//ajout allergenes
				Element Eallergens = new Element("allergens");
				for (String allergen : list) {
					Element Eallergen = new Element("allergen");
					Eallergen.setText(allergen);
					Eallergens.addContent(Eallergen);
				}
				//Eallergens.setText(list.toString());
				Edessert.addContent(Eallergens);
				//ajout disponibilité
				Element Eavailable = new Element("available");
				Eavailable.setText(available.toString());
				Edessert.addContent(Eavailable);
			}
			//PROMOTION
			for(Promotion promo : promotions){
				//recuperation infos
				name = promo.getName();
				authCustomer = promo.getAuthCustomer();
				percentage = promo.getPercentage();
				list = promo.getRecipes();
				//creation d'une branche
				Element Epromo = new Element("promo");
				racine.addContent(Epromo);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Promotion");
				Epromo.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Epromo.addContent(Ename);
				//ajout recipes
				Element Erecipes = new Element("recipes");
				for (String recipe : list) {
					Element Erecipe = new Element("recipe");
					Erecipe.setText(recipe);
					Erecipes.addContent(Erecipe);
				}
				Epromo.addContent(Erecipes);
				//ajout authOnly
				Element EauthCustumer = new Element("authCustomerOnly");
				EauthCustumer.setText(authCustomer.toString());
				Epromo.addContent(EauthCustumer);
				//ajout %age
				Element Epercentage = new Element("percentage");
				Epercentage.setText(percentage.toString());
				Epromo.addContent(Epercentage);
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(composants, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}
	

	/**Import components from a XML file*/
	public static void importer(String xmlFile){
		breads.clear();
		garnishs.clear();
		sauces.clear();
		recipes.clear();
		drinks.clear();
		desserts.clear();
		promotions.clear();
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		Element racine = null;
		try {
			document = sxb.build(new File(xmlFile));
			racine = document.getRootElement();
		} catch (JDOMException | IOException e) {}
		String name;
		String photo;
		Boolean available;
		Double price;
		String category;
		Double percentage;
		Boolean authCustomer;
		//BREAD
		List<Element> listImport = racine.getChildren("bread");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation pain
			breads.add(new Bread(name, photo));
			int index = breads.size()-1;
			//ajout disponibilité
			breads.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				breads.get(index).addAllergen(allergen.getText());
			}
		}
		//GARNISH
		listImport = racine.getChildren("garnish");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation garniture
			garnishs.add(new Garnish(name, photo));
			int index = garnishs.size()-1;
			//ajout disponibilité
			garnishs.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				garnishs.get(index).addAllergen(allergen.getText());
			}
		}
		//SAUCE
		listImport = racine.getChildren("sauce");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation sauce
			sauces.add(new Sauce(name, photo));
			int index = sauces.size()-1;
			//ajout disponibilité
			sauces.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				sauces.get(index).addAllergen(allergen.getText());
			}
		}
		//RECIPE
		listImport = racine.getChildren("recipe");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			price = Double.valueOf(component.getChildText("price"));
			category = component.getChildText("category");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation recette
			recipes.add(new Recipe(name, photo, price, category));
			int index = recipes.size()-1;
			//ajout disponibilité
			recipes.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				recipes.get(index).addAllergen(allergen.getText());
			}
			//ajout prix
			recipes.get(index).setPrice(price);
			//ajout categorie
			recipes.get(index).setCategory(category);			
		}
		//DRINK
		listImport = racine.getChildren("drink");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation boisson
			drinks.add(new Drink(name, photo));
			int index = drinks.size()-1;
			//ajout disponibilité
			drinks.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				drinks.get(index).addAllergen(allergen.getText());
			}
		}
		//DESSERT
		listImport = racine.getChildren("dessert");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			//creation dessert
			desserts.add(new Dessert(name, photo));
			int index = desserts.size()-1;
			//ajout disponibilité
			desserts.get(index).setAvailability(available);
			//ajout allergenes
			Element allergens = component.getChild("allergens");
			List<Element> listAllergenes = allergens.getChildren();
			for (Element allergen : listAllergenes) {
				desserts.get(index).addAllergen(allergen.getText());
			}
		}
		//PROMOTION
		listImport = racine.getChildren("promo");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			percentage = Double.valueOf(component.getChildText("percentage"));
			authCustomer = Boolean.valueOf(component.getChildText("authCustomerOnly"));
			//creation promotion
			promotions.add(new Promotion(name, percentage, authCustomer));
			int index = promotions.size()-1;
			//ajout authCustomer
			promotions.get(index).setAuthCustomer(authCustomer);
			//ajout listRecipes
			Element recipes = component.getChild("recipes");
			List<Element> listRecipe = recipes.getChildren();
			for (Element recipe : listRecipe) {
				promotions.get(index).addRecipe(recipe.getText()); 					
			}
			//ajout %age
			promotions.get(index).setPercentage(percentage);
		}
	}
}