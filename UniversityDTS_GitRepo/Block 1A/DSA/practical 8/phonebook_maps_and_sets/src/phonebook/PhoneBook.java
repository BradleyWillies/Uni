/**
 * 
 */
package phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A class to model a generic phone book.  
 * A generic phone book can keep different kinds of contact details. 
 * A generic phone book supports two kinds of record retrieval:
 * - search by name
 * - search by phone number
 * 
 * Records kept within a PhoneBook object are sorted according
 * 	to their natural order. 
 * 
 * Each record is associated with a key. The key is a field in the record.
 * Each key is associated with exactly one record. 
 * Hence, if a new record "happens" to have the same key 
 * 	as an existing record in the list, the new record will 
 * 	NOT be added to the list. 
 * 
 * @author S H S Wong
 * @version 13 Sep 2020
 */
public class PhoneBook<K,R extends Contact<K,R,Ph>, Ph> {

	/* ++++
	 * Two different ways to index the records in a phone book:
	 * - by name
	 * - by phone number
	 * The same phone number can be owned by >1 person.
	 * Each person can have >1 phone number.
	 */
	private SortedMap<K,R> byName;		// maps name with correspondence
	private Map<Ph,Set<R>> byPhone;		// maps phone number with correspondence
	
	/**
	 * Constructor
	 */
	public PhoneBook() {
		byName = new TreeMap<K,R>();
		byPhone = new HashMap<Ph,Set<R>>();
	}
	
	/**
	 * Adds the given record to the contact list 
	 * 	if the key associated with the given record 
	 * 	is not yet in the list. 
	 * @param record
	 */
	public void add(R record) {
		if(!byName.containsKey(record.key()))  { // The given record is not yet in the phone book.
			/* !!!! 
			 * Add a new key-value pair to the "byName" map
			 */
			
		}
		else {
			/* !!!! 
			 * Add the new phone number(s) to an existing record in the phone book.
			 */
			
		
		}
		/* !!!!
		 * Add the new phone number and correspondence associations to
		 * the "byPhone" map.
		 */				
		
		
		
		
		
	}
	
	/** !!!!
	 * Lookup a given phone record from the phone book.
	 * @param phone
	 * @return
	 */
	public Set<R> lookupPhone(Ph phone) {
		return byPhone.get(phone);
	}
	
	/** 
	 * Using a given key, look up the associated record 
	 * 	from the phone book.
	 * @param key
	 * @return
	 */
	public R lookupName(K key) {
		return byName.get(key);
	}
	
	/*
	 * Returns a String representation of the contact list
	 * 	in a table form (i.e. one record per row). 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String results = "";
		for(R record : byName.values()) {
			results += record.toString() + '\n';
		}
		return results;
	}

}
