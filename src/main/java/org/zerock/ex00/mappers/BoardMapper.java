package org.zerock.ex00.mappers;

import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;

public interface BoardMapper {

  java.util.List<BoardVO> getList();


    java.util.List<BoardVO> getPage(Criteria criteria);

    int getTotal(Criteria criteria);


   int insert(BoardVO boardVO);


   BoardVO select(Long bno);


   int update(BoardVO boardVO);


   //insert,update,delete는 항상 int형태, 데이터 여러개면 항상 vo타입으로 받음

}
