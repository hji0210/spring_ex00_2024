package org.zerock.ex00.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.domain.SampleDTO;
import org.zerock.ex00.domain.SampleDTOList;
import org.zerock.ex00.domain.TodoDTO;

import java.util.Arrays;

@Controller
@Log4j2
@RequestMapping(value = "/sample") //경로 설정 시 쓰는 것
public class SampleController {

    @GetMapping("/basic")
    public void basic() {
        log.info("basic------------------------");
    }

    /*@GetMapping("/ex1")
    public void ex1(
            @RequestParam("name") String name,
            @RequestParam(
                    value = "age",
                    required = false,
                    defaultValue = "10") int age
            ) {

        log.info("ex1----------------");
        log.info(name);
        log.info(age);
    }*/

    @GetMapping("/ex1")
    //SampleDTO에서 @ModelAttribute가 생략되어 있음
    public void ex1(SampleDTO sampleDTO) {
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
                @ModelAttribute("dto")  SampleDTO dto,
                @ModelAttribute("page") int page,
                Model model){

        model.addAttribute("list", new String[]{"AAA", "BBB", "CCC"});
        }

    @GetMapping("/ex05")
    public String ex05(RedirectAttributes rttr){


    rttr.addAttribute("v1","ABC");
        rttr.addAttribute("v2","XYZ");
        rttr.addFlashAttribute("core","ABCDE");


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
