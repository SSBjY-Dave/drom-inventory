package hu.davidorcsik.dorm.inventory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import hu.davidorcsik.dorm.inventory.utility.QRCaptureActivity;

public class RegisterQRCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_qr_code);

        findViewById(R.id.btnReadQR).setOnClickListener(scanButtonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String qrContent = result.getContents();
            if (qrContent != null) Toast.makeText(this, qrContent, Toast.LENGTH_LONG).show();
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private View.OnClickListener scanButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            QRCaptureActivity.initQRScan(RegisterQRCode.this);
        }
    };
}