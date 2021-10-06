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

        return randomJawaban.trim();
    }

    /**
     * Mereturn commands hendorbot
     * @return
     */
    public static String getCommands(){
        String jawab =
                "Commands tanpa prefix: \n" +
                "=============\n" +
                "1. apakah\n" +
                "2. mau\n" +
                "3. akmj\n\n" +
                "Commands dengan prefix [!]\n" +
                "=============\n" +
                "7. !t atau !translate;kode bahasa awal-kode bahasa target atau kode bahasa target;text\n" +
                "8. !leave\n" +
                "9. !joged\n" +
                "10. !roll atau !roll;min;max\n" +
                "11. Cocok gak?:nama1&nama2\n" +
                "12. !ini atau itu\n" +
                "13. !... atau ... atau ... (n)atau ...\n" +
                "14. !stats\n" +
                "15. !about\n" +
                "Tolong jangan disalahgunakan ya bro";

        return jawab;
    }


}
