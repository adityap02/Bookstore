package org.ood.adporwal.bookstore.enums;

public enum InventoryItemState {
    AVAILABLE{ public InventoryItemState transition() { return SOLD;}},
    SOLD {public InventoryItemState transition() { return AVAILABLE;}};
    public abstract InventoryItemState transition();
}
