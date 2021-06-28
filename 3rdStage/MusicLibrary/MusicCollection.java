import java.math.*;

/***************************************************************
Author: Lakshan Martin
Purpose: MusicCollection class to store songs as media type
         classes
Date: 16/10/2018
*****************************************************************/

public class MusicCollection
{
    //Class constants
    private static final int MAX_SONGS = 30;

    //Private class fields
    private Music[] musicArray;
    private int count;
    private double totalPlayTime;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Create default classes
    **********************************************************/
    public MusicCollection()
    {
        musicArray = new Music[MAX_SONGS];
        count = 0;
        totalPlayTime = 0.0;
    }      

    /*********************************************************
    *Alternate Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Initialise class fields
    **********************************************************/
    public MusicCollection(Music[] musicArray, int count, double totalPlayTime)
    {
        this.musicArray = musicArray;
        this.count = count;
        this.totalPlayTime = totalPlayTime;
    }

    /*************************************************************
    *Copy Constructor:
    *IMPORT: toCopy
    *EXPORT:
    *ASSERTION: Creates an object with an identical object state
    *           as import
    ***************************************************************/
    public MusicCollection (MusicCollection toCopy)
    {
        musicArray = toCopy.getMusicArray();
        count = toCopy.getCount();
        totalPlayTime = toCopy.getTotalPlayTime();
    }

    //ACCESSORS
    public Music[] getMusicArray()
    {
        return musicArray;
    }

    public int getCount()
    {
        return count;
    }

    public double getTotalPlayTime()
    {
        return totalPlayTime;
    }

    /*********************************************************
    *PURPOSE: Add new song
    *IMPORT: Music 
    *EXPORT: none
    *ASSERTION: 
    **********************************************************/
    public void addSong(Music newSong)
    {
        if(count > MAX_SONGS)
        {
            throw new IllegalArgumentException(
                "\nERROR: Maximum song capacity of 30 song has been reached\n");
        }
        else
        {
            musicArray[count] = newSong;
            count++;
            totalPlayTime = calcTotalPlayTime(newSong.getDuration());
        }
    }

    /*******************************************************
    *PURPOSE: Calculate total play time of library
    *IMPORT: addDuration (double) 
    *EXPORT totalDuration (double)
    *ASSERTION: none
    ********************************************************/
    private double calcTotalPlayTime(double addDuration)
    {
        BigDecimal total, toAdd;

        //Convert to BigDecimal values
        total = new BigDecimal(totalPlayTime);
        toAdd = new BigDecimal(addDuration);

        total = total.add(toAdd); //Perform addition
        total = total.setScale(2, RoundingMode.HALF_EVEN); //Round to 2 decimals

        return total.doubleValue();
    }
}
    
            
