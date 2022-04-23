import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        BasicConfigurator.configure();
//        ZKManager zk = new ZKManagerImpl("34.106.179.93");
//        ZKManager zk = new ZKManagerImpl("34.106.20.60");
        ZKManager zk = new ZKManagerImpl("34.125.251.158");
//        String result= (String) zk.getZNodeData("/test",true);
//        System.out.println("result : "+result);
        byte[] s = "mm".getBytes();
        zk.create("/test2", s);
        String result= (String) zk.getZNodeData("/test2",true);
        System.out.println("result : " + result);
    }
}
