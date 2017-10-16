package in.vit.yearbook.Model.UIModels;


public class ProgressUpdateEvent {

    private int year ;
    private int progress ;
    private String statusString ;

    public ProgressUpdateEvent(int year, int progress) {
        this.year = year;
        this.progress = progress;
        this.statusString = "idle" ;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }
}
