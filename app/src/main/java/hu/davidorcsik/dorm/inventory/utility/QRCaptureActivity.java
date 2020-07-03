package hu.davidorcsik.dorm.inventory.utility;

import android.app.Activity;
import android.content.Context;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;

public class QRCaptureActivity extends CaptureActivity {
    public static void initQRScan(Activity activity) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.setCaptureActivity(QRCaptureActivity.class);
        intentIntegrator.initiateScan();
    }
}
