package com.buensabor.backend.Products;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image")
    private List<String> images = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)  // Asegúrate de que la fecha de creación no se actualice
    private Date created_at;

    @LastModifiedDate  // Usamos @LastModifiedDate en lugar de @UpdateTimestamp
    private Date updated_at;

    private Date deleted_at;
}
