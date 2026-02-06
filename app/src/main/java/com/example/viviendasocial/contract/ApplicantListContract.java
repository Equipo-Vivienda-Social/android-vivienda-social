package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Applicant;

import java.util.List;

public interface ApplicantListContract {

    interface Model {

        interface OnLoadListener {
            void OnLoadSuccess(List<Applicant> applicants);
            void OnLoadError(String message);
        }

        void loadApplicants(OnLoadListener listener);
    }

    interface Presenter {
        void loadApplicants();
    }

    interface View {
        void showApplicants(List<Applicant> applicants);
        void showError(String message);
        void showMessage(String message);
    }
}
