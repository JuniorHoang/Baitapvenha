package DoAnCaNhanQuocHuy;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String owner;
    private String phone;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String vehicleType; 
    private String fuelType;    
    private String parkingSpot; 
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Vehicle(String licensePlate, String owner, String phone, LocalDateTime entryTime, String vehicleType, String fuelType, String parkingSpot) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.phone = phone;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.parkingSpot = parkingSpot;
        this.exitTime = null;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getOwner() { return owner; }
    public String getPhone() { return phone; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public String getVehicleType() { return vehicleType; }
    public String getFuelType() { return fuelType; }
    public String getParkingSpot() { return parkingSpot; }

    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
   
    public long calculateFee() {
        if (exitTime == null) return 0;
        
        Duration duration = Duration.between(entryTime, exitTime);
        long hours = duration.toHours();
        
     
        if (fuelType.equalsIgnoreCase("1. Xe dien")) {
            return 40000; 
        } else if (fuelType.equalsIgnoreCase("2. Xe may")) {
            return 130000; 
        } else {
            return Math.max(hours, 1) * 10000; 
        }
    }

    @Override
    public String toString() {
        String exitTimeStr = (exitTime != null) ? exitTime.format(FORMATTER) : "chưa ra";
        return String.format("Biển số: %s (Chủ xe: %s, SĐT: %s)\nNgày gửi: %s\nNgày ra: %s\nLoại nhiên liệu: %s\nBãi xe: %s",
                             licensePlate, owner, phone, 
                             entryTime.format(FORMATTER), exitTimeStr, fuelType, parkingSpot);
    }
}
