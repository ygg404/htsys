/**
 * FTL模板处理类抽象类
 */
package io.renren.common.ftlreport;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author hawkbird date 2018-10-
 *
 */
public abstract class FtlReportProcessAbstract {
	//版本2.3.28
	private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);		
	private Template template;
	private String ftlname;
	private BufferedInputStream fis;
	protected Object dataModel;
	
	public FtlReportProcessAbstract(BufferedInputStream fis) throws Exception{
		this.fis = fis;
	}
	//合并模板和并返回模板(byte[])
	public byte[] process() {
		File f = new File(ftlname);
		if(!f.exists())
			return null;
		//
		if(dataModel==null)
			return null;
		//合并
		try {
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
			File fout = new File(ftlname+uuid);
			FileOutputStream out = new FileOutputStream(fout);
			Writer writer = new OutputStreamWriter(out);
			template = cfg.getTemplate(ftlname);
			template.process(dataModel, writer);
			out.flush();			
			out.close();
			FileInputStream fis = new FileInputStream(fout);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			fout.delete();
			return data;
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void proccess(OutputStream os) {
		//
		if(dataModel==null)
			return;
		if(os==null)
			return;
		//合并
		try {
			Writer writer = new OutputStreamWriter(os);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			String tempname = UUID.randomUUID().toString().trim().replaceAll("-", "");
			StringTemplateLoader templateLoader = new StringTemplateLoader();
			templateLoader.putTemplate(tempname,new String(data,"utf-8"));
			cfg.setTemplateLoader(templateLoader);
			template = cfg.getTemplate(tempname);
			template.process(dataModel, writer);
			os.flush();			
			return;
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}	
	
}
