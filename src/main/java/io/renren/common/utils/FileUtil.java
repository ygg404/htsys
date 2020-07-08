package io.renren.common.utils;

import org.apache.http.util.TextUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Date;

public class FileUtil {

    public static void main(String[] args) {
        String dirName = "d:/yawei/topic/";// 创建目录
        FileUtil.createDir(dirName);
    }

    /**
     * 创建目录
     *
     * @param destDirName 目标目录名
     * @return 目录创建成功返回true，否则返回false
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建单个目录
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent     String
     * @return boolean
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }
    }

    /**
     * 设置下载文件的名称
     *
     * @param BASE_PATH      基础路径
     * @param s_filePathName
     * @param isFullPath     是否为全路径(yes:c:\abc\2016\def.jpg, no:\2016\jpg)
     * @return
     * @throws Exception
     */
    public static String setFilePath(String BASE_PATH, String s_filePathName, boolean isFullPath) throws Exception {
        //定义上传路径
        String s_filePathResult = "";
        String s_folderPathFormat = DateUtil.getMonth();
		String s_id = "(" + String.valueOf(new Date().getTime()) + ")";
        //获取文件后缀名
		String s_fileType = s_filePathName.substring(s_filePathName.lastIndexOf("."));
		//获取文件的名称
        String s_name = s_filePathName.substring( 0, s_filePathName.lastIndexOf("."));

        //组合最新文件名
        String s_newFileName = s_name + s_id + s_fileType;

        if (isFullPath) {
            //组合文件保存路径
            String s_dstPath = BASE_PATH ;
            //硬盘路径是否存在，如果不存在则创建
            File file_dstFile = new File(s_dstPath);
            if (!file_dstFile.exists()) {
                file_dstFile.mkdirs();
            }
            s_filePathResult = BASE_PATH + s_newFileName;
        } else {
            s_filePathResult = s_newFileName;
        }
        return s_filePathResult;
    }

    /**
     * 根据目录，获取文件名称及后缀名
     *
     * @param s_filePathName
     * @return
     * @throws Exception
     */
    public static String getFileName(String s_filePathName) throws Exception {
        File file_tempFile = new File(s_filePathName);
        return file_tempFile.getName();
    }

    /**
     * 根据目录，获取文件的路径
     *
     * @param s_filePathName
     * @return
     * @throws Exception
     */
    public static String getFilePath(String s_filePathName) throws Exception {
        File file_tempFile = new File(s_filePathName);
        return file_tempFile.getParent();
    }

    /**
     * 读取到字节数组0
     *
     * @param filePath //路径
     * @throws IOException
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * 读取到字节数组1
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filePath) throws IOException {

        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * 读取到字节数组2
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filePath) throws IOException {

        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray3(String filePath) throws IOException {

        FileChannel fc = null;
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filePath, "r");
            fc = rf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
                    fc.size()).load();
            //System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rf.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件复制
     *
     * @param s_source 文件源路径
     * @param s_dest   文件目标路径
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static void copyFiles(String s_source, String s_dest) throws Exception {
        try {
            //文件源路径
            FileInputStream fis_source = new FileInputStream(s_source);
            //文件目标路径
            FileOutputStream fos_dest = new FileOutputStream(s_dest);
            int i_readSourceLen = fis_source.read();
            while (i_readSourceLen != -1) {
                fos_dest.write(i_readSourceLen);
                i_readSourceLen = fis_source.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     * @param response
     * @param filePath
     * @return
     */
    public static void downloadFiles(HttpServletResponse response,String filePath) throws Exception {
        if(TextUtils.isEmpty(filePath)){
            throw new Exception("该文件不存在");
        } else {
            //设置文件路径
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            throw new Exception(e.getMessage());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            throw new Exception(e.getMessage());
                        }
                    }
                }
            }else{
                throw new Exception("文件不存在！");
            }
        }
    }
}