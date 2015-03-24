package com.minws.wechat.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.minws.wechat.model.blog.Article;

public class BlogController extends Controller {

	public void index() {
		render("front/index.htm");
	}

	public void blog() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.selectAllArticles(pageNumber, pageSize));
		render("front/blog.htm");
	}

	public void page() {
		render("front/page.htm");
	}

	public void pageTypography() {
		render("front/page-typography.htm");
	}

	public void pageElements() {
		render("front/page-elements.htm");
	}

	public void work() {
		render("front/work.htm");
	}

	public void contact() {
		render("front/contact.htm");
	}

	@ActionKey("/blog/manage/article")
	public void manageBlog() {
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
		}
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.selectAllArticles(pageNumber, pageSize));
		render("blog/blogManage.htm");
	}

	@ActionKey("/blog/add/article")
	public void addBlog() {
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
		}
		render("front/frontBlogAdd.htm");
	}
}
