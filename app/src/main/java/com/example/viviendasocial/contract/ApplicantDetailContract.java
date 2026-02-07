package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Applicant;

public interface ApplicantDetailContract {

    interface Model {

        interface OnLoadListener {
            void onLoadSuccess(Applicant applicant);
            void onLoadError(String message);
        }

        void loadApplicantById(long id, OnLoadListener listener);

        interface onDeleteListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void deleteApplicant(long id, onDeleteListener listener);
    }

    interface Presenter {
        void loadApplicant(long id);
        void deleteApplicant(long id);
    }

    interface View {
        void showApplicant(Applicant applicant);
        void showMessage(String message);
        void showError(String message);
        void navigateToApplicantList();
    }
}
