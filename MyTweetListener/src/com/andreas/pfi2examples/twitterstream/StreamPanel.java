package com.andreas.pfi2examples.twitterstream;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StreamPanel extends JPanel {
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public StreamPanel() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Twitter Stream", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
