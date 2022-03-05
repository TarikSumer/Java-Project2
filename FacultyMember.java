
public class FacultyMember extends Academician{
	double addCourseFee;

	public FacultyMember() {
		super();
		this.ssBenefits = (baseSalary*135)/100;
	}

	@Override
	public double calculateSalary(){
		return baseSalary + ssBenefits + severancePay + addCourseFee;
				
	}
}	
