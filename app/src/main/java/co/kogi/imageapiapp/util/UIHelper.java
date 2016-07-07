package co.kogi.imageapiapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by user on 5/07/2016.
 */
public class UIHelper {

    private static ProgressDialog progressDialog;

    public static final void navigateToBackFragment(FragmentManager fm) {
        fm.popBackStack();
    }


    public static final void changeFragment(int idViewToChange,
                                            FragmentManager fm,
                                            Fragment nextFragment) {

        final int fragmentIndexInBackStack = findFragmentIndexInBackStack(fm,
                nextFragment.getClass().getName());

        if (fragmentIndexInBackStack > 0) {
            fm.popBackStack(fragmentIndexInBackStack,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            final FragmentTransaction ft = fm.beginTransaction();
            ft.replace(idViewToChange, nextFragment);
            ft.addToBackStack(nextFragment.getClass().getName());
            ft.commit();
        }


    }


    public static final void changeFragment(int idViewToChange,
                                            FragmentManager fm,
                                            Fragment nextFragment,
                                            Bundle bundle) {

        if (bundle != null) {
            nextFragment.setArguments(bundle);
        }

        final int fragmentIndexInBackStack = findFragmentIndexInBackStack(fm,
                nextFragment.getClass().getName());

        if (fragmentIndexInBackStack > 0) {
            fm.popBackStack(fragmentIndexInBackStack,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            final FragmentTransaction ft = fm.beginTransaction();
            ft.replace(idViewToChange, nextFragment);
            ft.addToBackStack(nextFragment.getClass().getName());
            ft.commit();
        }
    }

    private static final int findFragmentIndexInBackStack(FragmentManager fm,
                                                          String tagname) {
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {

            final String name = fm.getBackStackEntryAt(i).getName();
            if (name.equalsIgnoreCase(tagname)) {
                return i + 1;
            }
        }
        return 0;
    }



    public static final void showProgressDialog(Activity uiContext, String progressDialogMessage) {
        if (!uiContext.isFinishing()) {
            progressDialog = new ProgressDialog(uiContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(progressDialogMessage);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public static final void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static final void showInformationDialog(final Activity uiContext,
                                                   String title,
                                                   String message,
                                                   DialogInterface.OnClickListener okListener,
                                                   DialogInterface.OnClickListener cancelListener) {
        if (!uiContext.isFinishing()) {
            new AlertDialog.Builder(uiContext)
                    .setTitle(title)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",
                            okListener)
                    .setNegativeButton("Cancelar", cancelListener)
                    .show();
        }

    }


    public static final void showInformationDialog(final Activity uiContext,
                                                   String title,
                                                   String message,
                                                   DialogInterface.OnClickListener listener) {
        if (!uiContext.isFinishing()) {
            new AlertDialog.Builder(uiContext)
                    .setTitle(title)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage(message)
                    .setPositiveButton("Aceptar",
                            listener).show();
        }

    }

    public static void displayImage(final String url, final ImageView imageView){
        try {
           int drawableId =  Integer.parseInt(url);
            ImageLoader.getInstance().displayImage("drawable://" + drawableId,
                    imageView, AppContext.getImageOptions());

        } catch (NumberFormatException ex){
            ImageLoader.getInstance().displayImage(url, imageView,
                    AppContext.getImageOptions());
        }


    }

    public static void changeAppBarTitle(Activity activity, String title){
        ((AppCompatActivity)activity).getSupportActionBar().setTitle(title);
    }

    public static String getStringResource(int stringResId){
        return AppContext.getContext().getResources().getString(stringResId);
    }


    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

}
