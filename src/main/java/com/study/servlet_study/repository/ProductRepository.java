package com.study.servlet_study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Product;
//계정 정보를 저장하고 조회하는데 사용되는 저장소 클래스입니다.
//싱글톤 패턴을 사용하여 하나의 인스턴스만 생성되도록 하였습니다. 
//계정을 저장하고, 유저네임으로 계정을 찾는 메서드를 제공합니다.
//2번 = Service클래스에서 사용해야하니까

public class ProductRepository {
	
	
	private List<Product> productList;
	
	//싱글톤 만들기
	private static ProductRepository instance;
	
	private ProductRepository() {
		productList = new ArrayList<>();
	}
	
	
	
	//싱글톤 만들어
	private static ProductRepository getInstance() {
		if(instance == null) {
			instance = new ProductRepository();
		}
		return instance;
	}
	
	public int saveProduct(Product product) {
		productList.add(product);
		return 1;
		
	}
	public Product findProductByproductname(String productname) {
		Product findProduct = null;
		
		
		for(Product product : productList ) {
			if(product.getProductName().equals(productname)) {
				findProduct = product;
				break;
			}
		}
		return findProduct;
	}


}
