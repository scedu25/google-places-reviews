@echo off

:while
	javac -cp "google-places-api-java-2.1.7.jar;google-maps-services-0.2.4.jar;httpclient-4.3.5.jar;httpcore-4.3.2.jar;commons-codec-1.6.jar;commons-logging-1.1.3.jar;commons-io-1.3.2.jar;json-20140107.jar;" App.java
	java -cp "google-places-api-java-2.1.7.jar;google-maps-services-0.2.4.jar;httpclient-4.3.5.jar;httpcore-4.3.2.jar;commons-codec-1.6.jar;commons-logging-1.1.3.jar;commons-io-1.3.2.jar;json-20140107.jar;"  App
	del App.class
	timeout /t 20 /nobreak
	goto :while