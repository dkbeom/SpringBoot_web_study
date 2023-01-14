// DOM 노드
const commentButton = document.getElementById("commentButton");
const writeComment = document.getElementById("writeComment");
const deleteCommentButtons = document.querySelector(".deleteCommentButtons");

// 댓글쓰기 버튼을 누르면 댓글쓰기란이 나타나게 함
commentButton.addEventListener('click', () => {
	if(writeComment.style.display == "none") {
		commentButton.innerHTML = "댓글쓰기 close";
		writeComment.style.display = "";
	}
	else {
		commentButton.innerHTML = "댓글쓰기 open";
		writeComment.style.display = "none";
		input.setAttribute('placeholder', '왜');
	}
})

// 댓글 삭제 버튼
deleteCommentButtons.addEventListener('click', () => {
	// 해당 댓글 삭제
	//console.log(deleteCommentButtons[0].dataset.id);
	const div = document.createElement('div');
	div.innerHTML = "눈이 부시게 아름다운 바닷가~";
	document.body.append(div);
})


