package com.shincha.ddh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shincha.ddh.model.dao.ProductDao;
import com.shincha.ddh.model.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> selectAll() {
		return productDao.selectAll();
	}

	@Override
	public List<Product> selectType(String type) {
		return productDao.selectType(type);
	}

}
