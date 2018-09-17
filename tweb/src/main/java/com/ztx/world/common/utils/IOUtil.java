package com.ztx.world.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 输入输出流工具类
 * @author zhoutianxiang 2018年9月13日16:24:28
 *
 */
public class IOUtil {
	
	/**
	 * 读取文件返回字节数组
	 * @param filePath 读取的文件路径
	 * @return
	 */
	public static byte[] readFile(String filePath){
		InputStream in = null;
		byte[] bytes = null;
		try {
			//根据path路径实例化一个输入流的对象
			File file = new File(filePath);
			in = new FileInputStream(file);
			//输入流中可以被读的剩下的bytes字节的估计值
			int len = in.available();
			bytes = new byte[len];
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	/**
	 * 读取文件返回字符串
	 * @param filePath 读取的文件路径
	 * @return
	 */
	public static String readFileToString(String filePath){
		InputStream in = null;
		String result = "";
		try {
			//根据path路径实例化一个输入流的对象
			File file = new File(filePath);
			in = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			//用于保存实际读取的字节数
			int hasRead = 0;
			//根据获取到的Byte数组新建一个字符串
			while((hasRead = in.read(bytes)) != -1){
				result = new String(bytes, 0, hasRead, "GBK");
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if(in != null){
				try{
					in.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 将内容写入文件
	 * @param filePath 写入文件路径
	 * @param content 写入内容
	 */
	public static void writeFile(String filePath, String content){
		OutputStream out = null;
		try {
			File file = new File(filePath);
			//如果没有文件则创建
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			byte[] bytes = content.getBytes();
			//把byte数组输出
			out.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 复制文件
	 * @param oldFile 源文件路径
	 * @param newFile 新文件路径
	 */
	public static void copyFile(String oldFile, String newFile){
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(new File(oldFile));
			//输入流中可以被读的剩下的bytes字节的估计值
			int len = in.available();
			byte[] bytes = new byte[len];
			in.read(bytes);
			File file = new File(newFile);
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			out.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
