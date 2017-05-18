package coo.frame;

import javax.swing.JFrame;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1625042017L; //hhjjmmaaaa
	//instanciation des composants
	static Panel panel = new Panel();
	static KListener keyListener = new KListener();
	static MListener mouseListener = new MListener();
	
	public Frame(String title) {
		//propriétés de la fenêtre
		this.setVisible(true);
		this.setTitle(title);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		//ajout des gestionnaires d'événements
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
	}
}
