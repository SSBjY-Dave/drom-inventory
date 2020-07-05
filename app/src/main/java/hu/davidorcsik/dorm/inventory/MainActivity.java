package hu.davidorcsik.dorm.inventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.asdkek).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterQRCode.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btnSetServer).setOnClickListener(btnSetServerOnClickListener);
    }

    private final View.OnClickListener btnSetServerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Context context = MainActivity.this;
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(context);
            adBuilder.setTitle("Szerver címe:");
            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setText(Config.getServerAddress(context));
            adBuilder.setView(input);
            adBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Config.setServerAddress(context, input.getText().toString());
                }
            });
            adBuilder.setNegativeButton("Mégse", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            adBuilder.show();
        }
    };
}