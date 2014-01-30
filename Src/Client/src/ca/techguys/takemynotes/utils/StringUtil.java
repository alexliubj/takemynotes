package ca.techguys.takemynotes.utils;


/**
 * String is Empty util
 * @author Alex.liu
 *
 */
public class StringUtil {

	public static boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}
}
