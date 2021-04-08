package com.company;

/*
Written by Lex Whalen

This class stores Strings in an array called "rowData".
The Strings are what is found in each row. This class later is used
in Chart, since a chart is basically an array of RowData objects.
The header for the chart is considered the first row.

 */

public class RowData {

    private String[] rowData;

    public RowData(int arrSize)
    {
        this.rowData = new String[arrSize];
    }
    public void addData(int index,String data)
    {
        this.rowData[index] = data;
    }

    public String[] getRowData()
    {
        return this.rowData;
    }
    public void printArr()
    {
        for(int i =0;i<this.rowData.length;i++)
        {
            System.out.println(this.rowData[i]);
        }
    }

}
