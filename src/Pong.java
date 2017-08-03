import Corrupted_ProceduralAPI.CorruptedProceduralAPI;

public class Pong extends CorruptedProceduralAPI
{
        int y = 4;              //Character initial Y coordinate
        
        // Bot
        int yBot = 4;           //Bot Initial Coordenate
        int vectorBot = 1;      //Bot Initial vector
        int difficulty = 4;     //Bot difficulty. From 1 to 10 where ten is the hardest
        
        // Ball Coordenates
        int yBall = 1;
        int xBall = 1;
        // Ball vector
        int yVector = 1;
        int xVector = 1;
        
        //FPS
        int fps = 6;            //Game Speed, the lowest the fastest
        int cfps = 0;
       
        
        public void moveCharacter(int direction){
            // Boundaries check
            if( ( (y+direction) >= 0 ) && ( (y+direction) <= 9 ) ){
                markTileForDelete(0,y);
                deleteMatchingTiles();
                y = y+direction;
                drawTile(0,y,"blue");
            }            
        }
        
        public void moveBot(){
            markTileForDelete(25,yBot);
            deleteMatchingTiles();
            
            // Generate Randomness
            int r1 = (int) (Math.random() * difficulty);
            int r2 = (int) (Math.random() * difficulty);
            // Randomize vector direction
            int rDir = (int) (Math.random() * 2);
            
            // Check the randomness
            if(r1 == r2){
                if( rDir == 0 ){
                    yBot = yBall + 1;
                }else{
                    yBot = yBall - 1;
                }
            }else{
                yBot = yBall;
            }     
            
            drawTile(25,yBot,"green");
        }
        
        public void moveBall(){
            /* If a collision is detected the vector values will be changed   */
            
            // Vertical Collision
            if( isOccupied(xBall, (yBall + yVector)) ){
                yVector = yVector * -1;
            }
           
            // Horizontal collision
            if( isOccupied((xBall + xVector),yBall) ){
                xVector = xVector * -1;
            }
            
            // 2D collision
            if( isOccupied((xBall + xVector),(yBall + yVector)) ){
                xVector = xVector * -1;
                yVector = yVector * -1;
            }
            
            /* Repaint ball */
            
            // Current position is deleted
            markTileForDelete(xBall,yBall);
            deleteMatchingTiles();
            
            // Change the coordinate
            yBall = yBall+yVector;
            xBall = xBall+xVector;
            
            /* Score dealing */
            
            if(xBall==0){
                spawnBall();
                //loseGame();
            }else if(xBall == 25){
                spawnBall();
                //winGame();
            }else{
                drawTile(xBall,yBall,"yellow");
            }
        }
        
        public void spawnBall(){
            xBall = 12;
            yBall = 1 + ( (int) (Math.random() * 7));
            
            int vr = (int) (Math.random() * 2);
            
            if(vr==0){
                xVector = -1;
            }else{
                xVector = 1;
            }
        }
        
        public void buildGame()
        {
            
            // Boundaries
            for(int i=1; i<25; i++){
                drawTile(i,9,"red");
                drawTile(i,0,"red");
            }
                        
            //Character
            drawTile(0,y,"blue");
            
            //Ball
            drawTile(xBall,yBall,"yellow");
            
            // Bot
            drawTile(25,yBot,"green");        
        }
        
        public void updateGame()
        {
            if(cfps == fps){
                cfps = 0;
                // Call functions
                moveBall();
                moveBot();
            }else{
                cfps++;
            }
            if(pressingUp()){                
                moveCharacter(1);
            }
            if(pressingDown()){                
                moveCharacter(-1);                
            }           
        }
}