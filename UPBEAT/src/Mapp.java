import java.util.ArrayList;

public class Mapp {
  protected int Budget = 0;
  private int rows;
  private int cols;
  protected ArrayList<Box> map = new ArrayList<>();

  public Mapp(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    GenerateMap();
  }

  public void GenerateMap() {
    for (int i = 1; i < rows+1; i++) {
      for (int j = 1; j < cols+1; j++) {
        map.add(new Box(i, j));
      }
    }
  }
  public ArrayList<Box> ShowMap(){
   return  map;
  }

  public void MapPrettyPrint(){
    int count = 0;
    for(int i = 0;i<map.size();i++){
      if(i % (rows) == 0 && i % 2 != 0){
        System.out.print("  ");
      }
        System.out.print(" ("+map.get(i).box_row_show() + "," + map.get(i).box_column_show()+") ");
      count++;
      if (count % rows == 0) {
        System.out.println("   ");
      }
    }
  }
  }