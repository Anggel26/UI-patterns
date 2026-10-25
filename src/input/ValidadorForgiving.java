package input;

public class ValidadorForgiving {

    // Clean and normalize phone numbers into a standard 10 digit string
    public String normalizePhone(String input) {
        if (input == null) {
            return "";
        }
        // Remove spaces, parentheses, dashes, plus signs, and dots
        String cleaned = input.replaceAll("[^0-9]", "");
        // Strip Colombian international code 57 if present at the start of a 12 digit number
        if (cleaned.startsWith("57") && cleaned.length() == 12) {
            cleaned = cleaned.substring(2);
        }
        return cleaned;
    }

    // Clean and normalize date input into standard YYYY-MM-DD format
    public String normalizeDate(String input) {
        if (input == null) {
            return "";
        }
        // Trim surrounding whitespaces
        String cleaned = input.trim();
        // Replace slashes and dots with hyphens
        cleaned = cleaned.replaceAll("[/.\\s]+", "-");
        // Convert DD-MM-YYYY to YYYY-MM-DD if day appears first
        if (cleaned.matches("^\\d{1,2}-\\d{1,2}-\\d{4}$")) {
            String[] parts = cleaned.split("-");
            String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
            String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
            String year = parts[2];
            return year + "-" + month + "-" + day;
        }
        // Normalize YYYY-MM-DD padding if single digits were provided
        if (cleaned.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            String[] parts = cleaned.split("-");
            String year = parts[0];
            String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
            String day = parts[2].length() == 1 ? "0" + parts[2] : parts[2];
            return year + "-" + month + "-" + day;
        }
        return cleaned;
    }

    // Normalize email input by trimming whitespaces and converting to lowercase
    public String normalizeEmail(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().toLowerCase();
    }
}
