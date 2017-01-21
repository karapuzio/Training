package edu.training.project.entity;

/**
 * Created by Dell on 26.12.2016.
 */
public class MusicGenre {
    private int id;
    private String genre;

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
