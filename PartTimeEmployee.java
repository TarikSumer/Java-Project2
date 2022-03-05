
public class PartTimeEmployee extends Personnel{
	double hourOfWork;
	
	@Override
	public double calculateSalary(){
		return severancePay + hourOfWork;
				
	}
}
