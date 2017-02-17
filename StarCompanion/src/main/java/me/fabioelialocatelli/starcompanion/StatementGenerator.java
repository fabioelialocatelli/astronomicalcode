package me.fabioelialocatelli.starcompanion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StatementGenerator implements Callable<Integer> {

	private Document stellarDatabase = null;
	private DocumentBuilderFactory documentBuilderFactory = null;
	private DocumentBuilder documentBuilder = null;
	private File script = null;
	private File scriptDirectory = null;
	private FileWriter sqlWriter = null;
	private NodeList selectedStarNodes = null;
	private String datasetsDirectory = null;
	private String resourcesDirectory = null;
	private String selectedDatabase = null;
	private String selectedStar = null;
	private String selectedStarData = null;
	private String statementDatabase = null;
	private String statementSyntax = null;
	private String xmlDatasheet = null;
	private XPath xpath = null;
	private XPathFactory xpathFactory = null;

	public StatementGenerator(String star, String database, String datasets, String resources) {
		this.selectedStar = star;
		this.selectedDatabase = database;
		this.datasetsDirectory = datasets;
		this.resourcesDirectory = resources;
	}

	public Integer call() throws Exception {
		int executionCode = 0;
		try {
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			xpathFactory = XPathFactory.newInstance();

			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			xpath = xpathFactory.newXPath();
			xmlDatasheet = resourcesDirectory + "stellardatabase.xml";

			stellarDatabase = documentBuilder.parse(xmlDatasheet);
			stellarDatabase.getDocumentElement().normalize();

			selectedStarData = "/data/row[denomination ='" + selectedStar + "']/*";

			if (!(selectedStarData == null)) {
				selectedStarNodes = (NodeList) xpath.compile(selectedStarData).evaluate(stellarDatabase,
						XPathConstants.NODESET);

				switch (selectedDatabase) {
				case "MySQL":
					statementSyntax = "INSERT INTO `argo`.`dynamicEdition`(";
					statementDatabase = "MySQL\\";
					break;
				case "Oracle":
					statementSyntax = "INSERT INTO dynamicEdition(";
					statementDatabase = "Oracle\\";
					break;
				default:
					break;
				}

				if (selectedStarNodes.item(0) != null) {

					scriptDirectory = new File(datasetsDirectory + statementDatabase);
					scriptDirectory.mkdirs();
					script = new File(scriptDirectory, selectedStar + ".sql");

					sqlWriter = new FileWriter(script);
					sqlWriter.write(statementSyntax);

					for (int i = 0; i < selectedStarNodes.getLength(); i++) {
						Node selectedStarItem = selectedStarNodes.item(i);
						if (i < selectedStarNodes.getLength() - 1) {
							sqlWriter.write(selectedStarItem.getNodeName() + ", ");
						} else {
							sqlWriter.write(selectedStarItem.getNodeName());
						}
					}
					sqlWriter.write(") VALUES (");

					for (int i = 0; i < selectedStarNodes.getLength(); i++) {
						Node selectedStarItem = selectedStarNodes.item(i);
						if (i < selectedStarNodes.getLength() - 1) {
							sqlWriter.write("'" + selectedStarItem.getFirstChild().getNodeValue() + "', ");
						} else {
							sqlWriter.write("'" + selectedStarItem.getFirstChild().getNodeValue() + "');");
						}
					}
					sqlWriter.close();
				} else {
					executionCode = 1;
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException threadException) {
			threadException.getMessage();
		}
		return executionCode;
	}
}
