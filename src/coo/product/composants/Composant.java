package coo.product.composants;

import java.util.ArrayList;
import java.util.List;

public class Composant {
	
    protected String name;
    protected boolean available;
    protected List<String> allergens  = new ArrayList<String> ();
    protected String photo;
    
    public Composant(String name, String photo) {
    	this.name = name;
    	this.photo = photo;
    	this.available = true;
	}
    
    public void addAllergen(String allergen){
		this.allergens.add(allergen);
	}
	public void delAllergens(){
		allergens.clear();
	}
	public void delAllergen(String allergenName){
		for(String allergen : allergens){
			if(allergenName == allergen){
				allergens.remove(allergen);
			}
		}
	}

//GETTERS / SETTERS
	//name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//photo
	public String getPhoto(){
	return photo;
	}
	public void setPhoto(String photo){
	this.photo = photo;
	}
	//available
	public boolean getAvailability(){
		return available;
	}
	public void setAvailability(boolean available){
		this.available = available;
	}
	//allergen
	public List<String> getAllergens(){
		return allergens;
	}
	
    
}
