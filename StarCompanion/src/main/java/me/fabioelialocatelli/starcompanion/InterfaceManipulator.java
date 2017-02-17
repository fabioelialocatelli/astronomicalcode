package me.fabioelialocatelli.starcompanion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class InterfaceManipulator {
	private JPanel contentPane = null;

	public InterfaceManipulator(JPanel applicationPanel) {
		this.contentPane = applicationPanel;
	}

	public void nightMode() {
		contentPane.setForeground(new Color(204, 0, 0));
		contentPane.setBackground(new Color(15, 15, 10));
		for (Component frameComponent : contentPane.getComponents()) {
			if (frameComponent instanceof JTextField) {
				JTextComponent frameField = (JTextComponent) frameComponent;
				frameField.setBackground(new Color(15, 15, 10));
				frameField.setForeground(new Color(204, 0, 0));
				frameField.setBorder(new LineBorder(new Color(204, 0, 0)));
			} else if (frameComponent instanceof JButton) {
				JButton frameButton = (JButton) frameComponent;
				frameButton.setForeground(new Color(204, 0, 0));
				frameButton.setBackground(new Color(15, 15, 10));
				frameButton.setBorder(new LineBorder(new Color(204, 0, 0)));
			} else if (frameComponent instanceof JComboBox) {
				JComboBox<?> frameList = (JComboBox<?>) frameComponent;
				frameList.setForeground(new Color(204, 0, 0));
				frameList.setBackground(new Color(15, 15, 10));
				frameList.setBorder(new LineBorder(new Color(204, 0, 0)));
			} else if (frameComponent instanceof JScrollPane) {
				JScrollPane frameScroll = (JScrollPane) frameComponent;
				frameScroll.getViewport().setForeground(new Color(204, 0, 0));
				frameScroll.getViewport().setBackground(new Color(15, 15, 10));
			} else {
				frameComponent.setForeground(new Color(204, 0, 0));
				frameComponent.setBackground(new Color(15, 15, 10));
			}
		}
	}

	public void defaultMode() {
		contentPane.setForeground(null);
		contentPane.setBackground(null);
		for (Component frameComponent : contentPane.getComponents()) {
			if (frameComponent instanceof JTextField) {
				JTextComponent frameField = (JTextComponent) frameComponent;
				frameField.setBackground(new Color(255, 255, 255));
				frameField.setBorder(UIManager.getBorder("TextField.border"));
				frameField.setText("");
			} else if (frameComponent instanceof JButton) {
				JButton frameButton = (JButton) frameComponent;
				frameButton.setForeground(null);
				frameButton.setBackground(null);
				frameButton.setBorder(UIManager.getBorder("Button.border"));
			} else if (frameComponent instanceof JComboBox) {
				JComboBox<?> frameList = (JComboBox<?>) frameComponent;
				frameList.setForeground(null);
				frameList.setBackground(null);
				frameList.setBorder(UIManager.getBorder("ComboBox.border"));
				frameList.setSelectedIndex(0);
			} else if (frameComponent instanceof JScrollPane) {
				JScrollPane frameScroll = (JScrollPane) frameComponent;
				frameScroll.getViewport().setForeground(null);
				frameScroll.getViewport().setBackground(null);
			} else {
				frameComponent.setForeground(null);
				frameComponent.setBackground(null);
			}
		}
	}
}
