package hu.davidorcsik.dorm.inventory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import hu.davidorcsik.dorm.inventory.utility.InventoryItem;
import hu.davidorcsik.dorm.inventory.utility.QRCaptureActivity;

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
                    detailsFragment.setQrId(1234); //TODO: REMOVE AFTER DEBUG!!!!!
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
                InventoryItem item = new InventoryItem(detailsFragment);
                try {
                    String jsonszar = new ObjectMapper().writeValueAsString(item);
                    int a = 0;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e) {
                Toast.makeText(RegisterQRCode.this, "Kérleg a megfelelő mezőket töltsd ki!", Toast.LENGTH_LONG).show();
            }
        }
    };
}