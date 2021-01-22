package com.example.mask.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mask.R;
import com.example.mask.adapter.CustomerListAdapter;
import com.example.mask.model.Customer;
import com.example.mask.model.CustomerList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Customer> list = new ArrayList<>();
    private CustomerListAdapter adapter;
    FloatingActionButton AddNewCustomer;
    RecyclerView CustomerListRecyclerView;
    DatabaseReference rootRef;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        searchBar = findViewById( R.id.search_bar );
        AddNewCustomer = findViewById( R.id.fab_action );
        CustomerListRecyclerView = findViewById( R.id.listOfcustomer );
        CustomerListRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        rootRef = FirebaseDatabase.getInstance().getReference( "Customer" );
        loadUserDetails();
        SearchData();

        AddNewCustomer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent( MainActivity.this, AddCustomerActivity.class );
                startActivity( intent );
            }
        } );
    }

    private void SearchData() {
        searchBar.addTextChangedListener( new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    adapter = new CustomerListAdapter( list, getApplicationContext() );
                    CustomerListRecyclerView.setAdapter( adapter );
                } else {
                    SearchValue( s.toString() );
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.e("TextWatcherTest", "afterTextChanged:\t" +s.toString());
            }
        } );
    }

    private void SearchValue(String search) {
        ArrayList<Customer> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get( i ).getCustomerCnic().contains( search )) {
                arrayList.add( list.get( i ) );
            }
        }
    }

    private void loadUserDetails() {
        rootRef.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Customer customer = ds.getValue( Customer.class );
                    list.add( customer );
                }
                adapter = new CustomerListAdapter( list, MainActivity.this );
                CustomerListRecyclerView.setAdapter( adapter );
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
    }
}
