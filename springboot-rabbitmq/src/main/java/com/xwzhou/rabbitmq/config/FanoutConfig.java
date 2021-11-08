package com.xwzhou.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布订阅模式
 */
@Configuration
public class FanoutConfig {


  public static final String FANOUT_EXCHANGE_TEST = "fanout.exchange.test";
  public static final String FANOUT_QUEUEA_TEST = "fanout.queueA.test";
  public static final String FANOUT_QUEUEB_TEST = "fanout.queueB.test";

  /**
   * 注册交换机
   *
   * @return
   */
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE_TEST);
  }

  /**
   * 注册A队列
   */
  @Bean
  public Queue fanoutQueueA() {
    return new Queue(FANOUT_QUEUEA_TEST, true, false, false);
  }

  /**
   * 注册B队列
   */
  @Bean
  public Queue fanoutQueueB() {
    return new Queue(FANOUT_QUEUEB_TEST, true, false, false);
  }

  /**
   * 绑定交换机 和A队列
   */
  @Bean
  public Binding fanoutBindingA() {
    return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
  }

  /**
   * 绑定交换机 和B队列
   */
  @Bean
  public Binding fanoutBindingB() {
    return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
  }
}
