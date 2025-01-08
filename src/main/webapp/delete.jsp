<%@page import="guestbook.GuestBookDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="guestbook.GuestBookDao"%>

<%
ServletContext context = getServletContext();
String dbUser = context.getInitParameter("dbUser");
String dbPass = context.getInitParameter("dbPass");

String no = request.getParameter("no");

GuestBookDao dao = new GuestBookDaoImpl(dbUser, dbPass);
dao.delete(Long.valueOf(no));

response.sendRedirect("index.jsp");
%>