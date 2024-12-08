package org.zerock.ex00.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BoardVO {

    private Long bno;
    private  String title;
    private  String Content;
    private  String writer;
    private boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;


}
