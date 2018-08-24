package logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import entities.Action;
import entities.Cell;
import entities.Action.ACTION;
import entities.Cell.CELL;

public class CellLogic {
	private static final int AMOUNT_OF_ROCKS = 7;
	private static final int CELL_SIZE = 10;
	private static final int STARTING_CELL_X = 10;
	private static final int STARTING_CELL_Y = 10;
	private static final int CELL_LENGTH = 50;
	private static Class<?> redRobot;
	private static Class<?> blueRobot;
	private static int redRobotDirection = 2;
	private static int blueRobotDirection = 0;

	Cell.CELL[][] map = new Cell.CELL[CELL_SIZE][CELL_SIZE];
	CellLogic cl;

	public int getAmountOfRocks() {
		return AMOUNT_OF_ROCKS;
	}

	public int getCellSize() {
		return CELL_SIZE;
	}

	public int getStartingCellX() {
		return STARTING_CELL_X;
	}

	public int getStartingCellY() {
		return STARTING_CELL_Y;
	}

	public int getCellLength() {
		return CELL_LENGTH;
	}

	public void setCellLogic(CellLogic cl) {
		this.cl = cl;
	}

	public Cell.CELL[][] getMap() {
		return map;
	}

	public CellLogic getCellLogic() {
		return cl;
	}

	public void resetMap() {

	}

	public void generateMap() {

		for (int i = 0; i < CELL_SIZE; ++i) {
			for (int j = 0; j < CELL_SIZE; ++j) {
				if (i == 0 && j == 0) {
					map[0][0] = Cell.CELL.RED_ROBOT;
				} else if (i == 9 && j == 9) {
					map[9][9] = Cell.CELL.BLUE_ROBOT;
				} else {
					map[i][j] = CELL.NEUTRAL;
				}
			}
		}

		generateRocks();
	}

	public void generateRocks() {
		for (int i = 0; i < AMOUNT_OF_ROCKS; ++i) {
			Random rand = new Random();
			int x = rand.nextInt(CELL_SIZE);
			int y = rand.nextInt(CELL_SIZE);

			if (map[x][y] == Cell.CELL.NEUTRAL) {
				map[x][y] = Cell.CELL.ROCK;
			} else {
				i--;
			}
		}
	}

	public void step() {
		Method[] redMethods = redRobot.getMethods();
		Method[] blueMethods = blueRobot.getMethods();

		Action.ACTION redAction = null;
		Action.ACTION blueAction = null;

		Cell.CELL[] cells = radar();
		System.out.println("Left: " + cells[0]);
		System.out.println("Front: " + cells[1]);
		System.out.println("Right: " + cells[2]);
		for (int i = 0; i < redMethods.length; ++i) {
			if (redMethods[i].getName().equals("returnAction")) {
				try {
					redAction = (ACTION) redMethods[i].invoke(null, cells);
					System.out.println(redAction);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					blueAction = (ACTION) blueMethods[i].invoke(null, null);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int[] redRobotLocation = getRedRobotLocation();

				if (redAction == Action.ACTION.MOVE_FORWARD) {
					moveRedRobotForward();
				} else if (redAction == Action.ACTION.TURN_LEFT) {
					System.out.println("LEFT");
					if (redRobotDirection == 0) {
						redRobotDirection = 3;
					} else {
						redRobotDirection--;
					}
				} else if (redAction == Action.ACTION.TURN_RIGHT) {
					System.out.println("RIGHT");
					if (redRobotDirection == 3) {
						redRobotDirection = 0;
					} else {
						redRobotDirection++;
					}
				}
			}
		}
	}

	public void setRedRobot(Class<?> redRobot) {
		CellLogic.redRobot = redRobot;
	}

	public void setBlueRobot(Class<?> blueRobot) {
		this.blueRobot = blueRobot;
	}

	public int[] getRedRobotLocation() {
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				if (map[i][j] == Cell.CELL.RED_ROBOT) {
					return new int[] { i, j };
				}
			}
		}

		return new int[] { -1, -1 };
	}

	public int[] getBlueRobotLocation() {
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				if (map[i][j] == Cell.CELL.RED_ROBOT) {
					return new int[] { i, j };
				}
			}
		}

		return new int[] { -1, -1 };

	}

	public void moveRedRobotForward() {
		int[] redRobotLocation = getRedRobotLocation();
		int row = redRobotLocation[0];
		int col = redRobotLocation[1];

		if (redRobotDirection == 0 && row != 0 && map[row - 1][col] != Cell.CELL.ROCK) {
			map[row][col] = Cell.CELL.RED;
			map[row - 1][col] = Cell.CELL.RED_ROBOT;
		} else if (redRobotDirection == 1 && col != 9 && map[row][col + 1] != Cell.CELL.ROCK) {
			map[row][col] = Cell.CELL.RED;
			map[row][col + 1] = Cell.CELL.RED_ROBOT;
		} else if (redRobotDirection == 2 && row != 9 && map[row + 1][col] != Cell.CELL.ROCK) {
			map[row][col] = Cell.CELL.RED;
			map[row + 1][col] = Cell.CELL.RED_ROBOT;
		} else if (redRobotDirection == 3 && col != 0 && map[row][col - 1] != Cell.CELL.ROCK) {
			map[row][col] = Cell.CELL.RED;
			map[row][col - 1] = Cell.CELL.RED_ROBOT;
		} else {
			System.out.println("Fake News!");
		}
	}

	public void moveBlueRobotForward() {

	}

	public Cell.CELL[] radar() {
		int[] redRobotLocation = getRedRobotLocation();
		int row = redRobotLocation[0];
		int col = redRobotLocation[1];
		Cell.CELL[] cells = new Cell.CELL[3];

		System.out.println("Direction: " + redRobotDirection);
		if (redRobotDirection == 0) {
			for (int i = 0; i < cells.length; ++i) {
				switch (i) {
					case 0:
						System.out.println(row + " " + col);
						if (col - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row][col - 1] != null) {
							cells[i] = map[row][col - 1];
						} 
						break;
					case 1:
						if (row - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row - 1][col] != null) {
							cells[i] = map[row - 1][col];
						} 
						break;
					case 2:
						if (col + 1 > 9) {
							cells[i] = CELL.WALL;
						} else if (map[row][col + 1] != null) {
							cells[i] = map[row][col + 1];
						} 
						break;
				}
			}
		} else if (redRobotDirection == 1) {
			for (int i = 0; i < cells.length; ++i) {
				switch (i) {
					case 0:
						System.out.println(row + " " + col);
						if (col - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row][col - 1] != null) {
							cells[i] = map[row][col - 1];
						} 
						break;
					case 1:
						if (row - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row - 1][col] != null) {
							cells[i] = map[row - 1][col];
						} 
						break;
					case 2:
						if (col + 1 > 9) {
							cells[i] = CELL.WALL;
						} else if (map[row][col + 1] != null) {
							cells[i] = map[row][col + 1];
						} 
						break;
				}
			}
		} else if (redRobotDirection == 2) {
			for (int i = 0; i < cells.length; ++i) {
				switch (i) {
					case 0:
						System.out.println(row + " " + col);
						if (col - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row][col - 1] != null) {
							cells[i] = map[row][col - 1];
						} 
						break;
					case 1:
						if (row - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row - 1][col] != null) {
							cells[i] = map[row - 1][col];
						} 
						break;
					case 2:
						if (col + 1 > 9) {
							cells[i] = CELL.WALL;
						} else if (map[row][col + 1] != null) {
							cells[i] = map[row][col + 1];
						} 
						break;
				}
			}
		} else if (redRobotDirection == 3) {
			for (int i = 0; i < cells.length; ++i) {
				switch (i) {
					case 0:
						System.out.println(row + " " + col);
						if (col - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row][col - 1] != null) {
							cells[i] = map[row][col - 1];
						} 
						break;
					case 1:
						if (row - 1 < 0) {
							cells[i] = CELL.WALL;
						} else if (map[row - 1][col] != null) {
							cells[i] = map[row - 1][col];
						} 
						break;
					case 2:
						if (col + 1 > 9) {
							cells[i] = CELL.WALL;
						} else if (map[row][col + 1] != null) {
							cells[i] = map[row][col + 1];
						} 
						break;
				}
			}
		} 
		
		return cells;
	}
}
