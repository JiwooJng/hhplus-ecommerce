package kr.hhplus.be.domain.product.repository;

import kr.hhplus.be.domain.product.entity.Product;

public interface ProductRepository {
    Product save(Product product);
    Product findByProdId(Long prodId);
}
