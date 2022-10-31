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
    private int uid_max;

    public Inventarisierung(int group_len, int object_len, int area_len, int room_len, int uid_max) {
        this.group = new HashMap<>();
        this.group_object = new HashMap<>();
        this.area = new HashMap<>();
        this.area_room = new HashMap<>();

        this.group_len = group_len;
        this.object_len = object_len;
        this.area_len = area_len;
        this.room_len = room_len;
        this.uid_max = uid_max;
    }

    public void print_values_from_keys(Map<String, Integer> keys_map) {
        System.out.println("Group: " + group.get(keys_map.get("group_key")));
        System.out.println("Object: " + group_object.get(keys_map.get("group_key")).get(keys_map.get("object_key")));
        System.out.println("Area: " + area.get(keys_map.get("area_key")));
        System.out.println("Floor: " + String.valueOf(keys_map.get("room")).charAt(0));
        System.out.println("Room: " + keys_map.get("room"));
        System.out.println("UID: " + keys_map.get("uid"));
    }

    public Map<String, Integer> decode_string_to_keys(String code) {
        Map<String, Integer> ret = new HashMap<>();
        int codelen_no_uid = group_len + object_len + area_len + room_len;
        int codeint = 0;
        int group_key = 0;
        int object_key = 0;
        int area_key = 0;
        int room = 0;
        int uid = 0;

        //Get and remove uid
        // System.out.println("Code: " + code);
        int uid_len = code.length() - codelen_no_uid;
        uid = Integer.parseInt(code.substring(code.length() - uid_len));
        code = code.substring(0, code.length() - uid_len);
        // System.out.println("UID: " + uid);
        // System.out.println("Code without UID: " + code);

        //Get and remove room
        codeint = Integer.parseInt(code);
        room = codeint % (int) Math.pow(10, room_len);
        codeint /= (int) Math.pow(10, room_len);
        // System.out.println("Room: " + room);
        // System.out.println("Code without Room: " + codeint);

        //Get and remove area key
        area_key = codeint % (int) Math.pow(10, area_len);
        codeint /= (int) Math.pow(10, area_len);
        // System.out.println("Area: " + area_key);
        // System.out.println("Code without Area: " + codeint);

        //Get and remove object key
        object_key = codeint % (int) Math.pow(10, object_len);
        codeint /= (int) Math.pow(10, object_len);
        // System.out.println("Object: " + object_key);
        // System.out.println("Code without Object: " + codeint);

        //Get group key
        group_key = codeint % (int) Math.pow(10, group_len);
        // System.out.println("Group: " + group_key);

        ret.put("group_key", group_key);
        ret.put("object_key", object_key);
        ret.put("area_key", area_key);
        ret.put("room", room);
        ret.put("uid", uid);
        return ret;
    }

    public void set_group(int group_key, String group_name) {
        this.group.put(group_key, group_name);
        this.group_object.put(group_key, new HashMap<Integer, String>());
    }

    public void set_group_object(int group_key, int object_key, String object_name) {
        this.group_object.get(group_key).put(object_key, object_name);
    }

    public void set_area(int area_key, String area_name) {
        this.area.put(area_key, area_name);
    }

    public void set_area_room(int area_key, List<Integer> room_list) {
        this.area_room.put(area_key, room_list);
    }

    public void setGroup_len(int group_len) {
        this.group_len = group_len;
    }

    public void setObject_len(int object_len) {
        this.object_len = object_len;
    }

    public void setArea_len(int area_len) {
        this.area_len = area_len;
    }

    public void setRoom_len(int room_len) {
        this.room_len = room_len;
    }

    public void setUid_max(int uid_max) {
        this.uid_max = uid_max;
    }

    public Map<Integer, String> get_group() {
        return group;
    }

    public Map<Integer, Map<Integer, String>> get_group_object() {
        return group_object;
    }

    public Map<Integer, String> get_area() {
        return area;
    }

    public Map<Integer, List<Integer>> get_area_room() {
        return area_room;
    }

    public int getGroup_len() {
        return group_len;
    }

    public int getObject_len() {
        return object_len;
    }

    public int getArea_len() {
        return area_len;
    }

    public int getRoom_len() {
        return room_len;
    }

    public int getUid_max() {
        return uid_max;
    }
}
