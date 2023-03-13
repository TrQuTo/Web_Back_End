package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.Product;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    //add
    public Product addProduct(Product product);
    public Product updateProduct(int id, Product product);
    public boolean deleteProduct(int id);
    public List<Product> getAllProduct();
    public Optional<Product> getOneProduct(int id);

    public String uploadImage(int productId, MultipartHttpServletRequest request);
}
