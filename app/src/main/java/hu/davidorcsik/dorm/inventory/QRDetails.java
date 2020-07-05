package hu.davidorcsik.dorm.inventory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import hu.davidorcsik.dorm.inventory.logic.ItemCondition;
import hu.davidorcsik.dorm.inventory.logic.ItemType;
import hu.davidorcsik.dorm.inventory.ui.ItemConditionSpinnerAdapter;
import hu.davidorcsik.dorm.inventory.ui.ItemTypeSpinnerAdapter;

public class QRDetails extends Fragment {
    private long qrId;
    private EditText edtRoomNumber;
    private EditText edtInventoryId;
    private Spinner spnItemType;
    private Spinner spnItemCondition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        ((RegisterQRCode)view.getContext()).setDetailsFragment(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                edtRoomNumber = view.findViewById(R.id.edtRoomNumber);
                edtInventoryId = view.findViewById(R.id.edtInventoryId);
                spnItemType = view.findViewById(R.id.spnItemType);
                spnItemCondition = view.findViewById(R.id.spnItemCondition);
                spnItemType.setAdapter(new ItemTypeSpinnerAdapter(view.getContext(), ItemType.values()));
                spnItemCondition.setAdapter(new ItemConditionSpinnerAdapter(view.getContext(), ItemCondition.getValuesDescending()));
            }
        }).start();
    }

    public long getQrId() {
        return qrId;
    }

    public void setQrId(long qrId) {
        this.qrId = qrId;
    }

    public EditText getEdtRoomNumber() {
        return edtRoomNumber;
    }

    public EditText getEdtInventoryId() {
        return edtInventoryId;
    }

    public Spinner getSpnItemType() {
        return spnItemType;
    }

    public Spinner getSpnItemCondition() {
        return spnItemCondition;
    }
}