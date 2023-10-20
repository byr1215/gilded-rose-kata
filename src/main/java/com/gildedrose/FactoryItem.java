package com.gildedrose;

/**
 * 팩토리 패턴
 * 장점
 *  - 팩토리 패턴은
 *    클라이언트 코드로부터 서브 클래스의 인스턴스화를 제거하여 서로 간의 종속성을 낮추고,
 *    결합도를 느슨하게 하며(Loosely Coupled), 확장을 쉽게 한다.
 *  - 팩토리 패턴은 클라이언트와 구현 객체들 사이에 추상화를 제공한다.
 * 단점
 *  - 새로 생성할 객체가 늘어날 때마다, Factory 클래스에 추가해야 되기 때문에 클래스가 많아짐.
 */

public interface FactoryItem {
    void itemQuality();
    void itemSellIn();
    void itemSellInLessThanZero();
}

// 기본 아이템
class BasicFactoryItem implements FactoryItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;       // 남은 기간 감소
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_TIMES  = -1;       // 퀄리티 배율

    private final Item item;

    BasicFactoryItem(Item item) {
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
class AgedBrieFactoryItem implements FactoryItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;       // 남은 기간 감소
    private static final int QUALITY_MAX    = 50;       // 최대 퀄리티
    private static final int QUALITY_TIMES  = 1;        // 퀄리티 배율

    private final Item item;

    AgedBrieFactoryItem(Item item) {
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

class BackstagePassesFactoryItem implements FactoryItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;        // 남은 기간 감소
    private static final int SELL_IN_2      = 11;       // 10일 부터 퀄리티 2씩 증가
    private static final int SELL_IN_3      = 6;        // 5일 부터 퀄리티 3씩 증가
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_MAX    = 50;       // 최대 퀄리티
    private static final int QUALITY_TIMES  = 1;        // 퀄리티 배율

    private final Item item;

    BackstagePassesFactoryItem(Item item) {
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

class SulfurasFactoryItem implements FactoryItem {

    private final Item item;

    SulfurasFactoryItem(Item item) {
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

class ConjuredFactoryItem implements FactoryItem {

    private static final int SELL_IN_ZERO   = 0;        // 판매 마감일
    private static final int SELL_IN_DAY    = -1;        // 남은 기간 감소
    private static final int QUALITY_MIN    = 0;        // 최소 퀄리티
    private static final int QUALITY_TIMES  = -2;        // 퀄리티 배율

    private final Item item;

    ConjuredFactoryItem(Item item) {
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

class Factory {
    public static FactoryItem getInstance(Item item) {
        switch (ManagementItem.findByManagementItem(item.name)) {
            case AGED_BRIE: return new AgedBrieFactoryItem(item);
            case BACKSTAGE_PASSES: return new BackstagePassesFactoryItem(item);
            case SULFURAS: return new SulfurasFactoryItem(item);
            case CONJURED: return new ConjuredFactoryItem(item);
            default: return new BasicFactoryItem(item);
        }
    }
}
