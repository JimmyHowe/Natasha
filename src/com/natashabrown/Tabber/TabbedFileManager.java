package com.natashabrown.Tabber;

import com.natashabrown.entries.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The tabbed file manager handles the tabbed file data
 */
public class TabbedFileManager
{
    /**
     * List of entries in the tabbed file
     */
    protected List<String> entries = new ArrayList<>();

    /**
     * Returns all the entries
     *
     * @return
     */
    public List<String> getEntries()
    {
        return entries;
    }

    /**
     * Reads the tabbed file, returns false if there is a problem
     *
     * @param filePath
     * @return
     */
    public boolean readFromFile(String filePath)
    {
        if ( Files.exists(Paths.get(filePath)) )
        {
            try
            {
                this.entries = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());

                this.entries.remove(0);

                return true;
            } catch ( IOException e )
            {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    /**
     * Returns the count of entries
     *
     * @return
     */
    public int count()
    {
        return this.entries.size();
    }

    /**
     * Gets the object at position (index)
     *
     * @param index
     * @return
     */
    public Entry getAt(int index)
    {
        return this.resolveObjectAt(index);
    }

    /**
     * This method retursn the specific object based on the entry information
     *
     * @param index
     * @return
     */
    public Entry resolveObjectAt(int index)
    {
        String entry = this.entries.get(index);

        String[] tabbedData = this.getTabbedData(entry);

        switch (this.determineEntryType(tabbedData[0]))
        {
            case "Lecture":
                return new Lecture(tabbedData[0], tabbedData[1], tabbedData[2], tabbedData[3], tabbedData[4],
                        tabbedData[5], tabbedData[6], tabbedData[7], tabbedData[8], tabbedData[9]);
            case "Seminar":
                return new Seminar(tabbedData[0], tabbedData[1], tabbedData[2], tabbedData[3], tabbedData[4],
                        tabbedData[5], tabbedData[6], tabbedData[7], tabbedData[8], tabbedData[9]);
            case "Practical":
                return new Practical(tabbedData[0], tabbedData[1], tabbedData[2], tabbedData[3], tabbedData[4],
                        tabbedData[5], tabbedData[6], tabbedData[7], tabbedData[8], tabbedData[9]);
            case "Tutorial":
                return new Tutorial(tabbedData[0], tabbedData[1], tabbedData[2], tabbedData[3], tabbedData[4],
                        tabbedData[5], tabbedData[6], tabbedData[7], tabbedData[8], tabbedData[9]);
        }

        return null;
    }

    /**
     * Determins the type of entry based on the name and returns the class name
     *
     * @param name
     * @return
     */
    public String determineEntryType(String name)
    {
        int dotIndex = name.indexOf(".");

        switch( name.substring(dotIndex + 1, dotIndex + 2) )
        {
            case "L": return "Lecture";
            case "S": return "Seminar";
            case "P": return "Practical";
            case "T": return "Tutorial";
        }

        return "other";
    }

    /**
     * Splits the entry up using the tab character into an array of strings
     *
     * @param entry
     * @return
     */
    public String[] getTabbedData(String entry)
    {
        return entry.split("\t", -1);
    }
}
