package com.baizhi.cxx.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringImageConverter;
import com.alibaba.fastjson.annotation.JSONField;
import com.baizhi.cxx.util.ImageConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
  @Id
  @ExcelProperty(value = "Id")
  private String id;
  @ExcelProperty(value = "标题")
  private String title;
  @ExcelProperty(value = "图片",converter = ImageConverter.class)
  private String url;
  @ExcelProperty(value = "超链接")
  private String href;
  @Column(name="create_date")
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @ExcelProperty(value = "创建时间")
  private java.util.Date createDate;
  @ExcelProperty(value = "描述")
  private String description;
  @ExcelProperty(value = "状态")
  private String status;


}
