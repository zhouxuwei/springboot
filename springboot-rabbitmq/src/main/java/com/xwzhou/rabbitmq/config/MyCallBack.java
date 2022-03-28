package com.xwzhou.rabbitmq.config;

import javax.annotation.PostConstruct;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostConstruct
  public void init() {
    rabbitTemplate.setConfirmCallback(this);
    rabbitTemplate.setReturnsCallback(this);
  }

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    String id = correlationData != null ? correlationData.getId() : "";
    if (ack) {
      System.out.println("交换机接受消息成功,id为：" + id);
    } else {
      System.out.println("交换机接受消息失败,id为：" + id + ",原因：" + cause);
    }
  }

  //可以在当消息传递过程中不可达目的队列时将消息返回给生产者
  //只有不可达队列时候，才会回退
  @Override
  public void returnedMessage(ReturnedMessage returnedMessage) {
    System.out.println(
        "消息" + new String(returnedMessage.getMessage().getBody()) + ",被交换机:" + returnedMessage
            .getExchange() + "退回，退回原因：" + returnedMessage.getReplyText() + ",路由key:"
            + returnedMessage.getRoutingKey());
  }
}
