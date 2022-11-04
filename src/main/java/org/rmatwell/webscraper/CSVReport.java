package org.rmatwell.webscraper;


import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Richard Atwell
 */

public class CSVReport {

    private String fileName;

    public CSVReport(String fileName) {

        this.fileName = fileName;
    }

    public void writeDataAtOnce(ArrayList<Listing> listings){


        try {

            PrintWriter csv = new PrintWriter(fileName);

            for(Listing listing: listings){
                csv.println(listing.toString());
            }

            csv.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



}
