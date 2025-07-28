package com.ritesh.healthtrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class LogFragment extends Fragment {

    EditText etSymptom;
    Button btnSave;
    LinearLayout layoutSymptomList;
    DBHelper dbHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        etSymptom = view.findViewById(R.id.etSymptom);
        btnSave = view.findViewById(R.id.btnSaveSymptom);
        layoutSymptomList = view.findViewById(R.id.layoutSymptomList);
        dbHelper = new DBHelper(getActivity());

        loadSymptoms();

        btnSave.setOnClickListener(v -> {
            String symptom = etSymptom.getText().toString().trim();
            if (!symptom.isEmpty()) {
                if (dbHelper.insertSymptom(symptom)) {
                    Toast.makeText(getActivity(), "Symptom Saved", Toast.LENGTH_SHORT).show();
                    etSymptom.setText("");
                    loadSymptoms();
                } else {
                    Toast.makeText(getActivity(), "Failed to Save", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void loadSymptoms() {
        layoutSymptomList.removeAllViews(); // clear old list
        Cursor cursor = dbHelper.getAllSymptoms();
        while (cursor.moveToNext()) {
            String symptom = cursor.getString(1);
            TextView tv = new TextView(getActivity());
            tv.setText("• " + symptom);
            tv.setTextSize(16);
            tv.setPadding(0, 8, 0, 8);
            layoutSymptomList.addView(tv);
        }
        cursor.close();
    }
}
