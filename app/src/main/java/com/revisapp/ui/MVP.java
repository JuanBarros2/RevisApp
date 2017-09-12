package com.revisapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Criado por Juan em 12/09/2017.
 */

public interface MVP {
    interface Model{

    }

    interface View{
        Context getContext();
        Activity getActivity();
    }

    interface Presenter{
        Context getContext();

    }
}
