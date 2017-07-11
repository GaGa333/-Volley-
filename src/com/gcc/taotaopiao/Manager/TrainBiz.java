package com.gcc.taotaopiao.Manager;

import java.util.ArrayList;
import java.util.List;

import com.gcc.taotaopiao.entity.A;
import com.gcc.taotaopiao.entity.TrainType_DEntity;
import com.gcc.taotaopiao.entity.TrainType_GEntity;
import com.gcc.taotaopiao.entity.TrainType_KTZEntity;

import android.util.Log;

public class TrainBiz {
	public static List<A> getTrainsByNumberTypeSelected(TrainType_GEntity type_GEntity,TrainType_DEntity type_DEntity,
			TrainType_KTZEntity type_KTZEntity,boolean LisChecked,List<A> trains){
		List<A> trains5=new ArrayList<A>();
		boolean GisChecked=type_GEntity.isChecked;
		boolean Gsw_isChecked=type_GEntity.sw_checked;
		boolean Gyd_isChecked=type_GEntity.yd_checked;
		boolean Ged_isChecked=type_GEntity.ed_checked;
		boolean DisChecked=type_DEntity.isChecked;
		boolean Dsw_isChecked=type_DEntity.sw_checked;
		boolean Dyd_isChecked=type_DEntity.yd_checked;
		boolean Ded_isChecked=type_DEntity.ed_checked;
		boolean Dwz_isChecked=type_DEntity.wz_checked;
		boolean KTZisChecked=type_KTZEntity.isChecked;
		boolean KTZrw_isChecked=type_KTZEntity.rw_checked;
		boolean KTZyw_isChecked=type_KTZEntity.yw_checked;
		boolean KTZyz_isChecked=type_KTZEntity.yz_checked;
		boolean KTZwz_isChecked=type_KTZEntity.wz_checked;
		if(!GisChecked&&!DisChecked&&!KTZisChecked&&!LisChecked){
			Log.i("TAG", "!GisChecked&&!DisChecked&&!KTZisChecked&&!LisChecked");
			trains5=trains;
			Log.i("TAG", trains5.size()+"!GisChecked&&!DisChecked&&!KTZisChecked&&!LisChecked");
		}else{
		for(A a:trains){
			String tNumber=a.getTrain_type();
			if(tNumber.equals("G")||tNumber.equals("C")){
				if(GisChecked){
					Log.i("TAG", "GisChecked");
					//int sw_left=Integer.parseInt(a.getSwz_num());
					String sw_left=a.getSwz_num();
					//int yd_left=Integer.parseInt(a.getYdz_num());
					String yd_left=a.getYdz_num();
					//int ed_left=Integer.parseInt(a.getEdz_num());
					String ed_left=a.getEdz_num();
					Log.i("TAG", Gsw_isChecked+"商务选择");
					Log.i("TAG", Gyd_isChecked+"一等选择");
					Log.i("TAG", Ged_isChecked+"二等选择");
					if(!Gsw_isChecked&&!Gyd_isChecked&&!Ged_isChecked){
						Log.i("TAG", "三种座位都没有选择");
						trains5.add(a);
					}else{
					if(Gsw_isChecked){
						Log.i("TAG", "商务被选择");
						if(!sw_left.equals("--")&&!sw_left.equals("0")){
							trains5.add(a);
							Log.i("TAG", trains5.size()+"商务选择后");
							continue;
						}
					}
					if(Gyd_isChecked){
						Log.i("TAG", "一等被选择");
						if(!yd_left.equals("--")&&!yd_left.equals("0")){
							trains5.add(a);
							Log.i("TAG", trains5.size()+"一等选择后");
							continue;
						}
					}
					if(Ged_isChecked){
						Log.i("TAG", "二等被选择");
						if(!ed_left.equals("--")&&!ed_left.equals("0")){
							trains5.add(a);
							Log.i("TAG", trains5.size()+"二等选择后");
							continue;
						}
					}
				}
				}else{
					continue;
				}
			}
			if(tNumber.equals("D")){
				if(DisChecked){
					//int sw_left=Integer.parseInt(a.getSwz_num());
					String sw_left=a.getSwz_num();
					//int yd_left=Integer.parseInt(a.getYdz_num());
					String yd_left=a.getYdz_num();
					//int ed_left=Integer.parseInt(a.getEdz_num());
					String ed_left=a.getEdz_num();
					//int wz_left=Integer.parseInt(a.getWz_num());
					String wz_left=a.getWz_num();
					if(!Dsw_isChecked&&!Dyd_isChecked&&!Ded_isChecked&&!Dwz_isChecked){
						trains5.add(a);
					}else{
					if(Dsw_isChecked){
						if(!sw_left.equals("--")&&!sw_left.equals("0")){
							trains5.add(a);
							continue;
						}
					}
					if(Dyd_isChecked){
						if(!yd_left.equals("--")&&!yd_left.equals("0")){
							trains5.add(a);
							continue;
						}
					}
					if(Ded_isChecked){
						if(!ed_left.equals("--")&&!ed_left.equals("0")){
							trains5.add(a);
							continue;
						}
					}
					if(Dwz_isChecked){
						if(!wz_left.equals("--")&&!wz_left.equals("0")){
							trains5.add(a);
							continue;
						}
					}
				}
				}else{
					continue;
				}
			}
			if(tNumber.equals("K")||tNumber.equals("T")||tNumber.equals("Z")){
				if(KTZisChecked){
					//int rw_left=Integer.parseInt(a.getRw_num());
					String rw_left=a.getRw_num();
					//int yw_left=Integer.parseInt(a.getYw_num());
					String yw_left=a.getYw_num();
					//int yz_left=Integer.parseInt(a.getYz_num());
					String yz_left=a.getYz_num();
					//int wz_left=Integer.parseInt(a.getWz_num());
					String wz_left=a.getWz_num();
					Log.i("TAG", "KTZrw_isChecked="+KTZrw_isChecked+"      "+"KTZyw_isChecked="+KTZyw_isChecked+"     "+"KTZyz_isChecked="+KTZyz_isChecked+"     "+"KTZwz_isChecked="+KTZwz_isChecked);
					if(!KTZrw_isChecked&&!KTZyw_isChecked&&!KTZyz_isChecked&&!KTZwz_isChecked){
						trains5.add(a);
					}else {
					if(KTZrw_isChecked){
						if(!rw_left.equals("--")&&!rw_left.equals("0")){
							trains5.add(a);
							continue;
						}else{
							
						}
					}
					if(KTZyw_isChecked){
						if(!yw_left.equals("--")&&!yw_left.equals("0")){
							trains5.add(a);
							continue;
						}
						else{
							
						}
					}
					if(KTZyz_isChecked){
						if(!yz_left.equals("--")&&!yz_left.equals("0")){
							trains5.add(a);
							continue;
						}
						else{
							
						}
					}
					if(KTZwz_isChecked){
						if(!wz_left.equals("--")&&!wz_left.equals("0")){
							trains5.add(a);
							continue;
						}
						else{
							
						}
					}	
				}
				}else{
					continue;
				}
			}
			if(!tNumber.equals("G")&&!tNumber.equals("C")&&!tNumber.equals("D")
					&&!tNumber.equals("K")&&!tNumber.equals("T")&&!tNumber.equals("Z")){
				if(LisChecked){
					trains5.add(a);
				}else{
					continue;
				}
			}
		}
		}
		/*}catch(Exception e){
			Log.i("TAG", "trainBiz出了异常");
			e.printStackTrace();
		}*/
		return trains5;
	}
	
	

}
