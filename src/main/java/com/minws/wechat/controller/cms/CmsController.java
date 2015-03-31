package com.minws.wechat.controller.cms;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.minws.wechat.entity.sys.DataGrid;
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

	/**
	 * 分页查询客户
	 */
	public void articleJson() {
		int pageNumber = getParaToInt("page", 1);
		int pageSize = getParaToInt("rows", 10);
		Page<Article> articlePage = Article.dao.selectAllArticles(pageNumber, pageSize);
		DataGrid DataGrid = new DataGrid(String.valueOf(articlePage.getTotalRow()), articlePage.getList());
		renderJson(DataGrid);
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

	@ActionKey("cms/back/article/manage")
	@RequiresPermissions("cms:article")
	public void manageArticle() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.selectAllArticles(pageNumber, pageSize));
		render("back/article-manage.htm");
	}

	@ActionKey("cms/back/article/add")
	@RequiresPermissions("cms:article")
	public void addArticle() {
		String articleTitle = getPara("articleTitle","");
		String categoryId = getPara("categoryId","");
		String editorValue = getPara("editorValue","");
		if ("POST".equalsIgnoreCase(this.getRequest().getMethod().toUpperCase())) {
		}
		setAttr("categoryList", Category.dao.selectAllCategories());
		setAttr("articleTitle", articleTitle);
		setAttr("categoryId", categoryId);
		setAttr("editorValue", editorValue);
		render("back/article-add.htm");
	}
}
