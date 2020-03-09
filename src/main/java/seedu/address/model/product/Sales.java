package seedu.address.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Product's price in the product list
 * Guarantees: immutable; is valid as declared in {@link #isValidSales(String)}
 */
public class Sales {

    public static final String MESSAGE_CONSTRAINTS = "Quantity can take any numeric values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param sales A valid address.
     */
    public Sales(String sales) {
        requireNonNull(sales);
        checkArgument(isValidSales(sales), MESSAGE_CONSTRAINTS);
        value = sales;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidSales(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sales // instanceof handles nulls
                && value.equals(((Sales) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
