import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTimeFormatter {

    public static void convertMatchTimeFormat(String inputMatchTime) {
        String pattern = "^\\[(PM|FT|HT|H1|H2)\\]\\040([0-9]{1}|[0-9]{2}):([0-5]\\d)\\.([0-9]{3})$";

        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(inputMatchTime);

        if (matcher.find( )) {
            System.out.println("Old format: " + matcher.group(0) );
            System.out.println("Long Form: " + matcher.group(1) );
            System.out.println("Min: " + matcher.group(2) );
            System.out.println("Sec: " + matcher.group(3) );
            System.out.println("Millisec: " + matcher.group(4) );
        }else {
            System.out.println("INVALID");
        }
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
        examples.add("[90:00");
        examples.add("[H3] 90:00.000");
        examples.add("[PM] -10:00.000");
        examples.add("FOO");

        for (String example: examples) {
            System.out.println("Original format: ");
            convertMatchTimeFormat(example);
        }
    }
}
