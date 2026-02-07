package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Applicant;
import com.example.viviendasocial.domain.Dwelling;

import java.time.LocalDate;
import java.util.List;

public interface DwellingRegisterContract {


	interface Model {
		interface OnRegisterListener{
			void onRegisterSuccess(Dwelling dwellings);
			void onRegisterError(String message);
		}

		void registerDwelling(Dwelling dwelling, OnRegisterListener listener);

		interface OnLoadApplicantsListener {
			void OnLoadSuccess(List<Applicant> applicants);
			void OnLoadError(String message);
		}

		void loadApplicants(OnLoadApplicantsListener listener);
	}

	interface Presenter {
		void registerDwelling( String street, String city, String type, int room,  LocalDate buildDate, boolean available, List<Long> applicantsIds);

		void loadApplicants();
	}

	interface View {
		void showMessage(String message);
		void showError(String message);
		void showApplicants(List<Applicant> applicants);
		void navigateToDwellingListView();
	}
}
