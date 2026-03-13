public class Ward implements IResource {
    private int wardNumber;
    private int capacity;
    private String wardType;
    private static int occupiedBeds = 0;

    public Ward(int wardNumber, int capacity, String wardType) {
        this.wardNumber = wardNumber;
        this.capacity = capacity;
        this.wardType = wardType;
    }

    @Override
    public boolean checkAvailability() {
        return occupiedBeds < capacity;
    }

    @Override
    public void assign() {
        if (checkAvailability()) {
            occupiedBeds++;
            System.out.println("Patient assigned to ward " + wardNumber + ". Occupied beds: " + occupiedBeds);
        } else {
            System.out.println("Ward " + wardNumber + " is full.");
        }
    }

    public int getWardNumber() { return wardNumber; }
    public void setWardNumber(int wardNumber) { this.wardNumber = wardNumber; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public String getWardType() { return wardType; }
    public void setWardType(String wardType) { this.wardType = wardType; }
    public static int getOccupiedBeds() { return occupiedBeds; }
}