package seedu.address.logic.parser.statistics;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.statistics.ProfitCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.DateTime;

/**
 * Parses input arguments and creates a new ProfitCommand object
 */
public class ProfitCommandParser implements Parser<ProfitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ProfitCommand
     * and returns an ProfitCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ProfitCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfitCommand.MESSAGE_USAGE));
        }

        DateTime startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE).get());
        DateTime endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE).get());

        return new ProfitCommand(startDateTime, endDateTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> {
            try {
                return argumentMultimap.getValue(prefix).isPresent();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

}
