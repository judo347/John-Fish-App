package dk.base.fileTransfering;

public class JSONcard {

    private String ownedByDisplay;
    private String text;
    private boolean isChecked;

    public JSONcard(String ownedByDisplay, String text, boolean isChecked) {
        this.ownedByDisplay = ownedByDisplay;
        this.text = text;
        this.isChecked = isChecked;
    }

    public String getOwnedByDisplay() {
        return ownedByDisplay;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
