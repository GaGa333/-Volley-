package com.gcc.taotaopiao.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter{

	   protected LayoutInflater layoutInflater=null;//布局解析器
	   protected Context context=null;//上下文对象
	    //适配数据的集合
	   private List<T> datas=new ArrayList<T>();

	    public MyBaseAdapter(Context context){
	        this.context=context;
	        layoutInflater=LayoutInflater.from(context);
	    }

	    //添加一批数据

	    /**
	     *
	     * @param list 要添加的集合数据
	     * @param isClear 是否清空集合中原有的数据
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
	    //删除所有的数据
	    public void removeDatas(){
	        datas.clear();
	        notifyDataSetChanged();
	    }
	    //删除指定的数据
	    public void removeDatas(T t){
	        datas.remove(t);
	        notifyDataSetChanged();
	    }

	    //获得适配器中的所有的数据
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

