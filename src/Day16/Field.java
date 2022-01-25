package Day16;

public class Field {
    int firstIntervalStart;
    int firstIntervalEnd;
    int secondIntervalStart;
    int secondIntervalEnd;
    String description;

    public Field(String line){
        description = line.split(":")[0];
        String firstInterval = line.split(": ")[1].split(" or ")[0];
        String secondInterval = line.split(": ")[1].split(" or ")[1];
        firstIntervalStart = Integer.parseInt(firstInterval.split("-")[0]);
        firstIntervalEnd = Integer.parseInt(firstInterval.split("-")[1]);
        secondIntervalStart = Integer.parseInt(secondInterval.split("-")[0]);
        secondIntervalEnd = Integer.parseInt(secondInterval.split("-")[1]);
    }

    public boolean isValidValue(int value){
        if(firstIntervalStart <= value && firstIntervalEnd >= value){
            return true;
        }
        if(secondIntervalStart <= value && secondIntervalEnd >= value) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
