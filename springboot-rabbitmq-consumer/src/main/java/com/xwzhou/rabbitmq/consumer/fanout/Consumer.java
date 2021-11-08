package com.xwzhou.rabbitmq.consumer.fanout;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @RabbitListener(queues = "simple.queue.test")
  public void receiveMessageSimple(String in) {
    System.out.println("接受到的消息为: " + in);
  }

  //--------------------------------工作模式-轮询-消费者开始-------------------------------------------------
  @SneakyThrows
  @RabbitListener(queues = "work.queue.test")
  public void receiveWorkMessageA(String in) {
    Thread.sleep(1000);
    System.out.println("消费者A接受到的消息为: " + in);
  }

  @RabbitListener(queues = "work.queue.test")
  public void receiveWorkMessageB(String in) {
    System.out.println("消费者B接受到的消息为: " + in);
  }

  //--------------------------------工作模式-轮询-消费者结束-------------------------------------------------


  //--------------------------------工作模式-公平-消费者开始-------------------------------------------------
  @SneakyThrows
  @RabbitListener(queues = "work.just.queue.test")
  public void receiveWorkMessageC(String in, Channel channel, Message message) {
    Thread.sleep(1000);
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    System.out.println("消费者C接受到的消息为: " + in);
  }

  @SneakyThrows
  @RabbitListener(queues = "work.just.queue.test")
  public void receiveWorkMessageD(String in, Channel channel, Message message) {
    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    System.out.println("消费者D接受到的消息为: " + in);
  }

  //--------------------------------工作模式-公平-消费者结束-------------------------------------------------

  @RabbitListener(queues = "fanout.queueA.test")
  public void receiveMessageA(String in) {
    System.out.println("A接受到的消息为: " + in);
  }

  @RabbitListener(queues = "fanout.queueB.test")
  public void receiveMessageB(String in) {
    System.out.println("B接受到的消息为: " + in);
  }


  @RabbitListener(queues = "direct.queueA.test")
  public void receiveDirectMessageA(String in) {
    System.out.println("Direct-A接受到的消息为: " + in);
  }

  @RabbitListener(queues = "direct.queueB.test")
  public void receiveDirectMessageB(String in) {
    System.out.println("Direct-B接受到的消息为: " + in);
  }


  @RabbitListener(queues = "topic.queueA.test")
  public void receiveTopicMessageA(String in) {
    System.out.println("topic-A接受到的消息为: " + in);
  }

  @RabbitListener(queues = "topic.queueB.test")
  public void receiveTopicMessageB(String in) {
    System.out.println("topic-B接受到的消息为: " + in);
  }

  @RabbitListener(queues = "topic.queueB.test")
  public void receiveTopicMessageC(String in) {
    System.out.println("topic-C接受到的消息为: " + in);
  }


  @RabbitListener(queues = "queue.test.xdelay.immediate")
  public void receiveDelayMessage(String in) {
    log.info("delay接受到的消息为: {}", in);
  }

}
