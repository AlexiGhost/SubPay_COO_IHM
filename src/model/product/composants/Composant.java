package model.product.composants;

import java.util.ArrayList;
import java.util.List;

public class Composant {
	
    protected String name;
    protected boolean available;
    protected boolean news;
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
	/**Return the name of the component*/
	public String getName() {
		return name;
	}
	/**Set the name of the component*/
	public void setName(String name) {
		this.name = name;
	}
	//photo
	/**Return the photo path of the component*/
	public String getPhoto(){
	return photo;
	}
	/**Set the photo path of the component*/
	public void setPhoto(String photo){
	this.photo = photo;
	}
	//available
	/**Return the availability of the component*/
	public boolean getAvailability(){
		return available;
	}
	/**Set the availability of the component*/
	public void setAvailability(boolean available){
		this.available = available;
	}
	//new
	/**Return the "new" of the component*/
	public boolean getNew(){
		return news;
	}
	/**Set the "new" of the component*/
	public void setNew(boolean news){
		this.news = news;
	}
	//allergen
	/**Return a list wich contains the allergens of the component*/
	public List<String> getAllergens(){
		return allergens;
	}
	
    
}
