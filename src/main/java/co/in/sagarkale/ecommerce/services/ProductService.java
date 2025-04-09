package co.in.sagarkale.ecommerce.services;

import co.in.sagarkale.ecommerce.entities.Product;
import co.in.sagarkale.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(int productId, Product product){
        Optional<Product> exisitngProduct = productRepository.findById(productId);
        if(exisitngProduct.isPresent()){
            Product productToUpdate = exisitngProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            return productRepository.save(productToUpdate);
        }
        return null;
    }

    public Product deleteProduct(int productId){
        Optional<Product> exisitngProduct = productRepository.findById(productId);
        if(exisitngProduct.isPresent()){
            productRepository.deleteById(productId);
            return exisitngProduct.get();
        }
        return null;
    }
}
