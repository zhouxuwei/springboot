package com.xwzhou.Controller;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pdf")
public class PdfController {

  @GetMapping("/export")
  public void exportPdf(HttpServletResponse response) throws Exception {
    ByteArrayOutputStream baos = null;
    OutputStream out = null;
    try {
      Map<String, Object> data = new HashMap<>();
      data.put("no", "123456");

      List<PDFDataTest> detailList = new ArrayList<>();
      detailList.add(new PDFDataTest(1, "2021-11-11", "路政人员姓名1", "称重和卸载单编号1", "公安交管人姓名1", "治超站名称1",
          "备注1"));
      detailList.add(new PDFDataTest(2, "2021-11-11", "路政人员姓名2", "称重和卸载单编号2", "公安交管人姓名2", "治超站名称2",
          "备注2"));
      detailList.add(new PDFDataTest(3, "2021-11-11", "路政人员姓名3", "称重和卸载单编号3", "公安交管人姓名3", "治超站名称3",
          "备注3"));
      data.put("detailList", detailList);

      baos = PDFTemplateUtil.createPDF(data, "index.ftl");
      ;
      // 设置响应消息头，告诉浏览器当前响应是一个下载文件
      response.setContentType("application/x-msdownload");
      // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
      String fileName = URLEncoder.encode("超限超载案件交接单.pdf", "UTF-8");
      response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
      out = response.getOutputStream();
      baos.writeTo(out);
      baos.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception("导出失败：" + e.getMessage());
    } finally {
      if (baos != null) {
        baos.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }
}

