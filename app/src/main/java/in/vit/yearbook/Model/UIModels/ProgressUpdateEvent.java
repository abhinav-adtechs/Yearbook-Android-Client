package in.vit.yearbook.Model.UIModels;


public class ProgressUpdateEvent {

    private int year ;
    private int progress ;

    public ProgressUpdateEvent(int year, int progress) {
        this.year = year;
        this.progress = progress;
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
}
