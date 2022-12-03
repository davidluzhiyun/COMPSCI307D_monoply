package ooga.event.eventRunnable;

import ooga.controller.InitBoardRecord;
import ooga.controller.ParsedProperty;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.event.GameEventType;
import ooga.event.command.BoardSetUpCommand;
import ooga.event.command.Command;
import ooga.model.ModelOutput;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.ControllerPlace;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the logic/functions that need to occur when the Controller sends the board set up information to the view
 */
public class BoardSetUpRunnable implements EventGenerator{

    private ModelOutput boardInfo;

    public static final String PLACE_PATH = "ooga/model/place/";

    public static final String JSON_EXTENSION = ".json";

    private String typeRegex = ".+\"type\": \"(\\w+)\".?";

    private String nameRegex = ".+\"name\": \"(.+)\".?";

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
            try {
                parsedProperties.add(new ParsedProperty(getPlaceType(place), getPlaceName(place), place.getColorSetId()));
            } catch (NoColorAttributeException e) {
                parsedProperties.add(new ParsedProperty(getPlaceType(place), getPlaceName(place), -1));
            }
        }
        return parsedProperties;
    }

    private String getPlaceName(ControllerPlace place) {
        return getString(place, nameRegex);
    }

    private String getString(ControllerPlace place, String regex) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;

        try {
            File file = getFileFromResource(fileName);
            BufferedReader fr = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fr.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String type = matcher.group(1);
//                    System.out.println(type);
                    return type;
                }
            }
        } catch (URISyntaxException | IOException e) {
            System.out.println("Unable to get file"); //TODO: maybe make errors a popup?
        }
        return null;
    }

    private String getPlaceType(ControllerPlace place) {
        return getString(place, typeRegex);
    }

    /**
     * Returns the file when searching for the string of a filename
     * @param fileName
     * @return File
     * @throws URISyntaxException
     */
    //https://mkyong.com/java/java-read-a-file-from-resources-folder/#:~:text=In%20Java%2C%20we%20can%20use,getClassLoader().
    public static File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = BoardSetUpRunnable.class.getClassLoader();
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
//    private static void printFile(File file) {
//
//        List<String> lines;
//        try {
//            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
