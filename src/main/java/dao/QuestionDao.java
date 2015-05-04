package dao;

import persistence.Catalogs;
import beans.Question;

public class QuestionDao extends Catalogs
{
	/**
	 * Generates randomly the question with possible answers depending on given type, field, studyYear
	 * Amount of possible answers is given in parameter length.
	 * 
	 * Checks if the generated question already exists in db and ads provided the new id if not or extract old id if yes
	 * 
	 * @param type
	 * @param field
	 * @param studyYear
	 * @param length
	 * 
	 * @return Question
	 */
	public Question generateQuestion(String type, String field, int studyYear, int length)
	{
		Question q = null;
		
		return q;
	}
}

