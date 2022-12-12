package ooga.model.gamearchive;

import com.google.gson.internal.LinkedTreeMap;
import ooga.model.exception.MonopolyException;

import java.util.Map;

public class InitialConfigLoader {
  Map<String, LinkedTreeMap> initialConfig;

  public InitialConfigLoader(Map<String, LinkedTreeMap> initialConfig) {
    this.initialConfig = initialConfig;
  }

  public void check() throws MonopolyException {
    checkIsJailIndexValid();
  }

  private void checkIsJailIndexValid() throws MonopolyException {
    int j = 0;
    int jailIndex = (int) (double) initialConfig.get("meta").get("jail");
    while (initialConfig.containsKey(String.valueOf(j))) {
      if (initialConfig.get(String.valueOf(j)).get("type").equals("jail")) {
        if (jailIndex != j) //if the index of jail is inconsistent with what is in the metadata
          throw new MonopolyException("Bad data file");
        break;
      }
      j++;
    }
  }
}
