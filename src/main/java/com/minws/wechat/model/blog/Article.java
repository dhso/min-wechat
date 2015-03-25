/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wechat.model.blog;

import java.util.List;

import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName = "blog_articles", pkName = "id")
public class Article extends Model<Article> {
	public static final Article dao = new Article();

	public Page<Article> selectArticlesByCategoryId(int category_id, int pageNumber, int pageSize) {
		return Article.dao.paginate(pageNumber, pageSize, SqlKit.sql("blog.selectArticlesByCategoryIdSelect"), SqlKit.sql("blog.selectArticlesByCategoryIdSelect"), category_id);
	}

	public Page<Article> selectAllArticles(int pageNumber, int pageSize) {
		return Article.dao.paginate(pageNumber, pageSize, SqlKit.sql("blog.selectAllArticlesSelect"), SqlKit.sql("blog.selectAllArticlesSqlExceptSelect"));
	}

	public Article selectArticleByArticleId(int articleId) {
		return Article.dao.findFirst(SqlKit.sql("blog.selectArticleByArticleIdSql"), articleId);
	}

	public List<Article> selectPopularArticles(int limitNum) {
		return Article.dao.find(SqlKit.sql("blog.selectPopularArticlesSql"), limitNum);
	}

	public List<Article> selectRecentArticles(int limitNum) {
		return Article.dao.find(SqlKit.sql("blog.selectRecentArticlesSql"), limitNum);
	}

}