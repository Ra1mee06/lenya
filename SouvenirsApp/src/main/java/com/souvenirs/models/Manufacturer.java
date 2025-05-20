package com.souvenirs.models;

import java.util.Objects;

public class Manufacturer {
    private int manufacturerId;
    private String name;
    private String country;
    
    public Manufacturer() {}
    
    public Manufacturer(int manufacturerId, String name, String country) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.country = country;
    }
    
    public int getManufacturerId() {
        return manufacturerId;
    }
    
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return manufacturerId == that.manufacturerId &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(manufacturerId, name, country);
    }
    
    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}