package item;

public class RandomFactoryItem {
	private static final double probabilityItem = 0.2;
	
	public static Item create(int x,int y) {
		if (Math.random() <= probabilityItem) {
			double rand = Math.random();
			if( rand <= 0.2)  
				return new Skull(x,y);
			if( rand <= 0.5)  
				return new FireSuit(x,y);
			if( rand  <= 0.8)  
					return new FireUp(x,y);
			return new FireDown(x,y);
		}
		return null;
	}
}