package com.natashabrown;// An implementation of a Training Record as an ArrayList

import com.natashabrown.entries.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Timetable
{
    private List<Entry> entries = new ArrayList<>();

    private List<Entry> lastSearchedEntries = new ArrayList<>();

    public List<Entry> getEntries()
    {
        return entries;
    }

    public List<Entry> getLastSearchedEntries()
    {
        return lastSearchedEntries;
    }

    public boolean entryExists(Entry entry)
    {
        for ( int i = 0; i < entries.size(); i++ )
        {
            if(entries.get(i).getName().toUpperCase().equals(entry.getName().toUpperCase()))
            {
                return true;
            }
        }

        return false;
    }

    public void addEntry(Entry entry)
    {
        entries.add(entry);
    }

    public String lookupAllEntries(String name)
    {
        if ( ! this.lastSearchedEntries.isEmpty() )
        {
            this.lastSearchedEntries = new ArrayList<>();
        }

        ListIterator<Entry> entryListIterator = entries.listIterator();

        String result = "";

        while ( entryListIterator.hasNext() )
        {
            Entry currentEntry = entryListIterator.next();

            if ( currentEntry.getName().toUpperCase().contains(name.toUpperCase()) )
            {
                this.lastSearchedEntries.add(currentEntry);

                System.out.println(currentEntry.getClass().getSimpleName());

                if( currentEntry.getClass().getSimpleName().equals("Lecture") )
                {
                    result += currentEntry.getEntry().toUpperCase() + "\n";
                } else {
                    result += currentEntry.getEntry() + "\n";
                }
            }

        }

        if ( result.equals("") ) result = "No Entries Found";

        return result;
    }

    /**
     * Fill the entries variable with the passed entries
     *
     * @param entries
     */
    public void fill(List<Entry> entries)
    {
        this.entries = entries;
    }
} // Timetable