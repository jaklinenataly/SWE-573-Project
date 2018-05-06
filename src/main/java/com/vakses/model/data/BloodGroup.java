package com.vakses.model.data;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Component
public class BloodGroup {
    private static final Map<String, String> types;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("A RH -", "A RH (-)");
        map.put("A RH +", "A RH (+)");
        map.put("B RH -", "B RH (-)");
        map.put("B RH +", "B RH (+)");
        map.put("AB RH -", "AB RH (-)");
        map.put("AB RH +", "AB RH (+)");
        map.put("0 RH -", "0 RH (-)");
        map.put("0 RH +", "0 RH (+)");

        map.put("A RH (-)", "A RH (-)");
        map.put("A RH (+)", "A RH (+)");
        map.put("B RH (-)", "B RH (-)");
        map.put("B RH (+)", "B RH (+)");
        map.put("AB RH (-)", "AB RH (-)");
        map.put("AB RH (+)", "AB RH (-)");
        map.put("0 RH (-)", "0 RH (-)");
        map.put("0 RH (+)", "0 RH (+)");

        map.put("A RH NEGATIF", "A RH (-)");
        map.put("A RH POZITIF", "A RH (+)");
        map.put("B RH NEGATIF", "B RH (-)");
        map.put("B RH POZITIF", "B RH (+)");
        map.put("AB RH NEGATIF", "AB RH (-)");
        map.put("AB RH POZITIF", "AB RH (-)");
        map.put("0 RH NEGATIF", "0 RH (-)");
        map.put("0 RH POZITIF", "0 RH (+)");

        types = Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getTypes() {
        return types;
    }
}
