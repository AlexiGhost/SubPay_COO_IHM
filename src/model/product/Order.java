package model.product;

import java.util.ArrayList;
import java.util.List;

import model.product.composants.*;

public class Order {
    private int orderNb;
    private List<Product> products = new ArrayList<Product> ();
    private List<Menu> menus = new ArrayList<Menu> ();
    private boolean authCustomer;
    private static int oldNb;
    
    public Order(boolean authCustomer){
    	this.authCustomer = authCustomer;
    	if(oldNb != 100){
    		orderNb = oldNb + 1;
    		oldNb++;
    	}
    	else{
    		orderNb = 0;
    		oldNb = 0;
    	}
    }
    
//ADD / REMOVE
    //product
    /**Add a new product to the order*/
    public void addProduct(int size, Recipe recipe, Bread bread, List<Sauce> sauces, List<Garnish> garnishs){
    	products.add(new Product(size, recipe, bread, sauces, garnishs));    	
    }
    /**Add an existing product to the order*/
    public void addProduct(Product product){
    	products.add(product);
    }
    /**Remove a product from the order*/
    public void delProduct(int index){
    	products.remove(index);
    }
    
    //menu
    /**Add a menu to the order*/
    public void addMenu(Menu menu){
    	menus.add(menu);
    }
    /**Remove a menu from the order*/
    public void delMenu(int index){
    	products.remove(index);
    }
//GETTERS / SETTERS
    //orderNb
    /**Return the order number*/
    public int getOrderNb() {
		return orderNb;
	}
    /**Set the order number*/
	public void setOrderNb(int orderNb) {
		orderNb = orderNb;
	}
	//menus
	/**Return the menus list*/
	public List<Menu> getMenus() {
		return menus;
	}
	/**Set the menus list*/
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	//products
	/**Return the products list*/
	public List<Product> getProducts() {
		return products;
	}
	/**Set the products list*/
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	//authCustomer
	/**Return if the order is for an authenticated client or not*/
	public boolean getAuthCustomer(){
		return authCustomer;
		
	}
	/**Set if the order is for an authenticated client*/
	public void setAuthCustomer(boolean authCustomer) {
		this.authCustomer = authCustomer;
	}
	
	
}
