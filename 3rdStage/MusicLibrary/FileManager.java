/*****************************************************
Author: Lakshan Martin
Purpose: This class holds all the file I/O methods
Date: 28/09/2018
Last Modified:
******************************************************/

import java.util.*;
import java.io.*;

public class FileManager
{    
    //CLASS FIELD
    private MusicController mCont;

    /*********************************************************
    *Default Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Create default classes
    **********************************************************/
    public FileManager()
    {
        this.mCont = null;
    }

    /*********************************************************
    *Alternate Constructor:
    *IMPORT: none
    *EXPORT: 
    *ASSERTION: Initialise class fields
    **********************************************************/
    public FileManager(MusicController mCont)
    {
        this.mCont = mCont;
    }

    /*************************************************************
    *Copy Constructor:
    *IMPORT: toCopy
    *EXPORT:
    *ASSERTION: Creates an object with an identical object state
    *           as import
    ***************************************************************/
    public FileManager(FileManager toCopy)
    {
        this.mCont = toCopy.getMCont();
    }

    //ACCESSOR
    public MusicController getMCont()
    {
        return mCont;
    }

    /********************************************
    *PURPOSE: Read in file
    *IMPORT: fileName
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    **********************************************/
    public void readFile(String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line, type;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            while ((line = bufRdr.readLine()) != null)
            {
                String[] lineArray = line.split(",");
                type = Validate.checkType(lineArray[0]);
                //System.out.println(line);
                 
                /*Each switch case confirms that each row has the correct 
                amount of data as per respective media types*/
                switch (type)
                {
                    case "R":
                        if ((lineArray.length) != 6)
                        {
                            System.out.println("INFORMATION MISSING about " + 
                            "the song\n");
                        }
                        else
                        {
                            processRecords(lineArray);
                        }
                    break;

                    case "C":
                        if ((lineArray.length) != 7)
                        {
                            System.out.println("INFORMATION MISSING about " + 
                            "the song\n");
                        }
                        else
                        {
                            processCassettes(lineArray);
                        }
                    break;

                    case "D":
                        if ((lineArray.length) != 5)
                        {
                            System.out.println("INFORMATION MISSING about " + 
                            "the song\n");
                        }
                        else
                        {
                            processDigitals(lineArray);
                        }            
                    break;

                    default:
                        System.out.println("Invalid MEDIA TYPE entered: " + 
                        lineArray[0] + "\n");
                }
                
            }        
 
            fileStrm.close();//close file
        }
        catch(FileNotFoundException e)
        {
            System.out.println("\nERROR: File not found - " + filename + "\n");
        }
        catch(IOException e)
        {
            System.out.println("\nERROR: File not accessible - " + filename + "\n");

            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
        }
    }

    /*********************************************************
    *PURPOSE: Process all the Records data
    *IMPORT: details - String array containing record details
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    **********************************************************/
    private void processRecords(String[] details)
    {
        String name, artist, playSpeed;
        double duration;
        int trackNumber;
        Record toAdd;

        name = details[1];
        artist = details[2];
        playSpeed = details[5];
        duration = 0.0;
        trackNumber = 0;
        
        if(!Validate.checkString(name))
        {
            System.out.println("Invalid SONG NAME: " + name + "\n");
        }
        else if(!Validate.checkString(artist))
        {
            System.out.println("Invalid ARTIST NAME: " + artist + "\n");
        }
        else if(!Validate.checkDuration(details[3]))
        {
            System.out.println("Invalid DURATION: " + details[3] + "\n");
        }
        else if(!Validate.checkTrack(details[4]))
        {
            System.out.println("Invalid TRACK NUMBER: " + details[4] + "\n");
        }
        else if(!Validate.checkSpeed(playSpeed))
        {
            System.out.println("Invalid PLAY SPEED: " + playSpeed + "\n");
        }
        else
        {
            try
            {
                //Convert to double
                duration = Double.parseDouble(details[3]);

                //Convert to integer
                trackNumber = Integer.parseInt(details[4]);
            }
            catch(NumberFormatException e)
            {
                //Already validated
            }
            
            //Create RecordClass object to add
            toAdd = new Record(name, artist, duration, trackNumber, playSpeed);       
            mCont.addSong(toAdd);
        }      
    } 

    /**********************************************************
    *PURPOSE: Process all the Cassettes data
    *IMPORT: details - String array containing song details
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    ***********************************************************/
    private void processCassettes(String[] details)
    {
        String name, artist;
        double duration, start;
        int trackNumber;
        char side;                 
        Cassette toAdd;

        name = details[1];
        artist = details[2];
        duration = 0.0;
        start = 0.0;
        trackNumber = 0;
        side = '-';

        //Validate details
        if(!Validate.checkString(name))
        {
            System.out.println("Invalid SONG NAME: " + name + "\n");
        }
        else if(!Validate.checkString(artist))
        {
            System.out.println("Invalid ARTIST NAME: " + artist + "\n");
        }
        else if(!Validate.checkDuration(details[3]))
        {
            System.out.println("Invalid DURATION: " + details[3] + "\n");
        }
        else if(!Validate.checkTrack(details[4]))
        {
            System.out.println("Invalid TRACK NUMBER: " + details[4] + "\n");
        }
        else if(!Validate.checkStart(details[5]))
        {
            System.out.println("Invalid START TIME: " + details[5] + "\n");
        }
        else if(!Validate.checkSide(details[6]))
        {
            System.out.println("Invalid CASSETTE SIDE: " + details[6] + "\n");
        }
        else
        {
            try
            {
                //Convert to double
                duration = Double.parseDouble(details[3]);

                //Convert to integer
                trackNumber = Integer.parseInt(details[4]);

                //Convert to double
                start = Double.parseDouble(details[5]);

                //Convert to char
                side = details[6].charAt(0);
            }
            catch(NumberFormatException e)
            {
                //Already validated
            }

            //Create CassetteClass object to add
            toAdd = new Cassette(name, artist, duration, trackNumber,
            start, side);
            mCont.addSong(toAdd);
        }
    }

    /*************************************************
    *PURPOSE: Process all the Digitals data
    *IMPORT: string array - song details
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    **************************************************/    
    private void processDigitals(String[] details)
    {
        String name, artist, type;
        double duration;
        Digital toAdd;    

        name = details[1];
        artist = details[2];
        type = details[4];
        duration = 0.0;

        //Validate details
        if(!Validate.checkString(name))
        {
            System.out.println("Invalid SONG NAME: " + name + "\n");
        }
        else if(!Validate.checkString(artist))
        {
            System.out.println("Invalid ARTIST NAME: " + artist + "\n");
        }
        else if(!Validate.checkDuration(details[3]))
        {
            System.out.println("Invalid DURATION: " + details[3] + "\n");
        }
        else if(!Validate.checkFileType(type))
        {
            System.out.println("Invalid FILE TYPE: " + type + "\n");
        }
        else
        {
            try
            {
                //Convert to double
                duration = Double.parseDouble(details[3]);
            }
            catch(NumberFormatException e)
            {
                //Already validated
            }

            //Create DigitalClass object to add
            toAdd = new Digital(name, artist, duration, type);
            mCont.addSong(toAdd);
        }
    }

    /************************************************************
    *PURPOSE: Create a .csv file with contents of Music Library
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *************************************************************/
    public void exportLibrary()
    {
        String filename, strDuration, strStart, line;
        FileOutputStream fileStrm;
        PrintWriter pw;
        MusicCollection music;
        Music[] musicArray;
        Record record;
        Cassette cassette;
        Digital digital;

        music = mCont.getMusic();
        musicArray = music.getMusicArray();

        //Get filename from user input
        filename = inputFilename();

        try
        {
            fileStrm = new FileOutputStream(filename); //Open file for writing
            pw = new PrintWriter(fileStrm);//initialise writer

            if(musicArray != null)
            {
                for(int i = 0; i < musicArray.length; i++)
                {
                    if(musicArray[i] != null)
                    {
                        //Identify Record object
                        if(musicArray[i] instanceof Record)
                        {
                            record = (Record)musicArray[i];

                            //Format duration to 2 decimal places
                            strDuration = String.format("%.2f", musicArray[i].getDuration());

                            //Create String to write
                            line = "R," + musicArray[i].getName() + "," + musicArray[i].getArtist() +
                            "," + strDuration + "," + record.getTrackNumber() +
                            "," + record.getPlaySpeed();

                            //Write to file
                            pw.println(line);
                        }
                        else if(musicArray[i] instanceof Cassette)
                        {
                            cassette = (Cassette)musicArray[i];

                            //Format duration & start time to 2 decimal places
                            strDuration = String.format("%.2f", musicArray[i].getDuration());
                            strStart = String.format("%.2f", cassette.getStart());

                            //Create String to write
                            line = "C," + musicArray[i].getName() + "," + musicArray[i].getArtist() +
                            "," + strDuration + "," + cassette.getTrackNumber() +
                            "," + strStart + "," + cassette.getSide();

                            //Write to file
                            pw.println(line);
                        }
                        else
                        {
                            digital = (Digital)musicArray[i];

                            //Format duration to 2 decimal places
                            strDuration = String.format("%.2f", musicArray[i].getDuration());

                            //Create String to write
                            line = "D," + musicArray[i].getName() + "," + musicArray[i].getArtist() +
                            "," + strDuration + "," + digital.getType();

                            //Write to file
                            pw.println(line);
                        }
                    }
                }
            }

            pw.close();

            System.out.println("\nYour Music Library has been exported to " +
            "the current directory under the\nfollowing file name: " + 
            filename);//Message to clarify the output file name
        }
        catch (IOException e)
        {
            System.out.println("Error in writing to file");
        }
    }        

    /************************************************************
    *PURPOSE: Get filename from user input
    *IMPORT: none
    *EXPORT: filename (String)
    *ASSERTION: none
    *************************************************************/
    private String inputFilename()
    {
        String filename;
        Scanner sc = new Scanner(System.in);

        System.out.println(
            "\nPlease enter a file name for the exported Music Libary:");
        filename = sc.next();

        //Add file type to string
        filename = filename + ".csv"; 

        return filename;
    }
}


