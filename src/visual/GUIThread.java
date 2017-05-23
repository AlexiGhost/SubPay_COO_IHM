package visual;

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
			ControllerManager.initialize();
			break;
		default:
			break;
		}
	}
}
