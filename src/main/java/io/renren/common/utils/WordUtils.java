package io.renren.common.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class WordUtils {
    /**
     * 转换docx 并返回html文本
     * @param imagePath
     * @throws Exception
     */
    public static String docxToHtml(MultipartFile file,String imagePath ) throws Exception {
        try {
            File filePath = new File(imagePath);
            if(!filePath.exists()){//如果文件夹不存在
                filePath.mkdir();//创建文件夹
            }
            XWPFDocument xwpfDocument = new XWPFDocument(file.getInputStream());
            XHTMLOptions options = XHTMLOptions.create();
            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            // ) 将 XWPFDocument转换成XHTML
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XHTMLConverter.getInstance().convert(xwpfDocument, out, options);
            String content = out.toString();
            out.close();
            return content;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 转换doc 并返回html文本
     * @param imagePath
     * @throws Exception
     */
    public static String docToHtml(MultipartFile file,String imagePath,String imgUrl) throws Exception {
        try {
            File filePath = new File(imagePath);
            if(!filePath.exists()){//如果文件夹不存在
                filePath.mkdir();//创建文件夹
            }
            HWPFDocument wordDocument = new HWPFDocument(file.getInputStream());
            Document htmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(htmlDocument);
            //保存图片，并返回图片的相对路径
            wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
                try (FileOutputStream out = new FileOutputStream(imagePath +"/" + name)) {
                    out.write(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return imgUrl + name;
            });
            wordToHtmlConverter.processDocument(wordDocument);

            // 也可以使用字符数组流获取解析的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(baos);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer serializer = factory.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);

            // 也可以使用字符数组流获取解析的内容
            String content = new String(baos.toByteArray());
            baos.close();
            return content;
        } catch (Exception e) {
            throw e;
        }
    }
}
