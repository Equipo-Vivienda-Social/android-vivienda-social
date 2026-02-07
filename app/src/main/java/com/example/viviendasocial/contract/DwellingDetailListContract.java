package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Dwelling;

public interface DwellingDetailListContract {

	interface Model {

		interface OnLoadListener {
			void onLoadSuccess(Dwelling dwelling);
			void onLoadError(String message);
		}

		void loadDwellingById(long id, OnLoadListener listener);

		interface onDeleteListener {
			void onDeleteSuccess();
			void onDeleteError(String message);
		}

		void deleteDwelling(long id, DwellingDetailListContract.Model.onDeleteListener listener);
	}

	interface View {
		void showDwelling(Dwelling dwelling);
		void showError(String message);
		void navigateToDwellingListView();
		void showMessage(String message);
	}

	interface Presenter {
		void loadDwelling(long id);
		void deleteDwelling(long id);
	}
}
