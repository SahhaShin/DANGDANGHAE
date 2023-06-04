package com.shincha.ddh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shincha.ddh.model.dto.Product;
import com.shincha.ddh.model.service.ProductService;

@RestController
@RequestMapping("/api-product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	//1. 상품 불러오기 (최신순)
	@GetMapping("/productList")
	public ResponseEntity<?> doProductList() {
		List<Product> productList = productService.selectAll();
	
		if (productList != null && productList.size() > 0) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//2. 타입에 따른 상품 정보 불러오기 (식료품/생활용품/가전)
	@GetMapping("/productByType/{type}")
	public ResponseEntity<?> productByType(@PathVariable String type) {
		List<Product> productList = productService.selectType(type);
	
		if (productList != null && productList.size() > 0) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
