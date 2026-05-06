package com.green.legacy.stu_manage.controller;

import com.green.legacy.stu_manage.dto.StuDTO;
import com.green.legacy.stu_manage.service.StuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StuController {
  private final StuService stuService;

  @RequestMapping("/list")
  public String list(@RequestParam(required = false) Integer classNum, Model model) {

    List<StuDTO> studentList = stuService.getStudents(classNum);

    model.addAttribute("studentList", studentList);
    model.addAttribute("classNum", classNum);
    model.addAttribute("classList", stuService.getClassList());
    model.addAttribute("totalCount", studentList.size());

    return "stu/stu_manage";
  }
}
