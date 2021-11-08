package com.xwzhou.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式
 */
@Configuration
public class TopicConfig {


  public static final String TOPIC_EXCHANGE_TEST = "topic.exchange.test";
  public static final String TOPIC_QUEUEA_TEST = "topic.queueA.test";
  public static final String TOPIC_QUEUEB_TEST = "topic.queueB.test";

  /**
   * 注册topic交换机
   *
   * @return
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_EXCHANGE_TEST);
  }

  /**
   * 注册A队列
   */
  @Bean
  public Queue topicQueueA() {
    return new Queue(TOPIC_QUEUEA_TEST, true, false, false);
  }

  /**
   * 注册B队列
   */
  @Bean
  public Queue topicQueueB() {
    return new Queue(TOPIC_QUEUEB_TEST, true, false, false);
  }

  /**
   * 绑定交换机 和A队列  关联topic
   */
  @Bean
  public Binding topicBindingA() {
    return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with("*.topic.A.#");
  }

  /**
   * 绑定交换机 和B队列 关联topic
   */
  @Bean
  public Binding topicBindingB() {
    return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("#.B.#");
  }
}
