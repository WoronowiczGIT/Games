package sample;

import java.util.Random;

public class Move {
    int[][] grid = new int[50][50];

    Random rng = new Random();

    public void generateGrid(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int x = rng.nextInt(10);
                grid[i][j]=0;
                if(x >7){grid[i][j]=2;}
                //if(x <3){grid[i][j]=3;}
            }
        }
    }


    public String drawMap(){
        String map ="";
        for (int[] row: grid
             ) {
            for (int field: row
                 ) {
                if(field == 0){map+="_ ";}
                if(field == 1){map+="G";}
                if(field == 2){map+="X";}
                if(field == 3){map+="M";}

            }
            map+="\n";
        }
        return map;
    }


    public void up(){
        int x=getPosition()[1];
        int y=getPosition()[0];
        if(y>0 && grid[y-1][x]== 0){
            this.grid[y-1][x]= 1;
            this.grid[y][x]=0;
        }
    }
    public void down(){
        int x=getPosition()[1];
        int y=getPosition()[0];
        if(y<grid.length-1 && grid[y+1][x]== 0){
            this.grid[y+1][x]= 1;
            this.grid[y][x]=0;
        }
    }
    public void right(){
        int x=getPosition()[1];
        int y=getPosition()[0];
        if(x<grid.length-1 && grid[y][x+1]== 0){
            this.grid[y][x+1]= 1;
            this.grid[y][x]=0;
        }
    }
    public void left(){
        int x=getPosition()[1];
        int y=getPosition()[0];
        if(x>0 && grid[y][x-1]== 0){
            this.grid[y][x-1]= 1;
            this.grid[y][x]=0;
        }
    }

    public int[] getPosition(){
        int[] resault = new int[2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if(grid[i][j]==1){
                   resault[0]=i;
                   resault[1]=j;

                }
            }
        }
        return resault;
    }
}
