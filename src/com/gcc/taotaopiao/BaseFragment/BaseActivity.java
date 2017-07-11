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
	
	 public RelativeLayout actionbar;//���Ⲽ��
	    public View contentView=null;//������ͼ
	    /**
	     *
	     * @param leftId ���ͼƬ��ԴId
	     * @param title �м���ı�����
	     * @param rightId �ұ�ͼƬ��ԴId
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
	            //��Դid���Ϊ-1˵���ÿؼ�����ʾ
	            imageView_Left.setVisibility(View.INVISIBLE);
	        }else{
	            //˵��Ҫ��ʾһ��ͼƬ
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
