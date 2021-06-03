package com.example.tugas5.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas5.R;
import com.example.tugas5.entity.DataBelanja;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataBelanja> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataBelanja> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.belanja_online,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataBelanja item = list.get(position);
        holder.tvEcommerce.setText(item.getEcommerce());
        holder.tvBarang.setText(item.getBarang());
        holder.tvHarga.setText(item.getHarga());
        holder.id.setText(item.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvEcommerce, tvBarang, tvHarga, id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvEcommerce = itemView.findViewById(R.id.tv_ecommerce);
            tvBarang = itemView.findViewById(R.id.tv_harga_barang);
            tvHarga = itemView.findViewById(R.id.tv_harga_barang);
            cardView = itemView.findViewById(R.id.cv_belanja_online);
        }
    }
}
