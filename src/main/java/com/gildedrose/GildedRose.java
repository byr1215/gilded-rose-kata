package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            itemQuality(item);
            itemSellIn(item);
            itemSellInLessThanZero(item);
        }
    }

    private void itemQuality(Item item) {
        if (ManagementItem.findByManagementItem(item.name) != ManagementItem.AGED_BRIE
            && ManagementItem.findByManagementItem(item.name) != ManagementItem.BACKSTAGE_PASSES) {
            itemQualityOverZero(item);
        } else {
            if (item.quality < 50) {
                itemQualityPlus(item);

                if (ManagementItem.findByManagementItem(item.name) == ManagementItem.BACKSTAGE_PASSES) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            itemQualityPlus(item);
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            itemQualityPlus(item);
                        }
                    }
                }
            }
        }
    }

    private void itemSellIn(Item item) {
        if (ManagementItem.findByManagementItem(item.name) != ManagementItem.SULFURAS) {
            itemSellInMinusOne(item);
        }
    }

    private void itemSellInLessThanZero(Item item) {
        if (item.sellIn < 0) {
            if (ManagementItem.findByManagementItem(item.name) != ManagementItem.AGED_BRIE) {
                if (ManagementItem.findByManagementItem(item.name) != ManagementItem.BACKSTAGE_PASSES) {
                    itemQualityOverZero(item);
                } else {
                    itemQualityZero(item);
                }
            } else {
                if (item.quality < 50) {
                    itemQualityPlus(item);
                }
            }
        }
    }

    private void itemSellInMinusOne(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void itemQualityZero(Item item) {
        item.quality = 0;
    }

    private void itemQualityPlus(Item item) {
        item.quality = item.quality + 1;
    }

    private void itemQualityMinus(Item item, int qualityDeteriorationTimes) {
        item.quality = item.quality - qualityDeteriorationTimes;
    }

    private void itemQualityNegativeNumberImpossible(Item item) {
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private void itemQualityOverZero(Item item) {
        if (item.quality > 0) {
            if (ManagementItem.findByManagementItem(item.name) != ManagementItem.SULFURAS) {
                if (ManagementItem.findByManagementItem(item.name) != ManagementItem.CONJURED) {
                    itemQualityMinus(item, 1);
                } else {
                    itemQualityMinus(item, 2);
                }

                itemQualityNegativeNumberImpossible(item);
            }
        }
    }
}
