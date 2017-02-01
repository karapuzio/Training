package edu.training.project.entity;


import java.util.List;

/**
 * Created by Dell on 26.12.2016.*/
public class Song {
    private int DEFAULT_VALUE_IN_DB = 1;
    /**
     * The song's id.
     */
    private int id;
    /**
     * The song's name.
     */
    private String name;
    /**
     * The path to demo version.
     */
    private String pathToDemo;
    /**
     * The song's cost.
     */
    private double cost;
    /**
     * The genre id.
     */
    private int genreId = DEFAULT_VALUE_IN_DB;
    /**
     * The performance id, who sing song.
     */
    private int performanceId = DEFAULT_VALUE_IN_DB;
    /**
     * The performance, whi sing song.
     */
    private MusicalPerformance performance;
    /**
     * The similarity coefficient to search requesr.
     */
    private double coeffJakkara;
    /**
     * The flag of deleted song.
     */
    private int isDeleted = 0;

    private List<Comment> comments;

    public Song(){
    }

    public Song(int id, String name, String pathToDemo, double cost, int genreId, int performanceId) {
        this.id = id;
        this.name = name;
        this.pathToDemo = pathToDemo;
        this.cost = cost;
        this.genreId = genreId;
        this.performanceId = performanceId;
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

    public String getPathToDemo() {
        return pathToDemo;
    }

    public void setPathToDemo(String pathToDemo) {
        this.pathToDemo = pathToDemo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public MusicalPerformance getPerformance() {
        return performance;
    }

    public void setPerformance(MusicalPerformance performance) {
        this.performance = performance;
    }

    public double getCoeffJakkara() {
        return coeffJakkara;
    }

    public void setCoeffJakkara(double coeffJakkara) {
        this.coeffJakkara = coeffJakkara;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Song song = (Song) object;

        if (id != song.id) return false;
        if (Double.compare(song.cost, cost) != 0) return false;
        if (genreId != song.genreId) return false;
        if (performanceId != song.performanceId) return false;
        if (name != null ? !name.equals(song.name) : song.name != null) return false;
        if (pathToDemo != null ? !pathToDemo.equals(song.pathToDemo) : song.pathToDemo != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pathToDemo != null ? pathToDemo.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + genreId;
        result = 31 * result + performanceId;
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pathToDemo='" + pathToDemo + '\'' +
                ", cost=" + cost +
                ", genreId=" + genreId +
                ", performanceId=" + performanceId +
                ", comments=" + comments +
                '}';
    }
}
