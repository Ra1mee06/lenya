package com.souvenirs.models;

import java.util.Date;
import java.util.Objects;

public class Souvenir {
    private int souvenirId;
    private String name;
    private int manufacturerId;
    private Date releaseDate;
    private double price;
    
    public Souvenir() {}
    
    public Souvenir(int souvenirId, String name, int manufacturerId, Date releaseDate, double price) {
        this.souvenirId = souvenirId;
        this.name = name;
        this.manufacturerId = manufacturerId;
        this.releaseDate = releaseDate;
        this.price = price;
    }
    
    public int getSouvenirId() {
        return souvenirId;
    }
    
    public void setSouvenirId(int souvenirId) {
        this.souvenirId = souvenirId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getManufacturerId() {
        return manufacturerId;
    }
    
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenir souvenir = (Souvenir) o;
        return souvenirId == souvenir.souvenirId &&
                manufacturerId == souvenir.manufacturerId &&
                Double.compare(souvenir.price, price) == 0 &&
                Objects.equals(name, souvenir.name) &&
                Objects.equals(releaseDate, souvenir.releaseDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(souvenirId, name, manufacturerId, releaseDate, price);
    }
    
    @Override
    public String toString() {
        return "Souvenir{" +
                "souvenirId=" + souvenirId +
                ", name='" + name + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}