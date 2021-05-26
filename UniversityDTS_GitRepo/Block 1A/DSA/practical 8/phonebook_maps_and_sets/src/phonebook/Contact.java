/**
 * 
 */
package phonebook;

import java.util.Set;

/**
 * ++++
 * To model a contact as a key-value pair that may exist in the context of a map.
 * 
 * @author S H S Wong
 * @version 13 Sep 2020
 */
public interface Contact<K,V,Ph> {
	
	/**
	 * @return the key of this key-value pair
	 */
	K key();
	
	/**
	 * @return the value of this key-value pair
	 */
	V value();
	
	/**
	 * @return a set of phone records
	 */
	Set<Ph> phones();

}
