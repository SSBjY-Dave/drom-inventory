package hu.davidorcsik.dorm.inventory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class QRDetails extends Fragment {
    private long qrId;
    private EditText edtRoomNumber;
    private EditText edtInventoryId;
    private Spinner spnItemType;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((RegisterQRCode)view.getContext()).setDetailsFragment(this);
        edtRoomNumber = view.findViewById(R.id.edtRoomNumber);
        edtInventoryId = view.findViewById(R.id.edtInventoryId);
        spnItemType = view.findViewById(R.id.spnItemType);
        spnItemType.setAdapter(ArrayAdapter.createFromResource(view.getContext(), R.array.item_types, android.R.layout.simple_spinner_item));
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

    public void setEdtRoomNumber(EditText edtRoomNumber) {
        this.edtRoomNumber = edtRoomNumber;
    }

    public EditText getEdtInventoryId() {
        return edtInventoryId;
    }

    public void setEdtInventoryId(EditText edtInventoryId) {
        this.edtInventoryId = edtInventoryId;
    }

    public Spinner getSpnItemType() {
        return spnItemType;
    }

    public void setSpnItemType(Spinner spnItemType) {
        this.spnItemType = spnItemType;
    }
}