package sense.nl.eevee.util;

import java.util.regex.Pattern;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

public class EeveeUtil {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    public static boolean checkEmail(String email){
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

}
