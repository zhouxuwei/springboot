package com.xwzhou.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式
 */
@Configuration
public class DirectConfig {


  public static final String DIRECT_EXCHANGE_TEST = "direct.exchange.test";
  public static final String DIRECT_QUEUEA_TEST = "direct.queueA.test";
  public static final String DIRECT_QUEUEB_TEST = "direct.queueB.test";

  /**
   * 注册交换机
   *
   * @return
   */
  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(DIRECT_EXCHANGE_TEST);
  }

  /**
   * 注册A队列
   */
  @Bean
  public Queue directQueueA() {
    return new Queue(DIRECT_QUEUEA_TEST, true, false, false);
  }

  /**
   * 注册B队列
   */
  @Bean
  public Queue directQueueB() {
    return new Queue(DIRECT_QUEUEB_TEST, true, false, false);
  }

  /**
   * 绑定交换机 和A队列  指明路由规则
   */
  @Bean
  public Binding directBindingA() {
    return BindingBuilder.bind(directQueueA()).to(directExchange()).with("direct.A.key");
  }

  /**
   * 绑定交换机 和B队列 指明路由规则
   */
  @Bean
  public Binding directBindingB() {
    return BindingBuilder.bind(directQueueB()).to(directExchange()).with("direct.B.key");
  }
}
