const cal = () => {
  // 두 input 태그에 입력된 값을 읽어온다.
  // -> input 태그의 value 속성 값을 읽어온다.
  // 1) 첫 번째 input 태그에 접근
  const num1_tag = document.querySelector('#num1');
  // 2) 접근한 태그에서 value 값 읽어옴
  console.log(num1_tag.value);

  const num1 = num1_tag.value;

  // 2) 두 번째 input 태그에 접근
  const num2 = document.querySelector('#num2').value;

  // 가져온 값을 더한다.
  const result = Number(num1) + Number(num2);

  // 더한 값을 결과 input에 넣어준다.
  const result_tag = document.querySelector('#result');
  result_tag.value = result;
};

const addRow = () => {
  // tbody 태그에 접근
  const tbody_tag = document.querySelector('#table > tbody');
  // console.log(tbody_tag);

  const str = `
    <tr>
      <td>1</td>
      <td>2</td>
      <td>3</td>
    </tr>`

  tbody_tag.insertAdjacentHTML('beforeend', `  `)
}