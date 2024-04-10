package com.squadtech.adminpanelquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squadtech.adminpanelquiz.Adapter.GeneralQuestionAdapter;
import com.squadtech.adminpanelquiz.Models.GeneralCategoryModel;

import java.util.ArrayList;

public class GeneralCategoryRV extends AppCompatActivity
{
    private RecyclerView questionRecyclerView;
    private DatabaseReference mRootRef_retrieve;
    private FirebaseAuth mAuth;

    private ArrayList<GeneralCategoryModel> listModel = new ArrayList<>();
    private GeneralQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_category_rv);

        questionRecyclerView = findViewById(R.id.general_Questions_rvID);
        questionRecyclerView.setHasFixedSize(true);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        mRootRef_retrieve = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child("General");

        mRootRef_retrieve.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                listModel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    GeneralCategoryModel model = snapshot.getValue(GeneralCategoryModel.class);
                    listModel.add(model);
                }
                adapter = new GeneralQuestionAdapter(getApplicationContext(), listModel);
                questionRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }
}
