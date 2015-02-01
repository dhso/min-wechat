package com.minws.wechat.frame.plugin.shiro;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.realm.jdbc.JdbcRealm;

public class MyJdbcRealm extends JdbcRealm {

	public MyJdbcRealm() {
		BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
	}

	private class EnumConverter extends AbstractConverter {
		@SuppressWarnings("rawtypes")
		@Override
		protected String convertToString(final Object value) throws Throwable {
			return ((Enum) value).name();
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected Object convertToType(final Class type, final Object value) throws Throwable {
			return Enum.valueOf(type, value.toString());
		}

		@SuppressWarnings("rawtypes")
		@Override
		protected Class getDefaultType() {
			return null;
		}

	}
}
