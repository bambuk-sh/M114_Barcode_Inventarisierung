package ch.bbw;

import java.util.*;
import java.util.stream.Collectors;

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

    public String console_input_code() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a Code: ");
        return s.next();
    }

    public int console_input_group() {
        Scanner s = new Scanner(System.in);
        int group_key = -1;

        System.out.println("Groups:");
        group.entrySet().forEach(System.out::println);
        while (!group.containsKey(group_key)) {
            System.out.print("Select a Group Key: ");
            group_key = s.nextInt();
            if (!group.containsKey(group_key)) System.out.println("Invalid Group Key.");
        }
        return group_key;
    }

    public int console_input_object(int group_key) {
        Scanner s = new Scanner(System.in);
        int object_key = -1;

        System.out.println("Objects:");
        group_object.get(group_key).entrySet().forEach(System.out::println);
        while (!group_object.get(group_key).containsKey(object_key)) {
            System.out.print("Select an Object Key: ");
            object_key = s.nextInt();
            if (!group_object.get(group_key).containsKey(object_key)) System.out.println("Invalid Object Key.");
        }
        return object_key;
    }

    public int console_input_area() {
        Scanner s = new Scanner(System.in);
        int area_key = -1;

        System.out.println("Areas:");
        area.entrySet().forEach(System.out::println);
        while (!area.containsKey(area_key)) {
            System.out.print("Select an Area Key: ");
            area_key = s.nextInt();
            if (!area.containsKey(area_key)) System.out.println("Invalid Area Key.");
        }
        return area_key;
    }

    public int console_input_floor(int area_key) {
        Scanner s = new Scanner(System.in);
        int floor = -1;

        System.out.println("Floors:");
        Set<Integer> floors = new HashSet<>();
        area_room.get(area_key).forEach(n -> {
            floors.add(n / 100);
        });

        int floor_min = floors.stream().min(Integer::compare).get();
        int floor_max = floors.stream().max(Integer::compare).get();
        System.out.println(floor_min + "-" + floor_max);

        while (!floors.contains(floor)) {
            System.out.print("Select a Floor: ");
            floor = s.nextInt();
            if (!floors.contains(floor)) System.out.println("Invalid Floor.");
        }
        return floor;
    }

    public int console_input_room(int area_key, int floor) {
        Scanner s = new Scanner(System.in);
        int room = -1;

        System.out.println("Rooms:");
        List<Integer> rooms = new ArrayList<>();
        area_room.get(area_key).forEach(n -> {
            if (n / 100 == floor) rooms.add(n);
        });

        int room_min = rooms.stream().min(Integer::compare).get();
        int room_max = rooms.stream().max(Integer::compare).get();
        System.out.println(room_min + "-" + room_max);

        while (!rooms.contains(room)) {
            System.out.print("Select a Room: ");
            room = s.nextInt();
            if (!rooms.contains(room)) System.out.println("Invalid Room.");
        }
        return room;
    }

    public int console_input_uid() {
        Scanner s = new Scanner(System.in);
        int uid = -1;

        System.out.println("Unique ID:");
        System.out.println("0-" + uid_max);

        while (!(uid >= 0) && (uid <= uid_max)) {
            System.out.print("Select an Unique ID: ");
            uid = s.nextInt();
            if (!((uid >= 0) && (uid <= uid_max))) System.out.println("Invalid UID.");
        }
        return uid;
    }

    public String console_input_to_code_string() {
        int group_key = console_input_group();
        // System.out.println(group_key);
        System.out.println("----------");

        int object_key = console_input_object(group_key);
        // System.out.println(object_key);
        System.out.println("----------");

        int area_key = console_input_area();
        // // System.out.println(area_key);
        System.out.println("----------");

        int floor = console_input_floor(area_key);
        // System.out.println(floor);
        System.out.println("----------");

        int room = console_input_room(area_key, floor);
        // System.out.println(room);
        System.out.println("----------");

        int uid = console_input_uid();
        // System.out.println(uid);
        System.out.println("----------");

        //Add leading zeroes to room in case of floor being 0
        String room_str = String.valueOf(room);
        while (room_str.length() < room_len) {
            room_str = "0" + room_str;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(group_key);
        sb.append(object_key);
        sb.append(area_key);
        sb.append(room_str);
        sb.append(uid);

        return sb.toString();
    }

    public String string_from_key_values(Map<String, Integer> keys_map) {
        String ret = "";

        ret += ("Group: " + group.get(keys_map.get("group_key")) + "\n");
        ret += ("Object: " + group_object.get(keys_map.get("group_key")).get(keys_map.get("object_key")) + "\n");
        ret += ("Area: " + area.get(keys_map.get("area_key")) + "\n");
        ret += ("Floor: " + String.valueOf(keys_map.get("room")).charAt(0) + "\n");
        ret += ("Room: " + keys_map.get("room") + "\n");
        ret += ("UID: " + keys_map.get("uid") + "\n");
        return ret;
    }

    public Map<String, Boolean> validate_keys(Map<String, Integer> keys_map) {
        Map<String, Boolean> ret = new HashMap<>();
        boolean group_key = false;
        boolean object_key = false;
        boolean area_key = false;
        boolean room = false;
        boolean uid = false;

        group_key = group.containsKey(keys_map.get("group_key"));
        if (group_key) {
            object_key = group_object.get(keys_map.get("group_key")).containsKey(keys_map.get("object_key"));
        }
        area_key = area.containsKey(keys_map.get("area_key"));
        if (area_key) {
            room = area_room.get(keys_map.get("area_key")).contains(keys_map.get("room"));
        }
        uid = (keys_map.get("uid") >= 0) && (keys_map.get("uid") <= uid_max);

        ret.put("group_key", group_key);
        ret.put("object_key", object_key);
        ret.put("area_key", area_key);
        ret.put("room", room);
        ret.put("uid", uid);
        return ret;
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
