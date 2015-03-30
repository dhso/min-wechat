/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wechat.model.CMS;

import java.util.List;

import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName = "blog_categories", pkName = "id")
public class Category extends Model<Category> {
	public static final Category dao = new Category();

	public List<Category> selectAllCategories() {
		return Category.dao.find(SqlKit.sql("blog.selectAllCategoriesSql"));
	}
}