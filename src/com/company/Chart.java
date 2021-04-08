/*
Written by Lex Whalen

This class stores the data found within the rows of a chart.
The collection of rows goes into this RowData[] array.

If you want to use this for your own purpose, I would change the
regex pattern here / get rid of it. If you get rid of it, you should also
get rid of the matcher for it.

 */

package com.company;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Chart {
    // holds row data
    private String name;
    private RowData[] rowDataArr;

    private Pattern pattern = Pattern.compile("\\d{4,}\\[.*\\]");

    public Chart(String name,int rowAmount)
    {
        this.name = name;
        this.rowDataArr = new RowData[rowAmount];
    }

    public RowData[] getRowDataArr() {
        return this.rowDataArr;
    }

    public void addRowsInMass(Elements tableRows)
    {
        for(int i=0;i<tableRows.size();i++)
        {
            Element rowElement = tableRows.get(i);

            // get the children of this row
            Elements childElements = rowElement.children();

            RowData currentRow = new RowData(childElements.size());

            for(int j = 0; j<childElements.size();j++)
            {
                String res = childElements.get(j).text().replaceAll("(\\$|,|¥)","");

                //for your own purpose, I would get rid of this matcher
                Matcher matcher = this.pattern.matcher(res);
                if(matcher.find())
                {
                    res = res.replaceAll("\\[.*\\]","");

                }
                currentRow.addData(j,res);
            }
            addRowData(i,currentRow);
        }

    }


    public void addRowData(int index,RowData rowData)
    {
        this.rowDataArr[index] = rowData;
    }
    public String getName()
    {
        return this.name;
    }

    public int getRowCount()
    {
        return this.rowDataArr.length;
    }
    public String toString()
    {
        return "Chart: " + this.name + " amount of rows: " + this.getRowCount();
    }
    public void printChart()
    {
        System.out.println(this.toString());
        for(int i=0; i< this.rowDataArr.length;i++)
        {
            this.rowDataArr[i].printArr();
        }
    }
}
