package com.minws.wechat.model.SYS;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName = "articles", pkName = "id")
public class Article extends Model<Article> {
	public static final Article dao = new Article();

	public Page<Article> findArticlesByCategoryId(int category_id, int pageNumber, int pageSize) {
		return Article.dao.paginate(pageNumber, pageSize, "select *", "from articles where category_id = ? order by update_dt desc", category_id);
	}

	public Page<Article> findAllArticles(int pageNumber, int pageSize) {
		return Article.dao.paginate(pageNumber, pageSize, 
				"select art.id as articleId, art.title as articleTitle, art.content as articleContent, art.writer as articleWrite, art.update_dt as articleUpdateDt, cat.id as categoryId, cat.title as categoryTitle",
				"from articles as art left join category as cat on cat.id = art.category_id order by art.update_dt desc");
	}
}
