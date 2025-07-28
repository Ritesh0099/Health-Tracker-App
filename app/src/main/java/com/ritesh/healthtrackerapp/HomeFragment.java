package com.ritesh.healthtrackerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextView waterGoalTextView, stepGoalTextView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        waterGoalTextView = view.findViewById(R.id.waterGoalTV);
        stepGoalTextView = view.findViewById(R.id.walkGoalTV);

        // Default values (you can retrieve from SharedPreferences if set during profile input)
        int weightKg = 60;
        int age = 25;

        // Calculate recommended water intake and step goal
        String waterIntake = calculateWaterIntake(weightKg);
        String stepGoal = calculateStepGoal(age);

        waterGoalTextView.setText("Water Intake Goal: " + waterIntake);
        stepGoalTextView.setText("Step Goal: " + stepGoal);
    }

    private String calculateWaterIntake(int weightKg) {
        // Simple formula: 35 ml per kg of body weight
        int intakeMl = weightKg * 35;
        return intakeMl + " ml";
    }

    private String calculateStepGoal(int age) {
        // Basic logic, can be customized
        if (age < 30) {
            return "10,000 steps";
        } else if (age < 50) {
            return "8,000 steps";
        } else {
            return "6,000 steps";
        }
    }
}
