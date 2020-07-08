/**
 * 读取YML配置中上传文件配置设置
 */
package io.renren.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hawkbird
 * date 2018-09-26
 */
@Component
@ConfigurationProperties(prefix="upload-config")
public class UploadConfig {
	private String root;
	public String getRoot() {
		return root==null?"":root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
}
