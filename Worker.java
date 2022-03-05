
public class Worker extends FullTimeEmployee{
	
	
	@Override
	public double calculateSalary() {	
		return severancePay + overWorkSalary;
	}
}
