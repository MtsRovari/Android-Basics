package com.mateusrovari.recyclerview;

public class Item {
    private String itemName;
    private String itemDescription;
    private String itemNumber;

    public Item(String itemName, String itemDescription, String itemNumber) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemNumber() {
        return itemNumber;
    }
}
