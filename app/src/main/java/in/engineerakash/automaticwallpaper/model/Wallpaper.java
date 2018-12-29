package in.engineerakash.automaticwallpaper.model;

import java.sql.Time;

public class Wallpaper {

    private int id;
    private String wPName;
    private String wPDes;
    private String filePath;
    private Time timeWhenWpWillSet;
    private boolean isCurrentWp;


    public Wallpaper(int id, String wPName, String wPDes, String filePath, Time timeWhenWpWillSet, boolean isCurrentWp) {
        this.id = id;
        this.wPName = wPName;
        this.wPDes = wPDes;
        this.filePath = filePath;
        this.timeWhenWpWillSet = timeWhenWpWillSet;
        this.isCurrentWp = isCurrentWp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getwPName() {
        return wPName;
    }

    public void setwPName(String wPName) {
        this.wPName = wPName;
    }

    public String getwPDes() {
        return wPDes;
    }

    public void setwPDes(String wPDes) {
        this.wPDes = wPDes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Time getTimeWhenWpWillSet() {
        return timeWhenWpWillSet;
    }

    public void setTimeWhenWpWillSet(Time timeWhenWpWillSet) {
        this.timeWhenWpWillSet = timeWhenWpWillSet;
    }

    public boolean isCurrentWp() {
        return isCurrentWp;
    }

    public void setCurrentWp(boolean currentWp) {
        isCurrentWp = currentWp;
    }
}
