package ch.bbw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventarisierung {
    private Map<Integer, String> group;
    private Map<Integer, Map<Integer, String>> group_object;
    private Map<Integer, String> area;
    private Map<Integer, List<Integer>> area_room;
    private int uid_min;
    private int uid_max;

    public Inventarisierung(int uid_min, int uid_max) {
        this.group = new HashMap<>();
        this.group_object = new HashMap<>();
        this.area = new HashMap<>();
        this.area_room = new HashMap<>();
        this.uid_min = uid_min;
        this.uid_max = uid_max;
    }
}
