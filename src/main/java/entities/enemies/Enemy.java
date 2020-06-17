package entities.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.GameObject;
import entities.Id;

public class Enemy extends GameObject{
	
	protected int damage;

	public Enemy(int x, int y, Id id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
