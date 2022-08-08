package sg.edu.rp.c346.mymovies;

import java.util.Calendar;

public class Movies {
    private int id;
    private String title;
    private String genre;
    private int year;
    private String rating;
    private Object Movies;

    public Movies(int id, String title, String genre, int year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title +
                "\n" + genre +
                "\n" + year +
                "\n" + rating;
    }

    public Movies toStringClass() {
        return (sg.edu.rp.c346.mymovies.Movies) Movies;
    }
}