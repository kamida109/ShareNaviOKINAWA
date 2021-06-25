package jp.co.group_c.category_process.dao;

import java.util.List;

import jp.co.group_c.entity.Category;

public interface CategoryProcessDao {

	public List<Category> selectMainCategory();
	public List<Category> selectAllChildCategory();
	void deleteCategory(Integer categoryId);
	void updateCategory(Integer categoryId, String categoryName);
	void insertCategory(String categoryName, Integer mainCategoryId);
	void insertCategory(String categoryName);
	public boolean findExistCategory(String pCateName);
	public Category findCategory(String subCateName);
	public Category findCategory(Integer cateId);

}
