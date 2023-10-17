package com.gildedrose;

class FactoryGildedRose {
    Item[] items;

    public FactoryGildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            FactoryItem factoryItem = Factory.getInstance(item);
            factoryItem.itemQuality();
            factoryItem.itemSellIn();
            factoryItem.itemSellInLessThanZero();
        }
    }
}
