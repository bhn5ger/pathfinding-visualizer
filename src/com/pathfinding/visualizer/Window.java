package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class Window implements ActionListener, ChangeListener, ItemListener{

	public static PolarGrid polargrid;
	private JFrame frame;
	private JPanel controlPanel, polarGridBorder, toolbox, console;
	private JLabel title, algoLabel, speedLbl;
	private JButton search, reset, generate;
	private JComboBox algorithms;
	private JRadioButton start, finish, wall, eraser;
	private JSlider speedSlider;
	private static JLabel checks, pathlen, weightedPathlen;
	private JLabel message;
	private ButtonGroup group;
	private int speed = 51;
	public static final String [] searchOptions = {"Select...", "BFS (unweighted)", "DFS (unweighted)", "Dijkstra's (weighted)"};
	public static final String [] messages = {"<html> <p style=\"text-align:center\"> Generate a map or create your own map with a start and finish, then select an algorithm to begin searching. </p> </html>",
											 "<html> <p style=\"text-align:center\"> Breadth-first Search is unweighted and guarantees the shortest unweighted path. </p> </html>",
											 "<html> <p style=\"text-align:center\"> Depth-first Search is unweighted and does not guarantee the shortest unweighted or weighted path. </p> </html>",
											 "<html> <p style=\"text-align:center\"> Dijkstra's Algorithm is weighted and guarantees the shortest weighted path. </p> </html>"};

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
		frame.setBounds(500, 150, 840, 674);
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
		
		algorithms = new JComboBox(searchOptions);
		algorithms.setBackground(Color.LIGHT_GRAY);
		algorithms.setBounds(38, 271, 126, 22);
		algorithms.addActionListener(this);
		algorithms.addItemListener(this);
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
		
		message = new JLabel(messages[0]);
		message.setBounds(11, 23, 186, 64);
		console.add(message);
		
		checks = new JLabel("Checks: 0 ");
		checks.setBounds(74, 113, 94, 14);
		console.add(checks);
		
		pathlen = new JLabel("Unweighted Path Length: 0 ");
		pathlen.setBounds(28, 132, 180, 14);
		console.add(pathlen);
		
		weightedPathlen = new JLabel("Weighted Path Length: 0.0 ");
		weightedPathlen.setBounds(28, 151, 186, 14);
		console.add(weightedPathlen);
		
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Search") && Search.isSearching == false && polargrid.hasStartAndFinish()){
			if(algorithms.getSelectedItem().equals(searchOptions[0])) {setMessage(messages[0]);}
			if(algorithms.getSelectedItem().equals(searchOptions[1])) {setMessage(messages[1]);}
			if(algorithms.getSelectedItem().equals(searchOptions[2])) {setMessage(messages[2]);}
			if(algorithms.getSelectedItem().equals(searchOptions[3])) {setMessage(messages[3]);}
			resetConsole();
			Search s = new Search(String.valueOf(algorithms.getSelectedItem()), speed);
			
		}
		if(e.getActionCommand().equals("Clear Map") && Search.isSearching == false){
			polargrid.reset();
			polargrid.setCompleteGrid(false);
			setMessage(messages[0]);
			resetConsole();
			
		}
		if(e.getActionCommand().equals("Generate Map") && Search.isSearching == false){
			polargrid.generateMap();
			polargrid.setCompleteGrid(false);
			setMessage(messages[0]);
			resetConsole();
			
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
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(Search.isSearching == false) {
				Object item = e.getItem();
				if(item.equals(searchOptions[0])) {setMessage(messages[0]);}
				if(item.equals(searchOptions[1])) {setMessage(messages[1]);}
				if(item.equals(searchOptions[2])) {setMessage(messages[2]);}
				if(item.equals(searchOptions[3])) {setMessage(messages[3]);}
			}
	    }
		
	}
	
	public void resetConsole() {
		setChecks(0);
		setPathlen(0);
		setWeightedPathlen(0);
	}
	
	public void setMessage(String m) {
		message.setText(m);
	}
	public static void setChecks(int n) {
		checks.setText("Checks: " + n + " ");
	}
	public static void setPathlen(int n) {
		pathlen.setText("Unweighted Path Length: " + n + " ");
	}
	public static void setWeightedPathlen(double n) {
		weightedPathlen.setText("Weighted Path Length: " + n + " ");
	}


	
	
	
}
