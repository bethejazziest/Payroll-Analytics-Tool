import java.util.List;
import java.util.ArrayList;

public class Employee {


    //ATTRIBUTES
    private String firstname;
    private String lastname;
    private String idnumber;
    private int numberofjobs;
    private List<Job> jobsheld;



    //METHODS
    public Employee(String fnamegiven, String lnamegiven, String idgiven){
        firstname = fnamegiven;
        lastname = lnamegiven;
        idnumber = idgiven;
        jobsheld = new ArrayList<>();
    }


    public int getTotalNumberofJobs() {
        return jobsheld.size();
    }

    public int getJobNumber(){
        numberofjobs = jobsheld.size();
        return numberofjobs;
    }

    public List<Job> getJobsHeld() {
        return jobsheld;
    }


    public void loadJob(String title, String department, String type, float pay){
        Job job = new Job(title, department, type, pay);
        this.jobsheld.add(job);
        }

    public void loadJob(Job job) {
        this.jobsheld.add(job);
    }

    public String getName(){
        return firstname + " " + lastname;
    }

    public String getFirstName(){
        return firstname;
    }

    public String getIdNumber(){
        return idnumber;
    }

    public String getLastName(){
        return lastname;
    }

    public float getSalaryForAllJobs(){
        float total = 0;
        for (int i = 0; i < jobsheld.size(); i ++) {
            total = total + jobsheld.get(i).getTotalPay();
        }
        return total;
    }

    public String getHighestPayingJobTitle() {
        if (jobsheld.isEmpty()){
            return "ERROR";
        }
        Job highestPayingJob = jobsheld.get(0);
        for (int i = 1; i < jobsheld.size(); i++) {
            if (jobsheld.get(i).getTotalPay() > highestPayingJob.getTotalPay()){
                highestPayingJob = jobsheld.get(i);
            }
        }
        return highestPayingJob.getPositionTitle();
    }

    @Override
    public String toString() {
        String result = "Employee: " + getName() + "\n" + "Highest paying job: " + getHighestPayingJobTitle()
        + "\n" + "Total Salary: " + getSalaryForAllJobs();
        return result;
        }

    
}
