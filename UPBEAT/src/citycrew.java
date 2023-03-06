import java.util.LinkedList;

public class citycrew {
    Box imhere;
    Box box = new Box(0, 0);
    Box box1 = new Box(1,1);
    int x = 0;
    int y = 0;
    public void movecrew(int x, int y){
        this.x = x;
        this.y = y;
       imhere = new Box(x,y);

    }
    public void returntobase(){
       imhere.row = 0;
       imhere.column = 0;
    }
    public  void whereImi(){
        System.out.println(imhere.row+" , "+ imhere.column);

    }
    public LinkedList<Box> whonearme(){
        LinkedList<Box> who = new LinkedList<>();
        who.add(new Box(x-1,y-1));
        who.add(new Box(x-1,y));
        who.add(new Box(x,y-1));
        who.add(new Box(x-1,y+1));
        who.add(new Box(x+1,y-1));
        who.add(new Box(x+1,y+1));
        return who;
    }

    public static void main(String[] args) {

    }
}
