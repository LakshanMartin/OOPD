public abstract class Music
{
    //CLASS FIELDS
    private String name;
    private String artist;
    private double duration;

    //DEFAULT CONSTRUCTOR
    public Music()
    {
        this.name = "";
        this.artist = "";
        this.duration = 0.0;
    }

    //ALTERNATE CONSTRUCTOR
    public Music(String name, String artist, double duration)
    {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
    }

    //COPY CONSTRUCTOR
    public Music(Music toCopy)
    {
        this.name = toCopy.getName();
        this.artist = toCopy.getArtist();
        this.duration = toCopy.getDuration();
    }

    //ACCESSORS
    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public double getDuration()
    {
        return duration;
    }

    //ABSTRACT METHODS
    public abstract String play();

    public abstract boolean search(String toFind);

    public abstract Music clone();
}