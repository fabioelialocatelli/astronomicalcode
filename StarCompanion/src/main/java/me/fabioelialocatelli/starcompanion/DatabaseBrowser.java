package me.fabioelialocatelli.starcompanion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseBrowser {

	private ArrayList<Vector<Object>> databaseObjects = null;
	private Connection localConnection = null;
	private PreparedStatement databaseStatement = null;
	private ResultSet databaseRetrieval = null;
	private ResultSetMetaData retrievalMetadata = null;
	private String databasePath = null;

	public DatabaseBrowser(String databasePath) {
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

	public ArrayList<Vector<Object>> displayConstellation(String latinDesignation) {
		try {
			databaseObjects = new ArrayList<Vector<Object>>();
			databaseStatement = localConnection
					.prepareStatement("SELECT * FROM bayerDesignation WHERE designation LIKE ?;");
			databaseStatement.setString(1, "%" + latinDesignation);

			databaseRetrieval = databaseStatement.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();

			Vector<Object> columnDenominations = new Vector<Object>();
			Vector<Object> recordObjects = new Vector<Object>();

			for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
				columnDenominations.add(retrievalMetadata.getColumnName(columnIndex));
			}

			while (databaseRetrieval.next()) {
				Vector<Object> retrievedRow = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					retrievedRow.add(databaseRetrieval.getObject(columnIndex));
				}
				recordObjects.add(retrievedRow);
			}
			databaseObjects.add(recordObjects);
			databaseObjects.add(columnDenominations);

		} catch (SQLException warning) {
			warning.getErrorCode();
		}

		return databaseObjects;
	}

	public ArrayList<Vector<Object>> displayStars(String selectionCriteria, Integer sortingOption) {
		try {
			databaseObjects = new ArrayList<Vector<Object>>();

			if (sortingOption == 0) {
				databaseStatement = localConnection
						.prepareStatement("SELECT * FROM classicDesignation ORDER BY " + selectionCriteria + " ASC;");
			} else if (sortingOption == 1) {
				databaseStatement = localConnection.prepareStatement(
						"SELECT * FROM classicDesignation ORDER BY " + selectionCriteria + " DESC;");
			}

			databaseRetrieval = databaseStatement.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();

			Vector<Object> columnDenominations = new Vector<Object>();
			Vector<Object> recordObjects = new Vector<Object>();

			for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
				columnDenominations.add(retrievalMetadata.getColumnName(columnIndex));
			}

			while (databaseRetrieval.next()) {
				Vector<Object> retrievedRow = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					retrievedRow.add(databaseRetrieval.getObject(columnIndex));
				}
				recordObjects.add(retrievedRow);
			}
			databaseObjects.add(recordObjects);
			databaseObjects.add(columnDenominations);

		} catch (SQLException warning) {
			warning.getErrorCode();
		}

		return databaseObjects;
	}

}
