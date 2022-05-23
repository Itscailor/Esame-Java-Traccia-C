package org.example;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
public class App
{
    public static ArrayList<Piatti> piatti = new ArrayList<>();
    public static void main( String[] args ) {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(1234), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildPiattiList();
        assert server != null;
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }
    static void buildPiattiList() {
        piatti.add(new Piatti("Il classico risotto alla milanese", 1, "Risotto alla milanese", 15.50));
        piatti.add(new Piatti("La classica cotoletta alla milanese", 2, "Cotoletta alla milanese", 16.20));
        piatti.add(new Piatti("Frittura mista di pesce", 3, "Fritto misto", 17.00));
        piatti.add(new Piatti("Costata", 4, "Costata buonissima da mezzo kg", 20.00));
        piatti.add(new Piatti("Coda di rospo", 5, "è un pesce non un rospo", 22.00));
        piatti.add(new Piatti("Profitterol fresco fresco", 6, "Profitterol", 12.00));
    }

    public static ArrayList<Piatti> getPiatti() {
        return piatti;
    }
}