/******************************************************************
Author: Lakshan Martin
Purpose: Record Class to describe what is to be placed
         in the object
Date: 16/10/2018
********************************************************************/

public class RecordClass
{
    //Private class fields
    private String name;
    private String artist;
    private double duration;
    private int trackNumber;
    private String playSpeed;

    /**************************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ***************************************************************/
    public RecordClass()
    {
        name = "default song";
        artist = "default artist";
        duration = 1.11;
        trackNumber = 1;
        playSpeed = "33 1/3 RPM";
    }

    /******************************************************************
    *Alternate Constructor:
    *IMPORT: inName, inArtist, inDuration, inTrackNumber, inPlaySpeed
    *EXPORT: none
    *ASSERTION: Initialise all class fields based on import values
    *******************************************************************/
    public RecordClass(String name, String artist, double duration,
    int trackNumber, String playSpeed)
    {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.trackNumber = trackNumber;
        this.playSpeed = playSpeed;
    }

    /*******************************************************************
    *Copy Constructor:
    *IMPORT: inRecord
    *EXPORT: none
    *ASSERTION: Create an object with identical object state as import
    ********************************************************************/
    public RecordClass(RecordClass toCopy)
    {
        name = toCopy.getName();
        artist = toCopy.getArtist();
        duration = toCopy.getDuration();
        trackNumber = toCopy.getTrackNumber();
        playSpeed = toCopy.getPlaySpeed();
    }

    //ACCESSORS
    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public double getDuration()
    {
        return duration;
    }

    public int getTrackNumber()
    {
        return trackNumber;
    }

    public String getPlaySpeed()
    {
        return playSpeed;
    }

    /*************************************************************
    *PURPOSE: Return play instructions as a string
    *IMPORT: none
    *EXPORT: instructions
    *ASSERTION:
    **************************************************************/
    public String play()
    {
        String instructions;
    
        instructions = "\n" + getName() + " is track number " + getTrackNumber() + 
                       " and the record speed is to be played at " + 
                       getPlaySpeed() + "\n";

        return instructions;
    }

    /*************************************************************
    *PURPOSE: Identify if song equals search term
    *IMPORT: none
    *EXPORT: boolean
    *ASSERTION: true if song equals search term
    **************************************************************/
    public boolean search(String toFind)
    {
        boolean found;

        found = false;

        if(name.equals(toFind))
        {
            found = true;
        }

        return found;
    }

    /*************************************************************
    *PURPOSE: Create clone of object data
    *IMPORT: none
    *EXPORT: RecordClass
    *ASSERTION: 
    **************************************************************/
    public RecordClass clone()
    {
        return new RecordClass(name, artist, duration, trackNumber, playSpeed);
    }
} 
    
    
