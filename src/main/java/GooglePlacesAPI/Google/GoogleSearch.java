package GooglePlacesAPI.Google;

import java.applet.Applet;
import java.net.*;
import java.awt.*;

public class GoogleSearch {

	public class Search extends Applet {
		TextField searchParameter;
		Choice searchEngine;
		Button searchButton;

		// initialize the display
		public void init() {
			setBackground(Color.white);
			searchParameter = new TextField(20);
			add(searchParameter);
			searchEngine = new Choice();
			searchEngine.addItem("AltaVista");
			searchEngine.addItem("WebCrawler");
			searchEngine.addItem("Yahoo");
			searchEngine.addItem("AskJeeves");
			searchEngine.addItem("Hotbot");
			searchEngine.addItem("Google");
			searchEngine.select(0);
			add(searchEngine);
			searchButton = new Button("Search");
			add(searchButton);
		}

		public boolean action(Event e, Object o) {
			if (e.target.equals(searchButton)) {
				try {
					sendSearch();
				} catch (Exception e1) {
					showStatus("Exception caught:" + e1.toString());
				}
			}
			return true;
		}

		public void sendSearch() throws Exception {
			String searchString = searchParameter.getText();
			if (searchString.equals("")) {
				showStatus("Must enter a search string");
				return;
			}
			String url;
			switch (searchEngine.getSelectedIndex()) {
			case 0:
				url = "http://www.altavista.digital.com/cgi-bin/query?pg=q&;what=web&fmt=.&q=";
				break;
			case 1:
				url = "http://www.webcrawler.com/cgi-bin/WebQuery?searchText=";
				break;
			case 2:
				url = "http://search.yahoo.com/bin/search?p=";
				break;
			case 3:
				url = "http://www.askjeeves.com/main/askjeeves.asp?ask=";
				break;
			case 4:
				url = "http://hotbot.lycos.com/?MT=";
				break;
			case 5:
				url = "://www.google.com/search?q=";
				break;
			default:
				showStatus("Invalid search engine selected.");
				return;
			}

			// encode the search data
			url += URLEncoder.encode(searchString);

			// launch the search engine
			showStatus("Connecting to search location " + url);
			getAppletContext().showDocument(new URL(url), "_top");
		}
	}
	
	public static void main(String [] args) {
		
	}
}
