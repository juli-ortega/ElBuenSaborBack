package com.buensabor.backend.Products;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private int stock;
    private List<String> images;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
