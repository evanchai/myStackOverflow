<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.stackoverflow.bean.Post"%>
<%@page import="com.stackoverflow.dao.PostDAOImpl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <base href="<%=basePath%>">
    
    <title>Result</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" href="WEB-PAGES/css/uikit.min.css" />
    <script src="WEB-PAGES/jquery.js"></script>
    <script src="WEB-PAGES/js/uikit.min.js"></script>
  </head>
  
  <body>
  	<%
  	List<Post> postList= new ArrayList<Post>();
  	PostDAOImpl postDAO= new PostDAOImpl();
  	postList = postDAO.findPosts(request.getAttribute("searchKeyword").toString());
  	//postList = request.getAttribute("postList");
  	String title = null;
  	String body = null;
  	String tag = null;
  	%>
	<div class="uk-container uk-container-center uk-margin-top uk-margin-large-bottom">
	
		<div class="uk-vertical-align uk-text-center" style="50% 0 no-repeat; height: 150px;">
                        <div class="uk-vertical-align-middle uk-width-1-2">
                            <h1 class="uk-heading-large">My StackOverflow</h1> 
                        </div>
        </div>
		<div class="uk-width-medium-1-1">
                    <div class="uk-panel uk-panel-box uk-text-center">
                        <div class="uk-form-row">
                        <form name ="searchForm" method="POST" action="servlet/SearchServlet">
                        <input class="uk-width-1-2 uk-form-large" type="text" placeholder="Search Keywords" name="searchKeyword" text="asd">
						<input type="submit" class="uk-button uk-button-primary uk-margin-left" name="searchButton" ></a>
						</form>
                    	</div>
                    </div>
        </div>
		<h1>Result</h1>
		
		<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#switcher-content'}">
                        <li class="uk-active"><a href="#">All</a></li>
                        <li><a href="#">Questions</a></li>
                        <li><a href="#">Answers</a></li>
                        <li><a href="#">Comments</a></li>
        </ul>
                    
		<div class="uk-grid" data-uk-grid-margin="">
		 <%
     		if(!postList.isEmpty())	
     			for(Post p : postList)
     			{	
     				title = p.getPost_title();
     				body = p.getPost_body();
     				tag = p.getPost_tag();
     				
  	 	%> 
                        <div class="uk-width-medium-1-1">
                            <div class="uk-panel uk-panel-box">
                                <h4 class="uk-panel-title"><%=title%>></h4>
                            </div>
                        </div>
        <%
         		}
  	 	%>
  	 	</div>
  	 	
  	 	<ul class="uk-pagination">
                        <li class="uk-disabled"><span><i class="uk-icon-angle-double-left"></i></span></li>
                        <li class="uk-active"><span>1</span></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><span>...</span></li>
                        <li><a href="#">20</a></li>
                        <li><a href="#"><i class="uk-icon-angle-double-right"></i></a></li>
        </ul>	
 </div>
	</body>
</html>