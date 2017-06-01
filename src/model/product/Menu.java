package model.product;

public class Menu {
    private Product product;
    private Drink drink;
    private Dessert dessert;

    /**Create a menu*/
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
}