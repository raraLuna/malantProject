<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 게시글 등록 폼 -->
<meta charset="UTF-8">
<title>boardInsert</title>
<style>
/* Reset some default styles */
body, h1, h2, p {
    margin: 0;
    padding: 0;
}

/* Page styling */
body {
    background-color: #f2f2f2;
    font-family: Arial, sans-serif;
}

/* Container for the form */
.container {
    height: 100vh;
}

/* Form styling */
form {
    background-color: #fff;
    border: 1px solid #ddd;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    max-width: 500px;
    height: 700px;
    position: relative;
    bottom: -50px;
    left: 420px;
}

/* Table styling */
table.insert-board {
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed;
    margin: 0 auto;
}

table.insert-board th {
    background-color: rgba(0, 0, 0, 0.1);
    color: white;
    border: 1px solid rgba(0, 0, 0, 0.1);
    padding: 10px;
    text-align: center;
}

table.insert-board td {
    border: 1px solid rgba(0, 0, 0, 0.1);
    padding: 10px;
    text-align: center;
    word-wrap: break-word;
    margin-right: 10px;
    padding-right : 33px;
}

/* Input field styling */
input[type="title"],
input[type="writer"],
textarea[name="content"],
input[name="hashtag"],
input[type="file"] {
    width: 100%;
    padding: 5px;
    margin-top: 5px;
}

/* Button styling */
input[type="submit"] {
    background-color: rgba(0, 0, 0, 0.1);
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    margin-top: 10px;
}

/* File upload field styling */
#filenamesView {
    margin-top: 10px;
}

textarea[name="content"] {
    width: 315px;
    height: 245px;
    padding: 5px;
    align : center;
}

/* Additional styling for hashtag input */
input[name="hashtag"] {
    width: 48%; /* Adjust as needed */
    padding: 5px;
    margin-right: 1%;
    
}

</style>
</head>
<body>
	<div class="myboard-insert" >
		<div class="container" style="display:contents;">
			<%@ include file="../../views/common/sidebar.jsp"%>
		</div>
		<form id="multiForm" action="/malant/binsert" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="userno"
				value="<%=loginMember.getUserNo()%>">
		
			<table class="insert-board" width="620" border="1" cellspacing="0">
				<tbody>
					<tr>
						<th width="120">제 목</th>
						<td align="center"><input type="title" name="title" size="30" required></td>
					</tr>
					<tr>
						<th width="120">작성자</th>
						<td align="center"><input type="writer" name="writer" size="30" value="<%= loginMember.getNickname() %>" required readonly></td>
					</tr>
					<tr>
						<th width="120">내 용</th>
						<td align="center"><textarea name="content" rows="5" cols="50"></textarea></td>
					</tr>
					<tr>
					    <th width="120">해시태그</th>
					    <td align="center">
					        #<input type="text" name="hashtag"><br>
					        #<input type="text" name="hashtag"><br>
					        #<input type="text" name="hashtag"><br>
					        #<input type="text" name="hashtag">
					    </td>
					</tr>
					<tr>
						<th width="120" required>첨부파일</th>
						<td><input type="file" name="multifile" id="multifile" multiple onchange="addHidden(); return false;">
							<br>
							<div id="filenamesView"></div>
						</td>
						<script type="text/javascript">
							function addHidden() {
								const form = document
										.getElementById("multiForm");
								const multiFile = document
										.getElementById("multifile");

								for (var i = 0; i < multiFile.files.length; i++) {
									let fname = multiFile.files[i].name;

									var inputTag = document
											.createElement("input");
									inputTag.setAttribute("type", "hidden");
									inputTag.setAttribute("name", "filenames");
									inputTag.setAttribute("value", fname);

									form.appendChild(inputTag);
								}

								console.log(form.elements.length);
							}
						</script>
					</tr>
					<tr>
						<th colspan="2"><input type="submit" value="글 등록하기">
						</th>
					</tr>
				</tbody>
			</table>
		
		</form>

	</div>

</body>
</html>