package kr.hhplus.be.domain.product.service;

import kr.hhplus.be.domain.product.dto.ProductDTO;
import kr.hhplus.be.domain.product.entity.Product;
import kr.hhplus.be.domain.product.entity.ProductInventory;
import kr.hhplus.be.domain.product.repository.ProductInventoryRepository;
import kr.hhplus.be.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductInventoryRepository productInventoryRepository;

    // 상품 조회
    public ProductDTO getProdInfo(Long prodId) {
        Product product = productRepository.findByProdId(prodId);

        return new ProductDTO(product.getProdName(), product.getPrice());
    }

    public void checkStock(Long prodId, int amount) {
        ProductInventory productInventory = productInventoryRepository.findByProdId(prodId);
        productInventory.checkStock(amount);
    }

    // 재고 차감
    public void deductStock(Long prodId, int amount) {
        ProductInventory productInventory = productInventoryRepository.findByProdId(prodId);
        productInventory.deductStock(amount);

        productInventoryRepository.save(productInventory);
    }

    // 재고 추가
    public void addStock(Long prodId, int amount) {
        ProductInventory productInventory = productInventoryRepository.findByProdId(prodId);
        productInventory.addStock(amount);
    }
}
