package io.swagger.helper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IbanTest {

    public static boolean IbanTest( String iban ) {
        // String to be scanned to find the pattern.
        String pattern = "^\\bNL[0-9]{2}INHO0[0-9]{9}$";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(iban);
        return m.find();
    }
}
