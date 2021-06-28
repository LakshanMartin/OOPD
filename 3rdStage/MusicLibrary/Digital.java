/************************************************************
Author: Lakshan Martin
Purpose: Digital Class to describe what is to be placed
         in the object
Date: 16/10/2018
*************************************************************/

public class Digital extends Music
{
    //Private class fields
    private String type;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: Initialise all class fields with default values
    ************************************************************/
    public Digital()
    {
        super();
        type = "wav";
    }

    /***********************************************************
    *Alternate Constructor:
    *IMPORT: inName, inArtist, inDuration, inType
    *EXPORT: none
    *ASSERTION: Initialise all class fields with imported values
    ************************************************************/
    public Digital(String name, String artist, double duration,
    String type)
    {
        super(name, artist, duration);
        this.type = type;
    }

    /******************************************************************
    *Copy Constructor:
    *IMPORT: inDigital
    *EXPORT: none
    *ASSERTION: Create an object with identical object state as import
    *******************************************************************/
    public Digital(Digital toCopy)
    {
        super(toCopy);
        type = toCopy.getType();
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
    @Override
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
    *EXPORT: DigitalClass
    *ASSERTION: 
    **************************************************************/
    @Override
    public Digital clone()
    {
        return new Digital(getName(), getArtist(), getDuration(), type);
    }
}
