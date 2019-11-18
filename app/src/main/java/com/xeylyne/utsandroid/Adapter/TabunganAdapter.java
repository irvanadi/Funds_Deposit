package com.xeylyne.utsandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xeylyne.utsandroid.Class.Tabungan;
import com.xeylyne.utsandroid.R;

import java.util.ArrayList;

public class TabunganAdapter extends RecyclerView.Adapter<TabunganAdapter.ViewHolder> {

    Context context;
    ArrayList<Tabungan> results;

    public TabunganAdapter(Context context, ArrayList<Tabungan> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Tabungan result = results.get(position);
        holder.txtUraian.setText(result.getUraian());
        holder.txtNominal.setText(String.valueOf(result.getNominal()));
        holder.txtJenis.setText(result.getJenis());
        holder.txtTanggal.setText(result.getTanggal());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, results.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtJenis, txtTanggal, txtNominal, txtUraian;
        private Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJenis = itemView.findViewById(R.id.txtDebit);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtNominal = itemView.findViewById(R.id.txtInputRp);
            txtUraian = itemView.findViewById(R.id.txtInputUraian);
            btnDelete = itemView.findViewById(R.id.btnHapus);
        }
    }
}
