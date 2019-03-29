package com.altertech.evacc.dialog.listeners;

import com.altertech.evacc.dialog.obj.CustomAlertDialog;

/**
 * Created by oshevchuk on 03.07.2018
 */
public interface DialogCallBackYesCancel {
    void dialogYes(CustomAlertDialog dialog, Object o);

    void dialogCancel(CustomAlertDialog dialog);
}
