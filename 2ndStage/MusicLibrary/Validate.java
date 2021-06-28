/***********************************************************
Author: Lakshan Martin
Purpose: This class holds all data validation submodules
Date: 30/09/2018
Last Modified:
************************************************************/
/****This file comprises externally-obtained code********
#include ".trim()"
Obtained from Allice Watson,
https://jaxenter.com/convert-java-string-int-134101.html
(Accessed on 28 September 2018)
*********************************************************/

public class Validate
{
    //CONSTANT VARIABLES
    private static final double MIN_DUR = 0.00;
    private static final double MAX_DUR = 9.59;
    private static final int MIN_TRACK = 0;
    private static final int MAX_TRACK = 20;
    private static final double MIN_START = 0.00;
    private static final double MAX_START = 160.0;

    /*********************************
    *PURPOSE: Validate media type
    *IMPORT: text
    *EXPORT: type
    *ASSERTION: Return media type
    **********************************/
    public static String checkType(String text)
    {
        String type;

        switch (text)
        {
            case "R": case "r":
                type = "R";
            break;

            case "C": case "c":
                type = "C";
            break;

            case "D": case "d":
                type = "D";
            break;

            default:
                type = "invalid";
        }

        return type;
    }

    /*****************************************
    *PURPOSE: Validate Song and Artist name
    *IMPORT: text
    *EXPORT: validatedText
    *ASSERTION: Return validatedText
    ******************************************/
    public static boolean checkString(String input)
    {
        boolean isValid;
        
        isValid = true;

        //Check if input is empty or null
        if(input.isEmpty() || input == null)
        {
            isValid = false;
        }

        return isValid;
    }

    /**************************************************************
    *PURPOSE: Validate duration time format is within min and max
    *IMPORT: text
    *EXPORT: duration
    ***************************************************************/
    public static boolean checkDuration(String input)
    {
        String error;
        String[] split;
        int minute, second;
        boolean isValid;

        error = "\nERROR: Duration must be greater than " + MIN_DUR + " and " +
                "less than " + MAX_DUR + ". [Time Format: \"m.ss\"]\n";
        isValid = true;

        if(input.equals(""))
        {
            isValid = false;    
        }
        else
        {
            split = input.split("\\.");
            
            if(split.length == 2) 
            {
                try
                {
                    minute = Integer.parseInt(split[0]);

                    if(split[1].length() == 1) //Pad zero to 2 decimal place
                    {
                        split[1] += "0"; 
                    }
                    else if(split[1].length() > 2) //Truncate seconds if more than 2 digits
                    {
                        //Concatenate first 2 individual digits
                        split[1] = "" + split[1].charAt(0) + split[1].charAt(1);
                    }

                    second = Integer.parseInt(split[1]);

                    //Time range: 0.01 - 0.59
                    if(minute == 0)
                    {
                        if(second <= 0 || second > 59)
                        {
                            System.out.println(error);
                            isValid = false;
                        }
                    }
                    //Time range: 1.00 - 9.59
                    else if(minute > 0 && minute <= 9)
                    {
                        if(second < 0 || second > 59)
                        {
                            System.out.println(error);
                            isValid = false;
                        }
                    }
                    else
                    {
                        System.out.println(error);
                        isValid = false;
                    }
                }   
                catch(NumberFormatException e)
                {
                    System.out.println(error);
                    isValid = false;
                }  
            }
            else
            {
                System.out.println(error);
                isValid = false;
            }  
        }    

        return isValid;
    }

    /*********************************************************
    *PURPOSE: Validate track number is between min and max
    *IMPORT: text, min, max
    *EXPORT: track
    *ASSERTION: Return track
    **********************************************************/
    public static boolean checkTrack(String input)
    {
        int track;
        String error;
        boolean isValid;

        track = 0;
        error = "\nERROR: Track number must be greater than " + MIN_TRACK + 
                " and less than " + MAX_TRACK + ".\n";
        isValid = true;

        if(input.equals(""))
        {
            isValid = false;
        }
        else
        {
            try
            {
                //Ignore spaces and convert to int
                track = Integer.parseInt(input.trim());

                if ((track <= MIN_TRACK) || (track > MAX_TRACK))
                {
                    System.out.println(error);
                    isValid = false;
                }
            }
            catch (NumberFormatException nfe)
            {
                System.out.println(error);
                isValid = false;
            }
        }

        return isValid;
    }

    /****************************************************************
    *PURPOSE: Validate record speed is one of three correct options
    *IMPORT: input
    *EXPORT: boolean
    *ASSERTION: Return true if input is valid
    *****************************************************************/
    public static boolean checkSpeed(String input)
    {
        boolean isValid;

        isValid = true;

        //Check if input is empty or null
        if(input.isEmpty() || input == null)
        {
            isValid = false;
        }
        else
        {
            switch(input.trim().toUpperCase())
            {
                case "33 1/3 RPM": case "45 RPM": case "78 RPM":
                    //Just checking
                break;

                default:
                    isValid = false;
            }
        }

        return isValid;
    }

    /**************************************************************
    *PURPOSE: Validate Start time format is within min and max
    *IMPORT: text
    *EXPORT: start
    *ASSERTION: Return start
    ***************************************************************/
    public static boolean checkStart(String input)
    {
        int minute, second;
        String error;
        String[] split;
        boolean isValid;

        isValid = true;
        error = "\nERROR: Real number entered must be greater than " + MIN_START +
                " and less than " + MAX_START + ".\n";

        if(input.equals(""))
        {
            System.out.println(error);
            isValid = false;
        }
        else
        {        
            split = input.split("\\.");

            if(split.length == 2)
            {
                try
                {
                    minute = Integer.parseInt(split[0]);

                    if(split[1].length() == 1) //Pad zero to 2 decimal place
                    {
                        split[1] += "0"; 
                    }
                    else if(split[1].length() > 2) //Truncate seconds if more than 2 digits
                    {
                        //Concatenate first 2 individual digits
                        split[1] = "" + split[1].charAt(0) + split[1].charAt(1);
                    }

                    second = Integer.parseInt(split[1]);

                    //Time range: 0.00 - 159.59 
                    if(minute >= 0 && minute < 160)
                    {
                        if(second < 0 || second > 59)
                        {
                            System.out.println(error);
                            isValid = false;
                        }
                    }
                    //Time range: 160.0
                    else if(minute == 160)
                    {
                        if(second != 0)
                        {
                            System.out.println(error);
                            isValid = false;
                        }
                    }
                    else
                    {
                        System.out.println(error);
                        isValid = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println(error);
                    isValid = false;
                }
            }
            else
            {
                System.out.println(error);
                isValid = false;
            }
        }

        return isValid;
    }

    /*************************************************************
    *PURPOSE: Validate the side of the cassette is either A or B
    *IMPORT: rawText
    *EXPORT: side
    *ASSERTION: Return side
    **************************************************************/
    public static boolean checkSide(String input)
    {
        char side;
        String error;
        boolean isValid;

        error = "\nERROR: Cassette side must be either 'A' or 'B'\n";
        isValid = true;
        
        if(input.isEmpty() || input == null)
        {
            System.out.println(error);
            isValid = false;
        }
        else if(input.length() != 1)
        {
            System.out.println(error);
            isValid = false;
        }
        else
        {
            //Ignore spaces, convert text to uppercase and stores first char
            side = (input.trim().toUpperCase().charAt(0));

            switch(side)
            {
                case 'A': case 'B':
                    //Just checking
                break;

                default:
                    System.out.println(error);
                    isValid = false;
            }
        }
        
        return isValid;
    }

    /**********************************************************
    *PURPOSE: Validate the digital media file type
    *IMPORT: string
    *EXPORT: boolean
    *ASSERTION: Return true if input is valid
    **********************************************************/
    public static boolean checkFileType(String input)
    {
        String error;
        boolean isValid;

        error = "\nERROR: File type must be either of the following:" +
                "\n- wav" +
                "\n- mp3" +
                "\n- acc";
        isValid = true;

        if(input.isEmpty() || input == null)
        {
            System.out.println(error);
            isValid = false;
        }
        else
        {
            //ignores spaces and converts string to uppercase            
            switch(input.trim().toUpperCase())
            {
                case "WAV": case "MP3": case "ACC":
                    //Just checking
                break;

                default:
                    System.out.println(error);
                    isValid = false;
            }
        }

        return isValid;
    }
}
            
