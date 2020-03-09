package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRODUCTS;

import seedu.address.model.Model;

/**
 * Lists all products in the product list to the user.
 */
public class ListProductCommand extends Command {

    public static final String COMMAND_WORD = "listp";

    public static final String MESSAGE_SUCCESS = "Listed all products";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProductList(PREDICATE_SHOW_ALL_PRODUCTS);
        System.out.println("List " + model.getFilteredProductList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
