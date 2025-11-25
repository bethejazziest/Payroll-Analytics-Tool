import javafx.scene.control.Label;
import java.util.*;
import java.util.stream.Collectors;

public class PayrollSearcher {
    private final List<Employee> employees;
    private final Label statusLabel;

    public PayrollSearcher(List<Employee> employees, Label statusLabel) {
        this.employees = employees;
        this.statusLabel = statusLabel;
    }

    public List<Employee> searchByName(String name) {
        final String nameFinal = name; 
        
        String[] nameParts = nameFinal.split(" ");
        boolean isFullNameSearch = nameParts.length == 2;
        
        List<Employee> filteredEmployees = employees.stream()
            .filter(emp -> {
                if (isFullNameSearch) {
                    return emp.getFirstName().equals(nameParts[0]) && emp.getLastName().equals(nameParts[1]);
                } else {
                    return emp.getFirstName().equals(nameFinal) ||
                           emp.getLastName().equals(nameFinal) ||
                           emp.getIdNumber().equals(nameFinal);
                }
            })
            .collect(Collectors.toList());

        statusLabel.setText("Found " + filteredEmployees.size() + " employees matching " + nameFinal);
        return filteredEmployees;
    }
    
    public List<Employee> searchByDept(String dept) {
        final String deptFinal = dept;
    
        List<Employee> filteredEmployees = employees.stream()
            .filter(emp -> emp.getJobsHeld().stream()
                .anyMatch(job -> {
                    String jobDept = job.getDept();
                    boolean match = jobDept.equals(deptFinal);
                    System.out.println("Checking " + jobDept + " against " + deptFinal + ": " + match);
                    return match;
                }))
            .collect(Collectors.toList());
        statusLabel.setText("Found " + filteredEmployees.size() + " employees in department " + deptFinal);
        return filteredEmployees;
    }

    public List<Employee> searchByJobTitle(String jobTitle) {
        System.out.println("Searching for exact match of job title: '" + jobTitle + "'");

        List<Employee> filteredEmployees = employees.stream()
            .filter(emp -> {
                boolean matchFound = emp.getJobsHeld().stream()
                    .anyMatch(job -> {
                        String jobPositionTitle = job.getPositionTitle();
                        boolean isMatch = jobPositionTitle.equals(jobTitle);
                        System.out.println("Comparing '" + jobPositionTitle + "' with input: '" + jobTitle + "' -> " + isMatch);
                        return isMatch;
                    });
                return matchFound;
            })
            .collect(Collectors.toList());

        statusLabel.setText("Found " + filteredEmployees.size() + " employees with job title " + jobTitle);
        return filteredEmployees;
    }
}


    //*public List<Employee> searchByBoth(String jobTitle, String dept) {
        //*jobTitle = jobTitle.trim();  // jobTitle is final after this point
       //* dept = dept.trim();  // dept is final after this point
       //* final String jobTitleFinal = jobTitle; // Make jobTitle effectively final
     //*   final String deptFinal = dept; // Make dept effectively final

    //*    List<Employee> bothEmployees = new ArrayList<>();
    //*    for (Employee employee : employees) {
    //*        for (Job job : employee.getJobsHeld()) {
            //*    if (job.getPositionTitle().trim().toLowerCase().equals(jobTitleFinal) && job.getDept().trim().toLowerCase().equals(deptFinal)) {
       //*             bothEmployees.add(employee);
       //*             break;
       //*         }
          //*  }
    //*    }
    //*    return bothEmployees;
  //*  }
//*}

    //*public void searchByDept(){
    //*    Scanner in = new Scanner(System.in);
    //*    System.out.println("ENTER DEPT NAME:");
    //*    String deptName = in.nextLine();
    //*    List<Employee> deptEmployees = new ArrayList<>();

    //*    for (Employee employee : employees) {
     //*       for (Job job: employee.getJobsHeld()){
    //*            if (job.getDept().equals(deptName)) {
    //*                deptEmployees.add(employee);
    //*                break;
    //*            }
     //*       }

    //*   

//* */}

   //* */     System.out.println("THere are " + titleEmployees.size() + " people with that job title.");
      //* */  System.out.println("AVG SALARY?!?!??! :" + calculateAverageSalary(titleEmployees));
    //*  */}