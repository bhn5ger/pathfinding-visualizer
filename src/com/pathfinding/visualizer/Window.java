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

public class Window implements ActionListener{

	public static PolarGrid polargrid;
	private JFrame frame;
	private JPanel controlPanel, polarGridBorder, toolbox;
	private JLabel title, algoLabel, speedLbl;
	private JButton search, reset, generate;
	private JComboBox algorithms;
	private JRadioButton start, finish, wall, eraser;
	private JSlider speedSlider;

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
		frame.setBounds(100, 100, 840, 672);
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
		controlPanel.setBounds(14, 37, 206, 583);
		frame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		search = new JButton("Search");
		search.setBackground(Color.LIGHT_GRAY);
		search.setBounds(38, 531, 126, 23);
		search.addActionListener(this);
		controlPanel.add(search);
		
		String [] searchOptions = {"Select...", "BFS"};
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
		
		ButtonGroup group = new ButtonGroup();
		group.add(start);
		group.add(finish);
		group.add(wall);
		group.add(eraser);
		
		reset = new JButton("Reset");
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setBounds(38, 29, 126, 23);
		reset.addActionListener(this);
		controlPanel.add(reset);
		
		generate = new JButton("Generate Map");
		generate.setBackground(Color.LIGHT_GRAY);
		generate.setBounds(38, 63, 126, 23);
		controlPanel.add(generate);
		
		algoLabel = new JLabel("Algorithm:");
		algoLabel.setBounds(38, 253, 126, 14);
		controlPanel.add(algoLabel);
		
		speedSlider = new JSlider();
		speedSlider.setBounds(32, 321, 139, 26);
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
		
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Search") && Search.isSearching == false){
			Search s = new Search(String.valueOf(algorithms.getSelectedItem()));
			
		}
		if(e.getActionCommand().equals("Reset") && Search.isSearching == false){
			polargrid.reset();
			
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
}
