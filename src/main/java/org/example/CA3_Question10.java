package org.example;

import java.util.*;

public class CA3_Question10 {
    public static void main(String[] args) {
        HashMap<String, HashSet<DistanceTo>> connections = new HashMap<>(); //uses hashset since treeset doesn't allow duplicates which are needed I believe

        DistanceTo[] pendletonDistances = {
                new DistanceTo("Pierre", 2),
                new DistanceTo("Pueblo", 8),
                new DistanceTo("Phoenix", 4)
        };
        DistanceTo[] pierreDistances = {
                new DistanceTo("Pendleton", 2),
                new DistanceTo("Pueblo", 3)
        };
        DistanceTo[] puebloDistances = {
                new DistanceTo("Pierre", 3),
                new DistanceTo("Pendleton", 8),
                new DistanceTo("Phoenix", 3),
                new DistanceTo("Peoria", 3)
        };
        DistanceTo[] phoenixDistances = {
                new DistanceTo("Pueblo", 3),
                new DistanceTo("Pendleton", 4),
                new DistanceTo("Pensacola", 5),
                new DistanceTo("Peoria", 4),
                new DistanceTo("Pittsburgh", 10)
        };
        DistanceTo[] peoriaDistances = {
                new DistanceTo("Pueblo", 3),
                new DistanceTo("Phoenix", 4),
                new DistanceTo("Pittsburgh", 5)
        };
        DistanceTo[] pittsburghDistances = {
                new DistanceTo("Peoria", 5),
                new DistanceTo("Phoenix", 10),
                new DistanceTo("Pensacola", 4),
                new DistanceTo("Princeton", 2)
        };
        DistanceTo[] pensacolaDistances = {
                new DistanceTo("Pittsburgh", 4),
                new DistanceTo("Phoenix", 5),
                new DistanceTo("Princeton", 5)
        };
        DistanceTo[] princetonDistances = {
                new DistanceTo("Pittsburgh", 2),
                new DistanceTo("Pensacola", 5)
        };

        String[] cityNames = {"Pendleton", "Pierre", "Pueblo", "Phoenix", "Peoria", "Pittsburgh", "Pensacola", "Princeton"};
        DistanceTo[][] allDistance = {pensacolaDistances, pierreDistances, puebloDistances, phoenixDistances, peoriaDistances, pittsburghDistances, pensacolaDistances, princetonDistances};

        for (int i = 0; i < 8; i++) {
            HashSet<DistanceTo> dist = new HashSet<>();
            for (DistanceTo d : allDistance[i]) {
                dist.add(d);
            }
            connections.put(cityNames[i], dist);
        }

        // the above code creates the connections - not reading from a file since the file was not included in sample zip

        String startPoint = "Pendleton", endPoint = "Pierre";

        PriorityQueue<DistanceTo> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new DistanceTo(startPoint, 0));

        HashMap<String, Integer> shortestKnownDistance = new HashMap<>();

        while(priorityQueue.size()!=0){ //I literally did the pseudocode exactly why doesn't this work
            DistanceTo smallest = priorityQueue.poll();

            if(!shortestKnownDistance.containsKey(smallest.target)){
                int d = smallest.distance;
                shortestKnownDistance.put(smallest.target, d);
                for(DistanceTo c : connections.get(smallest.target)) {
                    priorityQueue.add(new DistanceTo(c.target, d + c.distance));
                }
            }
            System.out.println("=====");
        }

        System.out.println(shortestKnownDistance.get(endPoint));

    }
}

class DistanceTo implements Comparable<DistanceTo> {
    public String target;
    public int distance;
    public DistanceTo(String city, int dist) {
        target = city;
        distance = dist; }
    public int compareTo(DistanceTo other) {
        return distance - other.distance;
    }


}

