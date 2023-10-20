package com.gildedrose;

public interface StrategyItem {
    // 퀄리티 증감
    void itemQuality();

    // 판매일 경과
    void itemSellIn();

    void itemSellInLessThanZero();
}

class UpdateQuality {
    private StrategyItem item;

    public void setItem(StrategyItem item) {
        this.item = item;
    }

    public void updateQuality() {
        this.item.itemQuality();
        this.item.itemSellIn();
        this.item.itemSellInLessThanZero();
    }
}

// 기본 아이템
class BasicStrategyItem implements StrategyItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;       // 남은 기간 감소
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_TIMES  = -1;       // 퀄리티 배율

    private final Item item;

    BasicStrategyItem(Item item) {
        this.item = item;
    }

    @Override
    public void itemQuality() {
        qualityDecrease();
    }

    @Override
    public void itemSellIn() {
        this.item.sellIn += SELL_IN_DAY;
    }

    @Override
    public void itemSellInLessThanZero() {
        if (this.item.sellIn < SELL_IN_ZERO) {
            qualityDecrease();
        }
    }

    private void qualityDecrease() {
        if (this.item.quality > QUALITY_MIN) {
            this.item.quality += QUALITY_TIMES;
        }
    }
}

// 오래된 브리치즈
class AgedBrieStrategyItem implements StrategyItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;       // 남은 기간 감소
    private static final int QUALITY_MAX    = 50;       // 최대 퀄리티
    private static final int QUALITY_TIMES  = 1;        // 퀄리티 배율

    private final Item item;

    AgedBrieStrategyItem(Item item) {
        this.item = item;
    }

    @Override
    public void itemQuality() {
        qualityIncrease();
    }

    @Override
    public void itemSellIn() {
        this.item.sellIn += SELL_IN_DAY;
    }

    @Override
    public void itemSellInLessThanZero() {
        if (this.item.sellIn < SELL_IN_ZERO) {
            qualityIncrease();
        }
    }

    private void qualityIncrease() {
        if (this.item.quality < QUALITY_MAX) {
            this.item.quality += QUALITY_TIMES;
        }
    }
}

class BackstagePassesStrategyItem implements StrategyItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;        // 남은 기간 감소
    private static final int SELL_IN_2      = 11;       // 10일 부터 퀄리티 2씩 증가
    private static final int SELL_IN_3      = 6;        // 5일 부터 퀄리티 3씩 증가
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_MAX    = 50;       // 최대 퀄리티
    private static final int QUALITY_TIMES  = 1;        // 퀄리티 배율

    private final Item item;

    BackstagePassesStrategyItem(Item item) {
        this.item = item;
    }

    @Override
    public void itemQuality() {
        qualityIncrease();

        if (this.item.sellIn < SELL_IN_2) {
            qualityIncrease();
        }

        if (this.item.sellIn < SELL_IN_3) {
            qualityIncrease();
        }
    }

    @Override
    public void itemSellIn() {
        this.item.sellIn += SELL_IN_DAY;
    }

    @Override
    public void itemSellInLessThanZero() {
        if (this.item.sellIn < SELL_IN_ZERO) {
            this.item.quality = QUALITY_MIN;
        }
    }

    private void qualityIncrease() {
        if (this.item.quality < QUALITY_MAX) {
            this.item.quality += QUALITY_TIMES;
        }
    }
}

class SulfurasStrategyItem implements StrategyItem {

    private final Item item;

    SulfurasStrategyItem(Item item) {
        this.item = item;
    }

    @Override
    public void itemQuality() {
        // 전설템 퀄리티 하락 안함
    }

    @Override
    public void itemSellIn() {
        // 판매될 필요 없음
    }

    @Override
    public void itemSellInLessThanZero() {
        // 전설템 퀄리티 하락 안함
    }
}

class ConjuredStrategyItem implements StrategyItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;        // 남은 기간 감소
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_TIMES  = -2;        // 퀄리티 배율

    private final Item item;

    ConjuredStrategyItem(Item item) {
        this.item = item;
    }

    @Override
    public void itemQuality() {
        qualityDecrease();
    }

    @Override
    public void itemSellIn() {
        this.item.sellIn += SELL_IN_DAY;
    }

    @Override
    public void itemSellInLessThanZero() {
        if (this.item.sellIn < SELL_IN_ZERO) {
            qualityDecrease();
            qualityZero();
        }
    }

    private void qualityDecrease() {
        if (this.item.quality > QUALITY_MIN) {
            this.item.quality += QUALITY_TIMES;
        }
    }

    private void qualityZero() {
        if (this.item.quality < QUALITY_MIN) {
            this.item.quality = QUALITY_MIN;
        }
    }
}
