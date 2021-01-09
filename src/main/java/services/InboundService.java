package services;

import game.GameVariables;

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
		if (position <= 0 || position >= GameVariables.WIDTH - 32) {
			return -1;
		}
		return 1;
	}
	
	public int keepInboundOnYAxe(int position) {
		if (position <= 0 || position >= GameVariables.HEIGHT - 48) {
			return -1;
		}
		return 1;
	}
	
}
