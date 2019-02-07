import static java.lang.Math.abs;

public class MatrixElementThread extends Thread {
    private double[][] M;
    private int y;
    private int x;
    private double coeff;
    private double refElem;

    public MatrixElementThread(double[][] m, int y, int x, double coeff, double refElem) {
        M = m;
        this.y = y;
        this.x = x;
        this.coeff = coeff;
        this.refElem = refElem;
    }

    @Override
    public void run(){
        float epsilon = 0.0000001f;
        double out =  M[y][x] + (coeff * refElem);
        M[y][x] =  (abs(out) < epsilon) ? 0.0f : out;
    }


    public double[][] getM() {
        return M;
    }

    public void setM(double[][] m) {
        M = m;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public double getRefElem() {
        return refElem;
    }

    public void setRefElem(int refElem) {
        this.refElem = refElem;
    }
}

