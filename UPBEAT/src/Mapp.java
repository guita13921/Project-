import java.util.ArrayList;

public class Mapp {
  private int Budget = 0;
  private int m;
  private int n;
  ArrayList<Box> map = new ArrayList<>();

  public void MappArea(int n, int m) {
    this.n = n;
    this.m = m;
  }

  public void GenerateMap() {
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        map.add(new Box(n, m));

      }
    }
  }
  public void own(){
  }
  public void Invest(int invest) {
    Budget = Budget+invest;
    if(Budget>0) {
      own();
    }else if(Budget == 0){
      own();
    }if(Budget <0){
      own();
    }
  }
}
