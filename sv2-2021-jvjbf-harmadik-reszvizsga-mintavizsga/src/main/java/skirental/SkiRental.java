package skirental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SkiRental {

    private Map<String, Equipment> rentals = new TreeMap<>();

    public void loadFromFile(Path path) {
        List<String> redFile = loadFile(path);
        for (int i = 1; i < redFile.size(); i++) {
            String[] readFileSplit = redFile.get(i).split(";");
            String[] readFileSplitSplit = readFileSplit[1].split(" ");
            String name = readFileSplit[0];
            int sizeOfSkis = Integer.parseInt(readFileSplitSplit[0]);
            int sizeOfBoot = Integer.parseInt(readFileSplitSplit[1]);
            rentals.put(name, new Equipment(sizeOfSkis, sizeOfBoot));
        }
    }

    public List<String> loadFile(Path path) {
        try {
            return Files.readAllLines(path);
        }
        catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + ioe.getMessage());
        }
    }

    public List<String> listChildren() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Equipment> me: rentals.entrySet()) {
            Equipment e = me.getValue();
            if (e.getSizeOfBoot() <= 37 && e.getSizeOfSkis() <= 120) {
                result.add(me.getKey());
            }
        }
        return result;
    }

    public String getNameOfPeopleWithBiggestFoot() {
        int max = 0;
        String name = null;
        for (Map.Entry<String, Equipment> me: rentals.entrySet()) {
            Equipment e = me.getValue();
            if (e.getSizeOfSkis() !=0) {
                if (e.getSizeOfBoot() > max) {
                    max = e.getSizeOfBoot();
                    name = me.getKey();
                }
            }
        }
        return name;
    }

    public Map<String, Equipment> getRentals() {
        return rentals;
    }
}
