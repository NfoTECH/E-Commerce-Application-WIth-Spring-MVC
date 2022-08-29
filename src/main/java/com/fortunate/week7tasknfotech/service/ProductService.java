package com.fortunate.week7tasknfotech.service;

import com.fortunate.week7tasknfotech.model.Product;
import com.fortunate.week7tasknfotech.repository.ProductRepository;
import com.fortunate.week7tasknfotech.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> listAllProduct() {
        return repository.findAll();
    }

    public void saveProduct(Product product) {
        this.repository.save(product);
    }

    public void saveProductWithImage(MultipartFile multipartFile, Product product) throws IOException {
        String imageFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "src/main/resources/static/images";
        FileUploadUtil.saveFile(uploadDir, imageFileName, multipartFile);
        product.setImage(imageFileName);
        this.repository.save(product);
    }

    public Product get(Long id) {
        Optional<Product> optional = repository.findById(id);
        Product product;
        if (optional.isPresent()) product = optional.get();
        else throw new RuntimeException("Product not found for id :: " + id);
        return product;
    }

    public void deleteProductById(Long id) {
        this.repository.deleteById(id);
    }

    public Product updateProduct( Product product) {
        return repository.save(product);
    }
}
