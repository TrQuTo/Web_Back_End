package com.tqt.WebBasic.controller;

import com.tqt.WebBasic.model.Brand;
import com.tqt.WebBasic.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "brands")
public class BrandController {
    @Autowired
    IBrandService iBrandService;

    @GetMapping("")
    public List<Brand> showALlBrand() {
        return iBrandService.getAllBrand();
    }

    @GetMapping("/{id}")
    public Optional<Brand> showOneBrand(@PathVariable("id") int id) {
        return iBrandService.getOneBrand(id);
    }

    @PostMapping("/add")
    public Brand addBrand(@RequestBody Brand brand) {
        return iBrandService.addBrand(brand);
    }

    @PutMapping("/update")
    public Brand updateBrand(@RequestParam("id") int id, @RequestBody Brand brand) {
        return iBrandService.updateBrand(id, brand);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteBrand(@PathVariable("id") int id) {
        return iBrandService.deleteBrand(id);
    }
}
