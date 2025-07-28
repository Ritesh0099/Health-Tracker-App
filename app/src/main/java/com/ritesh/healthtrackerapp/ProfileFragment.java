package com.ritesh.healthtrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    TextInputEditText etEmail, etUsername, etAge, etPassword;
    Button btnSave;
    DBHelper dbHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etEmail = view.findViewById(R.id.etEmail);
        etUsername = view.findViewById(R.id.etUsername);
        etAge = view.findViewById(R.id.etAge);
        etPassword = view.findViewById(R.id.etPassword);
        btnSave = view.findViewById(R.id.btnSaveProfile);
        dbHelper = new DBHelper(getActivity());

        loadProfile();

        btnSave.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || ageStr.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid age", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = dbHelper.saveProfile(email, username, age, password);
            if (success) {
                Toast.makeText(getActivity(), "Profile Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Save Failed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loadProfile() {
        Cursor cursor = dbHelper.getProfile();
        if (cursor != null && cursor.moveToFirst()) {
            etEmail.setText(cursor.getString(1));
            etUsername.setText(cursor.getString(2));
            etAge.setText(String.valueOf(cursor.getInt(3)));
            etPassword.setText(cursor.getString(4));
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}
