/**
 * Author: Eric Du
 * This program was used to split sample review data into sentences in preparation for manual labeling of
 * training data. 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Locale;

/**
 * This class prepares training data by allowing user to manually assign sentiments to individual sentences from 
 * collected reviews. Results in "Hotel Training Data.txt" and "Restaurant Training Data.txt" files.
 * @author EDU
 *
 */
public class SentenceParser {

	/**
	 * Parses input File folder for text files and reads lines from each file, adding text to String text. This 
	 * text is then used as an input for the getSentences() method, which returns a formatted String.
	 * @param folder
	 * @return
	 */
	public static String parseDirectory(final File folder) {
		String text = "";
		for (final File file : folder.listFiles()) {
			FileReader fr = null;
			BufferedReader br = null;
			String line = "";
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				br.readLine();
				while ((line = br.readLine()) != null) {
					if (line.startsWith("Rating:")) {}
					else if (line.startsWith("Text:")) {
						line = line.substring(6);
						text += line;
					}
					else if (line.startsWith("Author:")) {
						line = line.substring(7);
						text += line;
					}
					else if (line.startsWith("Time:")) {
						line = line.substring(5);
						text += line;
					}
					else if (line.startsWith("\n")) {}
					else {
						text += line;
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					fr.close();
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return getSentences(text);
	}
	
	/**
	 * Returns input String text in formatted manner, with one sentence per new line.
	 * @param text
	 * @return
	 */
	public static String getSentences(String text) {
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		String output = "";
		iterator.setText(text);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			output += text.substring(start, end) + "\n";
		}
		
		return output;
	}
	
	public static void main(String[] args) throws IOException {
		File hotels = new File("hotel-training");
		File restaurants = new File("restaurant-training");
		String hotelTraining = SentenceParser.parseDirectory(hotels);
		String restaurantTraining = SentenceParser.parseDirectory(restaurants);
		
		/* 
		 * Hotel and restaurant formatted text is written to "Hotel Training Data.txt" and "Restaurant Training
		 * Data.txt" respectively.
		 */
		BufferedWriter bw = new BufferedWriter(new FileWriter("Hotel Training Data.txt"));
		bw.write(hotelTraining);
		bw.close();
		
		bw = new BufferedWriter(new FileWriter("Restaurant Training Data.txt"));
		bw.write(restaurantTraining);
		bw.close();
		
		// Below is a test of the getSentences() method. It separates each sentence onto new lines.
		// System.out.println(Sentences.getSentences("This is a test. This is a T.L.A. test. Now with a Dr. in it."));
	}

}
