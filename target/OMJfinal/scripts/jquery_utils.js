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
			$('#number').change(sortQuizzes); 
			$('#thema').change(sortQuizzes);
			$('#study_year').change(sortQuizzes);
			$('#number_questions').change(sortQuizzes);
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function sortQuizzes()
{
	$.ajax({
		type: "GET",
		url: "selectQuizzes",
		data: $("#findQuiz").serialize(),
		dataType: "json",
		success: function(json)
	      {
			quizzes = json;
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
			 var ids = [], rows = [], correct_answers = [], student_answers = [];
			 var themas = new Set(),  dates = new Set(), scores = new Set();
			 ids[0] = "Quiz ID";
			 themas.add("Thema");
			 dates.add("Date");
			 scores.add("Score");
			 for(var i = 1; i <= json.length; i++) 
			 {
//				 correct_answers[i-1] = getBulletedList(json.answers.values);
				 rows[i-1] = [json[i-1].id, [json[i-1].thema], json[i-1].creationDate.time, [json[i-1].score]];
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
			
			//make cell clickable
			  $('.clickableCell').click(function(e){openQuizSolutionDialog(e.target.innerHTML, 0)});
			//add quiz info on hover
			  $().hover
			  
			//couple option selection with function
//			$('#number').change(sortQuizzes); 
//			$('#thema').change(sortQuizzes);
//			$('#study_year').change(sortQuizzes);
//			$('#number_questions').change(sortQuizzes);
	      },
	    error: function( error )
	      {

	         alert( "Error: " + error );

	      }
		});	
}

function fillSolutionsForTeacher()
{
	
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



function finishQuizDialog()
{
	finishDialog();
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
								}
							else if (json.specialisation != null) 
								{
									teacher = json;
									student = null;//in case of student was logged in before
									
									$('#login').html("<h4>Hello, " + teacher.firstName + " " + teacher.secondName + "</h4>");
									$( "#tabs-div" ).tabs("enable", 4);
									$( "#tabs-div" ).tabs("enable", 5);
									fillSolutionsForTeacher();
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
	$('#quiz_question').text("quiz ID " + quizID + quiz.questions[questionNumber].question);
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
				getInputData(quiz.questions[questionNumber]);
				score++;
				$.ajax({
					type: "GET",
					url: "check_answer",
					data: 'questionID='+quiz.questions[questionNumber].id+'&'+$('#formAnswer').serialize(),
					dataType: "text",
					success: function(checked)
				      {
						alert(checked);
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
						finishQuizDialog();
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

function finishDialog()
{
	$('#quiz_done').text("Quiz DONE!");
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
			$('#answer input').click(function(){ answer_to_check = [$('input[name="radio"]:checked').val()]; alert(answer_to_check);});
			break;
		case 'select_one':
			$('#formAnswer').append('<div id="answer">');
			for(i = 0; i < answers.length; i++)
				{
				$('#formAnswer').append('<br/><input type="radio" name = "radio" id="answer_'+answers[i].tok+'\" value='+answers[i].tok+' /><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
				}
			$('#formAnswer').append('</div>');
			$('#formAnswer input').click(function(){ answer_to_check = [$('input[name="radio"]:checked').val()]; alert(answer_to_check);});
			break;
		case 'select_more':
			$('#formAnswer').append('<div id="answer">');
			for(i = 0; i < answers.length; i++)
				{
				$('#formAnswer').append('<br/><input type="checkbox" id="answer_'+answers[i].tok+'\" value='+answers[i].tok+'/><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
				}
			$('#formAnswer').append('</div>');
			answer_to_check = "";
			$('#formAnswer input').click(function(){ answer_to_check += [$('input[name="radio"]:checked').val()]; alert(answer_to_check);});
			break;
		case 'type_number':
			answers[0] = eval(question.question);
			$('#formAnswer').append('<input type="text" name="answer" id="answer" class="text ui-widget-content ui-corner-all" />');
			answer_to_check = document.getElementById("answer").value;
			break;
		case 'scrol_number':
			answers[0] = eval(question.question);
			$('#formAnswer').append('<div id="slider" style="margin-left: 10px"></div>'+
										'<div id="slider_display" align="center">0</div>');
			$("#slider").slider({ min: -100, 
									max: 100, 
									serialization: {},
									slide: function(event, ui) {
										$this = $(this);
										$("#slider_display").html(ui.value); 
									    $("#slider_display").append(
									            $("<input type='hidden' />").attr({
									                name:$this.attr('id'),
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
				$('#formAnswer').append('<td><div id="draggable'+i+'"  class="ui-widget-content draggable">'+tok[i]+'</div></td>');
				$('#formAnswer').append('<td><div id="droppable'+i+'"  class="ui-widget-header droppable">'+tik[i]+'</div></td>');
				$('#formAnswer').append('</tr>');
				$( "#draggable"+i ).draggable();
			    $( "#droppable"+i ).droppable({
			      drop: function( event, ui ) {
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

function getInputData(question)
{
	switch(question.type)
	{
		case 'yes/no':
//			$('#formAnswer').append('<div id="answer"> \n' + 
//					'<input type="radio" name = "radio" id="answerTrue" /><label for="answerTrue">True</label> \n' +
//					'<input type="radio" name = "radio" id="answerFalse" /><label for="answerFalse">False</label></div>');
//			$("#answer").buttonset();
			break;
		case 'select_one':
//			$('#formAnswer').append('<div id="answer">');
//			for(i = 0; i < answers.length; i++)
//				{
//				$('#formAnswer').append('<br/><input type="radio" name = "radio" id="answer_'+answers[i].tok+'\" /><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
//				}
//			$('#formAnswer').append('</div>');
			break;
		case 'select_more':
//			$('#formAnswer').append('<div id="answer">');
//			for(i = 0; i < answers.length; i++)
//				{
//				$('#formAnswer').append('<br/><input type="checkbox" id="answer_'+answers[i].tok+'\" /><label for="answer_'+answers[i].tok+'\">'+answers[i].tok+'</label>');
//				}
//			$('#formAnswer').append('</div>');
			break;
		case 'type_number':
//			answers[0] = eval(question.question);
//			$('#formAnswer').append('<input type="text" name="answer" id="answer" class="text ui-widget-content ui-corner-all" />');
			break;
		case 'scrol_number':
//			answers[0] = eval(question.question);
//			$('#formAnswer').append('<div id="slider" style="margin-left: 10px"></div>'+
//										'<div id="slider_display" align="center">0</div>');
//			$("#slider").slider({ min: -100, 
//									max: 100, 
//									slide: function(event, ui) {
//										$("#slider_display").html(ui.value); 
//										}});
			break;
		case 'match':
//			var tik = [], tok = [];
//			if (question.correctAnswer != null)
//			{
//				for (var i = 0; i < question.correctAnswer.length; i++)
//					{
//						answers[i] = question.correctAnswer[i];
//						tik[i] = question.correctAnswer[i].tik;
//						tok[i] = question.correctAnswer[i].tok;
//					}
//				shuffle(tik);
//				shuffle(tok);
//			}
//			for(i = 0; i < question.correctAnswer.length; i++)
//			{
//				$('#formAnswer').append('<tr>');
//				$('#formAnswer').append('<td><div id="draggable'+i+'"  class="ui-widget-content draggable">'+tok[i]+'</div></td>');
//				$('#formAnswer').append('<td><div id="droppable'+i+'"  class="ui-widget-header droppable">'+tik[i]+'</div></td>');
//				$('#formAnswer').append('</tr>');
//				$( "#draggable"+i ).draggable();
//			    $( "#droppable"+i ).droppable({
//			      drop: function( event, ui ) {
//			        $( this )
//			          .addClass( "ui-state-highlight" )
//			          .find( "p" )
//			            .html( "Dropped!" );
//			      }});
//			}
			break;
		default:
			break;
	}
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