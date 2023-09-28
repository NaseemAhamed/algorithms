import java.util.*;

class Pair {
    String source;
    String destination;

    Pair(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }
}

public class ConstructItinerary {
    public List<String> constructItinerary(List<Pair> tickets, String startSource) {
        List<String> itinerary = new ArrayList<>();

        Map<String, List<String>> graph = new HashMap<>();
        for (Pair pair : tickets) {
            graph.computeIfAbsent(pair.source, node -> new ArrayList<>()).add(pair.destination);
        }

        for(List<String> destinations:graph.values()){
            Collections.sort(destinations);
        }

        dfs(graph, startSource, itinerary);

        return itinerary;
    }

    public void dfs(Map<String, List<String>> graph, String airport, List<String> itinerary) {
        List<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            String nextSourceToExplore = destinations.remove(0);
            dfs(graph, nextSourceToExplore, itinerary);
        }
        itinerary.add(0,airport);
    }

    public static void main(String[] args) {

        ConstructItinerary constructItinerary = new ConstructItinerary();
        List<Pair> tickets = new ArrayList<>();
        tickets.add(new Pair("JFK", "SFO"));
        tickets.add(new Pair("JFK", "ATL"));
        tickets.add(new Pair("SFO", "ATL"));
        tickets.add(new Pair("ATL", "JFK"));
        tickets.add(new Pair("ATL", "SFO"));

        List<String> result = constructItinerary.constructItinerary(tickets, "JFK");
        for (String element : result) {
            System.out.print(element + " ");
        }
    }
}
