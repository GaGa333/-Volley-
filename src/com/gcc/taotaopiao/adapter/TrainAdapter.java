package com.gcc.taotaopiao.adapter;
import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.Utils.TrainDurationFormatUtils;
import com.gcc.taotaopiao.entity.A;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TrainAdapter extends MyBaseAdapter<A>{
	private final int color_xxx=0xFF46A626; 
	private final int color_xx=0xFFED7011; 
	private final int color_x=0xFFbfbfbf; 

	public TrainAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	ViewHolder viewHolder=null;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    if(convertView==null) {
	        convertView = layoutInflater.inflate(R.layout.train_list_item_layout, null);
	        viewHolder = new ViewHolder();
	        
	        viewHolder.textView_arrayDays=(TextView) convertView.findViewById(R.id.textView_arrayDays);
	        viewHolder.textView_originstation= (TextView) convertView.findViewById(R.id.textView_train_item_originstation);
	        viewHolder.textView_gooff = (TextView) convertView.findViewById(R.id.textView_train_item_gooff);
	        viewHolder.textView_terminusstation = (TextView) convertView.findViewById(R.id.textView_train_item_terminusstation);
	        viewHolder.textView_arrivaltime = (TextView) convertView.findViewById(R.id.textView_train_item_arrivaltime);
	        viewHolder.textView_trainnumber= (TextView) convertView.findViewById(R.id.textView_train_item_trainnumber);
	        viewHolder.textView_duration = (TextView) convertView.findViewById(R.id.textView_train_item_duration);
	        
	        viewHolder.textView_rw_sw = (TextView) convertView.findViewById(R.id.textView_train_item_rw_sw);
	        viewHolder.textView_yw_ydz = (TextView) convertView.findViewById(R.id.textView_train_item_yw_ydz);
	        viewHolder.textView_yz_edz = (TextView) convertView.findViewById(R.id.textView_train_item_yz_edz);
	        viewHolder.textView_wz = (TextView) convertView.findViewById(R.id.textView_train_item_wz);
	        
	        viewHolder.textView_rw_sw_ticketPrice = (TextView) convertView.findViewById(R.id.textView_train_item_rw_sw_ticketPrice);
	        viewHolder.textView_yw_ydz_ticketPrice = (TextView) convertView.findViewById(R.id.textView_train_item_yw_ydz_ticketPrice);
	        viewHolder.textView_yz_edz_ticketPrice = (TextView) convertView.findViewById(R.id.textView_train_item_yz_edz_ticketPrice);
	        viewHolder.textView_wz_ticketPrice = (TextView) convertView.findViewById(R.id.textView_train_item_wz_ticketPrice);
	        
	        viewHolder.textView_rw_sw_ticketsleft = (TextView) convertView.findViewById(R.id.textView_train_item_rw_sw_ticketsLeft);
	        viewHolder.textView_yw_ydz_ticketsleft = (TextView) convertView.findViewById(R.id.textView_train_item_yw_ydz_ticketsLeft);
	        viewHolder.textView_yz_edz_ticketsleft = (TextView) convertView.findViewById(R.id.textView_train_item_yz_edz_ticketsLeft);
	        viewHolder.textView_wz_ticketsleft = (TextView) convertView.findViewById(R.id.textView_train_item_wz_ticketsLeft);

	        convertView.setTag(viewHolder);
	    }else  {
	        viewHolder= (ViewHolder) convertView.getTag();
	    }
	    
	        A train=getItem(position);
	        int arrivalDays=Integer.parseInt(train.getArrive_days());
	        
	        viewHolder.textView_originstation.setText(train.getFrom_station_name());
	        viewHolder.textView_gooff.setText(train.getStart_time());
	        viewHolder.textView_terminusstation.setText(train.getTo_station_name());
	        viewHolder.textView_trainnumber.setText(train.getTrain_code());
	        
	        if(arrivalDays==1){
	        	viewHolder.textView_arrayDays.setText("次日");
	        viewHolder.textView_arrivaltime.setText(train.getArrive_time());
	        }else 
	        if(arrivalDays==2){
	        	viewHolder.textView_arrayDays.setText("后日");
		        viewHolder.textView_arrivaltime.setText(train.getArrive_time());
		        }else{
		        	viewHolder.textView_arrayDays.setText("");
		        	 viewHolder.textView_arrivaltime.setText(train.getArrive_time());
       	        }
	        
	        int run_time_minute=Integer.parseInt(train.getRun_time_minute());
	        viewHolder.textView_duration.setText(TrainDurationFormatUtils.trainDurationFormat(run_time_minute));
	        //当查询到的结果是G列车时：
	        if(train.getTrain_type().equals("G")){
	        viewHolder.textView_rw_sw.setText("商务座 ");
		    viewHolder.textView_yw_ydz.setText("一等座");
		    viewHolder.textView_yz_edz.setText("二等座");
		    viewHolder.textView_wz.setText("---");
	        viewHolder.textView_rw_sw_ticketPrice .setText(train.getSwz_price());
	        viewHolder.textView_yw_ydz_ticketPrice.setText(train.getYdz_price());
	        viewHolder.textView_yz_edz_ticketPrice.setText(train.getEdz_price());
	        viewHolder.textView_wz_ticketPrice.setText("---");
	        
	        viewHolder.textView_rw_sw.setBackgroundColor(Color.WHITE);
	        viewHolder.textView_yw_ydz.setBackgroundColor(Color.WHITE);
	        viewHolder.textView_wz.setBackgroundColor(Color.WHITE);
	        viewHolder.textView_yz_edz.setBackgroundColor(Color.WHITE);
	        
	        if(!train.getSwz_num().equals("--")){
	        int price1=Integer.parseInt(train.getSwz_num());
	        if(price1>10){
        		viewHolder.textView_rw_sw.setBackgroundColor(color_xxx);
        	}else if(price1>0){
        		viewHolder.textView_rw_sw.setBackgroundColor(color_xx);
        	}else{
        		viewHolder.textView_rw_sw.setBackgroundColor(color_x);
        	}
	        }
	        
	        if(!train.getYdz_num().equals("--")){
	            	int price2=Integer.parseInt(train.getYdz_num());
	            	if(price2>30){
	            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xxx);
	            	}else if(price2>0){
	            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xx);
	            	}else{
	            		viewHolder.textView_yw_ydz.setBackgroundColor(color_x);
	            	}
	        }
	            	
	            	
	        if(!train.getEdz_num().equals("--")){
	            	int price3=Integer.parseInt(train.getEdz_num());
	            	if(price3>30){
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_xxx);
	            	}else if(price3>0){
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_xx);
	            	}else{
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_x);
	            	}	
	        }
	            	
	        viewHolder.textView_rw_sw_ticketsleft.setText(train.getSwz_num()+" 张");
	        viewHolder.textView_yw_ydz_ticketsleft.setText(train.getYdz_num()+" 张");
	        viewHolder.textView_yz_edz_ticketsleft.setText(train.getEdz_num()+" 张");
	        viewHolder.textView_wz_ticketsleft.setText("---");
	        }
	        else if(train.getTrain_type().equals("C")||train.getTrain_type().equals("D")){
	        	viewHolder.textView_rw_sw.setText("商务座 ");
		        viewHolder.textView_yw_ydz.setText("一等座");
		        viewHolder.textView_yz_edz.setText("二等座");
		        viewHolder.textView_wz.setText("无座");
		        
		        viewHolder.textView_rw_sw.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_yw_ydz.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_wz.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_yz_edz.setBackgroundColor(Color.WHITE);
		        
		        if(!train.getSwz_num().equals("--")){
		        	int price1=Integer.parseInt(train.getSwz_num());
		        	
		        	if(price1>10){
	            		viewHolder.textView_rw_sw.setBackgroundColor(color_xxx);
	            	}else if(price1>0){
	            		viewHolder.textView_rw_sw.setBackgroundColor(color_xx);
	            	}else{
	            		viewHolder.textView_rw_sw.setBackgroundColor(color_x);
	            	}
		        }
		        
		        if(!train.getYdz_num().equals("--")){
		        
            	int price2=Integer.parseInt(train.getYdz_num());
            	
            	if(price2>30){
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xxx);
            	}else if(price2>0){
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_x);
            	}
            	
		        }
		        
		        
		        if(!train.getEdz_num().equals("--")){
		        	int price3=Integer.parseInt(train.getEdz_num());
		        	if(price3>30){
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_xxx);
	            	}else if(price3>0){
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_xx);
	            	}else{
	            		viewHolder.textView_yz_edz.setBackgroundColor(color_x);
	            	}
		        }
            	
		        if(!train.getWz_num().equals("--")){
            	int price4=Integer.parseInt(train.getWz_num());

            	if(price4>30){
            		viewHolder.textView_wz.setBackgroundColor(color_xxx);
            	}else if(price4>0){
            		viewHolder.textView_wz.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_wz.setBackgroundColor(color_x);
            	}
		        }
		        viewHolder.textView_rw_sw_ticketPrice .setText(train.getSwz_price());
		        viewHolder.textView_yw_ydz_ticketPrice.setText(train.getYdz_price());
		        viewHolder.textView_yz_edz_ticketPrice.setText(train.getEdz_price());
		        viewHolder.textView_wz_ticketPrice.setText(train.getEdz_price());
		        
		        viewHolder.textView_rw_sw_ticketsleft.setText(train.getSwz_num()+"张");
		        viewHolder.textView_yw_ydz_ticketsleft.setText(train.getYdz_num()+"张");
		        viewHolder.textView_yz_edz_ticketsleft.setText(train.getEdz_num()+"张");
		        viewHolder.textView_wz_ticketsleft.setText(train.getWz_num()+"张");
		        }else
	          {
		        viewHolder.textView_rw_sw .setText("软卧  ");
			    viewHolder.textView_yw_ydz.setText("硬卧 ");
			    viewHolder.textView_yz_edz.setText("硬座 ");
			    viewHolder.textView_wz.setText("无座 ");	
			    
			    viewHolder.textView_rw_sw.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_yw_ydz.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_wz.setBackgroundColor(Color.WHITE);
		        viewHolder.textView_yz_edz.setBackgroundColor(Color.WHITE);

		        if(!train.getRw_num().equals("--")){
			    int price1=Integer.parseInt(train.getRw_num());
			    if(price1>30){
            		viewHolder.textView_rw_sw.setBackgroundColor(color_xxx);
            	}else if(price1>0){
            		viewHolder.textView_rw_sw.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_rw_sw.setBackgroundColor(color_x);
            	}
		        }
		        if(!train.getYw_num().equals("--")){
            	int price2=Integer.parseInt(train.getYw_num());
            	if(price2>30){
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xxx);
            	}else if(price2>0){
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_yw_ydz.setBackgroundColor(color_x);
            	} 
		        }
            	
            	if(!train.getYz_num().equals("--")){
            	int price3=Integer.parseInt(train.getYz_num());
            	if(price3>30){
            		viewHolder.textView_yz_edz.setBackgroundColor(color_xxx);
            	}else if(price3>0){
            		viewHolder.textView_yz_edz.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_yz_edz.setBackgroundColor(color_x);
            	}
            	}
            	if(!train.getWz_num().equals("--")){
            	int price4=Integer.parseInt(train.getWz_num());
            	if(price4>30){
            		viewHolder.textView_wz.setBackgroundColor(color_xxx);
            	}else if(price4>0){
            		viewHolder.textView_wz.setBackgroundColor(color_xx);
            	}else{
            		viewHolder.textView_wz.setBackgroundColor(color_x);
            	}
            	}
	        	viewHolder.textView_rw_sw_ticketPrice .setText(train.getRw_price());
		        viewHolder.textView_yw_ydz_ticketPrice.setText(train.getYw_price());
		        viewHolder.textView_yz_edz_ticketPrice.setText(train.getYz_price());
		        viewHolder.textView_wz_ticketPrice.setText(train.getWz_price());
		        
		        viewHolder.textView_rw_sw_ticketsleft.setText(train.getRw_num()+"张");
		        viewHolder.textView_yw_ydz_ticketsleft.setText(train.getYw_num()+"张");
		        viewHolder.textView_yz_edz_ticketsleft.setText(train.getYz_num()+"张");
		        viewHolder.textView_wz_ticketsleft.setText(train.getWz_num()+"张");	
	        }
	        
	    return convertView;
	}

	class ViewHolder{ 
		TextView textView_originstation;
		TextView textView_gooff;
		TextView textView_terminusstation;
		TextView textView_arrivaltime;
		TextView textView_trainnumber;
		TextView textView_duration;
		
		TextView textView_rw_sw;
		TextView textView_yw_ydz;
		TextView textView_yz_edz;
		TextView textView_wz;
		
		TextView textView_rw_sw_ticketPrice;
		TextView textView_yw_ydz_ticketPrice;
		TextView textView_yz_edz_ticketPrice;
		TextView textView_wz_ticketPrice;
		
		TextView textView_rw_sw_ticketsleft;
		TextView textView_yw_ydz_ticketsleft;
		TextView textView_yz_edz_ticketsleft;
		TextView textView_wz_ticketsleft;
		
		TextView textView_arrayDays;
		
	}
}
