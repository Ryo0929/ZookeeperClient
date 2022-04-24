import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Brayden
 * @create 4/23/22 1:05 PM
 * @Description
 */
public class LoadTest {
    // GCP load balancer
    ZKManager zk;
    LoadBalancer lb;

    public LoadTest(String ip) throws IOException, InterruptedException {
        BasicConfigurator.configure();
        zk = new ZKManagerImpl(ip);
        lb = new LoadBalancer();
    }

    public void writeTest(int st, int num, int tag) throws KeeperException, InterruptedException {
        ZKManager curr;

        // Get current time
        long start = System.currentTimeMillis();

        for (int i = st; i < st + num; i++) {
            // Schema choose
            curr = lb.chooseMethod(tag);

            String key = String.valueOf(i);
            key = "/" + key;
            String value = Util.getUUID();

            curr.create(key, value.getBytes());


            System.out.println("Set request Num: " + i);
            System.out.println("[" + i + "] " + "Key: " + key);
            System.out.println("[" + i + "] " + "Value: " + value);
        }

        // Get elapsed time in milliseconds
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        // Get elapsed time in seconds
        float elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("The process of " + num + " set() operation takes" + elapsedTimeMillis + " Seconds.");
    }

    public void readTest(int st, int num, int tag) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        ZKManager curr;

        // Get current time
        long start = System.currentTimeMillis();

        for (int i = st; i < st + num; i++) {
            // Schema choose
//            curr = lb.random();
            curr = lb.chooseMethod(tag);

            String key = String.valueOf(i);
            key = "/" + key;

            String result = (String) zk.getZNodeData(key, true);


            System.out.println("Read request Num: " + i);
            System.out.println("[" + i + "] " + "Key: " + key);
            System.out.println("[" + i + "] " + "Value: " + result);
        }

        // Get elapsed time in milliseconds
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        // Get elapsed time in seconds
        float elapsedTimeSec = elapsedTimeMillis/1000F;
        System.out.println("The process of " + num + " set() operation takes " + elapsedTimeSec+ " Seconds.");
    }

    public static void main(String args[]) throws IOException, InterruptedException, KeeperException {
        LoadTest test = new LoadTest("34.106.179.93");

//        test.writeTest(65, 20);
        test.readTest(65, 20, 1);
//        test.readTest(0, 5);
    }
}
