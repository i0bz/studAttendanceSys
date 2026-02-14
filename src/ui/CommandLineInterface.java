package ui;

import controllers.*;
import entity.AttendanceSheet;
import utility.ParseUtility;

import java.time.format.DateTimeParseException;
import java.util.*;


public class CommandLineInterface {


    private final AttendanceSystemController attendanceSystem;
    private final Scanner input = new Scanner(System.in);


    public CommandLineInterface(AttendanceSystemController controller) {
        this.attendanceSystem = controller;
    }


    /*
    CLI control flows
     */
    public void managementUI() {
        int decision;

        while (true) {

            System.out.println();
            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Attendance System");
            System.out.println("4. Attendance Mode");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 6) {
                System.out.print("Enter 1, 2, 3, 4, and 5 only: ");
                decision = safeIntInput();
            }


            switch (decision) {
                case 1:
                    studentManagement();
                    break;
                case 2:
                    attendanceManagementUI();
                    break;
                case 3:
                    attendanceSystemUI();
                    break;
                case 4:
                    attendanceModeUI();
                    break;
                case 5:
                    return;
            }
        }

    }
    private void studentManagement() {
        System.out.println();
        System.out.println();

        int decision = 0;


        while (decision != 4) {
            System.out.println("1. Enroll Student");
            System.out.println("2. Drop Student");
            System.out.println("3. List Roster");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = safeIntInput();
            }


            switch (decision) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    dropStudent();
                    break;
                case 3:
                    listStudentNames();
                    break;
            }
        }


    }
    private void attendanceManagementUI() {
        System.out.println();
        System.out.println();
        int decision = 0;



        while (decision != 4) {
            System.out.println("1. Create Attendance");
            System.out.println("2. Delete Attendance");
            System.out.println("3. List Attendances");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = safeIntInput();
            }


            switch (decision) {
                case 1:
                    createAttendance();
                    break;
                case 2:
                    deleteAttendance();
                    break;
                case 3:
                    listAttendances();
                    break;
            }
        }

    }



    /*
    Student Management CLI functionalities
     */
    private void addStudent() {
        System.out.println();

        System.out.print("Enter name: ");
        String name = input.nextLine();
        if (name.equals("q")) return;
        System.out.print("Enter valid uid: ");
        String uid = input.nextLine();

        while (true) {
            try {
                if (uid.equals("q")) return;
                attendanceSystem.enrollStudent(name, uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.print("Reenter: ");
                uid = input.nextLine();
            }
        }


    }
    private void dropStudent() {
        System.out.println();

        System.out.print("Enter valid uid: ");
        String uid = input.nextLine();

        while (true) {
            try {
                if (uid.equals("q")) return;
                attendanceSystem.dropStudent(uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.print("Reenter: ");
                uid = input.nextLine();
            }
        }


    }
    private void listStudentNames() {

        System.out.println();
        System.out.println();
        Map<String, String> roster = attendanceSystem.rosterLists();
        int i = 0;
        for (Map.Entry<String, String> student : roster.entrySet()) {
            System.out.printf("%d. %s\t%s\n", ++i, student.getValue(), student.getKey());
        }
        System.out.println();
        System.out.println();

    }


    /*
    Attendance Management CLI functionalities
     */
    private void createAttendance() {
        System.out.println();
        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                attendanceSystem.addAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = input.nextLine();
            }
        }


    }
    private void deleteAttendance() {
        System.out.println();

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = input.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                attendanceSystem.removeAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = input.nextLine();
            }
        }


    }
    private void listAttendances() {
        System.out.println();
        System.out.println();
        List<String> attendances = attendanceSystem.attendanceDateLists();
        int i = 0;

        for (String attendance : attendances) {
            System.out.println(++i + ". " + attendance);
        }

        System.out.println();
    }



    private void attendanceSystemUI() {
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
        decision = safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = safeIntInput();
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


        decision = safeIntInput();

        while (true) {
            try {
                if (rosterUIDList.size() < decision) return;
                studentChosen = rosterUIDList.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = safeIntInput();
            }
        }
        attendanceSystem.toggleAttendance(studentChosen, date);

    }



    public void attendanceModeUI() {
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
        decision = safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = safeIntInput();
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





    private int safeIntInput() {
        int tmp;
        try {
            tmp = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            return -1;
        }
        return tmp;
    }
}
