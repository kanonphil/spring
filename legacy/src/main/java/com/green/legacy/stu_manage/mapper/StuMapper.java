package com.green.legacy.stu_manage.mapper;

import com.green.legacy.stu_manage.dto.ClassDTO;
import com.green.legacy.stu_manage.dto.ScoreDTO;
import com.green.legacy.stu_manage.dto.StuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StuMapper {
  List<StuDTO> selectStudentsByClass(@Param("classNum") Integer classNum);
  List<ClassDTO> selectClassList();
  ScoreDTO getScoreInfo(@Param("stuNum") int stuNum);
  int isScore(@Param("stuNum") int stuNum);
  void insertScore(ScoreDTO scoreDTO);
  void updateScore(ScoreDTO scoreDTO);
}
