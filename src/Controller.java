import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML private Button browseButton;
    //*@FXML private Button searchJobButton;
    @FXML private Button clearButton;
    @FXML private Label statusLabel;
    @FXML private Label resultLabel;
    @FXML private MenuItem closeMenu;
    @FXML private TextField deptTextField;
    @FXML private TextField jobTitleTextField;
    @FXML private TextField searchNameTextField;
    @FXML private TabPane tabPane;
    @FXML private Tab searchDeptTab;
    @FXML private Tab searchNameTab;
    @FXML private Tab searchJobTab;

    private FileLoader fileLoader;
    private PayrollSearcher payrollSearcher;
    private List<Employee> employees;

    @FXML
    public void clearResults(){
        resultLabel.setText("");
    }

    @FXML
    public void initialize() {
        disableSearchButtons(true);
        statusLabel.setText("No file loaded.");
        resultLabel.setText("");
    }

    private void disableSearchButtons(boolean disable) {
        searchDeptTab.setDisable(disable);
        searchNameTab.setDisable(disable);
        searchJobTab.setDisable(disable);
    }
    
    @FXML
    public void browseForFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());

        if (file != null) {
            try {
                fileLoader = new FileLoader(statusLabel);
                fileLoader.setFile(file);
                employees = fileLoader.load();
                payrollSearcher = new PayrollSearcher(employees, statusLabel);

                statusLabel.setText("File loaded: " + file.getName());
                resultLabel.setText("");
                disableSearchButtons(false);
            } catch (Exception e) {
                statusLabel.setText("Error: " + e.getMessage());
            }
        } else {
            statusLabel.setText("No file selected.");
        }
    }

    @FXML
    public void loadDeptContent() {
        if (payrollSearcher != null) {
            String dept = deptTextField.getText().trim();
            if (dept.isEmpty()) {
                resultLabel.setText("Please enter a department.");
                return;
        }

        resultLabel.setText("Searching for department: " + dept);
        List<Employee> results = payrollSearcher.searchByDept(dept);

        if (results.isEmpty()) {
            resultLabel.setText("No results found for department: " + dept);
        } else {
            displayResults(results);
        }
    }
}

    @FXML
    public void searchByName() {
    if (payrollSearcher != null) {
        String name = searchNameTextField.getText();
        if (name.isEmpty()) {
            resultLabel.setText("Please enter a name or ID number.");
            return;
        }

        resultLabel.setText("Searching for: " + name);
        List<Employee> results = payrollSearcher.searchByName(name);

        if (results.isEmpty()) {
            resultLabel.setText("No results found for: " + name);
        } else {
            Employee emp = results.get(0); 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Employee Found: " + emp.getFirstName() + " " + emp.getLastName() + "\n");

            for (Job job : emp.getJobsHeld()) {
                stringBuilder.append("Job Title: " + job.getPositionTitle() + "\n");
                stringBuilder.append("Salary: " + job.getTotalPay() + "\n");
                stringBuilder.append("Department: " + job.getDept() + "\n\n");
            }
            resultLabel.setText(stringBuilder.toString());
        }
    }
}

    @FXML
    public void searchByJob(ActionEvent event) {
    if (payrollSearcher != null) {
        String jobTitle = jobTitleTextField.getText();
        if (jobTitle.isEmpty()) {
            resultLabel.setText("Please enter a job title.");
            return;
        }  
        List<Employee> results = payrollSearcher.searchByJobTitle(jobTitle); 
        displayResults(results); 
    }
}

    private void displayResults(List<Employee> employees) {
        if (employees.isEmpty()) {
            resultLabel.setText("No results found.");
        } else {
            StringBuilder string = new StringBuilder();
            for (Employee emp : employees) {
                string.append(emp.getFirstName()).append(" ")
                      .append(emp.getLastName()).append("\n");
                for (Job job : emp.getJobsHeld()) {
                    string.append("Job Title: ").append(job.getPositionTitle()).append("\n")
                          .append("Department: ").append(job.getDept()).append("\n")
                          .append("Salary: ").append(job.getTotalPay()).append("\n\n");
                }
            }
            resultLabel.setText(string.toString());
        }
    }

    @FXML
    public void close_the_program(ActionEvent event) {
        Platform.exit();
    }
}


//*@FXML
//*public void searchByBoth(ActionEvent event) {
//*    if (payrollSearcher != null) {
//*        String jobTitle = jobTitleTextField.getText().trim();
//*        String dept = deptTextField.getText().trim();

//*        if (jobTitle.isEmpty() || dept.isEmpty()) {
//*            resultLabel.setText("Please enter both job title and department.");
//*            return;
//*        }

//*        List<Employee> results = payrollSearcher.searchByBoth(jobTitle, dept);
//*        displayResults(results);
//*    }
//*}
