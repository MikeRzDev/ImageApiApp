package co.kogi.imageapiapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;

import co.kogi.imageapiapp.R;
import co.kogi.imageapiapp.ui.fragment.GridFragment;
import co.kogi.imageapiapp.util.UIHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIHelper.changeFragment(R.id.container,getSupportFragmentManager(),new GridFragment());
        FacebookSdk.sdkInitialize(getApplicationContext());

    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    } // end : onBackPressed Method

}
