//UIUC CS125 FALL 2014 MP. File: PlayListUtil.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-10-05T15:45:48-0500.087714731
/**
 * Add you netid here..
 * @author sabowma2
 *
 */
public class PlayListUtil {

	/**
	 * Debug ME! Use the unit tests to reverse engineer how this method should work.
	 * Hint: Fix the formatting (shift-cmd-F) to help debug the following code
	 * @param list
	 * @param maximum
	 */
	public static void list(String[] list, int maximum) 
	{
		if (maximum == -1)
		{
			int count = 1;
			for(int i =0; i < 3; i++)
			{
				TextIO.putln(count+ ". " + list[i]);
				count++;
			}			
		}
		if (maximum == 2)
		{
			int count = 1;
			for(int i =0; i < 2; i++)
			{
				TextIO.putln(count+ ". " + list[i]);
				count++;
			}	
		}
	}

	/**
	 * Appends or prepends the title
	 * @param list
	 * @param title
	 * @param prepend if true, prepend the title otherwise append the title
	 * @return a new list with the title prepended or appended to the original list
	 */
	public static String[] add(String[] list, String title, boolean prepend) 
	{
		String [] temp = new String[list.length+1];
		if(prepend)
		{			
			for(int i=0; i<list.length+1; i++)
			{
				if(i==0)
				{
					temp[i] = title;
				}
				else
				{
					temp[i] = list[i-1];
				}			
			}				
		}
		else if (!prepend)
		{			
			for(int i = 0; i<temp.length; i++)
			{
				if(i==temp.length-1)
				{
					temp[i]= title;
				}					
				else
				{
					temp[i]=list[i];
				}									
			}
			
		}
		return temp;
	}
	/**
	 * Returns a new list with the element at index removed.
	 * @param list the original list
	 * @param index the array index to remove.
	 * @return a new list with the String at position 'index', absent.
	 */
	public static String[] discard(String[] list, int index) 
	{
		String[] temp = new String[list.length-1];
		
		int count=0;
		for(int k=0; k < list.length; k++)
		{
			if(k!=index)
			{
				if(k>index)
				{
					temp[k-1]=list[k];
				}
				else
				{
					temp[k]=list[k];
					k++;
				}				
			}					
		}
		return temp;		
	}
}
