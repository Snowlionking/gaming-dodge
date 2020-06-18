package services;

import game.GameModel;

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
		if (position <= 0 || position >= GameModel.WIDTH - 32) {
			return -1;
		}
		return 1;
	}
	
	public int keepInboundOnYAxe(int position) {
		if (position <= 0 || position >= GameModel.HEIGHT - 48) {
			return -1;
		}
		return 1;
	}
	
}
