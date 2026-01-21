package com.green.rest_study;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * 사원과 관련된 CRUD API를 제공하는 EmpController를 생성하여 요청에 대한 응답처리 메서드 구현.
 * 1) EmpController 클래스는 Emp 객체를 다수 저장할 수 있는 List를 맴버변수로 가짐.
 * 2) 생성자에서는 List를 생성하고, 해당 List에 Emp 객체를 5개 저장
 * 3) 사원 목록 조회 요청에 응답하는 api 메서드 구현
 * 4) 사번을 통해 특정 사원 한 명의 정보를 요청하면 이에 응답하는 api 메서드 구현
 * 5) 사원 등록 요청에 응답하는 api메서드 구현. 요청 시 등록할 사원정보가 함께 전달 됨
 * 6) 사번을 통해 특정 사원 한 명을 삭제하는 요청이 오면 이에 응답하는 api 메서드 구현
 * 7) 사번을 통해 특정 사원 한 명의 정보 수정 요청이 오면 이에 응답하는 api 메서드 구현
 *    요청 시 정보를 수정하고자 하는 사번, 급여, 부서명 정보가 함께 전달 됨
 * 8) postman을 활용하여 위에서 만든 메서드의 동작을 확인
*/
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/emps")
public class EmpController {
  private List<EmpDTO> empList;

  public EmpController() {
    empList = new ArrayList<>();

    empList.add(new EmpDTO(1, "직원1", "개발부", 10000, "사원"));
    empList.add(new EmpDTO(2, "직원2", "개발부", 20000, "대리"));
    empList.add(new EmpDTO(3, "직원3", "개발부", 50000, "과장"));
    empList.add(new EmpDTO(4, "직원4", "사업부", 10000, "사원"));
    empList.add(new EmpDTO(5, "직원5", "사업부", 70000, "부장"));
  }

  @GetMapping("")
  public List<EmpDTO> getEmpList() {
    System.out.println("사원 목록 조회");
    return empList;
  }

  @GetMapping("/{empNo}")
  public EmpDTO getEmp(@PathVariable("empNo") int empNo) {
    System.out.println(empNo + "번 사원 조회");

    EmpDTO result = null;
    for (EmpDTO e : empList) {
      if (e.getEmpNo() == empNo) {
        result = e;
        break;
      }
    }

    return result;
  }

  @PostMapping("")
  public void regEmp(@RequestBody EmpDTO empDTO) {
    System.out.println("사원 등록");
    empList.add(empDTO);
  }

  @DeleteMapping("/{empNo}")
  public String deleteEmp(@PathVariable("empNo") int empNo) {
    System.out.println(empNo + "번 사원 삭제");
    return empNo + " 삭제";
  }

  @PutMapping("/{empNo}")
  public void updateEmp(@PathVariable("empNo") int empNo, @RequestBody EmpDTO empDTO) {
    System.out.println(empNo + "번 사원 정보 수정");
    System.out.println(empDTO);

    /*for (EmpDTO e : empList) {
      if (e.getEmpNo() == empNo) {
        e.setSalary(empDTO.getSalary());
        e.setDept(empDTO.getDept());
      }
    }*/
  }
}
