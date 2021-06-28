/***************************************************************
Author: Lakshan Martin
Purpose: Cassette Class to describe what is to be placed
         in the object
Date: 16/10/2018
******************************************************************/

public class Cassette extends Music
{
    //Private class fields
    private int trackNumber;
    private double start;
    private char side;

    /**************************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ***************************************************************/
    public Cassette()
    {
        super();
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
    public Cassette(String name, String artist, double duration,
    int trackNumber, double start, char side)
    {
        super(name, artist, duration);
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
    public Cassette(Cassette toCopy)
    {
        super(toCopy);
        trackNumber = toCopy.getTrackNumber();
        start = toCopy.getStart();
        side = toCopy.getSide();
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
    @Override
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
    *EXPORT: CassetteClass
    *ASSERTION: 
    **************************************************************/
    @Override
    public Cassette clone()
    {
        return new Cassette(
            getName(), getArtist(), getDuration(), trackNumber, start, side);
    }
}






























