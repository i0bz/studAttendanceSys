package entity;

import repository.AttendanceRegistry;
import repository.StudentRoster;

import java.io.Serializable;
import java.time.*;
import java.util.*;


public class AttendanceSheet implements Serializable,Comparable<AttendanceSheet> {
    private final LocalDate date;
    private final Set<Student> attendanceRoster;
    private transient StudentRoster studentRoster;


    //Comparing Functions
    @Override
    public int compareTo(AttendanceSheet other) {
        return this.date.compareTo(other.date);
    }

    //Hashing Functions
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AttendanceSheet other)) return false;
        return date == other.date;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }




    /**
     * Instantiates a new Attendance sheet.
     *
     * @param date          the date
     * @param studentRoster the student roster
     */
    public AttendanceSheet(LocalDate date, StudentRoster studentRoster) {
        this.date = date;
        this.attendanceRoster = new HashSet<>();
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
        attendanceRoster.add(student);
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
    public Set<Student> attendanceStudentsSet() {
        return attendanceRoster;
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
