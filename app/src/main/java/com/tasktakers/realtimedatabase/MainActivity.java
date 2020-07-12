package com.tasktakers.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  //UI Components
  private  DatabaseReference dRef;
  private List<Donor> itemList;
  private String personId;
  private RecyclerView recyclerView;
  private RecyclerViewAdapter rCViewAdapter;
 private EditText
    donorName,
    donorEmail,
    donorCity,
    donorBloodGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing UI Components
        recyclerView = findViewById(R.id.peopleList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        donorName = findViewById(R.id.etFullName);
        donorCity = findViewById(R.id.etCity);
        donorBloodGroup = findViewById(R.id.etBloodGroup);
        donorEmail = findViewById(R.id.etEmail);

        //for data persistence
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        dRef = FirebaseDatabase.getInstance().getReference("Donor");
        personId = dRef.push().getKey();
        findViewById(R.id.btnAddDonorInfo).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = donorName.getText().toString();
                String email = donorEmail.getText().toString();
                String bloodGroup = donorBloodGroup.getText().toString();
                String city = donorCity.getText().toString();
                addPerson(name,email,bloodGroup,city);
            }
        });


        findViewById(R.id.btnLoadDonorInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }


    private void addPerson(String name,String Email,String bloodGroup,String city){
        personId = dRef.push().getKey();
        Donor person = new Donor(name,Email,bloodGroup,city);
        dRef.child(personId).setValue(person);
    }


    private void readData(){
         itemList = new ArrayList<>();
         dRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Iterable<DataSnapshot> snapshotIterator = snapshot.getChildren();
                 Iterator<DataSnapshot> dataSnapshotIterator = snapshotIterator.iterator();
                 while (dataSnapshotIterator.hasNext()){
                     Donor donor = dataSnapshotIterator.next().getValue(Donor.class);
                     itemList.add(donor);
                     rCViewAdapter.notifyDataSetChanged();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
         rCViewAdapter = new RecyclerViewAdapter(this,itemList);
         recyclerView.setAdapter(rCViewAdapter);
    }

}