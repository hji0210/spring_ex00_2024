package org.zerock.ex00.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.domain.SampleDTO;
import org.zerock.ex00.domain.SampleDTOList;
import org.zerock.ex00.domain.TodoDTO;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequestMapping(value = "/sample") //경로 설정 시 쓰는 것
public class SampleController {

    @GetMapping("/basic")
     //url이 jsp
    //JSP이름이 void면 URL 따라감
    public void ssdqwd() {
        log.info("basic------------------------");
    }


    @GetMapping("/ex1")
    //SampleDTO에서 @ModelAttribute가 생략되어 있음
    public void ex1(SampleDTO sampleDTO) {
        //ModelAttribute가 없으면 앞에 소문자로 변수담김 =SampleDT0가 아닌
        log.info("ex1-------");
        log.info(sampleDTO);
    }

    @GetMapping("/ex02Array")
    public String ex02Array(String[] ids) {
        log.info("================");
        log.info(Arrays.toString(ids));
        return "/sample/ex2";
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info(list);
        return "/sample/ex2Bean";
    }

    @GetMapping("/ex03")
    public void ex03(TodoDTO todoDTO) {
        log.info("------------------");
        log.info(todoDTO);
    }

        @GetMapping("/ex04")
        public void ex04(

                //http://localhost:8080/sample/ex04?name=kk&age=23&page=2
                @ModelAttribute("dto")  SampleDTO dto,//class받을 때 ModelAttribute
                @RequestParam("page") int page,//RequestParam 1:1로 받을 때
                Model model){
         model.addAttribute("list", new String[]{"AAA", "BBB", "CCC"});
        }

    @GetMapping("/ex05")
    public String ex05(RedirectAttributes rttr) {
        //RedirectAttributes가 있으면 return도 redirect
        //redirect는 get방식밖에 되지 않음

        // rttr은 RedirectAttributes 객체로, 리다이렉트할 때 데이터를 전달하는 데 사용됩니다.

        // addAttribute 메서드는 쿼리 파라미터로 데이터를 추가합니다.
        rttr.addAttribute("v1", "ABC"); // "v1"이라는 이름으로 "ABC" 값을 추가합니다.
        rttr.addAttribute("v2", "XYZ"); // "v2"이라는 이름으로 "XYZ" 값을 추가합니다.





        // addFlashAttribute 메서드는 일회성 데이터를 추가합니다.
        rttr.addFlashAttribute("core", "ABCDE"); // "core"이라는 이름으로 "ABCDE" 값을 추가합니다.
//성공했다,실패했다 형태로 잠깐 보여줘야할 때 addFlashAttribute 많이 씀
        // "redirect:/sample/basic"은 /sample/basic URL로 리다이렉트합니다.
        return "redirect:/sample/basic";
    }







/*
    @GetMapping("/ex05")
     public String ex05(RedirectAttributes rttr){


        String[] arr = new String[]{"AAA", "BBB", "CCC"};
        rttr.addFlashAttribute("ids",arr);


        log.info("ex1--------------------");
        log.info(sampleDTO);
  }
*/





}
