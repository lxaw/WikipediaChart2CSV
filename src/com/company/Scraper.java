package com.company;

/*
Written by Lex Whalen

This class scrapes content from wikipedia URLs.
Each thing scraped by this class goes into "Chart[] charts"
The chart class is an array of RowData objects. Look into that class
to see more.

 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    private String seedURL;
    private Document doc;
    private Element content;
    private Elements tableElements;

    private Chart[] charts;

    public Scraper(String seedURL) throws IOException
    {
        this.seedURL = seedURL;
        setDoc();
        scrapeTables();
    }
    public void scrapeCSV() throws IOException
    {
        for(Chart c: this.charts)
        {
            CSVMaker.createCSV(c,c.getName());
        }
    }

    private void setDoc() throws IOException
    {
        this.doc = Jsoup.connect(seedURL).get();
        this.content = this.doc.getElementById("mw-content-text");
        this.tableElements = this.content.getElementsByClass("wikitable sortable plainrowheaders");

    }

    private void scrapeTables()
    {
        this.charts = new Chart[tableElements.size()];

        for(int i=0;i<this.tableElements.size();i++)
        {

            //looping through all the tables found

            Element currentTableElement = this.tableElements.get(i);
            String caption = this.tableElements.get(i).select("caption").text();

            Elements tBody = currentTableElement.select("tbody");
            Elements tR = tBody.select("tr");

            //zero is first index, which is the headers here
            Elements tableHeaders = tR.select("[scope=col]");

            //create the first row as the headers, so csv format correct
            RowData headersRow = new RowData(tableHeaders.size());

            //populate headersRow
            for(int headerIndex = 0; headerIndex<tableHeaders.size();headerIndex++)
            {
                Element currentHeader = tableHeaders.get(headerIndex);
                headersRow.addData(headerIndex,currentHeader.text());
            }
            if(!caption.equalsIgnoreCase("")){
                Chart currentChart = new Chart(caption,tR.size());
                currentChart.addRowsInMass(tR);
                this.charts[i] = currentChart;
            }else{
                String chartName = String.format("unknown_caption_%s",i);
                Chart currentChart = new Chart(chartName,tR.size());
                currentChart.addRowsInMass(tR);
                this.charts[i] = currentChart;
            }
        }

    }
}
