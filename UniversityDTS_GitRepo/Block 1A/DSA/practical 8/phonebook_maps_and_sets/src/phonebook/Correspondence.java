/**
 * 
 */
package phonebook;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to model a phone record in a phone book.
 * A phone record is a key-value pair, 
 * 	where the nickname part is the key and 
 * 	the modelling Phone record is the value.  
 * 
 * @author S H S Wong
 * @version 13 Sep 2020
 */
/* !!!! 
 * Class Correspondence should implement interfaces Comparable and Contact.
 * Make sure that you have passed an appropriate value to EACH of the type variables declared in 
 * the interfaces java.lang.Comparable and phonebook.Contact.
 */
public class Correspondence 
{
	private String nickname;				// the nickname for this correspondence
	private String name;					// the name of this correspondence 
	private Set<String> phones;	// the phone numbers
	private String address;					// the address 
	
	/** 
	 * Constructor: to create a new Correspondence object that keeps one phone number
	 * @param nickname
	 * @param name
	 * @param phone
	 * @param address
	 */
	public Correspondence(String nickname, String name, String phone, String address) 
	{
		this.nickname = nickname;
		this.name = name;
		phones = new HashSet<String>();
		addPhone(phone);
		this.address = address;
	}

	/**
	 * Add a new phone number to this record
	 * @param phone
	 */
	public void addPhone(String phone) {
		phones.add(phone);
	}
	
	/**
	 * Returns the nickname of this correspondence
	 * @return
	 */
	public String nickname() {
		return nickname;
	}
	
	/**
	 * Returns the name of this correspondence
	 * @return
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Returns the phone numbers of this correspondence
	 * @return
	 */
	public Set<String> phones() {
		return phones;
	}
	
	/** 
	 * Returns the address of this correspondence record
	 * @return
	 */
	public String address() {
		return address;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String results = nickname + " (" + name + ")\n Phone numbers: ";
		for(String phone : phones) {
			results += phone + ", \t";
		}
		results += "\n Address: " + address;
		return results;
	}

	/* !!!!
	 * Returns the key for this object.
	 * The key is a concatenation of the nickname and the name.
	 * 
	 * (non-Javadoc)
	 * @see phonebook.KeyValuePair#key()
	 */
	

	/* !!!!
	 * Returns the value for this object.
	 * The value is object reference of this object.
	 * (non-Javadoc)
	 * @see phonebook.KeyValuePair#value()
	 */
	
	
	/*
	 *  Compares two Correspondence objects.
	 *   
	 *  The natural order of Correspondence objects are defined
	 *  based on their keys. 
	 *  
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Correspondence another) {

		/* !!!!
		 * The natural order of Correspondence objects are defined
		 * in terms of the objects' keys. 
		 */
		
		
		
	}
	
	/*
	 * Compares this Correspondence object to the specified object.
	 * 
	 * The identity of a Correspondence object is defined by its key value,
	 * i.e. a concatenation over the value of two fields: nickname and name. 
	 * Two Correspondence object are considered equals 
	 * when they have the same key.
	 *  
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		
		boolean result = false;
		
		/* ++++
		 * Check if the given object is a Correspondence object.
		 * 
		 * We have to do this check so as to ensure that 
		 * 	the comparison is performed on the same type of objects.
		 * 
		 * We can't avoid type casting by defining the parameter type as Correspondence
		 * 	because method equals was inherited from Object. 
		 * As method overriding can ONLY be performed on the
		 * 	same method signature (i.e. the same method name with
		 * 	the same sequence of parameter types), we have to use
		 * 	Object as the parameter type of this method.
		 */
		if (o instanceof Correspondence) 
		{
			// Cast the given object to its underlying type
			Correspondence another = (Correspondence) o;
			
			result = this.key().equals(another.key());
		}
		
		return result;
	}
	
	/*
	 * Returns the hash code for this Correspondence object.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		/* !!!!
		 * Add a return statement here which returns the intended 
		 * 	hash code.
		 * As the identity of a Correspondence object is defined 
		 * by its key value, we can simply return the hash code of 
		 * the key of this object.
		 */
		
		
	}

}
