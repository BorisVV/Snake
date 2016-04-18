package com.BorisV;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 *
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {


	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint

	private Snake snake;
	private Kibble kibble;
	private Score score;


	DrawSnakeGamePanel(GameComponentManager components){
		this.snake = components.getSnake();
		this.kibble = components.getKibble();
		this.score = components.getScore();

	}

	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* Where are we at in the game? 4 phases..
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();



        switch (gameStage) {
			case SnakeGame.BEFORE_GAME: {
				displayInstructions(g);
				break;
			}
			case SnakeGame.DURING_GAME: {
				displayGame(g);
				break;
			}
			case SnakeGame.GAME_OVER: {
				displayGameOver(g);
				break;
			}
			case SnakeGame.GAME_WON: {
				displayGameWon(g);
				break;
        	}
        }
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;

		Image gameOver;
		ImageIcon fullSc = new ImageIcon("src\\you_won.png"); //Added by Boris
		gameOver = fullSc.getImage();             //Added by Boris
		g.drawImage(gameOver, 0, 0, maxX, maxY, this);


//		g.clearRect(100,100,350,350);
//		g.drawString("YOU WON SNAKE!!!", 150, 150);

	}
	private void displayGameOver(Graphics g) {
		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;

		Image gameOver;
		ImageIcon fullSc = new ImageIcon("src\\GameOver.jpg"); //Added by Boris
		gameOver = fullSc.getImage();             //Added by Boris
		g.drawImage(gameOver, 0, 0, maxX, maxY, this);

		g.clearRect(maxX-200,maxY-150,350,350);


		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();

		g.drawString("SCORE = " + textScore, maxX-175, maxY-125);

		g.drawString("HIGH SCORE = " + textHighScore, maxX-175, maxY-100);
		g.drawString(newHighScore, maxX-175, -75);

		g.drawString("press a key to play again", maxX-175, maxY-50);
		g.drawString("Press q to quit the game",maxX-175,maxY-25);


	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
	}

	private void displayGameGrid(Graphics g) {

		//Added by Boris
		Image backGrd;
		ImageIcon fullSc = new ImageIcon("src\\IlumCrystalCave-CW14.jpg"); //Added by Boris
		backGrd = fullSc.getImage();             //Added by Boris


		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
//		int squareSize = SnakeGame.squareSize;
//		g.clearRect(0, 0, maxX, maxY);
//		g.setColor(Color.RED);

		g.drawImage(backGrd, 0, 0, maxX, maxY, this);

		String textScore = score.getStringScore();
		g.drawString("SCORE = " + textScore,maxX-150, maxY-2);

		//Draw grid - horizontal lines
//		for (int y=0; y <= maxY ; y+= squareSize){
//			g.drawLine(0, y, maxX, y);
//		}
//		//Draw grid - vertical lines
//		for (int x=0; x <= maxX ; x+= squareSize){
//			g.drawLine(x, 0, x, maxY);
//		}
	}



	private void displayKibble(Graphics g) {

		//Added by Boris
		Image apple;
		ImageIcon iia = new ImageIcon("src\\apple-red-icon.png");
		apple = iia.getImage();
		//Added by Boris

		//Draw the kibble in green
//		g.setColor(Color.GREEN);
		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;


		g.drawImage(apple, x+1, y+1, SnakeGame.squareSize-2, SnakeGame.squareSize-2, this);
									//Added by Boris

//		g.fillRect(x+1, y+1, SnakeGame.squareSize-2, SnakeGame.squareSize-2);

	}



	private void displaySnake(Graphics g) {
		Snake heading;

		Image head1;
		ImageIcon iih = new ImageIcon("src\\LeftSnake.png"); //Added by Boris
		head1 = iih.getImage();

		Image head2;
		ImageIcon iih2 = new ImageIcon("src\\RightSnake.png"); //Added by Boris
		head2 = iih2.getImage();     //Added by Boris

		Image head3;
		ImageIcon iih3 = new ImageIcon("src\\UpSnake.png"); //Added by Boris
		head3 = iih3.getImage();             //Added by Boris


		Image head4;
		ImageIcon iih4 = new ImageIcon("src\\DownSnake.png"); //Added by Boris
		head4 = iih4.getImage();             //Added by Boris


		LinkedList<Point> coordinates = snake.segmentsToDraw();

		//Draw head in grey
//		g.setColor(Color.LIGHT_GRAY);
		Point head = coordinates.pop();

		//Added by Boris
		if(snake.currentHeading == snake.DIRECTION_LEFT){
		g.drawImage(head1, (int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize, this );}
		else if(snake.currentHeading == snake.DIRECTION_RIGHT){
		g.drawImage(head2, (int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize, this );}
		else if(snake.currentHeading == snake.DIRECTION_UP){
		g.drawImage(head3, (int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize, this );}
		else if(snake.currentHeading == snake.DIRECTION_DOWN){
		g.drawImage(head4, (int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize, this );}
		//Added by Boris

//		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);



		Image body;
		ImageIcon bodyIcon = new ImageIcon("src\\snakeskin_seamless_gray.png"); //Added by Boris
		body = bodyIcon.getImage();             //Added by Boris

		//Draw rest of snake in black
//		g.setColor(Color.BLACK);
		for (Point p : coordinates) {
			g.drawImage(body, (int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize, this);

//			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

	}


	private void displayInstructions(Graphics g) {
        g.drawString("Press any key to begin!",100,200);
        g.drawString("Press q to quit the game",100,300);

    	}

}


