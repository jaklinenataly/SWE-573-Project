package com.vakses.model.data;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by veraxmedax on 08/03/2018.
 */
@Component
public class Hospitals {
    private static final Map<String, String> names;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("ÇUKUROVA ÜNİVERSİTESİ TIP FAKÜLTESİ", "ADANA");

        map.put("HACETTEPE HASTANESİ", "ANKARA");
        map.put("Hacettepe ÜNİVERSİTESİ TIP FAKÜLTESİ", "ANKARA");
        map.put("BEŞEVLER GAZİ HASTANESİ", "ANKARA");
        map.put("SIHHIYE NUMUNE HASTANESİ", "ANKARA");
        map.put("SÖĞÜTÖZÜ MEDİCANA HASTANESİ", "ANKARA");
        map.put("SİNCAN LOKMAN HEKİM HASTANESİ", "ANKARA");

        map.put("ULUDAĞ TIP FAKÜLTESİ", "BURSA");

        map.put("MEHMET AKİF Hastanesi", "İSTANBUL");
        map.put("ÜSKÜDAR ÇAMLICA HASTANESİ", "İSTANBUL");
        map.put("ÇAPA TIP FAKÜLTESİ", "İSTANBUL");
        map.put("SIYAMI ERSEK", "İSTANBUL");
        map.put("SİYAMİ ERSEK", "İSTANBUL");
        map.put("GÖZTEPE EĞİTİM ARAŞTIRMA HASTANESİ", "İSTANBUL");

        map.put("DOKUZ EYLÜL", "İZMİR");

        map.put("OMÜ TIP FAKÜLTESİ", "SAMSUN");
        map.put("ON DOKUZ MAYIS ÜNİVERSİTESİ TIP FAKÜLTESİ", "SAMSUN");

        map.put("MEDİCAL PARK KARADENİZ HASTANESİ", "TRABZON");

        names = Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getNames() {
        return names;
    }
}
