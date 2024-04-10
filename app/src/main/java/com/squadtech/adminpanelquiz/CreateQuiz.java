package com.squadtech.adminpanelquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateQuiz extends AppCompatActivity {

    private Button creatBtn;
    private EditText opt1, opt2,opt3,opt4 , question , answer, time;
    private Spinner category;

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        opt1 =(EditText)findViewById(R.id.option1);
        opt2 =(EditText)findViewById(R.id.option2);
        opt3 =(EditText)findViewById(R.id.option3);
        opt4 =(EditText)findViewById(R.id.option4);
        question =(EditText)findViewById(R.id.writeQuestion);
        answer =(EditText)findViewById(R.id.crctanswer);
        time = (EditText)findViewById(R.id.setTimer_id);

        creatBtn = (Button)findViewById(R.id.quizBtn);

        category = (Spinner)findViewById(R.id.catSpin);


        creatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = question.getText().toString();
                String ans = answer.getText().toString();
                String op1 = opt1.getText().toString();
                String op2 = opt2.getText().toString();
                String op3 = opt3.getText().toString();
                String op4 = opt4.getText().toString();
                String cat = category.getSelectedItem().toString();
                if (cat.equalsIgnoreCase("Câu hỏi chung")) {
                    cat = "General";
                } else if (cat.equalsIgnoreCase("Câu hỏi logic")) {
                    cat = "Logical";
                } else if (cat.equalsIgnoreCase("Phân tích")) {
                    cat = "Analytical";
                } else if (cat.equalsIgnoreCase("Toán học")) {
                    cat = "ETEA";
                } else {
                    cat = "Generals";
                }
                String tym = time.getText().toString();

                if (!TextUtils.isEmpty(ques)&&!TextUtils.isEmpty(ans)
                        &&!TextUtils.isEmpty(op1)
                        &&!TextUtils.isEmpty(op2)
                        &&!TextUtils.isEmpty(op3)
                        &&!TextUtils.isEmpty(op4)
                        &&!TextUtils.isEmpty(cat)
                        &&!TextUtils.isEmpty(tym)){

                    CreatQuestions(ques , ans, op1 ,op2,op3,op4,cat , tym);
                }
                else {
                    Toast.makeText(CreateQuiz.this, "Check The Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CreatQuestions(String ques, String ans, String op1, String op2, String op3, String op4, String cat, String tym) {

        mRootRef = FirebaseDatabase.getInstance().getReference("Questions").child("Categories").child(cat).push();

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("question" ,ques);
        userMap.put("opt1" ,op1);
        userMap.put("opt2" ,op2);
        userMap.put("opt3" ,op3);
        userMap.put("opt4" ,op4);
        userMap.put("answer" ,ans);
        userMap.put("time" ,tym);

        mRootRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CreateQuiz.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
