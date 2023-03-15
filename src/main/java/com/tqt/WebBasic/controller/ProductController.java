package com.tqt.WebBasic.controller;

import com.tqt.WebBasic.model.Product;
import com.tqt.WebBasic.service.IGeneralService;
import com.tqt.WebBasic.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
    IProductService iProductService;
    @Autowired
    IGeneralService iGeneralService;
    @GetMapping("")
    public List<Product> showAllProduct() {
        return iProductService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Optional<Product> showOneProduct(@PathVariable("id") int id) {
        return iProductService.getOneProduct(id);
    }

    @PostMapping("/add")
    public Product addProduct(@ModelAttribute Product product, @RequestParam("files") MultipartFile[] files) {
        //Call method upload image
        product.setImage_url(iGeneralService.addImage(files,"products"));
        return iProductService.addProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestParam("id") int id, @RequestBody Product product) {
        return iProductService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProduct(@PathVariable("id") int id) {
        return iProductService.deleteProduct(id);
    }

}
