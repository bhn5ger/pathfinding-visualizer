package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window implements ActionListener, ChangeListener{

	public static PolarGrid polargrid;
	private JFrame frame;
	private JPanel controlPanel, polarGridBorder, toolbox, console;
	private JLabel title, algoLabel, speedLbl;
	private JButton search, reset, generate;
	private JComboBox algorithms;
	private JRadioButton start, finish, wall, eraser;
	private JSlider speedSlider;
	private static JLabel checks, pathlen;
	private JLabel message;
	private ButtonGroup group;
	private int speed = 51;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		        
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Pathfinding Visualizer");
		frame.setBounds(100, 100, 840, 674);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		polargrid = new PolarGrid();
		polargrid.addMouseListener(polargrid);
		polargrid.addMouseMotionListener(polargrid);
		polargrid.setBounds(239, 47, 564, 569);
		frame.getContentPane().add(polargrid);
		polargrid.setLayout(null);
		
		controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Control Panel", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textText));
		controlPanel.setBounds(13, 217, 206, 403);
		frame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		search = new JButton("Search");
		search.setBackground(Color.LIGHT_GRAY);
		search.setBounds(38, 358, 126, 23);
		search.addActionListener(this);
		controlPanel.add(search);
		
		String [] searchOptions = {"Select...", "BFS", "DFS", "Dijkstra's"};
		algorithms = new JComboBox(searchOptions);
		algorithms.setBackground(Color.LIGHT_GRAY);
		algorithms.setBounds(38, 271, 126, 22);
		algorithms.addActionListener(this);
		controlPanel.add(algorithms);
		
		toolbox = new JPanel();
		toolbox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Toolbox", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		toolbox.setBounds(36, 107, 130, 126);
		controlPanel.add(toolbox);
		toolbox.setLayout(null);
		
		start = new JRadioButton("Start");
		start.setBounds(11, 18, 109, 23);
		start.addActionListener(this);
		toolbox.add(start);
		
		finish = new JRadioButton("Finish");
		finish.setBounds(11, 44, 109, 23);
		finish.addActionListener(this);
		toolbox.add(finish);
		
		wall = new JRadioButton("Wall");
		wall.setBounds(11, 70, 109, 23);
		wall.addActionListener(this);
		toolbox.add(wall);
		
		eraser = new JRadioButton("Eraser");
		eraser.setBounds(11, 96, 109, 23);
		eraser.addActionListener(this);
		toolbox.add(eraser);
		
		group = new ButtonGroup();
		group.add(start);
		group.add(finish);
		group.add(wall);
		group.add(eraser);
		
		reset = new JButton("Clear Map");
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setBounds(38, 29, 126, 23);
		reset.addActionListener(this);
		controlPanel.add(reset);
		
		generate = new JButton("Generate Map");
		generate.setBackground(Color.LIGHT_GRAY);
		generate.setBounds(38, 63, 126, 23);
		generate.addActionListener(this);
		controlPanel.add(generate);
		
		algoLabel = new JLabel("Algorithm:");
		algoLabel.setBounds(38, 253, 126, 14);
		controlPanel.add(algoLabel);
		
		speedSlider = new JSlider();
		speedSlider.setBounds(32, 320, 139, 26);
		speedSlider.setMinimum(1);
		speedSlider.setMaximum(100);
		speedSlider.setValue(50);
		speedSlider.addChangeListener(this);
		controlPanel.add(speedSlider);
		
		speedLbl = new JLabel("Animation Speed:");
		speedLbl.setBounds(38, 304, 126, 14);
		controlPanel.add(speedLbl);
		
		polarGridBorder = new JPanel();
		polarGridBorder.setBorder(new LineBorder(new Color(0, 0, 0)));
		polarGridBorder.setBounds(229, 45, 576, 573);
		frame.getContentPane().add(polarGridBorder);
		polarGridBorder.setLayout(null); 
		
		title = new JLabel("Polar Pathfinding Visualizer");
		title.setFont(new Font("Tahoma", Font.BOLD, 14));
		title.setBounds(17, 7, 275, 24);
		frame.getContentPane().add(title);
		
		console = new JPanel();
		console.setBounds(13, 37, 206, 176);
		frame.getContentPane().add(console);
		console.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Console", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		console.setLayout(null);
		
		String mess = "<html> Generate a map or create your <br/> own map with a start and finish, <br/> and then select an algorithm to begin searching. </html>";
		message = new JLabel(mess);
		message.setBounds(11, 23, 186, 64);
		console.add(message);
		
		checks = new JLabel("Checks: 0 ");
		checks.setBounds(57, 126, 94, 14);
		console.add(checks);
		
		pathlen = new JLabel("Path Length: 0 ");
		pathlen.setBounds(57, 145, 94, 14);
		console.add(pathlen);
		
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Search") && Search.isSearching == false && polargrid.hasStartAndFinish()){
			checks.setText("Checks: " + 0 + " ");
			pathlen.setText("Path Length: " + 0 + " ");
			Search s = new Search(String.valueOf(algorithms.getSelectedItem()), speed);
			
		}
		if(e.getActionCommand().equals("Clear Map") && Search.isSearching == false){
			polargrid.reset();
			polargrid.setCompleteGrid(false);
			checks.setText("Checks: " + 0 + " ");
			pathlen.setText("Path Length: " + 0 + " ");
			
		}
		if(e.getActionCommand().equals("Generate Map") && Search.isSearching == false){
			polargrid.generateMap();
			polargrid.setCompleteGrid(false);
			checks.setText("Checks: " + 0 + " ");
			pathlen.setText("Path Length: " + 0 + " ");
			
		}
		if(e.getSource() == wall) {
			polargrid.setToolboxClicked(true);
			polargrid.setMouseColor(Color.black);
		}
		if(e.getSource() == eraser) {
			polargrid.setToolboxClicked(true);
			polargrid.setMouseColor(new Color(240,240,240));
		}
		if(e.getSource() == start) {
			polargrid.setToolboxClicked(true);
			polargrid.setMouseColor(Color.green);
		}
		if(e.getSource() == finish) {
			polargrid.setToolboxClicked(true);
			polargrid.setMouseColor(Color.red);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		speed = 101 - source.getValue();
		
	}
	
	
	public static void setChecks(int n) {
		checks.setText("Checks: " + n + " ");
	}
	public static void setPathlen(int n) {
		pathlen.setText("Path Length: " + n + " ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
