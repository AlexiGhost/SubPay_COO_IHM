package visual;

public class GUIThread extends Thread{
	
	public GUIThread() {
		run();
	}
	public void run() {
		Controller.initialize();
	}
}
