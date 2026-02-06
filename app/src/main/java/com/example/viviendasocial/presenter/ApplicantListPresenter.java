package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.ApplicantListContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.model.ApplicantListModel;

import java.util.List;

public class ApplicantListPresenter implements ApplicantListContract.Presenter, ApplicantListContract.Model.OnLoadListener {

   private ApplicantListContract.Model model;
   private ApplicantListContract.View view;

   public ApplicantListPresenter(ApplicantListContract.View view) {
       this.view = view;
       model = new ApplicantListModel();
   }

    @Override
    public void OnLoadSuccess(List<Applicant> applicants) {
        view.showApplicants(applicants);
        view.showMessage("Applicants have been loaded successfully");
    }

    @Override
    public void OnLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void loadApplicants() {
        model.loadApplicants(this);
    }
}
