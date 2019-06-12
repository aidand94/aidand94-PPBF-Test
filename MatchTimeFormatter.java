import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTimeFormatter {

    public static String convertMatchTimeFormat(String inputMatchTime) {
        // Regex to split input String in base components periodShortForm, minutes, seconds, milliseconds
        String pattern = "^\\[(PM|FT|HT|H1|H2)\\]\\040([0-9]{1}|[0-9]{2}|[0-9]{3}):([0-5]\\d)\\.([0-9]{3})$";

        // Pattern and Matcher for running regex on input string
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(inputMatchTime);

        //Check if the Regex matches the entire input String
        if (matcher.matches()) {
            // Parse groups returned from Regex and convert to int
            String periodShortForm = matcher.group(1).toString();
            int minutes = Integer.parseInt(matcher.group(2).toString());
            int seconds = Integer.parseInt(matcher.group(3).toString());
            int milliseconds = Integer.parseInt(matcher.group(4).toString());
            int addedTimeMinutes;
            int addedTimeSeconds;

            if (milliseconds >= 500) {
                seconds++;
            }

            if(seconds >= 60) {
                minutes++;
                seconds = 60 - seconds;
            }

            // Check if first half has added time and format correspondingly
            if(((minutes >= 45) && ((seconds + milliseconds) > 0)) && (periodShortForm.equals("H1"))) {
                addedTimeMinutes = (minutes - 45);
                addedTimeSeconds = seconds;
                minutes = 45;
                seconds = 0;
                int[] times = {minutes,seconds,addedTimeMinutes,addedTimeSeconds};
                return formatMatchTime(times, periodShortForm);
            }

            // Check if second half has added time and format correspondingly
            if(((minutes >= 90) && ((seconds + milliseconds) > 0)) && (periodShortForm.equals("H2"))) {
                addedTimeMinutes = (minutes - 90);
                addedTimeSeconds = seconds;
                minutes = 90;
                seconds = 0;
                int[] times = {minutes,seconds,addedTimeMinutes,addedTimeSeconds};
                return formatMatchTime(times, periodShortForm);
            }

            // Default format for all other match times
            int[] times = {minutes,seconds};
            return formatMatchTime(times, periodShortForm);

        } else {
            // Input String was not a in a valid format
            return "INVALID";
        }
    }

    public static String formatMatchTime(int[] times, String period) {
        ArrayList<String> formattedTimeStrings = new ArrayList<String>();
        StringBuilder formattedMatchTime = new StringBuilder();

        // Pad all the times
        for (int i=0; i<times.length; i++) {
            formattedTimeStrings.add(String.format("%02d",times[i]));
        }

        formattedMatchTime.append(formattedTimeStrings.get(0) + ":" + formattedTimeStrings.get(1)).toString();

        // Check to see if the format requires added time
        if (times.length > 2) {
            formattedMatchTime.append(" +" + formattedTimeStrings.get(2) + ":" + formattedTimeStrings.get(3));
        }

        formattedMatchTime.append(" - ");

        switch (period) {
            case "PM":
                formattedMatchTime.append("PRE_MATCH");
                break;
            case "H1":
                formattedMatchTime.append("FIRST_HALF");
                break;
            case "HT":
                formattedMatchTime.append("HALF_TIME");
                break;
            case "H2":
                formattedMatchTime.append("SECOND_HALF");
                break;
            case "FT":
                formattedMatchTime.append("FULL_TIME");
                break;

        }

        return formattedMatchTime.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> examples = new ArrayList<>();

        examples.add("[PM] 0:00.000");
        examples.add("[H1] 0:15.025");
        examples.add("[H1] 3:07.513");
        examples.add("[H1] 45:00.001");
        examples.add("[H1] 46:15.752");
        examples.add("[HT] 45:00.000");
        examples.add("[H2] 45:00.500");
        examples.add("[H2] 90:00.908");
        examples.add("[FT] 90:00.000");
        examples.add("90:00");
        examples.add("[H3] 90:00.000");
        examples.add("[PM] -10:00.000");
        examples.add("FOO");
        examples.add("[H1] 45:59.501");
        examples.add("[H2] 108:59.501");

        for (String example: examples) {
            System.out.println("---------------------");
            System.out.println("Original format: " + example);
            System.out.println("New format: " + convertMatchTimeFormat(example));
            System.out.println("---------------------");
        }
    }
}
