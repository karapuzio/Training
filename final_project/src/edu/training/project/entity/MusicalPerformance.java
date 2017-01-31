package edu.training.project.entity;

/**
 * Class is used to store MusicalPerformance object.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class MusicalPerformance {
    /**
     * The musical performance's id.
     */
    private int id;
    /**
     * The musical performance's name.
     */
    private String name;

    public MusicalPerformance() {
    }

    public MusicalPerformance(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicalPerformance that = (MusicalPerformance) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MusicalPerformance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
