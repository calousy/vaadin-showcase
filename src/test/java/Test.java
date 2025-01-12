import org.apache.commons.lang3.RegExUtils;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    @org.junit.Test
    public void name() {
        String text = "000-1111-222-33";

        Pattern pattern = Pattern.compile("(?<a>\\d+)-(?<b>\\d+)-(?<c>\\d+)-(?<d>\\d+)");

        Matcher matcher = pattern.matcher(text);
        matcher.matches();
        String group = matcher.group("a");
        matcher.matches();
        MatchResult list = pattern.matcher(text).toMatchResult();

        String s = RegExUtils.replacePattern("Lorem ipsum  dolor   sit",
                "( +)([a-z]+)", "_$2");
        int i = matcher.groupCount();


        int a = 0;
    }
}
