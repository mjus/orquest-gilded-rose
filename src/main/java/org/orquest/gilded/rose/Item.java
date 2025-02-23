/**
 * Represents an item in the Gilded Rose system.
 *
 * @author Iuliia Malykh
 */
package org.orquest.gilded.rose;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Class that represents an item with its basic properties.
 *
 * This class is used to store information about an item, including its name, sellIn date, and quality.
 */
@AllArgsConstructor
@ToString
public class Item {

    /**
     * The name of the item.
     */
    public String name;

    /**
     * The number of days left before the item is sold.
     *
     * This value decreases by 1 each day.
     */
    public int sellIn;

    /**
     * The quality of the item, which can vary over time.
     *
     * The quality of an item can increase or decrease depending on its type and age.
     */
    public int quality;

}
