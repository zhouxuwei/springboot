package com.xwzhou.rabbitmq.productor;

import com.xwzhou.rabbitmq.config.DirectConfig;
import com.xwzhou.rabbitmq.config.FanoutConfig;
import com.xwzhou.rabbitmq.config.TopicConfig;
import com.xwzhou.rabbitmq.config.XDelayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Productor {

  Logger log = LoggerFactory.getLogger(this.getClass());
  public static final String SIMPLE_QUEUE_TEST = "simple.queue.test";

  public static final String WORK_QUEUE_TEST = "work.queue.test";

  public static final String WORK_JUST_QUEUE_TEST = "work.just.queue.test";


  @Autowired
  private RabbitTemplate template;

  /**
   * 发送简单模式消息
   *
   * @param message
   */
  public void sendSimpleMessage(String message) {
    //由于没有交换机，所以路由规则就是队列名称
    template.convertAndSend(SIMPLE_QUEUE_TEST, message);
    System.out.println("发送的消息为: " + message);
  }

  /**
   * 发送工作模式消息
   *
   * @param message
   */
  public void sendWorkMessage(String message) {
    //由于没有交换机，所以路由规则就是队列名称
    template.convertAndSend(WORK_QUEUE_TEST, message);
    System.out.println("发送的消息为: " + message);
  }

  /**
   * 发送公平工作模式消息
   *
   * @param message
   */
  public void sendWorkJustMessage(String message) {
    //由于没有交换机，所以路由规则就是队列名称
    template.convertAndSend(WORK_JUST_QUEUE_TEST, message);
    System.out.println("发送的消息为: " + message);
  }

  public void sendMessage(String message) {
    template.convertAndSend(FanoutConfig.FANOUT_EXCHANGE_TEST, "", message);
    System.out.println("发送的消息为: " + message);
  }


  /**
   * 发送路由模式消息
   *
   * @param message
   */
  public void sendDirectMessage(String message, String routingKey) {
    template.convertAndSend(DirectConfig.DIRECT_EXCHANGE_TEST, routingKey, message);
    System.out.println("发送的消息为: " + message);
  }


  /**
   * 发送路由模式消息 应答机制 退回机制
   *
   * @param message
   */
  public void sendDirectMessageCallBack(String message, String routingKey, String id) {
    template.convertAndSend(DirectConfig.DIRECT_EXCHANGE_TEST, routingKey, message,
        new CorrelationData(id));
    System.out.println("发送的消息为: " + message);
  }

  /**
   * 发送topic主题模式消息
   *
   * @param message
   */
  public void sendTopicMessage(String message, String routingKey) {
    template.convertAndSend(TopicConfig.TOPIC_EXCHANGE_TEST, routingKey, message);
    System.out.println("发送的消息为: " + message);
  }


  /**
   * 发送延迟消息
   *
   * @param message
   */
  public void sendDelayMessage(String message, String routingKey, Integer time) {
    template.convertAndSend(XDelayConfig.DELAYED_EXCHANGE_XDELAY, routingKey, message, msg -> {
      msg.getMessageProperties().setDelay(time);
      return msg;
    });
    log.info("发送的消息为: {}", message);
  }
}
