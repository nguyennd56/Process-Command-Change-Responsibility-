package commandpattern;

/**
 * Created by Nguyen D. Ngo on 4/25/14.
 */
public class Request {
    private int moneyType;
    private int type;
    private String description;

    public Request(int type, int moneyType, String description) {
        this.moneyType = moneyType;
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    public int getMoneyType(){
        return moneyType;
    }
    public String toString(){
        String t;
        if(type==Constant.SELL) t = "SELL";
        else if(type == Constant.BUY) t = "BUY";
        else t = "UNKNOWN";

        return String.format("Type: %s\nMoney Type: %d\nDescription: %s", t, moneyType, description);
    }
}
