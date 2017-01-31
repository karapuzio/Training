package edu.training.project.entity;

import java.util.Date;

/**
 * Class is used to store Order object.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class Order {
    /**
     * The order's id.
     */
    private int id;
    /**
     * The user id, who order song.
     */
    private int userId;
    /**
     * The song id, which was order.
     */
    private int songId;
    /**
     * The flags show order is payed.
     */
    private int isPayed;
    /**
     * The date of order song.
     */
    private Date dateOfOrder;
    /**
     * The song, which was order.
     */
    private Song song;

    public Order() {
    }

    public Order(int id, int userId, int songId, int isPayed, Date dateOfOrder) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.isPayed = isPayed;
        this.dateOfOrder = dateOfOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(int isPayed) {
        this.isPayed = isPayed;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (songId != order.songId) return false;
        if (isPayed != order.isPayed) return false;
        return dateOfOrder != null ? dateOfOrder.equals(order.dateOfOrder) : order.dateOfOrder == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + songId;
        result = 31 * result + isPayed;
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", songId=" + songId +
                ", isPayed=" + isPayed +
                ", dateOfOrder=" + dateOfOrder +
                '}';
    }
}
