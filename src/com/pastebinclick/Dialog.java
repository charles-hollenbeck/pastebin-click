package com.pastebinclick;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Dialog extends JDialog {

	private static final long serialVersionUID = 6222221866090613957L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Dialog(String text) {
		setResizable(false);
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						Dialog.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
		setTitle("Pastebin Upload");
		setBounds(100, 100, 330, 115);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblLabelText = new JLabel(text);
		contentPanel.setLayout(null);
		{
			lblLabelText.setBounds(0, 0, 324, 52);
			lblLabelText.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblLabelText);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
