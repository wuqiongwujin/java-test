package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description zookeeper分布式锁
 * @Author Cain
 * @date 2020/12/15
 */
public class InterProcessMutexTest {

    public static void main(String[] args) {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.21.41.181:2181,10.21.42.47:2181,10.21.49.252:2181", retryPolicy);
        client.start();
        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
        try {
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //获得了锁, 进行业务流程
        } catch (Exception e) {

        } finally {
            try {
                //完成业务流程, 释放锁
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //关闭客户端
        client.close();
    }
}
