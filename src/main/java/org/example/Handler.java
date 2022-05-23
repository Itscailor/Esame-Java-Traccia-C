package org.example;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;

class MyHandler implements HttpHandler {
    static final ArrayList<Piatti> all=App.getPiatti();
    static final Gson gsonAll=new Gson();
    static final String gsonAllJson=gsonAll.toJson(all);
    public void handle(HttpExchange t) throws IOException {
        URI uri = t.getRequestURI();
        String query = uri.getQuery();
        if(query!=null) {
            runner(query, t);
        }
        else{
            printResponse("You have not sent nothing", 400, t);
        }
    }
    public void runner(String query, HttpExchange t) throws IOException {
        String[] param;
        param = query.split("&");
        if(param.length==1) {
            String[] value = param[0].split("=");
            if (value[0].equals("cmd")) {
                switch (value[1]) {
                    case "all":
                        printResponse(printJson("all"), 200, t);
                        break;
                    case "cheaper":
                        printResponse(printJson("more_expensive"), 200, t);
                        break;
                    case "all_sorted":
                        printResponse(printJson("all_sorted"), 200, t);
                        break;
                    default:
                        printResponse("filter not supported", 400, t);
                        break;
                }
            }
            else {
                printResponse("wrong syntax", 400, t);
            }
        }
        else
            printResponse("Too much parameters", 400, t);
    }

    public void printResponse(String text, int errorCode, HttpExchange t) throws IOException {
        String response = printHTML(text);
        t.sendResponseHeaders(errorCode, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    public String printHTML(String text){
        return text;
    }

    public String printJson(String action){
        Gson gson = new Gson();
        switch (action) {
            case "all":
                return gsonAllJson;
            case "all_sorted":
                return gson.toJson(sort());
            case "more_expensive":
                return gson.toJson(sort().get(0));
            default:
                return "no action";
        }
    }
    public ArrayList<Piatti> sort(){
        ArrayList<Piatti> p = App.getPiatti();
        p.sort((Piatti primo, Piatti secondo) -> (int) (primo.price - secondo.price));
        return p;
    }
}
