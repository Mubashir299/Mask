package com.example.mask.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mask.R;
import com.example.mask.activity.UpdateActivity;
import com.example.mask.model.Customer;
import com.example.mask.model.CustomerList;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.Holder> {
    private List<Customer> list;
    private Context context;

    public CustomerListAdapter(List<Customer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context ).inflate( R.layout.layout_customer_list,parent, false );
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Customer customerList = list.get(position);
        holder.tvCnic.setText(customerList.getCustomerCnic());
        holder.tvMaskNumber.setText(customerList.getMaskNumber());
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity( new Intent(context, UpdateActivity.class )
                        .putExtra( "CNIC",customerList.getCustomerCnic())
                        .putExtra( "Mask", customerList.getMaskNumber())
                        .putExtra( "id", customerList.getId()));
            }
        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvCnic, tvMaskNumber;

        public Holder(@NonNull View itemView) {
            super( itemView );
            tvCnic = itemView.findViewById( R.id.tv_cnic );
            tvMaskNumber = itemView.findViewById( R.id.tv_mask_number );
        }
    }
}
