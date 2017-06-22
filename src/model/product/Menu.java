package model.product;

public class Menu {
    private Product product;
    private Drink drink;
    private int iceCubeNb;
    private Dessert dessert;
    private static double menuPrice;

    /**Create a menu*/
    public Menu(){
    }
    public Menu(Product product, Drink drink, Dessert dessert) {
    	this.dessert = dessert;
    	this.drink = drink;
    	this.product = product;
	}
//GETTERS / SETTERS
    //product
    /**Return the product of the menu*/
    public Product getProduct() {
		return product;
	}
    /**Set the product of the menu*/
	public void setProduct(Product product) {
		this.product = product;
	}
	//drink
    /**Return the drink of the menu*/
    public Drink getDrink() {
		return drink;
	}
    /**Set the drink of the menu*/
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	//dessert
    /**Return the dessert of the menu*/
    public Dessert getDessert() {
		return dessert;
	}
    /**Set the dessert of the menu*/
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
	//iceCubeNb
	public int getIceCubeNb() {
		return iceCubeNb;
	}
	public void setIceCubeNb(int iceCubeNb) {
		this.iceCubeNb = iceCubeNb;
	}
	//prix du menu
	public static void setMenuPrice(double price){
		menuPrice = price;
	}
	public static double getMenuPrice(){
		return menuPrice;
	}
}
