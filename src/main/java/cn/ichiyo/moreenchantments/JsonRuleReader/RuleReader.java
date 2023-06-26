package cn.ichiyo.moreenchantments.JsonRuleReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;

public class RuleReader {
    public static void main(String[] args) {
        try {
            JsonParser parser = new JsonParser();

            JsonElement jsonElement = parser.parse(new FileReader("./src/main/resources/data/drop_rule/drop_rules.json"));

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray rulesArray = jsonObject.getAsJsonArray("drop_rules");

            for (JsonElement ruleElement : rulesArray) {
                JsonObject ruleObject = ruleElement.getAsJsonObject();

                String oreName = ruleObject.get("ore_name").getAsString();
                String dropItem = ruleObject.get("drop_item").getAsString();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
