# google-places-reviews
Sentiment analysis of Google Places reviews

Google Places Documentation - Eric


There are 3 java programs I wrote for this project:

•	DataCollection.java – used to retrieve review data from Google Places API

•	SentenceParser.java – used to prepare training data for sentiment analysis

•	OpenNLPCategorizer.java – used to perform sentiment analysis of Google Places reviews and to predict ratings of reviews from testing data


DataCollection.java


This class retrieves hotel and restaurant reviews from Google Places API, getting a maximum of five reviews per hotel/restaurant. A text file is created for each place with rating and text of review written to the file.

getReviews method:

This function retrieves reviews from Place objects in places List using the Place's getDetails() method. The getDetails() method returns a Place with more details, including reviews and author, time posted, and rating (1-5 scale) assigned by reviewer.

getNames method:

This function is used to append to a text file listing the names of all places for which data was collected. This text file is used for future Named Entity Recognition (NER) purposes, which would ideally allow the program to recognize these entities as either hotels or restaurants.

Main method:

New GooglePlaces client is established using Google Places API key. Latitude and longitude of search location is provided, and search radius is set at 50,000 meters (50 km). Maximum of 60 results are returned per function call. Current latitude and longitude is approximately set at San Francisco. Types of places searched for are specified as TYPE_LODGING (hotels) and TYPE_RESTAURANT (restaurant).


SentenceParser.java


This class prepares training data by allowing user to manually assign sentiments to individual sentences from collected reviews. Results in "Hotel Training Data.txt" and "Restaurant Training Data.txt" files.

parseDirectory method:

Parses input File folder for text files and reads lines from each file, adding text to String text. This text is then used as an input for the getSentences() method, which returns a formatted String.

getSentences method:

Returns input String text in formatted manner, with one sentence per new line.

Main method:

Hotel and restaurant formatted text is written to "Hotel Training Data.txt" and "Restaurant Training Data.txt" respectively.


OpenNLPCategorizer.java


This class uses the OpenNLP Document Categorizer to perform sentiment analysis on Google Places review data. It requires manually labeled training data and testing data and predicts positive/negative/neutral sentiment of a review as well as the rating of a review on a 1-5 scale.

trainModel method:

Trains Document Categorizer model using training data from text file to recognize categories.

classifyNewReview method:

Labels input testing review as positive, negative, or neutral sentiment based on trained model.

assingRating method:

Assigns rating to input testing reviews based on trained model using the OpenNLP Document Categorizer. Values in outcomes array correspond to percentages of review's likelihood to fall under a category.
