/**
 * 
 */
package phonebook;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A class which contains several utility methods 
 * 	(all are static methods as no object of GUIUtil is expected to be created)
 * for developing a GUI. 
 * 
 * @author S H S Wong
 * @version 13 Sep 2020
 */
public class GUIUtil {
	
	/**
	 * ++++
	 * Get all available fonts from the current machine.
	 * For each font, test if it can display a given character.
	 * Put the name of each usable font in a ArrayList for returning.
	 * 
	 * @param character	a character that is intended to be displayed
	 * @return	an ArrayList of suitable font names
	 */
	public static ArrayList<String> getSuitableFontNames(char character) 
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		
		ArrayList<String> results = new ArrayList<String>();
		
		for (String fontName : fontNames) {
			Font font = new Font(fontName, Font.PLAIN, 10);
			if (font.canDisplay(character)) {
				results.add(fontName);
			}
		}	
		return results;
	}

	/**
	 * ++++
	 * Get all available fonts from the current machine.
	 * For each font, test if it can display a given set of characters.
	 * Returns the first font that can support all of the given characters.
	 * 
	 * @param characters	the characters that are intended to be displayed
	 * @return	the name of a suitable font
	 */
	public static String getSuitableFontNames(char[] characters) 
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		
		String result = null;
		
		int index = 0;
		boolean found = false;
		
		while (!found) {
			Font font = new Font(fontNames[index], Font.PLAIN, 10);
			HashSet<Boolean> ok4Each = new HashSet<>();
			for (char character : characters) {  // Check each character to see if the font supports it.
				if (font.canDisplay(character)) {	
					ok4Each.add(true);
				}
				else {
					ok4Each.add(false);
					break;   // No need to check further, as this font can't work with the current character. 
				}
			}
			if (!ok4Each.contains(false)) {
				result = fontNames[index];
				found = true;
			}
			index++;
		}	
		
		return result;
	}
	
	
	/**
	 * ++++
	 * Set the font for a component and all its child components to 
	 * 	the specified font.
	 * As a component can be a Container which contains other components, 
	 * 	we need to go through every child components for this component
	 * 	and set their fonts accordingly.
	 *  
	 * @param component	A GUI component
	 * @param font	A font to be used within a GUI component
	 */
	public static void setFont4All(Component component, Font font) 
	{
		component.setFont(font);
		if (component instanceof Container) {
			for(Component child : ((Container) component).getComponents()) 
			{
				setFont4All(child, font);
			}
		}
		
	}

}
