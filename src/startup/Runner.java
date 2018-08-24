package startup;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import graphics.Window;

public class Runner<T> {
	private static final int WIDTH = 525;
	private static final int HEIGHT = 625;
	private static final String TITLE = "SplatBot Reverse Engineering Project V0.0.2";

	private static int nTurns;
	private static int delayMillis;
	private static Class<?> blueRobot;
	private static Class<?> redRobot;

	public Runner(Class<?> redRobot, Class<?> blueRobot, int delayMillis, int nTurns) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.blueRobot = blueRobot;
		this.redRobot = redRobot;
		this.nTurns = nTurns;
		this.delayMillis = delayMillis;
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Window window = new Window(WIDTH, HEIGHT, TITLE, redRobot, blueRobot);
				window.pack();
				window.setVisible(true);
			}
		});
	}
}