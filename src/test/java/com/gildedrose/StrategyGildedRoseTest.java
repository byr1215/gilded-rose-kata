package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("테스트")
class StrategyGildedRoseTest {
    Item[] items = new Item[] {
        new Item("+5 Dexterity Vest", 10, 20), //
        new Item("Aged Brie", 2, 0), //
        new Item("Elixir of the Mongoose", 5, 7), //
        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        // this conjured item does not work properly yet
        new Item("Conjured Mana Cake", 3, 6),
        new Item("Conjured", 3, 5) };

    @Test
    void foo() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @DisplayName("판매일수_없으면_퀄리티_2배_하락")
    @Test
    void 판매일수_없으면_퀄리티_2배_하락() {
        FactoryGildedRose factoryGildedRose = new FactoryGildedRose(items);


    }

    @DisplayName("퀄리티_음수_불가")
    @Test
    void 퀄리티_음수_불가() {

    }

    @DisplayName("오래된_브리치즈_시간이_지날수록_퀄리티_증가")
    @Test
    void 오래된_브리치즈_시간이_지날수록_퀄리티_증가() {

    }

    @DisplayName("퀄리티_50_초과_불가")
    @Test
    void 퀄리티_50_초과_불가() {

    }

    @DisplayName("전설아이템_SULFURAS_퀄리티_하락_안함")
    @Test
    void 전설아이템_SULFURAS_퀄리티_하락_안함() {

    }

    @DisplayName("백스테이지_입장권_판매기간에_따른_퀄리티_상승")
    @Nested
    class 백스테이지_입장권_판매기간에_따른_퀄리티_상승 {
        @DisplayName("매일_2씩_퀄리티_상승_10일부터")
        @Test
        void 매일_2씩_퀄리티_상승_10일부터() {

        }

        @DisplayName("매일_3씩_퀄리티_상승_5일부터")
        @Test
        void 매일_3씩_퀄리티_상승_5일부터() {

        }

        @DisplayName("콘서트_종료_퀄리티_0")
        @Test
        void 콘서트_종료_퀄리티_0() {

        }
    }

}
