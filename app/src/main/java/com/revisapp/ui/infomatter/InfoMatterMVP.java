package com.revisapp.ui.infomatter;

import com.revisapp.domain.Matter;
import com.revisapp.ui.MVP;

import java.util.Calendar;

/**
 * Criado por Juan em 11/09/2017.
 */

public interface InfoMatterMVP {
    interface Model extends MVP.Model{
        void insertMatter(String name, Calendar initial, Calendar finall);
    }

    interface View extends MVP.View{
        void onError(String message);
    }

    interface Presenter extends MVP.Presenter{
        void insertMatter(String name, String initial, String finall);
        void onError(String message);
        void onInsertedMatter(Matter matter);
    }
}
