package visual;

import visual.manager.ControllerAccueil;

public class GUIThread extends Thread{
	
	public GUIThread(String type) {
		run(type);
	}
	public void run(String type) {
		switch (type) {
		case "client":
			ControllerClient.initialize();
			break;
		case "manager":
			ControllerAccueil.initialize();
			break;
		default:
			break;
		}
	}
}
