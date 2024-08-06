package org.zerock.ex00.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.domain.BoardVO;
import org.zerock.ex00.domain.Criteria;
import org.zerock.ex00.domain.PageDTO;
import org.zerock.ex00.service.BoardService;

@Controller
@Log4j2
@RequiredArgsConstructor//생성자 주입을 자동으로 처리해줍니다.
@RequestMapping("/board")
public class BoardController {


    private final BoardService boardService;

    //list


/*
    @GetMapping("/list")
    public void list(Model model){
        log.info("list........................");

        java.util.List<BoardVO> list = boardService.list();

        log.info(list);

        model.addAttribute("list", list);
    }
*/




/*    @GetMapping("/read/{bno}")
    public  String read(
            @PathVariable(name="bno") Long bno, Model model) {

        //PathVariable을 사용하면 리소스 경로에 식별자를 넣어서 동적으로 URL에 정보를 담을 수 있다.
        //
        //URL 경로의 중괄호 { } 안쪽에 변수를 담고, 그 변수를 @PathVariable(" ")로 받아서 사용할 수 있다.
        log.info("bno: " + bno);

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);

        model.addAttribute("vo", boardVO);

        return  "/board/read";


    }*/


    @GetMapping("/list")
    public void list(
            @ModelAttribute("cri") Criteria criteria,
            Model model){
                log.info("list....................");
                log.info("criteria: " + criteria);



              // Criteria에 맞는 BoardVO 객체들의 리스트를 가져옵니다.
                java.util.List<BoardVO> list = boardService.getList(criteria);

                log.info("@@@@@@@@@");
                log.info(list);
                log.info("@@@@@@@@@");


                model.addAttribute("list", list);
                PageDTO pageDTO = new PageDTO(criteria, boardService.getTotal(criteria));

                model.addAttribute("pageMaker", pageDTO);
    }







 /*   @GetMapping("/modify/{bno}")
    public  String modify(
            @PathVariable(name="bno") Long bno, Model model) {

        //PathVariable을 사용하면 리소스 경로에 식별자를 넣어서 동적으로 URL에 정보를 담을 수 있다.
        //
        //URL 경로의 중괄호 { } 안쪽에 변수를 담고, 그 변수를 @PathVariable(" ")로 받아서 사용할 수 있다.
        log.info("bno: " + bno);

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);

        model.addAttribute("vo", boardVO);

        return  "/board/modify";


    }
*/



    // 이 메서드는 URL 경로에서 작업(job)과 게시글 번호(bno)를 추출하여 해당 작업을 수행하는 역할을 합니다.
    @GetMapping({"/{read}/{modify}"})
    //get은 페이지보여줄 때
    //post는 데이터 저장할 때

    //한 메소드를 가지고 여러 url 설계가능
    public String read(
            //void를 쓰면 계속 .jsp이런식으로 값을 만들어야해서 string문자열로 처리
            //PathVariable는 뭐가 들어올지몰라서 그 때사용
            @PathVariable(name="read") String job, // 경로 변수 'job'의 값을 파라미터로 받아옵니다.
            @PathVariable(name="modify") Long bno,   // 경로 변수 'bno'의 값을 파라미터로 받아옵니다.
/*            @ModelAttribute("cri") Criteria criteria,*/
            Model model) {                        // 모델 객체를 파라미터로 받아옵니다.

        // 받아온 job과 bno 값을 로그로 출력합니다.
        log.info("job: " + job);
        log.info("bno: " + bno);

        // job이 "read"나 "modify"가 아니면 예외를 발생시킵니다.
        if (!(job.equals("read") || job.equals("modify"))) {
            throw new RuntimeException("Bad Request job");
        }

        // 게시글 서비스를 통해 해당 게시글 번호에 해당하는 게시글을 가져옵니다.
        BoardVO boardVO = boardService.get(bno);

        // 가져온 게시글 정보를 로그로 출력합니다.
        log.info("boardVO: " + boardVO);

        // 모델에 "vo"라는 이름으로 게시글 정보를 추가합니다.
        model.addAttribute("vo", boardVO);

        // 작업(job)에 따라 다른 페이지로 이동합니다.
        return "/board/" + job;
    }
    @GetMapping("/register")
    public void register() {
    }




    //postman에서 post방식해야함
 @PostMapping("/register")
      //redirect를 할꺼라 string,boardVO에 있는 걸 받아와서 등록해야해서 class를 받음
     //RedirectAttributes에 addFlashAttribute를 같이 결합해서 한번만 데이터를 전송
    public String registerPost(BoardVO boardVO, RedirectAttributes rttr){

        log.info("boardvo: " + boardVO);

        Long bno = boardService.register(boardVO);

        //RedirectAttributes에 RedirectAttributes를 해서 한번만 데이터를 전송
        rttr.addFlashAttribute("result", bno);
        return  "redirect:/board/list";

 }




   //삭제시에는 post방식 사용
    @PostMapping("/remove/{bno}")
    public String remove(
            @PathVariable(name="bno") Long bno,
            RedirectAttributes rttr){

        // 삭제할 게시글의 정보를 새로운 BoardVO 객체에 설정합니다.
        BoardVO boardVO = new BoardVO();
        boardVO.setBno(bno);
        boardVO.setTitle("해당 글은 삭제되었습니다.");
        boardVO.setContent("해당 글은 삭제되었습니다.");

        // 게시글 서비스를 호출하여 게시글을 수정(삭제 처리)합니다.
        boardService.modify(boardVO);

        // 삭제된 게시글의 번호를 RedirectAttributes를 통해 전달합니다.
        // Flash 속성으로 설정하면 한 번의 요청에만 유효하게 전달됩니다.
        rttr.addFlashAttribute("result", boardVO.getBno());

        // 삭제가 완료되면 게시글 목록 페이지로 리다이렉트합니다.
        return "redirect:/board/list";
    }






    @PostMapping("/modify/{bno}")
    public String modify(
            @PathVariable(name="bno") Long bno,
            BoardVO boardVO,
            RedirectAttributes rttr){

        boardVO.setBno(bno);

        log.info("boardVO!!!!!!: " + boardVO);
        boardService.modify(boardVO);

        rttr.addFlashAttribute("result", boardVO.getBno());


        return  "redirect:/board/read" + bno ;

    }



}
