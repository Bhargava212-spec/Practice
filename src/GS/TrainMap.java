package GS;

import java.util.*;

public class TrainMap {

    static class Station {
        private final String name;
        private List<Station> neighbours;

        public Station(String name) {
            this.name = name;
            this.neighbours = new ArrayList<>(3);
        }

        public String getName() {
            return name;
        }

        public void addNeighbours(Station station) {
            this.neighbours.add(station);
        }

        public List<Station> getNeighbours() {
            return neighbours;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Station station = (Station) o;
            return Objects.equals(name, station.name) && Objects.equals(neighbours, station.neighbours);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, neighbours);
        }
    }

    private HashMap<String, Station> stations;

    public TrainMap() {
        this.stations = new HashMap<>();
    }

    public TrainMap addStation(String name) {
        Station station = new Station(name);
        this.stations.putIfAbsent(name, station);
        return this;
    }

    public Station getStation(String name) {
        return this.stations.get(name);
    }

    public TrainMap connectStations(Station fromStation, Station toStation) {
        if (fromStation == null || toStation == null) {
            throw new RuntimeException("station is null");
        }
        fromStation.addNeighbours(toStation);
        toStation.addNeighbours(fromStation);
        return this;
    }

    public List<Station> shortestPath(String from, String to) {
        Station start = stations.get(from);
        Station end = stations.get(to);

        Map<Station, Station> precedessorMap = new HashMap<>();
        Queue<Station> que = new LinkedList<>();
        que.offer(start);
        precedessorMap.put(start, null);

        while (!que.isEmpty()) {
            Station curr = que.poll();
            if (curr == end) {
                break;
            }
            for (Station st : curr.getNeighbours()) {
                if (!precedessorMap.containsKey(st)) {
                    precedessorMap.put(st, curr);
                    que.offer(st);
                }
            }
        }

        List<Station> res = new ArrayList<>();
        while (end != null) {
            res.add(end);
            end = precedessorMap.get(end);
        }
        Collections.reverse(res);
        return res;
    }

}
