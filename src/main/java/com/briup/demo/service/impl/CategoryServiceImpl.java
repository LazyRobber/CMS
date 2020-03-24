package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 操作栏目的service功能类
 * 
 * @author wangkang
 *
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	//栏目的dao层
	@Autowired
	private CategoryMapper categoryMapper;
	
	// 栏目的拓展dao层
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	//文章的dao层
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		if (category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if (existCategoryName(category)) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "此栏目已存在");
		} else if (category.getId() == null) {
			categoryMapper.insert(category);
		} else {
			categoryMapper.updateByPrimaryKey(category);
		}
	}

	@Override
	public void deleteCategoryById(Integer id) throws CustomerException {
		// 删除栏目的同事，需要先删除栏目中包含的文章信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategorysById(Integer id) throws CustomerException {
		// 如果搜索条件不为null，则返回满足条件的数据
		return categoryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查找指定名称的栏目<br>
	 * 存在返回true<br>
	 * 不存在返回 false
	 */
	public boolean existCategoryName(Category category) {

		CategoryExample example = new CategoryExample();
		example.createCriteria().andNameEqualTo(category.getName());
		List<Category> list = categoryMapper.selectByExample(example);
		if (list != null) {
			return true;
		}
		return false;

	}

	/**
	 * 查找首页所有相关信息
	 */
	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		return categoryExMapper.findAllCategoryExs();
	}

	/**
	 * 查询栏目及其包含的文章的所有数据
	 */
	@Override
	public CategoryEx findCategoryExById(Integer id) throws CustomerException {
		return categoryExMapper.findCategoryExById(id);
	}
	
}
