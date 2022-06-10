/* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */
//hi
public class Game {

  private Grid grid;
  private int userRow;
  private int userCol;
  private int getCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String userPic = "images/Knight.png";
  private String redWaterPic = "images/potion.png";
  private String skullEnemy = "images/skull.png";
  private int skullRow;
  private int skullCol;
  private int potionRow;
  private int potionCol;
  private int health = 10;
  private boolean isDead = false;

  public Game() {
    WavPlayer.play("Sounds/hauntedCaverns.wav");
    grid = new Grid(10, 10);
    userRow = 3;
    userCol = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
   
    grid.setImage(new Location(userRow, userCol), userPic);
  }
  
  public void play() {

    while (!isGameOver()) {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0) {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress(){

    //check last key pressed
    int key = grid.checkLastKeyPressed();
    System.out.println(key);

    //set "w" key to move the plane up/check case where out of bounds
    if(key == 87 && userRow != 0)
    {
        //change the field for userrow
        userRow--;
    
            //shift the user picture up in the array
            Location next = new Location(userRow, userCol);
            grid.setImage(next,userPic);
            
            Location prev = new Location(userRow+1,userCol);
            grid.setImage(prev, null);
    }

    //if I press "s" it goes down
    if(key == 83 && userRow != grid.getNumRows()-1)
     {
      userRow++;

    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow-1,userCol);
    grid.setImage(prev, null);
  }

//if I push "d" it goes right
if(key == 68 && userCol < grid.getNumCols()-1)
{
    userCol++;

Location next = new Location(userRow, userCol);
grid.setImage(next,userPic);
Location prev = new Location(userRow,userCol-1);
grid.setImage(prev, null);
}

//if I press "a" it goes left
if(key == 65 && userCol != 0)
{
  userCol--;

 Location next = new Location(userRow, userCol);
 grid.setImage(next,userPic);
 Location prev = new Location(userRow,userCol+1);
 grid.setImage(prev, null);
    }

    //if I push up arrow, then plane goes up
    if(key == 38 && userRow != 0)
    { 
        userRow--; 
     
    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow+1,userCol);
    grid.setImage(prev, null);
    }
    
    //if I push down arrow, then plane goes down
    if(key == 40 && userRow != grid.getNumRows()-1)
    { 
        userRow++; 
     
    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow-1,userCol);
    grid.setImage(prev, null);
    }
    
//if I push right arrow it shifts right
if(key == 39 && userCol!= grid.getNumCols()-1)
{
    userCol++;

Location next = new Location(userRow, userCol);
grid.setImage(next,userPic);
Location prev = new Location(userRow,userCol-1);
grid.setImage(prev, null);
}

//if I push left arrow it shifts left
if(key == 37 && userCol!= 0)
{
  userCol--;

 Location next = new Location(userRow, userCol);
 grid.setImage(next,userPic);
 Location prev = new Location(userRow,userCol+1);
 grid.setImage(prev, null);
    }

  }

  

  
  
  
   public void populateRightEdge(){
   //loop through last column
   int lastCol = grid.getNumCols()-1;
   int lastRow = grid.getNumRows()-1;
  for(int r = 0; r < lastRow; r++)
  {
   
    //find loc for each cell in last row
   Location loc = new Location(r, lastCol);
   
   //get random number to choose if obj appears
   double rng = Math.random();
   double rate = 0.01;
   // decide if object should appear
    if(rng < rate){
      grid.setImage(loc, this.redWaterPic);
    }

    Location skullLoc = new Location(r, lastCol);

    double skullRng = Math.random();
    double skullRate = 0.05;

    if(skullRng < skullRate){
      grid.setImage(skullLoc, this.skullEnemy);
    }


  }

  }

  

  public void scrollLeft(){
    //shifts potion image to left
          //look at right col and left col
           int lastCol = grid.getNumCols()-1;
           int lastRow = grid.getNumRows()-1;
        
  for(int r = 0; r <= lastRow; r++){           
    for(int c = 1; c <= lastCol; c++){
            Location loc = new Location(r,c);
            Location newLoc = new Location(r, c-1);

            String img = grid.getImage(loc);
            String newImg = grid.getImage(newLoc);
            // System.out.println(loc + img);
  
            if(skullEnemy.equals(img))
            {
              grid.setImage(newLoc, skullEnemy);
              grid.setImage(loc, null);
              skullRow = newLoc.getRow();
              skullCol = newLoc.getCol();
              if(userRow == skullRow && userCol == skullCol)
              handleCollision(newLoc);
            }



            if(redWaterPic.equals(img))
            {
              grid.setImage(newLoc, redWaterPic);
              grid.setImage(loc, null);
              potionRow = newLoc.getRow();
              potionCol = newLoc.getCol();
             if(userRow == potionRow && userCol == potionCol)
              heal(newLoc);
            }
            if(newLoc.getCol() <= 0)
            {
             grid.setImage(newLoc, null);
            }
            
            }
          //move items from right to left
          grid.setImage(new Location(userRow, userCol), userPic);
        
          }
       
        }



      
    
  
  public void handleCollision(Location loc) {
      health -= 1;
      System.out.println(health);
  }

  public void heal(Location loc)  {
    if(health<10)
    health++;
    System.out.println(health);
  }

  public int getScore() {
    return health;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver() {

      
    if(health == 0 ){
        
      isDead = true;

    }
    
    return isDead;
  }
    

}
