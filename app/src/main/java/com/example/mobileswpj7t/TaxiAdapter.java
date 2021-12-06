package com.example.mobileswpj7t;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileswpj7t.Adapters.Board;
import com.example.mobileswpj7t.Adapters.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.CustomViewHolder> {

    private List<Taxi> datas;

    public TaxiAdapter(List<Taxi> datas) {
        this.datas = datas;
    }

    //연결되고 뷰홀더 생성
    @NonNull
    @Override
    public TaxiAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taxi, parent, false));
    }

    //실질적으로 매칭
    @Override
    public void onBindViewHolder(@NonNull TaxiAdapter.CustomViewHolder holder, int position) {
        Taxi data = datas.get(position);
        holder.tv_starting.setText(data.getStartP());
        holder.tv_ending.setText(data.getEndP());
        holder.tv_people.setText(data.getPeople());
    }

    @Override
    public int getItemCount() {
        return (datas.size());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_starting;
        private TextView tv_ending;
        private TextView tv_people;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_starting = (TextView) itemView.findViewById(R.id.item_taxi_start);
            tv_ending = (TextView) itemView.findViewById(R.id.item_taxi_end);
            tv_people = (TextView) itemView.findViewById(R.id.item_taxi_people);
        }
    }
}
