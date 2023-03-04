public class Box {
    protected final int row;
    protected final int column;
    public long deposit = 0;
    protected Player owner = null;



    protected Box(int row,int column){
        this.row = row;
        this.column = column;
    }

    protected void ChangeDeposit(long input, Player p1){
        deposit = deposit + input;
        if(deposit == 0){
            owner = null;
        }else if(deposit >0){
            owner = p1;
            return;
        }else if(deposit < 0 ){
           // ChangeOwner();
            deposit = Math.abs(deposit);
            owner = p1;
        }
    }

//    protected void ChangeOwner(){//มันน่าจะไม่ต้องรับมั้ง
//        owner = new Player();//อันนีอยากให้เปลี่ยนเป็นคนที่ลงทุน //หาวิธีเปลี่ยนเจ้าของ
//    }

    protected int box_row_show(){
        return row;
    }

    protected int box_column_show(){
        return column;
    }

    protected long box_deposit_show(){
        return deposit;
    }
}

