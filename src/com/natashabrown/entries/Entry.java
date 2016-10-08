package com.natashabrown.entries;

abstract public class Entry
{
    private String name;
    private String day;
    private String startTime;
    private String endTime;
    private String weekPattern;
    private String location;
    private String roomSize;
    private String classSize;
    private String staffMember;
    private String department;

    public Entry(String name, String day, String startTime, String endTime, String weekPattern, String location,
                 String roomSize, String classSize, String staffMember, String department)
    {
        this.name = name;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekPattern = weekPattern;
        this.location = location;
        this.roomSize = roomSize;
        this.classSize = classSize;
        this.staffMember = staffMember;
        this.department = department;
    }

    public String getName()
    {
        return name;
    }

    public String getDay()
    {
        return day;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public String getWeekPattern()
    {
        return weekPattern;
    }

    public String getLocation()
    {
        return location;
    }

    public String getRoomSize()
    {
        return roomSize;
    }

    public String getClassSize()
    {
        return classSize;
    }

    public String getStaffMember()
    {
        return staffMember;
    }

    public String getDepartment()
    {
        return department;
    }

    public String getEntry()
    {
        return name + '\t' + day + '\t' + startTime + '\t' + endTime + '\t' + weekPattern + '\t' + location + '\t'
                + roomSize + '\t' + classSize + '\t' + staffMember + '\t' + department;
    }
}