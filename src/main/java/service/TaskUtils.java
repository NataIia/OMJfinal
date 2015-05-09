package service;

import java.util.ArrayList;

import persistence.Catalogs;
import beans.Task;

public class TaskUtils
{
	public ArrayList<Task> findTaskByQuizID(String id)
	{
		ArrayList<Task> tasks = new ArrayList<>();
		if (id != null && !id.equals("") && !id.equals("Quiz ID"))
			Catalogs.get().getTasks()
						.stream()
						.filter(task -> task.getQuiz().getId() == Integer.parseInt(id))
						.forEach(task -> tasks.add(task));
		return tasks;
	}
	
	public ArrayList<Task> findTaskByStudentID(String id)
	{
		ArrayList<Task> tasks = new ArrayList<>();
		if (id != null && !id.equals("") && !id.equals("Student"))
		{
			int studentId = Integer.parseInt(id.split("=")[1]);
			Catalogs.get().getTasks()
						.stream()
						.filter(task -> task.getStudent().getId() == studentId)
						.forEach(task -> tasks.add(task));
		}
		return tasks;
	}
	
	public ArrayList<Task> findTaskByStatus(String status)
	{
		ArrayList<Task> tasks = new ArrayList<>();
		if (status != null && !status.equals("") && !status.equals("Status"))
		{
			Catalogs.get().getTasks()
						.stream()
						.filter(task -> task.getStatus().equals(status))
						.forEach(task -> tasks.add(task));
		}
		return tasks;
	}
	
	public ArrayList<Task> findTaskByThema(String thema)
	{
		ArrayList<Task> tasks = new ArrayList<>();
		if (thema != null && !thema.equals("") && !thema.equals("Thema"))
		{
			Catalogs.get().getTasks()
						.stream()
						.filter(task -> task.getThema().equals(thema))
						.forEach(task -> tasks.add(task));
		}
		return tasks;
	}
	
	
}
