package com.example.pojet_app_mobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recnomPrenom.setText(dataList.get(position).getDataName());
        holder.recTel.setText(dataList.get(position).getDatatel());
        holder.recCredit.setText(dataList.get(position).getDatacrdt());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Tel", dataList.get(holder.getAdapterPosition()).getDatatel());
                intent.putExtra("Nom et Prenom", dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Credit", dataList.get(holder.getAdapterPosition()).getDatacrdt());
                intent.putExtra("key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{


    TextView recnomPrenom, recTel, recCredit;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recCard = itemView.findViewById(R.id.recCard);
        recnomPrenom = itemView.findViewById(R.id.recnomPrenom);
        recTel = itemView.findViewById(R.id.rectel);
        recCredit = itemView.findViewById(R.id.recCredit);
    }
}
