<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
    String userid = request.getParameter("userid");
%>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>회원탈퇴</title>
            <style>
                .container {
                    display: flex;
                }

                .content {
                    width: 500px;
                    height: 600px;
                    border: 1px solid red;
                    margin: auto;
                }
            </style>
            <script>
                function checkPwd() {
                    $.ajax({
                        url: '/malant/login/checkpwd',
                        type: 'post',
                        data: {
                            userid: $('#userid').val(),
                            userpwd: $('#userpwd').val()
                        },
                        dataType: "json",
                        success: function (data) {
                            console.log("date : " + data + ", datatype : " + typeof (data));
                            if (data == "fail") {
                                alert("비밀번호가 일치하지 않습니다.");
                                return false;
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
                        }
                    });

                    return true;
                }
            </script>
        </head>

        <body>
            <div class="container">
                <!-- 사이드바 -->
                <div>
                    <%@ include file="../common/sidebar.jsp" %>
                </div>

                <!-- 컨텐츠 영역 -->
                <div class="content">
                    <p1>회원탈퇴</p1>
                    <p4>
                    보안을 위해 회원님의 아이디와 계정 이메일 및 비밀번호를 확인합니다.</p4>
                    <form action="/malant/mleave" method="post" onsubmit="return checkPwd();">
                        <input type="hidden" name="userid" value="<%= userid %>">

                        <table>
                            <tr>
                                <th>아이디</th>
                                <td>
                                    <%= loginMember.getUserId() %>
                                </td>
                            </tr>
                            <tr>
                                <th>비밀번호</th>
                                <td><input type="password" name="userpwd" required></td>
                            </tr>
                        </table>
                        <input type="submit" value="확인">
                        <input type="button" value="취소" onclick="javascript:history.go(-1)">
                    </form>
                </div>

        </body>

        </html>