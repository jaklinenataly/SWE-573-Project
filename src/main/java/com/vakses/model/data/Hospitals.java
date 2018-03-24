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
        map.put("ÇUKUROVA ÜNİVERSİTESİ", "ADANA");
        map.put("ÇUKUROVA ÜNIVERSITESI", "ADANA");

        map.put("HACETTEPE HASTANESİ", "ANKARA");
        map.put("HACETTEPE HASTANESI", "ANKARA");
        map.put("HACETTEPE ÜNİVERSİTESİ TIP FAKÜLTESİ", "ANKARA");
        map.put("HACETTEPE UNIVERSITESI TIP FAKULTESI", "ANKARA");
        map.put("BEŞEVLER GAZİ HASTANESİ", "ANKARA");
        map.put("SIHHIYE NUMUNE HASTANESİ", "ANKARA");
        map.put("SÖĞÜTÖZÜ MEDİCANA HASTANESİ", "ANKARA");
        map.put("SİNCAN LOKMAN HEKİM HASTANESİ", "ANKARA");
        map.put("UMUTTEPE EĞİTİM VE ARAŞTIRMA HASTANESİ", "ANKARA");

        map.put("ULUDAĞ FAKÜLTESİ", "BURSA");
        map.put("ULUDAĞ FAKÜLTESI", "BURSA");
        map.put("ULUDAĞ ÜNİVERSİTESİ", "BURSA");
        map.put("ULUDAĞ ÜNIVERSITESI", "BURSA");

        map.put("MEHMET AKİF HASTANESİ", "İSTANBUL");
        map.put("ÜSKÜDAR ÇAMLICA HASTANESİ", "İSTANBUL");
        map.put("BAHÇELIEVLER MEDICALPARK HASTANESI", "İSTANBUL");
        map.put("BAHÇELIEVLER MEDICALPARK HASTANESİ", "İSTANBUL");
        map.put("ÇAPA", "İSTANBUL");
        map.put("SIYAMI ERSEK", "İSTANBUL");
        map.put("SİYAMİ ERSEK", "İSTANBUL");
        map.put("GÖZTEPE EĞİTİM ARAŞTIRMA HASTANESİ", "İSTANBUL");
        map.put("GÖZTEPE EĞITIM", "İSTANBUL");
        map.put("KAVACIK MEDİSTATE HASTANESİ", "İSTANBUL");

        map.put("KAYSERİ EĞİTİM VE ARAŞTIRMA HASTANESİ", "KAYSERİ");
        map.put("LÜLEBURGAZ ÖZEL MEDİKENT HASTANESİ", "KIRKLARELİ");


        map.put("DOKUZ EYLÜL", "İZMİR");
        map.put("EGE ÜNİVERSİTESİ", "İZMİR");
        map.put("EGE UNIVERSITESI", "İZMİR");

        map.put("OMÜ TIP FAKÜLTESİ", "SAMSUN");
        map.put("ON DOKUZ MAYIS ÜNİVERSİTESİ TIP FAKÜLTESİ", "SAMSUN");
        map.put("MEDİCAL PARK KARADENİZ HASTANESİ", "TRABZON");

        names = Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getNames() {
        return names;
    }
}
