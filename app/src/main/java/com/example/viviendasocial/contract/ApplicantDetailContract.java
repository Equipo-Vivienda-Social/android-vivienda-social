package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Applicant;

public interface ApplicantDetailContract {

    interface Model {

        interface OnLoadApplicant {
            void onLoadSuccess(Applicant applicant);
            void onLoadError(String message);
        }

        void loadApplicantById(long id, OnLoadApplicant listener);
    }

    interface Presenter {
        void loadApplicant(long id);
    }

    interface View {
        void showApplicant(Applicant applicant);
        void showMessage(String message);
        void showError(String message);
    }
}
