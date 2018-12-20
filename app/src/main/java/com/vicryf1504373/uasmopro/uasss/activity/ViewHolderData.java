package com.vicryf1504373.uasmopro.uasss.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vicryf1504373.uasmopro.uasss.R;

public class ViewHolderData  extends RecyclerView.ViewHolder {
    public TextView tv1,tv2;

    public ViewHolderData (View itemView) {
        super(itemView);
        tv1 = itemView.findViewById(R.id.tv1);
        tv2 = itemView.findViewById(R.id.tv2);
    }

}