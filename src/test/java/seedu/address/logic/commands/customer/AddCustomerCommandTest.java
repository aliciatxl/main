package seedu.address.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InventorySystem;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInventorySystem;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.product.Product;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.PersonBuilder;

public class AddCustomerCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCustomerCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Customer validCustomer = new PersonBuilder().build();

        CommandResult commandResult = new AddCustomerCommand(validCustomer).execute(modelStub);

        assertEquals(String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer validCustomer = new PersonBuilder().build();
        AddCustomerCommand addCustomerCommand = new AddCustomerCommand(validCustomer);
        ModelStub modelStub = new ModelStubWithPerson(validCustomer);

        assertThrows(CommandException.class,
                AddCustomerCommand.MESSAGE_DUPLICATE_PERSON, () -> addCustomerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Customer alice = new PersonBuilder().withName("Alice").build();
        Customer bob = new PersonBuilder().withName("Bob").build();
        AddCustomerCommand addAliceCommand = new AddCustomerCommand(alice);
        AddCustomerCommand addBobCommand = new AddCustomerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCustomerCommand addAliceCommandCopy = new AddCustomerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different customer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {

        }

        @Override
        public void setAddressBook(ReadOnlyInventorySystem newData, String commandWord) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInventorySystem getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Customer target, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product editedProduct) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredCustomerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTransaction(Transaction transaction) {
            return false;
        }

        @Override
        public void addTransaction(Transaction transaction) {

        }

        @Override
        public void updateFilteredCustomerList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionList(Predicate<Transaction> predicate) {

        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Transaction> getFilteredTransactionList() {
            return null;
        }
    }

    /**
     * A Model stub that contains a single customer.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Customer customer;

        ModelStubWithPerson(Customer customer) {
            requireNonNull(customer);
            this.customer = customer;
        }

        @Override
        public boolean hasPerson(Customer customer) {
            requireNonNull(customer);
            return this.customer.isSamePerson(customer);
        }
    }

    /**
     * A Model stub that always accept the customer being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Customer> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Customer customer) {
            requireNonNull(customer);
            return personsAdded.stream().anyMatch(customer::isSamePerson);
        }

        @Override
        public void addPerson(Customer customer) {
            requireNonNull(customer);
            personsAdded.add(customer);
        }

        @Override
        public ReadOnlyInventorySystem getAddressBook() {
            return new InventorySystem();
        }
    }

}
