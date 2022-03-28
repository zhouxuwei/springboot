package com.xwzhou;

import com.xwzhou.rabbitmq.config.XDelayConfig;
import com.xwzhou.rabbitmq.productor.Productor;
import java.util.concurrent.TimeUnit;
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
    productor.sendDirectMessage("路由模式消息！", "direct.B.key");

  }

  /**
   * 路由模式  应答机制 退回机制
   */
  @Test
  void directProducerCallBack() throws InterruptedException {
    productor.sendDirectMessageCallBack("路由模式消息！", "direct.A.key", "123");
    //当测试方法结束，rabbitmq相关的资源也就关闭了，虽然我们的消息发送出去，但异步的ConfirmCallback却由于资源关闭而出现了上面的问题
    TimeUnit.SECONDS.sleep(2);
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
