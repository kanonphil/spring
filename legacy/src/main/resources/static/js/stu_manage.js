const getStuList = () => {
  const select_tag = document.createElement("classSelector");
  const classNum = select_tag.value;

  location.href = `/students/list?classNum=${classNum}`
};

const asyncGetList = () => {
  const select_tag = document.createElement("classSelector");
  const classNum = select_tag.value;

  axios.get(`/students/list?classNum=${classNum}`)
    .then(res => {
      console.log(res);

      // 조회한 데이터로 그림을 다시 그린다.
      // 1. 그림을 다시 그릴 태그를 선택한다.
      const tbody_tag = document.querySelector("#list_table > tbody");

      // 2. 기존 tbody 안의 모든 태그를 지운다.
      tbody_tag.innerHTML = '';

      // 3. 새롭게 그릴 그림을 문자열로 만든다.
      let str = ``;

      for (let i = 0; i < res.data.length; i++) {
        str += `
          <tr>
            <td>${res.data.length - i}</td>
            <td>${res.data[i].stuNum}</td>
            <td>${res.data[i].stuName}</td>
            <td>${res.data[i].stuYear}</td>
            <td>${res.data[i].classNum}</td>
            <td>${res.data[i].classDTO.className}</td>
          </tr>
        `;
      }

      // res.data.forEach((stu, i) => {
      //   str += `
      //     <tr>
      //       <td>${res.data.length - i}</td>
      //       <td>${stu.stuNum}</td>
      //       <td>${stu.stuName}</td>
      //       <td>${stu.stuYear}</td>
      //       <td>${stu.classNum}</td>
      //       <td>${stu.classDTO.className}</td>
      //     </tr>
      //   `;
      // })

      // 4. 새롭게 만든 그림을 tbody에 넣는다.
      tbody_tag.insertAdjacentHTML('afterbegin', str);

    })
    .catch(err => {
      console.log(err)
      })
};

function getScore(stuNum) {
  axios.get(`/students/score/${stuNum}`)
  .then(res => {
    const score = res.data;
    let str = ``;

    str += `
      <div class="score-box">
        <input type="hidden" id="stuNum" value="${score.stuNum}" >
        
        <table border="1">
          <thead>
            <tr>
              <th>학생명</th>
              <th>국어점수</th>
              <th>영어점수</th>
              <th>수학점수</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${score.stuDTO.stuName}</td>
              <td>
                <input type="number" id="korScore" value="${score.korScore}">
              </td>
              <td>
                <input type="number" id="engScore" value="${score.engScore}">
              </td>
              <td>
                <input type="number" id="mathScore" value="${score.mathScore}">
              </td>
            </tr>
          </tbody>
        </table>
        
        <button type="button" onclick="updateScore()">
          수정
        </button>
      </div>
    `;

    document.querySelector("#scoreDiv").innerHTML = str;
  })
      .catch(err => {
        console.log(err)
      })

}

function updateScore() {
  const scoreInfo = {
    stuNum : document.querySelector("#stuNum").value,
    korScore : document.querySelector("#korScore").value,
    engScore : document.querySelector("#engScore").value,
    mathScore : document.querySelector("#mathScore").value,
  }

  axios.put('/students/updateScore', scoreInfo)
      .then((res) => {
        alert('수정 완료')
      })
      .catch(err => {
        console.log(err)
      })
}