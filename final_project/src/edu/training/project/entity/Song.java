package edu.training.project.entity;


import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 26.12.2016.*/
public class Song {
    private int id;
    private String name;
    private Date releaseDate;
    private int numberOfOrders;
    private String pathToDemo;
    private String pathToText;
    private int discountForSong;
    private double cost;

    private List<MusicGenre> genres;
    private List<Comment> comments;

    public Song(){
    }

    public Song(int id, String name, Date releaseDate, int numberOfOrders, String pathToDemo, String pathToText, int musicalGenreId, int discountForSong, double cost) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.numberOfOrders = numberOfOrders;
        this.pathToDemo = pathToDemo;
        this.pathToText = pathToText;
        this.discountForSong = discountForSong;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public String getPathToDemo() {
        return pathToDemo;
    }

    public void setPathToDemo(String pathToDemo) {
        this.pathToDemo = pathToDemo;
    }

    public String getPathToText() {
        return pathToText;
    }

    public void setPathToText(String pathToText) {
        this.pathToText = pathToText;
    }

    public int getDiscountForSong() {
        return discountForSong;
    }

    public void setDiscountForSong(int discountForSong) {
        this.discountForSong = discountForSong;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<MusicGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<MusicGenre> genres) {
        this.genres = genres;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (id != song.id) return false;
        if (numberOfOrders != song.numberOfOrders) return false;
        if (discountForSong != song.discountForSong) return false;
        if (Double.compare(song.cost, cost) != 0) return false;
        if (name != null ? !name.equals(song.name) : song.name != null) return false;
        if (releaseDate != null ? !releaseDate.equals(song.releaseDate) : song.releaseDate != null) return false;
        if (pathToDemo != null ? !pathToDemo.equals(song.pathToDemo) : song.pathToDemo != null) return false;
        return pathToText != null ? pathToText.equals(song.pathToText) : song.pathToText == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + numberOfOrders;
        result = 31 * result + (pathToDemo != null ? pathToDemo.hashCode() : 0);
        result = 31 * result + (pathToText != null ? pathToText.hashCode() : 0);
        result = 31 * result + discountForSong;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", numberOfOrders=" + numberOfOrders +
                ", pathToDemo='" + pathToDemo + '\'' +
                ", pathToText='" + pathToText + '\'' +
                ", discountForSong=" + discountForSong +
                ", cost=" + cost +
                '}';
    }
}
