package model.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

import model.product.composants.*;
/**Contain the list of components and methods for ADD/DEL/EDIT/IMPORT/EXPORT them*/
public class ComponentManagement {
	private static List<Bread> breads = new ArrayList<Bread>();
	private static List<Garnish> garnishs = new ArrayList<Garnish>();
	private static List<Sauce> sauces = new ArrayList<Sauce>();
	private static List<Recipe> recipes = new ArrayList<Recipe>();
	private static List<Promotion> promotions = new ArrayList<Promotion>();
	private static List<Drink> drinks = new ArrayList<Drink>();
	private static List<Dessert> desserts = new ArrayList<Dessert>();
	private static List<String> allergens = new ArrayList<String>();
	private static List<Composant> nouveautes = new ArrayList<Composant>();
	private static String componentPath = "component.xml";

// ---------- Lists Management (ADD/DEL/EDIT) ----------
	//BreadList management
	/**Add a bread to the component list*/
	public static void addBread(String name, String photo){
		Bread bread = new Bread(name, photo);
		breads.add(bread);
	}
	/**Delete a specific bread from the component list*/
	public static void delBread(String name){
		if(breads.size() == 1 && breads.get(0).getName().equals(name)){
			breads.clear();
		} else { 
			for (Bread bread : breads) {
				if(bread.getName().equals(name)){
					int index = breads.indexOf(bread);
					breads.remove(index);
					break;
				}
			}
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//GarnishList management
	/**Add a garnish to the component list*/
	public static void addGarnish(String name, String photo){
		Garnish garnish = new Garnish(name, photo);
		garnishs.add(garnish);
	}
	/**Delete a specific garnish from the component list*/
	public static void delGarnish(String name){
		if(garnishs.size() == 1 && garnishs.get(0).getName().equals(name)){
			garnishs.clear();
		} else { 
			for (Garnish garnish : garnishs) {
				if(garnish.getName().equals(name)){
					int index = garnishs.indexOf(garnish);
					garnishs.remove(index);
					break;
				}
			}
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//SauceList management
	/**Add a sauce to the component list*/
	public static void addSauce(String name, String photo){
		Sauce sauce = new Sauce(name, photo);
		sauces.add(sauce);
	}
	/**Delete a specific sauce from the component list*/
	public static void delSauce(String name){
		if(sauces.size() == 1 && sauces.get(0).getName().equals(name)){
			sauces.clear();
		} else { 
			for (Sauce sauce : sauces) {
				if(sauce.getName().equals(name)){
					int index = sauces.indexOf(sauce);
					sauces.remove(index);
					break;
				}
			}
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//RecipeList management
	/**Add a recipe to the component list*/
	public static void addRecipe(Recipe recipe){
		recipes.add(recipe);
	}
	/**Add a recipe to the component list*/
	public static void addRecipe(String name, String category, double price, String photo){
		Recipe recipe = new Recipe(name, photo, price, category);
		recipes.add(recipe);
	}
	/**Delete a specific recipe from the component list*/
	public static void delRecipe(String name){
		if(recipes.size() == 1 && recipes.get(0).getName().equals(name)){
			recipes.clear();
		} else {
			for (Recipe recipe : recipes) {
				if(recipe.getName().equals(name)){
					int index = recipes.indexOf(recipe);
					recipes.remove(index);
					break;
				}
			}
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//PromotionList management
	/**Add a promotion to the component list*/
	public static void addPromotion(String name, double percentage, boolean auth){
		Promotion promo = new Promotion(name, percentage, auth);
		promotions.add(promo);
	}
	/**Delete a specific promotion from the component list*/
	public static void delPromotion(String name){
		if(promotions.size() == 1 && promotions.get(0).getName().equals(name)){
			promotions.clear();
		} else {
			for (Promotion promo : promotions) {
				if(promo.getName().equals(name)){
					int index = promotions.indexOf(promo);
					promotions.remove(index);
					break;
				}
			}	
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//DrinkList management
	/**Add a drink to the component list*/
	public static void addDrink(String name, String photo){
		Drink drink = new Drink(name, photo);
		drinks.add(drink);
	}
	/**Delete a specific drink from the component list*/
	public static void delDrink(String name){
		if(drinks.size() == 1 && drinks.get(0).getName().equals(name)){
			drinks.clear();
		} else {
			for (Drink drink : drinks) {
				if(drink.getName().equals(name)){
					int index = drinks.indexOf(drink);
					drinks.remove(index);
					break;
				}
			}	
		}
		ComponentManagement.exportComponent("component.xml");
	}
	//DessertList management
	/**Add a dessert to the component list*/
	public static void addDessert(String name, String photo){
		Dessert dessert = new Dessert(name, photo);
		desserts.add(dessert);
	}
	/**Delete a specific dessert from the component list*/
	public static void delDessert(String name){
		if(desserts.size() == 1 && desserts.get(0).getName().equals(name)){
			desserts.clear();
		} else {
			for (Dessert dessert : desserts) {
				if(dessert.getName().equals(name)){
					int index = desserts.indexOf(dessert);
					desserts.remove(index);
					break;
				}
			}
		}
		ComponentManagement.exportComponent("component.xml");
	}
	
	//Nouveautés management
	public static void setNouveautes(){
		for(Bread bread : breads){
			if(bread.getNew()) nouveautes.add(bread);
		}
		for(Recipe recipe : recipes){
			if(recipe.getNew()) nouveautes.add(recipe);
		}
		for(Garnish garnish : garnishs){
			if(garnish.getNew()) nouveautes.add(garnish);
		}
		for(Sauce sauce : sauces){
			if(sauce.getNew()) nouveautes.add(sauce);
		}
		for(Drink drink : drinks){
			if(drink.getNew()) nouveautes.add(drink);
		}
		for(Dessert dessert : desserts){
			if(dessert.getNew()) nouveautes.add(dessert);
		}
	}
	
// ---------- GETTERS / SETTERS ----------
	//bread
	/**Return the list of breads*/
	public static List<Bread> getBreads(){
		return breads;
	}
	/**Return a bread from the list*/
	public static Bread getBread(String name){
		for(Bread bread : breads){
			if(bread.getName().equals(name)){
				return bread;
			}
		}
		return null;
	}
	//sauce
	/**Return the list of sauces*/
	public static List<Sauce> getSauces(){
		return sauces;
	}
	/**Return a sauce from the list*/
	public static Sauce getSauce(String name){
		for(Sauce sauce : sauces){
			if(sauce.getName().equals(name)){
				return sauce;
			}
		}
		return null;
	}
	//garnish
	/**Return the list of garnishs*/
	public static List<Garnish> getGarnishs() {
		return garnishs;
	}
	/**Return a garnish from the list*/
	public static Garnish getGarnish(String name){
		for(Garnish garnish : garnishs){
			if(garnish.getName().equals(name)){
				return garnish;
			}
		}
		return null;
	}
	//recipe
	/**Return the list of recipes*/
	public static List<Recipe> getRecipes() {
		return recipes;
	}
	/**Return a recipe from the list*/
	public static Recipe getRecipe(String name){
		for(Recipe recipe : recipes){
			if(recipe.getName().equals(name)){
				return recipe;
			}
		}
		return null;
	}
	//promotion
	/**Return the list of promotions*/
	public static List<Promotion> getPromotions() {
		return promotions;
	}
	/**Return a promotion from the list*/
	public static Promotion getPromotion(String name){
		for(Promotion promotion : promotions){
			if(promotion.getName().equals(name)){
				return promotion;
			}
		}
		return null;
	}
	//drink
	/**Return the list of drinks*/
	public static List<Drink> getDrinks() {
		return drinks;
	}
	/**Return a drink from the list*/
	public static Drink getDrink(String name){
		for(Drink drink : drinks){
			if(drink.getName().equals(name)){
				return drink;
			}
		}
		return null;
	}
	//dessert
	/**Return the list of desserts*/
	public static List<Dessert> getDesserts() {
		return desserts;
	}
	/**Return a dessert from the list*/
	public static Dessert getDessert(String name){
		for(Dessert dessert : desserts){
			if(dessert.getName().equals(name)){
				return dessert;
			}
		}
		return null;
	}
	
	//path
	
	public static String getComponentPath() {
		return componentPath;
	}
	//allergens
	public static List<String> getAllergens() {
		return allergens;
	}
	public static void setAllergens(List<String> allergens) {
		ComponentManagement.allergens = allergens;
	}
	public static void addAllergen(String allergen){
		allergens.add(allergen);
	}

// ---------- IMPORT / EXPORT ----------
	
	/**Export components to a XML file*/
	public static void exportComponent(String xmlFile) {
		Element racine = new Element("Composants");
		Document composants = new Document(racine);
		String name = null;
		String photo = null;
		Boolean available = null;
		Boolean news = null;
		Double price = null;
		String category = null;
		Double percentage = null;
		Boolean authCustomer = null;
		String date = null;
		List<String> list = null;
		try {
			//BREAD
			for(Bread bread : breads){
				//recuperation infos
				name = bread.getName();
				photo = bread.getPhoto();
				list = bread.getAllergens();
				available = bread.getAvailability();
				news = bread.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Ebread.addContent(Enew);
			}
			//GARNISH
			for(Garnish garnish : garnishs){
				//recuperation infos
				name = garnish.getName();
				photo = garnish.getPhoto();
				list = garnish.getAllergens();
				available = garnish.getAvailability();
				news = garnish.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Egarnish.addContent(Enew);
			}
			//SAUCE
			for(Sauce sauce : sauces){
				//recuperation infos
				name = sauce.getName();
				photo = sauce.getPhoto();
				list = sauce.getAllergens();
				available = sauce.getAvailability();
				news = sauce.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Esauce.addContent(Enew);
			}
			//RECIPE
			for(Recipe recipe : recipes){
				//recuperation infos
				name = recipe.getName();
				photo = recipe.getPhoto();
				list = recipe.getAllergens();
				available = recipe.getAvailability();
				news = recipe.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Erecipe.addContent(Enew);
			}
			//DRINK
			for(Drink drink : drinks){
				//recuperation infos
				name = drink.getName();
				photo = drink.getPhoto();
				list = drink.getAllergens();
				available = drink.getAvailability();
				news = drink.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Edrink.addContent(Enew);
			}
			//DESSERT
			for(Dessert dessert : desserts){
				//recuperation infos
				name = dessert.getName();
				photo = dessert.getPhoto();
				list = dessert.getAllergens();
				available = dessert.getAvailability();
				news = dessert.getNew();
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
				//ajout new
				Element Enew = new Element("new");
				Enew.setText(news.toString());
				Edessert.addContent(Enew);
			}
			//PROMOTION
			for(Promotion promo : promotions){
				//recuperation infos
				name = promo.getName();
				authCustomer = promo.getAuthCustomer();
				percentage = promo.getPercentage();
				String recipe = promo.getRecipe();
				category = promo.getCategory();
				date = promo.getDate();
				//creation d'une branche
				Element Epromo = new Element("promo");
				racine.addContent(Epromo);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "Promotion");
				Epromo.setAttribute(classe);
				//ajout nom
				Element Ename = new Element("name");
				Ename.setText(name);
				Epromo.addContent(Ename);
				//ajout recipe
				Element Erecipe = new Element("recipe");
				Erecipe.setText(recipe);
				Epromo.addContent(Erecipe);					
				//ajout categorie
				Element Ecategory = new Element("category");
				Ecategory.setText(category);
				Epromo.addContent(Ecategory);
				//ajout authOnly
				Element EauthCustumer = new Element("authCustomerOnly");
				EauthCustumer.setText(authCustomer.toString());
				Epromo.addContent(EauthCustumer);
				//ajout %age
				Element Epercentage = new Element("percentage");
				Epercentage.setText(percentage.toString());
				Epromo.addContent(Epercentage);
				//ajout date
				Element Edate = new Element("date");
				Edate.setText(date.toString());
				Epromo.addContent(Edate);
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(composants, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}
	

	/**Import components from a XML file*/
	public static void importComponent(String xmlFile){
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
		String name = null;
		String photo = null;
		Boolean available = null;
		Boolean news = null;
		Double price = null;
		String category = null;
		Double percentage = null;
		Boolean authCustomer = null;
		String date = null;
		//BREAD
		List<Element> listImport = racine.getChildren("bread");
		for (Element component : listImport) {
			name = component.getChildText("name");
			photo = component.getChildText("photo");
			available = Boolean.valueOf(component.getChildText("available"));
			news = Boolean.valueOf(component.getChildText("new"));
			//creation pain
			breads.add(new Bread(name, photo));
			int index = breads.size()-1;
			//ajout disponibilité
			breads.get(index).setAvailability(available);
			//ajout nouveauté
			breads.get(index).setNew(news);
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
			news = Boolean.valueOf(component.getChildText("new"));
			//creation garniture
			garnishs.add(new Garnish(name, photo));
			int index = garnishs.size()-1;
			//ajout disponibilité
			garnishs.get(index).setAvailability(available);
			//ajout nouveauté
			garnishs.get(index).setNew(news);
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
			news = Boolean.valueOf(component.getChildText("new"));
			//creation sauce
			sauces.add(new Sauce(name, photo));
			int index = sauces.size()-1;
			//ajout disponibilité
			sauces.get(index).setAvailability(available);
			//ajout nouveauté
			sauces.get(index).setNew(news);
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
			news = Boolean.valueOf(component.getChildText("new"));
			//creation recette
			recipes.add(new Recipe(name, photo, price, category));
			int index = recipes.size()-1;
			//ajout disponibilité
			recipes.get(index).setAvailability(available);
			//ajout nouveauté
			recipes.get(index).setNew(news);
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
			//ajout nouveauté
			drinks.get(index).setNew(news);
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
			news = Boolean.valueOf(component.getChildText("new"));
			//creation dessert
			desserts.add(new Dessert(name, photo));
			int index = desserts.size()-1;
			//ajout disponibilité
			desserts.get(index).setAvailability(available);
			//ajout nouveauté
			desserts.get(index).setNew(news);
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
			date = component.getChildText("date");
			category = component.getChildText("category");
			
			//creation promotion
			promotions.add(new Promotion(name, percentage, authCustomer));
			int index = promotions.size()-1;
			//ajout authCustomer
			promotions.get(index).setAuthCustomer(authCustomer);
			//ajout listRecipes
			String recipe = component.getChildText("recipe");
			promotions.get(index).setRecipe(recipe);
			//ajout categorie
			promotions.get(index).setCategory(category);
			//ajout %age
			promotions.get(index).setPercentage(percentage);
			//ajout date
			promotions.get(index).setDate(date);
		}
		setNouveautes();
		List<String> aList = new ArrayList<>();
		aList.add("Gluten");
		aList.add("Crustacés");
		aList.add("Oeufs");
		aList.add("Poissons");
		aList.add("Arachides");
		aList.add("Soja");
		aList.add("Lait");
		aList.add("Fruit à coques");
		aList.add("Céleri");
		aList.add("Moutarde");
		aList.add("Sésame");
		aList.add("Anhydride sulfureux");
		aList.add("Lupin");
		aList.add("Mollusques");
		setAllergens(aList);
	}
}