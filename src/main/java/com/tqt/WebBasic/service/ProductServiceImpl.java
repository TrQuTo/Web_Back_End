package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.Product;
import com.tqt.WebBasic.model.User;
import com.tqt.WebBasic.repository.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {

        if (product != null) {
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product product1 = productRepository.getReferenceById(id);
        product1.setIdBrand(product.getIdBrand());
        product1.setNameProduct(product.getNameProduct());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        return productRepository.save(product1);
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
        return true;
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
