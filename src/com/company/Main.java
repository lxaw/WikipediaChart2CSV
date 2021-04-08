package com.company;

/*
Written by Lex Whalen

This program is designed to scrape URLs from Wikipedia. Watch the video for more.

NOTE: this is not designed work with every single Wikipedia page, as many
Wikipedia charts are formatted differently with different classes and such.

Again watch the video for a better explanation.

Overall, this is more of a basis for what you can do.

 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Please note this project is rather specific to the URL I inputted. However, you can play around with it to work on other URLs.

        //Look into "Scraper" to see what you may want to change.

	    String url = "https://en.wikipedia.org/wiki/List_of_highest-grossing_anime_films";

	    Scraper scraper = new Scraper(url);

	    scraper.scrapeCSV();
    }
}
