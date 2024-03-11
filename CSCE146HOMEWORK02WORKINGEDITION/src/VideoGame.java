/*
 * Written by Michelle Clark
 */
public class VideoGame 
{
    private String name;
    private String console;

    public VideoGame()
    {
        this.name="none";
        this.console="none";
    }
    public VideoGame(String name, String console)
    {
        this.setName(name);
        this.setConsole(console);
    }
    public void setName(String name)
    {
        if(name!=null)
            this.name=name;
        else
            this.name="none";
    }
    public void setConsole(String console)
    {
        if(console!=null)
            this.console=console;
        else
            this.console="none";
    }
    public String getName()
    {
        return this.name;
    }
    public String getConsole()
    {
        return this.console;
    }
    public String toString()
    {
        return name+"\t"+console;
    }
    public boolean containsName(VideoGame other)
    {
        return other!=null && this.getName().toUpperCase().contains(other.getName().toUpperCase());
    }
    public boolean containsConsole(VideoGame other)
    {
        return other!=null && this.getConsole().toUpperCase().contains(other.getConsole().toUpperCase());
    }
    public boolean equals(VideoGame other)
    {
        return other!=null && this.getName().equalsIgnoreCase(other.getName()) && 
        this.getConsole().equalsIgnoreCase(other.getConsole());
    }
}
