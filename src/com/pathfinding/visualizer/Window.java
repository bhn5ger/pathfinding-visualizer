package com.pathfinding.visualizer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Window {

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
		PolarGrid c = new PolarGrid();
		c.setBounds(207, 0, 564, 569);
		frame.getContentPane().add(c);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 208, 569);
		frame.getContentPane().add(panel);
	}
}
