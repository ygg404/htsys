package io.renren.common.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
public class FreeMarkerUtil {
	
	
	
	/**
	   * @param fltFile  flt文件名
	    * @param templatePath flt文件路径   src/template
	    * @param datas 数据集合（与ftl中占位的变量名一致）
	    * @return
	   */
    public static String getFreeMarkerFile(String fltFile,
        Map<String, Object> datas) {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        try {
        	cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/static/ftl");
            //cfg.setDirectoryForTemplateLoading(new File(templatePath));
            Template template = cfg.getTemplate(fltFile);
            StringWriter out = new StringWriter();
            template.process(datas, out);
            out.flush();
            out.close();
            return out.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
