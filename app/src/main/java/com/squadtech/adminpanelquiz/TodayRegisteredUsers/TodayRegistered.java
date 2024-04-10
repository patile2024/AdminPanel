package com.squadtech.adminpanelquiz.TodayRegisteredUsers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.adminpanelquiz.AllUsers.AllUsersListAdapter;
import com.squadtech.adminpanelquiz.AllUsers.Alluserslist_Model;
import com.squadtech.adminpanelquiz.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TodayRegistered extends AppCompatActivity {

    private RecyclerView allusers_rv;
    private ArrayList<Today_Reg_userslist_Model> arrayList;
    private TodayRegUsersListAdapter adapter_users;
    DatabaseReference dbReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_registered);

        Toolbar toolbar = findViewById(R.id.today_reg_users_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Today Registered");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String date = DateFormat.getDateInstance().format(new Date());

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Users");

        allusers_rv = findViewById(R.id.today_reg_users_RV);
        allusers_rv.hasFixedSize();
        allusers_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        arrayList = new ArrayList<>();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                System.err.println("done one");
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    System.err.println("done two");
                    Today_Reg_userslist_Model model = ds.getValue(Today_Reg_userslist_Model.class);

                    if (date.equals(model.getRegistered_date()))
                    {
                        System.err.println("done three");
                        arrayList.add(model);
                    }
                }
                adapter_users = new TodayRegUsersListAdapter(getApplicationContext(), arrayList);
                allusers_rv.setAdapter(adapter_users);
                adapter_users.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
