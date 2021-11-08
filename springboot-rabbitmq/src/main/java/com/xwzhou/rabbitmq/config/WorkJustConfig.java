package com.xwzhou.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工作模式
 */
@Configuration
public class WorkJustConfig {

  public static final String WORK_JUST_QUEUE_TEST = "work.just.queue.test";


  /**
   * 注册A队列
   */
  @Bean
  public Queue workJustQueue() {
    return new Queue(WORK_JUST_QUEUE_TEST, true, false, false);
  }


}
