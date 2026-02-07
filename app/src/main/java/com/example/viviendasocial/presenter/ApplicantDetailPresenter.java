package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.ApplicantDetailContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.model.ApplicantDetailModel;

public class ApplicantDetailPresenter implements ApplicantDetailContract.Presenter,
        ApplicantDetailContract.Model.OnLoadApplicant{

    private ApplicantDetailContract.Model  model;
    private ApplicantDetailContract.View view;

    public ApplicantDetailPresenter(ApplicantDetailContract.View view) {
        this.view = view;
        this.model = new ApplicantDetailModel();
    }

    @Override
    public void onLoadSuccess(Applicant applicant) {
        view.showApplicant(applicant);
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void loadApplicant(long id) {
        model.loadApplicantById(id, this);
    }
}
