package com.eventorg.util;

import com.eventorg.participant.Individual;
import com.eventorg.reward.CertificateReward;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Reads participants from a CSV file.
    // Expected format (first line is a header):
    // participantId,name,email,phone
    public static List<Individual> loadParticipants(String filePath) {

        List<Individual> participants = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line = reader.readLine();          // skip header line
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;                          // ignore blank lines
                }

                String[] parts = line.split(",");

                if (parts.length != 4) {
                    System.out.println("Skipping invalid line " + lineNumber + ": " + line);
                    continue;
                }

                Individual individual = new Individual(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim(),
                    parts[3].trim()
                );
                participants.add(individual);
            }

            System.out.println("Loaded " + participants.size() + " participants from " + filePath);

        } catch (IOException e) {
            System.out.println("Could not read " + filePath + ": " + e.getMessage());
        }

        return participants;
    }

    // Writes a certificate to certificates/<rewardId>_<name>.txt
    public static void saveCertificate(CertificateReward certificate) {

        if (!certificate.isAssigned()) {
            System.out.println("Cannot save " + certificate.getRewardId() + ": not assigned to anyone.");
            return;
        }

        File folder = new File("certificates");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String name = certificate.getAssignedTo().getDisplayName();
        String fileName = certificate.getRewardId() + "_" + name.replace(" ", "_") + ".txt";
        File file = new File(folder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write("========================================");
            writer.newLine();
            writer.write("     CERTIFICATE OF " + certificate.getCertificateType().toUpperCase());
            writer.newLine();
            writer.write("========================================");
            writer.newLine();
            writer.newLine();
            writer.write("This is to certify that " + name);
            writer.newLine();
            writer.write("has participated in " + certificate.getEvent().getName() + ".");
            writer.newLine();
            writer.newLine();
            writer.write("Certificate ID : " + certificate.getRewardId());
            writer.newLine();
            writer.write("Issued by      : " + certificate.getIssuer());
            writer.newLine();
            writer.write("Issue Date     : " + LocalDate.now());
            writer.newLine();

            System.out.println("Certificate saved to " + file.getPath());

        } catch (IOException e) {
            System.out.println("Could not save certificate: " + e.getMessage());
        }
    }
}
