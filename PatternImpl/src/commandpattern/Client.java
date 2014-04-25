package commandpattern; /**
 * Created by Nguyen D. Ngo on 4/25/14.
 */

import java.util.Stack;

class Constant{
    public static final int USD = 0;
    public static final int EURO = 1;
    public static final int SELL = 0;
    public static final int BUY = 1;
}

// Handler class.
abstract class StockTrade {
    protected StockTrade successor;

    public void setSuccessor(StockTrade successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Request request);
}

class StockTradeBuy extends StockTrade{


    @Override
    public void handleRequest(Request request) {
        if(request.getType() == Constant.BUY)
        {
            System.out.println(request.toString());
            System.out.println("BUY request  have been handled!\n\n");
        }
        else
            successor.handleRequest(request);
    }
}

class StockTradeSell extends StockTrade{


    @Override
    public void handleRequest(Request request) {
        if(request.getType() == Constant.SELL)
        {
            System.out.println(request.toString());
            System.out.println("SELL request have been handled\n\n");
        }else
            successor.handleRequest(request);
    }
}
// final handler
class StockTradeDefault extends StockTrade{


    @Override
    public void handleRequest(Request request) {
        System.out.println(request.toString());
        System.out.println("I am the last one who can handle request for you but we don't serve this request\n\n");

    }
}

// Invoker.
class Agent {

    StockTradeDefault lastHandler = new StockTradeDefault();
    StockTradeBuy secondHandler = new StockTradeBuy();
    StockTradeSell firstHandler = new StockTradeSell();

    private Stack<Request> stack = new Stack<Request>();
    public Agent() {
        firstHandler.setSuccessor(secondHandler);
        secondHandler.setSuccessor(lastHandler);
    }

    void placeRequest(Request request) {
        stack.push(request);
        Request objRequest = stack.peek();
        stack.pop();
        firstHandler.handleRequest(objRequest);
    }
}


public class Client {
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.placeRequest(new Request(Constant.BUY, Constant.USD, "buy as usd"));
        agent.placeRequest(new Request(Constant.SELL, Constant.EURO, "sell as euro"));
        agent.placeRequest(new Request(Constant.SELL, Constant.USD, "sell as usd"));
        agent.placeRequest(new Request(Constant.BUY, Constant.EURO, "sell as euro"));
        agent.placeRequest(new Request(Constant.SELL, Constant.USD, "sell as usd"));
        agent.placeRequest(new Request(21, Constant.USD, "no description"));
        agent.placeRequest(new Request(Constant.SELL, Constant.USD, "sell as usd"));
    }
}