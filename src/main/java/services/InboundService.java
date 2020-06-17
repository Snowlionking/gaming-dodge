package services;

import game.Game;

public class InboundService {

	public int clamp(int position, int min, int max) {
		if (position <= min) {
			return min;
		}
		if (position >= max) {
			return max;
		}
		return position;
	}
	
	public int keepInboundOnXAxe(int position) {
		if (position <= 0 || position >= Game.WIDTH - 32) {
			return -1;
		}
		return 1;
	}
	
	public int keepInboundOnYAxe(int position) {
		if (position <= 0 || position >= Game.HEIGHT - 48) {
			return -1;
		}
		return 1;
	}
	
}
