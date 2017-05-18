package coo.product;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;

    public List<Product> product = new ArrayList<Product> ();

    public List<Drink> drink = new ArrayList<Drink> ();

    public List<Dessert> dessert = new ArrayList<Dessert> ();

//GETTERS / SETTERS
    //name
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
