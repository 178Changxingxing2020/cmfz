package com.baizhi.cxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {
    @Id
    private String id;
    private String name;
    private Date time;
    private String action;
    private String status;
}
