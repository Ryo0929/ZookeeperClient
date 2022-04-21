import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        BasicConfigurator.configure();
        ZKManager zk = new ZKManagerImpl("34.106.3.246");
        String result= (String) zk.getZNodeData("/test",true);
        System.out.println("result : "+result);
    }
}
