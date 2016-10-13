/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Naz Kazi
 */
public class WumpusWorld {
	public static final int x = 0;
	public static final int y = 1;
	
	public int world_size;
	public char[][] world;
	public int wumpus_count;
	
	public WumpusWorld(int world_size, double prob_wumpus, double prob_pit, double prob_block){
		this.world_size = world_size;
		this.world = new char[world_size][world_size];
		this.wumpus_count = 0;
		
		for(int row=0;row<world_size;row++){
			for(int col=0;col<world_size;col++){
				this.world[row][col] = '?';
			}
		}
		
		add_gold();
		add_wumpus(prob_wumpus);
		add_pit(prob_pit);
		add_block(prob_block);
	}
	
	private int[] random_position(){
		int[] pos = new int[2];
		for(int i=0;i<pos.length;i++){
			pos[i] = (int) (Math.random()*this.world_size);
		}
		return pos;
	}

	private void add_gold(){
		int[] pos = random_position();
		this.world[pos[x]][pos[y]] = 'G';
	}

	private void add_wumpus(double prob_wumpus){
		
	}

	private void add_pit(double prob_pit){
		
	}

	private void add_block(double prob_block){
		
	}
}
