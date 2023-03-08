package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    public List<Brand> getAllBrand();
    public Optional<Brand> getOneBrand(int id);
    public Brand addBrand(Brand brand);
    public Brand updateBrand(int id, Brand brand);
    public boolean deleleBrand(int id);
}
