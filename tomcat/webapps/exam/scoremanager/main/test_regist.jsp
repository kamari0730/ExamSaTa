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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<div class="my-2 text-end px-4">

			</div>
			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
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
							<c:forEach var="subject" items="${subjects}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${subject.cd}" <c:if test="${subject.cd==f3.cd}">selected</c:if>>${subject.name}</option>
							</c:forEach>
						</select>
					</div>

                    <div class="col-2">
						<label class="form-label" for="student-f4-select">回数 </label>
						<select class="form-select " id="student-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="num" items="${test_num_set}">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${num}" <c:if test="${num==f4}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("filter")}</div>
				</div>
			</form>

            <c:choose>
				<c:when test="${tests.size()>0}">
					<div>科目：${f3.name}(${f4}回)</div>
                    <form action="TestRegistException.action" method="post">
						<input type="hidden" name="subject" value="${f3.cd}">
						<input type="hidden" name="count" value="${f4}">
						<input type="hidden" name="f1" value="${f1}">
						<input type="hidden" name="f2" value="${f2}">
                        <table class="table table-hover">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
    
                            </tr>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.student.entYear}</td>
                                    <td><input type="hidden" name="cno" value="${test.student.classNum}">${test.student.classNum}</td>
                                    <td><input type="hidden" name="no" value="${test.student.no}"> ${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td><input type="number" name="point" min="0" max="100" <c:if test="${test.point != -1}">value="${test.point}"</c:if>></td>
                                    

                                </tr>
                            </c:forEach>
                        </table>
                        <div class="col-2 text-center">
                            <button class="btn btn-secondary" id="filter-button">登録して終了</button>
                        </div>
						<div class="mt-2 text-warning">${errors.get("point")}</div>
                    </form>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
            
        </section>
	</c:param>
</c:import>