package com.xwzhou;

import com.xwzhou.rabbitmq.config.XDelayConfig;
import com.xwzhou.rabbitmq.productor.Productor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootRabbitmqApplicationTests {

  @Autowired
  private Productor productor;

  /**
   * 简单模式
   */
  @Test
  void simpleProducer() {
    productor.sendSimpleMessage("简单模式消息！");
  }


  /**
   * 工作者模式
   */
  @Test
  void workProducer() {
    for (int i = 0; i < 10; i++) {
      productor.sendWorkMessage("工作者模式消息！" + i);
    }

  }

  /**
   * 工作者模式
   */
  @Test
  void workJustProducer() {
    for (int i = 0; i < 20; i++) {
      productor.sendWorkJustMessage("公平工作者模式消息！" + i);
    }

  }

  @Test
  void contextLoads() {
    productor.sendMessage("哈哈哈！123");
  }


  /**
   * 路由模式
   */
  @Test
  void directProducer() {
    productor.sendDirectMessage("路由模式消息！", "direct.C.key");
  }

  /**
   * topic模式
   */
  @Test
  void topicProducer() {
//    fanoutProductor.sendTopicMessage("topic模式消息！", "com.topic.A");
    for (int i = 0; i < 10; i++) {
      productor.sendTopicMessage("topic模式消息！", "com.topic.B");
    }

  }

  /**
   * 延迟消息
   */
  @Test
  void delayProducer() {
    productor.sendDelayMessage("延迟消息！", XDelayConfig.DELAY_ROUTING_KEY_XDELAY, 10 * 1000);


  }

}
