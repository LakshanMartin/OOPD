public class MusicController
{
    //CLASS FIELDS
    private MusicCollection music;

    //CONSTRUCTOR
    public MusicController(MusicCollection music)
    {
        this.music = music;
    }

    //ACCESSOR
    public MusicCollection getMusicCollection()
    {
        return music;
    }  

    /*******************************************************
    *PURPOSE: Add new record song
    *IMPORT: RecordClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addRecord(RecordClass toAdd)
    {
        try
        {
            music.addRecord(toAdd);
            System.out.println(
                "\nRecord: [" + toAdd.getName() + "] successfully added!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    /*******************************************************
    *PURPOSE: Add new cassette song
    *IMPORT: CassetteClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addCassette(CassetteClass toAdd)
    {
        try
        {
            music.addCassette(toAdd);
            System.out.println(
                "\nCassette: [" + toAdd.getName() + "] successfully added!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    /*******************************************************
    *PURPOSE: Add new digital song
    *IMPORT: DigitalClass 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addDigital(DigitalClass toAdd)
    {
        try
        {
            music.addDigital(toAdd);
            System.out.println(
                "\nDigital: [" + toAdd.getName() + "] successfully added!");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    /*******************************************************
    *PURPOSE: Retrieve total play time of Library
    *IMPORT: none 
    *EXPORT double
    *ASSERTION: none
    ********************************************************/
    public double getTotalPlayTime()
    {
        return music.getTotalPlayTime();
    }

    /*******************************************************
    *PURPOSE: Find play instructions for imported song
    *IMPORT: song name 
    *EXPORT play instructions (String)
    *ASSERTION: none
    ********************************************************/
    public String getPlayInstructions(String name)
    {
        String playInst;

        playInst = recordPlayInstructions(name);

        //Check Cassettes if not already found
        if(playInst == null)
        {
            playInst = cassettePlayInstructions(name);
        }

        //Check Digitals if not already found
        if(playInst == null)
        {
            playInst = digitalPlayInstructions(name);
        }

        //Set ERROR message if not found in library
        if(playInst == null)
        {
            playInst = "\nERROR: [" + name + "] not found in Music Library";
        }

        return playInst;
    } 

    /*******************************************************
    *PURPOSE: Search Records Library for play instructions 
    *IMPORT: song name 
    *EXPORT play instructions (String)
    *ASSERTION: none
    ********************************************************/
    private String recordPlayInstructions(String name)
    {
        String playInst;
        RecordClass[] rArray;

        playInst = null;
        rArray = music.getRArray();

        //Search through RecordClass[] if not null
        if(rArray != null)
        {
            for(int i = 0; i < rArray.length; i++)
            {
                //Check element isn't null
                if(rArray[i] != null)
                {
                    if(rArray[i].getName().equals(name))
                    {
                        playInst = rArray[i].play();
                        i = rArray.length; //Exit for loop
                    }
                }
            }
        }

        return playInst;
    }

    /*******************************************************
    *PURPOSE: Search Cassettes Library for play instructions 
    *IMPORT: song name 
    *EXPORT play instructions (String)
    *ASSERTION: none
    ********************************************************/
    private String cassettePlayInstructions(String name)
    {
        String playInst;
        CassetteClass[] cArray;

        playInst = null;
        cArray = music.getCArray();

        //Search through CassetteClass[] if not null
        if(cArray != null)
        {
            for(int i = 0; i < cArray.length; i++)
            {
                if(cArray[i] != null)
                {
                    if(cArray[i].getName().equals(name))
                    {
                        playInst = cArray[i].play();
                        i = cArray.length; //Exit for loop
                    }
                }
            }
        }

        return playInst;
    }

    /*******************************************************
    *PURPOSE: Search Digitals Library for play instructions 
    *IMPORT: song name 
    *EXPORT play instructions (String)
    *ASSERTION: none
    ********************************************************/
    private String digitalPlayInstructions(String name)
    {
        String playInst;
        DigitalClass[] dArray;

        playInst = null;
        dArray = music.getDArray();

        //Search through DigitalClass[] if not null
        if(dArray != null)
        {
            for(int i = 0; i < dArray.length; i++)
            {
                if(dArray[i] != null)
                {
                    if(dArray[i].getName().equals(name))
                    {
                        playInst = dArray[i].play();
                        i = dArray.length; //Exit for loop
                    }
                }
            }
        }

        return playInst;
    }

    /*******************************************************
    *PURPOSE: Find number of songs of imported criteria
    *IMPORT: criteria (String)
    *EXPORT number of song (Integer)
    *ASSERTION: none
    ********************************************************/
    public int getNumSongs(String criteria)
    {
        int numSongs;

        switch(criteria.toUpperCase())
        {
            case "33 1/3 RPM": case "45 RPM": case "78 RPM":
                numSongs = recordNumSongs(criteria);
            break;

            case "A": case "B":
                numSongs = cassetteNumSongs(criteria);
            break;

            case "WAV": case "MP3": case "ACC":
                numSongs = digitalNumSongs(criteria);
            break;

            default:
                numSongs = 0;
                System.out.println("ERROR: Invalid option");
        }
        
        return numSongs;
    }

    /*******************************************************
    *PURPOSE: Search Records for number of songs of imported play speed
    *IMPORT: play speed 
    *EXPORT number of song (Integer)
    *ASSERTION: none
    ********************************************************/
    private int recordNumSongs(String playSpeed)
    {
        int numSongs;
        RecordClass[] rArray;
        
        numSongs = 0;
        rArray = music.getRArray();

        for(int i = 0; i < rArray.length; i++)
        {
            //Check if element is null
            if(rArray[i] != null)
            {
                //Check if element's play speed  matches imported valued
                if(rArray[i].getPlaySpeed().equals(playSpeed))
                {
                    numSongs++;
                }
            }
        }

        return numSongs;
    }

    /*******************************************************
    *PURPOSE: Search Cassettes for number of songs of imported Tape Side
    *IMPORT: tapeSide (String)
    *EXPORT number of song (Integer)
    *ASSERTION: none
    ********************************************************/
    private int cassetteNumSongs(String tapeSide)
    {
        int numSongs;
        CassetteClass[] cArray;
        
        numSongs = 0;
        cArray = music.getCArray();

        for(int i = 0; i < cArray.length; i++)
        {
            //Check if element is null
            if(cArray[i] != null)
            {
                //Check if element's tape side matches imported valued
                if(cArray[i].getSide() == tapeSide.charAt(0))
                {
                    numSongs++;
                }
            }
        }

        return numSongs;
    }

    /*******************************************************
    *PURPOSE: Search Digitals for number of songs of imported file type
    *IMPORT: fileType (String) 
    *EXPORT number of song (Integer)
    *ASSERTION: none
    ********************************************************/
    private int digitalNumSongs(String fileType)
    {
        int numSongs;
        DigitalClass[] dArray;
        
        numSongs = 0;
        dArray = music.getDArray();

        for(int i = 0; i < dArray.length; i++)
        {
            //Check if element is null
            if(dArray[i] != null)
            {
                //Check if element's file type matches imported valued
                if(dArray[i].getType().equals(fileType))
                {
                    numSongs++;
                }
            }
        }

        return numSongs;
    }

    /*******************************************************
    *PURPOSE: Search for run time of imported criteria
    *IMPORT: criteria (String)
    *EXPORT run time (double)
    *ASSERTION: none
    ********************************************************/
    public double getRunTime(String criteria)
    {
        double runTime;

        switch(criteria.toUpperCase())
        {
            case "33 1/3 RPM": case "45 RPM": case "78 RPM":
                runTime = recordRunTime(criteria);
            break;

            case "A": case "B":
                runTime = cassetteRunTime(criteria);
            break;

            case "WAV": case "MP3": case "ACC":
                runTime = digitalRunTime(criteria);
            break;

            default:
                runTime = 0.0;
                System.out.println("ERROR: Invalid option");
        }
        
        return runTime;
    }

    /*******************************************************
    *PURPOSE: Search Records for run time of imported play speed
    *IMPORT: play speed 
    *EXPORT runTime (Double)
    *ASSERTION: none
    ********************************************************/
    private double recordRunTime(String playSpeed)
    {
        double runTime;
        RecordClass[] rArray;
        
        runTime = 0.0;
        rArray = music.getRArray();

        for(int i = 0; i < rArray.length; i++)
        {
            //Check if element is null
            if(rArray[i] != null)
            {
                //Check if element's play speed  matches imported valued
                if(rArray[i].getPlaySpeed().equals(playSpeed))
                {
                    runTime += rArray[i].getDuration();
                }
            }
        }

        return runTime;
    }

    /*******************************************************
    *PURPOSE: Search Cassettes for run time of imported tape side
    *IMPORT: tapeSide (String)
    *EXPORT runTime (Double)
    *ASSERTION: none
    ********************************************************/
    private double cassetteRunTime(String tapeSide)
    {
        double runTime;
        CassetteClass[] cArray;
        
        runTime = 0.0;
        cArray = music.getCArray();

        for(int i = 0; i < cArray.length; i++)
        {
            //Check if element is null
            if(cArray[i] != null)
            {
                //Check if element's play speed  matches imported valued
                if(cArray[i].getSide() == tapeSide.charAt(0))
                {
                    runTime += cArray[i].getDuration();
                }
            }
        }

        return runTime;
    }

    /*******************************************************
    *PURPOSE: Search Digitals for run time of imported file type
    *IMPORT: fileType (String)
    *EXPORT runTime (Double)
    *ASSERTION: none
    ********************************************************/
    private double digitalRunTime(String fileType)
    {
        double runTime;
        DigitalClass[] dArray;
        
        runTime = 0.0;
        dArray = music.getDArray();

        for(int i = 0; i < dArray.length; i++)
        {
            //Check if element is null
            if(dArray[i] != null)
            {
                //Check if element's play speed  matches imported valued
                if(dArray[i].getType().equals(fileType))
                {
                    runTime += dArray[i].getDuration();
                }
            }
        }

        return runTime;
    }
}
