package com.gcc.taotaopiao.Manager;

import java.util.ArrayList;
import java.util.List;

import com.gcc.taotaopiao.entity.RecentTrip;

public class RecentTripManager {
	
	public  static List<RecentTrip> getAllRecentTrip(){
		List<RecentTrip> recentTrips=new ArrayList<RecentTrip>();
		
		
		
		return recentTrips;
	}
	
	
	 /* public static List<Contact> getAllContacts(
	            Context context){
	        List<Contact> contacts=new ArrayList<Contact>();
	        //获得一个内容解析器
	        ContentResolver resolver=context.getContentResolver();
	        //content://com.andorid.contacts/contacts
	        Uri contactUri= ContactsContract.Contacts.CONTENT_URI;
	        String[] projection=new String[]{
	                ContactsContract.Contacts._ID,
	                ContactsContract.Contacts.PHOTO_ID};
	        //获得查询的游标集
	        Cursor contactCursor=resolver.
	                query(contactUri,projection,
	                        null,null,null);
	        //对游标进行遍历控制联系人
	        while(contactCursor.moveToNext()){
	            int _id=contactCursor.getInt(0);
	            int photoId=contactCursor.getInt(1);

	            Contact contact=new Contact();
	            contact.set_id(_id);
	            contact.setPhotoId(photoId);

	            //在内层循环中实现针对每一个联系人的多种类型的数据的查询
	            Uri dataUri=ContactsContract.Data.CONTENT_URI;
	            String[] dataProject=new String[]{
	                    ContactsContract.
	                            Data.MIMETYPE,
	                    ContactsContract.
	                            Data.DATA1};

	            String where=ContactsContract.
	                    Data.RAW_CONTACT_ID+"=?";
	            String args[]=
	                    new String[]{String.valueOf(_id)};

	            Cursor dataCursor=resolver.
	                    query(dataUri,dataProject,
	                            where,args,null);

	            while (dataCursor.moveToNext()){
	                String mimeType=dataCursor.getString(0);
	                String data1=dataCursor.getString(1);
	                if(MIMETYPE_NAME.equals(mimeType)){
	                    //获得的数据是姓名
	                    contact.setName(data1);
	                }else if(MIMETYPE_PHONE.equals(mimeType)){
	                    //获得的数据是电话号码
	                    contact.setPhone(data1);
	                }else  if(MIMETYPE_ADDRESS.equals(mimeType)){
	                    contact.setAddress(data1);
	                }else if(MIMETYPE_EMAIL.equals(mimeType)){
	                    contact.setEmail(data1);
	                }
	            }
	            dataCursor.close();

	            contacts.add(contact);
	        }
	        contactCursor.close();
	        return contacts;
	    }
*/
}
