package kr.hhplus.be.domain.product.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    private Long prodId;
    private Integer stock;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public static ProductInventory of(Long prodId, int stock) {
        ProductInventory productInventory = new ProductInventory();

        productInventory.prodId = prodId;
        productInventory.stock = stock;
        productInventory.createDate = LocalDateTime.now();
        productInventory.modifyDate = LocalDateTime.now();

        return productInventory;

    }

    public void checkStock(int amount) {
        if (this.stock < amount) {
            throw new RuntimeException();
        }
    }

    public void deductStock(int amount) {
        if (this.stock < amount) {
            throw new RuntimeException();
        }

        this.stock -= amount;
    }

    public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        this.stock += amount;
    }

}
