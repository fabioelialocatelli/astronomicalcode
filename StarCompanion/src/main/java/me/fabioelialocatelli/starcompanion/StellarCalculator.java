package me.fabioelialocatelli.starcompanion;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StellarCalculator {

	private DecimalFormat calculatorFormatter = null;
	private final double goldilocksConstant;
	private final double gregorianYear;
	private final double lightYearsConversion;
	private final double parsecsConversion;
	private final double pogsonConstant;
	private final double solarAbsoluteMagnitude;
	private final double solarBolometricMagnitude;
	private final double solarInnerBoundary;
	private final double solarOuterBoundary;
	private final double solarTemperature;

	public StellarCalculator() {
		this.goldilocksConstant = 2.795883;
		this.gregorianYear = 365.2425;
		this.lightYearsConversion = 3.261633440;
		this.parsecsConversion = 0.306594845;
		this.pogsonConstant = 2.511886431;
		this.solarAbsoluteMagnitude = 4.83;
		this.solarBolometricMagnitude = 4.75;
		this.solarInnerBoundary = 1.1;
		this.solarOuterBoundary = 0.53;
		this.solarTemperature = 5777;
		this.calculatorFormatter = new DecimalFormat("#.####");
		calculatorFormatter.setRoundingMode(RoundingMode.FLOOR);
	}

	public String absoluteLuminosity(double absoluteMagnitude) {
		double output = Math.pow(10, ((solarAbsoluteMagnitude - absoluteMagnitude) / 2.5));
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public String absoluteMagnitude(double apparentMagnitude, double parsecs) {
		double output = apparentMagnitude - 5 * Math.log10(parsecs / 10);
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public String apparentMagnitude(double absoluteMagnitude, double parsecs) {
		double output = absoluteMagnitude - 5 * (1 - Math.log10(parsecs));
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public String bolometricLuminosity(double bolometricMagnitude) {
		double output = Math.pow(10, ((solarBolometricMagnitude - bolometricMagnitude) / 2.5));
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public String bolometricMagnitude(double absoluteMagnitude, double magnitudeCorrection) {
		double output = absoluteMagnitude + magnitudeCorrection;
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public String distanceConverter(int option, double input) {
		double output = 0;
		if (option == 0)
			output = input * lightYearsConversion;
		else if (option == 1)
			output = input * parsecsConversion;
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}

	public ArrayList<String> habitableBoundaries(double bolometricLuminosity) {
		ArrayList<String> goldilocksParameters = new ArrayList<String>();
		double innerBoundary = bolometricLuminosity / solarInnerBoundary;
		double outerBoundary = bolometricLuminosity / solarOuterBoundary;
		double yearDuration = ((innerBoundary + outerBoundary) / goldilocksConstant) * gregorianYear;

		goldilocksParameters.add(calculatorFormatter.format(Math.sqrt(innerBoundary)));
		goldilocksParameters.add(calculatorFormatter.format(Math.sqrt(outerBoundary)));
		goldilocksParameters.add(calculatorFormatter.format(yearDuration));
		return (goldilocksParameters);
	}

	public String solarDiameter(double stellarTemperature, double bolometricMagnitude) {

		double argument = solarTemperature / stellarTemperature;
		double exponent = solarBolometricMagnitude - bolometricMagnitude;

		double power1 = Math.pow(argument, 2);
		double power2 = Math.pow(pogsonConstant, exponent);
		double power3 = Math.pow(power2, 0.5);

		double output = power1 * power3;
		String formattedOutput = calculatorFormatter.format(output);
		return (formattedOutput);
	}
}
