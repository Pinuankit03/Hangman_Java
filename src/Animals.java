import java.util.ArrayList;

public class Animals extends WordBank {


    private String[] animalNames = {"RABBIT", "FLYING FISH", "LION", "DEER", "FISH","COW"};

    public Animals() {

    }

    @Override
    public String[] wordbank() {
        return animalNames;
    }
}
