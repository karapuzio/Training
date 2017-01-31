package edu.training.project.entity;

/**
 * Class is used to store MusicGenre object.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class MusicGenre {
    /**
     * The music genre's id.
     */
    private int id;
    /**
     * Name of music genre.
     */
    private String genre;

    public MusicGenre() {
    }

    public MusicGenre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicGenre that = (MusicGenre) o;

        if (id != that.id) return false;
        return genre != null ? genre.equals(that.genre) : that.genre == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MusicGenre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
