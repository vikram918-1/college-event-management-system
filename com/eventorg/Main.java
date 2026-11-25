package com.eventorg;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.eventorg.participant.Individual;
import com.eventorg.participant.Participant;
import com.eventorg.participant.Registration;
import com.eventorg.participant.Team;

import com.eventorg.event.Attendance;
import com.eventorg.event.CulturalFestival;
import com.eventorg.event.Event;
import com.eventorg.event.EventManager;
import com.eventorg.event.Hackathon;
import com.eventorg.event.Schedule;
import com.eventorg.event.TechnicalCompetition;
import com.eventorg.event.Venue;
import com.eventorg.event.Workshop;

import com.eventorg.reward.CashReward;
import com.eventorg.reward.CertificateReward;
import com.eventorg.reward.MerchandiseReward;
import com.eventorg.reward.Reward;
import com.eventorg.reward.RewardManager;
import com.eventorg.reward.RewardPolicy;

import com.eventorg.exception.EventFullException;
import com.eventorg.exception.EventNotFoundException;
import com.eventorg.exception.InvalidRegistrationException;
import com.eventorg.exception.InvalidRewardException;

import com.eventorg.util.FileManager;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    // all data the system works with
    private static List<Participant> participants = new ArrayList<>();
    private static List<Venue> venues = new ArrayList<>();
    private static EventManager<Event> manager = new EventManager<>();
    private static EventManager<Hackathon> hackathonManager = new EventManager<>();
    private static RewardManager<Reward> rewardManager = new RewardManager<>();
    private static RewardManager<CashReward> cashManager = new RewardManager<>();
    private static RewardPolicy topThree = new RewardPolicy("POL1", 3, "Top 3 positions only", 20000);
    private static Set<String> certifiedEvents = new HashSet<>();
    private static int rewardCount = 1;
    private static int certificateCount = 1;

    public static void main(String[] args) {

        setup();

        while (true) {
            System.out.println();
            System.out.println("========== COLLEGE EVENT ORGANIZER ==========");
            System.out.println("1. Registration");
            System.out.println("2. Event Manager");
            System.out.println("3. Reward Manager");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: registrationMenu(); break;
                case 2: eventMenu();        break;
                case 3: rewardMenu();       break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ---------------------------------------------------------------
    // submenus
    // ---------------------------------------------------------------
    private static void registrationMenu() {
        while (true) {
            System.out.println();
            System.out.println("----- REGISTRATION -----");
            System.out.println("1. View participants");
            System.out.println("2. Add participant");
            System.out.println("3. Create team");
            System.out.println("4. Register for an event");
            System.out.println("5. View registrations");
            System.out.println("6. Cancel a registration");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: viewParticipants();   break;
                case 2: addParticipant();     break;
                case 3: createTeam();         break;
                case 4: registerForEvent();   break;
                case 5: viewRegistrations();  break;
                case 6: cancelRegistration(); break;
                case 0: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void eventMenu() {
        while (true) {
            System.out.println();
            System.out.println("----- EVENT MANAGER -----");
            System.out.println("1. View events");
            System.out.println("2. Allocate venue");
            System.out.println("3. Mark attendance");
            System.out.println("4. Conduct event");
            System.out.println("5. Cancel event");
            System.out.println("6. View hackathon themes");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: viewEvents();          break;
                case 2: allocateVenue();       break;
                case 3: markAttendance();      break;
                case 4: conductEvent();        break;
                case 5: cancelEvent();         break;
                case 6: viewHackathonThemes(); break;
                case 0: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void rewardMenu() {
        while (true) {
            System.out.println();
            System.out.println("----- REWARD MANAGER -----");
            System.out.println("1. Record winner and give reward");
            System.out.println("2. Generate participation certificates");
            System.out.println("3. View winners");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: recordWinner();         break;
                case 2: generateCertificates(); break;
                case 3: viewWinners();          break;
                case 0: return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ---------------------------------------------------------------
    // setup: load participants from file and create events and venues
    // ---------------------------------------------------------------
    private static void setup() {

        participants.addAll(FileManager.loadParticipants("participants.csv"));

        Workshop workshop = new Workshop(
            "E001", "Java Workshop", "2026-10-10", 50,
            "Dr. Kumar", "Java Programming", 120
        );
        workshop.setSchedule(new Schedule(
            "S001", "2026-10-10", "10:00", "12:00", "Java Basics", "Dr. Kumar"
        ));

        Hackathon hackathon = new Hackathon(
            "E002", "AI Hackathon", "2026-10-12", 100, 24, "Artificial Intelligence"
        );

        TechnicalCompetition codeSprint = new TechnicalCompetition(
            "E003", "Code Sprint", "2026-10-14", 30, 5, "Medium", "Correctness and speed"
        );

        CulturalFestival festival = new CulturalFestival(
            "E004", "Cultural Night", "2026-10-16", 80, "Music", "Solo"
        );

        Workshop gitBasics = new Workshop(
            "E005", "Git Basics", "2026-10-18", 2,
            "Ms. Priya", "Version control", 60
        );

        manager.addEvent(workshop);
        manager.addEvent(hackathon);
        manager.addEvent(codeSprint);
        manager.addEvent(festival);
        manager.addEvent(gitBasics);

        hackathonManager.addEvent(hackathon);   // only hackathons allowed here

        Venue auditorium = new Venue("V001", "Main Auditorium", "SSN College", 500,
                                     Arrays.asList("Projector", "AC", "WiFi"));
        Venue seminarHall = new Venue("V002", "Seminar Hall", "SSN College", 100,
                                      Arrays.asList("Projector", "WiFi"));
        venues.add(auditorium);
        venues.add(seminarHall);
        manager.addVenue(auditorium);
        manager.addVenue(seminarHall);

        rewardManager.addPolicy(topThree);
    }

    // ---------------------------------------------------------------
    // menu options
    // ---------------------------------------------------------------
    private static void viewEvents() {
        System.out.println("--- Events ---");
        for (Event e : manager.getEvents()) {
            String venue = (e.getVenue() == null) ? "not allocated" : e.getVenue().getName();
            String status = e.isCancelled() ? "  [CANCELLED]" : "";
            System.out.println(
                e.getEventId() + " | " + e.getName() + " | " + e.getEventType() +
                " | " + e.getDate() + " | " + e.getParticipantCount() + "/" +
                e.getMaxParticipants() + " registered | venue: " + venue + status
            );
        }
    }

    private static void viewParticipants() {
        System.out.println("--- Participants ---");
        if (participants.isEmpty()) {
            System.out.println("No participants yet.");
        }
        for (Participant p : participants) {
            String type = (p instanceof Team) ? "Team" : "Individual";
            System.out.println(p.getParticipantId() + " | " + p.getDisplayName() + " | " + type);
        }
    }

    private static void addParticipant() {
        String id = readLine("Participant ID: ");
        if (findParticipant(id) != null) {
            System.out.println("ID " + id + " already exists.");
            return;
        }
        String name = readLine("Name: ");
        String email = readLine("Email: ");
        String phone = readLine("Phone: ");

        participants.add(new Individual(id, name, email, phone));
        System.out.println(name + " added.");
    }

    private static void createTeam() {
        String id = readLine("Team ID: ");
        if (findParticipant(id) != null) {
            System.out.println("ID " + id + " already exists.");
            return;
        }
        String name = readLine("Team name: ");
        String memberIds = readLine("Member IDs (comma separated, e.g. P001,P002): ");

        Team team = new Team(id, name);

        for (String memberId : memberIds.split(",")) {
            Participant p = findParticipant(memberId.trim());
            if (p instanceof Individual) {
                team.addMember((Individual) p);
            } else {
                System.out.println("Skipping " + memberId.trim() + ": not an individual participant.");
            }
        }

        if (team.getMembers().isEmpty()) {
            System.out.println("Team not created: it needs at least one member.");
            return;
        }

        participants.add(team);
        System.out.println("Team " + name + " created with " + team.getMembers().size() + " member(s).");
    }

    private static void registerForEvent() {
        Participant p = askParticipant();
        if (p == null) return;

        try {
            Event event = manager.searchEvent(readLine("Event ID: "));
            event.registerParticipant(p);
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (EventFullException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private static void viewRegistrations() {
        Participant p = askParticipant();
        if (p == null) return;

        if (p.getRegistrations().isEmpty()) {
            System.out.println(p.getDisplayName() + " has no registrations.");
            return;
        }
        System.out.println("--- Registrations of " + p.getDisplayName() + " ---");
        for (Registration r : p.getRegistrations()) {
            System.out.println(
                r.getRegistrationId() + " | " + r.getEvent().getName() +
                " | " + r.getRegistrationDate() + " | " + r.getStatus()
            );
        }
    }

    private static void cancelRegistration() {
        Participant p = askParticipant();
        if (p == null) return;

        try {
            Registration r = p.getRegistration(readLine("Registration ID (e.g. REG1): "));
            r.cancel();                                  // throws if already cancelled
            r.getEvent().removeParticipant(p);          // frees the seat
            System.out.println(r.getRegistrationId() + " cancelled.");
        } catch (InvalidRegistrationException e) {
            System.out.println("Cannot cancel: " + e.getMessage());
        }
    }

    private static void allocateVenue() {
        try {
            Event event = manager.searchEvent(readLine("Event ID: "));
            Venue venue = findVenue(readLine("Venue ID (V001 / V002): "));
            if (venue == null) {
                System.out.println("No venue with that ID.");
                return;
            }
            manager.allocateVenue(event, venue);
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void markAttendance() {
        try {
            Event event = manager.searchEvent(readLine("Event ID: "));
            Participant p = askParticipant();
            if (p == null) return;

            if (!event.getParticipants().contains(p)) {
                System.out.println(p.getDisplayName() + " is not registered for " + event.getName());
                return;
            }

            String checkIn = readLine("Check-in time (HH:MM): ");
            String checkOut = readLine("Check-out time (HH:MM): ");
            LocalTime.parse(checkIn);     // throws DateTimeParseException if invalid
            LocalTime.parse(checkOut);

            Attendance attendance = new Attendance(p, event.getDate());
            attendance.markPresent(checkIn);
            attendance.checkOut(checkOut);
            event.addAttendance(attendance);

            System.out.println(
                p.getDisplayName() + " attended for " +
                attendance.calculateDuration() + " minutes."
            );
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time. Use HH:MM, for example 09:30.");
        }
    }

    private static void conductEvent() {
        try {
            Event event = manager.searchEvent(readLine("Event ID: "));
            if (event.isCancelled()) {
                System.out.println(event.getName() + " is cancelled.");
                return;
            }
            event.conductEvent();
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void recordWinner() {
        try {
            Event event = manager.searchEvent(readLine("Event ID: "));
            Participant p = askParticipant();
            if (p == null) return;

            if (!event.getParticipants().contains(p)) {
                System.out.println(p.getDisplayName() + " did not take part in " + event.getName());
                return;
            }

            int position = readInt("Position (1, 2, 3...): ");
            int type = readInt("Reward type (1 = Cash, 2 = Merchandise): ");
            String rewardId = "R" + String.format("%03d", rewardCount++);

            if (type == 1) {
                double amount = readDouble("Cash amount (Rs.): ");
                CashReward cash = new CashReward(
                    rewardId, "Cash Prize - Position " + position, event.getName() + " prize",
                    event, topThree, amount, "INR"
                );
                rewardManager.assignReward(p, cash, position);
                rewardManager.addReward(cash);
                cashManager.addReward(cash);
                cash.processPayment();

            } else if (type == 2) {
                String item = readLine("Item name: ");
                int quantity = readInt("Quantity: ");
                String size = readLine("Size: ");
                double price = readDouble("Unit price (Rs.): ");
                MerchandiseReward merch = new MerchandiseReward(
                    rewardId, item + " - Position " + position, event.getName() + " prize",
                    event, topThree, item, quantity, size, price
                );
                rewardManager.assignReward(p, merch, position);
                rewardManager.addReward(merch);
                merch.dispatchItem();

            } else {
                System.out.println("Invalid reward type.");
            }

        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidRewardException e) {
            System.out.println("Reward not given: " + e.getMessage());
        }
    }

    private static void generateCertificates() {
        try {
            Event event = manager.searchEvent(readLine("Event ID: "));

            if (event.getParticipants().isEmpty()) {
                System.out.println("No one is registered for " + event.getName());
                return;
            }
            if (certifiedEvents.contains(event.getEventId())) {
                System.out.println("Certificates were already generated for " + event.getName());
                return;
            }

            for (Participant p : event.getParticipants()) {
                CertificateReward certificate = new CertificateReward(
                    "CERT" + String.format("%03d", certificateCount++),
                    "Participation Certificate", event.getName() + " participation",
                    event, null, "Participation", "SSN College"
                );
                rewardManager.assignReward(p, certificate, 0);
                rewardManager.addReward(certificate);
                certificate.generateCertificate();
                FileManager.saveCertificate(certificate);
            }
            certifiedEvents.add(event.getEventId());

        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidRewardException e) {
            System.out.println("Certificate error: " + e.getMessage());
        }
    }

    private static void viewWinners() {
        System.out.println("--- Winners ---");
        boolean any = false;
        for (Reward reward : rewardManager.getRewards()) {
            if (reward.isAssigned() && reward.getPosition() > 0) {
                any = true;
                System.out.println(
                    reward.getEvent().getName() + " | Position " + reward.getPosition() +
                    " | " + reward.getAssignedTo().getDisplayName() + " | " +
                    reward.getName() + " | worth Rs." + reward.calculateValue()
                );
            }
        }
        if (!any) {
            System.out.println("No winners recorded yet.");
        }

        // cashManager holds only CashRewards, so getAmount() works without a cast
        double totalCash = 0;
        for (CashReward cash : cashManager.getRewards()) {
            totalCash += cash.getAmount();
        }
        System.out.println("Total cash prizes given: Rs." + totalCash);
    }

    private static void cancelEvent() {
        try {
            manager.cancelEvent(readLine("Event ID to cancel: "));
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewHackathonThemes() {
        System.out.println("--- Hackathons ---");
        // hackathonManager holds only Hackathons, so getTheme() works without a cast
        for (Hackathon h : hackathonManager.getEvents()) {
            System.out.println(
                h.getEventId() + " | " + h.getName() + " | theme: " + h.getTheme() +
                " | " + h.getDurationHours() + " hours"
            );
        }
    }

    // ---------------------------------------------------------------
    // input helpers
    // ---------------------------------------------------------------
    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!sc.hasNextLine()) {          // input ended (e.g. Ctrl+D)
            System.out.println();
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static Participant askParticipant() {
        Participant p = findParticipant(readLine("Participant or Team ID: "));
        if (p == null) {
            System.out.println("No participant with that ID.");
        }
        return p;
    }

    private static Participant findParticipant(String id) {
        for (Participant p : participants) {
            if (p.getParticipantId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static Venue findVenue(String id) {
        for (Venue v : venues) {
            if (v.getVenueId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }
}