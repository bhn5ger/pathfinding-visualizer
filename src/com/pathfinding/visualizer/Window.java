package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Window implements ActionListener{

	private JFrame frame;
	private JPanel controlPanel;
	public static PolarGrid polargrid;
	private JPanel polarGridBorder;
	private JLabel title;
	private JButton search;
	private JComboBox algorithms;

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
		
		algorithms = new JComboBox();
		algorithms.setBackground(Color.LIGHT_GRAY);
		algorithms.setBounds(38, 481, 126, 22);
		controlPanel.add(algorithms);
		
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
		if(e.getActionCommand().equals("Search")){
			Search s = new Search();
			
		}
	}
	
	
	
	

	
	
	
}
