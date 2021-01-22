package com.example.mask.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mask.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
     EditText updateCnic, updateMaskNumber;
     Button  updateBtn;
    DatabaseReference rootRef;
    String nodeId;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update );
        updateCnic = findViewById( R.id.update_cnic );
        updateMaskNumber = findViewById( R.id.update_mask_number);
        updateBtn = findViewById( R.id.update_btn );
        Intent intent =  getIntent();
        String CustomerCNIC = intent.getStringExtra( "CNIC" );
        String CustomerMask = intent.getStringExtra( "Mask" );
        nodeId = intent.getStringExtra( "id" );
        updateCnic.setText( CustomerCNIC );
        updateMaskNumber.setText(CustomerMask);
        rootRef = FirebaseDatabase.getInstance().getReference("Customer");
        UpdateData();

    }

    private void UpdateData() {
        updateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CustomerID = updateCnic.getText().toString();
                String MaskNumber = updateMaskNumber.getText().toString();
                Map<String, Object> taskMap = new HashMap<String, Object>();
                taskMap.put( "customerCnic",CustomerID );
                taskMap.put( "maskNumber",MaskNumber);

              rootRef.child( nodeId ).updateChildren( taskMap ).addOnSuccessListener( new OnSuccessListener() {
                  @Override
                  public void onSuccess(Object o) {
                      Toast.makeText( UpdateActivity.this, "Data id Updated...", Toast.LENGTH_SHORT ).show();
                  }
              } );
            }
        } );
    }
}