package mah.k3.pfi2.studentlist;

/**
 * Model for student object.
 * 
 * @author andreas
 * 
 */
public class Student {
	/* Unique values, a student may only have one entrance in each */
	private int id;
	private String firstName;
	private String lastName;
	private String adress;
	private int phonenumber;
	/* Non-unique values, a student can have several values in each */
	private String[] courseids;

	/**
	 * Constructor, the id must be unique for this single student.
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Student(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return a string representation of the student.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName).append(" ").append(lastName);

		return sb.toString();
	}

}
