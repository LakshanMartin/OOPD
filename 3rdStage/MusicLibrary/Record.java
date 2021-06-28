/******************************************************************
Author: Lakshan Martin
Purpose: Record Class to describe what is to be placed
         in the object
Date: 16/10/2018
********************************************************************/

public class Record extends Music
{
    //Private class fields
    private int trackNumber;
    private String playSpeed;

    /**************************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ***************************************************************/
    public Record()
    {
        super();
        trackNumber = 1;
        playSpeed = "33 1/3 RPM";
    }

    /******************************************************************
    *Alternate Constructor:
    *IMPORT: inName, inArtist, inDuration, inTrackNumber, inPlaySpeed
    *EXPORT: none
    *ASSERTION: Initialise all class fields based on import values
    *******************************************************************/
    public Record(String name, String artist, double duration,
    int trackNumber, String playSpeed)
    {
        super(name, artist, duration);
        this.trackNumber = trackNumber;
        this.playSpeed = playSpeed;
    }

    /*******************************************************************
    *Copy Constructor:
    *IMPORT: inRecord
    *EXPORT: none
    *ASSERTION: Create an object with identical object state as import
    ********************************************************************/
    public Record(Record toCopy)
    {
        super(toCopy);
        trackNumber = toCopy.getTrackNumber();
        playSpeed = toCopy.getPlaySpeed();
    }

    //ACCESSORS
    public String getName()
    {
        return super.getName();
    }

    public String getArtist()
    {
        return super.getArtist();
    }

    public double getDuration()
    {
        return super.getDuration();
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
    @Override
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
    @Override
    public boolean search(String toFind)
    {
        boolean found;

        found = false;

        if(getName().equals(toFind))
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
    @Override
    public Record clone()
    {
        return new Record(
            getName(), getArtist(), getDuration(), trackNumber, playSpeed);
    }
} 
    
    
