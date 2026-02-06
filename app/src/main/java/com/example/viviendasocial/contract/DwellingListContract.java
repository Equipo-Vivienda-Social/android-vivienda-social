package com.example.viviendasocial.contract;

import com.example.viviendasocial.domain.Dwelling;

import java.util.List;

public interface DwellingListContract {

    interface Model {
        interface OnLoadListener {
            void onLoadSuccesful(List<Dwelling> dwelling);
            void onLoadError(String message);
        }

        void loadDwellings(OnLoadListener listener);
    }

    interface Presenter {
        void loadDwellings();
    }

    interface View {
        void showDwellings(List<Dwelling> dwellings);
        void showMessage(String message);
        void showError(String message);
    }
}
