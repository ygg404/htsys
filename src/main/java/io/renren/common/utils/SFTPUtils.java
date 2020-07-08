package io.renren.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * sftp工具类
 * 
 * @author xxx
 * @date 2014-6-17
 * @time 下午1:39:44
 * @version 1.0
 */
public class SFTPUtils {

	private String host;// 服务器连接ip
	private String username;// 用户名
	private String password;// 密码
	private int port = 22;// 端口号
	private ChannelSftp sftp = null;
	private Session sshSession = null;

	public SFTPUtils() {
	}

	public SFTPUtils(String host, int port, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public SFTPUtils(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	/**
	 * 通过SFTP连接服务器
	 */
	public void connect() {
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);

			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();

			Channel channel = sshSession.openChannel("sftp");
			channel.connect();

			sftp = (ChannelSftp) channel;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();

			}
		}
		if (this.sshSession != null) {
			if (this.sshSession.isConnected()) {
				this.sshSession.disconnect();

			}
		}
	}

	/**
	 * 批量下载文件
	 * 
	 * @param remotPath     ：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
	 * @param localPath     ：本地保存目录(以路径符号结束,D:\Duansha\sftp\)
	 * @param fileFormat    ：下载文件格式(以特定字符开头,为空不做检验)
	 * @param fileEndFormat ：下载文件格式(文件格式)
	 * @param del           ：下载后是否删除sftp文件
	 * @return
	 */
	public List<String> batchDownLoadFile(String remotePath, String localPath, String fileFormat, String fileEndFormat,
			boolean del) {
		List<String> filenames = new ArrayList<String>();
		try {
			connect();
			// sftp.cd(remotePath);
			Vector v = listFiles(remotePath);

			if (v.size() > 0) {
				// System.out.println("本次处理文件个数不为零,开始下载...fileSize=" + v.size());
				Iterator it = v.iterator();
				while (it.hasNext()) {
					LsEntry entry = (LsEntry) it.next();
					String filename = entry.getFilename();
					SftpATTRS attrs = entry.getAttrs();
					if (!attrs.isDir()) {
						boolean flag = false;
						String localFileName = localPath + filename;
						fileFormat = fileFormat == null ? "" : fileFormat.trim();
						fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
						// 三种情况
						if (fileFormat.length() > 0 && fileEndFormat.length() > 0) {
							if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if (flag) {
									filenames.add(localFileName);
									if (flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						} else if (fileFormat.length() > 0 && "".equals(fileEndFormat)) {
							if (filename.startsWith(fileFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if (flag) {
									filenames.add(localFileName);
									if (flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						} else if (fileEndFormat.length() > 0 && "".equals(fileFormat)) {
							if (filename.endsWith(fileEndFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if (flag) {
									filenames.add(localFileName);
									if (flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						} else {
							flag = downloadFile(remotePath, filename, localPath, filename);
							if (flag) {
								filenames.add(localFileName);
								if (flag && del) {
									deleteSFTP(remotePath, filename);
								}
							}
						}
					}
				}
			}

		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return filenames;
	}

	/**
	 * 下载单个文件
	 * 
	 * @param remotPath      ：远程下载目录(以路径符号结束)
	 * @param remoteFileName ：下载文件名
	 * @param localPath      ：本地保存目录(以路径符号结束)
	 * @param localFileName  ：保存文件名
	 * @return
	 */
	public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
		FileOutputStream fieloutput = null;
		try {
			// sftp.cd(remotePath);
			File file = new File(localPath + localFileName);
			// mkdirs(localPath + localFileName);
			fieloutput = new FileOutputStream(file);
			sftp.get(remotePath + "/" + remoteFileName, fieloutput);
			System.out.println("SFtp文件：" + remoteFileName + "下载成功");
			return true;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (null != fieloutput) {
				try {
					fieloutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean downloadFile(String s_remoteFilePath, String s_localFilePath) {
		FileOutputStream fileOutput = null;
		try {
			File file = new File(s_localFilePath);
			fileOutput = new FileOutputStream(file);
			sftp.get(s_remoteFilePath, fileOutput);
			System.out.println("------SFtp文件：" + s_remoteFilePath + "下载成功到：" + s_localFilePath + "--------");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != fileOutput) {
				try {
					fileOutput.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 上传单个文件
	 * 
	 * @param remotePath     ：远程保存目录
	 * @param remoteFileName ：保存文件名
	 * @param localPath      ：本地上传目录(以路径符号结束)
	 * @param localFileName  ：上传的文件名
	 * @return
	 */
	public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
		FileInputStream in = null;
		try {
			File file = new File(localPath + File.separator + localFileName);
			in = new FileInputStream(file);
			sftp.put(in, remoteFileName);
			System.out.println("SFtp文件：" + remoteFileName + "上传成功");
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("SFtp文件：" + remoteFileName + "上传失败");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SftpException e) {
			System.out.println("SFtp文件：" + remoteFileName + "上传失败");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 批量上传文件
	 * 
	 * @param remotePath ：远程保存目录
	 * @param localPath  ：本地上传目录(以路径符号结束)
	 * @param del        ：上传后是否删除本地文件
	 * @return
	 */
	public boolean bacthUploadFile(String remotePath, String localPath, boolean del) {
		try {
			File file = new File(localPath);
			File[] files = file.listFiles();
			createDir(remotePath);
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile() && files[i].getName().indexOf("bak") == -1
						&& files[i].getName().indexOf("V3") == -1 && files[i].getName().indexOf(".mp3") == -1) {
					if (this.uploadFile(remotePath, files[i].getName(), localPath, files[i].getName()) && del) {
						deleteFile(localPath + files[i].getName());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			this.disconnect();
			this.connect();
		}
		return false;

	}

	/**
	 * 删除本地文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return false;
		}

		if (!file.isFile()) {
			return false;
		}
		boolean rs = file.delete();
		return rs;
	}

	/**
	 * 创建目录
	 * 
	 * @param createpath
	 * @return
	 */
	public boolean createDir(String createpath) {
		try {
			if (isDirExist(createpath)) {
				this.sftp.cd(createpath);
				return true;
			}

			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString())) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}

			}
			this.sftp.cd(createpath);
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @param directory
	 * @return
	 */
	public boolean isDirExist(String directory) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			isDirExistFlag = false;
		}
		return isDirExistFlag;
	}

	/**
	 * 删除stfp文件
	 * 
	 * @param directory  ：要删除文件所在目录
	 * @param deleteFile ：要删除的文件
	 * @param sftp
	 */
	public void deleteSFTP(String directory, String deleteFile) {
		try {
			// sftp.cd(directory);

			sftp.rm(directory + deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除stfp文件
	 * 
	 * @param directory  ：要删除文件夹
	 * @param deleteFile ：要删除的文件
	 * @param sftp
	 */
	public void deleteSFTPDir(String directory) {
		try {
			// 删除远程文件夹
			sftp.rmdir(directory);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 如果目录不存在就创建目录
	 * 
	 * @param path
	 */
	public void mkdirs(String path) {
		File f = new File(path);

		String fs = f.getParent();

		f = new File(fs);

		if (!f.exists()) {
			f.mkdirs();
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory ：要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ChannelSftp getSftp() {
		return sftp;
	}

	public void setSftp(ChannelSftp sftp) {
		this.sftp = sftp;
	}

	/** 测试 */
	public static void main(String[] args) {
		SFTPUtils sftp = new SFTPUtils("192.168.4.45", "hcicloud", "ipcc9918");
		sftp.connect();
		try {
			sftp.batchDownLoadFile("/home/hcicloud/liyue/maryunXml/1002/", "D:\\xml\\1002\\", "", "", false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// SFTPUtils sftp = null;
		// // 本地存放地址
		// String localPath = "D:/upl/";
		// // Sftp下载路径
		// String remotePath = "/home/hcicloud/liyue/voice";
		// List<String> filePathList = new ArrayList<String>();
		// try
		// {
		// sftp = new SFTPUtils("192.168.4.45", "hcicloud", "ipcc9918");
		// sftp.connect();
		// // 下载
		// //sftp.batchDownLoadFile(sftpPath, localPath, "ASSESS", ".wav",
		// true);
		// // sftp.bacthUploadFile(sftpPath, localPath, false);
		//
		//
		// sftp.batchDownLoadFile(remotePath, localPath, "", "", false);
		// //sftp.bacthUploadFile(remotePath, localPath, del);
		// //sftp.bacthUploadFile(remotePath, localPath, false);
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// finally
		// {
		// sftp.disconnect();
		// }

	}
}
