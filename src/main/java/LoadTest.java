import java.util.UUID;
/**
 * @author Brayden
 * @create 4/23/22 1:05 PM
 * @Description
 */
public class LoadTest {
    public static void readTest(int num) {
        for (int i = 0; i < num; i++) {
            String key = Util.getUUID();
            String value = Util.getUUID();
            System.out.println(key);
            System.out.println(value);
        }
    }
    public static void read() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        readTest(5);
    }
}
