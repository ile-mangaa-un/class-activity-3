public class Nurse extends Employee {
    private String department;

    public Nurse(String name, int age, String contactInfo, String employeeID, String department) {
        super(name, age, contactInfo, employeeID);
        this.department = department;
    }

    @Override
    public void performRole() {
        System.out.println(getName() + " is a nurse in " + department + " department. Assisting patients.");
    }

    public void administerMedication() {
        System.out.println(getName() + " is administering medication.");
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}