package com.example.android.miwok;

public class Word {

    /* Custom states */
    private final String mDefaultTranslation;
    private final String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDER;    // for checking in runtime that there is ImageView or not...
    private static final int NO_IMAGE_PROVIDER = -1;
    private int mAudioResourceId;

    // This constructor is for all the activities except PhrasesActivity because PhrasesActivity does not need image...
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int mAudioResourceId) {

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = mAudioResourceId;
    }

    // Constructor Overloading...
    // This constructor is for PhrasesActivity because it has no image resource as argument...
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mAudioResourceId) {

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = mAudioResourceId;
    }

    /* Getter methods */
    public String getmDefaultTranslation() {

        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {

        return mMiwokTranslation;
    }

    public int getmImageResourceId() {

        return mImageResourceId;
    }

    public boolean hasImage(){

        return mImageResourceId != NO_IMAGE_PROVIDER;
    }

    public int getmAudioResourceId() {

        return mAudioResourceId;
    }
}
