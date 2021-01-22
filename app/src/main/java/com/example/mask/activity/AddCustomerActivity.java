package com.example.mask.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mask.model.Customer;
import com.example.mask.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCustomerActivity extends AppCompatActivity {

    private DatabaseReference reference;
    DatabaseReference rootRef;
    EditText  customerCnic, MaskNumber;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_customer );

        reference = FirebaseDatabase.getInstance().getReference();
        rootRef = FirebaseDatabase.getInstance().getReference( "Customer" );
        customerCnic = findViewById(R.id.customer_cnic);
        MaskNumber = findViewById(R.id.face_mask_number);
        saveButton = findViewById(R.id.saveBtn);

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AddNewCustomer(customerCnic.getText().toString(),MaskNumber.getText().toString());
              customerCnic.setText( "" );
              MaskNumber.setText( "" );

            }
        } );
    }

    private void AddNewCustomer(String cnicNumber, String MaskNumber1) {
        if (TextUtils.isEmpty( customerCnic.getText().toString() ) &&
                TextUtils.isEmpty( MaskNumber.getText().toString() )) {
            Toast.makeText( this, "Please Enter Your Data...", Toast.LENGTH_SHORT ).show();
        }
        else {
             String CNIC = customerCnic.getText().toString().trim();
             String Mask = MaskNumber.getText().toString().trim();
             rootRef.addListenerForSingleValueEvent( new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                         String cnic = userSnapshot.child("customerCnic").getValue(String.class);
                         if(cnic.equals( CNIC )){
                             Toast.makeText( AddCustomerActivity.this, "Data Already Exist...", Toast.LENGTH_SHORT ).show();
                         }
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             } );
            Customer customer = new Customer( cnicNumber, MaskNumber1 );
            String id=reference.child( "Customer" ).push().getKey();

            customer.setId( id );
            Log.d( "firebasetest" ,id);
            reference.child( "Customer" ).child( id ).setValue( customer )
                    .addOnSuccessListener( new OnSuccessListener<Void>() {

                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText( AddCustomerActivity.this, "Data Save...", Toast.LENGTH_SHORT ).show();
                    Intent intent;
                    intent= new Intent(AddCustomerActivity.this,MainActivity.class);
                    startActivity( intent );
                    Log.d( "Save", "onSuccess" );
                }
            } ).addOnFailureListener( new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d( "Save", "onFaliuer" );
                }
            } );
        }
    }
}