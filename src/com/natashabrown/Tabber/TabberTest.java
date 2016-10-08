package com.natashabrown.Tabber;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jimmy on 02/03/2016.
 */
public class TabberTest
{
    public static void main(String[] args) throws IOException
    {
        TabbedFileReader tabbedFileReader = new TabbedFileReader("timetable_simple.test");

        List<String> fileContents = tabbedFileReader.readFromFile();

        System.out.println("File Contents: " + fileContents);

        int index = 0;

        System.out.println(fileContents.get(index));

        System.out.println(Arrays.toString(tabbedFileReader.getTabularData(fileContents.get(index))));

        System.out.println(tabbedFileReader.resolveEntryType(tabbedFileReader.getTabularData(fileContents.get(index))));

        System.out.println(tabbedFileReader.resolveObject(tabbedFileReader.getTabularData(fileContents.get(index))));

        System.out.println(tabbedFileReader.getEntries());

    }
}
