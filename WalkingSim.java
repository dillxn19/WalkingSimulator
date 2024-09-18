
import java.util.Random; 
import java.io.File; 
import processing.core.PImage;


public class WalkingSim {
	
	private static Random randGen;
	private static int bgColor;
	private static PImage[] frames;
	private static Walker[] walkers;
	
	
	public static void setup()
	{
		randGen = new Random();
		bgColor = randGen.nextInt();
		frames = new PImage[Walker.NUM_FRAMES];
		walkers = new Walker[8];
		for (int w = 0; w < (randGen.nextInt(walkers.length) + 1); w++)
		{
			walkers[w] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
		}
		
		for (int i = 0; i < frames.length; i++)
		{
			frames[i] = Utility.loadImage("images" + File.separator+ "walk-" + i + ".png");
			
		}
	}
	
	
	public static void draw()
	{
		Utility.background(bgColor);
		for (int z = 0; z < walkers.length; z++)
		{
			if (walkers[z] == null)
			{
				break;
			}
			if (walkers[z].isWalking())
			{
				walkers[z].setPositionX((walkers[z].getPositionX() + 3) % 800);
			}
			Utility.image(frames[walkers[z].getCurrentFrame()], walkers[z].getPositionX(), walkers[z].getPositionY());

		}
		

		
		for (int c = 0; c < walkers.length; c++)
		{
			if (walkers[c] == null)
			{
				break;

			}
			if (walkers[c].isWalking()) 
			{
				walkers[c].update();
			}

		}
		
	}
	
	
	public static boolean isMouseOver(Walker character)
	{
		if ((Utility.mouseX() >= (character.getPositionX() - (frames[0].width / 2)) &&
			     Utility.mouseX() <= (character.getPositionX() + (frames[0].width / 2))) &&
			    (Utility.mouseY() >= (character.getPositionY() - (frames[0].height / 2)) &&
			     Utility.mouseY() <= (character.getPositionY() + (frames[0].height / 2))))
		{
			return true;
		}
		return false;
	}
	
	
	public static void mousePressed()
	{
		for (int m = 0; m < walkers.length; m++)
		{
			if (walkers[m] == null)
			{
				break;
			}
			if (isMouseOver(walkers[m]))
			{
				walkers[m].setWalking(true);
				break;
			}

		}
	}
	
	
	public static void keyPressed(char key)
	{
		if (key == 'a')
		{
			for (int k = 0; k < walkers.length; k++)
			{
				
				if (walkers[k] == null)
				{
					int randX = randGen.nextInt(Utility.width());
					int randY = randGen.nextInt(Utility.height());
					walkers[k] = new Walker(randX, randY);
					break;
				}
			}
		}
		
		if (key == 's')
		{
			for (int k = 0; k < walkers.length; k++)
			{
				if (walkers[k] == null)
				{
					break;
				}
				walkers[k].setWalking(false);
			}
		}
	}

	
	public static void main(String[] args) {
		Utility.runApplication();

	}

}
