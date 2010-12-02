package com.andreas.pfi2examples.swingprocessing;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private MyProcessing sketch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Processing in Swing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		sketch = new MyProcessing();
		sketch.init();
		GridBagConstraints gbc_sketch = new GridBagConstraints();
		gbc_sketch.insets = new Insets(0, 0, 5, 0);
		gbc_sketch.fill = GridBagConstraints.BOTH;
		gbc_sketch.gridx = 0;
		gbc_sketch.gridy = 0;
		contentPane.add(sketch, gbc_sketch);

		JButton btnClearAllItems = new JButton("Clear all items");
		btnClearAllItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Clear the items list inside the Processing sketch */
				getSketch().clearList();
			}
		});
		GridBagConstraints gbc_btnClearAllItems = new GridBagConstraints();
		gbc_btnClearAllItems.gridx = 0;
		gbc_btnClearAllItems.gridy = 1;
		contentPane.add(btnClearAllItems, gbc_btnClearAllItems);
	}

	public MyProcessing getSketch() {
		return sketch;
	}
}
