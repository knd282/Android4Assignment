package com.example.android_knd;

import com.journeyapps.barcodescanner.CaptureActivity;

public class cam extends CaptureActivity {

    //back press animation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
    }
}
