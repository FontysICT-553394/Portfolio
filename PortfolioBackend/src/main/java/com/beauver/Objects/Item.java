package com.beauver.Objects;

import com.beauver.Enums.ItemType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Item extends PanacheEntity {
    public String name;
    public String description;
    public ItemType itemType;
    public String learnedAt;
}
