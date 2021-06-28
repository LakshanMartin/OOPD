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
    private static final int MAX_SONGS = 10;

    //Private class fields
    private RecordClass[] rArray;
    private CassetteClass[] cArray;
    private DigitalClass[] dArray;
    private int rCount;
    private int cCount;
    private int dCount;
    private double totalPlayTime;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Create default classes
    **********************************************************/
    public MusicCollection()
    {
        rCount = 0;
        rArray = new RecordClass[MAX_SONGS];
        cCount = 0;
        cArray = new CassetteClass[MAX_SONGS];
        dCount = 0; 
        dArray = new DigitalClass[MAX_SONGS];
        totalPlayTime = 0.0;
    }      

    /*************************************************************
    *Copy Construction:
    *IMPORT: inMusicCollection
    *EXPORT:
    *ASSERTION: Creates an object with an identical object state
    *           as import
    ***************************************************************/
    public MusicCollection (MusicCollection toCopy)
    {
        rArray = toCopy.getRArray();
        rCount = toCopy.getRCount();

        cArray = toCopy.getCArray();
        cCount = toCopy.getCCount();

        dArray = toCopy.getDArray();
        dCount = toCopy.getDCount();
    }

    //ACCESSORS
    public RecordClass[] getRArray()
    {
        return rArray;
    }

    public CassetteClass[] getCArray()
    {
        return cArray;
    }
    
    public DigitalClass[] getDArray()
    {
        return dArray;
    }

    public int getRCount()
    {
        return rCount;
    }

    public int getCCount()
    {
        return cCount;
    }

    public int getDCount()
    {
        return dCount;
    }

    public double getTotalPlayTime()
    {
        return totalPlayTime;
    }

    /*******************************************************
    *PURPOSE: Add new record song
    *IMPORT: RecordClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addRecord(RecordClass newRecord)
    {
        if(rCount > MAX_SONGS)
        {
            throw new IllegalArgumentException("\nMaximum Record song capacity of 10" +
            " songs has been reached\n");
        }
        else
        {
            rArray[rCount] = newRecord;
            rCount++;
            totalPlayTime = calcTotalPlayTime(newRecord.getDuration());
        }
    }

    /*******************************************************
    *PURPOSE: Add new cassette song
    *IMPORT: CassetteClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addCassette(CassetteClass newCassette)
    {
        if(cCount > MAX_SONGS)
        {
            throw new IllegalArgumentException("\nMaximum Cassette song capacity of 10" +
            " songs has been reached\n");
        }
        else
        {
            cArray[cCount] = newCassette;
            cCount++;
            totalPlayTime = calcTotalPlayTime(newCassette.getDuration());
        }
    }

    /*******************************************************
    *PURPOSE: Add new digital song
    *IMPORT: DigitalClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addDigital(DigitalClass newDigital)
    {
        if(dCount > MAX_SONGS)
        {
            throw new IllegalArgumentException("\nMaximum Digital song capacity of 10" +
            " songs has been reached\n");
        }
        else
        {
            dArray[dCount] = newDigital;
            dCount++;
            totalPlayTime = calcTotalPlayTime(newDigital.getDuration());
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
    
            
