package com.example.movieseats;

public class CustomList {
    private int mImgResId;
    private String mMovieName, mMovieRating;

    public CustomList() {
    }

    public CustomList(int mImgResId, String mMovieName, String mMovieRating) {
        this.mImgResId = mImgResId;
        this.mMovieName = mMovieName;
        this.mMovieRating = mMovieRating;
    }

    public int getmImgResId() {
        return mImgResId;
    }

    public void setmImgResId(int mImgResId) {
        this.mImgResId = mImgResId;
    }

    public String getmMovieName() {
        return mMovieName;
    }

    public void setmMovieName(String mMovieName) {
        this.mMovieName = mMovieName;
    }

    public String getmMovieRating() {
        return mMovieRating;
    }

    public void setmMovieRating(String mMovieRating) {
        this.mMovieRating = mMovieRating;
    }

    //    public CustomList(int imgId, String movieN, String movieR){
//        mImgResId = imgId;
//        mMovieName = movieN;
//        mMovieRating = movieR;
//    }
//    public int getImgResId()
//    {
//        return mImgResId;
//    }
//    public String getmMovieName(){
//        return mMovieName;
//    }
//    public String getmMovieRating(){
//        return mMovieRating;
//    }
}
