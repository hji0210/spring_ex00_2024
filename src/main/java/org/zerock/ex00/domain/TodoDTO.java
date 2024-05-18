package org.zerock.ex00.domain;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TodoDTO {


    private String title;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;


}
