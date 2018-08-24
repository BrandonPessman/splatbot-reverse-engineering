package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {
	private static final long serialVersionUID = -8963773059923499362L;
	JLabel redScore, blueScore;
	private static Class<?> redRobot;
	private static Class<?> blueRobot;
	
	public Window(int width, int height, String title, Class<?> redRobot, Class<?> blueRobot) {
		Window.redRobot = redRobot;
		Window.blueRobot = blueRobot;
		
		this.setTitle(title);
		
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		render();
	}
	
	public void render() {
		Drawing drawing = new Drawing();
		drawing.setRedRobot(redRobot);
		drawing.setBlueRobot(blueRobot);
		drawing.reset();
		
		JPanel drawingPanel = drawing;
		drawingPanel.setPreferredSize(new Dimension(400,525));
		drawingPanel.setBackground(Color.WHITE);
		this.add(drawingPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.LIGHT_GRAY);
		this.add(centerPanel, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.LIGHT_GRAY);
		this.add(southPanel, BorderLayout.SOUTH);
		
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(e -> {
			drawing.nextStep();
			updateScore();
		});
		southPanel.add(stepButton);
		
		JButton runButton = new JButton("Run");
		southPanel.add(runButton);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(e -> {
			drawing.setRedRobot(redRobot);
			drawing.setBlueRobot(blueRobot);
			drawing.reset();
			drawingPanel.repaint();
		});
		southPanel.add(newGameButton);
		
		redScore = new JLabel("RED Score: 0");
		centerPanel.add(redScore);
		
		JLabel spacer = new JLabel("                                                              ");
		centerPanel.add(spacer); 
		
		blueScore = new JLabel("BLUE Score: 0");
		centerPanel.add(blueScore);
	}
	
	public void updateScore() {
		redScore.setText("0");
		blueScore.setText("1");
	}
}
