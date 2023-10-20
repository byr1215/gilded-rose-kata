package com.gildedrose;

class FactoryGildedRose {
    Item[] items;

    public FactoryGildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            FactoryItem factoryItem = SimpleFactory.getInstance(item);
            factoryItem.itemQuality();
            factoryItem.itemSellIn();
            factoryItem.itemSellInLessThanZero();
        }
    }
}
