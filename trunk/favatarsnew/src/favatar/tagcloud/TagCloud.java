package favatar.tagcloud;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import favatar.favatar.Favatar;
import favatar.firstlogin.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


public class TagCloud extends Activity implements Renderer{

    private ClearGLSurfaceView mGLView;/*
    private float[] squareVertices = {
		      1.0f,  0.0f, 0.0f, 0.0f, // 0, Top Left
		      0.0f,  1.0f, 0.0f, 0.0f, // 1, Bottom Left
		      0.0f,  0.0f, 1.0f, 0.0f, // 2, Bottom Right
		      0.0f,  0.0f, 0.0f, 0.0f, // 3, Top Right
		};

    private float[] squareVerticesRm = {
		      0.0f,  0.0f, 0.0f, 0.0f, // 0, Top Left
		      0.0f,  0.0f, 0.0f, 0.0f, // 1, Bottom Left
		      0.0f,  0.0f, 0.0f, 0.0f, // 2, Bottom Right
		      0.0f,  0.0f, 0.0f, 0.0f, // 3, Top Right
		};
*/
	// Our vertices.
	public float vertices[] = {
		      -1.0f,  0.5f, 0.0f,  // 0, Top Left
		      -1.0f, -0.5f, 0.0f,  // 1, Bottom Left
		       1.0f, -0.5f, 0.0f,  // 2, Bottom Right
		       1.0f,  0.5f, 0.0f,  // 3, Top Right
		};

	// Our vertices.
	public float verticesArrTL[][] = { {-1.0f}, {0.5f}, {0.0f}, {1.0f} };
	public float verticesArrBL[][] = { {-1.0f}, {-0.5f}, {0.0f}, {1.0f} };
	public float verticesArrBR[][] = { {1.0f}, {-0.5f}, {0.0f}, {1.0f} };
	public float verticesArrTR[][] = { {1.0f}, {0.5f}, {0.0f}, {1.0f} };
	
	public float verticesArr[][] = { 	{-1.0f, -1.0f, 1.0f, 1.0f}, 
										{0.5f, -0.5f, -0.5f, 0.5f}, 
										{0.0f, 0.0f, 0.0f, 0.0f}, 
										{1.0f, 1.0f, 1.0f, 1.0f} };
		      /*{-1.0f,  0.5f, 0.0f, 0.0f},  // 0, Top Left
		      {-1.0f, -0.5f, 0.0f, 0.0f},  // 1, Bottom Left
		      { 1.0f, -0.5f, 0.0f, 0.0f},  // 2, Bottom Right
		       1.0f,  0.5f, 0.0f,  // 3, Top Right*/
	
	
	// The order we like to connect them.
	private short[] indices = { 0, 1, 2, 0, 2, 3 };

	private float textureCoordinates[] = {
		      -1.0f, -1.0f,   // 0, Bottom Left
		      -1.0f,  1.0f,   // 1, Top Left
		       1.0f,  1.0f,  // 2, Top Right
		       1.0f, -1.0f  // 3, Bottom Right
		};
    
	private float[] projectionMatrix;
	private float[] modelViewMatrix;
	
	public TagCloud()
    {
		// Initialize our square. 
		square = new Tag();

		square.setIndices(indices);
		square.setTextureCoordinates(textureCoordinates);
		square.setVertices(vertices);
		
		bottomButton =new Tag();

		float verticesButton[] = {
			      -1.0f,  0.5f, 0.0f,  // 0, Top Left
			      -1.0f, -0.5f, 0.0f,  // 1, Bottom Left
			       1.0f, -0.5f, 0.0f,  // 2, Bottom Right
			       1.0f,  0.5f, 0.0f,  // 3, Top Right
			};
		
		// The order we like to connect them.
		short[] indicesButton = { 0, 1, 2, 0, 2, 3 };

		float textureCoordinatesButton[] = {
			      -1.0f, -1.0f,   // 0, Bottom Left
			      -1.0f,  1.0f,   // 1, Top Left
			       1.0f,  1.0f,  // 2, Top Right
			       1.0f, -1.0f  // 3, Bottom Right
			};
		bottomButton.setIndices(indicesButton);
		bottomButton.setTextureCoordinates(textureCoordinatesButton);
		bottomButton.setVertices(verticesButton);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
        
        mGLView = new ClearGLSurfaceView(this);
        mGLView.setRenderer(this);
      //sets the wrapper  
       mGLView.setGLWrapper(mGLView);
          
        setContentView(mGLView);
        
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }



    private Tag square;
    private Tag bottomButton;
    
	private float angleLower = 1440;
	private float angleHigher = 1440 * 6;

	HashMap<Integer, Integer> texturesFront = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> texturesBack = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> texturesSelect = new HashMap<Integer, Integer>();
	HashMap<Integer, String> valueSet = new HashMap<Integer, String>();
	

	HashMap<Integer, Integer> texturesSecFront = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> texturesSecBack = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> texturesSecSelect = new HashMap<Integer, Integer>();
	HashMap<Integer, String> valueSetSec = new HashMap<Integer, String>();

	ArrayList<String> foodList = new ArrayList<String>();
	
	LinkedHashMap<Integer, String> foodTypes = new LinkedHashMap<Integer, String>();
	FavHash<Integer, String, Integer, Integer> foods = new FavHash<Integer, String, Integer, Integer>();
	FavMap<Integer, Integer> foodMapping = new FavMap<Integer, Integer>();
	
	
	private float rotateChangeFactorHigher = 0.3f;
	private float rotateChangeFactorLower = 0.3f;
	GL10 currentGL;

	Boolean touchResolved = true;
	//ArrayList<Integer> touchedTextures = new ArrayList<Integer>();
	ArrayList<Integer> touchedFoodTypes = new ArrayList<Integer>();
	ArrayList<Integer> touchedFoods = new ArrayList<Integer>();
	ArrayList<Integer> tempFoodList = new ArrayList<Integer>();
	
	
	int[] colorNormal = {255, 255, 0, 255};
	int[] colorBack = {255, 0, 255, 255};
	int[] colorSelect = {255, 255, 255, 255};
	

	int[] colorNormalSec = {255, 70, 165, 229};
	int[] colorBackSec = {255, 24, 41, 69};
	int[] colorSelectSec = {255, 161, 196, 54};
	
	int cVal = 0;
	
	int texButtonDef;
	int texButtonOver;
	Boolean buttonTouched = false;
	
	Boolean threadsFinished = false;

	int texLoading;
	
	int[] viewPort;
	

	float transFactor = 0.0f;
	float yVal = 0.0f;
	int index = 0;
	float yDiff = 0.5f;
	float xDiff = 20.0f;
	float[] winTL = new float[16];
	float[] winTR = new float[16];
	float[] winBR = new float[16];
	
	float tempAngle;
	float realangle;
	
	float[][] cModelViewTwo;
	float[][] current;
	
	float windowRealLeft;
	float windowRealRight;
	float windowRealTop;
	float windowRealBottom;
	
	int mHeight;
	int mWidth;
	
	//int windowDiviation = 40;
	
	float touchedX;
	float touchedY;
	long touchedTime = 0;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(final GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		//gl.glClearColor((float)232/255, (float)232/255, (float)240/255, 1.0f);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		LoadFoods();
		

		/*new Thread( new Runnable(){

			@Override
			public void run() {
				texturesFront = GenerateColors(gl, colorNormal, valueSet);
				texturesBack = GenerateColors(gl, colorBack, valueSet);
				if (!threadsFinished)
					threadsFinished = true;
			}
			
		}).start();
		new Thread( new Runnable(){

			@Override
			public void run() {
				texturesSecFront = GenerateColors(gl, colorNormalSec, valueSet);
				texturesSecBack = GenerateColors(gl, colorBackSec, valueSet);
				if (!threadsFinished)
					threadsFinished = true;
			}
			
		}).start();

		new Thread( new Runnable(){

			@Override
			public void run() {
				texturesSelect = GenerateColors(gl, colorSelect, valueSet);
				texturesSecSelect = GenerateColors(gl, colorSelectSec, valueSet);
			}
			
		}).start();
		
				texturesFront = GenerateColors(gl, colorNormal, valueSet);
				texturesBack = GenerateColors(gl, colorBack, valueSet);
				texturesSecFront = GenerateColors(gl, colorNormalSec, valueSet);
				texturesSecBack = GenerateColors(gl, colorBackSec, valueSet);
				texturesSelect = GenerateColors(gl, colorSelect, valueSet);
				texturesSecSelect = GenerateColors(gl, colorSelectSec, valueSet);
				
		*/

		
		texButtonDef = GenerateBitmapTexture(gl, "Share", colorSelect, 145);
		texButtonOver = GenerateBitmapTexture(gl,"Share", colorSelectSec, 145);
		texLoading = GenerateBitmapTexture(gl, "Loading...", colorSelect, 100);
	}
	

	void LoadFoods()
	{
		foodTypes.put(0, "Rice");
		foodTypes.put(1, "Vegetables");
		foodTypes.put(2, "Fruits");
		foodTypes.put(3, "Soup");
		foodTypes.put(4, "Chicken");
		foodTypes.put(5, "Meat");
		foodTypes.put(6, "Seafood");
		foodTypes.put(7, "Sweets");
		foodTypes.put(8, "Pastry");
		foodTypes.put(9, "Congee");
		foodTypes.put(10, "Dessert");
		foodTypes.put(11, "Cakes");
		foodTypes.put(12, "Rotti");
		foodTypes.put(13, "Curry");
		foodTypes.put(14, "Kottu");
		        foodTypes.put(15, "pizza");
		        foodTypes.put(16, "Juice");
		        foodTypes.put(17, "Buns");
		        foodTypes.put(18, "Ice cream");
		        foodTypes.put(19, "Pudding");
		        foodTypes.put(20, "Dairy Products");
		        foodTypes.put(21, "Biscuits");
		        foodTypes.put(22, "Candy");
		        foodTypes.put(23, "Bread");
		        foodTypes.put(24, "Cereal");
		        foodTypes.put(25, "Pasta");
		        foodTypes.put(26, "Beverage");
		        foodTypes.put(27, "Nut/Seed");
		        foodTypes.put(28, "Sweetener");
		foodTypes.put(29, "Flour");
		foodTypes.put(30, "Grain");
		foodTypes.put(31, "Muffin");
		foodTypes.put(32, "Bean");
		foodTypes.put(33, "Oil");
		foodTypes.put(34, "Herb");
		foodTypes.put(35, "Cheese");
		//foodTypes.put(36, "Cookies");








		        foods.put(0, "Biriyani", 287, 45);
		        foods.put(1, "Boiled Rice", 199, 23);
		        foods.put(2, "Fried Rice", 247, 34);
		        foods.put(3, "Steamed Rice",188, 77);
		        foods.put(4, "Cucumber", 56, 55);
		        foods.put(5, "Beans", 71, 22);
		        foods.put(6, "Pumpkin",33,29);
		        foods.put(7, "Carrot",80, 10);
		        foods.put(8, "Brinjals",89,38);
		        foods.put(9, "Potatoes",90, 66);
		        foods.put(10, "Chillie",34,29);
		        foods.put(11, "Mango", 19, 3);
		        foods.put(12, "Apple", 27,45);
		        foods.put(13, "Red Rice",32,31);
		                foods.put(14, "Nasiguran", 89,50);
		                foods.put(15, "Tomato",70,63);
		                foods.put(16, "Egg Kottu",199, 77);
		                foods.put(17, "Chicken Kottu",255,98);
		                foods.put(18, "Vegetable Kottu", 168,68);
		                foods.put(19, "Thanduri", 289, 45);
		                foods.put(20, "Spicy chicken",285,39);
		                foods.put(21, "Papaya", 34,23);
		                foods.put(22, "Pineapple",12,1);
		                foods.put(23, "Graphs",41,30);
		                foods.put(24, "Chocolate",247,34);
		                foods.put(25, "Fruit&Nuts",95,45);
		                foods.put(26, "Vanilla",90,40);
		                foods.put(27, "Caramel", 122,29);
		                foods.put(28, "Custered",175,67);
		                foods.put(29, "Biscuit pudding",164,63);
		                foods.put(28, "Curd",75,34);
		                foods.put(29, "Milk",56,23);
		        foods.put(30, "Avocado",13,56);
		        foods.put(31, "Cocoa",71,73);
		        foods.put(32, "Durian",19,22);
		        foods.put(33, "Jackfruit",46,25);
		        foods.put(34, "Lemon", 89,23);
		        foods.put(35, "Olive",28,34);
		        foods.put(36, "Tomato",56,67);
		        foods.put(37, "Strawberry",26,35);
		        foods.put(38, "Borscht",89,67);
		        foods.put(39, "Cazuela",92,26);
		        foods.put(40, "Pumpkin",38,51);
		        foods.put(41, "Lagman",84,67);
		        foods.put(43, "Develed chicken ",345,96);
		                foods.put(44, "Grilled chicken",300,79);
		                foods.put(45, "Sausages",285,45);
		                foods.put(46, "Beef",345,56);
		                foods.put(47, "Pork",377,98);
		                foods.put(48, "Eel",299,54);
		                foods.put(49, "Bluefish",67,56);
		                foods.put(50, "Butterfish",78,45);
		                foods.put(51, "Chocolate",167,56);
		                foods.put(52, "Toffee",34,24);
		                foods.put(53, "Graps",17,24);
		                foods.put(54, "Fruit Salads",63,13);
		                foods.put(55, "Faluda",73,45);
		                foods.put(56, "Chocolate cake",133,74);
		                foods.put(57, "Butter cake",100,46);
		                foods.put(58, "Parata",185,34);
		                foods.put(59, "Coconut rotti",111,37);
		                foods.put(60, "Godamba",90,33);
		                foods.put(61, "Dhal",86,41);
		                foods.put(62, "Mushrooms",184,90);
		                foods.put(63, "Carrot",43,82);
		                foods.put(64, "Flaky",81,46);
		                foods.put(65, "Phyllo",19,46);
		                foods.put(66, "Choux",48,56);
		                foods.put(67, "Kola Keda",15,67);
		                foods.put(68, "Green Gram",41,34);
		                foods.put(69, "Salada",54,86);
		                foods.put(70, "Donut",83,46);
		                foods.put(71, "Egg Bun",136,38);
		                foods.put(72, "Wheat",188,45);
		                foods.put(73, "Spler bread",83,56);
		                foods.put(74, "Cornflakes",68,34);
		                foods.put(75, "Cornmeal",155,35);
		                foods.put(76, "Couscou",66,45);
		                foods.put(77, "Oatmeal",242,72);
		                foods.put(78, "Familia",216,34);
		                foods.put(79, "Artichoke",266,45);
		                foods.put(80, "Soba",167,64);
		                foods.put(81, "Semolina",165,26);
		                foods.put(82, "Spinach",188,47);
		                foods.put(83, "Tea",37,34);
		                foods.put(84, "Tea(green)",23,75);
		                foods.put(85, "Wine",233,43);
		                foods.put(86, "Coffee",85,56);
		                foods.put(87, "Beer",197,56);
		                foods.put(88, "Soda(Club)",79,39);
		                foods.put(89, "Soba(Cola)",87,45);
		                foods.put(90, "Cashew", 159,56);
		                foods.put(91, "Almond",166,46);
		                foods.put(92, "Chestnut",184,75);
		                foods.put(93, "Peanut",199,36);
		                foods.put(94, "Barley",102,56);
		                foods.put(95, "Sugar(Brown)",168,85);
		                foods.put(96, "Sugar(White)",200,23);
		                foods.put(97, "Honey",262,46);        
		                foods.put(98, "Avocado",29,35);
		                foods.put(99, "Blackberry",46,34);
		                foods.put(100, "Chery",14,25);
		                foods.put(101, "Cranberry",19,35);
		                foods.put(102, "Date",37,44);
		                foods.put(103, "Guava",27,45);
		                foods.put(104, "Lemon",87,46);
		                foods.put(105, "Peach",80,45);
		                foods.put(106, "Plum",90,14);
		                foods.put(107, "Strwberry",79,34);
		                foods.put(108, "Melon",50,35);
		                foods.put(109, "Watermelon",38,35);
		                foods.put(110, "Raisin",59,45);
		                foods.put(111, "Starfruit",85,38);
		                foods.put(112, "Dragonfruit", 28,58);
		foods.put(113, "Barley Flour",166,78);
		foods.put(114, "Gluten Flour",176,46);
		foods.put(115, "Graham Flour",185,43);
		foods.put(116, "Rice Flour",144,23);
		foods.put(117, "Bulgar Flour",173,45);
		foods.put(118, "Barley",45,23);
		foods.put(119, "Corn(White)",45,34);
		foods.put(120, "Corn(Yellow)",67,45);
		foods.put(121, "Millet",89,82);
		foods.put(122, "Corn Muffin",70,45);
		foods.put(123, "Fin Crisp",46,42);

		foods.put(124, "Rye Crisp",89,34);
		foods.put(125, "Copper Bean",34,97);
		foods.put(126, "Lima Bean",55,22);
		foods.put(127, "Red Bean",17,34);
		foods.put(128, "Canola Oil",39,23);
		foods.put(129, "Olive Oil",89,27);
		foods.put(130, "Cod Liver Oil",122,42);
		foods.put(131, "Clove",67,23);
		foods.put(132, "Cumin",70,45);
		foods.put(133, "Nutmeg",45,76);
		foods.put(134, "Dill",69,56);
		foods.put(135, "Swiss Cheese",149,59);
		foods.put(136, "Goat Cheese",280,67);
		foods.put(137, "Vegitarian Cheese",179,56);
		foods.put(138, "Feta Cheese",189,48);
		foods.put(139, "Fontina Cheese",189,38);
		foods.put(140, "Blue Cheese",177,29);
		foods.put(141, "Biscotti", 123,34);
		foods.put(142, "Brownies",154,26);
		foods.put(143, "Cake Mix Cookies",78,25);
		foods.put(144, "Drop Cookies",177,34);
		foods.put(145, "No Bake Cookies",199,25);


		        
		//Key will be that of the food, value will be food type
		foodMapping.put(0, 0);
		foodMapping.put(0, 1);
		foodMapping.put(0, 2);
		foodMapping.put(0, 3);
		foodMapping.put(1, 0);
		foodMapping.put(1, 2);
		foodMapping.put(1, 4);
		foodMapping.put(1, 5);
		foodMapping.put(1, 6);
		foodMapping.put(1, 7);
		foodMapping.put(1, 8);
		foodMapping.put(1, 9);
		foodMapping.put(1, 10);
		foodMapping.put(2, 11);
		foodMapping.put(2, 12);
		foodMapping.put(2, 30);
		foodMapping.put(2, 31);
		foodMapping.put(2, 32);
		foodMapping.put(2, 33);
		foodMapping.put(2, 34);
		foodMapping.put(2, 35);
		foodMapping.put(2, 36);
		foodMapping.put(2, 37);
		foodMapping.put(3, 38);
		foodMapping.put(3, 39);
		foodMapping.put(3, 40);
		foodMapping.put(3, 41);
		foodMapping.put(3, 42);
		foodMapping.put(4, 0);
		foodMapping.put(4, 43);
		        foodMapping.put(4, 44);
		        foodMapping.put(5, 45);
		        foodMapping.put(5, 46);
		        foodMapping.put(5, 47);
		        foodMapping.put(6, 48);
		        foodMapping.put(6, 49);
		        foodMapping.put(6, 50);
		        foodMapping.put(7, 51);
		        foodMapping.put(7, 52);
		        
		        foodMapping.put(8, 64);
		        foodMapping.put(8, 65);
		        foodMapping.put(8, 66);

		        foodMapping.put(9, 67);
		        foodMapping.put(9, 68);
		        foodMapping.put(9, 69);
		        
		        foodMapping.put(10, 53);
		        foodMapping.put(10, 54);
		        foodMapping.put(10, 55);
		        foodMapping.put(11, 56);
		        foodMapping.put(11, 57);
		        foodMapping.put(12, 58);
		        foodMapping.put(12, 59);
		        foodMapping.put(12, 60);
		        foodMapping.put(13, 61);
		        foodMapping.put(13, 62);
		        foodMapping.put(13, 63);
		foodMapping.put(14, 16);
		        foodMapping.put(14, 17);
		        foodMapping.put(14, 18);
		        foodMapping.put(15, 19);
		        foodMapping.put(15, 20);
		        foodMapping.put(16, 21);
		        foodMapping.put(16, 22);
		        foodMapping.put(16, 23);

		        foodMapping.put(17, 70);
		        foodMapping.put(17, 71);
		        
		        foodMapping.put(18, 24);
		        foodMapping.put(18, 25);
		        foodMapping.put(18, 26);
		        foodMapping.put(19, 27);
		        foodMapping.put(19, 28);
		        foodMapping.put(20, 28);
		        foodMapping.put(20, 29);
		        foodMapping.put(23, 72);
		        foodMapping.put(23, 73);
		        foodMapping.put(24, 74);
		        foodMapping.put(24, 75);
		        foodMapping.put(24, 76);
		        foodMapping.put(24, 77);
		        foodMapping.put(24, 78);
		        foodMapping.put(25, 79);
		        foodMapping.put(25, 80);
		        foodMapping.put(25, 81);
		        foodMapping.put(25, 82);
		        foodMapping.put(26, 83);
		        foodMapping.put(26, 84);
		        foodMapping.put(26, 85);
		        foodMapping.put(26, 86);
		        foodMapping.put(26, 87);
		        foodMapping.put(26, 88);
		        foodMapping.put(26, 89);
		        foodMapping.put(27, 90);
		        foodMapping.put(27, 91);
		        foodMapping.put(27, 92);
		    foodMapping.put(27, 93);
		      foodMapping.put(28, 94);
		    foodMapping.put(28, 95);
		    foodMapping.put(28, 96);
		    foodMapping.put(28, 97);
		    foodMapping.put(2, 98);
		    foodMapping.put(2, 99);
		    foodMapping.put(2, 100);
		    foodMapping.put(2, 101);
		    foodMapping.put(2, 102);
		    foodMapping.put(2, 103);
		    foodMapping.put(2, 104);
		    foodMapping.put(2, 105);
		    foodMapping.put(2, 106);
		    foodMapping.put(2, 107);
		    foodMapping.put(2, 108);
		    foodMapping.put(2, 109);
		    foodMapping.put(2, 110);
		    foodMapping.put(2, 111);
		foodMapping.put(2, 112); 

		foodMapping.put(29, 113); 
		foodMapping.put(29, 114); 
		foodMapping.put(29, 115); 
		foodMapping.put(29, 116); 
		foodMapping.put(29, 117); 
		foodMapping.put(30, 118); 
		foodMapping.put(30, 119); 
		foodMapping.put(30, 120);
		foodMapping.put(30, 121); 
		foodMapping.put(31, 122); 
		 foodMapping.put(31, 123); 
		foodMapping.put(31, 124); 
		foodMapping.put(32, 125); 
		foodMapping.put(32, 126);
		foodMapping.put(32, 127); 
		 foodMapping.put(33, 128);
		foodMapping.put(33, 129);
		foodMapping.put(33, 130); 
		 foodMapping.put(34, 131); 
		 foodMapping.put(34, 132); 
		 foodMapping.put(34, 133); 
		 foodMapping.put(34, 134); 
		foodMapping.put(35, 135); 
		foodMapping.put(35, 136); 
		foodMapping.put(35, 137);
		foodMapping.put(35, 138);
		foodMapping.put(35, 139);
		foodMapping.put(35, 140); 
		 foodMapping.put(36, 141); 
		foodMapping.put(36, 142); 
		foodMapping.put(36, 143); 
		foodMapping.put(36, 144); 
		foodMapping.put(36, 145); 

	}
	
	

	
	private int GenerateBitmapTexture(GL10 gl, String value, int[] color, int fontSize) {
		int[] textures = new int[1];
		Bitmap temp;

		// Create an empty, mutable bitmap		
		temp = Bitmap.createBitmap(512,256, Bitmap.Config.ARGB_8888); 
		
		// get a canvas to paint over the bitmap 
		Canvas canvas = new Canvas(temp); 
		temp.eraseColor(0);
			
	
		// Draw the text 
		
		Paint textPaint = new Paint(); 
		textPaint.setTextSize(fontSize); 
		textPaint.setAntiAlias(false); 
		textPaint.setARGB(color[0], color[1], color[2], color[3]); 
		
		// draw the text centered 
		canvas.drawText(value, 16,112, textPaint); 
		
		gl.glEnable( GLES10.GL_ALPHA_TEST );

		gl.glAlphaFunc( GLES10.GL_GREATER, 0 );
		//Generate one texture pointer... 
		gl.glGenTextures(1, textures , 0); 
		//...and bind it to our array 
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]); 
		//Create Nearest Filtered Texture 
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST); 
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE 
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE); 
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE); 
		//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, temp, 0); 

		//SaveImage(temp, value);
		//Clean up 
		temp.recycle();
		
		return textures[0];
	}
    /*
	private void SaveImage(Bitmap img, String name)
	{
		String path = "/sdcard/favatars/";
		// create a File object for the parent directory
		//File wallpaperDirectory = new File(path);
		// have the object build the directory structure, if needed.
		//wallpaperDirectory.mkdirs();
		// create a File object for the output file
		//File outputFile = new File(wallpaperDirectory, filename);
		// now attach the OutputStream to the file object, instead of a String representation
		//FileOutputStream fos = new FileOutputStream(outputFile);

		try {       
			FileOutputStream out = new FileOutputStream(path + name + ".png");       
			img.compress(Bitmap.CompressFormat.PNG, 90, out);
		} catch (Exception e) {       
			e.printStackTrace();
		} 
	}
*/
	/*private HashMap<Integer, Integer> GenerateColors(GL10 gl, int color[], HashMap<Integer, String> values)
	{
		HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for (int i : values.keySet()) {
			temp.put(i, GenerateBitmapTexture(gl, values.get(i),color, 90));
		}
		
		return temp;
	}*/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {

		
		currentGL = gl;
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();
		// Translates 10 units into the screen.
		gl.glTranslatef(0, 0, -10); 

		if (!threadsFinished){
			if (foodTypes.size() > cVal){
				if (foodTypes.containsKey(cVal)){
					String cName= foodTypes.get(cVal);
					texturesFront.put(cVal, GenerateBitmapTexture(gl, cName, colorNormal, 90));
					texturesBack.put(cVal, GenerateBitmapTexture(gl,cName, colorBack, 90));
					texturesSelect.put(cVal, GenerateBitmapTexture(gl, cName, colorSelect, 90));
					/*
					*/
					valueSet.put(cVal, cName);
				}
				cVal++;	
			}	
			else if (tempFoodList.size() > 0){

				int val = tempFoodList.get(0);
				if (foods.containsKey(val)){
				String cName = foods.get(val);
					texturesSecFront.put(val, GenerateBitmapTexture(gl, cName, colorNormalSec, 90));
					texturesSecBack.put(val, GenerateBitmapTexture(gl, cName, colorBackSec, 90));
					texturesSecSelect.put(val, GenerateBitmapTexture(gl, cName, colorSelectSec, 90));
					valueSetSec.put(val, cName);					
				}
				tempFoodList.remove((Object)val);
			} else {
				threadsFinished = true;
			}
		}
		

		DrawElements(gl);
		
		

		gl.glPushMatrix();
		if (threadsFinished){
			gl.glTranslatef(1.6f, -3.6f, 0.5f);
			if (buttonTouched)
				bottomButton.draw(gl, texButtonOver);
			else
				bottomButton.draw(gl, texButtonDef);
		}
		else {
			gl.glTranslatef(1.0f, -3.0f, 1.8f);
			bottomButton.draw(gl, texLoading);
		}
		gl.glPopMatrix();

	}
	
	void DrawElements(GL10 gl) {

		transFactor = 0.0f;
		yVal = 0.0f;
		index = 0;
		yDiff = 0.5f;
		xDiff = 20.0f;
		try {
			for (int i : texturesFront.keySet()) {			
				
				tempAngle = angleLower - transFactor;
				realangle = tempAngle % 360;
				// Save the current matrix
				gl.glPushMatrix();
				gl.glRotatef(realangle, 0, 1, 0);
				gl.glTranslatef(4.0f, yVal, 0);
				gl.glRotatef(-realangle, 0, 1, 0);

				int item = ChooseAndDraw(gl, texturesSelect, texturesFront, texturesBack, i,
						225, 315, 20, touchedFoodTypes);
				if (item > 0){
					if (touchedFoodTypes.contains((Object)item)) {
						ArrayList<Integer> cfoods = foodMapping.getFromFirst(item);
						
						for(int it : cfoods) {
							if (!valueSetSec.containsKey(it)) {
								tempFoodList.add(it);
								threadsFinished = false;
							}
						}
					}
					else {
						
					}
				}
				gl.glPopMatrix();
				index++;
				transFactor += xDiff;
				
				if (index % 18 == 0)
					yVal += -yDiff;
			}

			if (threadsFinished)
				angleLower+=rotateChangeFactorLower;

			transFactor = 0.0f;
			yDiff = 0.5f;
			xDiff = 20.0f;
			yVal = 1.0f;
			for (int i : texturesSecSelect.keySet())
			{
				tempAngle = angleHigher - transFactor;
				realangle = tempAngle % 360;
				// Save the current matrix
				gl.glPushMatrix();
				gl.glRotatef(tempAngle, 0, 1, 0);
				gl.glTranslatef(4.0f, yVal, 1.0f);
				gl.glRotatef(-tempAngle, 0, 1, 0);

				ChooseAndDraw(gl, texturesSecSelect, texturesSecFront, texturesSecBack, i, 225, 360, 40, touchedFoods);
				
				gl.glPopMatrix();
				index++;
				transFactor += xDiff;
				
				if (index % (360 / xDiff) == 0)
					yVal += yDiff;
			}

			if (threadsFinished)
				angleHigher += rotateChangeFactorHigher;
			
			
		} catch (Exception e) {
			String ex=e.getMessage();
		}
	}

	int ChooseAndDraw(GL10 gl,
			HashMap<Integer, Integer> select, 
			HashMap<Integer, Integer> front, 
			HashMap<Integer, Integer> back,
			int index,
			int minAngle,
			int maxAngle,
			int windowDiviation,
			ArrayList<Integer> touched) {

		int temp = -1;
		//create the matrix grabber object in your initialization code  
		MatrixGrabber mg = new MatrixGrabber();  
		mg.getCurrentProjection(gl);  
		projectionMatrix = mg.mProjection;
		mg.getCurrentModelView(gl);
		modelViewMatrix = mg.mModelView;
		
		cModelViewTwo = OneToTwo(modelViewMatrix);
		cModelViewTwo = Transpose(cModelViewTwo);
		current = multiply(cModelViewTwo, verticesArr);
		current = Transpose(current);



		GLU.gluProject(current[0][0], current[0][1], current[0][2], 
				modelViewMatrix, 0, 
				projectionMatrix, 0, 
				viewPort, 0, 
				winTL, 0);
		GLU.gluProject(current[3][0], current[3][1], current[3][2], 
				modelViewMatrix, 0, 
				projectionMatrix, 0, 
				viewPort, 0, 
				winTR, 0);
		GLU.gluProject(current[2][0], current[2][1], current[2][2], 
				modelViewMatrix, 0, 
				projectionMatrix, 0, 
				viewPort, 0, 
				winBR, 0);
		
		windowRealLeft = winTL[0];
		windowRealRight = winTR[0];
		windowRealTop = mHeight - winTL[1];
		windowRealBottom = mHeight - winBR[1];
		
		if ((windowRealLeft >= -windowDiviation || windowRealRight >= -windowDiviation) && (windowRealTop >= -windowDiviation || windowRealBottom >= -windowDiviation)
				&& (windowRealLeft <= mWidth + windowDiviation || windowRealRight <= mWidth + windowDiviation)
				&& (windowRealTop <= mHeight + windowDiviation || windowRealBottom <= mHeight + windowDiviation)){
			
			Boolean touchedOnCurrent = false;// = !touchResolved && touchedX <= windowRealRight + windowDiviation && touchedX >= windowRealLeft - windowDiviation && touchedY <= windowRealTop - windowDiviation && touchedY >= windowRealBottom + windowDiviation;
			
			if (!touchResolved) {
				if (touchedX <= windowRealRight + windowDiviation && touchedX >= windowRealLeft - windowDiviation && touchedY >= windowRealTop - windowDiviation && touchedY <= windowRealBottom + windowDiviation) {
					touchedOnCurrent = true;
					touchResolved = true;
				}
			}
				
			
			if (!touched.contains(index)) {
				if (realangle < maxAngle && realangle > minAngle) {
					if (touchedOnCurrent) {
						square.draw(gl, select.get(index)); // 
						touched.add(index);
						temp =index;
					}
					else {
						square.draw(gl, front.get(index));
					}
				}
				else
					square.draw(gl, back.get(index));
			}
			else {
				if (touchedOnCurrent && realangle < maxAngle && realangle > minAngle) {
					square.draw(gl, front.get(index));
					touched.remove((Object)index);
					temp = index;
				}
				else
					square.draw(gl, select.get(index));
			}
		}
		
		return temp;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
     
		viewPort = new int[]{0, 0, width, height};
        mHeight = height;
        mWidth = width;
        
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}

		
	public void AddToLists(String value)
	{
		texturesFront.put(cVal, GenerateBitmapTexture(currentGL, value,colorNormal, 90));
		texturesBack.put(cVal, GenerateBitmapTexture(currentGL, value,colorBack, 90));
		texturesSelect.put(cVal, GenerateBitmapTexture(currentGL, value,colorSelect, 90));
		
		cVal++;
	}
	
	
	class ClearGLSurfaceView extends GLSurfaceView  implements android.opengl.GLSurfaceView.GLWrapper{    
		public ClearGLSurfaceView(Context context) {        
			super(context);               
			//setRenderer(TagCloud.this);    
		}    
		
		public boolean onTouchEvent(final MotionEvent e) {   
			if (threadsFinished) {
				final float x = e.getX();
				final float y = e.getY(); 
				final long time = e.getEventTime();
				final float relativeY = e.getY() - (getHeight() / 2);
				switch (e.getAction()) {	
					case MotionEvent.ACTION_DOWN:
						if ((x > getWidth() - 75) && (y > getHeight() - 50))
							buttonTouched = true;
						break;
					case MotionEvent.ACTION_UP:
						if (!buttonTouched) {
							if (!moveHappen) {
								if (relativeY > 0)
									rotateChangeFactorLower = 0;
								else
									rotateChangeFactorHigher = 0;
							}
							moveHappen = false;
						}
						else {
							buttonTouched = false;
							if ((x > getWidth() - 75) && (y > getHeight() - 50)) {
								ArrayList<String> foods = new ArrayList<String>();
								for (Integer tex : touchedFoods) {
									foods.add(valueSetSec.get(tex));
								}
								setSelectedFoods(foods);
								setContentView(R.layout.home);
							}
						}
						break;
					case MotionEvent.ACTION_MOVE:
						if (!buttonTouched) {
							final float dx = x - mPreviousX;
							final long dtime = time - mPreviousTime;
							final float speed = dx / (float)dtime;
							final float dy = y - mPreviousY; 
							queueEvent(new Runnable(){
								public void run() {
									if (relativeY > 0) {
										if (dx < 0) {
											if (rotateChangeFactorLower > 0){
												rotateChangeFactorLower=-0.2f;
												rotateChangeFactorHigher=-0.2f;
											}
										}
										else {
											if (rotateChangeFactorLower < 0){
												rotateChangeFactorLower=0.2f;
												rotateChangeFactorHigher=0.2f;
											}
										}
										angleLower+=speed/3.0f;
										angleHigher+=speed/3.0f;
										if (rotateChangeFactorLower < 2.0f 
												&& rotateChangeFactorLower > -2.0f){
											rotateChangeFactorLower+=speed / 30.0f;
											rotateChangeFactorHigher+=speed / 30.0f;
										}
									} else {
										if (dx < 0) {
											if (rotateChangeFactorHigher > 0){
												rotateChangeFactorHigher=-0.2f;
												rotateChangeFactorLower=-0.2f;
											}
										}
										else {
											if (rotateChangeFactorHigher < 0){
												rotateChangeFactorHigher=0.2f;
												rotateChangeFactorLower=0.2f;
											}
										}
										angleLower+=speed/3.0f;
										angleHigher+=speed/3.0f;
										
		
										if (rotateChangeFactorHigher < 2.0f 
												&& rotateChangeFactorHigher > -2.0f){
											rotateChangeFactorHigher+=speed / 30.0f;
											rotateChangeFactorLower+=speed / 30.0f;
										}
									}
															
									//AddToLists(String.valueOf(yChange));
									//double rad = Math.asin(Double.parseDouble(String.valueOf(((getWidth() / 2) - e.getX()) / 768.0f)));
									
									//degreeTouch = ((float)Math.toDegrees(rad)) * -1.0f + 255.0f;
									/*float matX = ((getWidth() / 2.0f) - x) * -1.0f;
									float matY = (getHeight() / 2.0f) - y;
									degreeTouch = matY / matX;
									
									yChange = ((getHeight() / 2) - e.getY()) / 96.0f;*/
									if (time - touchedTime > 500 || (touchedX - x > 10 && touchedY - y > 10) || (touchedX - x < -10 && touchedY - y < -10)) {
										touchedX = x;
										touchedY = y;
										touchResolved = false;
										touchedTime = time;
									}
								}
							});
							moveHappen = true;
						}
						break;
						
				}
	
				
				requestRender();
				mPreviousX = x;
				mPreviousY = y;
				mPreviousTime = time;
				return true;
			}
			else 
				return false;
		}
		
		private boolean moveHappen = false;
		private float mPreviousX;    
		private float mPreviousY;
		private long mPreviousTime;
		
		@Override
		public GL wrap(GL gl) {
			return new MatrixTrackingGL(gl); 
		}
	}

	public static float[][] OneToTwo(float[] matrix){
		int len = (int)Math.sqrt((double)matrix.length);
		float[][] temp = new float[matrix.length / len][len];
		
		int row = -1;
		int col = 0;
		for (int i = 0; i < matrix.length; ++i){
			if (i % len == 0){
				++row;
				col = 0;
			}
			
			temp[row][col] = matrix[i];
			++col;
		}
		return temp;
	}

	  public static float[][] multiply(float[][] m1, float[][] m2) {
		    int m1rows = m1.length;
		    int m1cols = m1[0].length;
		    int m2rows = m2.length;
		    int m2cols = m2[0].length;
		    if (m1cols != m2rows)
		      throw new IllegalArgumentException("matrices don't match: " + m1cols + " != " + m2rows);
		    float[][] result = new float[m1rows][m2cols];

		    // multiply
		    for (int i=0; i<m1rows; i++)
		      for (int j=0; j<m2cols; j++)
		        for (int k=0; k<m1cols; k++)
		        result[i][j] += m1[i][k] * m2[k][j];

		    return result;
	  }
	  public static float[][] Transpose(float[][] m){
		  float[][] ans = new float[m[0].length][m.length];
          for(int rows = 0; rows < m.length; rows++){
                  for(int cols = 0; cols < m[0].length; cols++){
                          ans[cols][rows] = m[rows][cols];
                  }
          }
          
          return ans;
	  }
	  
	public void setSelectedFoods(ArrayList<String> foods){
		
		Favatar.change(120);
    	this.finish();
    	
	}
}





class FavHash<A, B, C, D> {
	HashMap<A, B> mapb = new HashMap<A, B>();
	HashMap<A, C> mapc = new HashMap<A, C>();
	HashMap<A, D> mapd = new HashMap<A, D>();
	
	public FavHash(){}
	
	public void put(A key, B val0, C val1, D val2) {
		mapb.put(key, val0);
		mapc.put(key, val1);
		mapd.put(key, val2);
	}
	
	public B get(A key){
		return mapb.get(key);
	}
	
	public Boolean containsKey(A key){
		return mapb.containsKey(key);
	}
}