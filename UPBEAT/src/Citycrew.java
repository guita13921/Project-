import java.util.LinkedList;

public class Citycrew {
    Box imhere;
    int x = 1;
    int y = 1;
    Box ul;
    Box up;
    Box ur;
    Box dl;
    Box d;
    Box dr;
    Mapp map;

    public Citycrew(Mapp map,int x,int y){
        this.x = x;
        this.y = y;
        this.map = map;
    }
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

    public void checkAroudME(){
        if(x-1 <= 0){
            up = null;
        }else{
            up = map.getBox(map,x-1,y);
        }
//        who.add(new Box(x-1,y-1));
//        who.add(new Box(x-1,y));
//        who.add(new Box(x,y-1));
//        who.add(new Box(x-1,y+1));
//        who.add(new Box(x+1,y-1));
//        who.add(new Box(x+1,y+1));
//        return who.;
    }

    public String showDirectionBox(){
        return null;
    }

//    public static void main(String[] args) {
//        Citycrew citycrew = new Citycrew();
//        Mapp demo = new Mapp(5,5);
//        demo.MapPrettyPrint();
//        for(int i =0;i<citycrew.whoNearMe().size();i++) {
//            citycrew.whoNearMe().get(i).box_show();
//        }
//    }
}
