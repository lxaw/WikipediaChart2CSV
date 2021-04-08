package com.company;

/*
Written by Lex Whalen
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CSVMaker {

    public static void createCSV(Chart chart,String outCSVFileName) throws IOException
    {
        String fN = setCSVName(outCSVFileName);

        String eol = System.getProperty("line.separator");

        try
        {
            try(Writer writer = new FileWriter(fN))
            {
                RowData[] chartRows = chart.getRowDataArr();
                for(RowData row:chartRows)
                {
                    String[] stringData = row.getRowData();
                    for(int i = 0;i<stringData.length;i++)
                    {
                        if(i<stringData.length-1)
                        {
                            writer.append(stringData[i])
                                    .append(",");
                        }
                        else
                        {
                            writer.append(stringData[i]);
                        }

                    }
                    writer.append(eol);
                }
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.err);
        }
    }

    private static String setCSVName(String fN)
    {
        return fN + ".csv";
    }

}
