<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.io.*"%>
<%
   String fileName = request.getParameter("file_name");

   String savePath ="boardUpload";   // 경로중요
   ServletContext context = getServletContext();
   String sDownloadPath = context.getRealPath(savePath);   // 8~10라인 대신에 String sDownloadPath="C:\\Project138\\upload"; 로 대신할 수 있음
   String sFilePath = sDownloadPath + "\\" + fileName;   // 실제경로 + 파일명

   byte b[] = new byte[4096];
   File oFile = new File(sFilePath);
   FileInputStream in = new FileInputStream(sFilePath);   // 파일 다운로드는 서버에 있는 파일을 하나 복사시켜주는 원리

   String sMimeType = getServletContext().getMimeType(sFilePath);   // getMimeType(sFilePath) 파일의 유형을 얻어옴?
   System.out.println("sMimeType>>>" + sMimeType);

   //octet.stream은 8비트로 된 일련의 데이터를 뜻합니다. 지정되지 않은 파일 형식을 의미합니다.
   if (sMimeType == null)
      sMimeType = "application/octet-stream";
   response.setContentType(sMimeType);

   //한글 업로드(이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다.)
   String sEncoding = new String(fileName.getBytes("utf-8"), "8859_1");
   //이 부분이 모든 파일 링크를 클릭했을 때 다운로드 화면이 출력되게 처리하는 부분입니다.
   response.setHeader("Content-Disposition", "attachment; filename=" + sEncoding); // 헤드에 첨부할 파일을 지정해준다 / 다운로드에 사용할 파일명을 여기서 정해줌
	
   out.clear(); // 서블릿의 out 객체와의 충돌로 에러 메세지가 남. 그래서 기존의 out 객체를 지워줌.
   
   ServletOutputStream out2 = response.getOutputStream();   // 출력객체 생성
   int numRead;

   //바이트 배열 b의 0번부터 numRead번까지 브라우저로 출력
   while ((numRead = in.read(b, 0, b.length)) != -1) {   // 0번쨰 위치부터 b(바이트타입의 배열)만큼 읽어서 저장 / 읽은 바이트 수를 저장
      out2.write(b, 0, numRead);
   }
   out2.flush();
   out2.close();
   in.close();
%>