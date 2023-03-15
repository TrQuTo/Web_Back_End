package com.tqt.WebBasic.service;

import com.tqt.WebBasic.model.Brand;
import com.tqt.WebBasic.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getOneBrand(int id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand addBrand(Brand brand) {
        if (brand != null) {
            return brandRepository.save(brand);
        }
        return null;
    }

    @Override
    public Brand updateBrand(int id, Brand brand) {
        Brand brand1 = brandRepository.getReferenceById(id);
        if (brand1 != null) {
            brand1.setNameBrand(brand.getNameBrand());
            return brandRepository.save(brand1);
        }
        return null;
    }

    @Override
    public boolean deleteBrand(int id) {
        Brand brand = brandRepository.getReferenceById(id);
        if (brand != null) {
            brandRepository.delete(brand);
            return true;
        }
        return false;
    }
}
