package controllers;

import repository.AttendanceRegistry;
import repository.StudentRoster;
import services.StudentAttendanceService;
import services.StudentManagementService;
import utility.Persist;

public class AttendanceControllerFactory {
    StudentRoster roster;
    AttendanceRegistry registry;

    public  AttendanceControllerFactory() {
        this.roster = Persist.loadRoster();
        this.registry = Persist.loadRegistry(roster);
    }

    public AttendanceSystemController createController() {
        StudentAttendanceService attendanceService = new StudentAttendanceService(registry, roster);
        StudentManagementService managementService = new StudentManagementService(roster);
        return new AttendanceSystemController(managementService, attendanceService);
    }

    public StudentRoster roster() {
        return roster;
    }

    public AttendanceRegistry registry() {
        return registry;
    }
}
