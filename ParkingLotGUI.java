package DoAnCaNhanQuocHuy;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ParkingLotGUI extends JFrame {

    private ParkingLotManager manager;
    private JTextArea displayArea;
    private JTextField licensePlateField, ownerField, phoneField, entryTimeField, exitTimeField;
    private JComboBox<String> vehicleTypeCombo, fuelTypeCombo;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"); 

    public ParkingLotGUI() {
        manager = new ParkingLotManager();
        setTitle("Parking Lot Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        displayArea = new JTextArea(15, 60);
        displayArea.setBackground(new Color(255, 255, 204));
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        String[] buttonLabels = {"Thêm Xe", "Xóa Xe", "Hiển Thị", "Tính Tổng Phí", "Xuất File", "Tìm Xe", "Hiển Thị Số Lượng"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this::handleButtonClick);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
        
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        
        licensePlateField = new JTextField(15);
        ownerField = new JTextField(15);
        phoneField = new JTextField(15);
        entryTimeField = new JTextField(15); 
        exitTimeField = new JTextField(15);
        
        vehicleTypeCombo = new JComboBox<>(new String[]{"Car", "Other"});
        fuelTypeCombo = new JComboBox<>(new String[]{"1. Xe dien", "2. Xe may", "3. Xăng/Dầu"});

        formPanel.add(new JLabel("Biển số:"));
        formPanel.add(licensePlateField);
        formPanel.add(new JLabel("Chủ xe:"));
        formPanel.add(ownerField);
        formPanel.add(new JLabel("SĐT:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Thời gian vào (dd/M/yyyy HH:mm):"));
        formPanel.add(entryTimeField);
        formPanel.add(new JLabel("Thời gian ra (dd/M/yyyy HH:mm hoặc để trống):"));
        formPanel.add(exitTimeField);
        formPanel.add(new JLabel("Loại xe (Car/Other):"));
        formPanel.add(vehicleTypeCombo);
        formPanel.add(new JLabel("Nhiên liệu/Loại xe:"));
        formPanel.add(fuelTypeCombo);
        
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin xe"));
        panel.add(formPanel, BorderLayout.NORTH);
        return panel;
    }

    private void handleButtonClick(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (!command.equals("Xuất File")) {
            displayArea.setText(""); 
        }

        try {
            switch (command) {
                case "Thêm Xe":
                    addVehicleAction();
                    break;
                case "Xóa Xe":
                    removeVehicleAction();
                    break;
                case "Hiển Thị":
                    displayAllAction();
                    break;
                case "Tính Tổng Phí":
                    displayArea.setText(manager.calculateAllFees());
                    break;
                case "Tìm Xe":
                    findVehicleAction();
                    break;
                case "Hiển Thị Số Lượng":
                    displayArea.setText(manager.getVehicleCounts());
                    break;
                case "Xuất File":
                    exportFileAction(); 
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi Hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addVehicleAction() throws DateTimeParseException {
        String licensePlate = licensePlateField.getText();
        String entryTimeStr = entryTimeField.getText().trim();
        
        if (entryTimeStr.isEmpty()) {
            throw new IllegalArgumentException("Thời gian vào không được để trống.");
        }
        
        LocalDateTime entryTime = LocalDateTime.parse(entryTimeStr, FORMATTER);
        String owner = ownerField.getText();
        String phone = phoneField.getText();
        String vehicleType = (String) vehicleTypeCombo.getSelectedItem();
        String fuelType = (String) fuelTypeCombo.getSelectedItem();
        
        String parkingSpot = (vehicleType.equals("Car") ? "Bãi xe số 2 và 4 (Xe dien)" : "Bãi xe số 3 và 5");
        
        Vehicle newVehicle = new Vehicle(licensePlate, owner, phone, entryTime, vehicleType, fuelType, parkingSpot);
        
        String exitTimeStr = exitTimeField.getText().trim();
        if (!exitTimeStr.isEmpty()) {
            LocalDateTime exitTime = LocalDateTime.parse(exitTimeStr, FORMATTER);
            newVehicle.setExitTime(exitTime); 
        }
        
        if (manager.addVehicle(newVehicle)) {
            displayArea.append("Đã thêm xe: " + licensePlate + "\n");
            displayArea.append(newVehicle.toString().replace("\n", "\n  ") + "\n"); 

            if (newVehicle.getExitTime() != null) {
                 displayArea.append("\n");
                 displayArea.append("-----------------------------\n");
                 displayArea.append("--- THANH TOÁN PHÍ GIỮ XE ---\n");
                 displayArea.append("Biển số: " + licensePlate + "\n");
                 displayArea.append("Phí giữ xe cần thanh toán: " + newVehicle.calculateFee() + " VND\n");
                 displayArea.append("-----------------------------\n");
            }
            
        } else {
            displayArea.append("Lỗi: Biển số " + licensePlate + " đã tồn tại trong bãi.\n");
        }
    }
    
    private void removeVehicleAction() throws DateTimeParseException {
        String licensePlate = licensePlateField.getText();
        if (licensePlate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập biển số cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        LocalDateTime exitTime = LocalDateTime.now();
        String exitTimeStr = exitTimeField.getText().trim();

        if (!exitTimeStr.isEmpty()) {
            exitTime = LocalDateTime.parse(exitTimeStr, FORMATTER); 
        }
        
        Optional<Vehicle> removedVehicle = manager.removeVehicle(licensePlate, exitTime);
        
        if (removedVehicle.isPresent()) {
            displayArea.append("Đã xóa xe: " + licensePlate + "\n");
        } else {
            displayArea.append("Lỗi: Không tìm thấy xe có biển số " + licensePlate + ".\n");
        }
    }
    
    private void findVehicleAction() {
        String licensePlate = licensePlateField.getText();
        if (licensePlate.isEmpty()) return;
        
        Optional<Vehicle> foundVehicle = manager.findVehicle(licensePlate);
        
        if (foundVehicle.isPresent()) {
            displayArea.append("Xe tìm thấy:\n");
            displayArea.append(foundVehicle.get().toString() + "\n");
        } else {
            displayArea.append("Không tìm thấy xe có biển số " + licensePlate + ".\n");
        }
    }
    
    private void displayAllAction() {
        manager.getAllVehicles().forEach(v -> displayArea.append(v.toString() + "\n---\n"));
    }
    
    private void exportFileAction() {
        String fileName = JOptionPane.showInputDialog(this, "Nhập tên file để xuất:", "Xuất File", JOptionPane.QUESTION_MESSAGE);
        if (fileName != null && !fileName.trim().isEmpty()) {
            try {
                manager.saveToFile(fileName.trim() + ".dat");
                JOptionPane.showMessageDialog(this, "Đã xuất dữ liệu ra file " + fileName + ".dat", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ParkingLotGUI::new);
    }
}
