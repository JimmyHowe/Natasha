package com.natashabrown;

import com.natashabrown.entries.Entry;
import com.natashabrown.entries.Lecture;
import com.natashabrown.entries.Seminar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jimmy on 02/03/2016.
 */
public class TimetableTest
{
    Timetable timetable = new Timetable();

    @Before
    public void setUp() throws Exception
    {
//        timetable.fill(this.getMockEntries());
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testGetEntries() throws Exception
    {

    }

    @Test
    public void testAddEntry() throws Exception
    {
        timetable.addEntry(new Lecture("ACCP002.L#", "Friday", "14:05", "15:55", "3-6, 8-9", "C.LTW1", "100", "50", "Jimmy Howe", "MGTACF_PG"));

        System.out.println(timetable.lookupAllEntries("ACCP002.L#"));
    }

    @Test
    public void testLookupAllEntries() throws Exception
    {

    }

    @Test
    public void testFill() throws Exception
    {

    }

    private List<Entry> getMockEntries()
    {
        List<Entry> entries = new ArrayList<>();

        entries.add(new Lecture("ACCP002.L#", "Friday", "14:05", "15:55", "3-6, 8-9", "C.LTW1", "100", "50", "Jimmy Howe", "MGTACF_PG"));
        entries.add(new Seminar("ACCP002.S#", "Friday", "14:05", "15:55", "3-6, 8-9", "C.LTW1", "100", "50", "Jimmy Howe", "MGTACF_PG"));

        return entries;
    }
}