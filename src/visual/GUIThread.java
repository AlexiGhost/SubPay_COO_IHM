package visual;

public class GUIThread extends Thread{
	
	public void run() {
		ControllerClient.initialize();
	}
}
