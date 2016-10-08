package com.natashabrown;// Skeleton GUI and main program for the Timetable
// processes files of the form
// 	nameField, dayField, start time, end time, week weekPatternField, locationField, roomSizeField classSizeField, class classSizeField, staffMemberField, department

import com.natashabrown.Tabber.TabbedFileManager;
import com.natashabrown.Tabber.TabbedFileReader;
import com.natashabrown.entries.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TimetableGUI extends JFrame implements ActionListener
{
    /*
     * Textfields for nameField, dayField, start time, end time
     * weekPatternField, locationField, roomSizeField, classSizeField, staffMemberField, departmentField
     */
    //Module code of class
    private JTextField nameField = new JTextField(10);
    //Day of the week
    private JTextField dayField = new JTextField(10);
    //Start Time
    private JTextField startTimeField = new JTextField(4);
    //End Time: hour
    private JTextField endTimeField = new JTextField(4);
    //Week weekPatternField
    private JTextField weekPatternField = new JTextField(10);
    //Location of class
    private JTextField locationField = new JTextField(10);
    //Room classSizeField
    private JTextField roomSizeField = new JTextField(15);
    //Class classSizeField
    private JTextField classSizeField = new JTextField(10);
    //Staff member nameField of course
    private JTextField staffMemberField = new JTextField(20);
    //Department of course 
    private JTextField departmentField = new JTextField(20);

    /*
     * Labels for nameField, dayField, startTimeField, startMin, endTimeField, endMin
     * weekPatternField, locationField, roomSizeField, classSizeField, staffMemberField, departmentField
     */
    private JLabel nameLabel = new JLabel(" Module Code:");
    private JLabel dayLabel = new JLabel(" Day:");
    private JLabel startTimeLabel = new JLabel(" Start Time:");
    private JLabel endTimeLabel = new JLabel(" End Time:");
    private JLabel weekPatternLabel = new JLabel(" Week Pattern:");
    private JLabel locationLabel = new JLabel(" Location:");
    private JLabel roomSizeLabel = new JLabel(" Room Size:");
    private JLabel classSizeLabel = new JLabel(" Class Size:");
    private JLabel staffMemberLabel = new JLabel(" Staff:");
    private JLabel departmentLabel = new JLabel(" Department:");

    /*
     * Combobox to choose type of class: Lecture, Practical, Seminar
     */
    private String[] classTypes = { "Lecture", "Practical", "Seminar", "Tutorial" };
    private JComboBox classTypeCombo = new JComboBox(classTypes);

    /*
     * Buttons to Add, Look up singular or all entries(classes)
     */
    private JButton addEntryButton = new JButton("Add Entry");
    private JButton lookupAllButton = new JButton("Look up all");
    private JButton saveToHtmlButton = new JButton("Save to HTML");

    private JButton testDataButton = new JButton("Test Data");
    private JButton dumpLastSearchedButton = new JButton("Dump Last Searched");

    private JTextArea outputArea = new JTextArea(5, 50); //creates display area to show entries

    private static Timetable timetable = new Timetable(); //Stores timetable entries

    private static TabbedFileReader tabbedFileReader = new TabbedFileReader("timetable_simple.txt");

    public static void main(String[] args)
    {
        TimetableGUI application = new TimetableGUI();

        try
        {
            timetable.fill(tabbedFileReader.getEntries());
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    // set up the GUI
    public TimetableGUI()
    {
        super("CSCU9T4 Timetabler");

        setLayout(new FlowLayout());

        add(nameLabel);
        add(nameField);
        nameField.setEditable(true);

        add(dayLabel);
        add(dayField);
        dayField.setEditable(true);

        add(classTypeCombo);
        classTypeCombo.addActionListener(this);

        add(startTimeLabel);
        add(startTimeField);
        startTimeField.setEditable(true);

        add(endTimeLabel);
        add(endTimeField);
        endTimeField.setEditable(true);

        add(weekPatternLabel);
        add(weekPatternField);
        weekPatternField.setEditable(true);

        add(locationLabel);
        add(locationField);
        locationField.setEditable(true);

        add(roomSizeLabel);
        add(roomSizeField);
        roomSizeField.setEditable(true);

        add(classSizeLabel);
        add(classSizeField);
        classSizeField.setEditable(true);

        add(staffMemberLabel);
        add(staffMemberField);
        staffMemberField.setEditable(true);

        add(departmentLabel);
        add(departmentField);
        departmentField.setEditable(true);

        add(addEntryButton);
        addEntryButton.addActionListener(this);

        add(lookupAllButton);
        lookupAllButton.addActionListener(this);

        add(saveToHtmlButton);
        saveToHtmlButton.addActionListener(this);

        add(testDataButton);
        testDataButton.addActionListener(this);

        add(dumpLastSearchedButton);
        dumpLastSearchedButton.addActionListener(this);

        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 500);
        setVisible(true);

        blankDisplay();

    } // constructor

    // listen for and respond to GUI events
    public void actionPerformed(ActionEvent event)
    {
        /*
            Sets the labels context
         */
        if ( event.getSource() == classTypeCombo )
        {

            if ( classTypeCombo.getSelectedIndex() == 0 ) locationLabel.setText("Lecture Room Size:");
            else if ( classTypeCombo.getSelectedIndex() == 1 ) locationLabel.setText("Practical Room Size:");
            else if ( classTypeCombo.getSelectedIndex() == 2 ) locationLabel.setText("Seminar Room Size");
            else if ( classTypeCombo.getSelectedIndex() == 3 ) locationLabel.setText("Tutorial Room Size");
        }

        if ( event.getSource() == addEntryButton )
        {
            try
            {
                if ( CheckClassSize() == false )
                {
                    outputArea.setText("Class Size is bigger than Room Size - Adding entry prevented");
                } else if ( validateFields() == false )
                {
                    outputArea.setText("Invalid Entry - Please make sure all textfields are filled.");
                } else
                {
                    String name = nameField.getText();
                    String day = dayField.getText();
                    String weekPattern = weekPatternField.getText();
                    String location = locationField.getText();
                    String startTime = startTimeField.getText();
                    String endTime = endTimeField.getText();
                    String staffMember = staffMemberField.getText();
                    String department = departmentField.getText();
                    String roomSize = roomSizeField.getText();
                    String classSize = classSizeField.getText();

                    Entry entry = null;

                    switch ( classTypeCombo.getSelectedIndex() )
                    {
                        case 0: // Lecture
                            entry = new Lecture(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staffMember, department);
                        case 1: // Practical
                            entry = new Practical(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staffMember, department);
                        case 2: // Seminar
                            entry = new Seminar(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staffMember, department);
                        case 3: // Tutorial
                            entry = new Seminar(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staffMember, department);
                    }

                    if(entry != null)
                    {
                        if ( timetable.entryExists(entry) )
                        {
                            outputArea.setText("Record for " + entry.getName().toUpperCase() + " already exists!");
                        } else {
                            timetable.addEntry(entry);
                            outputArea.setText("Record added\n" + entry.getEntry());
                        }
                    }
                }
            } catch ( Exception e1 )
            {
                outputArea.setText("ERROR 1: Invalid Entry :- Make sure the correct data is entered");
            }

            blankDisplay();
        }

        /*
         * Lookup all button looks up all and displays entries that match the nameField entered
         */
        if ( event.getSource() == lookupAllButton )
        {
            try
            {
                String name = nameField.getText(); //required for looking up entries of the same nameField

                String result = timetable.lookupAllEntries(name); //result stores entries found

                outputArea.setText("Module Code Day  StartTime  EndTime   Department   Location    Room    Class     Staff     Week  " + "\n" + result); //displays entries

            } catch ( Exception e3 )
            {
                outputArea.setText("ERROR 3: Invalid Entry");
            }
            blankDisplay();
        }

        /*
         * Saves entries to html file
         */
        if ( event.getSource() == saveToHtmlButton )
        {
            if(timetable.getLastSearchedEntries().isEmpty())
            {
                outputArea.setText("You need to search for an entry first");

                return;
            }

            HtmlOuput.save(timetable.getLastSearchedEntries());

            outputArea.setText("Save results to html");

            blankDisplay();
        }

        if ( event.getSource() == dumpLastSearchedButton )
        {
            String output  = "";

            for ( int i = 0; i < timetable.getLastSearchedEntries().size(); i++ )
            {
                output += timetable.getLastSearchedEntries().get(i).getEntry() + "\n";
            }

            outputArea.setText(output);
        }

        /*
         * Saves entries to html file
         */
        if ( event.getSource() == testDataButton )
        {
            nameField.setText("ACCP002.L#");
            dayField.setText("Wednesday");
            weekPatternField.setText("1, 2, 3, 4");
            locationField.setText("Glasgow");
            startTimeField.setText("11:07");
            endTimeField.setText("12:07");
            staffMemberField.setText("Jimmy Howe");
            departmentField.setText("Glasgow");
            roomSizeField.setText("100");
            classSizeField.setText("50");
        }
    }

    // actionPerformed
    public boolean CheckClassSize()
    {
        /*
         * This method checks whether class classSizeField exceeds roomSizeField classSizeField
         * returns false if cSize > r condition is met
         * 
         */

        String roomSize = roomSizeField.getText();
        String classSize = classSizeField.getText();

        int result = roomSize.compareTo(classSize);

        System.out.println(result);

        if ( result > 0 )
        {
            return false;
        }

        return true;
    }

    public boolean validateFields()
    {
        /*
         * Method checks if entry is valid for adding data
         * All the variables below need to be filled in order to add data
         */

        ArrayList<String> fields = new ArrayList<String>();

        fields.add(nameField.getText());
        fields.add(dayField.getText());
        fields.add(startTimeField.getText());
        fields.add(endTimeField.getText());
        fields.add(weekPatternField.getText());
        fields.add(locationField.getText());
        fields.add(staffMemberField.getText());
        fields.add(roomSizeField.getText());
        fields.add(classSizeField.getText());
        fields.add(departmentField.getText());

        for ( int i = 0; i < fields.size(); i++ )
        {
            if(fields.get(i).equals(""))
            {
                return false;
            }
        }

        return true;
    }

    public void blankDisplay()
    {
        nameField.setText("");
        dayField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
        weekPatternField.setText("");
        locationField.setText("");
        roomSizeField.setText("");
        classSizeField.setText("");
        staffMemberField.setText("");
        departmentField.setText("");

    }// blankDisplay

} // TimetableGUI

