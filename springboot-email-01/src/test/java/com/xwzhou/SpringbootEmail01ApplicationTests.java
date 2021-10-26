package com.xwzhou;

import java.io.File;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class SpringbootEmail01ApplicationTests {

  @Autowired
  JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String fromSender;

  /**
   * 普通邮件发送
   */
  @Test
  void contextLoads() {
    // 构建一个邮件对象
    SimpleMailMessage message = new SimpleMailMessage();
    // 设置邮件主题
    message.setSubject("这是一封测试邮件11");
    // 设置邮件发送者，这个跟application.yml中设置的要一致
    message.setFrom(fromSender);
    // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
    // message.setTo("10*****16@qq.com","12****32*qq.com");
    message.setTo("747935586@qq.com");
    // 设置邮件抄送人，可以有多个抄送人
//    message.setCc("664385033@qq.com");
    // 设置隐秘抄送人，可以有多个
//    message.setBcc("664385033@qq.com");
    // 设置邮件发送日期
    message.setSentDate(new Date());
    // 设置邮件的正文
    message.setText("这是测试邮件的正文22");
    // 发送邮件
    javaMailSender.send(message);
  }


  /**
   * 发送带附件的邮件
   */
  @Test
  void complexAttachMail() throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    // true表示构建一个可以带附件的邮件对象
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

    helper.setSubject("这是一封测试邮件");
    helper.setFrom(fromSender);
    helper.setTo("747935586@qq.com");
    //helper.setCc("37xxxxx37@qq.com");
    //helper.setBcc("14xxxxx098@qq.com");
    helper.setSentDate(new Date());
    helper.setText("这是测试邮件的正文", true);
    // src='cid:p01' 占位符写法 ，第二个参数true表示这是一个html文本
    helper.setText(
        "<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",
        true);
    // 第一个参数指的是html中占位符的名字，第二个参数就是文件的位置
    helper.addInline("p01",
        new FileSystemResource(new File("C:\\Users\\wangshiqi\\Desktop\\1.png")));
    helper.addInline("p02",
        new FileSystemResource(new File("C:\\Users\\wangshiqi\\Desktop\\1.png")));
    // 第一个参数是自定义的名称，后缀需要加上，第二个参数是文件的位置
    helper.addAttachment("巡检报表模板.xlsx",
        new File("C:\\Users\\wangshiqi\\Desktop\\11.xlsx"));
    javaMailSender.send(mimeMessage);
  }


  @Autowired
  TemplateEngine templateEngine;

  /**
   * 发送Thymeleaf的邮件
   */
  @Test
  void thymeleafMail() throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setSubject("这是一封测试邮件");
    helper.setFrom(fromSender);
    helper.setTo(new String[]{"747935586@qq.com", "qqweiwei2008love@126.com"});
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
    helper.setSentDate(new Date());
    // 这里引入的是Template的Context
    Context context = new Context();
    // 设置模板中的变量
    context.setVariable("username", "周大伟");
    context.setVariable("num", "000001");
    context.setVariable("salary", "99999");
    // 第一个参数为模板的名称
    String process = templateEngine.process("hello.html", context);
    // 第二个参数true表示这是一个html文本
    helper.setText(process, true);
    javaMailSender.send(mimeMessage);
  }

}
