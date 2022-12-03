package ooga.event.eventRunnable;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import ooga.event.GameEvent;

import java.io.*;
import java.util.Map;

public class ParsingJsonRunnable implements EventGenerator{

    @Override
    public GameEvent processEvent() {
        return null;
    }

    protected Map<String, LinkedTreeMap> parseJSON(File file) {
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
}
