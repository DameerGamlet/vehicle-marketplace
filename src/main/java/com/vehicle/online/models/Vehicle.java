package com.vehicle.online.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Vehicle extends PanacheEntity {
    public String type;
    public String brand;
    public String model;
    public int productionYear;
    public double price;

    public Vehicle() {
    }

    public Vehicle(String type, String brand, String model, int productionYear, double price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.price = price;
    }
}
