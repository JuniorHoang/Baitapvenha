package DoAnCaNhanQuocHuy;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingLotManager {
    private List<Vehicle> parkedVehicles;

    public ParkingLotManager() {
        this.parkedVehicles = new ArrayList<>();
    }
    
    public boolean addVehicle(Vehicle vehicle) {
        if (findVehicle(vehicle.getLicensePlate()).isPresent()) {
            return false; 
        }
        parkedVehicles.add(vehicle);
        return true;
    }
    
    public Optional<Vehicle> removeVehicle(String licensePlate, LocalDateTime exitTime) {
        Optional<Vehicle> foundVehicle = findVehicle(licensePlate);
        if (foundVehicle.isPresent()) {
            Vehicle vehicle = foundVehicle.get();
            vehicle.setExitTime(exitTime); 
            parkedVehicles.remove(vehicle); 
            return Optional.of(vehicle);
        }
        return Optional.empty();
    }

    public Optional<Vehicle> findVehicle(String licensePlate) {
        return parkedVehicles.stream()
                .filter(v -> v.getLicensePlate().equalsIgnoreCase(licensePlate))
                .findFirst();
    }
    
    public String calculateAllFees() {
        return parkedVehicles.stream()
                .filter(v -> v.getExitTime() != null)
                .map(v -> String.format("Biển số: %s, Phí giữ xe: %.1f VND", v.getLicensePlate(), (double)v.calculateFee()))
                .collect(Collectors.joining("\n"));
    }
    
    public String getVehicleCounts() {
        long carCount = parkedVehicles.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase("Car"))
                .count();
        long otherCount = parkedVehicles.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase("Other"))
                .count();
        
        return String.format("Danh sách xe hiện có:\nSố ô tô: %d\nSố xe khác: %d", carCount, otherCount);
    }

  
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(parkedVehicles);
    }
    
    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(parkedVehicles);
        }
    }
    
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            parkedVehicles = (List<Vehicle>) ois.readObject();
        }
    }
}
