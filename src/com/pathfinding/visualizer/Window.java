package com.pathfinding.visualizer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window{

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 776, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		PolarGrid polargrid = new PolarGrid();
		polargrid.addMouseListener(polargrid);
		polargrid.setBounds(207, 0, 564, 569);
		frame.getContentPane().add(polargrid);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 208, 569);
		frame.getContentPane().add(panel);
		
	}


	

	
}
