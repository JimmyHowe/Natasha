package com.natashabrown.Tabber;

import com.natashabrown.entries.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads entries from tabbed file
 */
public class TabbedFileReader
{
    private String tabbedFilePath;

    /**
     * Initialize the TabbedFileReader with the desired path
     *
     * @param tabbedFilePath
     */
    public TabbedFileReader(String tabbedFilePath)
    {
        this.tabbedFilePath = tabbedFilePath;
    }

    public List<Entry> getEntries() throws IOException
    {
        List<Entry> entries = new ArrayList<>();

        List<String> fileContents = this.readFromFile();

        for ( int i = 0; i < fileContents.size(); i++ )
        {
            entries.add(this.resolveObject(this.getTabularData(fileContents.get(i))));
        }

        return entries;
    }

    /**
     * Reads from the specified file and creates a List of strings with each line in it
     *
     * @return
     * @throws IOException
     */
    public List<String> readFromFile() throws IOException
    {
        List<String> fileContents = Files.readAllLines(Paths.get(this.tabbedFilePath), Charset.defaultCharset());

        fileContents.remove(0);

        return fileContents;
    }

    /**
     * Splits the file contents line of text into fields separated by tabs
     *
     * @param fileLine
     * @return
     */
    public String[] getTabularData(String fileLine)
    {
        return fileLine.split("\t", -1);
    }

    /**
     * Takes tabular data and creates objects based on class type
     *
     * @param tabularData
     * @return
     */
    public Entry resolveObject(String[] tabularData)
    {
        switch ( resolveEntryType(tabularData) )
        {
            case "L":
                return new Lecture(tabularData[0], tabularData[1], tabularData[2], tabularData[3], tabularData[4],
                        tabularData[5], tabularData[6], tabularData[7], tabularData[8], tabularData[9]);
            case "S":
                return new Seminar(tabularData[0], tabularData[1], tabularData[2], tabularData[3], tabularData[4],
                        tabularData[5], tabularData[6], tabularData[7], tabularData[8], tabularData[9]);
            case "C":
                return new Practical(tabularData[0], tabularData[1], tabularData[2], tabularData[3], tabularData[4],
                        tabularData[5], tabularData[6], tabularData[7], tabularData[8], tabularData[9]);
            case "T":
                return new Tutorial(tabularData[0], tabularData[1], tabularData[2], tabularData[3], tabularData[4],
                        tabularData[5], tabularData[6], tabularData[7], tabularData[8], tabularData[9]);
        }

        return null;
    }

    /**
     * Returns the type of class from the tabular data
     *
     * @param tabularData
     * @return
     */
    public String resolveEntryType(String[] tabularData)
    {
        String nameField = tabularData[0];

        int dotIndex = nameField.indexOf(".");

        return nameField.substring(dotIndex + 1, dotIndex + 2);
    }
}
