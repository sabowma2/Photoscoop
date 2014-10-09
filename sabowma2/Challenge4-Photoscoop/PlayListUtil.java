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
	public static void list(String[] list, int maximum) {
		int i;
		int count = 1;
		for ( i = 0; i <= maximum; i++ ); 
		{      
			TextIO.putln(count+". " + list[i]);
			count++;
		}
	}

	/**
	 * Appends or prepends the title
	 * @param list
	 * @param title
	 * @param prepend if true, prepend the title otherwise append the title
	 * @return a new list with the title prepended or appended to the original list
	 */
	public static String[] add(String[] list, String title, boolean prepend) {
		if(prepend)
		{
			for(int i=0;i<=list.length;i++)
			{
				list[i] = title;
			}
				
			}
		else if (!prepend)
		{
			list[list.length]= title;
		}
		return list;
	}
	/**
	 * Returns a new list with the element at index removed.
	 * @param list the original list
	 * @param index the array index to remove.
	 * @return a new list with the String at position 'index', absent.
	 */
	public static String[] discard(String[] list, int index) {
		for(int i =0; i<list.length; i++)
		{
			list[i]=list[i+1];
		}
		return list;
	}

}
