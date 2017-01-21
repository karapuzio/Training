package edu.training.project.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dell on 04.01.2017.
 */
public class Order {
    private int id;
    private double cost;
    private Date dateOfOrder;

    private List<Song> songs;

    public Order(int id, int userId, double cost, Date dateOfOrder) {
        this.id = id;
        this.cost = cost;
        this.dateOfOrder = dateOfOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (Double.compare(order.cost, cost) != 0) return false;
        return dateOfOrder != null ? dateOfOrder.equals(order.dateOfOrder) : order.dateOfOrder == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cost=" + cost +
                ", dateOfOrder=" + dateOfOrder +
                '}';
    }
}
