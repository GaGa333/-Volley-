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
		//�˷������ص��ַ�����ʾ���޷���������������ʾ��ֵ��ʮ������(����Ϊ16)
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
		//����һ���ṩ��ϢժҪ�㷨�Ķ��󣬳�ʼ��Ϊmd5�㷨����
		try {
			MessageDigest digest=MessageDigest.getInstance("MD5");
			//���ֽ�������ʽ�����ݽ�������
			digest.update(md5str.getBytes());
			int i;
			StringBuffer buffer=new StringBuffer();
			//�õ�����ժҪ����ֽ�����
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
