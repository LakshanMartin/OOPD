public class MusicController
{
    //CLASS FIELDS
    private MusicCollection music;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Create default classes
    **********************************************************/
    public MusicController()
    {
        this.music = null;
    }     

    /*********************************************************
    *Alternate Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Initialise class fields
    **********************************************************/
    public MusicController(MusicCollection music)
    {
        this.music = music;
    }

    /*************************************************************
    *Copy Constructor:
    *IMPORT: toCopy
    *EXPORT:
    *ASSERTION: Creates an object with an identical object state
    *           as import
    ***************************************************************/
    public MusicController(MusicController toCopy)
    {
        music = toCopy.getMusic();
    }

    //ACCESSOR
    public MusicCollection getMusic()
    {
        return music;
    }  

    /*******************************************************
    *PURPOSE: Add new song
    *IMPORT: Music 
    *EXPORT none
    *ASSERTION: none
    ********************************************************/
    public void addSong(Music toAdd)
    {
        try
        {
            music.addSong(toAdd);
            System.out.println(
                "\nRecord: [" + toAdd.getName() + "] successfully added!");
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
    *PURPOSE: Search Music Library for play instructions 
    *IMPORT: song name 
    *EXPORT play instructions (String)
    *ASSERTION: none
    ********************************************************/
    public String getPlayInstructions(String name)
    {
        String playInst;
        Music[] musicArray;

        playInst = null;
        musicArray = music.getMusicArray();

        //Search through Music[] if not null
        if(musicArray != null)
        {
            for(int i = 0; i < musicArray.length; i++)
            {
                //Check element isn't null
                if(musicArray[i] != null)
                {
                    if(musicArray[i].getName().equals(name))
                    {
                        playInst = musicArray[i].play();
                        i = musicArray.length; //Exit for loop
                    }
                }
            }
        }

        //Set ERROR message if not found in library
        if(playInst == null)
        {
            playInst = "\nERROR: [" + name + "] not found in Music Library";
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
        Music[] musicArray;
        Record record;
        
        numSongs = 0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Record objects
            if(musicArray[i] instanceof Record)
            {
                record = (Record)musicArray[i];

                //Check if element's play speed matches imported valued
                if(record.getPlaySpeed().equals(playSpeed))
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
        Music[] musicArray;
        Cassette cassette;

        numSongs = 0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Cassette objects
            if(musicArray[i] instanceof Cassette)
            {
                cassette = (Cassette)musicArray[i];

                //Check if element's tape side matches imported valued
                if(cassette.getSide() == tapeSide.charAt(0))
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
        Music[] musicArray;
        Digital digital;
        
        numSongs = 0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Digital objects
            if(musicArray[i] instanceof Digital)
            {
                digital = (Digital)musicArray[i];

                //Check if element's file type matches imported valued
                if(digital.getType().equals(fileType))
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
        Music[] musicArray;
        Record record;
        
        runTime = 0.0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Record object
            if(musicArray[i] instanceof Record)
            {
                record = (Record)musicArray[i];

                //Check if element's play speed  matches imported valued
                if(record.getPlaySpeed().equals(playSpeed))
                {
                    runTime += musicArray[i].getDuration();
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
        Music[] musicArray;
        Cassette cassette;
        
        runTime = 0.0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Cassette object
            if(musicArray[i] instanceof Cassette)
            {
                cassette = (Cassette)musicArray[i];

                //Check if element's play speed  matches imported valued
                if(cassette.getSide() == tapeSide.charAt(0))
                {
                    runTime += musicArray[i].getDuration();
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
        Music[] musicArray;
        Digital digital;
        
        runTime = 0.0;
        musicArray = music.getMusicArray();

        for(int i = 0; i < musicArray.length; i++)
        {
            //Identify Digital objects
            if(musicArray[i] instanceof Digital)
            {
                digital = (Digital)musicArray[i];

                //Check if element's play speed  matches imported valued
                if(digital.getType().equals(fileType))
                {
                    runTime += musicArray[i].getDuration();
                }
            }
        }

        return runTime;
    }
}
