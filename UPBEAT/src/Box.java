public class Box {
    private final int row;
    private final int column;
    private long deposit = 0;

    public Box(int row,int column){
        this.row = row;
        this.column = column;
    }

    public int box_row_show(){
        return row;
    }

    public int box_column_show(){
        return column;
    }

    public long box_deposit_show(){
        return deposit;
    }
}

