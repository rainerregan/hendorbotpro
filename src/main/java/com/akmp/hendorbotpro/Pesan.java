package com.akmp.hendorbotpro;

/**
 * Class Pesan
 *
 * @author Rainer Regan
 */
public class Pesan{

    /**
     * Pesan raw yang masuk
     */
    private String pesan;

    /**
     * Prefix
     */
    private char Prefix;

    /**
     * Getter pesan
     * @return pesan
     */
    public String getPesan() {
        return pesan;
    }

    /**
     * Setter pesan
     * @param pesan string pesan
     */
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    /**
     * Constructor
     * @param pesan
     */
    Pesan(String pesan){
        this.pesan = pesan;
    }

    /**
     * Return pesan split
     *
     * mereturn pesan split untuk commands
     *
     * Array [commands, isi pesan]
     * @return
     */
    public String[] getPesanSplitCommands(String pesan){
        return this.pesan.split(" ", 2);
    }

    /**
     * Check prefix pesan
     * @param prefix
     * @return
     */
    public boolean hasCustomPrefix(char prefix){
        if (this.pesan.charAt(0) == prefix){
            return true;
        }
        return false;
    }

    /**
     * Is default prefix set
     * @return
     */
    public boolean hasDefaultPrefixSet(){
        if(this.pesan.charAt(0) == Settings.BOT_PREFIX){
            return true;
        }
        return false;
    }

    /**
     * Get Pesan Without Prefix
     * @return
     */
    public String getPesanWithoutPrefix(){
        return this.pesan.substring(1);
    }


}
