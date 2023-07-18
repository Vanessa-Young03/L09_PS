package sg.edu.rp.c346.id22038532.l09_ps;

import java.io.Serializable;

public class Songs implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int years;
    private int stars;

    public Songs(int id, String title, String singers, int years, int stars)
    {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.years = years;
        this.stars = stars;

    }
    public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public String getSingers()
    {
        return singers;
    }
    public int getYear()
    {
        return years;
    }
    public int getStars()
    {
        return stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String toString() { return "Title: " + title +  '\n' + "Singers: " + singers +  '\n' + "Year: " + years +  '\n' + "Stars: " + stars ; }
}
