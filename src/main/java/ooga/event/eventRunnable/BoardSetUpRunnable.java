package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.place.ControllerPlace;
import ooga.model.place.Place;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up information to the view
 */
public class BoardSetUpRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    private String placePath = "ooga/model/place/";

    private String jsonExtension = ".json";

    private String typeRegex = " .+\"type\": \"(\\w+)\".?";

    /**@param arguments; should be a model interface from the model**/
    public BoardSetUpRunnable(Command arguments) {
        this.boardInfo = (ModelOutput) arguments.getCommandArgs();
    }

    @Override
    public GameEvent processEvent() {
        InitBoardRecord startInfo = new InitBoardRecord(getParsedProperty(), this.boardInfo.getStationaryAction(), this.boardInfo.getPlayers(), this.boardInfo.getCurrentPlayer());
        BoardSetUpCommand setUp = new BoardSetUpCommand(startInfo);
        return GameEventHandler.makeGameEventwithCommand(GameEventType.CONTROLLER_TO_VIEW_BOARD_SET_UP.name(), setUp);
    }

    private List<ParsedProperty> getParsedProperty() {
        List<ParsedProperty> parsedProperties = new ArrayList<>();
        for(ControllerPlace place : this.boardInfo.getBoard()) {
            parsedProperties.add(new ParsedProperty(getPlaceType(place), place.getColorSetId())); //TODO: change later
        }
        return parsedProperties;
    }

    private String getPlaceType(ControllerPlace place) {
        String fileName = placePath + place.getPlaceId() + jsonExtension;

        try {
            File file = getFileFromResource(fileName);
            BufferedReader fr = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fr.readLine()) != null) {
                Pattern pattern = Pattern.compile(typeRegex);
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String type = matcher.group(1);
//                    System.out.println(type);
                    return type;
                }
            }
            //printFile(file);
        } catch (URISyntaxException e) {
            System.out.println("Unable to get file");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to get file");
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
        return null;
    }

    //https://mkyong.com/java/java-read-a-file-from-resources-folder/#:~:text=In%20Java%2C%20we%20can%20use,getClassLoader().
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    /** Prints out the file read for testing purposes **/
    private static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
