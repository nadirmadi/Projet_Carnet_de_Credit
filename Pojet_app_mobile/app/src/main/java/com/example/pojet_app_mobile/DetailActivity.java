package com.example.pojet_app_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DetailActivity extends AppCompatActivity {

    TextView detailnomPrenom, detailtel, detailcredit;
    FloatingActionButton deleteButton, editButton;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailnomPrenom = findViewById(R.id.detailnomPrenom);
        detailtel = findViewById(R.id.detailtel);
        detailcredit = findViewById(R.id.detailcredit);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailnomPrenom.setText(bundle.getString("Nom et Prenom"));
            detailtel.setText(bundle.getString("Tel"));
            detailcredit.setText(bundle.getString("Credit"));
            key = bundle.getString("key");
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
                    reference.child(key).removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Deletion successful
                                    Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Tel", detailtel.getText().toString())
                        .putExtra("Credit", detailcredit.getText().toString())
                        .putExtra("nomPrenom", detailnomPrenom.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });

    }
}