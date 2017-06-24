package model;

import java.util.ArrayList;
import java.util.List;

import model.product.*;
import model.product.composants.*;

public class AuthentificatedCustomer {
	private String lastName;
	private String firstName;
	private String mail;
	private String phoneNumber;
    private String password;
    private int iceCubeNb;
    private Bread favoriteBread;
    private Sauce favoriteSauce;
    private Recipe favoriteRecipe;
    private Drink favoriteDrink;
    private List <Garnish> favoriteGarnish = new ArrayList<Garnish>();
    private List<Order> order = new ArrayList<Order> ();
    private List<String> allergens = new ArrayList<String> ();
    private boolean mailChoice;
    private boolean phoneChoice;

    public AuthentificatedCustomer(){
    	lastName = "";
    	firstName = "";
    	mail = "";
    	phoneNumber = "";
    	password = "";
    	iceCubeNb = -1;
    	favoriteBread = new Bread();
    	favoriteSauce = new Sauce();
    	favoriteRecipe = new Recipe();
    	favoriteDrink = new Drink();
    	mailChoice = false;
    	phoneChoice = false;
    }
    
   /**Methods**/
    
    //Verify number in String
    private boolean numberInStringControl(String s){
    	char[] c = s.toCharArray();
    	for ( int i = 0; i < c.length; i++){
    	   if ( Character.isDigit(c[i]))
    		   return true;
    	}
    	return false;
    }
    //Verify all characters in string are numbers
    private boolean onlyNumberInStringControl(String s){
    	char[] c = s.toCharArray();
    	for ( int i = 0; i < c.length; i++){
    	   if ( ! Character.isDigit(c[i]))
    		   return false;
    	}
    	return true;
    }
    //LastName
    public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	//FirstName
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//Mail
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) throws Exception{
		if (mail.contains("@")){ // Verify @ in mail
			this.mail = mail;
		}
		else
			throw new Exception();
	}
	//PhoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber (String phoneNumber) throws Exception{
		if(phoneNumber.length() == 10 && onlyNumberInStringControl(phoneNumber)) //Verify 10 numbers in string
			this.phoneNumber = phoneNumber;
		else
			throw new Exception();
	}
	//Password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws Exception {
		if(password.length() >=8 && password.matches(".*[a-zA-Z].*") && numberInStringControl(password))
			this.password = password;
		else
			throw new Exception();
	}
	//IceCubeNb
	public int getIceCubeNb() {
		return iceCubeNb;
	}
	public void setIceCubeNb(int iceCubeNb) {
		this.iceCubeNb = iceCubeNb;
	}
	//FavoriteBread
	public Bread getFavoriteBread() {
		return favoriteBread;
	}
	public void setFavoriteBread(Bread favoriteBread) {
		this.favoriteBread = favoriteBread;
	}
	//FavoriteSauce
	public Sauce getFavoriteSauce() {
		return favoriteSauce;
	}
	public void setFavoriteSauce(Sauce favoriteSauce) {
		this.favoriteSauce = favoriteSauce;
	}
	//FavoriteRecipe
	public Recipe getFavoriteRecipe() {
		return favoriteRecipe;
	}
	public void setFavoriteRecipe(Recipe favoriteRecipe) {
		this.favoriteRecipe = favoriteRecipe;
	}
	//FavoriteDrink
	public Drink getFavoriteDrink() {
		return favoriteDrink;
	}
	public void setFavoriteDrink(Drink favoriteDrink) {
		this.favoriteDrink = favoriteDrink;
	}
	//FavoriteGarnish
	public List<Garnish> getFavoriteGarnish() {
		return favoriteGarnish;
	}
	public void setFavoriteGarnish(List<Garnish> favoriteGarnish) {
		this.favoriteGarnish = favoriteGarnish;
	}
    public void addGarnish(Garnish garnish){
    	this.favoriteGarnish.add(garnish);
    }
    public void delGarnishs(){
    	favoriteGarnish.clear();
    }
    public void delGarnish(Garnish g){
    	this.favoriteGarnish.remove(g);
    }
	//Order
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	public void addOrder(Order order){
    	this.order.add(order);
    }
    public void delOrders(){
    	order.clear();
    }
    public void delGarnish(Order order){
    	for(Order existingOrder : this.order){
    		if(existingOrder.equals(order)){
    			this.order.remove(order);
    		}
    	}
    }
	//Allergens
	public List<String> getAllergens() {
		return allergens;
	}
	public void setAllergens(List<String> allergens) {
		this.allergens = allergens;
	}
	public void addAllergen(String allergen){
    	this.allergens.add(allergen);
    }
    public void delAllergens(){
    	allergens.clear();
    }
    public void delAllergen(String allergen){
    	for(String existingAllergen : this.allergens){
    		if(existingAllergen.equals(allergen)){
    			this.allergens.remove(allergen);
    		}
    	}
    }
    //MailChoice
    public boolean getMailChoice(){
    	return mailChoice;
    }
    public void setMailChoice(boolean mailChoice) {
		this.mailChoice = mailChoice;
	}
    //PhoneChoice
    public boolean getPhoneChoice(){
    	return phoneChoice;
    }
    public void setPhoneChoice(boolean phoneChoice) {
		this.phoneChoice = phoneChoice;
	}
}
