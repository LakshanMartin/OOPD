/************************************************************
Author: Lakshan Martin
Purpose: Digital Class to describe what is to be placed
         in the object
Date: 16/10/2018
*************************************************************/

public class DigitalClass
{
    //Private class fields
    private String name;
    private String artist;
    private double duration;
    private String type;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ************************************************************/
    public DigitalClass()
    {
        name = "default song";
        artist = "default artist";
        duration = 1.11;
        type = "wav";
    }

    /***********************************************************
    *Alternate Constructor:
    *IMPORT: inName, inArtist, inDuration, inType
    *EXPORT: none
    *ASSERTION: Initialise all class fields with imported values
    ************************************************************/
    public DigitalClass(String name, String artist, double duration,
    String type)
    {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.type = type;
    }

    /******************************************************************
    *Copy Constructor:
    *IMPORT: inDigital
    *EXPORT: none
    *ASSERTION: Create an object with identical object state as import
    *******************************************************************/
    public DigitalClass(DigitalClass toCopy)
    {
        name = toCopy.getName();
        artist = toCopy.getArtist();
        duration = toCopy.getDuration();
        type = toCopy.getType();
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

    public String getType()
    {
        return type;
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

        instructions = "\n" + getName() + " requires the " + getType() + 
                        " to play\n";

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
    *EXPORT: DigitalClass
    *ASSERTION: 
    **************************************************************/
    public DigitalClass clone()
    {
        return new DigitalClass(name, artist, duration, type);
    }
}
