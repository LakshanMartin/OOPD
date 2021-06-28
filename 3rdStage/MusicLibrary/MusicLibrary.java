public class MusicLibrary 
{
    public static void main(String[] args)
    {
        MusicCollection music;
        MusicController mCont;
        FileManager fm;
        UserInterface ui;
        boolean exit;

        System.out.println("\nWELCOME TO YOUR MUSIC LIBRARY!\n");

        //Initialise objects
        music = new MusicCollection();
        mCont = new MusicController(music);
        fm = new FileManager(mCont);
        ui = new UserInterface(mCont, fm);
        
        exit = ui.addMusicMenu();

        if(!exit)
        {
           ui.mainMenu(); 
        }
    }
}
