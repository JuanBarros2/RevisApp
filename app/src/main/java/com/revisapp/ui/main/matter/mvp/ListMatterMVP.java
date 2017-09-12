package com.revisapp.ui.main.matter.mvp;

import android.content.Intent;

import com.revisapp.domain.Matter;
import com.revisapp.ui.MVP;

import java.util.List;

/**
 * Criado por Juan em 12/09/2017.
 */

public interface ListMatterMVP {
    final int REQUEST_ADD_MATTER = 104;
    final int REQUEST_UPDATE_MATTER = 105;

    interface Model extends MVP.Model{
        List<Matter> getAll();

        void removeMatter(Matter matter);
    }

    interface View extends MVP.View{
        void loading();
        void loaded();
    }

    interface Presenter extends MVP.Presenter{
        void setUpRecycle();
        void onUpdateMatter(Matter matter);
        void onRemoveMatter(Matter matter);
        void onRemoved(Matter matter);
        void onError(String message);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
