package com.example.android.miwok;

public class Words {
    private String miwokWord;
    private String englishWord;
    private int imageResourceId;
    private int soundResourceID;

    public Words(String englishWord, String miwokWord, int soundResourceID) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.soundResourceID = soundResourceID;
    }

    public Words(String englishWord, String miwokWord, int imageResourceId, int soundResourceID) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.imageResourceId = imageResourceId;
        this.soundResourceID = soundResourceID;
    }

    public int getSoundResourceID() {
        return soundResourceID;
    }

    public String getMiwokWord() {
        if (miwokWord == null) {
            return "NULL";
        }
        return miwokWord;
    }


    public String getEnglishWord() {
        if (englishWord == null) {
            return "NULL";
        }
        return englishWord;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }
}
