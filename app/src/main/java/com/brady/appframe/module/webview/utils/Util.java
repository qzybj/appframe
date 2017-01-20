package com.brady.appframe.module.webview.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import com.cwebview.i.IChooseFileListener;

/**
 *
 * @author Cuckoo
 * @date 2016-12-21
 * @description
 *      相关工具类
 */

public class Util {

    /**
     * 显示图片选择框
     * @param chooseFileListener
     */
    public static void showChooseFileDialog(Activity activity, final IChooseFileListener chooseFileListener) {
        if (chooseFileListener == null || activity == null ) {
            return;
        }
        String[] selectPicTypeStr = {"相机", "图库"};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("上传图片方式");
        Dialog chooseDialog = builder.setItems(selectPicTypeStr, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    // 相机拍摄
                    case 0:
                        chooseFileListener.onChooseCamera();
                        break;
                    // 手机相册
                    case 1:
                        chooseFileListener.onChoosePhotos();
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        })
                .setPositiveButton(null, null)
                .setNegativeButton(null, null).create();
        chooseDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                chooseFileListener.onDismiss();
            }
        });
        chooseDialog.show();
    }
}
