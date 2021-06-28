/***************************************************************
Author: Lakshan Martin
Purpose: Cassette Class to describe what is to be placed
         in the object
Date: 16/10/2018
******************************************************************/

public class CassetteClass
{
    //Private class fields
    private String name;
    private String artist;
    private double duration;
    private int trackNumber;
    private double start;
    private char side;

    /**************************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ***************************************************************/
    public CassetteClass()
    {
        name = "default song";
        artist = "default artist";
        duration = 1.11;
        trackNumber = 1;
        start = 1.11;
        side = 'A';
    }

    /**************************************************************
    *Alternate Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initalise all class fields with imported values
    ***************************************************************/
    public CassetteClass(String name, String artist, double duration,
    int trackNumber, double start, char side)
    {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.trackNumber = trackNumber;
        this.start = start;
        this.side = side;
    }

    /***********************************************************************
    *Copy Constructor:
    *IMPORT: inCassette
    *EXPORT: none
    *ASSERTION: Create an object with an identical object state as import
    ************************************************************************/
    public CassetteClass(CassetteClass toCopy)
    {
        name = toCopy.getName();
        artist = toCopy.getArtist();
        duration = toCopy.getDuration();
        trackNumber = toCopy.getTrackNumber();
        start = toCopy.getStart();
        side = toCopy.getSide();
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

    public double getStart()
    {
        return start;
    }

    public char getSide()
    {
        return side;
    }
    
    /**********************************************************
    *PURPOSE: Return play instructions as a string
    *IMPORT: none
    *EXPORT: instructions
    *ASSERTION:
    ***********************************************************/
    public String play()
    {
        String start, instructions;

        //Format start time to 2 decimal places
        start = String.format("%.2f", getStart());

        instructions = "\n" + getName() + " is track number " + getTrackNumber() + 
                       ", it starts at " + start + " minutes, on side " +
                       getSide() + "\n";
    
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
    *EXPORT: CassetteClass
    *ASSERTION: 
    **************************************************************/
    public CassetteClass clone()
    {
        return new CassetteClass(name, artist, duration, trackNumber, start, side);
    }
}






























