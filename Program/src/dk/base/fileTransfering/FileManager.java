package dk.base.fileTransfering;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;
import dk.base.Card;
import dk.base.DayDisplay;
import dk.base.fileTransfering.JSON;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/** TODO WHEN WE LOAD FROM PASTEBIN, TAKE THE MOST RECENT ONE. */
/** TODO: whitelist an IP to use the Pastebin API. */
/** TODO: Maybe use google drive? https://developers.google.com/drive/api/v3/quickstart/java*/

public class FileManager {

    private static final String DEV_KEY = "5d9b27419e24a576ff014e8c1c49738b";
    private static final String USER_NAME = "mktodoapp";
    private static final String USER_PASSWORD = "je7Zk8BK";
    private static final String PASTE_URL = "https://pastebin.com/KZXSGkp4";
    private static final String PASTE_URL_POSTFIX = "KZXSGkp4";
    private static final String PASTE_NAME = "mikkelkuntz";
    private static final String USER_KEY = "7867f43dfe587ff8759737b94b21974d";
    //Aquired from https://pastebin.com/api/api_user_key.html

    public static void saveToPastebin(HBox root){

        //Convert HBox with cards into string lines.
        ArrayList<String> cardLines = new ArrayList<>(JSON.createJSONstring(root));

        //Create one string containing all info
        StringBuilder stringBuilder = new StringBuilder();

        for(String string : cardLines)
            stringBuilder.append(string);

        //Paste the string to pastebin
        pasteToPastebin(stringBuilder.toString());
    }

    /** Pastes a string to pastebin.com*/
    private static void pasteToPastebin(String pasteString){
        PastebinFactory factory = new PastebinFactory();
        Pastebin pastebin = factory.createPastebin(DEV_KEY);

        //Get a pastebuilder to build the paste i want to publish
        final PasteBuilder pasteBuilder = factory.createPaste();

        pasteBuilder.setTitle(PASTE_NAME);
        pasteBuilder.setRaw(pasteString); //TODO This will be where my paste is going
        pasteBuilder.setMachineFriendlyLanguage("text");
        pasteBuilder.setVisiblity(PasteVisiblity.Public); //TODO Paste are not currently public?
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

    /** boolean String TODO : needed? */
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

        //Login to pastebin
        final Response<String> userLoginKeyResponse = pastebin.login(USER_NAME, USER_PASSWORD);

        if (userLoginKeyResponse.hasError()) {
            System.out.println("Could not login " + userLoginKeyResponse.getError());
            return;
        }

        final String userKey = userLoginKeyResponse.get();

        System.out.println(userKey); //TODO TMEP

        final Response<List<Paste>> pastesResponse = pastebin.getPastesOf(userKey); //The limit could be changed to one?
        //final Response<List<Paste>> pastesResponse = pastebin.getPastesOf(userKey, 5); //The limit could be changed to one?
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
