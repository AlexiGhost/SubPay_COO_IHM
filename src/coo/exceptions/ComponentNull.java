package coo.exceptions;

public class ComponentNull extends Exception{
	private static final long serialVersionUID = 1825042017L; //hhjjmmaaaa

	public ComponentNull(String component) {
		String info = "Ce composant n'existe pas : "+component+"\n";
		javax.swing.JOptionPane.showMessageDialog(null, info); 
	}
	
}
