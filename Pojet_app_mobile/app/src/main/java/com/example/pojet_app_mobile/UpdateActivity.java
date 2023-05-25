package com.example.pojet_app_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {
    Button updateButton;
    EditText updateTel, updateCredit, updatedNomPrenom;
    String tel, credit, nomPrenom;
    String key;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateTel = findViewById(R.id.updateTel);
        updateCredit = findViewById(R.id.updateCredit);
        updatedNomPrenom = findViewById(R.id.updatedNomPrenom);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            updateTel.setText(bundle.getString("Tel"));
            updateCredit.setText(bundle.getString("Credit"));
            updatedNomPrenom.setText(bundle.getString("nomPrenom"));
            key = bundle.getString("Key");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials").child(key);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

                updateData();
                dialog.dismiss();
    }
    public void updateData(){
        nomPrenom = updatedNomPrenom.getText().toString().trim();
        tel = updateTel.getText().toString().trim();
        credit = updateCredit.getText().toString().trim();

        DataClass dataClass = new DataClass(nomPrenom,tel,credit);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}