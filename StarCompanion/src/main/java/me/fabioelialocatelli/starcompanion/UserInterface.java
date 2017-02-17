package me.fabioelialocatelli.starcompanion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JComboBox<String> distanceUnitSelection = null;
	private JComboBox<String> databaseSystemSelection = null;
	private JPanel contentPane = null;
	private JTextField spectralParametersClass = null;
	private JTextField spectralParametersCorrection = null;
	private JTextField spectralParametersTemperature = null;
	private JTextField innerBoundary = null;
	private JTextField bolometricLuminosityInput = null;
	private JTextField absoluteLuminosityInput = null;
	private JTextField bolometricMagnitudeInput = null;
	private JTextField absoluteMagnitudeInput = null;
	private JTextField apparentMagnitudeInput = null;
	private JTextField solarDiameterTemperature = null;
	private JTextField outerBoundary = null;
	private JTextField bolometricLuminosityResult = null;
	private JTextField absoluteLuminosityResult = null;
	private JTextField bolometricMagnitudeCorrection = null;
	private JTextField absoluteMagnitudeParsecs = null;
	private JTextField apparentMagnitudeParsecs = null;
	private JTextField solarDiameterMagnitude = null;
	private JTextField bolometricMagnitudeResult = null;
	private JTextField absoluteMagnitudeResult = null;
	private JTextField apparentMagnitudeResult = null;
	private JTextField solarDiameterResult = null;
	private JTextField gregorianYear = null;
	private JTextField distanceConversionInput = null;
	private JTextField distanceConversionOutput = null;
	private JTextField stellarDenominationField = null;
	private JTextField descriptionOutcome = null;

	private String databasePath = null;
	private String urlStar = null;
	private String xmlStar = null;
	private String astronomicWebsite = null;
	private String datasetsDirectory = null;
	private String databaseOption = null;
	private String descriptionsDirectory = null;
	private String mysqlDirectory = null;
	private String oracleDirectory = null;
	private String resourcesDirectory = null;
	private SpectralClass spectralClassInstance = null;
	private StellarCalculator stellarCalculatorInstance = null;

	/**
	 * Create the frame.
	 */
	public UserInterface() {
		setTitle("Star Companion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmNightView = new JMenuItem("Night View");
		mntmNightView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InterfaceManipulator interfaceManipulatorInstance = new InterfaceManipulator(contentPane);
				interfaceManipulatorInstance.nightMode();
			}
		});
		mnOptions.add(mntmNightView);

		JMenuItem mntmOpenDatabase = new JMenuItem("Open Database");
		mntmOpenDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Color backgroundColor = contentPane.getBackground();

				if (backgroundColor.getRGB() == -1118482) {
					DatabaseInterface databaseFrame = new DatabaseInterface(0);
					databaseFrame.setVisible(true);
				} else if (backgroundColor.getRGB() == -15790326) {
					DatabaseInterface databaseFrame = new DatabaseInterface(1);
					databaseFrame.setVisible(true);
				}
			}
		});
		mnOptions.add(mntmOpenDatabase);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		databasePath = "C:\\Program Files\\StarCompanion\\resources\\starCompanion.db";
		resourcesDirectory = "C:\\Program Files\\StarCompanion\\resources\\";
		datasetsDirectory = "C:\\ProgramData\\StarCompanion\\datasets\\";
		descriptionsDirectory = "C:\\ProgramData\\StarCompanion\\descriptions\\";

		mysqlDirectory = datasetsDirectory + "MySQL";
		oracleDirectory = datasetsDirectory + "Oracle";

		spectralClassInstance = new SpectralClass(databasePath);
		stellarCalculatorInstance = new StellarCalculator();
		spectralClassInstance.anchorDatabase();

		distanceUnitSelection = new JComboBox<>();
		distanceUnitSelection.setFont(new Font("Verdana", Font.PLAIN, 15));
		distanceUnitSelection.setModel(new DefaultComboBoxModel<String>(new String[] { "Light Years", "Parsecs" }));
		GridBagConstraints gbc_distanceUnitSelection = new GridBagConstraints();
		gbc_distanceUnitSelection.insets = new Insets(0, 0, 5, 5);
		gbc_distanceUnitSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceUnitSelection.gridx = 1;
		gbc_distanceUnitSelection.gridy = 8;
		contentPane.add(distanceUnitSelection, gbc_distanceUnitSelection);

		databaseSystemSelection = new JComboBox<>();
		databaseSystemSelection.setFont(new Font("Verdana", Font.PLAIN, 15));
		databaseSystemSelection.setModel(new DefaultComboBoxModel<String>(new String[] { "MySQL", "Oracle" }));
		GridBagConstraints gbc_databaseSystemSelection = new GridBagConstraints();
		gbc_databaseSystemSelection.insets = new Insets(0, 0, 0, 5);
		gbc_databaseSystemSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_databaseSystemSelection.gridx = 1;
		gbc_databaseSystemSelection.gridy = 9;
		contentPane.add(databaseSystemSelection, gbc_databaseSystemSelection);

		spectralParametersClass = new JTextField();
		spectralParametersClass.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_spectralParametersClass = new GridBagConstraints();
		gbc_spectralParametersClass.insets = new Insets(0, 0, 5, 5);
		gbc_spectralParametersClass.fill = GridBagConstraints.HORIZONTAL;
		gbc_spectralParametersClass.gridx = 2;
		gbc_spectralParametersClass.gridy = 0;
		contentPane.add(spectralParametersClass, gbc_spectralParametersClass);
		spectralParametersClass.setColumns(10);

		spectralParametersCorrection = new JTextField();
		spectralParametersCorrection.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_spectralParametersCorrection = new GridBagConstraints();
		gbc_spectralParametersCorrection.insets = new Insets(0, 0, 5, 5);
		gbc_spectralParametersCorrection.fill = GridBagConstraints.HORIZONTAL;
		gbc_spectralParametersCorrection.gridx = 4;
		gbc_spectralParametersCorrection.gridy = 0;
		contentPane.add(spectralParametersCorrection, gbc_spectralParametersCorrection);
		spectralParametersCorrection.setColumns(10);

		spectralParametersTemperature = new JTextField();
		spectralParametersTemperature.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_spectralParametersTemperature = new GridBagConstraints();
		gbc_spectralParametersTemperature.insets = new Insets(0, 0, 5, 0);
		gbc_spectralParametersTemperature.fill = GridBagConstraints.HORIZONTAL;
		gbc_spectralParametersTemperature.gridx = 6;
		gbc_spectralParametersTemperature.gridy = 0;
		contentPane.add(spectralParametersTemperature, gbc_spectralParametersTemperature);
		spectralParametersTemperature.setColumns(10);

		innerBoundary = new JTextField();
		innerBoundary.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_innerBoundary = new GridBagConstraints();
		gbc_innerBoundary.insets = new Insets(0, 0, 5, 5);
		gbc_innerBoundary.fill = GridBagConstraints.HORIZONTAL;
		gbc_innerBoundary.gridx = 2;
		gbc_innerBoundary.gridy = 1;
		contentPane.add(innerBoundary, gbc_innerBoundary);
		innerBoundary.setColumns(10);

		outerBoundary = new JTextField();
		outerBoundary.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_outerBoundary = new GridBagConstraints();
		gbc_outerBoundary.insets = new Insets(0, 0, 5, 5);
		gbc_outerBoundary.fill = GridBagConstraints.HORIZONTAL;
		gbc_outerBoundary.gridx = 4;
		gbc_outerBoundary.gridy = 1;
		contentPane.add(outerBoundary, gbc_outerBoundary);
		outerBoundary.setColumns(10);

		gregorianYear = new JTextField();
		gregorianYear.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_gregorianYear = new GridBagConstraints();
		gbc_gregorianYear.insets = new Insets(0, 0, 5, 0);
		gbc_gregorianYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_gregorianYear.gridx = 6;
		gbc_gregorianYear.gridy = 1;
		contentPane.add(gregorianYear, gbc_gregorianYear);
		gregorianYear.setColumns(10);

		bolometricLuminosityInput = new JTextField();
		bolometricLuminosityInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_bolometricLuminosityInput = new GridBagConstraints();
		gbc_bolometricLuminosityInput.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricLuminosityInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricLuminosityInput.gridx = 2;
		gbc_bolometricLuminosityInput.gridy = 2;
		contentPane.add(bolometricLuminosityInput, gbc_bolometricLuminosityInput);
		bolometricLuminosityInput.setColumns(10);

		bolometricLuminosityResult = new JTextField();
		bolometricLuminosityResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_bolometricLuminosityResult = new GridBagConstraints();
		gbc_bolometricLuminosityResult.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricLuminosityResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricLuminosityResult.gridx = 4;
		gbc_bolometricLuminosityResult.gridy = 2;
		contentPane.add(bolometricLuminosityResult, gbc_bolometricLuminosityResult);
		bolometricLuminosityResult.setColumns(10);

		absoluteLuminosityInput = new JTextField();
		absoluteLuminosityInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_absoluteLuminosityInput = new GridBagConstraints();
		gbc_absoluteLuminosityInput.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteLuminosityInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteLuminosityInput.gridx = 2;
		gbc_absoluteLuminosityInput.gridy = 3;
		contentPane.add(absoluteLuminosityInput, gbc_absoluteLuminosityInput);
		absoluteLuminosityInput.setColumns(10);

		absoluteLuminosityResult = new JTextField();
		absoluteLuminosityResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_absoluteLuminosityResult = new GridBagConstraints();
		gbc_absoluteLuminosityResult.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteLuminosityResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteLuminosityResult.gridx = 4;
		gbc_absoluteLuminosityResult.gridy = 3;
		contentPane.add(absoluteLuminosityResult, gbc_absoluteLuminosityResult);
		absoluteLuminosityResult.setColumns(10);

		bolometricMagnitudeInput = new JTextField();
		bolometricMagnitudeInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_bolometricMagnitudeInput = new GridBagConstraints();
		gbc_bolometricMagnitudeInput.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricMagnitudeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricMagnitudeInput.gridx = 2;
		gbc_bolometricMagnitudeInput.gridy = 4;
		contentPane.add(bolometricMagnitudeInput, gbc_bolometricMagnitudeInput);
		bolometricMagnitudeInput.setColumns(10);

		bolometricMagnitudeCorrection = new JTextField();
		bolometricMagnitudeCorrection.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_bolometricMagnitudeCorrection = new GridBagConstraints();
		gbc_bolometricMagnitudeCorrection.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricMagnitudeCorrection.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricMagnitudeCorrection.gridx = 4;
		gbc_bolometricMagnitudeCorrection.gridy = 4;
		contentPane.add(bolometricMagnitudeCorrection, gbc_bolometricMagnitudeCorrection);
		bolometricMagnitudeCorrection.setColumns(10);

		bolometricMagnitudeResult = new JTextField();
		bolometricMagnitudeResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_bolometricMagnitudeResult = new GridBagConstraints();
		gbc_bolometricMagnitudeResult.insets = new Insets(0, 0, 5, 0);
		gbc_bolometricMagnitudeResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricMagnitudeResult.gridx = 6;
		gbc_bolometricMagnitudeResult.gridy = 4;
		contentPane.add(bolometricMagnitudeResult, gbc_bolometricMagnitudeResult);
		bolometricMagnitudeResult.setColumns(10);

		absoluteMagnitudeInput = new JTextField();
		absoluteMagnitudeInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_absoluteMagnitudeInput = new GridBagConstraints();
		gbc_absoluteMagnitudeInput.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteMagnitudeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteMagnitudeInput.gridx = 2;
		gbc_absoluteMagnitudeInput.gridy = 5;
		contentPane.add(absoluteMagnitudeInput, gbc_absoluteMagnitudeInput);
		absoluteMagnitudeInput.setColumns(10);

		absoluteMagnitudeParsecs = new JTextField();
		absoluteMagnitudeParsecs.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_absoluteMagnitudeParsecs = new GridBagConstraints();
		gbc_absoluteMagnitudeParsecs.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteMagnitudeParsecs.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteMagnitudeParsecs.gridx = 4;
		gbc_absoluteMagnitudeParsecs.gridy = 5;
		contentPane.add(absoluteMagnitudeParsecs, gbc_absoluteMagnitudeParsecs);
		absoluteMagnitudeParsecs.setColumns(10);

		absoluteMagnitudeResult = new JTextField();
		absoluteMagnitudeResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_absoluteMagnitudeResult = new GridBagConstraints();
		gbc_absoluteMagnitudeResult.insets = new Insets(0, 0, 5, 0);
		gbc_absoluteMagnitudeResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteMagnitudeResult.gridx = 6;
		gbc_absoluteMagnitudeResult.gridy = 5;
		contentPane.add(absoluteMagnitudeResult, gbc_absoluteMagnitudeResult);
		absoluteMagnitudeResult.setColumns(10);

		apparentMagnitudeInput = new JTextField();
		apparentMagnitudeInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_apparentMagnitudeInput = new GridBagConstraints();
		gbc_apparentMagnitudeInput.insets = new Insets(0, 0, 5, 5);
		gbc_apparentMagnitudeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeInput.gridx = 2;
		gbc_apparentMagnitudeInput.gridy = 6;
		contentPane.add(apparentMagnitudeInput, gbc_apparentMagnitudeInput);
		apparentMagnitudeInput.setColumns(10);

		apparentMagnitudeParsecs = new JTextField();
		apparentMagnitudeParsecs.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_apparentMagnitudeParsecs = new GridBagConstraints();
		gbc_apparentMagnitudeParsecs.insets = new Insets(0, 0, 5, 5);
		gbc_apparentMagnitudeParsecs.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeParsecs.gridx = 4;
		gbc_apparentMagnitudeParsecs.gridy = 6;
		contentPane.add(apparentMagnitudeParsecs, gbc_apparentMagnitudeParsecs);
		apparentMagnitudeParsecs.setColumns(10);

		apparentMagnitudeResult = new JTextField();
		apparentMagnitudeResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_apparentMagnitudeResult = new GridBagConstraints();
		gbc_apparentMagnitudeResult.insets = new Insets(0, 0, 5, 0);
		gbc_apparentMagnitudeResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeResult.gridx = 6;
		gbc_apparentMagnitudeResult.gridy = 6;
		contentPane.add(apparentMagnitudeResult, gbc_apparentMagnitudeResult);
		apparentMagnitudeResult.setColumns(10);

		solarDiameterTemperature = new JTextField();
		solarDiameterTemperature.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_solarDiameterTemperature = new GridBagConstraints();
		gbc_solarDiameterTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_solarDiameterTemperature.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarDiameterTemperature.gridx = 2;
		gbc_solarDiameterTemperature.gridy = 7;
		contentPane.add(solarDiameterTemperature, gbc_solarDiameterTemperature);
		solarDiameterTemperature.setColumns(10);

		solarDiameterMagnitude = new JTextField();
		solarDiameterMagnitude.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_solarDiameterMagnitude = new GridBagConstraints();
		gbc_solarDiameterMagnitude.insets = new Insets(0, 0, 5, 5);
		gbc_solarDiameterMagnitude.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarDiameterMagnitude.gridx = 4;
		gbc_solarDiameterMagnitude.gridy = 7;
		contentPane.add(solarDiameterMagnitude, gbc_solarDiameterMagnitude);
		solarDiameterMagnitude.setColumns(10);

		solarDiameterResult = new JTextField();
		solarDiameterResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_solarDiameterResult = new GridBagConstraints();
		gbc_solarDiameterResult.insets = new Insets(0, 0, 5, 0);
		gbc_solarDiameterResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarDiameterResult.gridx = 6;
		gbc_solarDiameterResult.gridy = 7;
		contentPane.add(solarDiameterResult, gbc_solarDiameterResult);
		solarDiameterResult.setColumns(10);

		distanceConversionInput = new JTextField();
		distanceConversionInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_distanceConversionInput = new GridBagConstraints();
		gbc_distanceConversionInput.insets = new Insets(0, 0, 5, 5);
		gbc_distanceConversionInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceConversionInput.gridx = 3;
		gbc_distanceConversionInput.gridy = 8;
		contentPane.add(distanceConversionInput, gbc_distanceConversionInput);
		distanceConversionInput.setColumns(10);

		distanceConversionOutput = new JTextField();
		distanceConversionOutput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_distanceConversionOutput = new GridBagConstraints();
		gbc_distanceConversionOutput.insets = new Insets(0, 0, 5, 5);
		gbc_distanceConversionOutput.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceConversionOutput.gridx = 5;
		gbc_distanceConversionOutput.gridy = 8;
		contentPane.add(distanceConversionOutput, gbc_distanceConversionOutput);
		distanceConversionOutput.setColumns(10);

		stellarDenominationField = new JTextField();
		stellarDenominationField.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_stellarDenominationField = new GridBagConstraints();
		gbc_stellarDenominationField.insets = new Insets(0, 0, 0, 5);
		gbc_stellarDenominationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stellarDenominationField.gridx = 3;
		gbc_stellarDenominationField.gridy = 9;
		contentPane.add(stellarDenominationField, gbc_stellarDenominationField);
		stellarDenominationField.setColumns(10);

		descriptionOutcome = new JTextField();
		descriptionOutcome.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_descriptionOutcome = new GridBagConstraints();
		gbc_descriptionOutcome.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionOutcome.fill = GridBagConstraints.HORIZONTAL;
		gbc_descriptionOutcome.gridx = 5;
		gbc_descriptionOutcome.gridy = 9;
		contentPane.add(descriptionOutcome, gbc_descriptionOutcome);
		descriptionOutcome.setColumns(10);

		JButton spectralParametersButton = new JButton("Spectral Parameters");
		spectralParametersButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		spectralParametersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(spectralParametersClass.getText().equals(""))) {
					try {
						ArrayList<Object> returnedObjects = spectralClassInstance
								.retrieveParameters(spectralParametersClass.getText());
						Integer returnedObjectsSize = returnedObjects.size();
						if (returnedObjectsSize == 2) {
							spectralParametersTemperature.setText(returnedObjects.get(0).toString());
							spectralParametersCorrection.setText(returnedObjects.get(1).toString());
						} else if (returnedObjectsSize <= 1) {
							spectralParametersCorrection.setText("Missing References");
						}
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						spectralParametersClass.setText(warning.getMessage());
					}
				} else {
					spectralParametersClass.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_spectralParametersButton = new GridBagConstraints();
		gbc_spectralParametersButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_spectralParametersButton.insets = new Insets(0, 0, 5, 5);
		gbc_spectralParametersButton.gridx = 0;
		gbc_spectralParametersButton.gridy = 0;
		contentPane.add(spectralParametersButton, gbc_spectralParametersButton);

		JButton habitableBoundariesButton = new JButton("Habitable Boundaries");
		habitableBoundariesButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		habitableBoundariesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(bolometricLuminosityResult.getText().equals(""))) {
					try {
						Double bolometricLuminosity = Double.parseDouble(bolometricLuminosityResult.getText());
						ArrayList<String> goldilocksParameters = stellarCalculatorInstance
								.habitableBoundaries(bolometricLuminosity);
						innerBoundary.setText(goldilocksParameters.get(0));
						outerBoundary.setText(goldilocksParameters.get(1));
						gregorianYear.setText(goldilocksParameters.get(2));
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						innerBoundary.setText(warning.getMessage());
					}
				} else {
					innerBoundary.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_habitableBoundariesButton = new GridBagConstraints();
		gbc_habitableBoundariesButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_habitableBoundariesButton.insets = new Insets(0, 0, 5, 5);
		gbc_habitableBoundariesButton.gridx = 0;
		gbc_habitableBoundariesButton.gridy = 1;
		contentPane.add(habitableBoundariesButton, gbc_habitableBoundariesButton);

		JButton bolometricLuminosityButton = new JButton("Bolometric Luminosity");
		bolometricLuminosityButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		bolometricLuminosityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(bolometricLuminosityInput.getText().equals(""))) {
					try {
						Double bolometricMagnitude = Double.parseDouble(bolometricLuminosityInput.getText());
						String bolometricLuminosity = stellarCalculatorInstance
								.bolometricLuminosity(bolometricMagnitude);
						bolometricLuminosityResult.setText(bolometricLuminosity);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						bolometricLuminosityInput.setText(warning.getMessage());
					}
				} else {
					bolometricLuminosityInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_bolometricLuminosityButton = new GridBagConstraints();
		gbc_bolometricLuminosityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricLuminosityButton.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricLuminosityButton.gridx = 0;
		gbc_bolometricLuminosityButton.gridy = 2;
		contentPane.add(bolometricLuminosityButton, gbc_bolometricLuminosityButton);

		JButton absoluteLuminosityButton = new JButton("Absolute Luminosity");
		absoluteLuminosityButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		absoluteLuminosityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(absoluteLuminosityInput.getText().equals(""))) {
					try {
						Double absoluteMagnitude = Double.parseDouble(absoluteLuminosityInput.getText());
						String absoluteLuminosity = stellarCalculatorInstance.absoluteLuminosity(absoluteMagnitude);
						absoluteLuminosityResult.setText(absoluteLuminosity);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						absoluteLuminosityInput.setText(warning.getMessage());
					}
				} else {
					absoluteLuminosityInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_absoluteLuminosityButton = new GridBagConstraints();
		gbc_absoluteLuminosityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteLuminosityButton.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteLuminosityButton.gridx = 0;
		gbc_absoluteLuminosityButton.gridy = 3;
		contentPane.add(absoluteLuminosityButton, gbc_absoluteLuminosityButton);

		JButton bolometricMagnitudeButton = new JButton("Bolometric Magnitude");
		bolometricMagnitudeButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		bolometricMagnitudeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(bolometricMagnitudeInput.getText().equals(""))) {
					try {
						Double absoluteMagnitude = Double.parseDouble(bolometricMagnitudeInput.getText());
						Double magnitudeCorrection = Double.parseDouble(bolometricMagnitudeCorrection.getText());
						String bolometricMagnitude = stellarCalculatorInstance.bolometricMagnitude(absoluteMagnitude,
								magnitudeCorrection);
						bolometricMagnitudeResult.setText(bolometricMagnitude);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						bolometricMagnitudeInput.setText(warning.getMessage());
					}
				} else {
					bolometricMagnitudeInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_bolometricMagnitudeButton = new GridBagConstraints();
		gbc_bolometricMagnitudeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_bolometricMagnitudeButton.insets = new Insets(0, 0, 5, 5);
		gbc_bolometricMagnitudeButton.gridx = 0;
		gbc_bolometricMagnitudeButton.gridy = 4;
		contentPane.add(bolometricMagnitudeButton, gbc_bolometricMagnitudeButton);

		JButton absoluteMagnitudeButton = new JButton("Absolute Magnitude");
		absoluteMagnitudeButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		absoluteMagnitudeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(absoluteMagnitudeInput.getText().equals(""))) {
					try {
						Double apparentMagnitude = Double.parseDouble(absoluteMagnitudeInput.getText());
						Double parsecs = Double.parseDouble(absoluteMagnitudeParsecs.getText());
						String absoluteMagnitude = stellarCalculatorInstance.absoluteMagnitude(apparentMagnitude,
								parsecs);
						absoluteMagnitudeResult.setText(absoluteMagnitude);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						absoluteMagnitudeInput.setText(warning.getMessage());
					}
				} else {
					absoluteMagnitudeInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_absoluteMagnitudeButton = new GridBagConstraints();
		gbc_absoluteMagnitudeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_absoluteMagnitudeButton.insets = new Insets(0, 0, 5, 5);
		gbc_absoluteMagnitudeButton.gridx = 0;
		gbc_absoluteMagnitudeButton.gridy = 5;
		contentPane.add(absoluteMagnitudeButton, gbc_absoluteMagnitudeButton);

		JButton apparentMagnitudeButton = new JButton("Apparent Magnitude");
		apparentMagnitudeButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		apparentMagnitudeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(apparentMagnitudeInput.getText().equals(""))) {
					try {
						Double absoluteMagnitude = Double.parseDouble(apparentMagnitudeInput.getText());
						Double parsecs = Double.parseDouble(apparentMagnitudeParsecs.getText());
						String apparentMagnitude = stellarCalculatorInstance.apparentMagnitude(absoluteMagnitude,
								parsecs);
						apparentMagnitudeResult.setText(apparentMagnitude);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						apparentMagnitudeInput.setText(warning.getMessage());
					}
				} else {
					apparentMagnitudeInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_apparentMagnitudeButton = new GridBagConstraints();
		gbc_apparentMagnitudeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_apparentMagnitudeButton.insets = new Insets(0, 0, 5, 5);
		gbc_apparentMagnitudeButton.gridx = 0;
		gbc_apparentMagnitudeButton.gridy = 6;
		contentPane.add(apparentMagnitudeButton, gbc_apparentMagnitudeButton);

		JButton solarDiameterButton = new JButton("Solar Diameter");
		solarDiameterButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		solarDiameterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(solarDiameterTemperature.getText().equals("") && solarDiameterMagnitude.getText().equals(""))) {
					try {
						Double stellarTemperature = Double.parseDouble(solarDiameterTemperature.getText());
						Double bolometricMagnitude = Double.parseDouble(solarDiameterMagnitude.getText());
						String solarDiameter = stellarCalculatorInstance.solarDiameter(stellarTemperature,
								bolometricMagnitude);
						solarDiameterResult.setText(solarDiameter);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						solarDiameterTemperature.setText(warning.getMessage());
					}
				} else {
					solarDiameterTemperature.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_solarDiameterButton = new GridBagConstraints();
		gbc_solarDiameterButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_solarDiameterButton.insets = new Insets(0, 0, 5, 5);
		gbc_solarDiameterButton.gridx = 0;
		gbc_solarDiameterButton.gridy = 7;
		contentPane.add(solarDiameterButton, gbc_solarDiameterButton);

		JButton distanceButton = new JButton("Distance");
		distanceButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		distanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(distanceConversionInput.getText().equals(""))) {
					try {
						int selectedConversion = distanceUnitSelection.getSelectedIndex();
						Double distanceInput = Double.parseDouble(distanceConversionInput.getText());
						String distanceConversion = stellarCalculatorInstance.distanceConverter(selectedConversion,
								distanceInput);
						distanceConversionOutput.setText(distanceConversion);
					} catch (IndexOutOfBoundsException | NumberFormatException warning) {
						distanceConversionInput.setText(warning.getMessage());
					}
				} else {
					distanceConversionInput.setText("Incomplete Submission");
				}
			}
		});
		GridBagConstraints gbc_distanceButton = new GridBagConstraints();
		gbc_distanceButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_distanceButton.insets = new Insets(0, 0, 5, 5);
		gbc_distanceButton.gridx = 0;
		gbc_distanceButton.gridy = 8;
		contentPane.add(distanceButton, gbc_distanceButton);

		JButton stellarInformationButton = new JButton("Stellar Information");
		stellarInformationButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		stellarInformationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stellarInformation();
			}
		});
		GridBagConstraints gbc_stellarInformationButton = new GridBagConstraints();
		gbc_stellarInformationButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_stellarInformationButton.insets = new Insets(0, 0, 0, 5);
		gbc_stellarInformationButton.gridx = 0;
		gbc_stellarInformationButton.gridy = 9;
		contentPane.add(stellarInformationButton, gbc_stellarInformationButton);

		JButton btnInvert = new JButton("Invert");
		btnInvert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String intermediateVariable = null;
				String distanceInputValue = distanceConversionInput.getText();
				String distanceOutputValue = distanceConversionOutput.getText();

				intermediateVariable = distanceInputValue;
				distanceInputValue = distanceOutputValue;
				distanceOutputValue = intermediateVariable;

				distanceConversionInput.setText(distanceInputValue);
				distanceConversionOutput.setText(distanceOutputValue);

			}
		});
		GridBagConstraints gbc_btnInvert = new GridBagConstraints();
		gbc_btnInvert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInvert.insets = new Insets(0, 0, 5, 0);
		gbc_btnInvert.gridx = 6;
		gbc_btnInvert.gridy = 8;
		contentPane.add(btnInvert, gbc_btnInvert);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				FileCleaner fileCleanerInstance = new FileCleaner(descriptionsDirectory, datasetsDirectory,
						mysqlDirectory, oracleDirectory);
				new Thread(fileCleanerInstance).start();

				InterfaceManipulator interfaceManipulatorInstance = new InterfaceManipulator(contentPane);
				interfaceManipulatorInstance.defaultMode();
			}
		});
		GridBagConstraints gbc_resetButton = new GridBagConstraints();
		gbc_resetButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_resetButton.gridx = 6;
		gbc_resetButton.gridy = 9;
		contentPane.add(resetButton, gbc_resetButton);

		JLabel lblNewLabel = new JLabel("Spectral Class:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Magnitude Correction:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Stellar Temperature:");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblInnerBoundaryAu = new JLabel("Inner Boundary AU:");
		lblInnerBoundaryAu.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblInnerBoundaryAu = new GridBagConstraints();
		gbc_lblInnerBoundaryAu.anchor = GridBagConstraints.EAST;
		gbc_lblInnerBoundaryAu.insets = new Insets(0, 0, 5, 5);
		gbc_lblInnerBoundaryAu.gridx = 1;
		gbc_lblInnerBoundaryAu.gridy = 1;
		contentPane.add(lblInnerBoundaryAu, gbc_lblInnerBoundaryAu);

		JLabel lblOuterBoundaryAu = new JLabel("Outer Boundary AU:");
		lblOuterBoundaryAu.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblOuterBoundaryAu = new GridBagConstraints();
		gbc_lblOuterBoundaryAu.anchor = GridBagConstraints.EAST;
		gbc_lblOuterBoundaryAu.insets = new Insets(0, 0, 5, 5);
		gbc_lblOuterBoundaryAu.gridx = 3;
		gbc_lblOuterBoundaryAu.gridy = 1;
		contentPane.add(lblOuterBoundaryAu, gbc_lblOuterBoundaryAu);

		JLabel lblGregorianYear = new JLabel("Gregorian Year:");
		lblGregorianYear.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblGregorianYear = new GridBagConstraints();
		gbc_lblGregorianYear.anchor = GridBagConstraints.EAST;
		gbc_lblGregorianYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblGregorianYear.gridx = 5;
		gbc_lblGregorianYear.gridy = 1;
		contentPane.add(lblGregorianYear, gbc_lblGregorianYear);

		JLabel lblBolometricMagnitude = new JLabel("Bolometric Magnitude:");
		lblBolometricMagnitude.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBolometricMagnitude = new GridBagConstraints();
		gbc_lblBolometricMagnitude.anchor = GridBagConstraints.EAST;
		gbc_lblBolometricMagnitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblBolometricMagnitude.gridx = 1;
		gbc_lblBolometricMagnitude.gridy = 2;
		contentPane.add(lblBolometricMagnitude, gbc_lblBolometricMagnitude);

		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.anchor = GridBagConstraints.EAST;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 3;
		gbc_lblResult.gridy = 2;
		contentPane.add(lblResult, gbc_lblResult);

		JLabel lblAbsoluteMagnitude = new JLabel("Absolute Magnitude:");
		lblAbsoluteMagnitude.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAbsoluteMagnitude = new GridBagConstraints();
		gbc_lblAbsoluteMagnitude.anchor = GridBagConstraints.EAST;
		gbc_lblAbsoluteMagnitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbsoluteMagnitude.gridx = 1;
		gbc_lblAbsoluteMagnitude.gridy = 3;
		contentPane.add(lblAbsoluteMagnitude, gbc_lblAbsoluteMagnitude);

		JLabel lblResult_1 = new JLabel("Result:");
		lblResult_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_1 = new GridBagConstraints();
		gbc_lblResult_1.anchor = GridBagConstraints.EAST;
		gbc_lblResult_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_1.gridx = 3;
		gbc_lblResult_1.gridy = 3;
		contentPane.add(lblResult_1, gbc_lblResult_1);

		JLabel lblBolometricMagnitude_1 = new JLabel("Absolute Magnitude:");
		lblBolometricMagnitude_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBolometricMagnitude_1 = new GridBagConstraints();
		gbc_lblBolometricMagnitude_1.anchor = GridBagConstraints.EAST;
		gbc_lblBolometricMagnitude_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBolometricMagnitude_1.gridx = 1;
		gbc_lblBolometricMagnitude_1.gridy = 4;
		contentPane.add(lblBolometricMagnitude_1, gbc_lblBolometricMagnitude_1);

		JLabel lblMagnitudeCorrection = new JLabel("Magnitude Correction:");
		lblMagnitudeCorrection.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMagnitudeCorrection = new GridBagConstraints();
		gbc_lblMagnitudeCorrection.anchor = GridBagConstraints.EAST;
		gbc_lblMagnitudeCorrection.insets = new Insets(0, 0, 5, 5);
		gbc_lblMagnitudeCorrection.gridx = 3;
		gbc_lblMagnitudeCorrection.gridy = 4;
		contentPane.add(lblMagnitudeCorrection, gbc_lblMagnitudeCorrection);

		JLabel lblResult_2 = new JLabel("Result:");
		lblResult_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_2 = new GridBagConstraints();
		gbc_lblResult_2.anchor = GridBagConstraints.EAST;
		gbc_lblResult_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_2.gridx = 5;
		gbc_lblResult_2.gridy = 4;
		contentPane.add(lblResult_2, gbc_lblResult_2);

		JLabel lblAbsoluteMagnitude_1 = new JLabel("Apparent Magnitude:");
		lblAbsoluteMagnitude_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAbsoluteMagnitude_1 = new GridBagConstraints();
		gbc_lblAbsoluteMagnitude_1.anchor = GridBagConstraints.EAST;
		gbc_lblAbsoluteMagnitude_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbsoluteMagnitude_1.gridx = 1;
		gbc_lblAbsoluteMagnitude_1.gridy = 5;
		contentPane.add(lblAbsoluteMagnitude_1, gbc_lblAbsoluteMagnitude_1);

		JLabel lblParsecs = new JLabel("Parsecs:");
		lblParsecs.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblParsecs = new GridBagConstraints();
		gbc_lblParsecs.anchor = GridBagConstraints.EAST;
		gbc_lblParsecs.insets = new Insets(0, 0, 5, 5);
		gbc_lblParsecs.gridx = 3;
		gbc_lblParsecs.gridy = 5;
		contentPane.add(lblParsecs, gbc_lblParsecs);

		JLabel lblResult_3 = new JLabel("Result:");
		lblResult_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_3 = new GridBagConstraints();
		gbc_lblResult_3.anchor = GridBagConstraints.EAST;
		gbc_lblResult_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_3.gridx = 5;
		gbc_lblResult_3.gridy = 5;
		contentPane.add(lblResult_3, gbc_lblResult_3);

		JLabel lblAbsoluteMagnitude_2 = new JLabel("Absolute Magnitude:");
		lblAbsoluteMagnitude_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAbsoluteMagnitude_2 = new GridBagConstraints();
		gbc_lblAbsoluteMagnitude_2.anchor = GridBagConstraints.EAST;
		gbc_lblAbsoluteMagnitude_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblAbsoluteMagnitude_2.gridx = 1;
		gbc_lblAbsoluteMagnitude_2.gridy = 6;
		contentPane.add(lblAbsoluteMagnitude_2, gbc_lblAbsoluteMagnitude_2);

		JLabel lblParsecs_1 = new JLabel("Parsecs:");
		lblParsecs_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblParsecs_1 = new GridBagConstraints();
		gbc_lblParsecs_1.anchor = GridBagConstraints.EAST;
		gbc_lblParsecs_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblParsecs_1.gridx = 3;
		gbc_lblParsecs_1.gridy = 6;
		contentPane.add(lblParsecs_1, gbc_lblParsecs_1);

		JLabel lblResult_4 = new JLabel("Result:");
		lblResult_4.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_4 = new GridBagConstraints();
		gbc_lblResult_4.anchor = GridBagConstraints.EAST;
		gbc_lblResult_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_4.gridx = 5;
		gbc_lblResult_4.gridy = 6;
		contentPane.add(lblResult_4, gbc_lblResult_4);

		JLabel lblStellarTemperature = new JLabel("Stellar Temperature:");
		lblStellarTemperature.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblStellarTemperature = new GridBagConstraints();
		gbc_lblStellarTemperature.anchor = GridBagConstraints.EAST;
		gbc_lblStellarTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_lblStellarTemperature.gridx = 1;
		gbc_lblStellarTemperature.gridy = 7;
		contentPane.add(lblStellarTemperature, gbc_lblStellarTemperature);

		JLabel lblBolometricMagnitude_2 = new JLabel("Bolometric Magnitude:");
		lblBolometricMagnitude_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBolometricMagnitude_2 = new GridBagConstraints();
		gbc_lblBolometricMagnitude_2.anchor = GridBagConstraints.EAST;
		gbc_lblBolometricMagnitude_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblBolometricMagnitude_2.gridx = 3;
		gbc_lblBolometricMagnitude_2.gridy = 7;
		contentPane.add(lblBolometricMagnitude_2, gbc_lblBolometricMagnitude_2);

		JLabel lblResult_5 = new JLabel("Result:");
		lblResult_5.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_5 = new GridBagConstraints();
		gbc_lblResult_5.anchor = GridBagConstraints.EAST;
		gbc_lblResult_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_5.gridx = 5;
		gbc_lblResult_5.gridy = 7;
		contentPane.add(lblResult_5, gbc_lblResult_5);

		JLabel lblInput = new JLabel("Input:");
		lblInput.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblInput = new GridBagConstraints();
		gbc_lblInput.anchor = GridBagConstraints.EAST;
		gbc_lblInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblInput.gridx = 2;
		gbc_lblInput.gridy = 8;
		contentPane.add(lblInput, gbc_lblInput);

		JLabel lblResult_6 = new JLabel("Result:");
		lblResult_6.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_6 = new GridBagConstraints();
		gbc_lblResult_6.anchor = GridBagConstraints.EAST;
		gbc_lblResult_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult_6.gridx = 4;
		gbc_lblResult_6.gridy = 8;
		contentPane.add(lblResult_6, gbc_lblResult_6);

		JLabel lblStellarDenomination = new JLabel("Stellar Denomination:");
		lblStellarDenomination.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblStellarDenomination = new GridBagConstraints();
		gbc_lblStellarDenomination.anchor = GridBagConstraints.EAST;
		gbc_lblStellarDenomination.insets = new Insets(0, 0, 0, 5);
		gbc_lblStellarDenomination.gridx = 2;
		gbc_lblStellarDenomination.gridy = 9;
		contentPane.add(lblStellarDenomination, gbc_lblStellarDenomination);

		JLabel lblResult_7 = new JLabel("Result:");
		lblResult_7.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_lblResult_7 = new GridBagConstraints();
		gbc_lblResult_7.anchor = GridBagConstraints.EAST;
		gbc_lblResult_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblResult_7.gridx = 4;
		gbc_lblResult_7.gridy = 9;
		contentPane.add(lblResult_7, gbc_lblResult_7);

		stellarDenominationField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						stellarInformation();
					} catch (StringIndexOutOfBoundsException warning) {
						stellarDenominationField.setText("Invalid Submission");
					}
				}
			}
		});

	}

	protected void stellarInformation() {
		if (!(stellarDenominationField.getText().equals(""))) {
			try {
				descriptionOutcome.setText("");
				validateInput(stellarDenominationField, databaseSystemSelection);
				Integer concurrentThreads = 2;
				Integer descriptionExecutionCode = 0;
				Integer statementExecutionCode = 0;
				ExecutorService stellarDescriptionOperation;
				Future<Integer> descriptionThread;
				Future<Integer> statementThread;

				stellarDescriptionOperation = Executors.newFixedThreadPool(concurrentThreads);
				descriptionThread = stellarDescriptionOperation
						.submit(new DescriptionFetcher(astronomicWebsite, urlStar, descriptionsDirectory));
				statementThread = stellarDescriptionOperation
						.submit(new StatementGenerator(xmlStar, databaseOption, datasetsDirectory, resourcesDirectory));

				descriptionExecutionCode = descriptionThread.get();
				statementExecutionCode = statementThread.get();

				if (descriptionExecutionCode == 0 && statementExecutionCode == 0) {
					descriptionOutcome.setText("Operation Successful");
				} else if (statementExecutionCode == 1 && descriptionExecutionCode == 0) {
					descriptionOutcome.setText("Missing Dataset");
				} else if (descriptionExecutionCode == 1 && statementExecutionCode == 0) {
					descriptionOutcome.setText("Missing Description");
				} else if (descriptionExecutionCode == 1 && statementExecutionCode == 1) {
					descriptionOutcome.setText("Missing References");
				}

			} catch (IndexOutOfBoundsException | NumberFormatException validationException) {
				stellarDenominationField.setText(validationException.getMessage());
			} catch (ExecutionException | InterruptedException executionException) {
				stellarDenominationField.setText(executionException.getMessage());
			}
		} else {
			stellarDenominationField.setText("Incomplete Submission");
		}
	}

	protected void validateInput(JTextField inputField, JComboBox<String> inputOption) {
		String[] userInput = inputField.getText().split("\\s");
		String[] initialValidation = new String[userInput.length];
		String[] xmlValidation = new String[userInput.length];
		String[] htmlValidation = new String[userInput.length];
		StringBuilder htmlInput = new StringBuilder();
		StringBuilder xmlInput = new StringBuilder();

		for (int i = 0; i < userInput.length; i++) {
			if (i == 0 && userInput.length > 1) {
				initialValidation[i] = userInput[i].toLowerCase() + " ";
			} else {
				initialValidation[i] = userInput[i].toLowerCase();
			}

			htmlValidation[i] = userInput[i].trim().toLowerCase();
			htmlInput.append(htmlValidation[i]);

			xmlValidation[i] = initialValidation[i].substring(0, 1).toUpperCase() + initialValidation[i].substring(1);
			xmlInput.append(xmlValidation[i]);
		}

		urlStar = htmlInput.toString();
		xmlStar = xmlInput.toString();
		astronomicWebsite = "http://stars.astro.illinois.edu/sow/" + urlStar + ".html";
		databaseOption = inputOption.getSelectedItem().toString();
	}
}
