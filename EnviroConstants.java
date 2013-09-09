import java.awt.Color;


public interface EnviroConstants {
	public static final int BASE_BREED_TIME = 10;
	public static final double PREY_BREED_MULTIPLIER = 0.2;
	public static final double PREDATOR_BREED_MULTIPLIER = 0.7;
	public static final int PREY_BREED_YIELD = 2;
	public static final int PREDATOR_BREED_YIELD = 2;
	
	public static final int BASE_SURVIVAL_TIME = 5;
	public static final int BASE_FEED_TIME = 2;
	
	public static final int PREY_CAPACITY = 4;
	public static final int PREDATOR_CAPACITY = 3;

	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 650;
	public static final int BOX_SIDE = 10;
	
	public static final int PAUSE_TIME = 5;
	/*
	 * ADD COLORS TO SHOW CONCENTRATIONS OF PREY/PREDATORS.
	 * 1 SHADE FOR EACH NUMBER OF EACH THAT COULD BE IN A SQUARE.
	 * SET OPACITY SO THEY CAN OVERLAP
	 * USE DIFFERENT COLORS TO NOTE NUMBER OF PREDATORS, AND DIFFERENT SHADE FOR NUMBER OF PREY
	 */
	public static final Color prey0pred0 = Color.WHITE;
	//variations of green (light to dark)
	public static final Color prey1pred0 = new Color(170, 255, 150);
	public static final Color prey2pred0 = new Color(125, 255, 100);
	public static final Color prey3pred0 = new Color(66, 255, 28);
	public static final Color prey4pred0 = new Color(37, 219, 0);
	//variations of blue (light to dark)
	public static final Color prey0pred1 = new Color(130, 247, 255);
	public static final Color prey1pred1 = new Color(90, 245, 255);
	public static final Color prey2pred1 = new Color(25, 230, 245);
	public static final Color prey3pred1 = new Color(0, 202, 217);
	public static final Color prey4pred1 = new Color(33, 166, 176);
	//variations of purple (light to dark)
	public static final Color prey0pred2 = new Color(255, 189, 255);
	public static final Color prey1pred2 = new Color(213, 160, 255);
	public static final Color prey2pred2 = new Color(186, 102, 255);
	public static final Color prey3pred2 = new Color(165, 55, 255);
	public static final Color prey4pred2 = new Color(122, 0, 222);
	//variations of red/orange (light to dark)
	public static final Color prey0pred3 = new Color(255, 168, 130);
	public static final Color prey1pred3 = new Color(255, 140, 90);
	public static final Color prey2pred3 = new Color(255, 120, 60);
	public static final Color prey3pred3 = new Color(240, 90, 30);
	public static final Color prey4pred3 = new Color(195, 65, 10);
}
