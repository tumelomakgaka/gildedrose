package com.gildedrose;

import static org.hamcrest.core.Is.is;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GildedRoseTest {



    @Test
    // At the end of each day our system lowers both values for every item
    public void LowerBothValuesForEveryItemPerDay() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("foo 1", 2, 2),
                        new Item("foo 2", 2, 3)
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat( items[0].sellIn, is(1) );
        assertThat( items[0].quality, is(1) );
        assertThat( items[1].sellIn, is(1) );
        assertThat( items[1].quality, is(2) );
    }

    //Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void DegradesQualityTwiceAsFastWhenSellByPasses() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("foo 1", 2, 10),
                        new Item("foo 2", 1, 20)
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].quality, is(6) );
        assertThat( items[1].quality, is(15) );
    }
    //The Quality of an item is never negative
    @Test (expected = QualityMustNotBeLessThanZeroException.class)
    public void QualityInputMayNeverBeNegative()  throws Exception {
        Item[] items = new Item[]
                {
                        new Item("foo 1", 2, -1)
                };

        GildedRose app = new GildedRose(items);
    }
    //The Quality of an item is never negative
    @Test public void QualityMayNeverBeNegative()  throws Exception {
        Item[] items = new Item[]
                {
                        new Item("foo 1", 2, 2),
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].quality, is(0) );
    }

    //"Aged Brie" actually increases in Quality the older it gets
    @Test public void AgedBrieIncreasesInQualityWithTime() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("Aged Brie", 2, 1),
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].quality, is(5) );
    }



    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test public void SulfurasNeverDecreaseInQuality() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("Sulfuras, Hand of Ragnaros", 2, 2),
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].quality, is(2) );
    }

    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test public void SulfurasNeverHasToBeSold() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("Sulfuras, Hand of Ragnaros", 1, 2),
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].sellIn, is(1) );
    }


    // "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches; Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but	Quality drops to 0 after the concert
    //"Aged Brie" actually increases in Quality the older it gets
    @Test public void BackstagePassesIncreasesInQualityWithTime() throws Exception {
        Item[] items = new Item[]
                {
                        new Item("Backstage passes to a TAFKAL80ETC concert", 20, 2),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 9, 2),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 4, 2),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 3, 2),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 2, 2),
                };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat( items[0].quality, is(5) );
        assertThat( items[1].quality, is(8) );
        assertThat( items[2].quality, is(11) );
        assertThat( items[3].quality, is(11) );
        assertThat( items[4].quality, is(0) );
    }

//    //"Conjured" items degrade in Quality twice as fast as normal items
//    @Test public void ConjuredItemsDecreaseInQualityWithTime() throws Exception {
//        Item[] items = new Item[]
//                {
//                        new Item("Conjured Items", 2, 4),
//                };
//
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        app.updateQuality();
//
//        assertThat( items[0].quality, is(0) );
//    }
}
