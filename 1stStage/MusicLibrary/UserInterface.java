import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface 
{
    //CONSTANT VARIABLES
    private static Scanner sc = new Scanner(System.in);

    /*******************************************************
    *PURPOSE: Present options to add music to the library
    *IMPORT: none
    *EXPORT: boolean
    ********************************************************/
    public static boolean addMusicMenu()
    {
        int selection;
        String filename;
        boolean exit, songsAdded;

        exit = false;
        songsAdded = false;

        do
        {
            //Submenu options
            System.out.println(
                "\nTo add music, please select from the following options:" +
                "\n1. Add individual song" +
                "\n2. Import music library through .csv file" +
                "\n3. Proceed to Main Menu" +
                "\n4. Exit to Terminal");

            try
            {
                selection = sc.nextInt();//store user integer input
            }
            catch (InputMismatchException e)//catch non-int data types
            {
                sc.nextLine();//clear buffer of incorrect input
                selection = 0;//initialise incorrect value to force loop
            }

            switch(selection)
            {
                case 1:
                    selectMedia();
                    songsAdded = true;
                break;

                case 2:
                    System.out.println("\nPlease enter the file name.");
                    filename = sc.next();
                    FileManager.readFile(filename);
                    songsAdded = true;
                break;

                case 3:
                    //Can only proceed to Main Menu if songs have been added
                    if(!songsAdded)
                    {
                        System.out.println("\nERROR: Must have songs added " +
                                            "to Library first...\n");
                        selection = 0; //Force loop again
                    }
                    else
                    {
                        selection = 4; //Exit loop and proceed to Main menu
                    }
                break;

                case 4:
                    System.out.println("\nExiting to Terminal. Thank You.\n");
                    exit = true;
                break;

                default:
                    System.out.println("\nError! Please enter a valid " + 
                    "integer between 1 - 4 (inclusive) from the menu.");
                    selection = 0;
            } 
        }
        while (selection != 4);

        return exit;
    }  

    /*****************************************************************
    *PURPOSE: Present user with media type options to select from
    *IMPORT: none
    *EXPORT: none
    ******************************************************************/
    private static void selectMedia()
    {
        int selection;
        
        do
        {
            System.out.println(
                "\nPlease select the appropriate media type of the song by " +
                "entering an integer from the following:" + 
                "\n1. Record" +
                "\n2. Cassette" +
                "\n3. Digital");

            //Media type options to select from
            try
            {
                selection = sc.nextInt();//store user integer input
            }
            catch (InputMismatchException e)//catch non-int data types
            {
                sc.nextLine();//clear buffer of incorrect input
                selection = 0;
            }

            switch (selection)
            {
                case 1:
                    addRecord();
                break;
                
                case 2:
                    addCassette();
                break;
            
                case 3:
                    addDigital();
                break;

                default:
                    System.out.println("\nError! Please enter a valid " +
                    "integer, between 1 - 3 (inclusive), from the menu.");
            }
        }
        while (selection < 1 || selection > 3);
    }

    /*********************************************************
    *PURPOSE: Record and validate user input for record info      
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    **********************************************************/
    private static void addRecord()
    {
        String name, artist, prompt, option1, option2, option3, playSpeed;
        double duration;
        int trackNumber;
            
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "Please enter the song Duration as a real " +
        "number between 0.0 and 9.59 (inclusive):";
        //duration range based on constants
        duration = durationInput(prompt);
        
        prompt = "Please enter the Track Number as an integer:";
        trackNumber = intInput(prompt);
        
        //initialise option values for record play speed
        option1 = "33 1/3 RPM";
        option2 = "45 RPM";
        option3 = "78 RPM";
        prompt = "\nPlease select the appropriate Play Speed by entering an " +
                    "integer from the following:" +
                    "\n1. " + option1 + 
                    "\n2. " + option2 +
                    "\n3. " + option3; 
        playSpeed = stringOptions(prompt, option1, option2, option3);

        //Test values
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);
    }

    /***********************************************************
    *PURPOSE: Record and validate user input for cassette info
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    ************************************************************/
    private static void addCassette()
    {
        String name, artist, prompt;
        double duration, start;
        int trackNumber;
        char side;
        
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "Please enter the song Duration as a real number between " +
        "0.0 and 9.59 (inclusive):";
        //duration range based on constants
        duration = durationInput(prompt);
        
        prompt = "Please enter the Track Number as an integer:";
        trackNumber = intInput(prompt);
        
        prompt = "Please enter the song Start time as a real number between " +
        "0.0 and 160.0 (inclusive):";
        start = startInput(prompt);

        prompt = "Please enter which side of the cassette the song is on " +
                 "as a single character, 'A' OR 'B':";
        side = sideInput(prompt);

        //Test values
        MusicStub.addCassette(name, artist, duration, trackNumber, start, 
        side);
    
    }

    /*********************************************************
    *PURPOSE: Record and validate user input for digitals info      
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    **********************************************************/
    private static void addDigital()
    {
        String name, artist, prompt, option1, option2, option3, type;
        double duration;
                    
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "Please enter the song Duration as a real " +
        "number between 0.0 and 9.59 (inclusive):";
        duration = durationInput(prompt);
        
        //initialise option values for record play speed
        option1 = "WAV";
        option2 = "MP3";
        option3 = "ACC";
        prompt = "\nPlease select the appropriate Digital File Type by " +
                    "entering an integer from the following:" +
                    "\n1. " + option1 + 
                    "\n2. " + option2 + 
                    "\n3. " + option3; 
        type = stringOptions(prompt, option1, option2, option3);
        //call checkStringOptions for user to select relevant option        

        //Test values
        MusicStub.addDigital(name, artist, duration, type);
    }

    /*********************************************
    *PURPOSE: Record user string input
    *IMPORT: prompt
    *EXPORT: userInput
    *ASSERTION: Return userInput
    **********************************************/
    private static String stringInput(String prompt)
    {
        String userInput;

        System.out.println(prompt);
        userInput = sc.next();

        return userInput;
    }

    /*****************************************************
    *PURPOSE: Store and validate duration input
    *IMPORT: prompt
    *EXPORT: duration
    *ASSERTION: Return validated duration 
    ******************************************************/
    private static double durationInput(String prompt)
    {
        String input;
        boolean isValid;
        double duration;
        String[] split;

        duration = 0.0;

        do
        {
            System.out.println(prompt);//output imported prompt
            input = sc.next();//store user input
            isValid = Validate.checkDuration(input);
        }
        while (!isValid);

        split = input.split("\\.");

        //Truncate seconds if more than 2 digits
        if(split[1].length() > 2)
        {
            //Concatenate 2 individual digits
            split[1] = "" + split[1].charAt(0) + split[1].charAt(1);
        }

        //Rejoin input
        input = split[0] + "." + split[1];

        //Convert to double
        try
        {
            duration = Double.parseDouble(input.trim());
        }
        catch(NumberFormatException e)
        {
            //Already validated
        }

        return duration;
    }

    /***********************************************************
    *PURPOSE: Store a validate user integer input
    *IMPORT: prompt
    *EXPORT: integer
    *ASSERTION: Return integer between range of min and max 
    ************************************************************/
    private static int intInput(String prompt)
    {
        String input;
        boolean isValid;
        int integer;

        integer = 0;

        do
        {
            System.out.println(prompt);//output imported prompt
            input = sc.next();//store user input
            isValid = Validate.checkTrack(input);
        }
        while (!isValid);

        try
        {
            integer = Integer.parseInt(input.trim());
        }
        catch(NumberFormatException e)
        {
            //Already validated
        }

        return integer;
    }

    /******************************************************
    *PURPOSE: Present options for the user to select from
    *IMPORT: prompt, option1, option2, option3
    *EXPORT: selectedString
    *ASSERTION: Return selected option
    *******************************************************/
    private static String stringOptions(String prompt, String option1,
    String option2, String option3)
    {
        String selectedString;
        int selection;

        selectedString = "";

        do
        {
            System.out.println(prompt);
            
            try
            {
                selection = sc.nextInt();
            }
            catch (InputMismatchException e)//catch non-int data types
            {
                sc.nextLine();//clear buffer of incorrect input
                selection = 0;//initialise as incorrect value to force loop
            }

            switch (selection)
            {
                case 1:
                    selectedString = option1;
                break;

                case 2:
                    selectedString = option2;
                break;

                case 3:
                    selectedString = option3;
                break;

                default:
                    System.out.println("\nError! Please enter a valid " +
                    "integer between 1-3 from the options provided.");
            }
        }
        while ((selection < 1) || (selection > 3));
        
        return selectedString;
    }

    /***********************************************************
    *PURPOSE: Store and validate start time input
    *IMPORT: prompt
    *EXPORT: real
    *ASSERTION: Return validated start time 
    ************************************************************/
    private static double startInput(String prompt)
    {
        double start;
        String input;
        boolean isValid;

        do
        {
            System.out.println(prompt);//output imported prompt
            input = sc.next();//store user input
            isValid = Validate.checkStart(input);
        }
        while (!isValid);

        start = Double.parseDouble(input.trim());

        return start;
    }

    /***********************************************************
    *PURPOSE: Store and validate cassette side input
    *IMPORT: prompt
    *EXPORT: char
    *ASSERTION: Return validated side 
    ************************************************************/
    private static char sideInput(String prompt)
    {
        char side;
        String input;
        boolean isValid;

        do
        {
            System.out.println(prompt); //ouptut imported prompt
            input = sc.next(); //store user input
            isValid = Validate.checkSide(input);
        }
        while(!isValid);

        side = input.charAt(0);

        return side;
    }

    /***********************************************************
    *PURPOSE: Present Main menu options to user
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    ************************************************************/
    public static void mainMenu()
    {
        int selection;

        System.out.println("\nMAIN MENU!");

        do
        {
            //Main menu options
            System.out.println("\nPlease select from the following options:" +
            "\n1. Show library's total play time" + 
            "\n2. Search Options" + 
            "\n3. Export Music Library (.csv file)" + 
            "\n4. Exit to Terminal");
            
            try
            {
                selection = sc.nextInt();//store user input
            }
            catch (InputMismatchException e)//catch non-integer data types
            {
                sc.next();//clear buffer of incorrect data type
                selection = 0;//initialise invalid value to force loop
            }
    
            switch (selection)
            {
                case 1:
                    System.out.println(
                        "\nSelected option will be implemented in later stages");
                break;

                case 2:
                    searchMenu();
                break;

                case 3:
                    FileManager.exportLibrary();
                break;

                case 4:
                    System.out.println("\nExiting to Terminal. Thank You.\n");
                break;

                default:
                    System.out.println(
                        "\nERROR: Please enter a valid integer from the menu.");
                    selection = 0;
            }
        }
        while (selection != 4);
    }

    /************************************************
    *PURPOSE: Present user with Search menu options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *************************************************/
    private static void searchMenu()
    {
        int selection;

        do
        {
            //Search menu options to select form
            System.out.println(
                "\nPlease select from the following Search options:" +
                "\n1. Find a specific song's Play Instructions" +
                "\n2. Find the total number of Songs, and total run time for " +
                        "a specific media type" +
                "\n3. Return to MAIN MENU");

            try
            {
                selection = sc.nextInt();
            }
            catch (InputMismatchException e)//catch non-int data types
            {
                sc.nextLine();//clear buffer of incorrect input
                selection = 0;
            }

            switch (selection)
            {
                case 1:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;
        
                case 2:
                    searchMedia();
                break;

                case 3:
                    //Returning to main menu
                break;

                default:
                    System.out.println(
                        "\nError! Please enter a valid integer, between 1 - 3 " +
                        "(inclusive), from the menu.");
            }
        }
        while (selection != 3);
        //ASSERTION: selection == 3
    }   
    
    /*********************************************
    *PURPOSE: Present user with Search sub menu options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private static void searchMedia()
    {
        int selection;

        do
        {
            //Menu options
            System.out.println("\nPlease select a Media Type:" +
                "\n1. Records" +
                "\n2. Cassettes" +
                "\n3. Digitals" +
                "\n4. Return to Previous Menu");

            try
            {
                selection = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //clear buffer of incorrect input
                selection = 0; //force loop 
            }

            switch(selection)
            {
                case 1:
                    recordDuration();
                break;

                case 2:
                    cassetteDuration();
                break;

                case 3:
                    digitalDuration();
                break;

                case 4:
                    //Returning to search menu
                break;

                default:
                    System.out.println(
                        "\nError! Please enter a valid integer, between 1 - 4 " +
                        "(inclusive), from the menu.");
            }
        }
        while(selection != 4);
    }

    /*********************************************
    *PURPOSE: Present user with Record duration play speed options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private static void recordDuration()
    {
        int selection;

        do
        {
            //Menu options
            System.out.println("\nPlease select a Play Speed:" +
                "\n1. 33 1/3 RPM" +
                "\n2. 45 RPM" +
                "\n3. 78 RPM" +
                "\n4. Return to Previous Menu");

            try
            {
                selection = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //clear buffer of incorrect input
                selection = 0; //force loop 
            }

            switch(selection)
            {
                case 1:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 2:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 3:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 4:
                    //Returning to searchMedia() menu
                break;

                default:
                    System.out.println(
                        "\nError! Please enter a valid integer, between 1 - 4 " +
                        "(inclusive), from the menu.");
            }
        }
        while(selection != 4);
    }

    /*********************************************
    *PURPOSE: Present user with Cassette duration tape side options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private static void cassetteDuration()
    {
        int selection;

        do
        {
            //Menu options
            System.out.println("\nPlease select the Tape Side:" +
                "\n1. 'A'" +
                "\n2. 'B'" +
                "\n3. Return to Previous Menu");

            try
            {
                selection = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //clear buffer of incorrect input
                selection = 0; //force loop 
            }

            switch(selection)
            {
                case 1:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 2:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 3:
                    //Returning to searchMedia() menu
                break;

                default:
                    System.out.println(
                        "\nError! Please enter a valid integer, between 1 - 3 " +
                        "(inclusive), from the menu.");
            }
        }
        while(selection != 3);
    }

    /*********************************************
    *PURPOSE: Present user with Digital duration file type options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private static void digitalDuration()
    {
        int selection;

        do
        {
            //Menu options
            System.out.println("\nPlease select a File Type:" +
                "\n1. wav" +
                "\n2. mp3" +
                "\n3. acc" +
                "\n4. Return to Previous Menu");

            try
            {
                selection = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //clear buffer of incorrect input
                selection = 0; //force loop 
            }

            switch(selection)
            {
                case 1:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 2:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 3:
                    System.out.println(
                        "\nSelected Option will be implemented in later stages");
                break;

                case 4:
                    //Returning to searchMedia() menu
                break;

                default:
                    System.out.println(
                        "\nError! Please enter a valid integer, between 1 - 4 " +
                        "(inclusive), from the menu.");
            }
        }
        while(selection != 4);
    }
}
