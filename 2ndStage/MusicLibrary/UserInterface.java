import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface 
{
    //CLASS FIEDS
    private MusicController mCont;
    private FileManager fm;

    //CONSTRUCTOR
    public UserInterface(MusicController mCont, FileManager fm)
    {
        this.mCont = mCont;
        this.fm = fm;
    }

    /*******************************************************
    *PURPOSE: Present options to add music to the library
    *IMPORT: none
    *EXPORT: boolean
    ********************************************************/
    public boolean addMusicMenu()
    {
        int selection;
        String filename;
        boolean exit, songsAdded;
        Scanner sc = new Scanner(System.in);

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
                    fm.readFile(filename);
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
    private void selectMedia()
    {
        int selection;
        Scanner sc = new Scanner(System.in);
        
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
    private void addRecord()
    {
        String name, artist, prompt, option1, option2, option3, playSpeed;
        double duration;
        int trackNumber;
        RecordClass toAdd;
            
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "\nPlease enter the song Duration as a real " +
        "number between 0.0 and 9.59 (inclusive):";
        //duration range based on constants
        duration = durationInput(prompt);
        
        prompt = "\nPlease enter the Track Number as an integer:";
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

        //Create RecordClass object to add
        toAdd = new RecordClass(name, artist, duration, trackNumber, playSpeed);       
        mCont.addRecord(toAdd);
    }

    /***********************************************************
    *PURPOSE: Record and validate user input for cassette info
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    ************************************************************/
    private void addCassette()
    {
        String name, artist, prompt;
        double duration, start;
        int trackNumber;
        char side;
        CassetteClass toAdd;
        
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "\nPlease enter the song Duration as a real number between " +
        "0.0 and 9.59 (inclusive):";
        //duration range based on constants
        duration = durationInput(prompt);
        
        prompt = "\nPlease enter the Track Number as an integer:";
        trackNumber = intInput(prompt);
        
        prompt = "\nPlease enter the song Start time as a real number between " +
        "0.0 and 160.0 (inclusive):";
        start = startInput(prompt);

        prompt = "\nPlease enter which side of the cassette the song is on " +
                 "as a single character, 'A' OR 'B':";
        side = sideInput(prompt);

        //Create CassetteClass object to add
        toAdd = new CassetteClass(name, artist, duration, trackNumber,
                                        start, side);
        mCont.addCassette(toAdd);
    }

    /*********************************************************
    *PURPOSE: Record and validate user input for digitals info      
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    **********************************************************/
    private void addDigital()
    {
        String name, artist, prompt, option1, option2, option3, type;
        double duration;
        DigitalClass toAdd;
                    
        prompt = "\nPlease enter the Song name:";
        name = stringInput(prompt);//record song name

        prompt = "\nPlease enter the Artist name:";
        artist = stringInput(prompt);//record artist name
            
        prompt = "\nPlease enter the song Duration as a real " +
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

        //Create DigitalClass object to add
        toAdd = new DigitalClass(name, artist, duration, type);
        mCont.addDigital(toAdd);
    }

    /*********************************************
    *PURPOSE: Record user string input
    *IMPORT: prompt
    *EXPORT: userInput
    *ASSERTION: Return userInput
    **********************************************/
    private String stringInput(String prompt)
    {
        String userInput;
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);
        userInput = sc.nextLine();

        return userInput;
    }

    /*****************************************************
    *PURPOSE: Store and validate duration input
    *IMPORT: prompt
    *EXPORT: duration
    *ASSERTION: Return validated duration 
    ******************************************************/
    private double durationInput(String prompt)
    {
        String input;
        boolean isValid;
        double duration;
        String[] split;
        Scanner sc = new Scanner(System.in);

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
    private int intInput(String prompt)
    {
        String input;
        boolean isValid;
        int integer;
        Scanner sc = new Scanner(System.in);

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
    private String stringOptions(String prompt, String option1,
    String option2, String option3)
    {
        String selectedString;
        int selection;
        Scanner sc = new Scanner(System.in);

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
    private double startInput(String prompt)
    {
        double start;
        String input;
        boolean isValid;
        Scanner sc = new Scanner(System.in);

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
    private char sideInput(String prompt)
    {
        char side;
        String input;
        boolean isValid;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println(prompt); //ouptut imported prompt
            input = sc.next(); //store user input
            isValid = Validate.checkSide(input);
        }
        while(!isValid);

        //Convert to upper case and to char 
        side = input.toUpperCase().charAt(0);

        return side;
    }

    /***********************************************************
    *PURPOSE: Present Main menu options to user
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    ************************************************************/
    public void mainMenu()
    {
        int selection;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("\n\nMAIN MENU");
            System.out.println("---------");

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
                    getTotalPlayTime();    
                break;

                case 2:
                    searchMenu();
                break;

                case 3:
                    fm.exportLibrary();
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
    *PURPOSE: Get total play time
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *************************************************/
    private void getTotalPlayTime()
    {
        double totalPlayTime;
        String formatted;

        totalPlayTime = mCont.getTotalPlayTime();
        formatted = String.format("%.2f", totalPlayTime);

        System.out.println("\nLibrary's Total Play Time: " + formatted);
    }

    /************************************************
    *PURPOSE: Present user with Search menu options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *************************************************/
    private void searchMenu()
    {
        int selection;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("\n\nSEARCH MENU");
            System.out.println("------------");

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
                    searchPlayInstructions();
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
    *PURPOSE: Present user with option to enter song name
    *IMPORT: none
    *EXPORT: user input
    *ASSERTION: none
    *****************************************************/
    private void searchPlayInstructions()
    {
        String prompt, input, playInst;

        //Get user input
        prompt = "\nPlease enter the song name:";
        input = stringInput(prompt);

        //Search for Play Instructions
        playInst = mCont.getPlayInstructions(input);

        System.out.println(playInst);
    }

    /*********************************************
    *PURPOSE: Present user with Search sub menu options
    *IMPORT: none
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private void searchMedia()
    {
        int selection;
        Scanner sc = new Scanner(System.in);

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
                    recordNumSongsRunTime();
                break;

                case 2:
                    cassetteNumSongsRunTime();
                break;

                case 3:
                    digitalNumSongsRunTime();
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
    private void recordNumSongsRunTime()
    {
        int selection;
        String option1, option2, option3, record, playSpeed;
        Scanner sc = new Scanner(System.in);

        option1 = "33 1/3 RPM";
        option2 = "45 RPM";
        option3 = "78 RPM";
        record = "Record";
        playSpeed = "Play Speed songs:";

        do
        {
            //Menu options
            System.out.println("\nPlease select a " + playSpeed + ":" +
                "\n1. " + option1 +
                "\n2. " + option2 +
                "\n3. " + option3 +
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
                    outputMediaDetails(record, option1, playSpeed);
                break;

                case 2:
                    outputMediaDetails(record, option2, playSpeed);
                break;

                case 3:
                    outputMediaDetails(record, option3, playSpeed);
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
    private void cassetteNumSongsRunTime()
    {
        int selection;
        String cassette, side;
        Scanner sc = new Scanner(System.in);

        cassette = "Cassette";
        side = "Tape Side songs:";

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
                    outputMediaDetails(cassette, "A", side);
                break;

                case 2:
                    outputMediaDetails(cassette, "B", side);
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
    private void digitalNumSongsRunTime()
    {
        int selection;
        String digital, fileType;
        Scanner sc = new Scanner(System.in);

        digital = "Digital";
        fileType = "File Type songs:";

        do
        {
            //Menu options
            System.out.println("\nPlease select a File Type:" +
                "\n1. WAV" +
                "\n2. MP3" +
                "\n3. ACC" +
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
                    outputMediaDetails(digital, "WAV", fileType);
                break;

                case 2:
                    outputMediaDetails(digital, "MP3", fileType); 
                break;

                case 3:
                    outputMediaDetails(digital, "ACC", fileType);
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
    *PURPOSE: Output number of songs and run time for imported song type and
    *         format 
    *IMPORT: media, criteria, type
    *EXPORT: none
    *ASSERTION: none
    *****************************************************/
    private void outputMediaDetails(String media, String criteria, String type)
    {
        int numSongs;
        double runTime;
        String strRunTime;

        //Retrieve media details
        numSongs = mCont.getNumSongs(criteria);
        runTime = mCont.getRunTime(criteria);

        //Format runTime to 2 decimal places
        strRunTime = String.format("%.2f", runTime);

        //Output media details
        System.out.println(
                    "\n" + media + " details for " + criteria + " " + type + 
                    "\nNumber of Songs: " + numSongs +
                    "\nTotal Run Time: " + strRunTime + "\n");
    }
}
