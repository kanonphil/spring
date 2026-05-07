package com.green.legacy.stu_manage.service;

import com.green.legacy.stu_manage.dto.ClassDTO;
import com.green.legacy.stu_manage.dto.ScoreDTO;
import com.green.legacy.stu_manage.dto.StuDTO;
import com.green.legacy.stu_manage.mapper.StuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StuService {
  private final StuMapper stuMapper;

  public List<StuDTO> getStudents(Integer classNum) {
    return stuMapper.selectStudentsByClass(classNum);
  }

  public List<ClassDTO> getClassList() {
    return stuMapper.selectClassList();
  }

  public ScoreDTO getScoreInfo(int stuNum) {
    return stuMapper.getScoreInfo(stuNum);
  }

  public void updateScore(ScoreDTO scoreDTO) {
    int cnt = stuMapper.isScore(scoreDTO.getStuNum());

    if (cnt == 0) {
      stuMapper.insertScore(scoreDTO);
    } else {
      stuMapper.updateScore(scoreDTO);
    }
  }
}
