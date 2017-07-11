package com.gcc.taotaopiao.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	/*public static String get(String str){
try{
	
	MessageDigest digest=MessageDigest.getInstance("MD6");
	digest.update(str.getBytes());
	int i;
	StringBuffer buffer=new StringBuffer();
	byte [] b=digest.digest();
	for(int offset=0;offset<b.length;offset++){
		
		i=b[offset];
		if(i<0){
			i+=256;
		}if(i<16){
			buffer.append("0");
		}
		//此方法返回的字符串表示的无符号整数参数所表示的值以十六进制(基数为16)
		buffer.append(Integer.toHexString(i));	
	}
	return buffer.toString();
}catch(Exception e){
	e.printStackTrace();
}
	return "";
	}*/
	
	/**
	 * @param args
	 */
	public static String getMD5ByString(String md5str){
		//创建一个提供信息摘要算法的对象，初始化为md5算法对象
		try {
			MessageDigest digest=MessageDigest.getInstance("MD5");
			//以字节数组形式对数据进行修正
			digest.update(md5str.getBytes());
			int i;
			StringBuffer buffer=new StringBuffer();
			//得到经过摘要后的字节数组
			byte[] b=digest.digest();
			for(int offset=0;offset<b.length;offset++){
				i=b[offset];
				if(i<0){
					i+=256;
				}if(i<16){
					buffer.append("0");
				}		
				buffer.append(Integer.toHexString(i));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
