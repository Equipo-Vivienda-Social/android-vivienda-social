package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Dwelling;

public interface DwellingDetailListContract {

	interface Model {

		interface OnLoadListener {
			void onLoadSuccess(Dwelling dwelling);
			void onLoadError(String message);
		}

		void loadDwellingById(long id, OnLoadListener listener);
	}

	interface View {
		void showDwelling(Dwelling dwelling);
		void showError(String message);
	}

	interface Presenter {
		void loadDwelling(long id);
	}
}
