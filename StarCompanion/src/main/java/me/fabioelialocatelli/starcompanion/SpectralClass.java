package me.fabioelialocatelli.starcompanion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpectralClass {

	private ArrayList<Object> returnedObjects = null;
	private Connection localConnection = null;
	private PreparedStatement spectralAttributes = null;
	private ResultSet databaseRetrieval = null;
	private ResultSetMetaData retrievalMetadata = null;
	private String databasePath = null;

	public SpectralClass(String databasePath) {
		this.databasePath = databasePath;
	}

	public String anchorDatabase() {
		String exceptionMessage = null;
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			localConnection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| SQLException databaseException) {
			exceptionMessage = databaseException.getMessage();
		}
		return exceptionMessage;
	}

	public ArrayList<Object> retrieveParameters(String spectralClass) {
		String exceptionMessage = null;
		try {
			spectralAttributes = localConnection.prepareStatement(
					"SELECT temperatureKelvin, magnitudeCorrection FROM spectralClassification WHERE spectra = ?;");
			spectralAttributes.setString(1, spectralClass);
			databaseRetrieval = spectralAttributes.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();
			returnedObjects = new ArrayList<Object>();

			while (databaseRetrieval.next()) {
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					returnedObjects.add(databaseRetrieval.getObject(columnIndex));
				}
			}
		} catch (NullPointerException | SQLException retrievalException) {
			exceptionMessage = retrievalException.getMessage();
			returnedObjects.add(exceptionMessage);
		}
		return returnedObjects;
	}
}
