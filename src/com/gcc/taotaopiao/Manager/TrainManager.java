package com.gcc.taotaopiao.Manager;

import java.util.List;

import android.os.AsyncTask;
import com.gcc.taotaopiao.entity.A;
import com.gcc.taotaopiao.entity.Trains5;
import com.google.gson.Gson;

public class TrainManager {
	private static LoadTrainsListener listener;
	private static String startingstation;
	private static String terminalstation;
	private static String date;
	
	
	public TrainManager(String startingstation,String terminalstation,String date) {
		super();
		this.startingstation=startingstation;
		this.terminalstation=terminalstation;
		this.date=date;
		// TODO Auto-generated constructor stub
	}

	public static List<A> getAllTrain(String startingstation,String terminalstation,String date){
		Gson gson=new Gson();
		String json=new String();
		Trains5 trains5=gson.fromJson(json, Trains5.class);
		List<A> trains=trains5.getResult().getList();
		return trains;
	}
	 private static class MyAsyncTask extends AsyncTask<Void,Void,List<A>>{
	        @Override
	        protected List<A> doInBackground(Void... params) {
	            List<A> trains=getAllTrain(startingstation,terminalstation,date);
	            return trains;
	        }

	        @Override
	        protected void onPostExecute(List<A> trains) {
	           //��������UI
	            // �Ѽ��������Ϣ֪ͨ��ȥ
	            listener.onTrainsLoadEnd(trains);
	        }
	    }
	    //���ýӿڻص�����ʵ��UI���ݵĸ��´���
	    public interface LoadTrainsListener{
	        public void onTrainsLoadEnd(List<A> trains);
	    }

	    public void asyncLoadTrains(LoadTrainsListener trainListener){
	        listener=trainListener;
	        MyAsyncTask asyncTask=new MyAsyncTask();
	        asyncTask.execute();
	    }
	
	

}
