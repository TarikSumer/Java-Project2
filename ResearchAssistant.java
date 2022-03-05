
public class ResearchAssistant extends Academician{

	public ResearchAssistant() {
		super();
		this.ssBenefits = (baseSalary*105)/100;
	}

	@Override
	public double calculateSalary(){
		return baseSalary + ssBenefits + severancePay;
				
	}
}
