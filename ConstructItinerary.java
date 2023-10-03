import java.util.*;

//Cover all or none.

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

        if(dfs(graph, startSource, itinerary, tickets.size())){
            return itinerary;
        }
        else{
            return new ArrayList<>();
        }
    }

    public boolean dfs(Map<String, List<String>> graph, String airport, List<String> itinerary, int totalTickets) {
        itinerary.add(airport);

        if(itinerary.size()==totalTickets+1) {
            return true;
        }

        List<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            String nextSourceToExplore = destinations.remove(0);
            if(dfs(graph, nextSourceToExplore, itinerary,totalTickets)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        ConstructItinerary constructItinerary = new ConstructItinerary();
        List<Pair> tickets = new ArrayList<>();
        tickets.add(new Pair("JFK", "SFO"));
        tickets.add(new Pair("SFO", "ATL"));
        tickets.add(new Pair("ATL", "LHR"));
        //tickets.add(new Pair("RAN", "DOM"));


        List<String> result = constructItinerary.constructItinerary(tickets, "JFK");
        for (String element : result) {
            System.out.print(element + " ");
        }
    }
}
