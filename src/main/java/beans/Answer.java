package beans;

import java.util.GregorianCalendar;

/**
 * 
 * property type defines one type of the answer from possible:
 * o Ja/nee vraag 
 * o Multiple choice door selectie van 1 antwoord 
 * o Muliple choice met meedere mogelijke antwoorden 
 * o Numeriek antwoord door gebruik van een slider. 
 * o Numeriek antwoord door ingave via toetsenbord. 
 * o Drag and drop waarbij het juiste antwoord in een bepaald veld moet geplaatst worden. 
 * 	 (hier met variatie meerdere mogelijkheden naar 1 antwoord gebied maar ook meerdere 
 * 	 mogelijkheden naar evenveel antwoordgebieden.
 * 
 * Only for the last possibility both tik and tok are implemented
 * 
 * @author Natalia Dyubankova
 * @since 26/04/2015
 *
 */
public class Answer extends Entity
{
	private String type, tik, tok;

	public Answer(int id, String tik, String tok, String type, String thema, Person author, GregorianCalendar creationDate)
	{
		super(thema, id, author, creationDate);

		this.tik = tik;
		this.tok = tok;
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the tik
	 */
	public String getTik()
	{
		return tik;
	}

	/**
	 * @param tik the tik to set
	 */
	public void setTik(String tik)
	{
		this.tik = tik;
	}

	/**
	 * @return the tok
	 */
	public String getTok()
	{
		return tok;
	}

	/**
	 * @param tok the tok to set
	 */
	public void setTok(String tok)
	{
		this.tok = tok;
	}
	
	@Override
	public String toString()
	{
		return "Answer: " + super.toString() + 
				"; answer:" + this.tok;
	}
}
