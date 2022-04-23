import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.Random;

/**
 * @author Brayden
 * @create 4/23/22 2:43 PM
 * @Description
 */
public class LoadBalancer {
    ZKManager[] zkList;
    String[] ip = {
            "34.106.20.60",
            "34.106.3.246",
            "34.125.251.158",
            "34.106.148.135",
    };


    public LoadBalancer() throws IOException, InterruptedException {
        BasicConfigurator.configure();
        zkList = new ZKManager[ip.length];
        int idx = 0;
        for (String addr : ip) {
            ZKManager zk = new ZKManagerImpl(addr);
            zkList[idx] = (zk);
            idx++;
        }
    }


    public ZKManager random() {
        Random random = new Random();
        int curr = random.nextInt(ip.length);
        System.out.println(curr);
        return zkList[curr];
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 0; i < 20; i++) {
            lb.random();
        }
    }
}
