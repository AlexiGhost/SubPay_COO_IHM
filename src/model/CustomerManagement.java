package model;

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

import model.product.*;
import model.product.composants.*;

public class CustomerManagement {
	private static List<AuthentificatedCustomer> customers = new ArrayList<>();
	
//ADD/EDIT/DEL
	/**Add a customer to the customerList*/
	public static void addCustomer(AuthentificatedCustomer aCustomer){
		customers.add(aCustomer);
	}
	/**Delete a customer from the customerList*/
	public static void delCustomer(String login, String password){
		for (AuthentificatedCustomer aCustomer : customers) {
			if((aCustomer.getMail().equals(login) || aCustomer.getPhoneNumber().equals(login)) && aCustomer.getPassword().equals(password)){
				customers.remove(aCustomer);
			}
		}
	}
	
//GETTERS/SETTERS
	/**Return the customers list*/
	public static List<AuthentificatedCustomer> getCustomers() {
		return customers;
	}
	
//IMPORT/EXPORT
	/**Export customers to a XML file*/
	public static void exportCustomer(String xmlFile) {
		Element racine = new Element("Customers");
		Document Dcustomers = new Document(racine);
		String lastName;
		String firstName;
		String mail;
		String phoneNumber;
		String password;
		Integer iceCubeNb;
		Bread favoriteBread;
		Sauce favoriteSauce;
		Recipe favoriteRecipe;
		Drink favoriteDrink;
		List<Garnish> favoriteGarnish = null;
		List<String> allergenList = null;
		Boolean mailChoice;
		Boolean phoneChoice;
		try {
			//BREAD
			for(AuthentificatedCustomer customer : customers){
				//recuperation infos
				lastName = customer.getLastName();
				firstName = customer.getFirstName();
				mail = customer.getMail();
				phoneNumber = customer.getPhoneNumber();
				password = customer.getPassword();
				iceCubeNb = customer.getIceCubeNb();
				favoriteBread = customer.getFavoriteBread();
				favoriteSauce = customer.getFavoriteSauce();
				favoriteRecipe = customer.getFavoriteRecipe();
				favoriteDrink = customer.getFavoriteDrink();
				favoriteGarnish = customer.getFavoriteGarnish();
				allergenList = customer.getAllergens();
				mailChoice = customer.getMailChoice();
				phoneChoice = customer.getPhoneChoice();
				//creation d'une branche
				Element eCustomer = new Element("customer");
				racine.addContent(eCustomer);
				org.jdom2.Attribute classe = new org.jdom2.Attribute("classe", "AuthentificatedCustomer");
				eCustomer.setAttribute(classe);
				//ajout lastname
				Element eLastName = new Element("lastName");
				eLastName.setText(lastName);
				eCustomer.addContent(eLastName);
				//ajout firstName
				Element eFirstName = new Element("firstName");
				eFirstName.setText(firstName);
				eCustomer.addContent(eFirstName);
				//ajout mail
				Element eMail = new Element("mail");
				eMail.setText(mail);
				eCustomer.addContent(eMail);
				if(mailChoice){
					org.jdom2.Attribute aMailChoice = new org.jdom2.Attribute("choice", "true");
					eMail.setAttribute(aMailChoice);
				}
				//ajout phoneNumber
				Element ePhoneNumber = new Element("phoneNumber");
				ePhoneNumber.setText(phoneNumber);
				eCustomer.addContent(ePhoneNumber);
				if(phoneChoice){
					org.jdom2.Attribute aPhoneChoice = new org.jdom2.Attribute("choice", "true");
					ePhoneNumber.setAttribute(aPhoneChoice);
				}
				//ajout password
				Element ePassword = new Element("password");
				ePassword.setText(password);
				eCustomer.addContent(ePassword);
				//ajout iceCubeNb
				Element eIceCube = new Element("iceCubeNb");
				eIceCube.setText(iceCubeNb.toString());
				eCustomer.addContent(eIceCube);
				//ajout fBread
				Element eFBread = new Element("fBread");
				eFBread.setText(favoriteBread.getName());
				eCustomer.addContent(eFBread);
				//ajout fSauce
				Element eFSauce = new Element("fSauce");
				eFSauce.setText(favoriteSauce.getName());
				eCustomer.addContent(eFSauce);
				//ajout fRecipe
				Element eFRecipe = new Element("fRecipe");
				eFRecipe.setText(favoriteRecipe.getName());
				eCustomer.addContent(eFRecipe);
				//ajout fDrink
				Element eFDrink = new Element("fDrink");
				eFDrink.setText(favoriteDrink.getName());
				eCustomer.addContent(eFDrink);
				//ajout fGarnish
				Element eFGarnishs = new Element("fGarnishs");
				for (Garnish garnish : favoriteGarnish) {
					Element eFGarnish = new Element("fGarnish");
					eFGarnish.setText(garnish.getName());
					eFGarnishs.addContent(eFGarnish);
				}
				eCustomer.addContent(eFGarnishs);
				//ajout allergenes
				Element eAllergens = new Element("allergens");
				for (String allergen : allergenList) {
					Element eAllergen = new Element("allergen");
					eAllergen.setText(allergen);
					eAllergens.addContent(eAllergen);
				}
				eCustomer.addContent(eAllergens);

				
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(Dcustomers, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}
	
	//Orders
	public static void exportCustomerOrders(String xmlFile) {
		Element racine = new Element("CustomerOrders");
		Document Dcustomers = new Document(racine);
		String mail;
		String phoneNumber;
		Integer size;
		Boolean plate;
		Bread bread;
		Recipe recipe;
		Drink drink;
		Integer iceCubeNb;
		Dessert dessert;
		List<Sauce> sauces;
		List<Garnish> garnishs = null;
		try {
			//Liste des clients auth
			for(AuthentificatedCustomer customer : customers){
				//recuperation infos
				mail = customer.getMail();
				phoneNumber = customer.getPhoneNumber();
				Element eOrders = new Element("Orders");
				racine.addContent(eOrders);
				org.jdom2.Attribute aMail = new org.jdom2.Attribute("mail", mail);
				eOrders.setAttribute(aMail);
				org.jdom2.Attribute aPhone = new org.jdom2.Attribute("phone", phoneNumber);
				eOrders.setAttribute(aPhone);
				//Liste des commandes du client
				for(Order order : customer.getOrder()){
					Element eOrder = new Element("order");
					eOrders.addContent(eOrder);
					Element eMenus = new Element("Menus");
					eOrder.addContent(eMenus);
					//Liste menu de la commande
					for(Menu menu : order.getMenus()){
						size = menu.getProduct().getSize();
						plate = menu.getProduct().getPlate();
						bread = menu.getProduct().getBread();
						recipe = menu.getProduct().getRecipe();
						drink = menu.getDrink();
						iceCubeNb = menu.getIceCubeNb();
						dessert = menu.getDessert();
						sauces = menu.getProduct().getSauces();
						garnishs = menu.getProduct().getGarnishs();
						//ajout branche menu
						Element eMenu = new Element("menu");
						eMenus.addContent(eMenu);
						//ajout branche produit
						Element eProduct = new Element("product");
						eMenu.addContent(eProduct);
						org.jdom2.Attribute aSize = new org.jdom2.Attribute("size", size.toString());
						eProduct.setAttribute(aSize);
						if(plate){
							org.jdom2.Attribute aPlate = new org.jdom2.Attribute("plate", "true");
							eProduct.setAttribute(aPlate);
						}
						//ajout bread dans produit
						Element eBread = new Element("bread");
						eBread.setText(bread.getName());
						eProduct.addContent(eBread);
						//ajout recipe dans produit
						Element eRecipe = new Element("recipe");
						eRecipe.setText(recipe.getName());
						eProduct.addContent(eRecipe);
						//ajout garnish dans produit
						Element eGarnishs = new Element("Garnishs");
						for (Garnish garnish : garnishs) {
							Element eGarnish = new Element("garnish");
							eGarnish.setText(garnish.getName());
							eGarnishs.addContent(eGarnish);
						}
						eProduct.addContent(eGarnishs);
						//ajout sauce dans produit
						Element eSauces = new Element("Sauces");
						for (Sauce sauce : sauces) {
							Element eSauce = new Element("sauce");
							eSauce.setText(sauce.getName());
							eSauces.addContent(eSauce);
						}
						eProduct.addContent(eSauces);
						//ajout drink dans menu
						Element eDrink = new Element("drink");
						eDrink.setText(drink.getName());
						eMenu.addContent(eDrink);
						org.jdom2.Attribute aIceCubeNb = new org.jdom2.Attribute("iceCubeNb", iceCubeNb.toString());
						eDrink.setAttribute(aIceCubeNb);
						//ajout dessert dans menu
						Element eDessert = new Element("dessert");
						eDessert.setText(dessert.getName());
						eMenu.addContent(eDessert);
					}
					//Liste produits de la commande
					Element eProducts = new Element("ProductsOnly");
					eOrder.addContent(eProducts);
					for(Product product : order.getProducts()){
						size = product.getSize();
						plate = product.getPlate();
						bread = product.getBread();
						recipe = product.getRecipe();
						sauces = product.getSauces();
						garnishs = product.getGarnishs();
						//ajout branche produit uniquement
						Element eProduct = new Element("productOnly");
						eProducts.addContent(eProduct);
						org.jdom2.Attribute aSize = new org.jdom2.Attribute("size", size.toString());
						eProduct.setAttribute(aSize);
						if(plate){
							org.jdom2.Attribute aPlate = new org.jdom2.Attribute("plate", "true");
							eProduct.setAttribute(aPlate);
						}
						//ajout bread dans produit
						if(!plate){
							Element eBread = new Element("bread");
							eBread.setText(bread.getName());
							eProduct.addContent(eBread);
						}
						//ajout recipe dans produit
						Element eRecipe = new Element("recipe");
						eRecipe.setText(recipe.getName());
						eProduct.addContent(eRecipe);
						//ajout garnish dans produit
						Element eGarnishs = new Element("Garnishs");
						for (Garnish garnish : garnishs) {
							Element eGarnish = new Element("garnish");
							eGarnish.setText(garnish.getName());
							eGarnishs.addContent(eGarnish);
						}
						eProduct.addContent(eGarnishs);
						//ajout sauce dans produit
						Element eSauces = new Element("Sauces");
						for (Sauce sauce : sauces) {
							Element eSauce = new Element("sauce");
							eSauce.setText(sauce.getName());
							eSauces.addContent(eSauce);
						}
						eProduct.addContent(eSauces);			
					}
				}
				
			}
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(Dcustomers, new FileOutputStream(xmlFile));
		}
		catch (IOException e) {}
	}

	/**Import customers from a XML file
	 * @throws Exception */
	public static void importCustomer(String xmlFile) throws Exception{
		customers.clear();
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		Element racine = null;
		try {
			document = sxb.build(new File(xmlFile));
			racine = document.getRootElement();
		} catch (JDOMException | IOException e) {}
		
		
		List<Element> Ecustomers = racine.getChildren("customer");
		for (Element customer : Ecustomers) {
			AuthentificatedCustomer tCustomer = new AuthentificatedCustomer();
			tCustomer.setLastName(customer.getChildText("lastName"));
			tCustomer.setFirstName(customer.getChildText("firstName"));
			if(!(customer.getChildText("mail").equals("")))
				tCustomer.setMail(customer.getChildText("mail"));
			tCustomer.setMailChoice(Boolean.valueOf(customer.getChild("mail").getAttributeValue("choice")));
			if(!(customer.getChildText("phoneNumber").equals("")))
				tCustomer.setPhoneNumber(customer.getChildText("phoneNumber"));
			tCustomer.setPhoneChoice(Boolean.valueOf(customer.getChild("phoneNumber").getAttributeValue("choice")));
			if(!(customer.getChildText("password").equals("")))
				tCustomer.setPassword(customer.getChildText("password"));
			tCustomer.setIceCubeNb(Integer.valueOf(customer.getChildText("iceCubeNb")));
			//creation pain
			for (Bread bread : ComponentManagement.getBreads()) {
				if(bread.getName().equals(customer.getChildText("fBread"))){
					tCustomer.setFavoriteBread(bread);
				}
			}
			//creation sauce
			for (Sauce sauce : ComponentManagement.getSauces()) {
				if(sauce.getName().equals(customer.getChildText("fSauce"))){
					tCustomer.setFavoriteSauce(sauce);
				}
			}
			//creation recette
			for (Recipe recipe : ComponentManagement.getRecipes()) {
				if(recipe.getName().equals(customer.getChildText("fRecipe"))){
					tCustomer.setFavoriteRecipe(recipe);
				}
			}
			//creation garniture
			for(Element garnish : customer.getChild("fGarnishs").getChildren()){
				for (Garnish garnishMana : ComponentManagement.getGarnishs()) {
					if(garnishMana.getName().equals(garnish.getText())){
						tCustomer.addGarnish(garnishMana);
					}
				}
			}
			
			//ajout allergenes
			for(Element allergen : customer.getChild("allergens").getChildren()){
				for (String allergenMana : ComponentManagement.getAllergens()) {
					if(allergenMana.equals(allergen.getText())){
						tCustomer.addAllergen(allergenMana);
					}
				}
			}
			//creation customer
			addCustomer(tCustomer);
		}


	}
	/**Import customer's orders from a XML file*/
	public static void importCustomerOrders(String xmlFile){
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		Element racine = null;
		try {
			document = sxb.build(new File(xmlFile));
			racine = document.getRootElement();
		} catch (JDOMException | IOException e) {}
		
		
		List<Element> Eorders = racine.getChildren("Orders");
		for(Element orders : Eorders){
			
			AuthentificatedCustomer tCustomer = new AuthentificatedCustomer();
			for(AuthentificatedCustomer customer : customers){
				if(customer.getMail().equals(orders.getAttributeValue("mail").toString()) || customer.getPhoneNumber().equals(orders.getAttributeValue("phone").toString())){
					tCustomer = customer;
				}
			}
			List<Order> tOrders = new ArrayList<Order>();	
			List<Element> Eorder = orders.getChildren("order");
			for (Element order : Eorder) {
				Order tOrder = new Order();
				List<Element> Emenus = order.getChild("Menus").getChildren();
				List<Element> Eproducts = order.getChild("ProductsOnly").getChildren();
				
				//Menus
				for (Element menu : Emenus) {
					Menu tMenu = new Menu();
					Product productMenu = new Product();
					productMenu.setSize(Integer.valueOf(menu.getChild("product").getAttributeValue("size").toString()));
					productMenu.setPlate(Boolean.valueOf(menu.getChild("product").getAttributeValue("plate").toString()));
					//Bread
					Bread breadMenu = new Bread();
					for (Bread bread : ComponentManagement.getBreads()) {
						if(bread.getName().equals(menu.getChild("product").getChildText("bread"))){
							breadMenu = bread;
						}
					}
					productMenu.setBread(breadMenu);
					//Recipe
					Recipe recipeMenu = new Recipe();
					for (Recipe recipe : ComponentManagement.getRecipes()) {
						if(recipe.getName().equals(menu.getChild("product").getChildText("recipe"))){
							recipeMenu = recipe;
						}
					}
					productMenu.setRecipe(recipeMenu);
					//Garnishs
					List<Garnish> garnishsMenu = new ArrayList<Garnish>();
					for (Element garnish : menu.getChild("product").getChild("Garnishs").getChildren()) {
						Garnish garnishMenu = new Garnish();
						for (Garnish garnishMana : ComponentManagement.getGarnishs()) {
							if(garnishMana.getName().equals(garnish.getText())){
								garnishMenu = garnishMana;
							}
						}
						garnishsMenu.add(garnishMenu);
					}
					productMenu.setGarnishs(garnishsMenu);
					//Sauces
					List<Sauce> saucesMenu = new ArrayList<Sauce>();
					for (Element sauce : menu.getChild("product").getChild("Sauces").getChildren()) {
						Sauce sauceMenu = new Sauce();
						for (Sauce sauceMana : ComponentManagement.getSauces()) {
							if(sauceMana.getName().equals(sauce.getText())){
								sauceMenu = sauceMana;
							}
						}
						saucesMenu.add(sauceMenu);
					}
					productMenu.setSauces(saucesMenu);
					//Product
					tMenu.setProduct(productMenu);
					//Drink
					Drink drinkMenu = new Drink();
					for (Drink drink : ComponentManagement.getDrinks()) {
						if(drink.getName().equals(menu.getChildText("drink"))){
							drinkMenu = drink;
						}
					}
					tMenu.setDrink(drinkMenu);
					//IceCubeNb
					tMenu.setIceCubeNb(Integer.valueOf(menu.getChild("drink").getAttributeValue("iceCubeNb").toString()));
					//Dessert
					Dessert dessertMenu = new Dessert();
					for (Dessert dessert : ComponentManagement.getDesserts()) {
						if(dessert.getName().equals(menu.getChildText("dessert"))){
							dessertMenu = dessert;
						}
					}
					tMenu.setDessert(dessertMenu);
					tOrder.addMenu(tMenu);
				}
				
				//ProductsOnly
				for (Element product : Eproducts) {
					Product tProduct = new Product();
					tProduct.setSize(Integer.valueOf(product.getAttributeValue("size")));
					tProduct.setPlate(Boolean.valueOf(product.getAttributeValue("plate")));
					//Bread
					Bread tBread = new Bread();
					for (Bread bread : ComponentManagement.getBreads()) {
						if(bread.getName().equals(product.getChildText("bread"))){
							tBread = bread;
						}
					}
					tProduct.setBread(tBread);
					//Recipe
					Recipe tRecipe = new Recipe();
					for (Recipe recipe : ComponentManagement.getRecipes()) {
						if(recipe.getName().equals(product.getChildText("recipe"))){
							tRecipe = recipe;
						}
					}
					tProduct.setRecipe(tRecipe);
					//Garnishs
					List<Garnish> tGarnishs = new ArrayList<Garnish>();
					for (Element garnish : product.getChild("Garnishs").getChildren()) {
						Garnish tGarnish = new Garnish();
						for (Garnish garnishMana : ComponentManagement.getGarnishs()) {
							if(garnishMana.getName().equals(garnish.getText())){
								tGarnish = garnishMana;
							}
						}
						tGarnishs.add(tGarnish);
					}
					tProduct.setGarnishs(tGarnishs);
					//Sauces
					List<Sauce> tSauces = new ArrayList<Sauce>();
					for (Element sauce : product.getChild("Sauces").getChildren()) {
						Sauce tSauce = new Sauce();
						for (Sauce sauceMana : ComponentManagement.getSauces()) {
							if(sauceMana.getName().equals(sauce.getText())){
								tSauce = sauceMana;
							}
						}
						tSauces.add(tSauce);
					}
					tProduct.setSauces(tSauces);
					tOrder.addProduct(tProduct);
				}
				tOrders.add(tOrder);
			}
			tCustomer.setOrder(tOrders);
		}

	}
}
