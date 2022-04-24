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

    int[] weight = {3, 7, 1, 5};
    int[] cumulativeW;
    int totalWeight = 0;
    Random random;


    public LoadBalancer() throws IOException, InterruptedException {
        BasicConfigurator.configure();
        zkList = new ZKManager[ip.length];
        random = new Random();
        int idx = 0;
        for (String addr : ip) {
            ZKManager zk = new ZKManagerImpl(addr);
            zkList[idx] = (zk);
            idx++;
        }

        for (int w : weight) {
            totalWeight += w;
        }

        int count = 0;
        cumulativeW = new int[weight.length];
        for (int i = 0; i < weight.length; i++) {
            count += weight[i];
            cumulativeW[i] = count;
        }
    }

    public ZKManager chooseMethod(int tag) {
        if (tag == 0) {
            return random();
        } else {
            return weightedRandomRobin();
        }
    }

    public ZKManager random() {
        int curr = random.nextInt(ip.length);
        System.out.println(curr);
        return zkList[curr];
    }


    private ZKManager weightedRandomRobin() {
        int curr = random.nextInt(totalWeight);
        System.out.print(curr);

        int st = 0;
        for (int w : cumulativeW) {
            if (curr < w) {
                System.out.println(", " + st);
                return zkList[st];
            }
            st++;
        }
        return zkList[st];
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 0; i < 20; i++) {
//            lb.random();
            lb.weightedRandomRobin();
        }
    }
}
