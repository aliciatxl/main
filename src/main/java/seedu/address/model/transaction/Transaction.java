package seedu.address.model.transaction;

import java.util.UUID;

import seedu.address.model.customer.Customer;
import seedu.address.model.product.Product;
import seedu.address.model.util.Description;
import seedu.address.model.util.Money;
import seedu.address.model.util.Quantity;

/**
 * Represents a Transaction in the system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {

    private final Customer customer;
    private final Product product;
    private final UUID productId;
    private final DateTime dateTime;
    private final Quantity quantity;
    private final Money money;
    private final Description description;


    public Transaction(Customer customer, Product product, UUID productId, DateTime dateTime,
                       Quantity quantity, Money money, Description description) {
        this.customer = customer;
        this.product = product;
        this.productId = productId;
        this.quantity = quantity;
        this.money = money;
        this.dateTime = dateTime;
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public UUID getProductId() {
        return productId;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getMoney() {
        return money;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if any two transactions have the same customer, products, and dateTime.
     * This defines a weaker notion of equality.
     * @param otherTransaction other transaction.
     * @return true if these two transactions have the same customer, products, and dateTime.
     */
    public boolean isSameTransaction(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction.getCustomer().equals(getCustomer())
                && otherTransaction.getProduct().equals(getProduct())
                && otherTransaction.getProductId().equals(getProductId())
                && otherTransaction.getDateTime().equals(getDateTime())
                && otherTransaction.getQuantity().equals(getQuantity());

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getCustomer().equals(getCustomer())
                && otherTransaction.getProduct().equals(getProduct())
                && otherTransaction.getProductId().equals(getProductId())
                && otherTransaction.getDateTime().equals(getDateTime())
                && otherTransaction.getQuantity().equals(getQuantity());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCustomer().getName())
                .append(" bought ")
                .append(getProduct().getDescription())
                .append("\nDate/ time: ")
                .append(getDateTime())
                .append(" Quantity: ")
                .append(getQuantity())
                .append(" Amount: ")
                .append(getMoney());
        return builder.toString();
    }
}
