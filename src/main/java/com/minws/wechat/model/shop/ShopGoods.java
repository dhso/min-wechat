package com.minws.wechat.model.shop;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
@TableBind(tableName = "shop_goods", pkName = "goods_id")
public class ShopGoods extends Model<ShopGoods> {
	public static final ShopGoods dao = new ShopGoods();

	/**
	 * 获取所有商品
	 * 
	 * @return
	 */
	public List<Record> getAllGoods() {
		return Db.find("select * from shop_goods");
	}

	/**
	 * 获取商品详细信息
	 * 
	 * @param id
	 * @return
	 */
	public Record fetchGoodsDetail(int goodsId) {
		return Db.findFirst("select name,image,detail,price,old_price from shop_goods where goods_id = ?", goodsId);
	}
}
