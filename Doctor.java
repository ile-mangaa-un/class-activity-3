public class Doctor extends Employee implements IManageable {
    private String specialization;

    public Doctor(String name, int age, String contactInfo, String employeeID, String specialization) {
        super(name, age, contactInfo, employeeID);
        this.specialization = specialization;
    }

    @Override
    public void performRole() {
        System.out.println(getName() + " is a doctor specializing in " + specialization + ". Consulting patients.");
    }

    @Override
    public void saveData() {
        System.out.println("Saving doctor " + getName() + " (ID: " + getEmployeeID() + ") to database.");
    }

    @Override
    public void deleteData() {
        System.out.println("Deleting doctor " + getName() + " from registry.");
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}