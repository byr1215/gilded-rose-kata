package com.gildedrose;

import java.util.Arrays;

public enum ManagementItem {

      AGED_BRIE("Aged Brie")
    , BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert")
    , SULFURAS("Sulfuras, Hand of Ragnaros")
    , CONJURED("Conjured");

    ManagementItem(String name) {
        this.name = name;
    }

    private final String name;

    public static ManagementItem findByManagementItem(String name) {
        return Arrays.stream(ManagementItem.values())
            .filter(loginStatus -> loginStatus.hasManagementItemName(name))
            .findAny()
            .orElse(null);
    }

    public boolean hasManagementItemName(String name) {
        if (this.name.equals(name)) {
            return true;
        }

        if (name.contains(this.name)) {
            return true;
        }

        return false;
    }



}
