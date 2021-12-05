package com.example.mobileswpj7t;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.CustomViewHolder> {

    private ArrayList<Taxi> arrayList;

    public TaxiAdapter(ArrayList<Taxi> arrayList) {

        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TaxiAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taxi,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaxiAdapter.CustomViewHolder holder, int position) {
        holder.tv_starting.setText(arrayList.get(position).getStartP());
        holder.tv_ending.setText(arrayList.get(position).getEndP());
        holder.tv_people.setText(arrayList.get(position).getPeople()+"/4");

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_starting.getText().toString();
                Toast.makeText(view.getContext(), curName,Toast.LENGTH_SHORT).show();

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public void remove(int position){
        try{
            //arrayList
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_starting;
        protected TextView tv_ending;
        protected TextView tv_people;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_starting = (TextView) itemView.findViewById(R.id.item_taxi_start);
            this.tv_ending = (TextView) itemView.findViewById(R.id.item_taxi_end);
            this.tv_people = (TextView) itemView.findViewById(R.id.item_taxi_people);
        }
    }
}
