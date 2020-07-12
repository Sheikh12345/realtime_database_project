package com.tasktakers.realtimedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Donor> itemList;

    public RecyclerViewAdapter(Context mContext, List<Donor> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
       return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donor item = itemList.get(position);
        holder.Name.setText(item.getFullName());
        holder.Email.setText(item.getEmail());
        holder.BloodGroup.setText(item.getBloodGroup());
        holder.City.setText(item.getCity());
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView City;
        TextView BloodGroup;
        TextView Email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             Name = itemView.findViewById(R.id.donorName);
             City = itemView.findViewById(R.id.donorCity);
             BloodGroup = itemView.findViewById(R.id.donorBloodGroup);
             Email = itemView.findViewById(R.id.donorEmail);
        }
    }
}
