package entity;

import repository.StudentRoster;

import java.io.Serializable;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;


public class AttendanceSheet implements Serializable {
    private final LocalDate date;
    private final ArrayList<Student> attendanceRoster;
    private transient StudentRoster studentRoster;


    /**
     * Instantiates a new Attendance sheet.
     *
     * @param date          the date
     * @param studentRoster the student roster
     */
    public AttendanceSheet(LocalDate date, StudentRoster studentRoster) {
        this.date = date;
        this.attendanceRoster = new ArrayList<>();
        this.studentRoster = studentRoster;
    }

    public void setRoster(StudentRoster studentRoster) {
        this.studentRoster = studentRoster;
    }


    //Attendance Manipulation

    /**
     * Toggle attendance.
     *
     * @param studentUID the student uid
     * @throws NoSuchElementException If the student uid given does not exist in the roster.
     */
    public void toggleAttendance(int studentUID) {
        if (!studentRoster.studentExists(studentUID)) throw new NoSuchElementException("Student does not exist in the roster.");
        Student student = studentRoster.queryStudent(studentUID);
        if (!attendanceRoster.contains(student)) attendanceRoster.add(student);
        else attendanceRoster.remove(student);
    }


    public void markPresent(int studentUID) {
        if (!studentRoster.studentExists(studentUID)) return;
        Student student = studentRoster.queryStudent(studentUID);
        if (!attendanceRoster.contains(student)) attendanceRoster.add(student);
    }


    //Attendance Checkers
    /**
     * Check if student is present.
     *
     * @param studentUID the student uid
     * @return the boolean
     */
    public boolean isPresent(int studentUID) {
        return attendanceRoster.contains(studentRoster.queryStudent(studentUID));
    }


    //Query
    /**
     * Attendance students uid list in a sorted set.
     *
     * @return the sorted set
     */
    public SortedSet<Integer> attendanceStudentsUIDList() {
        return attendanceRoster.stream().map(Student::uid).collect(Collectors.toCollection(TreeSet::new));
    }
    /**
     * Attendance students name list sorted set.
     *
     * @return the sorted set
     */
    public List<String> attendanceStudentsNameList() {
        return attendanceRoster.stream().map(Student::name).sorted().collect(Collectors.toCollection(ArrayList::new));
    }



//Getter
    /**
     * Getter for date.
     *
     * @return the local date
     */
    public LocalDate date(){
        return this.date;
    }




}
