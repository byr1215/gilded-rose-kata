package com.gildedrose;

public abstract class AbstractItem {
    protected int SELL_IN_ZERO;      // 판매 마감일
    protected int SELL_IN_DAY;       // 남은 기간 감소
    protected int QUALITY_MIN;       // 최소 퀄리티
    protected int QUALITY_MAX;       // 최대 퀄리티
    protected int QUALITY_TIMES;     // 퀄리티 배율
    abstract public void setting();

    abstract public void itemQuality(Item item);
    abstract public void itemSellIn(Item item);
    abstract public void itemSellInLessThanZero(Item item);
}

class BasicAbstractItem extends AbstractItem {

    @Override
    public void setting() {

    }

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
