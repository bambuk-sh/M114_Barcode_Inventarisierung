package ch.bbw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventarisierung {
    private Map<Integer, String> group;
    private Map<Integer, Map<Integer, String>> group_object;
    private Map<Integer, String> area;
    private Map<Integer, List<Integer>> area_room;
    //Length of each key
    private int group_len;
    private int object_len;
    private int area_len;
    private int room_len;
    private int uid_min;
    private int uid_max;

    public Inventarisierung(int group_len, int object_len, int area_len, int room_len, int uid_min, int uid_max) {
        this.group = new HashMap<>();
        this.group_object = new HashMap<>();
        this.area = new HashMap<>();
        this.area_room = new HashMap<>();

        this.group_len = group_len;
        this.object_len = object_len;
        this.area_len = area_len;
        this.room_len = room_len;
        this.uid_min = uid_min;
        this.uid_max = uid_max;
    }

    public void set_group(int group_key, String group_name){
        this.group.put(group_key, group_name);
    }

    public void set_group_object(int group_key, int object_key, String object_name){
        this.group_object.get(group_key).put(object_key, object_name);
    }

    public void set_area(int area_key, String area_name){
        this.area.put(area_key, area_name);
    }

    public void set_area_room(int area_key, List<Integer> room_list){
        this.area_room.put(area_key, room_list);
    }
}
