<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member, java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/malant/resources/common/css/font.css" />
<script src="/malant/resources/common/js/jquery-3.7.0.min.js"></script></head>
<body>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>판매회원 전용페이지</title>
    <style>
    	
		title{
		magin-botton:35px;
		}    
    
    
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 36px;
            color: #333;
        }

        #login-form {
            margin-top: 20px;
        }

        input[type="text"]{
       		width: 80%; 
            padding: 12px;
            margin: 5px auto; 
            display: block;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
        
        
        
        } input[type="password"] {
            width: 80%; 
            padding: 12px;
            margin: 5px auto; 
            display: block;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 40px;
        }

        #login-button {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
     		
        }

        #login-button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
    <div class="container">
        <h1>판매회원 전용페이지</h1>
        <form action="/malant/login" method="post" id="login-form">
            <input type="userid" name="sellerid" placeholder="아이디">
            <input type="userpwd" name="sellerpwd" placeholder="비밀번호">
            <input type="hidden" name="loc" value="seller">
        </form>
        <button type="submit" id="login-button" form="login-form">로그인</button>
    </div>
  
</body>
</html>

</body>
</html>
