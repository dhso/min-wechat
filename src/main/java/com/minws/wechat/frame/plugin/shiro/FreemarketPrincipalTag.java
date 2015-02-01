package com.minws.wechat.frame.plugin.shiro;

import java.io.IOException;
import java.util.Map;

import cn.dreampie.shiro.freemarker.SecureTag;
import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 * <p>
 * Tag used to print out the String value of a user's default principal, or a
 * specific principal as specified by the tag's attributes.
 * </p>
 * <p/>
 * <p>
 * If no attributes are specified, the tag prints out the <tt>toString()</tt>
 * value of the user's default principal. If the <tt>type</tt> attribute is
 * specified, the tag looks for a principal with the given type. If the
 * <tt>property</tt> attribute is specified, the tag prints the string value of
 * the specified property of the principal. If no principal is found or the user
 * is not authenticated, the tag displays nothing unless a <tt>defaultValue</tt>
 * is specified.
 * </p>
 * <p/>
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.PrincipalTag}
 * </p>
 * 
 * @since 0.2
 */
public class FreemarketPrincipalTag extends SecureTag {
	static final Logger log = Logger.getLogger("FreemarketPrincipalTag");

	@SuppressWarnings("unchecked")
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		String result = null;
		if (getSubject() != null) {
			// Get the principal to print out
			Object principal = getSubject().getPrincipal();
			// Get the string value of the principal
			if (null != principal) {
				result = principal.toString();
			} else {
				result = "访客";
			}
		} else {
			result = "访客";
		}

		// Print out the principal value if not null
		if (result != null) {
			try {
				env.getOut().write(result);
			} catch (IOException ex) {
				throw new TemplateException("Error writing [" + result + "] to Freemarker.", ex, env);
			}
		}
	}
}