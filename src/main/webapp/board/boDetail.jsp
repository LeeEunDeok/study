<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			.container {
				margin: 10px;
			}
			
			#backButton {
				margin: auto;
				text-align: center;
			}
			
			.emoticon {
				text-decoration: none;
				margin-left: 10px;
			}
			
			<
			style type ="text /css "> /* 댓글들을 위한 스타일 지정 */ * {
				padding: 0;
				margin: 0;
				color: #333;
			}
			
			ul {
				list-style: none;
			}
			
			#container {
				padding: 30px 20px;
			}
			
			#insertComment {
				padding: 20px 15px;
				border-bottom: 1px solid #7BAEB5;
			}
			
			#insertComment label {
				display: inline-block;
				width: 80px;
				font-size: 14px;
				font-weight: bold;
				margin-bottom: 10px;
			}
			
			#insertComment input[type='text'], #insertComment textarea {
				border: 1px solid #ccc;
				vertical-align: middle;
				padding: 3px 10px;
				font-size: 12px;
				line-height: 150%;
			}
			
			#insertComment textarea {
				width: 450px;
				height: 120px;
			}
			
			.commentItem {
				font-size: 13px;
				color: #333;
				padding: 15px;
				border-bottom: 1px dotted #ccc;
				line-height: 150%;
			}
			
			.commentItem .id {
				color: #555;
				line-height: 200%;
			}
			
			.commentItem .id input {
				vertical-align: middle;
			}
			
			.commentItem .id .name {
				color: #222;
				font-weight: bold;
				font-size: 14px;
			}
			
			.form-group {
				margin-bottom: 3px;
			}
			
			.form-control {
				height: 25px;
			}
			
			.btn-primary {
				opacity: 0.8;
			}
		</style>
		<script type="text/javascript">
			/* 삭제 버튼 클릭 */
			/* jQuery의 on() 메소드는 선택된 요소에 이벤트 핸들러 함수를 연결해 주는 역할을 합니다. */
			/* 개발자가 직접 지정한 no와 cnum의 속성을 이용하여 삭제하도록 해보겠습니다. */
			
			/* .delete_btn 항목이 click 되었을 때 function(){}을 on() 하세요. */
			$(document).on('click', '.delete_btn', function(){
				if(confirm('선택하신 항목을 삭제하시겠습니까?')){
					
					/*
						삭제 버튼에 사용자가 정의한 no와 cnum의 속성 정보가 들어있습니다.
						no 파라미터는 컨트롤러에서 사용되지는 않지만 반드시 넘겨 주어야 합니다.
						상세 보기 페이지로 이동할 때 필수 사항이기 때문입니다.
					*/
					$.ajax({
						url:'<%=notWithFormTag%>cmDelete',
						data:'no=' + $(this).attr('no') + '&cnum=' + $(this).attr('cnum'),
						type:'get',
						dataType:'json',
						success: function(result, status){
							console.log(result);
							console.log('상태 메시지 : ' + status);
							fillListComment(); /* 삭제 후 화면 다시 읽어오기 */
						}
					});
				}
			});
			
			function fillListComment(){
				$('#comment_list').empty(); /* 이전 목록 우선 지우기 */
				
				/* $.ajax() 함수를 이용하여 데이터를 가져와서 목록을 동적으로 만들어 줍니다.*/
				$.ajax({
					url:'<%=notWithFormTag%>cmList',
					data:'no=' + '${requestScope.bean.no}',
					type:'get',
					dataType:'json',
					success: function(result, status){
						/* console.log('success');
						console.log(result);
						console.log('상태 메시지 : ' + status); */
						
						/* 반환 받은 목록을 반목문을 이용하여 처리합니다. */
						$.each(result, function(idx){/* idx는 색인 번호 입니다. */
							var no = result[idx].no;
							var cnum = result[idx].cnum;
							var id = result[idx].id;
							var contents = result[idx].contents;
							var regdate = result[idx].regdate;
							
							console.log(no + '/' + cnum + '/' + id + '/' + contents + '/' + regdate);
							addNewItem(no,cnum,id,contents,regdate);
						});
					},
					error:function(result, status){
						console.log('error');
						console.log(result);
						console.log('상태 메시지 : ' + status);
					}
					
				});
			}
			
			
				function addNewItem(no, cnum, id, contents, regdate) {
					/* 댓글 1개에 대한 정보를 화면에 보여줍니다. */
			
					var litag = $('<li>'); /* 댓글의 외곽 li 태그  */
					litag.addClass('commentItem');
			
					var ptag = $('<p>'); /* 작성자 정보가 들어갈 태그  */
					ptag.addClass('comment_' + cnum);
			
					var spantag = $('<span>'); /* 작성자 이름이 들어갈 태그  */
					spantag.addClass('name');
					spantag.html(id + "님");
			
					var spandate = $('<span>'); /* 작성 일자가 들어갈 태그  */
					spandate.html("&nbsp;&nbsp;/&nbsp;&nbsp;" + regdate
							+ '&nbsp;&nbsp;&nbsp;');
			
					/* 로그인한 사람이 작성한 댓글이면 삭제 가능 */
					if (id == '${sessionScope.logInfo.id}') {
						var deleteTag = $('<input>'); /* 삭제 버튼 */
						var attrlist = {
							'id' : id,
							'type' : 'button',
							'value' : '삭제',
							'class' : 'btn btn-xs btn-outline-primary',
							'cnum' : cnum,
							'no' : no
						};
						deleteTag.attr(attrlist);
						deleteTag.addClass('delete_btn');
			
						var updateTag = $('<input>'); /* 수정 버튼 */
						var attrlist = {
							'id' : id,
							'type' : 'button',
							'value' : '수정',
							'class' : 'btn btn-xs btn-outline-primary',
							'cnum' : cnum,
							'no' : no
						};
						updateTag.attr(attrlist);
						updateTag.addClass('update_btn');
					} else {
						var inputtag = '';
					}
			
					var contents_p = $('<p>'); /* 작성한 댓글 내용 */
					contents_p.html(contents);
					contents_p.addClass('contents_' + cnum)
			
					/* 조립하기(compose up) */
					ptag.append(spantag).append(spandate).append(deleteTag).append(
							updateTag);
					litag.append(ptag).append(contents_p);
			
					$('#comment_list').append(litag);
				}
			
				$(document).ready(function() {
			
					fillListComment();
					/* 사용자가 댓글을 입력하고, 전송 버튼을 눌렀습니다.*/
					$('#comment_form').submit(function() {
			
						/* 입력한 댓글이 없으면 전송이 불가능합니다. */
						if (!$('#contents').val()) {
							alert('댓글을 반드시 입력해 주세요.');
							$('#contents').focus();
							return false;
						}
			
						/* submit 버튼의 캡션 정보 가져오기*/
						var caption = $('button[type="submit"]').text();
						// alert(caption);
			
						if (caption == '저장하기') {
							/* jQuery의 Ajax 기능 중에서 post 방식을 이용하여 데이터를 전송합니다.*/
			
							var URL = '<%=notWithFormTag%>cmInsert';
						
						// 명시된 폼 양식 내의 모든 파라미터를 인코딩이 적용된 문자열을 만들어 줍니다.
						var data=$('#comment_form').serialize();
						
						$.post(URL,data,function(data,status,xhr){
							fillListComment();
							$('#contents').val(''); //입력한 글자 지우기
							return true;
						}).fail(function(){
							alert('댓글 작성에 실패 하였습니다.');
							return false;
						});
						
						return false;
					}else if(caption='수정하기'){
						return false;
						
					}
				});
			});
			
		</script>
	</head>
	<body>
		<div class="row">
			<div class="col"></div>
			<div class="col">
				<div class="container mt-3">
					<h2>[${requestScope.bean.no}]번 게시물 정보</h2>
					<table class="table table-hover">
						<thead>
						<tbody>
							<tr>
								<td align="center">글 번호</td>
								<td>${requestScope.bean.no}</td>
							</tr>
							<tr>
								<td align="center">작성자</td>
								<td><c:if test="${not empty requestScope.bean.id}">
										${requestScope.bean.id}
									</c:if> <c:if test="${empty requestScope.bean.id}">
										탈퇴회원
									</c:if></td>
							</tr>
							<tr>
								<td align="center">글 제목</td>
								<td>${requestScope.bean.subject}</td>
							</tr>
							<tr>
								<td align="center">글 내용</td>
								<td>${requestScope.bean.contents}</td>
							</tr>
							<tr>
								<td align="center">조회수</td>
								<td>${requestScope.bean.readhit}</td>
							</tr>
							<tr>
								<td align="center">작성일자</td>
								<td>${requestScope.bean.regdate}</td>
							</tr>
	
						</tbody>
					</table>
					<div id="backButton">
						<button type="button" class="btn btn-primary"
							onclick="location.href='<%=notWithFormTag%>boList${requestScope.paramList }'">
							돌아가기</button>
						&nbsp;&nbsp;&nbsp;
						<!-- 이모티콘 보여 주는 영역 -->
						<a class="emoticon"
							href="<%=notWithFormTag%>boEmoticon&emoticon=likes&no=${bean.no}">
							<img src="<%=appName%>/image/likes.png" width="30px"
							height="30px" alt="좋아요"> ${emoticon.likes}
						</a> <a class="emoticon"
							href="<%=notWithFormTag%>boEmoticon&emoticon=hates&no=${bean.no}">
							<img src="<%=appName%>/image/hates.png" width="30px"
							height="30px" alt="싫어요"> ${emoticon.hates}
						</a> <a class="emoticon"
							href="<%=notWithFormTag%>boEmoticon&emoticon=love&no=${bean.no}">
							<img src="<%=appName%>/image/love.png" width="30px" height="30px"
							alt="사랑해요"> ${emoticon.love}
						</a> <a class="emoticon"
							href="<%=notWithFormTag%>boEmoticon&emoticon=sad&no=${bean.no}">
							<img src="<%=appName%>/image/sad.png" width="30px" height="30px"
							alt="슬퍼요"> ${emoticon.sad}
						</a> <a class="emoticon"
							href="<%=notWithFormTag%>boEmoticon&emoticon=angry&no=${bean.no}">
							<img src="<%=appName%>/image/angry.png" width="30px"
							height="30px" alt="화나요"> ${emoticon.angry}
						</a>
					</div>
				</div>
				<div>
					<%-- 댓글 영역(Comment Zone) --%>
					<ul id="comment_list">
						<%-- 여기에 동적으로 요소들을 추가합니다. --%>
					</ul>
				</div>
				<div id="insertComment">
					<form id="comment_form" method="post" role="form"
						class="form-horizontal">
						<table class="table">
							<thead>
							</thead>
							<tbody>
								<tr>
									<td><label for="id" class="col-xs-3 col-lg-3 control-label">작성자</label>
									</td>
									<td><input type="hidden" name="no" value="${bean.no}" /> <input
										type="text" name="fakeid" id="fakeid" class="form-control"
										size="10" disabled="disabled"
										value="${sessionScope.logInfo.name}(${sessionScope.logInfo.id})님">
										<input type="hidden" name="id" id="id"
										value="${sessionScope.logInfo.id}"></td>
								</tr>
								<tr>
									<td><label for="contents"
										class="col-xs-3 col-lg-3 control-label">댓글 내용</label></td>
									<td><textarea id="contents" name="contents" rows="3"
											cols="50"></textarea></td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="submit" class="btn btn-info">저장하기</button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<div class="col"></div>
		</div>
	</body>
</html>