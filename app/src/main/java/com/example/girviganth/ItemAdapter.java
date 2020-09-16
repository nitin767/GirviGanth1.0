package com.example.girviganth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private Context context;
    //for re-fresh activity
    Activity activity;
    String id;
    private ArrayList item_id, item_name, metal_name, actual_weight, wastage_weight, net_weight, purity, metal_rate, today_value;
    ItemAdapter(Activity activity, Context context,
                String customer_id,
                ArrayList item_id,
                ArrayList item_name,
                ArrayList metal_name,
                ArrayList actual_weight,
                ArrayList wastage_weight,
                ArrayList net_weight,
                ArrayList purity,
                ArrayList metal_rate,
                ArrayList today_value) {
        this.activity = activity; //for re-fresh activity
        this.context = context;
        this.id = customer_id;
        this.item_id = item_id;
        this.item_name = item_name;
        this.metal_name = metal_name;
        this.actual_weight = actual_weight;
        this.wastage_weight = wastage_weight;
        this.net_weight = net_weight;
        this.purity = purity;
        this.metal_rate = metal_rate;
        this.today_value = today_value;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.item_name_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_metal_txt.setText(String.valueOf(metal_name.get(position)));
        holder.actual_weight_txt.setText(String.valueOf(actual_weight.get(position)));
        holder.wastage_weight_txt.setText(String.valueOf(wastage_weight.get(position)));
        holder.net_weight_txt.setText(String.valueOf(net_weight.get(position)));
        holder.item_purity_txt.setText(String.valueOf(purity.get(position)));
        holder.metal_rate_txt.setText(String.valueOf(metal_rate.get(position)));
        holder.today_value_txt.setText(String.valueOf(today_value.get(position)));

        //for UpdateItem
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(context, UpdateItem.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("customer", String.valueOf(customer_name.get(position)));
                intent.putExtra("father", String.valueOf(customer_father.get(position)));
                intent.putExtra("village", String.valueOf(customer_village.get(position)));
                intent.putExtra("phone", String.valueOf(customer_phone.get(position)));
                //for re-fresh activity
                // context.startActivity(intent);
                activity.startActivityForResult(intent, 1); Commented by Shivi*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_name_txt, item_metal_txt, actual_weight_txt, wastage_weight_txt, net_weight_txt, item_purity_txt, metal_rate_txt, today_value_txt;
        //item_row.xml LinearLayout ID onClick to updateItem
        LinearLayout itemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_txt = itemView.findViewById(R.id.item_name_text);
            item_metal_txt = itemView.findViewById(R.id.item_metal_text);
            actual_weight_txt = itemView.findViewById(R.id.actual_weight_text);
            wastage_weight_txt = itemView.findViewById(R.id.wastage_weight_text);
            net_weight_txt = itemView.findViewById(R.id.net_weight_text);
            item_purity_txt = itemView.findViewById(R.id.item_purity_text);
            metal_rate_txt = itemView.findViewById(R.id.metal_rate_text);
            today_value_txt = itemView.findViewById(R.id.today_value_text);
            //Find the ID of LinearLayout item_row.xml for UpdateItem
            itemLayout = itemView.findViewById(R.id.itemLayout);

        }
    }
}
