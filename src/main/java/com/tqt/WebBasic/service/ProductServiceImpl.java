package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.Product;
import com.tqt.WebBasic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        if(product != null){
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product product1 = productRepository.getReferenceById(id);
        if(product1 != null){
            product1.setIdBrand(product.getIdBrand());
            product1.setNameProduct(product.getNameProduct());
            product1.setPrice(product.getPrice());
            product1.setDescription(product.getDescription());
            return productRepository.save(product1);
        }
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product = productRepository.getReferenceById(id);
        if (product != null){
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();

    }

    @Override
    public Optional<Product> getOneProduct(int id) {
        return productRepository.findById(id);
    }
}
