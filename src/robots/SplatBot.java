package robots;

import entities.Action;
import entities.Cell;
import entities.Action.ACTION;

public class SplatBot {
	public static int nTurn = 0;
	
	public static ACTION returnAction(Cell.CELL leftCell, Cell.CELL frontCell, Cell.CELL rightCell) {
		nTurn++;
		System.out.println(nTurn);
		
		if(nTurn % 5 == 1) {
			return Action.ACTION.TURN_LEFT;
		}
		return Action.ACTION.MOVE_FORWARD;
	}
}
