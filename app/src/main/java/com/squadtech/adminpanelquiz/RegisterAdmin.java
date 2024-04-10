package com.squadtech.adminpanelquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterAdmin extends AppCompatActivity {


    private EditText eName , eEmail , ePass , ePhone ;

    private TextView txtAlreadyAcnt;
    private Button confirmBtn;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        eName = (EditText)findViewById(R.id.RegName);
        eEmail = (EditText)findViewById(R.id.RegEmail);
        ePass = (EditText)findViewById(R.id.RegPass);
        ePhone = (EditText)findViewById(R.id.RegPhone);

        mProgress = new ProgressDialog(this);

        txtAlreadyAcnt = (TextView)findViewById(R.id.txtAlreadyReg);
        txtAlreadyAcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterAdmin.this , LoginAdmin.class));
            }
        });


        //Button and Check box


        confirmBtn = (Button) findViewById(R.id.RegBtn);
        checkBox = (CheckBox) findViewById(R.id.Regchkbox);

        mAuth = FirebaseAuth.getInstance();



        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = eName.getText().toString();
                String sEmail = eEmail.getText().toString();
                String sPass = ePass.getText().toString();
                String sPhone = ePhone.getText().toString();

                if (TextUtils.isEmpty(sName)&&TextUtils.isEmpty(sEmail)&&TextUtils.isEmpty(sPass)&&TextUtils.isEmpty(sPhone)){

                    Exception exception = null;
                    Toast.makeText(RegisterAdmin.this, "error "+ exception.getMessage() , Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterAccount(sName , sEmail, sPass , sPhone);
                }
            }
        });

    }

    private void RegisterAccount(final String sName, final String sEmail, final String sPass, final String sPhone) {


mProgress.setTitle("Please Wait");
mProgress.setMessage("Registering Account");
mProgress.show();
        mAuth.createUserWithEmailAndPassword(sEmail , sPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){


                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Admins").child(FirebaseAuth.getInstance().getUid());
                    final HashMap<String , Object> userMap = new HashMap<>();
                    userMap.put("admin_name" , sName);
                    userMap.put("admin_email" , sEmail);
                    userMap.put("admin_phone" , sPhone);
                    userMap.put("admin_pass" , sPass);
                    userMap.put("admin_uid", FirebaseAuth.getInstance().getUid());
                    userMap.put("admin_profile" , "default");

                    mProgress.dismiss();
                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent mainIntent = new Intent(RegisterAdmin.this, NavigationActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                        }
                    });

                }

            }
        });

    }

}
