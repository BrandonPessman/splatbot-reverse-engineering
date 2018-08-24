package startup;

import java.lang.reflect.InvocationTargetException;

public class Main {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int delayMillis = 10;
		int nTurns = 500;
		
		new Runner(robots.SplatBot.class, robots.SplatBotTwo.class, delayMillis, nTurns);
	}
}
