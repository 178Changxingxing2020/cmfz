package com.baizhi.cxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGuru {
  @Id
  private String id;
  private String guruid;
  private String userid;

}
