package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entities.Cell;
import logic.CellLogic;

public class Drawing extends JPanel {
	private static final long serialVersionUID = -3947244960337503271L;
	CellLogic cl = new CellLogic();
	
	public void paintComponent(Graphics g) {
		cl.setCellLogic(cl);
		
		Cell.CELL[][] map = cl.getMap();
		
		for(int row = 0; row < cl.getCellSize(); ++row) {
			for (int col = 0; col < cl.getCellSize(); ++col) {
				int x = cl.getStartingCellX() + cl.getCellLength()*row + 1;
				int y = cl.getStartingCellY() + cl.getCellLength()*col + 1;
				
				if (map[row][col] == Cell.CELL.ROCK) {
					g.setColor(Color.BLACK);
				} else if (map[row][col] == Cell.CELL.BLUE_ROBOT) {
					g.setColor(new Color(0,0,100));
				} else if (map[row][col] == Cell.CELL.RED_ROBOT) {
					g.setColor(new Color(100,0,0));
				} else if (map[row][col] == Cell.CELL.RED) {
					g.setColor(new Color(255,0,0));
				} else if (map[row][col] == Cell.CELL.BLUE) {
					g.setColor(new Color(0,0,255));
				} else {
					g.setColor(Color.GRAY);
				}
				
				g.fillRect(y, x, cl.getCellLength() - 1, cl.getCellLength() - 1);
			}
		}
	}
	
	public void setRedRobot(Class<?> redRobot) {
		cl.setRedRobot(redRobot);
	}
	
	public void setBlueRobot(Class<?> blueRobot) {
		cl.setBlueRobot(blueRobot);
	}
	
	public void nextStep() {
		cl.step();
		this.repaint();
	}
	
	public void reset() {
		cl.generateMap();
		this.repaint();
	}
}
