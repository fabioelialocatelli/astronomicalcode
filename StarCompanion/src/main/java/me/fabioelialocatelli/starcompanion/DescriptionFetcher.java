package me.fabioelialocatelli.starcompanion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DescriptionFetcher implements Callable<Integer> {

	private int connectionCode = 0;
	private BufferedReader source = null;
	private Document unrefinedPage = null;
	private File description = null;
	private File descriptionDirectory = null;
	private FileWriter descriptionWriter = null;
	private String cssStylesheet = null;
	private String descriptionsPath = null;
	private String documentClosure = null;
	private String documentHead = null;
	private String precedingData = null;
	private String selectedURL = null;
	private String selectedStar = null;
	private String textContent = null;
	private StringBuilder refinedHTML = null;
	private StringBuilder unrefinedHTML = null;
	private URL descriptionURL = null;
	private HttpURLConnection sourceConnection = null;

	public DescriptionFetcher(String url, String star, String descriptions) {
		this.selectedURL = url;
		this.selectedStar = star;
		this.descriptionsPath = descriptions;
	}

	@Override
	public Integer call() throws Exception {
		int executionCode = 0;
		try {
			descriptionURL = new URL(selectedURL);
			sourceConnection = (HttpURLConnection) descriptionURL.openConnection();
			connectionCode = sourceConnection.getResponseCode();

			if (connectionCode != 404) {

				source = new BufferedReader(new InputStreamReader(descriptionURL.openStream()));
				unrefinedHTML = new StringBuilder();

				while ((precedingData = source.readLine()) != null) {
					unrefinedHTML.append(precedingData + "\n");
				}

				unrefinedPage = Jsoup.parse(unrefinedHTML.toString());
				textContent = unrefinedPage.body().text().replace("Return to STARS.", "");
				refinedHTML = new StringBuilder(textContent);
				cssStylesheet = "body{font-family: Helvetica, Sans-Serif; font-size: 1.25em; background-color: azure; text-align: justify;}";

				documentHead = "<head><title>" + selectedStar + "</title>" + "<style>" + cssStylesheet + "</style>"
						+ "</head><body>";
				documentClosure = "</body>";

				refinedHTML.insert(0, documentHead);
				refinedHTML.append(documentClosure);

				descriptionDirectory = new File(descriptionsPath);
				descriptionDirectory.mkdirs();
				description = new File(descriptionDirectory, selectedStar + ".html");

				descriptionWriter = new FileWriter(description);
				descriptionWriter.write(refinedHTML.toString());
				descriptionWriter.close();
				source.close();
			} else {
				executionCode = 1;
			}
		} catch (IOException threadException) {
			threadException.getMessage();
		}
		return executionCode;
	}
}
