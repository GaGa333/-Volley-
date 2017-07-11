package com.gcc.taotaopiao.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter{

	   protected LayoutInflater layoutInflater=null;//���ֽ�����
	   protected Context context=null;//�����Ķ���
	    //�������ݵļ���
	   private List<T> datas=new ArrayList<T>();

	    public MyBaseAdapter(Context context){
	        this.context=context;
	        layoutInflater=LayoutInflater.from(context);
	    }

	    //���һ������

	    /**
	     *
	     * @param list Ҫ��ӵļ�������
	     * @param isClear �Ƿ���ռ�����ԭ�е�����
	     */
	    public void addDatas(List<T> list,
	                         boolean isClear){
	        if(isClear){
	            datas.clear();
	        }
	        if(list!=null){
	            datas.addAll(list);
	            notifyDataSetChanged();
	        }
	    }
	    //ɾ�����е�����
	    public void removeDatas(){
	        datas.clear();
	        notifyDataSetChanged();
	    }
	    //ɾ��ָ��������
	    public void removeDatas(T t){
	        datas.remove(t);
	        notifyDataSetChanged();
	    }

	    //����������е����е�����
	    public List<T> getAdapterDatas(){
	        return datas;
	    }

	    @Override
	    public int getCount() {
	        return datas.size();
	    }

	    @Override
	    public T getItem(int position) {
	        return datas.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public abstract View getView(int position, View convertView, ViewGroup parent);

	}

