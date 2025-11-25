import javafx.application.Platform;
import javafx.scene.control.Label;
import java.io.*;
import java.util.*;

public class FileLoader {

    private File file;
    private File loadedfile;
    private String filelocation;
    private List<Employee> employees;
    private Label statusLabel;

    public FileLoader(Label statusLabel) {
        this.employees = new ArrayList<>();
        this.statusLabel = statusLabel;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Employee> load() throws FileNotFoundException {
        if (file != null && file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                scanner.nextLine();
    
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    
                    String idnumber = parts[1];
                    String lastname = parts[2];
                    String firstname = parts[3]; 
                    String employeename = firstname + " - " + lastname;
    
                    String departmentdivision = parts[4];
                    String positiontype = parts[6];
                    String positiontitle = parts[5];
                    float paytotalactual = 0;
    
                    try {
                        paytotalactual = Float.parseFloat(parts[8]);
                    } catch (NumberFormatException e) {
                        updateStatusLabel("Invalid input in pay field: " + parts[8] + " (skipping)");
                    }
    
                    Job job = new Job(positiontitle, departmentdivision, positiontype, paytotalactual);
    
                    Employee foundEmployee = findEmployee(idnumber);
                    if (foundEmployee != null) {
                        foundEmployee.loadJob(job);
                    } else {
                        Employee newEmployee = new Employee(firstname, lastname, idnumber);
                        newEmployee.loadJob(job);
                        employees.add(newEmployee);
                    }
                }
    
            } catch (FileNotFoundException e) {
                updateStatusLabel("File not found.");
                throw e;
            }
        } else {
            updateStatusLabel("No file found.");
        }
    
        updateStatusLabel("Employees loaded: " + employees.size()); 
        return employees;
    }

    private Employee findEmployee(String idnumber) {
        for (Employee employee : employees) {
            if (employee.getIdNumber().equals(idnumber)) {
                return employee;
            }
        }
        return null;
    }

    private void updateStatusLabel(String message) {
        Platform.runLater(() -> statusLabel.setText(message));
    }
}