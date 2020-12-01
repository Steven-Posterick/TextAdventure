package HelperClasses;

public class StringHelper {

    // Private constructor as there is only static methods.
    private StringHelper(){}

    // Formats the name of an enum to a more readable form, ie WEATHER to Weather.
    public static String formatEnumName(String name){
        if (name.contains("_")){
            // This is a bit over what it needs to be for this enum, but helpful for scaling.
            StringBuilder sb = new StringBuilder();
            String[] splitName = name.split("_");

            for (int i = 0; i < splitName.length; i++) {
                // Append the first upper case character and the rest of the lowercase string.
                String portion = splitName[i];

                if (portion.equalsIgnoreCase("to") || portion.equalsIgnoreCase("at")){
                    // Keep convert "to" and "at" to lowercase.
                    sb.append(portion.toLowerCase());
                } else {
                    // Otherwise keep this format "FORMAT" -> "Format"
                    sb.append(portion.charAt(0));
                    sb.append(portion.substring(1).toLowerCase());
                }

                // Add space if not the end.
                if (i != splitName.length - 1){
                    sb.append(" ");
                }
            }

            return sb.toString();
        } else {
            return name.charAt(0) + name.substring(1).toLowerCase();
        }
    }
}
