package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Applicant;

import java.time.LocalDate;

public interface ApplicantRegisterContract {

    interface Model {

        interface OnRegisterListener {
            void onRegisterSuccess(Applicant applicant);
            void onRegisterError(String message);
        }

        void register(Applicant applicant, OnRegisterListener listener);

        interface onModifyListener {
            void onModifySuccess(Applicant applicant);
            void onModifyError(String message);
        }

        void modifyApplicant(Applicant applicant, onModifyListener listener);
    }

    interface Presenter {
        void register(String name, String surname, String dni, LocalDate birthDate,
                      int salary, int familyMembers, boolean employed);
        void modifyApplicant(long id, String name, String surname, String dni, LocalDate birthDate,
                      int salary, int familyMembers, boolean employed);

    }

    interface View {
        void showError(String message);
        void showMessage(String message);
        void navigateToApplicantList();
    }
}
