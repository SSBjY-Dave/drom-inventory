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

import java.io.IOException;

import hu.davidorcsik.dorm.inventory.logic.InventoryItem;
import hu.davidorcsik.dorm.inventory.ui.QRCaptureActivity;
import hu.davidorcsik.dorm.inventory.logic.RESTHelper;

public class RegisterQRCode extends AppCompatActivity {
    private QRDetails detailsFragment;

    protected void setDetailsFragment(QRDetails df) {
        detailsFragment = df;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_qr_code);

        findViewById(R.id.btnReadQR).setOnClickListener(scanButtonClickListener);
        findViewById(R.id.btnSave).setOnClickListener(saveButtonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String qrContent = result.getContents();
            if (qrContent != null) {
                try {
                    detailsFragment.setQrId(Long.parseLong(qrContent));
                } catch (NumberFormatException e) {
                    Toast.makeText(RegisterQRCode.this, "Hibás QR kód!", Toast.LENGTH_LONG).show();
                }
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }
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

    private View.OnClickListener saveButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final InventoryItem item = new InventoryItem(detailsFragment);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            RESTHelper.getInstance().addQR(RegisterQRCode.this, item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IllegalArgumentException e) {
                Toast.makeText(RegisterQRCode.this, "Kérlek a megfelelő mezőket töltsd ki!", Toast.LENGTH_LONG).show();
            }
        }
    };
}