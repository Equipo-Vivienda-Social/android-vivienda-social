package com.example.viviendasocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.domain.Dwelling;

import java.util.List;

public class DwellingAdapter extends RecyclerView.Adapter<DwellingAdapter.DwellingHolder> {

    private Context context;
    private List<Dwelling> dwellingList;

    public DwellingAdapter(Context context, List<Dwelling> dwellingList) {
        this.context = context;
        this.dwellingList = dwellingList;
    }

    @NonNull
    @Override
    public DwellingAdapter.DwellingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dwelling_item, parent, false);
        return new DwellingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DwellingAdapter.DwellingHolder holder, int position) {
        Dwelling dwelling = dwellingList.get(position);

        holder.itemDwellingCity.setText(dwelling.getCity());
        holder.itemDwellingType.setText(dwelling.getType());
        holder.itemDwellingRoom.setText("Rooms: " + dwelling.getRoom());
        holder.itemDwellingAvailable.setText(dwelling.isAvailable()
                ? "Available 🟢"
                : "Not available 🔴");
    }

    @Override
    public int getItemCount() {
        return dwellingList.size();
    }

    public class DwellingHolder extends  RecyclerView.ViewHolder {
        private TextView itemDwellingCity;
        private TextView itemDwellingType;
        private TextView itemDwellingRoom;
        private  TextView itemDwellingAvailable;

        public DwellingHolder(@NonNull View itemView) {
            super(itemView);

            itemDwellingCity = itemView.findViewById(R.id.item_dwelling_city);
            itemDwellingType = itemView.findViewById(R.id.item_dwelling_type);
            itemDwellingRoom = itemView.findViewById(R.id.item_dwelling_room);
            itemDwellingAvailable = itemView.findViewById(R.id.item_dwelling_available);
        }
    }
}
