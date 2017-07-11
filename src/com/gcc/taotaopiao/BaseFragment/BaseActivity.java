package com.gcc.taotaopiao.BaseFragment;

import com.gcc.taotaopiao.R;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseActivity extends Activity{
	
	 public RelativeLayout actionbar;//标题布局
	    public View contentView=null;//内容视图
	    /**
	     *
	     * @param leftId 左边图片资源Id
	     * @param title 中间的文本内容
	     * @param rightId 右边图片资源Id
	     */
	    public void initialActionbar(
	            int leftId,
	            String title,
	            int rightId){
	        if(actionbar==null){
	            return;
	        }
	        ImageView imageView_Left= (ImageView)
	                actionbar.findViewById(
	                        R.id.imageView_Actionbar_Left);
	        TextView textView_Title=
	                (TextView) actionbar.findViewById(
	                        R.id.textView_Actionbar_Title);
	        ImageView imageView_Right= (ImageView)
	                actionbar.findViewById(
	                        R.id.imageView_Actionbar_Right);
	        if(leftId==-1){
	            //资源id如果为-1说明该控件不显示
	            imageView_Left.setVisibility(View.INVISIBLE);
	        }else{
	            //说明要显示一个图片
	            imageView_Left.setVisibility(View.VISIBLE);
	            imageView_Left.setImageResource(leftId);
	        }
	        if(TextUtils.isEmpty(title)){
	            textView_Title.setVisibility(View.INVISIBLE);
	        }else{
	            textView_Title.setVisibility(View.VISIBLE);
	            textView_Title.setText(title);
	        }
	        if(rightId==-1){
	            imageView_Right.setVisibility(View.INVISIBLE);
	        }else {
	            imageView_Right.setVisibility(View.VISIBLE);
	            imageView_Right.setImageResource(rightId);
	        }
	    }
	    public  abstract void initialUI();
	
}
