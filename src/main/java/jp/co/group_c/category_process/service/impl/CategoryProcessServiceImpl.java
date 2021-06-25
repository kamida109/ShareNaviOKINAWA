package jp.co.group_c.category_process.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.group_c.category_process.dao.CategoryProcessDao;
import jp.co.group_c.category_process.service.CategoryProcessService;
import jp.co.group_c.entity.Category;

@Service
public class CategoryProcessServiceImpl implements CategoryProcessService {

	@Autowired
	CategoryProcessDao categoryProcessDao;

	@Override
	public List<Category> selectMainCategory() {
		return categoryProcessDao.selectMainCategory();
	}

	@Override
	public List<Category> selectAllChildCategory() {
		return categoryProcessDao.selectAllChildCategory();
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryProcessDao.deleteCategory(categoryId);
	}

	@Override
	public void updateCategory(Integer categoryId, String categoryName) {
		categoryProcessDao.updateCategory(categoryId, categoryName);
	}

	@Override
	public void insertCategory(String categoryName, Integer mainCategoryId) {
		categoryProcessDao.insertCategory(categoryName, mainCategoryId);
	}

	@Override
	public void insertCategory(String categoryName) {
		categoryProcessDao.insertCategory(categoryName);
	}

	@Override
	public boolean findExistCategory(String pCateName) {
		return categoryProcessDao.findExistCategory(pCateName);
	}

	@Override
	public Category findCategory(String cateName) {
		return categoryProcessDao.findCategory(cateName);
	}
	@Override
	public Category findCategory(Integer cateId) {
		return categoryProcessDao.findCategory(cateId);
	}

}
