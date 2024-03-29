package com.baizhi.cxx.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
  @Id
  private String id;
  private String title;
  private String userId;
  private String type;
  @JSONField(format = "yyyy-MM-dd")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date createDate;

}
