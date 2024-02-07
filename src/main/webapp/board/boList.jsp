<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 목록 페이지 02</title>
	<style type="text/css">
		.container{margin-top: 10px;}
     	.rounded-pill{opacity: 0.7;}
     	.mode, .keyword, .col{margin: auto;}
     	.form-control-sm{border:1px solid Gainsboro;} 
 	</style>
	<script type="text/javascript">
	    $(document).ready(function(){
	        /* 필드 검색시 체크한 콤보박스 상태 보존 */
	        var optionList = $('#mode option'); /* 옵션 목록 */
	        for (var i = 0; i < optionList.length; i++) {
	            if (optionList[i].value == '${requestScope.paging.mode}') {
	                optionList[i].selected = true;
	            }
	        }
	
	        /* 필드 검색시 입력한 keyword 내용 보존 */
	        $('#keyword').val('${requestScope.paging.keyword}');
	    });
	
	    function searchAll() { /* 전체 검색 */
	        location.href = '<%=notWithFormTag%>boList';
	    }
		
		function writeForm(){ /* 게시물 작성 */
			location.href='<%=notWithFormTag%>boInsert';
		}
		
		function deleteBoard(no, paramList){ /* 게시물 삭제 */
			/* no : 삭제될 게시물 번호, paramList : 페이징 관련 파라미터들 */
			var response = window.confirm('해당 게시물을 삭제하시겠습니까?');
			
			if(response == true){
				var url = '<%=notWithFormTag%>boDelete&no=' + no + paramList;
				location.href = url;
			}else{
				alert('게시물 삭제가 취소되었습니다.');
				return false;
			}
		}
	</script>
</head>
<body>
	<div class="container mt-3">
		<h2>게시물 목록</h2>
		<p>사용자들이 작성한 게시물 목록을 보여주는 페이지입니다.</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>글제목</th>
					<th>글내용</th>
					<th>조회수</th>
					<th>작성일자</th>
					<th>수정</th>
					<th>삭제</th>
					<th>답글</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9" align="center">
                  		<div class="row">
                     		<div class="col-sm-1"></div>
                     		<div class="col-sm-10">
                        		<form action="<%=withFormTag%>" method="get">
	                   				<input type="hidden" name="command" value="boList">
	                        		<div class="row">
	                              	<div class="col-sm-12">
	                              		<select class="form-control-sm" id="mode" name="mode">
	                                    	<option value="all">--- 선택해주세요.
	                                    	<option value="id">작성자
	                                    	<option value="subject">글제목
	                                    	<option value="contents">글내용
	                                 	</select>
	                                 	<input class="form-control-sm" type="text" id="keyword" name="keyword">
	                                 	<!-- 참조 BoardDao.java, SuperDao.java -->
	                                 	<button class="form-control-sm btn btn-warning" type="submit">검색</button>
	                                 
	                                 	<button class="form-control-sm btn btn-warning" type="button" onclick="searchAll();">전체 검색</button>
	                                 
	                                 	<button class="form-control-sm btn btn-info" type="button" onclick="writeForm();">글쓰기</button>
	                                 	
	                                 	&nbsp;&nbsp;
	                                 	<span class="label label-default">
	                                 		<!-- 총 ?건 in Paging.java -->
	                                    	${requestScope.paging.pagingStatus}
	                                 	</span>
                                 	</div>                        
	                            	</div>
                         		 </form>
                      		 </div>
                      		 <div class="col-sm-1"></div>
                   		 </div>
               		 </td>
            	</tr>
				<c:forEach var="bean" items="${dataList}">
					<tr>
						<td align="center">${bean.no}</td>
						<td>
							<c:if test="${not empty bean.id}">
								${bean.id}
							</c:if>
							<c:if test="${empty bean.id}">
								탈퇴 회원
							</c:if>
						</td>
						<td>
							<%-- 로그인 아이디와 작성자의 아이디가 다르면 true 로 지정합니다. --%>
							<%-- readhitUpdate 파라미터는 조회수 업데이트를 할 것인지 결정해주는 boolean 변수입니다. --%>
							<c:set var="readhitUpdate" value="${not (sessionScope.logInfo.id == bean.id)}" />
							
							<%-- 글 제목을 클릭하면 상세보기 페이지로 이동합니다.(re도 포함됨) --%>
							<a href="<%=notWithFormTag%>boDetail&no=${bean.no}&readhitUpdate=${readhitUpdate}${requestScope.paging.flowParameter}">
								<c:forEach var="i" begin="1" end="${bean.depth}" step="1">
									<span class="badge rounded-pill bg-secondary">re</span>
								</c:forEach>
								${bean.subject}
							</a>
						</td>
						
						<td>${bean.contents}</td>
						<td>
							<c:if test="${bean.readhit >= 25 }">
								<span class="badge rounded-pill bg-primary">
									${bean.readhit}
								</span>
							</c:if>
							
							<c:if test="${bean.readhit < 25 }">
								<span class="badge rounded-pill bg-danger">
									${bean.readhit}
								</span>
							</c:if>
						</td>
						<td>${bean.regdate}</td>
						<td>
							<c:if test="${sessionScope.logInfo.id == bean.id}">
								<a href="<%=notWithFormTag%>boUpdate&no=${bean.no}${requestScope.paging.flowParameter}">수정</a>
							</c:if>
						</td>
						<td>
							<c:if test="${sessionScope.logInfo.id == bean.id}">       <!-- flowParameter in Paging.java -->
								<a href="#" onclick="return deleteBoard('${bean.no}','${requestScope.paging.flowParameter}');">삭제</a>
							</c:if>
						</td>
						<td>
							<c:set var="replyInfo" value="&groupno=${bean.groupno}&orderno=${bean.orderno}&depth=${bean.depth}" />
							<c:if test="${sessionScope.logInfo.id == bean.id}">
								<a href="<%=notWithFormTag%>boReply&no=${bean.no}${requestScope.paging.flowParameter}${replyInfo}">
									답글
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		${requestScope.paging.pagingHtml}
	</div>
</body>
</html>