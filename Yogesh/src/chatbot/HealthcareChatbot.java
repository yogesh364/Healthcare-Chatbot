package chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HealthcareChatbot {

    private static final Map<String, DiseaseInfo> diseases = new HashMap<>();
    private static final Map<String, Hospital> hospitals = new HashMap<>();

    static {
        // Adding diseases and their respective information
        diseases.put("cold", new DiseaseInfo(
                new String[]{"Symptoms: Runny nose, sore throat", "Prevention: Wash hands frequently"},
                new MedicineInfo("Acetaminophen", "Ibuprofen", "Antihistamines"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("flu", new DiseaseInfo(
                new String[]{"Symptoms: Fever, body aches", "Prevention: Get vaccinated yearly"},
                new MedicineInfo("Tamiflu", "Acetaminophen", "Oseltamivir"),
                new String[]{"mild", "moderate", "severe"}
        ));
       diseases.put("diabetes", new DiseaseInfo(
                new String[]{"Symptoms: Excessive thirst, frequent urination", "Prevention: Maintain a healthy diet"},
                new MedicineInfo("Insulin", "Metformin", "Glipizide"),
                new String[]{"low", "medium", "high"}
        ));
        diseases.put("asthma", new DiseaseInfo(
                new String[]{"Symptoms: Shortness of breath, wheezing", "Prevention: Avoid triggers like smoke or pollen"},
                new MedicineInfo("Albuterol", "Inhaled Corticosteroids", "Oral Corticosteroids"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("hypertension", new DiseaseInfo(
                new String[]{"Symptoms: High blood pressure, headaches", "Prevention: Exercise regularly, reduce salt intake"},
                new MedicineInfo("Lifestyle modifications", "Thiazide diuretics", "Calcium channel blockers"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("migraine", new DiseaseInfo(
                new String[]{"Symptoms: Throbbing headache, sensitivity to light", "Prevention: Identify triggers, manage stress"},
                new MedicineInfo("Aspirin", "Sumatriptan", "Ergotamine"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("arthritis", new DiseaseInfo(
                new String[]{"Symptoms: Joint pain, stiffness", "Prevention: Maintain a healthy weight, exercise regularly"},
                new MedicineInfo("Ibuprofen", "Naproxen", "Corticosteroids"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("anxiety", new DiseaseInfo(
                new String[]{"Symptoms: Excessive worry, restlessness", "Prevention: Practice relaxation techniques, seek support"},
                new MedicineInfo("Therapy", "Selective serotonin reuptake inhibitors", "Benzodiazepines"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("depression", new DiseaseInfo(
                new String[]{"Symptoms: Persistent sadness, loss of interest", "Prevention: Regular exercise, talk therapy"},
                new MedicineInfo("Psychotherapy", "Selective serotonin reuptake inhibitors", "Tricyclic antidepressants"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("insomnia", new DiseaseInfo(
                new String[]{"Symptoms: Difficulty falling asleep, waking up often", "Prevention: Maintain a regular sleep schedule, limit caffeine"},
                new MedicineInfo("Behavioral therapy", "Short-acting sedative-hypnotics", "Benzodiazepines"),
                new String[]{"mild", "moderate", "severe"}
        ));
        diseases.put("fever", new DiseaseInfo(
                new String[]{"Symptoms: High body temperature", "Prevention: Stay hydrated, rest"},
                new MedicineInfo("Aspirin", "Ibuprofen", "Paracetamol"),
                new String[]{"low", "medium", "high"}
        ));
        diseases.put("headache", new DiseaseInfo(
                new String[]{"Symptoms: Pain in the head or neck", "Prevention: Manage stress, stay hydrated"},
                new MedicineInfo("Aspirin", "Aspirin", "Aspirin"),
                new String[]{"mild", "moderate", "severe"}
        ));

        // Adding hospitals and their respective information
        hospitals.put("1", new Hospital("RK Hospital", "No-18, Ramachandra Iyer Street, Nehru Nagar, Chromepet, Chennai, Tamil Nadu 600044", "044-12345678", "9 AM - 12 PM and 2 PM - 6 PM"));
        hospitals.put("2", new Hospital("MIOT Hospital", "4/112, Mount Poonamallee Rd, Sathya Nagar, Manapakkam, Chennai, Tamil Nadu 600089", "044-23456789", "24 hours"));
        hospitals.put("3", new Hospital("KAUVERY", "81, TTK Road Junction, CIT Colony, Alwarpet, Chennai, Tamil Nadu 600018", "044-34567890", "10 AM - 7 PM"));
        hospitals.put("4", new Hospital("GH", "GH Post Office, Poonamallee High Road, 3, Grand Southern Trunk Rd, near Park Town, Near Chennai Central, Park Town, Chennai, Tamil Nadu 600003", "044-45678901", "24 hours"));
        hospitals.put("5", new Hospital("APOLLO", "Greams Lane, 21, Greams Rd, Thousand Lights West, Thousand Lights, Chennai, Tamil Nadu 600006", "044-56789012", "8 AM - 12 PM and 1 PM - 9 PM"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Healthcare Chatbot!");
        while (true) {
            System.out.print("What disease are you experiencing? (Type 'quit' to exit): ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (diseases.containsKey(userInput)) {
                DiseaseInfo diseaseInfo = diseases.get(userInput);
                System.out.println("Here are some points about " + capitalize(userInput) + ":");
                for (String point : diseaseInfo.getInfo()) {
                    System.out.println("- " + point);
                }

                String severityOptions = String.join(", ", diseaseInfo.getSeverity());
                System.out.print("What is the severity of your " + userInput + "? (" + severityOptions + "): ");
                String severity = scanner.nextLine().trim().toLowerCase();
                
                if (isValidSeverity(severity, diseaseInfo.getSeverity())) {
                    String recommendedMedicine = diseaseInfo.getMedicine().getMedicineBySeverity(severity);
                    System.out.println("Recommended medicine: " + recommendedMedicine);

                    System.out.print("Would you like to fix an appointment? (yes/no): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        System.out.println("Please select one of the following hospitals or clinics:");
                        for (Map.Entry<String, Hospital> entry : hospitals.entrySet()) {
                            System.out.println(entry.getKey() + ". " + entry.getValue().getName() + " - " + entry.getValue().getLocation() + " (Timings: " + entry.getValue().getTimings() + ")");
                        }

                        System.out.print("Enter the number of your choice: ");
                        String appointmentChoice = scanner.nextLine().trim();
                        if (hospitals.containsKey(appointmentChoice)) {
                            System.out.print("Please enter your preferred appointment time: ");
                            String appointmentTime = scanner.nextLine().trim();
                            Hospital chosenHospital = hospitals.get(appointmentChoice);
                            System.out.println("Appointment fixed at " + appointmentTime + " at " + chosenHospital.getName() + " located at " + chosenHospital.getLocation() + ".");
                            System.out.println("Contact number: " + chosenHospital.getContact());
                        } else {
                            System.out.println("Invalid choice. Appointment not fixed.");
                        }
                    } else {
                        System.out.print("Would you like to suggest a doctor? (yes/no): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                            System.out.println("Please fix an appointment with your suggested doctor.");
                        } else {
                            System.out.println("Okay, take care!");
                        }
                    }
                } else {
                    System.out.println("Invalid severity level.");
                }
            } else {
                System.out.println("I'm sorry, I don't have information on that disease.");
            }
        }
        scanner.close();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static boolean isValidSeverity(String severity, String[] validSeverities) {
        for (String validSeverity : validSeverities) {
            if (validSeverity.equalsIgnoreCase(severity)) {
                return true;
            }
        }
        return false;
    }
}

class DiseaseInfo {
    private final String[] info;
    private final MedicineInfo medicine;
    private final String[] severity;

    public DiseaseInfo(String[] info, MedicineInfo medicine, String[] severity) {
        this.info = info;
        this.medicine = medicine;
        this.severity = severity;
    }

    public String[] getInfo() {
        return info;
    }

    public MedicineInfo getMedicine() {
        return medicine;
    }

    public String[] getSeverity() {
        return severity;
    }
}

class MedicineInfo {
    private final String mild;
    private final String moderate;
    private final String severe;

    public MedicineInfo(String mild, String moderate, String severe) {
        this.mild = mild;
        this.moderate = moderate;
        this.severe = severe;
    }

    public String getMedicineBySeverity(String severity) {
        switch (severity.toLowerCase()) {
            case "mild":
                return mild;
            case "moderate":
                return moderate;
            case "severe":
                return severe;
            default:
                return null;
        }
    }
}

class Hospital {
    private final String name;
    private final String location;
    private final String contact;
    private final String timings;

    public Hospital(String name, String location, String contact, String timings) {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.timings = timings;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getTimings() {
        return timings;
    }
}
