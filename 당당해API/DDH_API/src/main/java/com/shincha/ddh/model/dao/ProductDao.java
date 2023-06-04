package com.shincha.ddh.model.dao;

import java.util.List;

import com.shincha.ddh.model.dto.Product;

public interface ProductDao {
	
	//상품 불러오기(최신순)
	List<Product> selectAll();
	
	//타입에 따른 상품 불러오기(식료품/생활용품/가전)
	List<Product> selectType(String type);
}
