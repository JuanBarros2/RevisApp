package com.revisapp.ui.infomatter;

import com.revisapp.ui.MVP;

import java.util.Calendar;

/**
 * Criado por Juan em 11/09/2017.
 */

public interface InfoMatterMVP {
    interface Model extends MVP.Model{
        void insertMatter(String name, Calendar initial, Calendar finall);
        void updateMatter(String oldName, String newName, Calendar newInitial, Calendar newFinall);
    }

    interface View extends MVP.View{
        void onInsertMatter(String name, Calendar initial, Calendar finall);
        void onUpdateMatter(String name, Calendar initial, Calendar finall);
        void onError(String message);
    }

    interface Presenter extends MVP.Presenter{
        void insertMatter(String name, Calendar initial, Calendar finall);
        void updateMatter(String oldName, String newName, Calendar newInitial, Calendar newFinall);
    }
}
