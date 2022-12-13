package ooga.event.eventRunnable;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import ooga.event.GameEvent;
import ooga.model.place.ControllerPlace;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingJsonRunnable implements EventGenerator{

    public static final String PLACE_PATH = "ooga/model/place/";

    public static final String JSON_EXTENSION = ".json";

    public static final String typeRegex = ".+\"type\": \"(\\w+)\".?";

    public static final String nameRegex = ".+\"name\": \"(.+)\".?";

    public static final String colorRegex = ".+\"colorId\": (\\d+).?";

    public static final String imageRegex = ".+\"image\": (.+).?";

    public static final String upperTextRegex = ".+\"upperText\": \"(.+)\".?";

    public static final String lowerTextRegex = ".+\"lowerText\": \"(.+)\".?";

    public static final String idRegex = ".+\"id\": \"(.+)\".?";

    @Override
    public GameEvent processEvent() {
        return null;
    }

    protected static Map<String, LinkedTreeMap> parseJSON(File file) {
        try (Reader reader = new FileReader(file)) {
            // Convert JSON File to Java Object
            return new Gson().fromJson(reader, Map.class);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found1");
        } catch (IOException e) {
            System.out.println("IOException thrown1");
        }
        return null;
    }

    protected static File getFileFromResource(String fileName) throws URISyntaxException {

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

    protected static String getPlaceName(ControllerPlace place) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
        return getString(fileName, nameRegex);
    }

    protected static String getString(String fileName, String regex) {
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

    protected static String getPlaceType(ControllerPlace place) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
        return getString(fileName, typeRegex);
    }

//    protected static String getImage(ControllerPlace place) {
//        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
//        return getString(fileName, imageRegex);
//    }

    protected static String getId(ControllerPlace place) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
        return getString(fileName, idRegex);
    }

    protected static String getLowerText(ControllerPlace place) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
        return getString(fileName, lowerTextRegex);
    }

    protected static String getUpperText(ControllerPlace place) {
        String fileName = PLACE_PATH + place.getPlaceId() + JSON_EXTENSION;
        return getString(fileName, upperTextRegex);
    }
}
