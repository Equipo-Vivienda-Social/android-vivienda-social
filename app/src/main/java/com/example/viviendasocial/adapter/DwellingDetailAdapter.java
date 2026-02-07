package com.example.viviendasocial.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viviendasocial.R;
import com.example.viviendasocial.domain.Dwelling;

public class DwellingDetailAdapter extends RecyclerView.Adapter<DwellingDetailAdapter.ViewHolder> {

	private Dwelling dwelling;

	public DwellingDetailAdapter(Dwelling dwelling){this.dwelling = dwelling;}


	@NonNull
	@Override
	public DwellingDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_dwelling_detail, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DwellingDetailAdapter.ViewHolder holder, int position) {
		holder.itemStreet.setText("Street: " + dwelling.getStreet());
		holder.itemType.setText("Type: " + dwelling.getType());
		holder.itemCity.setText("City: " + dwelling.getCity());
		holder.itemRooms.setText(String.valueOf("Rooms number: " + dwelling.getRoom()));
		holder.itemBuildDate.setText(String.valueOf("Build date: " + dwelling.getBuildDate()));
		holder.itemAvailable.setText(dwelling.isAvailable()
				? "Dwelling is available"
				: "Dwelling isn't available"
		);
	}

	@Override
	public int getItemCount() {
		return dwelling == null ? 0 : 1;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView itemStreet, itemCity, itemType, itemBuildDate, itemRooms, itemAvailable;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			itemStreet = itemView.findViewById(R.id.dwelling_detail_street);
			itemCity = itemView.findViewById(R.id.dwelling_detail_city);
			itemType = itemView.findViewById(R.id.dwelling_detail_type);
			itemBuildDate = itemView.findViewById(R.id.dwelling_detail_buildDate);
			itemRooms = itemView.findViewById(R.id.dwelling_detail_rooms);
			itemAvailable = itemView.findViewById(R.id.dwelling_detail_available);
		}
	}

	public Dwelling getDwelling() {
		return dwelling;
	}

	public void setDwelling(Dwelling dwelling) {
		this.dwelling = dwelling;
		notifyDataSetChanged();
	}
}
