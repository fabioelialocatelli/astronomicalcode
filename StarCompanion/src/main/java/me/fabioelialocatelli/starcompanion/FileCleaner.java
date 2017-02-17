package me.fabioelialocatelli.starcompanion;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCleaner implements Runnable {

	private boolean datasetsExist = false;
	private boolean descriptionsExist = false;
	private File descriptionDirectory = null;
	private File datasetDirectory = null;
	private File[] descriptions = null;
	private File[] statements = null;
	private Path descriptionLocation = null;
	private Path datasetLocation = null;
	private String descriptionPath = null;
	private String datasetPath = null;
	private String mysqlPath = null;
	private String oraclePath = null;

	public FileCleaner(String descriptionsDirectory, String statementsDirectory, String mysqlDirectory, String oracleDirectory) {
		this.descriptionPath = descriptionsDirectory;
		this.datasetPath = statementsDirectory;		
		this.mysqlPath = mysqlDirectory;
		this.oraclePath = oracleDirectory;
		this.datasetLocation = new File(datasetPath).toPath();
		this.descriptionLocation = new File(descriptionPath).toPath();
		this.datasetsExist = Files.exists(datasetLocation);
		this.descriptionsExist = Files.exists(descriptionLocation);
	}

	public void cleanDirectories() {

		datasetDirectory = new File(mysqlPath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		datasetDirectory = new File(oraclePath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		datasetDirectory = new File(datasetPath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		descriptionDirectory = new File(descriptionPath);
		descriptions = descriptionDirectory.listFiles();
		for (File starDescription : descriptions) {
			if (starDescription.isFile()) {
				starDescription.delete();
			}
		}
	}

	public void run() {		
		
		if (datasetsExist && descriptionsExist) {
			cleanDirectories();
		}
	}
}
