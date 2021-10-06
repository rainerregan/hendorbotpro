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

}
