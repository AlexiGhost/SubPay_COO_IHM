package coo.product;

import java.util.ArrayList;
import java.util.List;

import coo.product.composants.*;

public class Order {
    private String OrderNb;
    public List<Product> products = new ArrayList<Product> ();
    public List<Menu> menus = new ArrayList<Menu> ();
    
//ADD / REMOVE
    public void addProduct(int size, Recipe recipe, Bread bread, List<Sauce> sauces, List<Garnish> garnishs){
    	products.add(new Product(size, recipe, bread, sauces, garnishs));    	
    }
    public void addProduct(Product product){
    	products.add(product);
    }
    public void delProduct(int index){
    	products.remove(index);
    }
//GETTERS / SETTERS
    //orderNb
    public String getOrderNb() {
		return OrderNb;
	}
	public void setOrderNb(String orderNb) {
		OrderNb = orderNb;
	}
}
