package item;

public class RandomFactoryItem {
	private static final double probabilityItem = 0.2;
	public static Item create(int x,int y) {
		if (Math.random() <= probabilityItem) {
			if(Math.random() <= 1)  
				return new Skull(x,y);
			if(Math.random() <= 0.35)  
				return new FireSuit(x,y);
			if(Math.random() <= 0.60)  
					return new FireUp(x,y);
			return new FireDown(x,y);
		}
		return null;
	}
}