import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.io.FileWriter;

public class FileOperations {

	// A single file path argument taking method for file reading
	// It returns array contains lines as elements 
	public static String[] readFile(String path) {
		try {
			int i=0;
			int length=Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[length];
				for (String line : Files.readAllLines(Paths.get(path))) {
					results[i++] = line;
				}
			return results;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Method for parsing input files and creating properties of personnels
	// It simply takes 2 file as arguments for, first is holds personnel infos 
	//and second working details which will be used for salary calculations 
	public static void outputPreparer(String personnelfile, String monitoringfile) throws IOException {
		String[] personnel0 = FileOperations.readFile(personnelfile);   
		String[] monitoring0 = FileOperations.readFile(monitoringfile); 
		String[][] monitoringArray = new String[monitoring0.length][];
		String[][] personnelArray = new String[personnel0.length][];
			for (int i=0;i < personnel0.length;i++) {				   // parsing out personnels into seperate individuals
				personnelArray[i] = personnel0[i].split("\t");  
			}
			for (int i=0;i < monitoring0.length;i++) {	  			   // parsing out working hours and registration numbers for each personnel 
				monitoringArray[i] = monitoring0[i].split("\t");
			}
			
			for (int i=0;i < personnelArray.length;i++) {			   // determining all the properties and calculating salaries of personnels seperately
				
				if (personnelArray[i][2].equals("FACULTY_MEMBER")) {   // assigning personal datas to objects
					FacultyMember facultymember= new FacultyMember();
					facultymember.name = personnelArray[i][0].split(" ");
					facultymember.registrationNumber = personnelArray[i][1];
					facultymember.position = personnelArray[i][2];
					facultymember.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					facultymember.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0;j < 4;j++) {																// calculating components of salary and storing them
						if ((Double.parseDouble(monitoringArray[i][j + 1]) - 40) >= 0 && (Double.parseDouble(monitoringArray[i][j + 1]) - 40) <= 8) {
							facultymember.addCourseFee += (Double.parseDouble(monitoringArray[i][j + 1]) - 40)*20;
						}
						else {
							facultymember.addCourseFee += 8*20;
						}
					}
					FileOperations.writeToFile(facultymember);
				}
				else if (personnelArray[i][2].equals("RESEARCH_ASSISTANT")) {
					ResearchAssistant researchassistant = new ResearchAssistant();
					researchassistant.name = personnelArray[i][0].split(" ");
					researchassistant.registrationNumber = personnelArray[i][1];
					researchassistant.position = personnelArray[i][2];
					researchassistant.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					researchassistant.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					FileOperations.writeToFile(researchassistant);
				}
				else if (personnelArray[i][2].equals("OFFICER")) {
					Officer officer = new Officer();
					officer.name = personnelArray[i][0].split(" ");
					officer.registrationNumber = personnelArray[i][1];
					officer.position = personnelArray[i][2];
					officer.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					officer.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0;j < 4;j++) {
						if ((Double.parseDouble(monitoringArray[i][j + 1]) - 40) >= 0 && (Double.parseDouble(monitoringArray[i][j + 1]) - 40) <= 10) {
							officer.overWorkSalary += (Double.parseDouble(monitoringArray[i][j + 1]) - 40)*20;
						}
						else {
							officer.overWorkSalary += 10*20;
						}
					}
					FileOperations.writeToFile(officer);
				}
				else if (personnelArray[i][2].equals("PARTTIME_EMPLOYEE")) {
					PartTimeEmployee parttimeemployee = new PartTimeEmployee();
					parttimeemployee.name = personnelArray[i][0].split(" ");
					parttimeemployee.registrationNumber = personnelArray[i][1];
					parttimeemployee.position = personnelArray[i][2];
					parttimeemployee.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					parttimeemployee.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0; j < 4;j++) {
						if ((Double.parseDouble(monitoringArray[i][j + 1])) >= 10 && (Double.parseDouble(monitoringArray[i][j + 1])) <= 20) {
							parttimeemployee.hourOfWork += (Double.parseDouble(monitoringArray[i][j + 1])) * 18;
						}
						else if(Double.parseDouble(monitoringArray[i][j + 1]) > 20) {
							parttimeemployee.hourOfWork += 20 * 18;
						}
						else {
							parttimeemployee.hourOfWork += 0;
						}
					}
					FileOperations.writeToFile(parttimeemployee);
				}
				else if (personnelArray[i][2].equals("WORKER")) {
					Worker worker = new Worker();
					worker.name = personnelArray[i][0].split(" ");
					worker.registrationNumber = personnelArray[i][1];
					worker.position = personnelArray[i][2];
					worker.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					worker.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0;j < 4;j++) {
						if ((Double.parseDouble(monitoringArray[i][j + 1])) >= 40 && (Double.parseDouble(monitoringArray[i][j + 1])) <= 50) {
							worker.overWorkSalary += 5*105 + ((Double.parseDouble(monitoringArray[i][j + 1])) - 40)*11;  // overwork and daily totals added up together
						}
						else {
							worker.overWorkSalary += 5*105 + 10*11;
						}
					}
					FileOperations.writeToFile(worker);
				}
				else if (personnelArray[i][2].equals("CHIEF")) {
					Chief chief = new Chief();
					chief.name = personnelArray[i][0].split(" ");
					chief.registrationNumber = personnelArray[i][1];
					chief.position = personnelArray[i][2];
					chief.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					chief.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0;j < 4;j++) {
						if ((Double.parseDouble(monitoringArray[i][j + 1])) >= 40 && (Double.parseDouble(monitoringArray[i][j + 1])) <= 48) {
							chief.overWorkSalary += 5*125 + ((Double.parseDouble(monitoringArray[i][j + 1])) - 40)*8;  // overwork and daily totals added up together
						}
						else {
							chief.overWorkSalary += 5*125 + 8*15;
						}
					}
					FileOperations.writeToFile(chief);
				}
				else if (personnelArray[i][2].equals("SECURITY")) {
					Security security = new Security();
					security.name = personnelArray[i][0].split(" ");
					security.registrationNumber = personnelArray[i][1];
					security.position = personnelArray[i][2];
					security.yearOfStart = Double.parseDouble(personnelArray[i][3]);
					security.severancePay = (2020 - Double.parseDouble(personnelArray[i][3])) * 16;
					for (int j=0;j < 4;j++) {
						if ((Double.parseDouble(monitoringArray[i][j + 1])) >= 30 && (Double.parseDouble(monitoringArray[i][j + 1])) <= 54) {
							security.hourOfWork += (Double.parseDouble(monitoringArray[i][j + 1]))*10 + 15*6; // food and transportation added to weekly earning
						}
						else if ((Double.parseDouble(monitoringArray[i][j + 1])) > 54) {
							security.hourOfWork += 54*10 + 15*6;
						}
						else {
							security.hourOfWork += 0;
						}
					}
					FileOperations.writeToFile(security);
				}
			}
	}
	// Method for writing information of personnels to seperate files
	// It takes personnel object as single argument
	public static void writeToFile(Personnel personel) throws IOException {
		Double salary = personel.calculateSalary();
		File f = new File(String.format("%s.txt", personel.registrationNumber));
		FileWriter filewriter = new FileWriter(f);
		filewriter.write("Name : " + personel.name[0] +"\n");
		filewriter.write("Surname : " + personel.name[1] +"\n");
		filewriter.write("Registration Number : " + personel.registrationNumber +"\n");
		filewriter.write("Position : " + personel.position +"\n");
		filewriter.write("Year of Start : " + (int)personel.yearOfStart +"\n");
		filewriter.write("Salary : " + String.format(Locale.ROOT, "%.2f", salary) + " TL");
		filewriter.close();
	}

	
}
