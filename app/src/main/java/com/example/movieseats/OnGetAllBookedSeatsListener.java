package com.example.movieseats;

import java.util.List;

public interface OnGetAllBookedSeatsListener {

    void onGetAllBookedSeatsSuccess(List<String> bookedSeatsList);
    void onGetAllBookedSeatsFailure();
}
