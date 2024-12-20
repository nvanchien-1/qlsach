package com.service.uesr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.connectData;
import com.model.Category;

public class CategoryService {
  public List<Category> getAllCategory(){
	 List <Category> list = new ArrayList<>();
	  String query = "select * from categories";
	  try {
		  Connection con = connectData.getConnection();
		 PreparedStatement stm = con.prepareStatement(query) ;
		 ResultSet rs = stm.executeQuery();
		 
		while (rs.next()) {
			Category category = new Category();
			category.setCategoryId(rs.getInt("CategoryID"));
			category.setNameCategory(rs.getString("Name"));
			list.add(category);					
		} 	  
	  }
	  catch (Exception e) {		
	}	  
	 return list; 
	  
  }
  
  public Category getCategoryById(int categoryId) {
	    String query = "SELECT * FROM Categories WHERE CategoryID = ?";
	    Category category = null;

	    try (Connection con = connectData.getConnection();
	         PreparedStatement stm = con.prepareStatement(query)) {
	        
	        stm.setInt(1, categoryId);
	        ResultSet rs = stm.executeQuery();

	        if (rs.next()) {
	            category = new Category();
	            category.setCategoryId(rs.getInt("CategoryID"));
	            category.setNameCategory(rs.getString("Name"));	          
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return category;
	}
  
}
