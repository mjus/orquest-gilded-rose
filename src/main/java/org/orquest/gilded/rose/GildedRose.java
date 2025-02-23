package org.orquest.gilded.rose;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a Gilded Rose, which is responsible for updating the quality of items.
 */
@Getter
@AllArgsConstructor
class GildedRose {

    // Constantes
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final int BACKSTAGE_PASSES_THRESHOLD_1 = 11;
    private static final int BACKSTAGE_PASSES_THRESHOLD_2 = 6;

    /**
     * The array of items to be updated.
     */
    private Item[] items;

    /**
     * Updates the quality of all items in the array.
     */
    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    /**
     * Updates the quality of a single item based on its type.
     *
     * @param item the item to be updated
     */
    private void updateItemQuality(Item item) {
        if (isNormalItem(item)) {
            updateNormalItemQuality(item);
        } else if (isAgedBrie(item)) {
            updateAgedBrieQuality(item);
        } else if (isBackstagePasses(item)) {
            updateBackstagePassesQuality(item);
        } else if (isSulfuras(item)) {
            // No se actualiza la calidad
        }
    }

    /**
     * Checks if an item is a normal item.
     *
     * @param item the item to be checked
     * @return true if the item is a normal item, false otherwise
     */
    private boolean isNormalItem(Item item) {
        return !item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)
                && !item.name.equals(SULFURAS);
    }

    /**
     * Checks if an item is an Aged Brie.
     *
     * @param item the item to be checked
     * @return true if the item is an Aged Brie, false otherwise
     */
    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    /**
     * Checks if an item is a Backstage Passes.
     *
     * @param item the item to be checked
     * @return true if the item is a Backstage Passes, false otherwise
     */
    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    /**
     * Checks if an item is a Sulfuras.
     *
     * @param item the item to be checked
     * @return true if the item is a Sulfuras, false otherwise
     */
    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    /**
     * Updates the quality of a normal item.
     *
     * @param item the item to be updated
     */
    private void updateNormalItemQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - 1;
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            if (item.quality > MIN_QUALITY) {
                item.quality = item.quality - 1;
            }
        }
    }

    /**
     * Updates the quality of an Aged Brie item.
     *
     * @param item the item to be updated
     */
    private void updateAgedBrieQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            if (item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }
    }

    /**
     * Updates the quality of a Backstage Passes item.
     *
     * @param item the item to be updated
     */
    private void updateBackstagePassesQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
            if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_1) {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;
                }
            }
            if (item.sellIn < BACKSTAGE_PASSES_THRESHOLD_2) {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;
                }
            }
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }
}