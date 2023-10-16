package com.gildedrose;

abstract class AbstractItem {
    abstract public void itemQuality(Item item);
    abstract public void itemSellIn(Item item);
    abstract public void itemSellInLessThanZero(Item item);
}

class BasicItem extends AbstractItem {
    @Override
    public void itemQuality(Item item) {
    }

    @Override
    public void itemSellIn(Item item) {
    }

    @Override
    public void itemSellInLessThanZero(Item item) {
    }
}
