package com.vakses.model.data;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Component
public class BloodGroup {
    private static final Set<String> types;

    static {
        Set<String> set = new HashSet<>();
        set.add("A RH -");
        set.add("A RH +");
        set.add("B RH -");
        set.add("B RH +");
        set.add("AB RH -");
        set.add("AB RH +");
        set.add("0 RH -");
        set.add("0 RH +");

        set.add("A RH (-)");
        set.add("A RH (+)");
        set.add("B RH (-)");
        set.add("B RH (+)");
        set.add("AB RH (-)");
        set.add("AB RH (+)");
        set.add("0 RH (-)");
        set.add("0 RH (+)");

        types = Collections.unmodifiableSet(set);
    }

    public static Set<String> getTypes() {
        return types;
    }
}
