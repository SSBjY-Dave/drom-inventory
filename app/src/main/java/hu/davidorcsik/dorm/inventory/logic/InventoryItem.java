package hu.davidorcsik.dorm.inventory.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import hu.davidorcsik.dorm.inventory.QRDetails;

public class InventoryItem implements Serializable {
    private long qrId;
    private String roomNumber;
    private String inventoryId;
    @JsonIgnore
    private ItemType itemType;
    private int itemTypeId;
    @JsonIgnore
    private ItemCondition itemCondition;
    private int itemConditionId;

    public InventoryItem() {}

    public InventoryItem(QRDetails details) {
        this.qrId = details.getQrId();
        this.roomNumber = details.getEdtRoomNumber().getText().toString();
        this.inventoryId = details.getEdtInventoryId().getText().toString();
        setItemType((ItemType) details.getSpnItemType().getSelectedItem());
        setItemCondition((ItemCondition) details.getSpnItemCondition().getSelectedItem());
        if (roomNumber.isEmpty() || inventoryId.isEmpty()) throw new IllegalArgumentException();
    }

    public long getQrId() {
        return qrId;
    }

    public void setQrId(long qrId) {
        this.qrId = qrId;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
        this.itemTypeId = itemType.getTypeId();
    }

    public ItemCondition getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
        this.itemConditionId = itemCondition.getConditionId();
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public int getItemConditionId() {
        return itemConditionId;
    }
}
