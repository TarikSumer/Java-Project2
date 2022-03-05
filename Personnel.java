
public class Personnel {
	String[] name;
	String registrationNumber;
	String position;
	double yearOfStart;
	
	double severancePay;

	public Personnel() {
		
	}
	
	// method for salary calculations that all subclasses will override it
	public double calculateSalary() {	
		return 0;
	}

}
