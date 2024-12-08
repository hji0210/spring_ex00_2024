package org.zerock.ex00.mappers;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

    @Autowired(required = false)
    BoardMapper boardMapper;

    @Test
    public  void test1(){
        log.info(boardMapper);
    }


    @Test
    public  void testList(){
     boardMapper.getList().forEach(boardVO ->log.info(boardVO));
    }


    @Test
    public void testInsert(){

        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("NewTest");
        boardVO.setContent("NewTest...");
        boardVO.setWriter("Newbie");

         log.info("COUNT: " + boardMapper.insert(boardVO));
         //몇 게가 추가되었는지


        log.info("BNO: " + boardVO.getBno());
         //몇번으로 추가되었는지는 xml에서 selectkey를 이용해서 처리

    }


    @Test
    public void testPage(){

        Criteria criteria = new Criteria();
        //1페이지 10개기준
        criteria.setPageNum(2);

        criteria.setTypes(new String[]{"T"});
        criteria.setKeyword("1");

        //제목,내용,작성자로 검색을 한다면
        java.util.List<BoardVO> list = boardMapper.getPage(criteria);
        list.forEach(boardVO -> log.info(boardVO));

    }



    @Test
    public void testSelect(){

        Long bno = 9L;
        //9번 테스트하라면 long타입이라 L붙여서 9L로 써야함

        log.info(boardMapper.select(bno));
    }


    @Test
    public void testUpdate(){
     //제목이랑 내용주고 값 던져주는 것


        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("Updated Title");
        boardVO.setContent("Updated Content");
        boardVO.setBno(9L);

        int updateCount = boardMapper.update(boardVO);


        log.info("update: " + updateCount);
    }





}
