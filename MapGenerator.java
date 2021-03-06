package breakbricks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHight;
	public MapGenerator(int row,int col)
	{
		map=new int[row][col];
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[0].length;j++)
				map[i][j]=1;
		
		brickWidth= 540/col;
		brickHight=250/col;
	}
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
			for(int j=0;j<map[0].length;j++)
				if(map[i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+80, i*brickHight+80, brickWidth, brickHight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*brickWidth+80, i*brickHight+80, brickWidth, brickHight);
				}
		
	}
	public void setBrickValue(int value,int row, int col)
	{
		map[row][col]=value;
	}

}
