package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.ApplicantRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.model.ApplicantRegisterModel;

import java.time.LocalDate;

public class ApplicantRegisterPresenter implements ApplicantRegisterContract.Presenter,
ApplicantRegisterContract.Model.OnRegisterListener {

    private ApplicantRegisterContract.Model model;
    private ApplicantRegisterContract.View view;

    public ApplicantRegisterPresenter(ApplicantRegisterContract.View view) {
        this.view = view;
        model = new ApplicantRegisterModel();
    }

    @Override
    public void onRegisterSuccess(Applicant applicant) {
        view.showMessage("The applicant has been registered successfully");
        view.navigateToApplicantList();
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }

    @Override
    public void register(String name, String surname, String dni, LocalDate birthDate, int salary,
                         int familyMembers, boolean employed) {

        Applicant applicant = Applicant.builder()
                .name(name)
                .surname(surname)
                .dni(dni)
                .birthDate(birthDate)
                .salary(salary)
                .familyMembers(familyMembers)
                .employed(employed)
                .build();

        model.register(applicant, this);
    }
}
