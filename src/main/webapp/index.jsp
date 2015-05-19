<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OMJ final project</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="scripts/jquery.js" type="text/javascript"></script>
  	<script src="scripts/jquery-ui.custom.js" type="text/javascript"></script>
  	<script src="./scripts/tablesorter.js" type="text/javascript" ></script>
  	<script src="scripts/jquery_utils.js" type="text/javascript"></script>
</head>
<body>
<div id="container">
    <div id="header">
      <div id="title">
        <h1>Ontwerpen met JAVA. Final project 2014-2015</h1>
      </div>
      <div id ="login">
      	<h4>Log In / Register</h4>
      </div>
    </div>
  	<div id="main">
  		<div id="tabs-div">
		  <ul>
		  	<li><a href = "#home_tab">Home</a></li>
		    <li><a href="#quiz_tab">Quiz</a></li>
		    <li><a href="#results_tab">My Results</a></li>
		    <li><a href="#tasks_tab">My tasks</a></li>
			<li><a href="#teacher_results">For teacher. Results</a></li>    
		    <li><a href="#teacher_tasks">For teacher. Assign tasks</a></li>
		  </ul>
		  <div id="home_tab">
	        <h2>Opdracht</h2>
	        <ul class = opdracht>
	        	<li>Bezoekers kunnen browsen door de vragenreeksen - OK</li>
	        	<li>Vragenreeksen kunnen per thema bekeken worden - OK</li>
	        	<li>Er zijn verschillende vragen reeksen beschikbaar - OK</li>
	        	<li>1 reeks bestaat uit een naam en heeft een variabel aantal vragen over een bepaald thema - OK</li>
	        	<li>Vragen van onderstaande types:
	        		<ul>
	        			<li>Ja/nee vraag - OK</li>
	        			<li>Multiple choice door selectie van 1 antwoord - OK</li>
	        			<li>Muliple choice met meedere mogelijke antwoorden - OK</li>
	        			<li>Numeriek antwoord door gebruik van een slider - OK</li>
	        			<li>Numeriek antwoord door ingave via toetsenbord - OK</li>
	        			<li>Drag and drop waarbij het juiste antwoord in een bepaald veld moet geplaatst worden - OK</li>
	        		</ul>
	        	</li>
	        	<li>Deelnemers krijgen na elke vraag te zien of deze correct werd beantwoord - OK</li>
	        	<li>Deelnemers krijgen na de vragenreeks te zien wat hun score is. - OK</li>
	        	<li>Deelnemers kunnen zich registreren - OK</li>
	        	<li>Deelnemers kunnen de behaalde resultaten bekijken. - OK</li>
	        	<li>Beheerders kunnen voor deelnemers een todo lijst opmaken - OK</li>
	        	<li>Vragenreeksen kunnen afhankelijkheden hebben. (x kan pas gedaan worden indien deelnemer reeks y reeds met success voltooide.) OK</li>
	        	<li>Beheerders kunnen rapporten opmaken met de resultaten van 1 of meerdere deelnemers - OK</li>
	        </ul>
		   <h2>Log In</h2>
		   <p>Everyone can see available quizzes. But only inlogged student can solve the quiz</p>
		   <p>If noone is logged in, personal tabs with results and tasks are dissabled</p>
	       <p class=tip>There are a students hardcoded in db with <em>login name=natalia password=111</em> and <em>login name=student password=student</em></p>
	       <p>If student is logged in, tabs with personal tasks and results become enabled</p>
	       <p class=tip>There is a teacher hardcoded in db with <em>login name=sciencekids password=111</em></p>
	       <p>If teacher is logged in, tab for teacher become enabled</p>
	       <h2>Tables</h2>
	       <p>Initially all results (quizzes, tasks and solutions lists) are shown in the table. They can be sorted asc and desc 
	       		by clicking the corresponding table header. To show selected choose desired selection value
	       		in the options lists</p>
	       	<h2>Solve Quiz</h2>
	       	<p>Some quizzes can be only solved if other is solved with at least 50% result</p>
		  </div>
		  <div id="quiz_tab">
	       <table>
			<tr>
			<td>
				<fieldset class = quiz><legend>Filter quizzes</legend>
				<form action="#" id = "findQuiz" >
	        	<table class = 'ajaxTable'>
		        	<tr>
		        		<td><select name="number" id=number></select></td>
		        		<td><select name="thema" id=thema></select></td>
		        		<td><select name="study_year" id=study_year></select></td>
		        		<td><select name="number_questions" id=number_questions></select></td>
		        	</tr>        	
		        </table>
		        </form>
		        </fieldset>
		        <div id="quizTable"></div>
		     </td>
		     </tr>
		    </table>
		  </div>
		  <div id="results_tab">
		    <table>
			<tr>
			<td>
				<fieldset class = quiz><legend>Filter results</legend>
				<form action="#" id = "findSolutions" >
	        	<table class = 'ajaxTable'>
		        	<tr>
		        		<td><select name="numberSolution" id=numberSolution></select></td>
		        		<td><select name="themaSolution" id=themaSolution></select></td>
		        		<td><select name="dateSolution" id=dateSolution></select></td>
		        		<td><select name="scoreSolution" id=scoreSolution></select></td>
		        	</tr>        	
		        </table>
		        </form>
		        </fieldset>
		        <div id="solutionsTable"></div>
		     </td>
		     </tr>
		    </table>
		  </div>
		  <div id="tasks_tab">
		    <table>
			<tr>
			<td>
				<fieldset class = quiz><legend>Assign task to Student</legend>
				<form action="#" id = "taskStudent" >
	        	<table class = 'ajaxTable'>
		        	<tr>
		        		<td><select name="taskQuizStudent" id=taskQuizStudent></select></td>		        		
		        		<td><select name="taskThemaStudent" id=taskThemaStudent></select></td>
		        		<td><select name="taskStatusStudent" id=taskStatusStudent></select></td>
		        	</tr>        	
		        </table>
		        </form>
		        </fieldset>
		        <fieldset class = quiz><legend>Assigned tasks to Student</legend>
		        	<div id="tasksTableStudent"></div>
		        </fieldset>
		     </td>
		     </tr>
		    </table>
		  </div><!-- end tasks_tab -->
		  <div id="teacher_results">
		    <table>
			<tr>
			<td>
				<fieldset class = quiz><legend>Filter results</legend>
				<form action="#" id = "findSolutionsTeacher" >
	        	<table class = 'ajaxTable'>
		        	<tr>
		        		<td><select name="numberSolutionTeacher" id=numberSolutionTeacher></select></td>
		        		<td><select name="themaSolutionTeacher" id=themaSolutionTeacher></select></td>
		        		<td><select name="scoreSolutionTeacher" id=scoreSolutionTeacher></select></td>
		        		<td><select name="studentSolutionTeacher" id=studentSolutionTeacher></select></td>
		        	</tr>        	
		        </table>
		        </form>
		        </fieldset>
		        <div id="solutionsTableTeacher"></div>
		     </td>
		     </tr>
		    </table>
		  </div>
		  <div id="teacher_tasks">
		    <table>
			<tr>
			<td>
				<fieldset class = quiz><legend>Assign task to Student</legend>
				<form action="#" id = "taskTeacher" >
	        	<table class = 'ajaxTable'>
		        	<tr>
		        		<td><select name="taskPersonTeacher" id=taskPersonTeacher></select></td>
		        		<td><select name="taskQuizTeacher" id=taskQuizTeacher></select></td>		        		
		        		<td><select name="taskStatusTeacher" id=taskStatusTeacher></select></td>
		        		<td><input type="button" value="Assign" id = "assignQuizStudent"/></td>
		        	</tr>        	
		        </table>
		        </form>
		        </fieldset>
		        <fieldset class = quiz><legend>Assigned tasks to Student</legend>
		        	<div id="tasksTableTeacher"></div>
		        </fieldset>
		     </td>
		     </tr>
		    </table>
		  </div><!-- end teacher_tasks -->
		</div> <!-- end id=div-tabs -->
  	</div><!-- end id=main -->
</div> <!-- end id=container -->

<!-- dialogs -->
<div id="loginDialog" title="Log In or Register">
	<div id="register_accordion">

		  <h3>Log In</h3>
		  		<form action=# id=loginForm>
		  <div id="login_panel">
		  <p class="validateTips"></p>
			  <table>
				    <tr>
				    	<td> <label for="login_name">Name</label></td>
						<td><input type="text" name="login_name" id="login_name" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
					<tr>
						<td><label for="password">Password</label></td>
						<td><input type="password" name="password" id="password" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
				</table>
		  </div><!--  end login_panel -->
		</form><!-- end loginForm -->

		  <h3>Register</h3>
		  		<form action=# id=registrationForm>
		  <div id="register_panel">
		  		<h2> This is registration form for students. If you are teacher, for registration ask your system administrator</h2>
		    	<table>
		    		<tr>
				    	<td> <label for="first_name">First Name</label></td>
						<td><input type="text" name="first_name" id="first_name" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
					 <tr>
				    	<td> <label for="last_name">Last Name</label></td>
						<td><input type="text" name="last_name" id="last_name" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
				    <tr>
				    	<td> <label for="register_name">Login Name</label></td>
						<td><input type="text" name="register_name" id="register_name" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
					<tr>
						<td><label for="register_password">Password</label></td>
						<td><input type="password" name="register_password" id="register_password" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
					<tr>
				    	<td> <label for="study_year">Study Year</label></td>
						<td><input type="text" name="study_year" id="study_year" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
					<tr>
				    	<td> <label for="birthdate">Birth Date</label></td>
						<td><input type="text" name="birthdate" id="birthdate" class="text ui-widget-content ui-corner-all" /></td>
					</tr>
				</table>
		  </div><!-- end register_panel -->
	  </form><!-- end registrationForm -->
	</div><!-- end register_accordion -->
</div><!-- end loginDialog -->
<div id="QuizSolutionDialog" title = "Solve Quiz">
		  <div id = progress_quiz></div>
		  <div id = progressbar1></div>
		  <div id = progress_score></div>
		  <div id = progressbar2></div>
		  <div id = quiz_question></div>
		  <div id = student_answer></div>
</div>
<div id="QuizFinishDialog" title = "Quiz done!">
	<form>
		  <div id = quiz_done></div>
	</form>
</div>
<!-- end dialogs -->
</body>
</html>