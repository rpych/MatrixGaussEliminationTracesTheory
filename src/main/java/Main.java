import java.io.*;


public class Main {

    public static void swapRows(double [][] M, int y, int N){
        for(int i=y + 1;i<N;++i){
            if(M[i][y] != 0){
                for(int k=0;k<N+1;++k){
                    double temp = M[y][k];
                    M[y][k] = M[i][k];
                    M[i][k] = temp;
                }
                break;
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {


        //preparing input matrix
        int k = 20;
        float epsilon = 0.00001f;
        int N;
        File fil = new File("20.txt"); // "15.txt"
        FileReader inputFil = new FileReader(fil);
        BufferedReader in = new BufferedReader(inputFil);

        String s = in.readLine();
        System.out.println(s);
        N = Integer.parseInt(s);
        double[][] M = new double[N][N+1];

        for (int i = 0; i < N; i++) {
            do{
                s = in.readLine();
            }while(s.equals(""));
            String[] sp = s.split(" ");
            for (int j = 0; j < N; j++) {
                if(sp[j] != null && sp[j].length() > 0)
                    M[i][j] = Double.parseDouble(sp[j]);
            }
        }

        do{
            s = in.readLine();
        }while(s.equals(""));

        String[] sp = s.split(" ");
        for (int j = 0; j < N; j++) {
            if(sp[j] != null && sp[j].length() > 0)
                M[j][N] = Double.parseDouble(sp[j]);
        }


        // Gauss elimination
        for(int i=0;i<N;++i){ //columns
            if(M[i][i] == 0 && M[i][i] > epsilon){
                swapRows(M, i, N);
            }
            MatrixRowThread[] mrt = new MatrixRowThread[N];
            for(int j=0;j<N;++j){  //rows in each column
                if(i != j){
                    mrt[j] = new MatrixRowThread(M, j, i, i, N);
                    mrt[j].start();
                }
            }
            for(int j=0;j<N;++j){ //waiting for threads terminating
                if( i!= j){
                    try {
                        mrt[j].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for(int i=0;i<N;i++){
            M[i][N] = M[i][N] / M[i][i];
            M[i][i] /= M[i][i];
        }

        //output
        System.out.println("Matrix after Gauss elimination:\n");

        for(int i=0;i<N;++i){
            for(int j=0;j<N+1;++j){
                System.out.printf("%23s", M[i][j]);
            }
            System.out.println();
        }

        File file = new File("./" + k + "result.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        System.out.println(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
        for(int i=0;i<N;++i){
            System.out.print(M[i][N] + " ");
        }
    }
}
