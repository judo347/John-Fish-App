package dk.base;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/** TODO WHEN WE LOAD FROM PASTEBIN, TAKE THE MOST RECENT ONE. */

public class FileManager {

    private static final String DEV_KEY = "5d9b27419e24a576ff014e8c1c49738b";
    private static final String USER_NAME = "mkTodoApp";
    private static final String USER_PASSWORD = "je7Zk8BK";
    private static final String PASTE_URL = "https://pastebin.com/KZXSGkp4";
    private static final String PASTE_URL_POSTFIX = "KZXSGkp4";
    private static final String PASTE_NAME = "mikkelkuntz";
    private static final String USER_KEY = "4a591c3189c450be28f80fecb36fe09c";



    public static void saveToFile(HBox root){

        if(root.getChildren().size() != 5) //Check for correct number of elements
            throw new IllegalArgumentException();

        //TODO Is the order always this??

        //Get the displays
        DayDisplay old = (DayDisplay)root.getChildren().get(0);
        DayDisplay today = (DayDisplay)root.getChildren().get(1);
        DayDisplay tomorrow = (DayDisplay)root.getChildren().get(2);
        DayDisplay theDayAfter = (DayDisplay)root.getChildren().get(3);
        DayDisplay thoughts = (DayDisplay)root.getChildren().get(4);

        //Convert cards in displays to arraylists of strings
        ArrayList<String> oldLines = fromDayDisplayToStrings(old);
        ArrayList<String> todayLines = fromDayDisplayToStrings(today);
        ArrayList<String> tomorrowLines = fromDayDisplayToStrings(tomorrow);
        ArrayList<String> theDayAfterLines = fromDayDisplayToStrings(theDayAfter);
        ArrayList<String> thoughtsLines = fromDayDisplayToStrings(thoughts);



        //Create one string containing all info //TODO COULD AND SHOULD BE MADE TO JSON
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("OLD:");
        for(String string : oldLines){
            stringBuilder.append(string);
        }

        stringBuilder.append(";TODAY:");
        for(String string : todayLines){
            stringBuilder.append(string);
        }

        stringBuilder.append(";TOMORROW:");
        for(String string : tomorrowLines){
            stringBuilder.append(string);
        }

        stringBuilder.append(";THEDAYAFTER:");
        for(String string : theDayAfterLines){
            stringBuilder.append(string);
        }

        stringBuilder.append(";TOUGHTS:");
        for(String string : thoughtsLines){
            stringBuilder.append(string);
        }

        pasteToPastebin(stringBuilder.toString());
    }

    private static void pasteToPastebin(String pasteString){
        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin(DEV_KEY);

        //Get a pastebuilder to build the paste i want to publish
        final PasteBuilder pasteBuilder = factory.createPaste();

        pasteBuilder.setTitle(PASTE_NAME);
        pasteBuilder.setRaw(pasteString); //TODO This will be where my paste is going
        pasteBuilder.setMachineFriendlyLanguage("text");
        pasteBuilder.setVisiblity(PasteVisiblity.Unlisted);
        pasteBuilder.setExpire(PasteExpire.Never);

        // Create paste object
        final Paste paste = pasteBuilder.build();

        // ask to Pastebin to post the paste
        // the .post method: if the paste has been published will return the key assigned
        // by pastebin
        final Response<String> postResult = pastebin.post(paste);

        if (postResult.hasError()) {
            System.out.println("Something wrong: " + postResult.getError());
            return;
        }

        System.out.println("Paste published! Url: " + postResult.get());
    }

    /** boolean String */
    private static ArrayList<String> fromDayDisplayToStrings(DayDisplay dayDisplay){

        ArrayList<String> cardStrings = new ArrayList<>();

        for(Node node : dayDisplay.getChildren()){

            if(node instanceof Card){
                cardStrings.add(((Card) node).getCheckedStatus() + " " + ((Card) node).getText());
            }
        }

        return cardStrings;
    }


    /** TODO Should return an HBox*/
    public static void loadFromPasteBin(){

        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin(DEV_KEY);

        final Response<String> userLoginKeyResponse = pastebin.login(USER_NAME, USER_PASSWORD);

        if (userLoginKeyResponse.hasError()) {
            System.out.println("Could not login " + userLoginKeyResponse.getError());
            return;
        }

        final String userKey = userLoginKeyResponse.get();

        final Response<List<Paste>> pastesResponse = pastebin.getPastesOf(userKey, 5);
        //final Response<List<Paste>> pastesResponse = pastebin.getPastesOf(USER_KEY, 5);

        if (pastesResponse.hasError()) {
            System.out.println("Could not read pasts! " + pastesResponse.getError());
            return;
        }

        final List<Paste> pastes = pastesResponse.get();

        for (final Paste paste : pastes) {
            System.out.println(paste.getTitle());
            System.out.println("---");
        }
    }
}
