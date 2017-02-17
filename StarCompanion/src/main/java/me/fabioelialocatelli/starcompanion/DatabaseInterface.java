package me.fabioelialocatelli.starcompanion;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DatabaseInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel = null;
	private String databasePath = null;
	private JComboBox<String> denominationSelector = null;
	private JComboBox<String> apparentMagnitudeSelector = null;
	private JComboBox<String> designationSelector = null;
	private JComboBox<String> distanceSelector = null;
	private JComboBox<String> solarDiameterSelector = null;
	private JComboBox<String> solarLuminositySelector = null;
	private JComboBox<String> gregorianYearSelector = null;
	private JPanel contentPane = null;
	private JTable recordTable = null;
	private JTextField designationField = null;
	private JScrollPane scrollPane = null;
	private DatabaseBrowser databaseBrowserInstance = null;
	private InterfaceManipulator interfaceManipulatorInstance = null;

	/**
	 * Create the frame.
	 */
	public DatabaseInterface(Integer mode) {
		setTitle("Data Browser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1250, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		databasePath = "C:\\Program Files\\StarCompanion\\resources\\starCompanion.db";
		databaseBrowserInstance = new DatabaseBrowser(databasePath);
		databaseBrowserInstance.anchorDatabase();

		denominationSelector = new JComboBox<String>();
		denominationSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		denominationSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_denominationSelector = new GridBagConstraints();
		gbc_denominationSelector.insets = new Insets(0, 0, 5, 5);
		gbc_denominationSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_denominationSelector.gridx = 1;
		gbc_denominationSelector.gridy = 0;
		contentPane.add(denominationSelector, gbc_denominationSelector);

		apparentMagnitudeSelector = new JComboBox<String>();
		apparentMagnitudeSelector
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		apparentMagnitudeSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_apparentMagnitudeSelector = new GridBagConstraints();
		gbc_apparentMagnitudeSelector.insets = new Insets(0, 0, 5, 5);
		gbc_apparentMagnitudeSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeSelector.gridx = 1;
		gbc_apparentMagnitudeSelector.gridy = 1;
		contentPane.add(apparentMagnitudeSelector, gbc_apparentMagnitudeSelector);

		designationSelector = new JComboBox<String>();
		designationSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		designationSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_designationSelector = new GridBagConstraints();
		gbc_designationSelector.insets = new Insets(0, 0, 5, 5);
		gbc_designationSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_designationSelector.gridx = 1;
		gbc_designationSelector.gridy = 2;
		contentPane.add(designationSelector, gbc_designationSelector);

		distanceSelector = new JComboBox<String>();
		distanceSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		distanceSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_distanceSelector = new GridBagConstraints();
		gbc_distanceSelector.insets = new Insets(0, 0, 5, 5);
		gbc_distanceSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceSelector.gridx = 1;
		gbc_distanceSelector.gridy = 3;
		contentPane.add(distanceSelector, gbc_distanceSelector);

		solarDiameterSelector = new JComboBox<String>();
		solarDiameterSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		solarDiameterSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_solarDiameterSelector = new GridBagConstraints();
		gbc_solarDiameterSelector.insets = new Insets(0, 0, 5, 5);
		gbc_solarDiameterSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarDiameterSelector.gridx = 1;
		gbc_solarDiameterSelector.gridy = 4;
		contentPane.add(solarDiameterSelector, gbc_solarDiameterSelector);

		solarLuminositySelector = new JComboBox<String>();
		solarLuminositySelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		solarLuminositySelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_solarLuminositySelector = new GridBagConstraints();
		gbc_solarLuminositySelector.insets = new Insets(0, 0, 5, 5);
		gbc_solarLuminositySelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarLuminositySelector.gridx = 1;
		gbc_solarLuminositySelector.gridy = 5;
		contentPane.add(solarLuminositySelector, gbc_solarLuminositySelector);

		gregorianYearSelector = new JComboBox<String>();
		gregorianYearSelector.setModel(new DefaultComboBoxModel<String>(new String[] { "Ascendent", "Descendent" }));
		gregorianYearSelector.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_gregorianYearSelector = new GridBagConstraints();
		gbc_gregorianYearSelector.insets = new Insets(0, 0, 5, 5);
		gbc_gregorianYearSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_gregorianYearSelector.gridx = 1;
		gbc_gregorianYearSelector.gridy = 6;
		contentPane.add(gregorianYearSelector, gbc_gregorianYearSelector);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		recordTable = new JTable();
		recordTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(recordTable);

		JButton denominationButton = new JButton("Denomination");
		denominationButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		denominationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("denomination",
						denominationSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_denominationButton = new GridBagConstraints();
		gbc_denominationButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_denominationButton.insets = new Insets(0, 0, 5, 5);
		gbc_denominationButton.gridx = 0;
		gbc_denominationButton.gridy = 0;
		contentPane.add(denominationButton, gbc_denominationButton);

		JButton designationButton = new JButton("Designation");
		designationButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		designationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("designation",
						designationSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_designationButton = new GridBagConstraints();
		gbc_designationButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_designationButton.insets = new Insets(0, 0, 5, 5);
		gbc_designationButton.gridx = 0;
		gbc_designationButton.gridy = 2;
		contentPane.add(designationButton, gbc_designationButton);

		JButton apparentMagnitudeButton = new JButton("Apparent Magnitude");
		apparentMagnitudeButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		apparentMagnitudeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("apparentMagnitude",
						apparentMagnitudeSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_apparentMagnitudeButton = new GridBagConstraints();
		gbc_apparentMagnitudeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeButton.insets = new Insets(0, 0, 5, 5);
		gbc_apparentMagnitudeButton.gridx = 0;
		gbc_apparentMagnitudeButton.gridy = 1;
		contentPane.add(apparentMagnitudeButton, gbc_apparentMagnitudeButton);

		JButton distanceButton = new JButton("Distance");
		distanceButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		distanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("parsecs",
						distanceSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_distanceButton = new GridBagConstraints();
		gbc_distanceButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceButton.insets = new Insets(0, 0, 5, 5);
		gbc_distanceButton.gridx = 0;
		gbc_distanceButton.gridy = 3;
		contentPane.add(distanceButton, gbc_distanceButton);

		JButton diameterButton = new JButton("Solar Diameter");
		diameterButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		diameterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("solarDiameter",
						solarDiameterSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		contentPane.add(distanceSelector, gbc_distanceSelector);
		GridBagConstraints gbc_diameterButton = new GridBagConstraints();
		gbc_diameterButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_diameterButton.insets = new Insets(0, 0, 5, 5);
		gbc_diameterButton.gridx = 0;
		gbc_diameterButton.gridy = 4;
		contentPane.add(diameterButton, gbc_diameterButton);

		JButton luminosityButton = new JButton("Solar Luminosity");
		luminosityButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		luminosityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("bolometricLuminosity",
						solarLuminositySelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_luminosityButton = new GridBagConstraints();
		gbc_luminosityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_luminosityButton.insets = new Insets(0, 0, 5, 5);
		gbc_luminosityButton.gridx = 0;
		gbc_luminosityButton.gridy = 5;
		contentPane.add(luminosityButton, gbc_luminosityButton);

		JButton constellationButton = new JButton("Constellation");
		constellationButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		constellationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String latinDesignation = designationField.getText();
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayConstellation(latinDesignation);
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_constellationButton = new GridBagConstraints();
		gbc_constellationButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_constellationButton.insets = new Insets(0, 0, 0, 5);
		gbc_constellationButton.gridx = 0;
		gbc_constellationButton.gridy = 7;
		contentPane.add(constellationButton, gbc_constellationButton);

		JButton gregorianYearButton = new JButton("Gregorian Year");
		gregorianYearButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		gregorianYearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Vector<Object>> databaseObjects = databaseBrowserInstance.displayStars("gregorianYear",
						gregorianYearSelector.getSelectedIndex());
				tableModel = new DefaultTableModel(databaseObjects.get(0), databaseObjects.get(1));
				recordTable.setModel(tableModel);
			}
		});
		GridBagConstraints gbc_gregorianYearButton = new GridBagConstraints();
		gbc_gregorianYearButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_gregorianYearButton.insets = new Insets(0, 0, 5, 5);
		gbc_gregorianYearButton.gridx = 0;
		gbc_gregorianYearButton.gridy = 6;
		contentPane.add(gregorianYearButton, gbc_gregorianYearButton);

		designationField = new JTextField();
		designationField.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_designationField = new GridBagConstraints();
		gbc_designationField.insets = new Insets(0, 0, 0, 5);
		gbc_designationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_designationField.gridx = 1;
		gbc_designationField.gridy = 7;
		contentPane.add(designationField, gbc_designationField);
		designationField.setColumns(10);

		if (mode == 1) {
			interfaceManipulatorInstance = new InterfaceManipulator(contentPane);
			interfaceManipulatorInstance.nightMode();
		}
	}
}
