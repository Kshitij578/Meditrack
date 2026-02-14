package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Specialization;

public class AIHelper {

    private AIHelper() {}

    public static Specialization recommendDoctor(String symptom) {

        symptom = symptom.toLowerCase();

        if (symptom.contains("heart")) {
            return Specialization.CARDIOLOGIST;
        } else if (symptom.contains("skin")) {
            return Specialization.DERMATOLOGIST;
        } else if (symptom.contains("bone")) {
            return Specialization.ORTHOPEDIC;
        } else {
            return Specialization.GENERAL_PHYSICIAN;
        }
    }
}
