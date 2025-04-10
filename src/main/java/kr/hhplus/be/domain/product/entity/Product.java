package kr.hhplus.be.domain.product.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long prodId;
    private String prodName;

    private BigDecimal price;

    public static Product of(String prodName, BigDecimal price) {
        Product product = new Product();

        product.prodName = prodName;
        product.price = price;

        return product;
    }

}
