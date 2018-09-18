package instaclone.mahmoud.com.collegeapptest5.models;

import instaclone.mahmoud.com.collegeapptest5.TimetableListObject;

public class TimetableSlotDetails {

    private static final String TAG = "TimetableSlotDetails";

    private String module_name;
    private String module_room;
    private String module_time;

    public TimetableSlotDetails(String module_name, String module_room, String module_time) {
        this.module_name = module_name;
        this.module_room = module_room;
        this.module_time = module_time;
    }

    public TimetableSlotDetails(){

    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getModule_room() {
        return module_room;
    }

    public void setModule_room(String module_room) {
        this.module_room = module_room;
    }

    public String getModule_time() {
        return module_time;
    }

    public void setModule_time(String module_time) {
        this.module_time = module_time;
    }

    @Override
    public String toString() {
        return "TimetableSlotDetails{" +
                "module_name='" + module_name + '\'' +
                ", module_room='" + module_room + '\'' +
                ", module_time='" + module_time + '\'' +
                '}';
    }
}
