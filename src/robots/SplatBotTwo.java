package robots;

import entities.Action;
import entities.Action.ACTION;

public class SplatBotTwo {
	public static int nTurn = 0;
	
	public static ACTION returnAction() {
		nTurn++;
		System.out.println(nTurn);
		return Action.ACTION.MOVE_FORWARD;
	}
}
