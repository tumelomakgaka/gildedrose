package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) throws QualityMustNotBeLessThanZeroException {
        for (Item item : items) {
            if ( item.quality < 0 ) throw new QualityMustNotBeLessThanZeroException();
        }
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items)
        {
            if (!item.name.equals("Aged Brie")&& !item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
            {
                if (item.quality > 0)
                {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                    {
                        item.quality --;
                    }
                }
            }
            else
                {
                if (item.quality < 50)
                {
                    item.quality++;
                    //Is a Backstage pass
                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality ++;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality ++;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
            {
                item.sellIn --;
            }

            if (item.sellIn < 0)
            {
                if (!item.name.equals("Aged Brie"))
                {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (item.quality > 0)
                        {
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                            {
                                item.quality --;
                            }
                        }
                    }
                    else
                        {
                        item.quality = 0;
                    }
                } else
                    {
                    if (item.quality < 50)
                    {
                        item.quality ++;
                    }
                }
            }

        }
    }
}