package com.beauver.Objects;

import com.beauver.Enums.ItemType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Item extends PanacheEntity {
    public String name;
    public String description;

    @Nullable
    public ItemType itemType;

    @ManyToOne
    public Category category;
    public String link;
    public String logoLink;
}
