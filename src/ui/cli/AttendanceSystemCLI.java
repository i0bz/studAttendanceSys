package ui.cli;

import controllers.AttendanceSystemController;
import entity.AttendanceSheet;
import utility.ParseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AttendanceSystemCLI {
    private final AttendanceSystemController attendanceSystem;
    private final InputValidator validator;
    private final Scanner input;


    AttendanceSystemCLI(Scanner input, AttendanceSystemController attendanceSystem, InputValidator validator) {
        this.input = input;
        this.attendanceSystem = attendanceSystem;
        this.validator = validator;
    }



    //TODO Split this up later


    void initCLI() {
        System.out.println();

        List<String> attendanceLists = attendanceSystem.attendanceDateLists();
        int i = 0, decision;


        System.out.println();
        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return;
        }



        for (String attendanceDate : attendanceLists) {
            System.out.println(++i + ". " + attendanceDate);
        }

        System.out.println();
        System.out.print("Select Attendance: ");
        decision = validator.safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = validator.safeIntInput();
            }
        }


        Map<String, String> rosterList = attendanceSystem.rosterLists();
        List<String> rosterUIDList = new ArrayList<>(attendanceSystem.rosterLists().keySet());
        System.out.println();
        System.out.println();

        i = 0;

        for (Map.Entry<String, String> student : rosterList.entrySet()) {
            System.out.println(++i + ". " + student.getValue() + "\t " + student.getKey() + " " + attendanceSystem.isPresent(student.getKey(), date) );
        }



        String studentChosen;
        System.out.println();
        System.out.print("Select student to toggle (select number above to exit): ");


        decision = validator.safeIntInput();

        while (true) {
            try {
                if (rosterUIDList.size() < decision) return;
                studentChosen = rosterUIDList.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = validator.safeIntInput();
            }
        }
        attendanceSystem.toggleAttendance(studentChosen, date);

    }



    public void attendanceMode() {
        System.out.println();

        List<String> attendanceLists = attendanceSystem.attendanceDateLists();
        int i = 0, decision;


        System.out.println();
        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return;
        }



        for (String attendanceDate : attendanceLists) {
            System.out.println(++i + ". " + attendanceDate);
        }

        System.out.println();
        System.out.print("Select Attendance: ");
        decision = validator.safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = validator.safeIntInput();
            }
        }

        AttendanceSheet sheet = attendanceSystem.queryAttendance(date);
        System.out.println();
        System.out.println("Attendance Mode : " + date);


        int parsedUid;
        String line = "";
        while (true) {
            try {
                System.out.println("Enter UID (or q to exit):");
                line = input.nextLine();
                parsedUid = ParseUtility.parseUID(line);
                sheet.markPresent(parsedUid);
            } catch (RuntimeException e) {
                if (line.equals("q")) return;

                System.out.print("Enter valid uid: ");
                line = input.nextLine();
            }
        }

    }
}
