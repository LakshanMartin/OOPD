public class MusicLibrary 
{
    public static void main(String[] args)
    {
        boolean exit;

        System.out.println("\nWELCOME TO YOUR MUSIC LIBRARY!\n");

        exit = UserInterface.addMusicMenu();

        if(!exit)
        {
           UserInterface.mainMenu(); 
        }
    }
}
