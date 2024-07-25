<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧(<c:if test="${students.size()>0}">科目</c:if><c:if test="${studentss.size()>0}">学生</c:if>)</h2>
			<div class="my-2 text-end px-4">

			</div>
			<form action="TestListSubjectException.action" method="post">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					科目情報
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度 </label>
						<select class="form-select " id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select " id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select " id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${subject_set}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${subject.cd}" <c:if test="${subject.cd==f3.cd}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

                    

					<div class="col-2 text-center">
						<button class="btn btn-primary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("filter")}</div>
				</div>
			</form>

			<form action="TestListStudentException.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					学生情報
					<div class="col-4">
						<label class="form-label" for="student-f3-select">学生番号</label>
							<input class="input-group-text bg-white" id="student-f3-select" type="text" name="f4" value="${student.no}" required>
					</div>

                    

					<div class="col-2 text-center">
						<button class="btn btn-primary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("filter")}</div>
				</div>


			</form>

            <c:choose>
				<c:when test="${students.size()>0}">
					<div>科目：${f3.name}</div>
                    <form method="get">
						<input type="hidden" name="subject" value="${f3.cd}">
						<input type="hidden" name="student_name" value="${f4}">
						<input type="hidden" name="f1" value="${f1}">
						<input type="hidden" name="f2" value="${f2}">
                        <table class="table table-hover">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回目</th>
                                <th>2回目</th>
    
                            </tr>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td>${student.entYear}</td>
                                    <td>${student.classNum}</td>
                                    <td>${student.studentNo}</td>
                                    <td>${student.studentName}</td>
                                    <c:forEach var="i" begin="1" end="2">
                                        <td>${student.getPoint(i)}</td>
                                    </c:forEach>
                                    
                                    

                                </tr>
                            </c:forEach>
                        </table>
						<div class="mt-2 text-warning">${errors.get("filter")}</div>
                    </form>
				</c:when>
				<c:when test="${studentss.size() > 0}">
					<div>氏名:${student.name}(${student.no})</div>
					<form method="get">
						<table class="table table-hover">
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
    
                            </tr>
                            <c:forEach var="sbj" items="${studentss}">
                                <tr>
                                    <td>${sbj.subjectName}</td>
                                    <td>${sbj.subjectCd}</td>
                                    <td>${sbj.num}</td>
                                    <td>${sbj.point}</td>

                                </tr>
                            </c:forEach>
                        </table>
					</form>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
            
        </section>
	</c:param>
</c:import>