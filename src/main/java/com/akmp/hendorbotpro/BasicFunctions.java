package com.akmp.hendorbotpro;

import java.util.Random;

public class BasicFunctions {

    /**
     * Mereturn jawaban dengan random
     * @return
     */
    public static String getRandomJawaban(){
        String jawaban = "";
        int random = new Random().nextInt();
        if(random % 2 == 0){
            jawaban = "Ya";
        } else{
            jawaban = "Nggak";
        }
        return jawaban;
    }

    /**
     * Get Pilihan
     *
     * mendapatkan pilihan dari command pilih
     * @param pilihan
     * @return
     */
    public static String getPilihan(String pilihan){
        String pilihan_list[] = pilihan.split("atau");

        String randomJawaban = pilihan_list[new Random().nextInt(pilihan_list.length)];

        return randomJawaban;
    }

}
