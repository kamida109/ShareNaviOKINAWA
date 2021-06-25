package jp.co.group_c.category_process.service;

import java.util.List;

import jp.co.group_c.entity.Category;

public interface CategoryProcessService {

	public List<Category> selectMainCategory();
	public List<Category> selectAllChildCategory();
	public void deleteCategory(Integer categoryId);
	public void updateCategory(Integer categoryId, String categoryName);
	public void insertCategory(String categoryName, Integer mainCategoryId);
	public void insertCategory(String categoryName);
	public boolean findExistCategory(String pCateName);
	public Category findCategory(String cateName);
	public Category findCategory(Integer cateId);
}
