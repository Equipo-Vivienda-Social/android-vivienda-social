package com.example.viviendasocial.presenter;

import com.example.viviendasocial.contract.ApplicantRegisterContract;
import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.model.ApplicantRegisterModel;

import java.time.LocalDate;

public class ApplicantRegisterPresenter implements ApplicantRegisterContract.Presenter,
ApplicantRegisterContract.Model.OnRegisterListener, ApplicantRegisterContract.Model.onModifyListener {

    private ApplicantRegisterContract.Model model;
    private ApplicantRegisterContract.View view;
    private Applicant currentApplicant;

    public ApplicantRegisterPresenter(ApplicantRegisterContract.View view, Applicant applicant) {
        this.view = view;
        model = new ApplicantRegisterModel();
        this.currentApplicant = applicant;
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

        if (currentApplicant == null) {
            model.register(applicant, this);
        } else {
            applicant.setId(currentApplicant.getId());
            model.modifyApplicant(applicant, this);
        }

    }

    @Override
    public void modifyApplicant(long id, String name, String surname, String dni, LocalDate birthDate, int salary, int familyMembers, boolean employed) {
            register(name, surname, dni, birthDate, salary, familyMembers, employed);
    }

    @Override
    public void onModifySuccess(Applicant applicant) {
        view.showMessage("Applicant modified successfully");
        view.navigateToApplicantList();
    }

    @Override
    public void onModifyError(String message) {
        view.showError(message);
    }
}
