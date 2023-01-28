if (document.getElementById("commentButton") !== null && document.getElementById("writeComment") !== null) {
	
	// DOM 노드
	const commentButton = document.getElementById("commentButton");
	const writeComment = document.getElementById("writeComment");

	// 댓글쓰기 버튼을 누르면 댓글쓰기란이 나타나게 함
	commentButton.addEventListener('click', () => {
		if (writeComment.style.display == "none") {
			commentButton.innerHTML = "댓글쓰기 close";
			writeComment.style.display = "";
		}
		else {
			commentButton.innerHTML = "댓글쓰기 open";
			writeComment.style.display = "none";
		}
	})
}