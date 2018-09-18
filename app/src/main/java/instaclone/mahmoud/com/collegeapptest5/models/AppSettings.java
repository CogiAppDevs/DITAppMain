package instaclone.mahmoud.com.collegeapptest5.models;

public class AppSettings {

    private TimetableSlotDetails timetableSlotDetails;

    public AppSettings(TimetableSlotDetails timetableSlotDetails)
    {
        this.timetableSlotDetails = timetableSlotDetails;
    }

    public AppSettings()
    {

    }

    public TimetableSlotDetails getTimetableSlotDetails() {
        return timetableSlotDetails;
    }

    public void setTimetableSlotDetails(TimetableSlotDetails timetableSlotDetails) {
        this.timetableSlotDetails = timetableSlotDetails;
    }

    @Override
    public String toString() {
        return "AppSettings{" +
                "timetableSlotDetails=" + timetableSlotDetails +
                '}';
    }
}
