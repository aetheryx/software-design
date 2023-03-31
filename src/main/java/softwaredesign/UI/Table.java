package softwaredesign.UI;

import java.util.*;


/**
 * <p>
 *     A utility class that can be used to easily generate ASCII tables.
 *      The class is constructed with a list of table headers.
 * </p>
 * @author Ammar
 */


public class Table {
    private final List<String> headers;
    private final List<List<String>> values;

    public Table(String... inHeaders) {
        this.headers = Arrays.asList(inHeaders);
        this.values = new ArrayList<>();
    }

    /**
     * <p>
     *     addEntry() method takes a variable amount of arguments, the
     *      amount of arguments should be the length of the table header.
     * </p>
     * @author Ammar
     */
    public void addEntry(String... entry) {
        if (entry.length != headers.size()) {
            throw new IllegalArgumentException("Entry length should match header length");
        }
        values.add(Arrays.asList(entry));
    }

    /**
     * <p>
     *     calcColWidth() is a method that will calculate the maximum width of the strings to
     *     make the table as organized as possible.
     * </p>
     * @author Ammar
     *
     */
    private int[] calcColWidth() {
        int[] colWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            int maxWidth = headers.get(i).length();
            for (List<String> row : values) {
                int entryWidth = row.get(i).length();
                if (entryWidth > maxWidth) {
                    maxWidth = entryWidth;
                }
            }
            colWidths[i] = maxWidth;
        }
        return colWidths;
    }

    /**
     * <p>
     *     toString() method can be called to generate an
     *      ASCII table.
     * </p>
     * @author Ammar
     *
     */
    public String toString() {
        StringBuilder tableString = new StringBuilder();
        int[] columnWidths = calcColWidth();

        for (int width : columnWidths) {
            tableString.append("*");
            tableString.append("=".repeat(width));
        }

        tableString.append("*");
        tableString.append("\n");


        tableString.append("|");
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            tableString.append(header);
            tableString.append(" ".repeat(columnWidths[i] - header.length()));
            tableString.append("|");
        }
        tableString.append("\n");


        tableString.append("*");
        for (int i = 0; i < headers.size(); i++) {
            tableString.append("=".repeat(columnWidths[i]));
            tableString.append("*");
        }
        tableString.append("\n");


        for (List<String> row : values) {
            tableString.append("|");
            for (int i = 0; i < row.size(); i++) {
                String entry = row.get(i);
                tableString.append(entry);
                tableString.append(" ".repeat(columnWidths[i] - entry.length()));
                tableString.append("|");
            }
            tableString.append("\n");
        }

        tableString.append("+");
        for (int i = 0; i < headers.size(); i++) {
            tableString.append("-".repeat(columnWidths[i]));
            tableString.append("*");
        }
        tableString.append("\n");

        return tableString.toString();
    }
}