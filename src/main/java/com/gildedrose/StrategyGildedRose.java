package com.gildedrose;

class StrategyGildedRose {
    Item[] items;

    public StrategyGildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        UpdateQuality quality = new UpdateQuality();
        for (Item item : items) {
            quality.setItem(getStrategyItem(item));
            quality.updateQuality();
        }
    }

    private StrategyItem getStrategyItem(Item item) {
        switch (ManagementItem.findByManagementItem(item.name)) {
            case AGED_BRIE: return new AgedBrieStrategyItem(item);
            case BACKSTAGE_PASSES: return new BackstagePassesStrategyItem(item);
            case SULFURAS: return new SulfurasStrategyItem(item);
            case CONJURED: return new ConjuredStrategyItem(item);
            default: return new BasicStrategyItem(item);
        }
    }
}
