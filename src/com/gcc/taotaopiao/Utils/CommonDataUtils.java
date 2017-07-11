package com.gcc.taotaopiao.Utils;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.widget.DatePicker;

public class CommonDataUtils {
	
	public static void showAddDateDialog(Context context, String titleText,  
            final DateDialogCallBack callBack) {  
        final Calendar c = Calendar.getInstance();  
        DatePickerDialog dialog = new DatePickerDialog(context,  
                new DatePickerDialog.OnDateSetListener() {  
                    @Override  
                    public void onDateSet(DatePicker view, int year,  
                            int monthOfYear, int dayOfMonth) {  
                        c.set(year, monthOfYear, dayOfMonth);  
                        String date = (String) DateFormat.format("yyyy-MM-dd",  
                                c);  
                        callBack.getDate(date);  
                    }  
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),  
                c.get(Calendar.DAY_OF_MONTH));  
        dialog.setTitle(titleText);  
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",  
                new DialogInterface.OnClickListener() {  
                    @Override  
                    public void onClick(DialogInterface dialog, int which) {  
                    }  
                });  
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", dialog);  
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {  
            @Override  
            public boolean onKey(DialogInterface dialog, int keyCode,  
                    KeyEvent event) {  
                return true;  
            }  
        });  
        dialog.show();  
    }  
	
}

