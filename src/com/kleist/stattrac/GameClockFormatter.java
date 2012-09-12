package com.kleist.stattrac;

public class GameClockFormatter {
	public String formatTime(long millisLeft) {
		int minutes = (int) (millisLeft/GameClock.MILLIS_PER_MINUTE);
		int seconds = (int) (millisLeft/GameClock.MILLIS_PER_SECOND)%GameClock.SECONDS_PER_MINUTE;
		int tenthsOfASecond = (int)(millisLeft/ (GameClock.MILLIS_PER_SECOND/10)) % 10;
		return String.format("%02d:%02d.%01d", minutes, seconds, tenthsOfASecond);
	}

}
