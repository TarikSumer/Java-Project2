
public class Officer extends Personnel{

	double baseSalary;
	double ssBenefits;
	double overWorkSalary;
	
	public Officer() {
		super();
		this.baseSalary = 2600;
		this.ssBenefits = (baseSalary*65)/100;
	}
	
	@Override
	public double calculateSalary(){
		return baseSalary + ssBenefits + severancePay + overWorkSalary;
				
	}
	
}
