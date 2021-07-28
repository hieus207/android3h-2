package com.example.mototest.View.Test;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mototest.Model.Question;
import com.example.mototest.R;

import java.util.ArrayList;

public class CheckAnserAdapter extends BaseAdapter {
    ArrayList<String> arrayListanser;
    ArrayList<String> arrayListDadung;
    LayoutInflater inflater;

    public CheckAnserAdapter(ArrayList<String> arrayListanser, ArrayList<String> arrayListDadung, Context context) {
        this.arrayListanser = arrayListanser;
        this.arrayListDadung = arrayListDadung;
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayListanser.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListanser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String data=(String) getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.grid_items_question,null);
            viewHolder.ll_da=(LinearLayout) convertView.findViewById(R.id.ll_da);
            viewHolder.tvnum=(TextView) convertView.findViewById(R.id.tv_ansernum);
            viewHolder.tvanser=(TextView) convertView.findViewById(R.id.tv_anser_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        int i=position+1;
        viewHolder.tvnum.setText("Câu "+i+": ");
        viewHolder.tvanser.setText(data);
        if(this.arrayListDadung.size()>position){
            if (arrayListDadung.get(position).equals(data))
                viewHolder.ll_da.setBackgroundColor(Color.parseColor("#81C784"));
            else
                if(data!="")
                viewHolder.ll_da.setBackgroundColor(Color.parseColor("#FFF86E6E"));
//            Log.e("da chay 1 lan","size:"+arrayListDadung.size()+"dap an thu :"+Integer.toString(position)+"KQ:"+arrayListDadung.get(position));
        }
        return convertView;
    }
    private static class ViewHolder{
        TextView tvnum, tvanser;
        LinearLayout ll_da;

    }

}
