package ch.bbw;

import java.util.*;

/**
 * @author Peter S.
 */
public class App {
    public static void main(String[] args) {
        Inventarisierung i = new Inventarisierung(1, 1, 1, 3, 99999);
        set_inventarisierung_data(i);
        
        System.out.println(i.console_input_to_code_string());
    }

    public static void set_inventarisierung_data(Inventarisierung i) {
        String[][] groupkey_groupname = {
                {"1", "Computer"},
                {"2", "Peripherals"},
                {"3", "Tools"},
                {"4", "Parts"}
        };
        String[][] groupkey_objectkey_objectname = {
                {"1", "1", "PC"},
                {"1", "2", "Laptop"},
                {"2", "1", "Printer"},
                {"2", "2", "Monitor"},
                {"2", "3", "Keyboard"},
                {"2", "4", "Mouse"},
                {"2", "5", "Headphones"},
                {"2", "6", "Speakers"},
                {"2", "7", "Camera"},
                {"3", "1", "Screwdriver"},
                {"3", "2", "Drill"},
                {"3", "3", "Wrench"},
                {"3", "4", "Pliers"},
                {"4", "1", "CPU"},
                {"4", "2", "RAM"},
                {"4", "3", "GPU"},
                {"4", "4", "MB"},
                {"4", "5", "PSU"},
                {"4", "6", "Printer Paint"}
        };
        String[][] areakey_areaname = {
                {"1", "Sales"},
                {"2", "Repair"},
                {"3", "Warehouse"},
        };
        int[][] areakey_floormax_roommax = {
                {1, 1, 25},
                {2, 4, 40},
                {3, 6, 10}
        };
        //Fill groups map
        Arrays.stream(groupkey_groupname).toList().forEach(n -> {
            i.set_group(Integer.parseInt(n[0]), n[1]);
        });
        //Fill group objects map
        Arrays.stream(groupkey_objectkey_objectname).toList().forEach(n -> {
            i.set_group_object(Integer.parseInt(n[0]), Integer.parseInt(n[1]), n[2]);
        });
        //Fill areas map
        Arrays.stream(areakey_areaname).toList().forEach(n -> {
            i.set_area(Integer.parseInt(n[0]), n[1]);
        });
        //Fill area room map
        Arrays.stream(areakey_floormax_roommax).toList().forEach(n -> {
            List<Integer> roomlist = new ArrayList<>();

            for (int f = 0; f <= n[1]; f++) {
                for (int r = 1; r <= n[2]; r++) {
                    roomlist.add(f * 100 + r);
                }
            }
            i.set_area_room(n[0], roomlist);
        });
    }
}
