/**
 * JQuery Utilities 
 * Final Opdracht
 * Ontwerpen met Java. GroepT 2014-2015
 * 
 * @auteur Natalia Dyubankova
 * @created 28/04/2015
 * 
 */
var quizzes;
var answer_to_check = "";
var score = 0;
var student;
var teacher;
var matchAnswers = [];
var quizAnswers = "quizAnswers=";

//executed on load
$(function() {
	//connect data base
//	  $.ajax({ url: "connect", datatype: "text", success: function(data) { alert(data) } });
	  
	//fill in quiz selection lists (Tab Quiz)
	  fillQuizzes();
	  
	// tabbed panels
    $( "#tabs-div" ).tabs({
    	collapsible: true,
    	disabled: [ 2, 3, 4, 5 ]
    });
    
    //log in dialog windows 
    loginDialog(); 
    $("#login").click(openLogin);
    
    //accordion
    $("#register_accordion").accordion({
    	collapsible: true
    });
});

function fillQuizzes()
{
	$.ajax({
		type: "GET",
		url: "quizzes",
		dataType: "json",
		success: function(json)
	      {
			quizzes = json;
			 var headings = ["Quiz ID", "Thema", "Study Year", "Number of questions"];
			 var ids = [], rows = [];
			 var themas = new Set(),  years = new Set(), questions = new Set();
			 ids[0] = "Quiz ID";
			 themas.add("Thema");
			 years.add("Study year");
			 questions.add("Number of questions");
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].id, [json[i-1].thema], [json[i-1].studyYear], [json[i-1].questions.length]];
				 ids[i] = json[i-1].id;
				 themas.add(json[i-1].thema);
				 years.add(json[i-1].studyYear);
				 questions.add(json[i-1].questions.length);
			 }
			var number = document.getElementById('number');
			var thema = document.getElementById('thema');
			var year = document.getElementById('study_year');
			var question = document.getElementById('number_questions');
			for(var i = 0; i < ids.length; i++) 
			{
			    var optNumber = document.createElement('option');
			    optNumber.innerHTML = ids[i];
			    optNumber.value = ids[i];
			    number.appendChild(optNumber);
			}
			
			themas.forEach(function(value){
				var optThema = document.createElement('option');
				optThema.innerHTML = value;
				optThema.value = value;
				thema.appendChild(optThema);
			});
			
			years.forEach(function(value){
				var optYear = document.createElement('option');
				optYear.innerHTML = value;
				optYear.value = value;
				year.appendChild(optYear);
			})
			
			questions.forEach(function(value){
				var optQuestion = document.createElement('option');
				optQuestion.innerHTML = value;
				optQuestion.value = value;
				question.appendChild(optQuestion);
			})
			
			htmlInsert("quizTable", getSortedTable(headings, rows, 'quizResult'));
			
			//sort table
			$("#quizResult").tablesorter();
			
			//make cell clickable
			  $('.clickableCell').click(function(e){openQuizSolutionDialog(e.target.innerHTML, 0)});
			//add quiz info on hover
			  $().hover
			  
			//couple option selection with function
			$('#number').change(selectQuizzes); 
			$('#thema').change(selectQuizzes);
			$('#study_year').change(selectQuizzes);
			$('#number_questions').change(selectQuizzes);
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function selectQuizzes()
{
	$.ajax({
		type: "GET",
		url: "selectQuizzes",
		data: $("#findQuiz").serialize(),
		dataType: "json",
		success: function(json)
	      {
			 var headings = ["Quiz ID", "Thema", "Study Year", "Number of questions"];
			 var rows = [];
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].id, [json[i-1].thema], [json[i-1].studyYear], [json[i-1].questions.length]];
			 }
			
			htmlInsert("quizTable", getSortedTable(headings, rows, 'quizResult'));
			
			//sort table
			$("#quizResult").tablesorter();
			
			//make cell clickable
			  $('.clickableCell').click(function(e){openQuizSolutionDialog(e.target.innerHTML, 0)});
			//add quiz info on hover
			  $().hover;
			  
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function fillSolutionsForStudent()
{
	$.ajax({
		type: "GET",
		url: "solutions",
		data: student,
		dataType: "json",
		success: function(json)
	      {
			
			 var headings = ["Quiz ID", "Thema", "Date", "Score", "Question", "Correct answer", "Your answer"];
			 var ids = [], rows = [], quiz_questions = []; correct_answers = [], student_answers = [];
			 var themas = new Set(),  dates = new Set(), scores = new Set();
			 ids[0] = "Quiz ID";
			 themas.add("Thema");
			 dates.add("Date");
			 scores.add("Score");
			 for(var i = 1; i <= json.length; i++) 
			 {
				 var questions_as_string = [], correct_answers_as_string = [], student_answers_as_string = [];
				 for (var q in json[i - 1].questionsAsString)
					 {
					 	questions_as_string.push(json[i - 1].questionsAsString[q]);
					 }
				 for (var ca in json[i -1].correctAnswersAsString)
					 {
				 		correct_answers_as_string.push(json[i -1].correctAnswersAsString[ca]);
					 }
				 for(var a in json[i-1].answersAsString)
					 {
					 	student_answers_as_string.push(json[i-1].answersAsString[a]);
					 }
				 quiz_questions[i-1] = getBulletedList(questions_as_string);
				 correct_answers[i-1] = getBulletedList(correct_answers_as_string);
				 student_answers[i-1] = getBulletedList(student_answers_as_string);
				 rows[i-1] = [json[i-1].id, [json[i-1].thema], json[i-1].date, [json[i-1].score], quiz_questions[i-1], correct_answers[i-1], student_answers[i-1] ];
				 ids[i] = json[i-1].id;
				 themas.add(json[i-1].thema);
				 dates.add(json[i-1].date_solution);
				 scores.add(json[i-1].score);
			 }
			var number = document.getElementById('numberSolution');
			var thema = document.getElementById('themaSolution');
			var date = document.getElementById('dateSolution');
			var score = document.getElementById('scoreSolution');
			for(var i = 0; i < ids.length; i++) 
			{
			    var optNumber = document.createElement('option');
			    optNumber.innerHTML = ids[i];
			    optNumber.value = ids[i];
			    number.appendChild(optNumber);
			}
			
			themas.forEach(function(value){
				var optThema = document.createElement('option');
				optThema.innerHTML = value;
				optThema.value = value;
				thema.appendChild(optThema);
			});
			
			dates.forEach(function(value){
				var optDate = document.createElement('option');
				optDate.innerHTML = value;
				optDate.value = value;
				date.appendChild(optDate);
			})
			
			scores.forEach(function(value){
				var optScore = document.createElement('option');
				optScore.innerHTML = value;
				optScore.value = value;
				score.appendChild(optScore);
			})
			
			htmlInsert("solutionsTable", getSortedTable(headings, rows, 'solutionsResult'));
			
			//sort table
			$("#solutionsResult").tablesorter();
			
			//add quiz info on hover
			  $().hover
			  
			//couple option selection with function
			$('#numberSolution').change(selectSolutionsStudent); 
			$('#themaSolution').change(selectSolutionsStudent);
			$('#scoreSolution').change(selectSolutionsStudent);
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function selectSolutionsStudent()
{
	$.ajax({
		type: "GET",
		url: "selectSolutionsStudent",
		data: $("#findSolutions").serialize(),
		dataType: "json",
		success: function(json)
	      {
			var headings = ["Quiz ID", "Thema", "Date", "Score", "Question", "Correct answer", "Your answer"];
			 var rows = [], quiz_questions = []; correct_answers = [], student_answers = [];
			 for(var i = 1; i <= json.length; i++) 
			 {
				 var questions_as_string = [], correct_answers_as_string = [], student_answers_as_string = [];
				 for (var q in json[i - 1].questionsAsString)
					 {
					 	questions_as_string.push(json[i - 1].questionsAsString[q]);
					 }
				 for (var ca in json[i -1].correctAnswersAsString)
					 {
					 correct_answers_as_string.push(json[i -1].correctAnswersAsString[ca]);
					 }
				 for(var a in json[i-1].answersAsString)
					 {
					 	student_answers_as_string.push(json[i-1].answersAsString[a]);
					 }
				 quiz_questions[i-1] = getBulletedList(questions_as_string);
				 correct_answers[i-1] = getBulletedList(correct_answers_as_string);
				 student_answers[i-1] = getBulletedList(student_answers_as_string);
				 rows[i-1] = [json[i-1].id, [json[i-1].thema], json[i-1].date, [json[i-1].score], quiz_questions[i-1], correct_answers[i-1], student_answers[i-1] ];
			 }
			
			htmlInsert("solutionsTable", getSortedTable(headings, rows, 'solutionsResult'));
			
			//sort table
			$("#solutionsResult").tablesorter();
			
			//add quiz info on hover
			  $().hover;
			  
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function fillTasksForStudent()
{
	$.ajax({
		type: "GET",
		url: "tasks",
		dataType: "json",
		success: function(json) 
		{
			var headings = [ "Quiz ID", "Thema", "Status"];
			 var rows = [];
			 var ids = new Set(), themas = new Set(), statuses = new Set();
			 ids.add("Quiz ID");
			 themas.add("Thema");
			 statuses.add("Status");
		
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].quiz.id , json[i-1].thema, json[i-1].status];
				 ids.add(json[i-1].quiz.id);
				 statuses.add(json[i-1].status);
				 themas.add(json[i-1].quiz.thema);
			 }
			var number = document.getElementById('taskQuizStudent');
			var thema = document.getElementById('taskThemaStudent');
			var status = document.getElementById('taskStatusStudent');
		
			ids.forEach(function(value){
			    var optNumber = document.createElement('option');
			    optNumber.innerHTML = value;
			    optNumber.value = value;
			    number.appendChild(optNumber);
			});
			
			themas.forEach(function(value){
				var optThema = document.createElement('option');
				optThema.innerHTML = value;
				optThema.value = value;
				thema.appendChild(optThema);
			});
			
			statuses.forEach(function(value){
				var optStatus = document.createElement('option');
				optStatus.innerHTML = value;
				optStatus.value = value;
				status.appendChild(optStatus);
			});
			
			htmlInsert("tasksTableStudent", getSortedTable(headings, rows, 'tasksResultStudent'));
			
			//sort table
			$("#tasksResultStudent").tablesorter();
			
			//make cell clickable
			  $('.clickableCell').click(function(e){openQuizSolutionDialog(e.target.innerHTML, 0)});
			
			//add quiz info on hover
			  $().hover;
			  
			//couple option selection with function
			$('#taskQuizStudent').change(selectTaskStudent); 
			$('#taskThemaStudent').change(selectTaskStudent);
			$('#taskStatusStudent').change(selectTaskStudent);
			
		 },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function selectTaskStudent()
{
	$.ajax({
		type: "GET",
		url: "selectTasksStudent",
		data: $('#taskStudent').serialize() + "&student=" + student.id,
		dataType: "json",
		success: function(json)
	      {
			 var headings = ["Student", "Quiz ID", "Status"];
			 var rows = [];
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].quiz.id , json[i-1].thema, json[i-1].status];
			 }
			
			htmlInsert("tasksTableStudent", getSortedTable(headings, rows, 'tasksResultStudent'));
			
			//sort table
			$("#tasksResultStudent").tablesorter();
			
			//add quiz info on hover
			  $().hover;
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function fillSolutionsForTeacher()
{
	$.ajax({
		type: "GET",
		url: "solutions",
		dataType: "json",
		success: function(json)
	      {
			 var headings = ["Quiz ID", "Thema", "Date", "Score", "Student"];
			 var rows = [];
			 var ids = new Set(), themas = new Set(),  dates = new Set(), scores = new Set(), students = new Set();
			 ids.add("Quiz ID");
			 themas.add("Thema");
			 scores.add("Score");
			 students.add("Student");
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].quiz.id, [json[i-1].thema], json[i-1].date, [json[i-1].score], json[i-1].author.firstName + " " + json[i-1].author.secondName];
				 ids.add(json[i-1].quiz.id);
				 themas.add(json[i-1].thema);
				 scores.add(json[i-1].score);
				 students.add(json[i-1].author.firstName + " " + json[i-1].author.secondName + " id=" + json[i-1].author.id)
			 }
			var number = document.getElementById('numberSolutionTeacher');
			var thema = document.getElementById('themaSolutionTeacher');
			var score = document.getElementById('scoreSolutionTeacher');
			var st = document.getElementById('studentSolutionTeacher');

			ids.forEach(function(value){
			    var optNumber = document.createElement('option');
			    optNumber.innerHTML = value;
			    optNumber.value = value;
			    number.appendChild(optNumber);
			});
			
			themas.forEach(function(value){
				var optThema = document.createElement('option');
				optThema.innerHTML = value;
				optThema.value = value;
				thema.appendChild(optThema);
			});
			
			
			scores.forEach(function(value){
				var optScore = document.createElement('option');
				optScore.innerHTML = value;
				optScore.value = value;
				score.appendChild(optScore);
			});
				
			students.forEach(function(value){
				var optSt = document.createElement('option');
				optSt.innerHTML = value;
				optSt.value = value;
				st.appendChild(optSt);
			});
			
			htmlInsert("solutionsTableTeacher", getSortedTable(headings, rows, 'solutionsResultTeacher'));
			
			//sort table
			$("#solutionsResultTeacher").tablesorter();
			
			//add quiz info on hover
			  $().hover;
			  
			//couple option selection with function
			$('#numberSolutionTeacher').change(selectSolutionTeacher); 
			$('#themaSolutionTeacher').change(selectSolutionTeacher);
			$('#scoreSolutionTeacher').change(selectSolutionTeacher);
			$('#studentSolutionTeacher').change(selectSolutionTeacher);
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function selectSolutionTeacher()
{
	$.ajax({
		type: "GET",
		url: "selectSolutionsTeacher",
		data: $("#findSolutionsTeacher").serialize(),
		dataType: "json",
		success: function(json)
	      {
			var headings = ["Quiz ID", "Thema", "Date", "Score", "Student"];
			var rows = [];
			 
			 for(var i = 1; i <= json.length; i++) 
			 {
				 rows[i-1] = [json[i-1].quiz.id, [json[i-1].thema], json[i-1].date, [json[i-1].score], json[i-1].author.firstName + " " + json[i-1].author.secondName];
			 }
						
			htmlInsert("solutionsTableTeacher", getSortedTable(headings, rows, 'solutionsResultTeacher'));
			
			//sort table
			$("#solutionsResultTeacher").tablesorter();
			
			//add quiz info on hover
			  $().hover;
			  
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});
}

function fillTasksForTeacher()
{
	$.ajax({
		type: "GET",
		url: "tasks",
		dataType: "json",
		success: function(json) 
		{
			var headings = ["Student", "Quiz ID", "Status", "Modify"], statuses = ['Status', 'todo', 'done'];
			 var rows = [];
			 var ids = new Set(), students = new Set();
			 ids.add("Quiz ID");
			 students.add("Student");
			 for(var i = 1; i <= json.length; i++) 
				 {
				 	var buttonvalues = json[i-1].student.id + ';' + json[i-1].quiz.id ;
				 	var buttonnames = ("$'#unassignQuizStudent'" + i);
					 var unassignButton = '<input type="button" value="Unassign" class = unassignButton id = "unassignQuizStudent' + i + '\"/>';
//					 
					 rows[i-1] = [json[i-1].student.firstName + " " + json[i-1].student.secondName, json[i-1].quiz.id , json[i-1].status, unassignButton];
					 students.add(json[i-1].student.firstName + " " + json[i-1].student.secondName + " id=" + json[i-1].student.id);

				 }
			 $('#unassignQuizStudent 1').click( function() 
					 { 
					 	alert( "something" ) ;
					 });
//			 $('#unassignQuizStudent' + i).val()
//			 buttonvalues, unassignQuizStudent
			 for(var i = 0; i < quizzes.length; i++)
				 {
				 	ids.add(quizzes[i].id);
				 }
			var number = document.getElementById('taskQuizTeacher');
			var st = document.getElementById('taskPersonTeacher');
			var status = document.getElementById('taskStatusTeacher');

			ids.forEach(function(value){
			    var optNumber = document.createElement('option');
			    optNumber.innerHTML = value;
			    optNumber.value = value;
			    number.appendChild(optNumber);
			});
			
			students.forEach(function(value){
				var optSt = document.createElement('option');
				optSt.innerHTML = value;
				optSt.value = value;
				st.appendChild(optSt);
			});
			
			statuses.forEach(function(value){
				var optStatus = document.createElement('option');
				optStatus.innerHTML = value;
				optStatus.value = value;
				status.appendChild(optStatus);
			});
			
			htmlInsert("tasksTableTeacher", getSortedTable(headings, rows, 'tasksResultTeacher'));
			
			//sort table
			$("#tasksResultTeacher").tablesorter();
			
			//add quiz info on hover
			  $().hover;
			  
			//couple option selection with function
			$('#taskQuizTeacher').change(selectTaskTeacher); 
			$('#taskPersonTeacher').change(selectTaskTeacher);
			$('#taskStatusTeacher').change(selectTaskTeacher);
			
			//add buttons listeners
			$('#assignQuizStudent').click(assignQuizStudent);
		},
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function selectTaskTeacher()
{
	$.ajax({
		type: "GET",
		url: "selectTasksTeacher",
		data: $('#taskTeacher').serialize(),
		dataType: "json",
		success: function(json)
	      {
			 var headings = ["Student", "Quiz ID", "Status", "Modify"];
			 var rows = [];
			 for(var i = 1; i <= json.length; i++) 
			 {
				 var unassignButton = '<input type="button" value="Unassign" id = "unassignQuizStudent' + i + '"/>';
				 rows[i-1] = [json[i-1].student.firstName + " " + json[i-1].student.secondName, json[i-1].quiz.id , json[i-1].status, unassignButton];
				 $('#unassignQuizStudent' + i).click(unassignQuizStudent);
			 }
			
			htmlInsert("tasksTableTeacher", getSortedTable(headings, rows, 'tasksResultTeacher'));
			
			//sort table
			$("#tasksResultTeacher").tablesorter();
			
			//add quiz info on hover
			  $().hover;
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}


//***************Dialog Windows Functions **********************

function openLogin()
{	
	$( "#loginDialog" ).dialog( "open" );
}


function openQuizSolutionDialog(quizID, questionNumber)
{ 
	quizDialog(quizID, questionNumber);
	$( "#QuizSolutionDialog" ).dialog( "open" ); 
}



function finishQuizDialog(quizID)
{
	finishDialog(quizID);
	$( "#QuizFinishDialog" ).dialog( "open" ); 
}

function loginDialog()
{
	var name = $( "#login_name" );
	$("#birthdate").datepicker({
				   	 changeMonth: true,
				   	 changeYear:true,
				   	 dateFormat: "dd-mm-yy",
				     numberOfMonths: 2});
	
//	tips = $( ".validateTips" )
//	allFields = $( [] ).add( name );
	
	$( "#loginDialog" ).dialog({
		autoOpen: false,
		height: 500,
		width: 800,
		modal: true,
		buttons: {
			"Submit": function() {
				var send_data = "";
				var bValid = true;
				var login_or_registration_login = $('#loginForm').css('display');
				var login_or_registration_registration = $('#registrationForm').css('display');
				
				if (login_or_registration_login == 'block') send_data = $('#loginForm').serialize();
				else if (login_or_registration_registration == 'block') send_data = $('#registrationForm').serialize();
//				allFields.removeClass( "ui-state-error" );
				alert(send_data);

				bValid = checkLength( name, "name", 3, 16 );

				if ( bValid ) {
					$.ajax({
						type: "GET",
						url: "login",
						data: send_data,
						dataType: "json",
						success: function(json)
					      {
							if(json.studyYear != null) 
								{
									student = json;
									teacher = null; // in case if teacher was logged in before
									
									$('#login').html("<h4>Hello, " + student.firstName + " " + student.secondName + "</h4>");
									
									//if student is logged in tabs with personal tasks and results become enabled
									$( "#tabs-div" ).tabs("enable", 2);
									$( "#tabs-div" ).tabs("enable", 3);
									//loading results for inlogged student
									fillSolutionsForStudent();
									fillTasksForStudent();
								}
							else if (json.specialisation != null) 
								{
									teacher = json;
									student = null;//in case of student was logged in before
									
									$('#login').html("<h4>Hello, " + teacher.firstName + " " + teacher.secondName + "</h4>");
									$( "#tabs-div" ).tabs("enable", 4);
									$( "#tabs-div" ).tabs("enable", 5);
									fillSolutionsForTeacher();
									fillTasksForTeacher();
								}
							else alert(json.id);
							$( "#loginDialog" ).dialog( "close" );
					      },
					    error: function( error )
					      {

					         alert( "Error: " + error );

					      }
						});
				}
				
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( this ).dialog( "close" );
//			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});
}

function quizDialog(quizID, questionNumber)
{
	var quiz;
	if (questionNumber == 0) score = 0;
	for (var j = 0; j < quizzes.length; j++)
		{
			if (quizzes[j].id == quizID) { quiz = quizzes[j]; }
		}
	$('#quiz_question').text("quiz ID: " + quizID + ". Question: " + quiz.questions[questionNumber].question);
	$('#student_answer').empty();
	generateAnswerField(quiz.questions[questionNumber]);
	$('#progress_quiz').html("Question " + (questionNumber+1) + " from " + quiz.questions.length);
	$("#progressbar1").progressbar({value: (questionNumber*100)/quiz.questions.length});
	$("#progressbar2").progressbar({value: (score*100)/quiz.questions.length});
	$('#progress_score').html("Score " + score + " from " + quiz.questions.length);
	$( "#QuizSolutionDialog" ).dialog({
		autoOpen: false,
		height: 500,
		width: 800,
		modal: true,
		buttons: {
			"Submit answer": function() {
				var answer = getInputData(quiz.questions[questionNumber]);
				alert(answer);
				$.ajax({
					type: "GET",
					url: "check_answer",
					data: 'questionID='+quiz.questions[questionNumber].id+'&'+answer,
					dataType: "text",
					success: function(checked)
				      {
						if(checked < 0)
							{
								alert("Not correct");
							}
						else
							{
								alert("Correct!");
								score++;
							}
						quizAnswers += quiz.questions[questionNumber].id+answer + ":";
						matchAnswers=[];
						$( "#QuizSolutionDialog" ).dialog( "close" );
						if(questionNumber < quiz.questions.length - 1)
							{
								openQuizSolutionDialog(quizID, questionNumber + 1);
							}
						else 
							{
								finishQuizDialog(quizID);
							}
				      },
				    error: function( error )
				      {

				         alert( "Error: " + error );

				      }
					});
				
			},
			"Next question": function() {
				$( this ).dialog( "close" );
				if(questionNumber < quiz.questions.length - 1)
					{
						openQuizSolutionDialog(quizID, questionNumber + 1);
					}
				else 
					{
						finishQuizDialog(quizID);
					}
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$(this).dialog('close');
		}
	});
}

function finishDialog(quizID)
{
	alert(quizID);
	$('#quiz_done').text("Quiz DONE! Total score is: " + score);
	// insert data in db
	$.ajax({
		type: "GET",
		url: "submitSolution",
		data: "student="+student.id+"&quiz="+quizID+"&"+quizAnswers,
		dataType: "text",
		success: function(json)
		{
			alert(json);
			quizAnswers="quizAnswers=";
		},
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
	$( "#QuizFinishDialog" ).dialog({
		autoOpen: false,
		height: 500,
		width: 800,
		modal: true,
		buttons: {
			"OK": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$(this).dialog('close');
		}
	});
}

function generateAnswerField(question) {
	var answers = [];
	if (question.correctAnswer != null)
		{
			for (var i = 0; i < question.correctAnswer.length; i++)
				{
					answers[i] = question.correctAnswer[i];
				}
		}
	if (question.answer != null)
		{
			for(var i = 0; i < question.answer.length; i++)
				{
					answers[i + question.correctAnswer.length] = question.answer[i];
				}
			shuffle(answers);
		}
	$('#student_answer').append('<form action="#" id = "formAnswer">');
	switch(question.type)
	{
		case 'yes/no':
			$('#formAnswer').append('<div id="answer"> \n' + 
					'<input type="radio" name = "radio" id="answerTrue" value=true /><label for="answerTrue">True</label> \n' +
					'<input type="radio" name = "radio" id="answerFalse" value=false /><label for="answerFalse">False</label></div>');
			$("#answer").buttonset();
			break;
		case 'select_one':
			$('#formAnswer').append('<div id="answer">');
			for(i = 0; i < answers.length; i++)
				{
				$('#formAnswer').append('<br/><input type="radio" name = "radio" id="answer_'+answers[i].tok+'\" value='+answers[i].tok+' /><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
				}
			$('#formAnswer').append('</div>');
			break;
		case 'select_more':
			$('#formAnswer').append('<div id="answer">');
			for(i = 0; i < answers.length; i++)
				{
				$('#formAnswer').append('<br/><input type="checkbox" name="checkbox" id="answer_'+answers[i].tok+'\" value=\"'+answers[i].tok+'\"/><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
				}
			$('#formAnswer').append('</div>');
			answer_to_check = "";
			break;
		case 'type_number':
			answers[0] = eval(question.question); //calculates expression in question
			$('#formAnswer').append('<input type="text" name="answer" id="answer" class="text ui-widget-content ui-corner-all" />');
			answer_to_check = document.getElementById("answer").value;
			break;
		case 'scrol_number':
			answers[0] = eval(question.question); //calculates expression in question
			$('#formAnswer').append('<div id="slider" style="margin-left: 10px"></div>'+
										'<div id="slider_display" align="center">0</div>');
			$("#slider").slider({ min: -100, 
									max: 100, 
									slide: function(event, ui) {
										$this = $(this);
										$("#slider_display").html(ui.value); 
									    $("#slider_display").append(
									            $("<input type='hidden' />").attr({
									                name: $this.attr('id'),
									                value: ui.value
									            }));
										}});
			answer_to_check = $("#slider_display").text();
			break;
		case 'match':
			var tik = [], tok = [];
			if (question.correctAnswer != null)
			{
				for (var i = 0; i < question.correctAnswer.length; i++)
					{
						answers[i] = question.correctAnswer[i];
						tik[i] = question.correctAnswer[i].tik;
						tok[i] = question.correctAnswer[i].tok;
					}
				shuffle(tik);
				shuffle(tok);
			}
			for(i = 0; i < question.correctAnswer.length; i++)
			{
				$('#formAnswer').append('<tr>');
				$('#formAnswer').append('<td><div id="draggable'+i+'"  data-id="' + tok[i] + '" class="ui-widget-content draggable">'+ tok[i] +'</div></td>');
				$('#formAnswer').append('<td><div id="droppable'+i+'"  data-id="' + tik[i] + '" class="ui-widget-header droppable">'+ tik[i] +'</div></td>');
				$('#formAnswer').append('</tr>');
				$( "#draggable"+i ).draggable();
			    $( "#droppable"+i ).droppable({
			      drop: function( event, ui ) {
			    	  matchAnswers.push($(this).data("id")+"-"+$(ui.helper).data("id"));
			        $( this )
			          .addClass( "ui-state-highlight" )
			          .find( "p" )
			            .html( "Dropped!" );
			      }});
			}
			break;
		default:
			$('#formAnswer').append('error: unknown answer type. ask your db manager for help');
			break;
	}
	$('#student_answer').append('</form>');
}

function assignQuizStudent()
{
	$.ajax({
		type: "GET",
		url: "assignQuizStudent",
		data: $('#taskTeacher').serialize()+"&teacher="+teacher.id,
		dataType: "text",
		success: function(json)
		{
			alert(json);
			fillTasksForTeacher();
		},
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function unassignQuizStudent(value)
{
	alert("unassign: " + value);
}

function getInputData(question)
{
	var answer = "answer=";
	$('').serialize()
	switch(question.type)
	{
		case 'yes/no':
		case 'select_one':
			answer += $("#formAnswer input:radio:checked").val();
			break;
		case 'select_more':
			var selectedValues = $("#formAnswer input:checkbox:checked").map(function(){
			      return $(this).val();
			    }).toArray();
			for (i = 0; i < selectedValues.length; i++)
			{
				answer += selectedValues[i]+";";
			}
			break;
		case 'type_number':
			answer += $("#formAnswer input:text").val();
			break;
		case 'scrol_number':
			answer += $("#slider_display").text();
			break;
		case 'match':
			for (i = 0; i < matchAnswers.length; i++)
			{
				answer += matchAnswers[i]+";";
			}
			break;
		default:
			break;
	}
	return answer;
}


// ************************* end Dialog Windows Functions

//****************Table functions*******************************
function getTable(headings, rows) 
{
  var table = "<table border='1' class='ajaxTable'>\n" +
              getTableHeadings(headings) +
              getTableBody(rows) +
              "</table>";
  return(table);
}

function getSortedTable(headings, rows, tableId) 
{
	var text = "";
	switch(tableId)
	{
		case ("quizResult"):
			text = " quizzen found" + 
			  " <em>TIP!</em> Sort by clicking column header! </p> " +
			  "<p class=tip> <em>TIP!</em> Click Quiz ID to start Quiz (only for inlogged students) </p>";
			break;
		case ("solutionsResult"):
			text = " solution(s) found" + 
			  " <em>TIP!</em> Sort by clicking column header! </p> ";
			break;
		case("solutionsResultTeacher"):
			text = " solution(s) found" + 
			  " <em>TIP!</em> Sort by clicking column header! </p> ";
		case("tasksResultTeacher"):
			text = " task(s) found" + 
			  " <em>TIP!</em> Sort by clicking column header! </p> ";
		case ("tasksResultStudent"):
			text = " tasks found" + 
			  " <em>TIP!</em> Sort by clicking column header! </p> " +
			  "<p class=tip> <em>TIP!</em> Click Quiz ID to start Quiz </p>";
		default:
			break;		
	}

	
	var table = " <p class=tip> Total " + rows.length + text + 
	  		  "<table id = " + tableId +  " border='1' class='ajaxTable'>\n" +
  			  	"<thead>" +  getTableHeadings(headings) + "</thead>" +
  			  	"<tbody>" + getTableBody(rows) + "</tbody>" +
              "</table>";
  return(table);
}

function getTableHeadings(headings) 
{
  var firstRow = "  <tr>";
  for(var i=0; i<headings.length; i++) {
    firstRow += "<th>" + headings[i] + "</th>";
  }
  firstRow += "</tr>\n";
  return(firstRow);
}

function getTableBody(rows) 
{
  var body = "";
  for(var i=0; i<rows.length; i++) 
  {
    body += "  <tr class = 'hoverableRow'>";
    var row = rows[i];
    for(var j=0; j<row.length; j++) 
    {
    	if (j==0) body += "<td class='clickableCell'>" + row[j] + "</td>";
    	else body += "<td>" + row[j] + "</td>";
    }
    body += "</tr>\n";
  }
  return(body);
}

//Insert the html data into the element that has the specified id.
function htmlInsert(id, htmlData) 
{
  document.getElementById(id).innerHTML = htmlData;
}

//*********************************************


//********** utils***************
function shuffle(array) {
    var counter = array.length, temp, index;

    // While there are elements in the array
    while (counter > 0) {
        // Pick a random index
        index = Math.floor(Math.random() * counter);

        // Decrease counter by 1
        counter--;

        // And swap the last element with it
        temp = array[counter];
        array[counter] = array[index];
        array[index] = temp;
    }

    return array;
}

function checkLength( o, n, min, max ) {
	if ( o.val().length > max || o.val().length < min ) {
		o.addClass( "ui-state-error" );
		updateTips( "Length of " + n + " must be between " +
			min + " and " + max + "." );
		return false;
	} else {
		return true;
	}
}

function updateTips( t ) {
	$("P.validateTips")
		.text( t )
		.addClass( "ui-state-highlight" );
	setTimeout(function() {
		$("P.validateTips").removeClass( "ui-state-highlight", 1500 ).text("");
	}, 1500 );
}

//Takes an array of strings and produces a <ul>
//list with the strings inside the <li> elements.
function getBulletedList(listItems) 
{
	var list = "<ul>\n";
	for(var i=0; i < listItems.length; i++) 
	{
		list = list + "  <li>" + listItems[i] + "</li>\n";
	}
	list = list + "</ul>"
	return(list);
}

//*********** end utils