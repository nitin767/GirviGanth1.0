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

public class CustomerAdapter  extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context context;
    //for re-fresh activity
    Activity activity;
    private ArrayList customer_id, customer_name, customer_father, customer_village, customer_phone;
    CustomerAdapter(Activity activity, Context context,
                         ArrayList customer_id,
                         ArrayList customer_name,
                         ArrayList customer_father,
                         ArrayList customer_village,
                         ArrayList customer_phone) {
        this.activity = activity; //for re-fresh activity
        this.context = context;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_father = customer_father;
        this.customer_village = customer_village;
        this.customer_phone = customer_phone;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.customer_id_txt.setText(String.valueOf(customer_id.get(position)));
        holder.customer_name_txt.setText(String.valueOf(customer_name.get(position)));
        holder.customer_father_txt.setText(String.valueOf(customer_father.get(position)));
        holder.customer_village_txt.setText(String.valueOf(customer_village.get(position)));
        holder.customer_phone_txt.setText(String.valueOf(customer_phone.get(position)));
        //for UpdateActivity
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateCustomer.class);
                intent.putExtra("id", String.valueOf(customer_id.get(position)));
                intent.putExtra("customer", String.valueOf(customer_name.get(position)));
                intent.putExtra("father", String.valueOf(customer_father.get(position)));
                intent.putExtra("village", String.valueOf(customer_village.get(position)));
                intent.putExtra("phone", String.valueOf(customer_phone.get(position)));
                //for re-fresh activity
                // context.startActivity(intent);
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customer_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView customer_id_txt, customer_name_txt, customer_father_txt, customer_village_txt, customer_phone_txt;
        //customer_row.xml LinearLayout ID onClick to updateActivity
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_id_txt = itemView.findViewById(R.id.customer_id_text);
            customer_name_txt = itemView.findViewById(R.id.customer_name_text);
            customer_father_txt = itemView.findViewById(R.id.customer_father_text);
            customer_village_txt = itemView.findViewById(R.id.customer_village_text);
            customer_phone_txt = itemView.findViewById(R.id.customer_phone_text);
            //Find the ID of LinearLayout customer_row.xml for UpdateActivity
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
