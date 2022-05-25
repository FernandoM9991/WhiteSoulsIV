/* Game Class Starter File
 * Last Edit: 5/6/2021
 * Author: _____________________
 */

public class Game {

  private Grid grid;
  private int userRow;
  private int userCol;
  private int getCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String userPic = "images/Knight.png";
  private String getPic = "images/potion.png";
private String skullEnemy = "images/skull.png";

  public Game() {

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
    if(key == 83 && userRow != grid.getNumRows())
     {
      userRow++;

    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow-1,userCol);
    grid.setImage(prev, null);
  }

//if I push "d" it goes right
if(key == 68 && userCol < grid.getNumCols())
{
    userCol++;

Location next = new Location(userRow, userCol);
grid.setImage(next,userPic);
Location prev = new Location(userRow,userCol-1);
grid.setImage(prev, null);
}

//if I press "a" it goes left
if(key == 65 && userCol!= grid.getNumCols() )
{
  userCol--;

 Location next = new Location(userRow, userCol);
 grid.setImage(next,userPic);
 Location prev = new Location(userRow,userCol+1);
 grid.setImage(prev, null);
    }

    //if I push up arrow, then plane goes up
    if(key == 38 && userRow != grid.getNumRows())
    { 
        userRow--; 
     
    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow+1,userCol);
    grid.setImage(prev, null);
    }
    
    //if I push down arrow, then plane goes down
    if(key == 40 && userRow != grid.getNumRows())
    { 
        userRow++; 
     
    Location next = new Location(userRow, userCol);
    grid.setImage(next,userPic);
    Location prev = new Location(userRow-1,userCol);
    grid.setImage(prev, null);
    }
    
//if I push right arrow it shifts right
if(key == 39 && userCol!= grid.getNumCols() )
{
    userCol++;

Location next = new Location(userRow, userCol);
grid.setImage(next,userPic);
Location prev = new Location(userRow,userCol-1);
grid.setImage(prev, null);
}

//if I push left arrow it shifts left
if(key == 37 && userCol!= grid.getNumCols() )
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
      grid.setImage(loc, this.getPic);
    }

  }

  }

  

  public void scrollLeft(){
//shifts potion image to left
      //look at right col and left col
       int lastCol = grid.getNumCols()-1;
       int lastRow = grid.getNumRows()-1;
       
       for(int r = 0; r < lastRow; r++){
       for(int c = 1; c< lastCol; c++){
        Location loc = new Location(r,c);
        Location newLoc = new Location(r, c-1);

        String img = grid.getImage(loc);

        if(img != null)
        {
          grid.setImage(newLoc, skullEnemy);
          grid.setImage(loc, null);
        }

        if()
        {

        }
      }
    }
      //move items from right to left

  }

  
  public void handleCollision(Location loc) {

  }
  
  public int getScore() {
    return 0;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver() {
    return false;
  }
    

}