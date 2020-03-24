package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与栏目相关的 web层
 * @author wangkang
 *
 */
@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/getAllCategory")
	@ApiOperation("获取所有的栏目信息")
	public Message<List<Category>> findAllCategory(){
		List<Category> list = categoryService.findAllCategorys();
		return MessageUtil.success(list);
	}
	
	@GetMapping("/saveCategory")
	@ApiOperation("添加栏目")
	public Message<String> addCategory(Category category){
		try {
			categoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误：" + e.getMessage());
		}
	}
	
	@GetMapping("/updateCategory")
	@ApiOperation("修改栏目信息")
	public Message<String> updateCategory(Category category){
		categoryService.saveOrUpdateCategory(category);
		return MessageUtil.success();
	}
	
	@GetMapping("/deleteCategoryById")
	@ApiOperation("根据id删除栏目信息")
	public Message<String> deleteCategoryId(int id){
		categoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/findCategorysById")
	@ApiOperation("根据id查询指定栏目信息")
	public Message<Category> findCategorysById(int id){
		return MessageUtil.success(categoryService.findCategorysById(id));
	}
	
	/**
	 * 根据id查找栏目及其包含的所有文章信息
	 */
	@GetMapping("/findCategoryExById")
	@ApiOperation("根据栏目id查找栏目及其包含文章的信息")
	public Message<CategoryEx> findCategoryExById(int id){
		return MessageUtil.success(categoryService.findCategoryExById(id));
	}
}
