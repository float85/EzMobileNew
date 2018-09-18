package com.fpts.mobile.eztrading.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileInputAndOutputStream {
    public static void saveData(Context context, String data, String file) {
        try {
            FileOutputStream out = context.openFileOutput(file, context.MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
        } catch (Exception e) {
        }
    }

    public static String readData(Context context, String file) {
        try {
            FileInputStream in = context.openFileInput(file);
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            StringBuilder sb= new StringBuilder();
            String s= null;
            while((s= br.readLine())!= null)  {
                sb.append(s).append("\n");
            }
            if(sb.length()==0) {
                return "";
            }
            else   return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
