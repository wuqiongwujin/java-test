package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description
 * @Author Cain
 * @date 2020/10/22
 */
public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer");
        consumer.setNamesrvAddr("114.116.237.83:9876");
        consumer.setVipChannelEnabled(false);
        try {
            consumer.subscribe("TopicTest", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    for (MessageExt message : list) {
                        String topic = message.getTopic();
                        byte[] body = message.getBody();
                        String keys = message.getKeys();
                        String tags = message.getTags();
                        System.out.println("消息内容,topic:"+topic+",body:"+new String(body)+",keys:"+keys+",tags:"+tags);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
