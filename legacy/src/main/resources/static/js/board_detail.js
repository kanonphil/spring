function deleteBoard(boardNum) {
  if (confirm("정말 삭제하시겠습니까?")) {
  const form = document.createElement("form")
  form.method = "post"
  form.action = "/boards/delete"

  const input = document.createElement("input")
  input.type = "hidden"
  input.name = "boardNum"
  input.value = boardNum

  form.appendChild(input)
  document.body.appendChild(form)
  form.submit()
  }
}