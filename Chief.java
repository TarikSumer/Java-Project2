
public class Chief extends FullTimeEmployee{
	
	
	@Override	
	public double calculateSalary() {	
		return severancePay + overWorkSalary;
	}
}
