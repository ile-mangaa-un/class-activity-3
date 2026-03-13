public class Patient extends Person {
    private String patientID;
    private String ailment;
    private double billAmount;

    public Patient(String name, int age, String contactInfo, String patientID, String ailment) {
        super(name, age, contactInfo);
        this.patientID = patientID;
        this.ailment = ailment;
        this.billAmount = 0.0;
    }

    @Override
    public void performRole() {
        System.out.println(getName() + " is a patient with ailment: " + ailment + ". Undergoing treatment.");
    }

    public void payBill() {
        this.billAmount = 500.0;
        System.out.println(getName() + " paid bill of $" + billAmount);
    }

    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public String getAilment() { return ailment; }
    public void setAilment(String ailment) { this.ailment = ailment; }
    public double getBillAmount() { return billAmount; }
    public void setBillAmount(double billAmount) { this.billAmount = billAmount; }
}