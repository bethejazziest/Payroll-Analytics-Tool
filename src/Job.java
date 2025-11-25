

public class Job {

    //ATTRIBUTES
    private String positiontitle;
    private String jobdepartment;
    private String positiontype;
    private float paytotalactual;



    //METHODS
    public Job(String titlegiven, String departmentgiven, String typegiven, float paygiven) {
        this.positiontitle = titlegiven;
        this.jobdepartment = departmentgiven;
        this.positiontype = typegiven;
        this.paytotalactual = paygiven;
    }

    public float getTotalPay(){
        return paytotalactual;
    }

    public String getPositionTitle(){
        return positiontitle;
    }

    public String getDept(){
        return jobdepartment;
    }

    @Override
    public String toString(){
    String results = "Title: " + positiontitle + "\n" + "Department: " + jobdepartment + "\n" + "Yearly pay: " + paytotalactual;
    return results;
    }

}
