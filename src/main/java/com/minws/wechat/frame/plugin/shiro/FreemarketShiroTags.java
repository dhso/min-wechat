package com.minws.wechat.frame.plugin.shiro;

import cn.dreampie.shiro.freemarker.ShiroTags;

public class FreemarketShiroTags extends ShiroTags{

	public FreemarketShiroTags(){
		super();
		super.remove("principal");
		put("principal", new FreemarketPrincipalTag());
	}
}
