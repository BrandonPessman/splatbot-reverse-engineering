package entities;

public class Action {
	public enum ACTION {
		MOVE_FORWARD,
		MOVE_BACKWARD,
		TURN_RIGHT,
		TURN_LEFT,
		PASS,
		SPLAT;
		
		public String toString() {
			if(this == ACTION.MOVE_FORWARD) {
				return "MOVE_FORWARD";
			} else if (this == ACTION.MOVE_BACKWARD) {
				return "MOVE_BACKWARD";
			} else if (this == ACTION.TURN_RIGHT) {
				return "TURN_RIGHT";
			} else if (this == ACTION.TURN_LEFT) {
				return "TURN_LEFT";
			} else if (this == ACTION.PASS) {
				return "PASS";
			} else if (this == ACTION.SPLAT) {
				return "SPLAT";
			} else {
				return "NULL";
			}
		}
	}
}
