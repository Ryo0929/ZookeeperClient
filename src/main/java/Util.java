import java.util.UUID;

/**
 * @author Brayden
 * @create 4/23/22 1:21 PM
 * @Description
 */
public class Util {
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String uuid_s =
                (uuid.substring(0,8))    +
                        (uuid.substring(9,13))   +
                        (uuid.substring(14,18))  +
                        (uuid.substring(19,23))  +
                        (uuid.substring(24,36));
        return uuid_s;

    }
}
