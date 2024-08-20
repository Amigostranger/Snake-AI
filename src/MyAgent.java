import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import za.ac.wits.snake.DevelopmentAgent;
// import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent  extends DevelopmentAgent {

     public static void main(String args[]) {
         MyAgent agent = new MyAgent();
         MyAgent.start(agent, args);
     }

    // @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);

            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                String apple1 = line;
                
                //do stuff with apples
                String[] lock=apple1.split(" ");
                int Applex=Integer.parseInt(lock[0]);
                int Appley=Integer.parseInt(lock[1]);
                
                int headx=0;
                int heady=0;
                int tailx=0;
                int taily=0;

                ArrayList<String> follow = new ArrayList<String>();

                int numRows = Integer.parseInt(temp[2]);
                int numCols = Integer.parseInt(temp[1]);
                String[][] grid = new String[numRows][numCols];

                for (int r = 0; r < numRows; r++) {
                    for (int c = 0; c < numCols; c++) {
                        grid[r][c] = "0";
                    }
                }
                grid[Appley][Applex] = "G";

                // read in obstacles and do something with them!
                int nObstacles = 3;
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    /// do something with obs
                    drawSnake(grid,"*", obs);
                }

                String[] body=null;
                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    String[]DevSnake=snakeLine.split(" ",4);
                    if(DevSnake[0].equals("dead")){
                    	continue;
                    }
                    else {
                        if (i == mySnakeNum) {
                            //hey! That's me :)
                            body = DevSnake[3].split(" ");
                        	String head = body[0];
                        	String tail = body[body.length-1];
                            
                        	tailx =Integer.parseInt(split(tail,0));
                        	taily =Integer.parseInt(split(tail,1));
                        	headx=Integer.parseInt(split(head,0));
                        	heady=Integer.parseInt(split(head,1));
                        	drawSnake(grid,"*", DevSnake[3]);
                        	continue;
                        }
                        //do stuff with other snakes
                        
                       drawSnake(grid,"*", DevSnake[3]);
                       String[] b = DevSnake[3].split(" ");
                       String thoho = b[0];
                       String tail = b[b.length-1];
                       setHead(grid,thoho);
                    }

                }
                BFS(grid,body,Applex,Appley);
                //grid[heady][headx] = "S";
                //finished reading, calculate move:
                //int move = new Random().nextInt(4);
                //System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   public void randomMove(String[][] grid,String head) {
   	
   	int row=grid.length;
   	int col=grid[0].length;
   	String []pack=head.split(",");
   	int curry=Integer.parseInt(pack[1]);
    int currx=Integer.parseInt(pack[0]);
       if(curry+1<row && !grid[curry+1][currx].equals("*")){
           
               
               System.out.println(1);//down
               
         
       }
       
       else if(currx>0 && !grid[curry][currx-1].equals("*")){
         
               
               	System.out.println(2);//left
              
          // } 
       }
       

       
       else if(curry>0 && !grid[curry-1][currx].equals("*")){
        
           
               		System.out.println(0);//up
               
          
       }
       
       else if(currx+1<col && !grid[curry][currx+1].equals("*")){
             
               	System.out.println(3);//right
               
           }
       else {
    	   System.out.println("log"+"straight");
    	   System.out.println(5);
       }
       
   }
    
    
    public void setHead(String[][] grid,String head) {
    	
    	
    	int row=grid.length;
    	int col=grid[0].length;
    	String []pack=head.split(",");
    	int curry=Integer.parseInt(pack[1]);
        int currx=Integer.parseInt(pack[0]);
        if(curry+1<row){
            //if(distanceArray[curry+1][currx]==-5){
                if(!grid[curry+1][currx].equals("*")  ){
                	//grid[curry+1][currx]="*";
                	grid[curry+1][currx]="*";//+grid[curry+1][currx];
                }
                
          //  }
        }
        
        if(currx>0){
          //  if(distanceArray[curry][currx-1]==-5){
                if(!grid[curry][currx-1].equals("*")){
                	grid[curry][currx-1]="*";//+grid[curry][currx-1];
                }///
           // } 
        }
        //System.out.println(q.peek());

        
        if(curry>0){
         //   if(distanceArray[curry-1][currx]==-5){
                if(!grid[curry-1][currx].equals("*") ){
                	grid[curry-1][currx]="*";//+grid[curry-1][currx];
                }//
           // }
        }
        
        if(currx+1<col){
           // if(distanceArray[curry][currx+1]==-5  ){
                if(!grid[curry][currx+1].equals("*") ){
                	grid[curry][currx+1]="*";//;+grid[curry][currx+1];
                }
          //  }
        } 
    }

    public String split(String n,int at) {
    	String[] pack = n.split(",");
    	return pack[at];
    }
   
    public void drawSnake(String[][] board, String snakeNumber, String snakeInfo) {
        String[] points = snakeInfo.split(" ");
        for (int i = 0; i < points.length - 1; i++) {
            String[] point1 = points[i].split(",");
            String[] point2 = points[i + 1].split(",");
            int x1 = Integer.parseInt(point1[0]);
            int y1 = Integer.parseInt(point1[1]);
            int x2 = Integer.parseInt(point2[0]);
            int y2 = Integer.parseInt(point2[1]);
            
            drawLine(board, snakeNumber, x1, y1, x2, y2);
        }
    }

    public void drawLine(String[][] board, String snakeNumber, int x1, int y1, int x2, int y2) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                board[y][x] = snakeNumber;
            }
        }
    }

    public void tail(String[][] grid,int headx,int heady,int Applex,int Appley) {
    	Queue<String> q = new LinkedList<>();
    	int row=grid.length;
    	int col=grid[0].length;
        int[][] distanceArray = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distanceArray[i][j] = -5;
            }
        }
        String[][] ParentArray = new String[row][col];
         for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ParentArray[i][j] = "-3,-3";
            }
        }
       String S=headx+","+heady;
       String G=Applex+","+Appley;
       String nn=null;
        q.add(S);
        
        while(!q.isEmpty() && distanceArray[Appley][Applex]==-5 ){
        
            String[] curr=q.peek().split(",");
            int curry=Integer.parseInt(curr[1]);
            int currx=Integer.parseInt(curr[0]);
            //System.out.println(q.peek());
            // System.out.println(c);
            String vv=q.peek();
            
            q.poll();
            if(curry+1<row){
                if(distanceArray[curry+1][currx]==-5){
                    if(!grid[curry+1][currx].equals("*")){
                        distanceArray[curry+1][currx]=distanceArray[curry][currx]+1;
                        ParentArray[curry+1][currx]=vv;

                        q.add(toString(curry+1,currx));
                    }
                    
                }
            }
            //System.out.println(q.peek());
            if(currx>0){
                if(distanceArray[curry][currx-1]==-5){
                    if(!grid[curry][currx-1].equals("*")){
                        distanceArray[curry][currx-1]=distanceArray[curry][currx]+1;
                        ParentArray[curry][currx-1]=vv;

                        q.add(toString(curry,currx-1));
                    }///
                } 
            }
            //System.out.println(q.peek());

            
            if(curry>0){
                if(distanceArray[curry-1][currx]==-5){
                    if(!grid[curry-1][currx].equals("*")){
                        distanceArray[curry-1][currx]=distanceArray[curry][currx]+1;
                        ParentArray[curry-1][currx]=vv;

                        q.add(toString(curry-1,currx));
                    }//
                }
            }
            
            if(currx+1<col){
                if(distanceArray[curry][currx+1]==-5  ){
                    if(!grid[curry][currx+1].equals("*")){
                        distanceArray[curry][currx+1]=distanceArray[curry][currx]+1;
                        ParentArray[curry][currx+1]=vv;

                        q.add(toString(curry,currx+1));
                    }
                }
            } 
            if(q.isEmpty()) {
            	nn=vv;
            }
        }

        ArrayList<String> out=new ArrayList<String>();
        String curr = G;
        
        String[] nne=S.split(",");
        String[] kk=nn.split(",");
        int fx=Integer.parseInt(kk[0]);
        int fy=Integer.parseInt(kk[1]);
        if(q.isEmpty()) {
        	//bfs(grid,body,fx,fy)
        	net(grid,headx,heady,fx,fy);
        	//randomMove(grid,S);
        	return;
        }
        
        while(!curr.equals(S)){
            out.add(curr);
           // System.out.println("log"+curr);
            String[] pack=curr.split(",");
            int currx=Integer.parseInt(pack[0]);
            int curry=Integer.parseInt(pack[1]);
           // System.out.println("log"+currx+","+curry);
            curr=ParentArray[curry][currx];
            //System.out.println("log"curr);
        }

        if (!out.isEmpty()) {
            int x=headx;
        	int y=heady;
            String[] pack=out.get(out.size()-1).split(",");
            int nextX=Integer.parseInt(pack[0]);
            int nextY=Integer.parseInt(pack[1]);
        	//System.out.print("log"+pack[0]+","+pack[1]+" "+Sx+","+Sy);
        	
        	if(y==nextY) {
        		if(x<nextX) {
        			//right
        			System.out.println(3);
        			//System.out.print("log"+"right");
        		}
        		else if(x>nextX) {
        			//left
        			System.out.println(2);
        			//System.out.print("log"+"left");
        		}
        	}
        	else if(x==nextX) {
        		if(y<nextY) {
        			//up
        			System.out.println(1);
        			//System.out.print("log"+"down");
        		}
        		else if(y>nextY) {
        			//down
        			System.out.println(0);
        			//System.out.print("log"+"up");
        		}
        	}
        	
        }
        else {
        	String[] check=out.get(out.size()-1).split(",");
        	//System.out.print("log "+pack[0]+","+pack[1]);
        	//int nextX=Integer.parseInt(pack[0])
        	//System.out.print("log"+"i am empty");
        }
    }
    public void net(String[][] grid,int headx,int heady,int lastx,int lasty ) {
    	Queue<String> q = new LinkedList<>();
    	int row=grid.length;
    	int col=grid[0].length;
        int[][] distanceArray = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distanceArray[i][j] = -5;
            }
        }
        
        String[][] ParentArray = new String[row][col];
        for (int i = 0; i < row; i++) {
           for (int j = 0; j < col; j++) {
               ParentArray[i][j] = "-3,-3";
           }
       }
      String S=headx+","+heady;
      String G=lastx+","+lasty;
       q.add(S);
       while(!q.isEmpty() && distanceArray[lasty][lastx]==-5 ){
           
           String[] curr=q.peek().split(",");
           int curry=Integer.parseInt(curr[1]);
           int currx=Integer.parseInt(curr[0]);
           //System.out.println(q.peek());
           // System.out.println(c);
           String vv=q.peek();

           q.poll();
           if(curry+1<row){
               if(distanceArray[curry+1][currx]==-5){
                   if(!grid[curry+1][currx].equals("*")){
                       distanceArray[curry+1][currx]=distanceArray[curry][currx]+1;
                       ParentArray[curry+1][currx]=vv;

                       q.add(toString(curry+1,currx));
                   }
                   
               }
           }
           //System.out.println(q.peek());
           if(currx>0){
               if(distanceArray[curry][currx-1]==-5){
                   if(!grid[curry][currx-1].equals("*")){
                       distanceArray[curry][currx-1]=distanceArray[curry][currx]+1;
                       ParentArray[curry][currx-1]=vv;

                       q.add(toString(curry,currx-1));
                   }///
               } 
           }
           //System.out.println(q.peek());

           
           if(curry>0){
               if(distanceArray[curry-1][currx]==-5){
                   if(!grid[curry-1][currx].equals("*")){
                       distanceArray[curry-1][currx]=distanceArray[curry][currx]+1;
                       ParentArray[curry-1][currx]=vv;

                       q.add(toString(curry-1,currx));
                   }//
               }
           }
           
           if(currx+1<col){
               if(distanceArray[curry][currx+1]==-5  ){
                   if(!grid[curry][currx+1].equals("*")){
                       distanceArray[curry][currx+1]=distanceArray[curry][currx]+1;
                       ParentArray[curry][currx+1]=vv;

                       q.add(toString(curry,currx+1));
                   }
               }
           }  
       }
       ArrayList<String> out=new ArrayList<String>();
       String curr = G;
       String[] nne=S.split(",");
       if(q.isEmpty()) {
       	randomMove(grid,S);
       	return;
       }
       while(!curr.equals(S)){
           out.add(curr);
          // System.out.println("log"+curr);
           String[] pack=curr.split(",");
           int currx=Integer.parseInt(pack[0]);
           int curry=Integer.parseInt(pack[1]);
          // System.out.println("log"+currx+","+curry);
           curr=ParentArray[curry][currx];
           //System.out.println("log"curr);
       }
       if (!out.isEmpty()) {
           int x=headx;
       	int y=heady;
           String[] pack=out.get(out.size()-1).split(",");
           int nextX=Integer.parseInt(pack[0]);
           int nextY=Integer.parseInt(pack[1]);
       	//System.out.print("log"+pack[0]+","+pack[1]+" "+Sx+","+Sy);
       	
       	if(y==nextY) {
       		if(x<nextX) {
       			//right
       			System.out.println(3);
       			//System.out.print("log"+"right");
       		}
       		else if(x>nextX) {
       			//left
       			System.out.println(2);
       			//System.out.print("log"+"left");
       		}
       	}
       	else if(x==nextX) {
       		if(y<nextY) {
       			//up
       			System.out.println(1);
       			//System.out.print("log"+"down");
       		}
       		else if(y>nextY) {
       			//down
       			System.out.println(0);
       			//System.out.print("log"+"up");
       		}
       	}
       	
       }
       
    }
    

    public void BFS(String[][] grid,String[] body,int Applex,int Appley){
    	
    	
    	String head = body[0];
        Queue<String> q = new LinkedList<>();
    	int row=grid.length;
    	int col=grid[0].length;
    	String tail = body[body.length-1];
    	int headx=Integer.parseInt(split(head,0));
    	int heady=Integer.parseInt(split(head,1));
    	int tailx =Integer.parseInt(split(tail,0));
    	int taily =Integer.parseInt(split(tail,1));
    	
    	
        int[][] distanceArray = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                distanceArray[i][j] = -5;
            }
        }
        String[][] ParentArray = new String[row][col];
         for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ParentArray[i][j] = "-3,-3";
            }
        }
       String S=headx+","+heady;
       String G=Applex+","+Appley;
        q.add(S);
        while(!q.isEmpty() && distanceArray[Appley][Applex]==-5 ){
        
            String[] curr=q.peek().split(",");
            int curry=Integer.parseInt(curr[1]);
            int currx=Integer.parseInt(curr[0]);
            //System.out.println(q.peek());
            // System.out.println(c);
            String vv=q.peek();

            q.poll();
            if(curry+1<row){
                if(distanceArray[curry+1][currx]==-5){
                    if(!grid[curry+1][currx].equals("*")){
                        distanceArray[curry+1][currx]=distanceArray[curry][currx]+1;
                        ParentArray[curry+1][currx]=vv;

                        q.add(toString(curry+1,currx));
                    }
                    
                }
            }
            //System.out.println(q.peek());
            if(currx>0){
                if(distanceArray[curry][currx-1]==-5){
                    if(!grid[curry][currx-1].equals("*")){
                        distanceArray[curry][currx-1]=distanceArray[curry][currx]+1;
                        ParentArray[curry][currx-1]=vv;

                        q.add(toString(curry,currx-1));
                    }///
                } 
            }
            //System.out.println(q.peek());

            
            if(curry>0){
                if(distanceArray[curry-1][currx]==-5){
                    if(!grid[curry-1][currx].equals("*")){
                        distanceArray[curry-1][currx]=distanceArray[curry][currx]+1;
                        ParentArray[curry-1][currx]=vv;

                        q.add(toString(curry-1,currx));
                    }//
                }
            }
            
            if(currx+1<col){
                if(distanceArray[curry][currx+1]==-5  ){
                    if(!grid[curry][currx+1].equals("*")){
                        distanceArray[curry][currx+1]=distanceArray[curry][currx]+1;
                        ParentArray[curry][currx+1]=vv;

                        q.add(toString(curry,currx+1));
                    }
                }
            }  
        }

//        String [] rwani=G.split(",");
//        int dx=Integer.parseInt(rwani[0]);
//        int dy=Integer.parseInt(rwani[1]);

        
        ArrayList<String> out=new ArrayList<String>();
        String curr = G;
        String[] nne=S.split(",");
        
        if(q.isEmpty()) {

        	tail(grid,headx,heady,tailx,taily);
        	//randomMove(grid,S);
        	return;
        }
        
        while(!curr.equals(S)){
            out.add(curr);
           // System.out.println("log"+curr);
            String[] pack=curr.split(",");
            int currx=Integer.parseInt(pack[0]);
            int curry=Integer.parseInt(pack[1]);
           // System.out.println("log"+currx+","+curry);
            curr=ParentArray[curry][currx];
            //System.out.println("log"curr);
        }

        if (!out.isEmpty()) {
            int x=headx;
        	int y=heady;
            String[] pack=out.get(out.size()-1).split(",");
            int nextX=Integer.parseInt(pack[0]);
            int nextY=Integer.parseInt(pack[1]);
        	//System.out.print("log"+pack[0]+","+pack[1]+" "+Sx+","+Sy);
        	
        	if(y==nextY) {
        		if(x<nextX) {
        			//right
        			System.out.println(3);
        		}
        		else if(x>nextX) {
        			//left
        			System.out.println(2);
        		}
        	}
        	else if(x==nextX) {
        		if(y<nextY) {
        			System.out.println(1);
        		}
        		else if(y>nextY) {
        			System.out.println(0);
        		}
        	}
        	
        }
    }

    public static String toString(int y,int x){
    String n=x+","+y;
    
    return n;
    }


}