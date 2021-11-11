<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <title>Title</title>
  <style type="text/css">
    @page {
      size: A4 landscape;
    }

    body {
      font-family: SimSun;
    }

    .div_h1 {
      margin-top: 200px;
    }


    table {
      border-collapse: collapse;
      border-spacing: 0;
      table-layout: fixed;
      width: 100%;
      margin: 20px 0px;
      text-align: center;
      word-wrap: break-word;
    }

    th, td {
      border: 1px solid;
      padding: 0;
      text-align: center;

    }


  </style>
</head>
<body>
<div class="div_h1" style="text-align: center">
  <h1>超限超载案件交接单</h1>
</div>
<div class="div_span" style="text-align: right">
  <span>NO：${no}</span>
</div>
<div>
  <table>
    <tbody>
    <tr>
      <th>编号</th>
      <th>日期</th>
      <th>路政人员姓名</th>
      <th>称重和卸载单编号</th>
      <th>公安交管人姓名</th>
      <th>治超站名称</th>
      <th>备注</th>
    </tr>
    <#list detailList as ad>
      <tr>
        <td>${ad.column1}</td>
        <td>${ad.column2}</td>
        <td>${ad.column3}</td>
        <td>${ad.column4}</td>
        <td>${ad.column5}</td>
        <td>${ad.column6}</td>
        <td>${ad.column7}</td>
      </tr>
    </#list>


    </tbody>
  </table>
</div>
</body>
</html>