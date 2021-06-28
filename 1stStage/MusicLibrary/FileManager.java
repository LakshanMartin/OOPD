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
    //CONSTANT VARIABLES
    private static final Scanner sc = new Scanner(System.in);

    /********************************************
    *PURPOSE: Read in file
    *IMPORT: fileName
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    **********************************************/
    public static void readFile(String filename)
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
                System.out.println(line);
                 
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
    private static void processRecords(String[] details)
    {
        String song, artist, playSpeed;
        double duration;
        int trackNumber;

        song = details[1];
        artist = details[2];
        playSpeed = details[5];
        duration = 0.0;
        trackNumber = 0;
        
        if(!Validate.checkString(song))
        {
            System.out.println("Invalid SONG NAME: " + song + "\n");
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
            
            //Test values
            MusicStub.addRecord(song, artist, duration, trackNumber, playSpeed);
            System.out.println("\n");
        }      
    } 

    /**********************************************************
    *PURPOSE: Process all the Cassettes data
    *IMPORT: details - String array containing song details
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    ***********************************************************/
    private static void processCassettes(String[] details)
    {
        String song, artist;
        double duration, start;
        int trackNumber;
        char side;                 
        
        song = details[1];
        artist = details[2];
        duration = 0.0;
        start = 0.0;
        trackNumber = 0;
        side = '-';

        //Validate details
        if(!Validate.checkString(song))
        {
            System.out.println("Invalid SONG NAME: " + song + "\n");
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

            //Test values
            MusicStub.addCassette(song, artist, duration, trackNumber, start, 
                                    side);
            System.out.println("\n");
        }
    }

    /*************************************************
    *PURPOSE: Process all the Digitals data
    *IMPORT: string array - song details
    *EXPORT: none
    *ASSERTION: Outputs relevant error messages
    **************************************************/    
    public static void processDigitals(String[] details)
    {
        String song, artist, fileType;
        double duration;
            
        song = details[1];
        artist = details[2];
        fileType = details[4];
        duration = 0.0;

        //Validate details
        if(!Validate.checkString(song))
        {
            System.out.println("Invalid SONG NAME: " + song + "\n");
        }
        else if(!Validate.checkString(artist))
        {
            System.out.println("Invalid ARTIST NAME: " + artist + "\n");
        }
        else if(!Validate.checkDuration(details[3]))
        {
            System.out.println("Invalid DURATION: " + details[3] + "\n");
        }
        else if(!Validate.checkFileType(fileType))
        {
            System.out.println("Invalid FILE TYPE: " + fileType + "\n");
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

            MusicStub.addDigital(song, artist, duration, fileType);
            System.out.println("\n");
        }
    }

    /************************************************************
    *PURPOSE: Create a .csv file with contents of Music Library
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *************************************************************/
    public static void exportLibrary()
    {
        String fileName;
        FileOutputStream fileStrm = null;
        PrintWriter pw;

        System.out.println("\nPlease enter a file name for the exported " +
        "Music Libary:");
        fileName = sc.next();
        fileName = fileName + ".csv";

        try
        {
            fileStrm = new FileOutputStream(fileName);//open file for writing
            pw = new PrintWriter(fileStrm);//initialise writer

            pw.println("Data storage not yet implemented");
            pw.println("This is a test file");
            pw.println("To ensure the output is correctly printed");
            pw.close();//close the stream

            System.out.println("\nYour Music Library has been exported to " +
            "the current directory under the\nfollowing file name: " + 
            fileName);//Message to clarify the output file name
        }
        catch (IOException e)
        {
            System.out.println("Error in writing to file");
        }
    }        
}


