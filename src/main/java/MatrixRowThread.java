public class MatrixRowThread extends Thread {

    private double[][] M;
    private int row;
    private int startColumn;
    private int refElemColIdx;
    private int n;

    public MatrixRowThread(double[][] M, int row, int startColumn, int refElemColIdx, int n) {
        this.M = M;
        this.row = row;
        this.startColumn = startColumn;
        this.refElemColIdx = refElemColIdx;
        this.n = n;
    }

    @Override
    public void run(){
        double coeff = -(M[row][startColumn] / M[refElemColIdx][refElemColIdx]);
        int len = n+1-startColumn;
        MatrixElementThread[] met = new MatrixElementThread[len];
        for(int i = startColumn;i<n+1;++i){
            met[i-startColumn] = new MatrixElementThread(M, row, i, coeff, M[refElemColIdx][i]);
            met[i-startColumn].start();
        }
        for(int i = 0;i<len;++i) {
            try {
                met[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRefElemColIdx() {
        return refElemColIdx;
    }

    public void setRefElemColIdx(int refElemColIdx) {
        this.refElemColIdx = refElemColIdx;
    }

    public double[][] getM() {
        return M;
    }

    public void setM(double[][] m) {
        M = m;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
