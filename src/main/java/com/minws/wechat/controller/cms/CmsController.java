package com.minws.wechat.controller.cms;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.minws.wechat.model.cms.Article;
import com.minws.wechat.model.cms.Category;

public class CmsController extends Controller {

	public void index() {
		setAttr("recentArticleList", Article.dao.selectRecentArticles(10));
		render("front/index.htm");
	}

	public void article() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.selectAllArticles(pageNumber, pageSize));
		setAttr("categoryList", Category.dao.selectAllCategories());
		setAttr("popularArticleList", Article.dao.selectPopularArticles(5));
		render("front/article.htm");
	}

	public void single() {
		Integer articleId = getParaToInt("articleId", 1);
		setAttr("article", Article.dao.selectArticleByArticleId(articleId));
		setAttr("categoryList", Category.dao.selectAllCategories());
		setAttr("popularArticleList", Article.dao.selectPopularArticles(5));
		render("front/single.htm");
	}

	public void work() {
		Integer articleId = getParaToInt("articleId", 1);
		setAttr("article", Article.dao.selectArticleByArticleId(articleId));
		setAttr("categoryList", Category.dao.selectAllCategories());
		setAttr("popularArticleList", Article.dao.selectPopularArticles(5));
		render("front/work.htm");
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

	public void contact() {
		render("front/contact.htm");
	}

	@ActionKey("/cms/manage/article")
	public void manageBlog() {
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
		}
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.selectAllArticles(pageNumber, pageSize));
		render("back/blogManage.htm");
	}

	@ActionKey("/cms/add/article")
	public void addBlog() {
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
		}
		render("back/frontBlogAdd.htm");
	}
}
