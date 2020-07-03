package hu.davidorcsik.dorm.inventory.utility;

import java.io.Serializable;
import java.text.ParseException;

import hu.davidorcsik.dorm.inventory.QRDetails;

public class InventoryItem implements Serializable {
    private long qrId;
    private String roomNumber;
    private String inventoryId;
    private String itemType;

    public InventoryItem() {}

    public InventoryItem(QRDetails details) {
        this.qrId = details.getQrId();
        this.roomNumber = details.getEdtRoomNumber().getText().toString();
        this.inventoryId = details.getEdtInventoryId().getText().toString();
        this.itemType = details.getSpnItemType().getSelectedItem().toString();
        if (roomNumber.isEmpty() || inventoryId.isEmpty() || itemType.isEmpty()) throw new IllegalArgumentException();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public long getQrId() {
        return qrId;
    }

    public void setQrId(long qrId) {
        this.qrId = qrId;
    }
}
