package com.beauver.Objects;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Category extends PanacheEntity {
    public String name;
    public int orderId;

    public int getOrderId() {
        return orderId;
    }
}
