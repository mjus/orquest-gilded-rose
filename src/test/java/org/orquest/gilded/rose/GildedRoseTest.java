package org.orquest.gilded.rose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class GildedRoseTest {

    private GildedRose gildedRose;

    @BeforeEach
    public void setUp () {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 0, 8), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20),
                new Item("Conjured Mana Cake", 3, 6),
                new Item("Conjured Mana Cake", 0, 6)
        };
        gildedRose = new GildedRose(items);
    }

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.getItems()[0].name);
    }

    /**
     * Test that verifies the updateNormalItemQuality method throws a NullPointerException
     * when a null item is passed.
     */
    @Test
    public void testUpdateNormalItemQuality_NullItem_ThrowsNullPointerException() {
        try {
            gildedRose.testUpdateNormalItemQuality(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            assertEquals("Item cannot be null", e.getMessage());
        }
    }

    /**
     * Test that verifies the updateNormalItemQuality method does not change the quality
     * when the sellIn date is positive and the quality is already at the minimum.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInPositive_QualityAtMinQuality_NoChange() {
        Item item = new Item("Test Item", 10, 0);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(0, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateNormalItemQuality method decreases the quality by 1
     * when the sellIn date is positive.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInPositive_QualityDecreasesBy1() {
        Item item = new Item("Test Item", 10, 10);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(9, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateNormalItemQuality method decreases the quality by 2
     * when the sellIn date is zero.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInZero_QualityDecreasesBy2() {
        Item item = new Item("Test Item", 0, 10);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(8, item.quality);
        assertEquals(-1, item.sellIn);
    }

    /**
     * Test that verifies the updateNormalItemQuality method does not change the quality
     * when the sellIn date is zero and the quality is already at the minimum.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInZero_QualityAtMinQuality_NoChange() {
        Item item = new Item("Test Item", 0, 0);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(0, item.quality);
        assertEquals(-1, item.sellIn);
    }

    /**
     * Test that verifies the updateNormalItemQuality method decreases the quality by 2
     * when the sellIn date is negative.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInNegative_QualityDecreasesBy2() {
        Item item = new Item("Test Item", -1, 10);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(8, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateNormalItemQuality method does not change the quality
     * when the sellIn date is negative and the quality is already at the minimum.
     */
    @Test
    public void testUpdateNormalItemQuality_SellInNegative_QualityAtMinQuality_NoChange() {
        Item item = new Item("Test Item", -1, 0);
        gildedRose.testUpdateNormalItemQuality(item);
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateAgedBrieQuality method increases the quality by 1
     * when the sellIn date is positive.
     */
    @Test
    public void testUpdateAgedBrieQuality_SellInPositive_QualityIncreasesBy1() {
        Item item = new Item("Aged Brie", 10, 10);
        gildedRose.testUpdateAgedBrieQuality(item);
        assertEquals(11, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateAgedBrieQuality method increases the quality by 2
     * when the sellIn date is zero or negative.
     */
    @Test
    public void testUpdateAgedBrieQuality_SellInZeroOrNegative_QualityIncreasesBy2() {
        Item item = new Item("Aged Brie", 0, 10);
        gildedRose.testUpdateAgedBrieQuality(item);
        assertEquals(12, item.quality);
        assertEquals(-1, item.sellIn);
    }

    /**
     * Test that verifies the updateAgedBrieQuality method does not increase the quality
     * beyond the maximum quality of 50.
     */
    @Test
    public void testUpdateAgedBrieQuality_QualityAtMax_QualityDoesNotIncrease() {
        Item item = new Item("Aged Brie", 10, 50);
        gildedRose.testUpdateAgedBrieQuality(item);
        assertEquals(50, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateAgedBrieQuality method increases the quality by 2
     * when the sellIn date is negative.
     */
    @Test
    public void testUpdateAgedBrieQuality_SellInNegative_QualityIncreasesBy2() {
        Item item = new Item("Aged Brie", -1, 10);
        gildedRose.testUpdateAgedBrieQuality(item);
        assertEquals(12, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateAgedBrieQuality method does not increase the quality
     * beyond the maximum quality of 50 when the sellIn date is negative.
     */
    @Test
    public void testUpdateAgedBrieQuality_SellInNegative_QualityAtMax_QualityDoesNotIncrease() {
        Item item = new Item("Aged Brie", -1, 50);
        gildedRose.testUpdateAgedBrieQuality(item);
        assertEquals(50, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateBackstagePassesQuality method increases the quality by 1
     * when the sellIn date is more than 10 days away.
     */
    @Test
    public void testUpdateBackstagePassesQuality_SellInMoreThan10Days_QualityIncreasesBy1() {
        Item item = new Item("Backstage Passes", 15, 10);
        gildedRose.testUpdateBackstagePassesQuality(item);
        assertEquals(11, item.quality);
        assertEquals(14, item.sellIn);
    }

    /**
     * Test that verifies the updateBackstagePassesQuality method increases the quality by 2
     * when the sellIn date is between 5 and 10 days away.
     */
    @Test
    public void testUpdateBackstagePassesQuality_SellInBetween5And10Days_QualityIncreasesBy2() {
        Item item = new Item("Backstage Passes", 7, 10);
        gildedRose.testUpdateBackstagePassesQuality(item);
        assertEquals(12, item.quality);
        assertEquals(6, item.sellIn);
    }

    /**
     * Test that verifies the updateBackstagePassesQuality method increases the quality by 3
     * when the sellIn date is 5 days or less away.
     */
    @Test
    public void testUpdateBackstagePassesQuality_SellIn5DaysOrLess_QualityIncreasesBy3() {
        Item item = new Item("Backstage Passes", 3, 10);
        gildedRose.testUpdateBackstagePassesQuality(item);
        assertEquals(13, item.quality);
        assertEquals(2, item.sellIn);
    }

    /**
     * Test that verifies the updateBackstagePassesQuality method sets the quality to 0
     * when the sellIn date has passed.
     */
    @Test
    public void testUpdateBackstagePassesQuality_SellInHasPassed_QualitySetsTo0() {
        Item item = new Item("Backstage Passes", -1, 10);
        gildedRose.testUpdateBackstagePassesQuality(item);
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateBackstagePassesQuality method does not increase the quality
     * beyond the maximum quality of 50.
     */
    @Test
    public void testUpdateBackstagePassesQuality_QualityAtMax_QualityDoesNotIncrease() {
        Item item = new Item("Backstage Passes", 10, 50);
        gildedRose.testUpdateBackstagePassesQuality(item);
        assertEquals(50, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateConjuredQuality method decreases the quality by 2
     * when the sellIn date is positive.
     */
    @Test
    public void testUpdateConjuredQuality_SellInPositive_QualityDecreasesBy2() {
        Item item = new Item("Conjured Item", 10, 10);
        gildedRose.testUpdateConjuredQuality(item);
        assertEquals(8, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateConjuredQuality method decreases the quality by 4
     * when the sellIn date is negative.
     */
    @Test
    public void testUpdateConjuredQuality_SellInNegative_QualityDecreasesBy4() {
        Item item = new Item("Conjured Item", -1, 10);
        gildedRose.testUpdateConjuredQuality(item);
        assertEquals(6, item.quality);
        assertEquals(-2, item.sellIn);
    }

    /**
     * Test that verifies the updateConjuredQuality method does not decrease the quality
     * below the minimum quality of 0.
     */
    @Test
    public void testUpdateConjuredQuality_QualityAtMin_QualityDoesNotDecrease() {
        Item item = new Item("Conjured Item", 10, 0);
        gildedRose.testUpdateConjuredQuality(item);
        assertEquals(0, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateConjuredQuality method decreases the quality by 2
     * when the sellIn date is positive and the quality is greater than the minimum.
     */
    @Test
    public void testUpdateConjuredQuality_SellInPositive_QualityGreaterThanMin_QualityDecreasesBy2() {
        Item item = new Item("Conjured Item", 10, 5);
        gildedRose.testUpdateConjuredQuality(item);
        assertEquals(3, item.quality);
        assertEquals(9, item.sellIn);
    }

    /**
     * Test that verifies the updateConjuredQuality method decreases the quality by 4
     * when the sellIn date is negative and the quality is greater than the minimum.
     */
    @Test
    public void testUpdateConjuredQuality_SellInNegative_QualityGreaterThanMin_QualityDecreasesBy4() {
        Item item = new Item("Conjured Item", -1, 5);
        gildedRose.testUpdateConjuredQuality(item);
        assertEquals(1, item.quality);
        assertEquals(-2, item.sellIn);
    }

}
