package com.paulinhodelivery.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.paulinhodelivery.R;
import com.paulinhodelivery.databinding.ActivityFinishedOrderBinding;
import com.paulinhodelivery.databinding.ActivityOrderBinding;
import com.paulinhodelivery.databinding.ActivityReportProblemOrderBinding;

public class ReportProblemOrderActivity extends AppCompatActivity {

    private ActivityReportProblemOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem_order);

        getSupportActionBar().setTitle(getString(R.string.problem));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ActivityReportProblemOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btPhoto.setOnClickListener(v -> {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }else{
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
            }
        });

        binding.btReport.setOnClickListener(v -> Toast.makeText(ReportProblemOrderActivity.this, "Problema enviado com sucesso!!", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 100);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}