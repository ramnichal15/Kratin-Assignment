import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SeniorApp {

	    // Rest of the class implementation...

	    public static void main(String[] args) {
	        SeniorApp seniorApp = new SeniorApp();

	        seniorApp.addContact("John Smith", "1234567890");
	        seniorApp.addMedicineReminder("Medicine 1", LocalTime.of(8, 0)); // Example reminder time
	        seniorApp.addMealAlert("Breakfast", LocalTime.of(9, 0)); // Example alert time

	        // Other method calls and application logic

	        // Wait for a while to allow the scheduled tasks to run
	        try {
	            Thread.sleep(60000); // Wait for 1 minute
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	


    private Map<String, String> contacts;
    private Map<String, LocalTime> medicineReminders;
    private Map<String, LocalTime> mealAlerts;
    private Map<String, String> emergencyContacts;

    public SeniorApp() {
    	
        contacts = new HashMap<>();
        medicineReminders = new HashMap<>();
        mealAlerts = new HashMap<>();
        emergencyContacts = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber) {
        contacts.put(name, phoneNumber);
    }

    public String getContact(String name) {
        return contacts.get(name);
    }

    public void deleteContact(String name) {
        contacts.remove(name);
    }

    public void addMedicineReminder(String medicineName, LocalTime reminderTime) {
        medicineReminders.put(medicineName, reminderTime);
        scheduleReminder(medicineName, reminderTime);
    }

    public void deleteMedicineReminder(String medicineName) {
        medicineReminders.remove(medicineName);
    }

    public void addMealAlert(String mealName, LocalTime alertTime) {
        mealAlerts.put(mealName, alertTime);
        scheduleAlert(mealName, alertTime);
    }

    public void deleteMealAlert(String mealName) {
        mealAlerts.remove(mealName);
    }

    public void addEmergencyContact(String name, String phoneNumber) {
        emergencyContacts.put(name, phoneNumber);
    }

    public String getEmergencyContact(String name) {
        return emergencyContacts.get(name);
    }

    public void deleteEmergencyContact(String name) {
        emergencyContacts.remove(name);
    }

    private void scheduleReminder(String medicineName, LocalTime reminderTime) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Reminder: It's time to take " + medicineName);
            }
        };

        // Schedule the task to run at the specified reminderTime
        timer.schedule(task, getTimeToNextReminder(reminderTime));
    }

    private void scheduleAlert(String mealName, LocalTime alertTime) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Alert: It's time for " + mealName);
            }
        };

        // Schedule the task to run at the specified alertTime
        timer.schedule(task, getTimeToNextAlert(alertTime));
    }

    private long getTimeToNextReminder(LocalTime reminderTime) {
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), currentTime);
        LocalDateTime reminderDateTime = LocalDateTime.of(LocalDate.now(), reminderTime);

        if (currentDateTime.isBefore(reminderDateTime)) {
            return currentDateTime.until(reminderDateTime, ChronoUnit.SECONDS) * 1000L;
        } else {
            LocalDateTime nextDayReminderDateTime = reminderDateTime.plusDays(1);
            return currentDateTime.until(nextDayReminderDateTime, ChronoUnit.SECONDS) * 1000L;
        }
    }

    private long getTimeToNextAlert(LocalTime alertTime) {
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), currentTime);
        LocalDateTime alertDateTime = LocalDateTime.of(LocalDate.now(), alertTime);

        if (currentDateTime.isBefore(alertDateTime)) {
            return 0;
       
}
		return 0;}}
