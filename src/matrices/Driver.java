package matrices;

import java.util.logging.Level;
import java.util.logging.Logger;
import matrices.DimensionesIncompatibles;
import matrices.Matriz;

public class Driver {

    public static void main(String[] args) {
        //Sumar matrices
        Matriz m1 = new Matriz(3, 4, true);
        System.out.println(m1);
        Matriz m2 = new Matriz(3, 4, true);
        System.out.println(m2);
        try {
            System.out.println(Matriz.sumarDosMatrices(m1, m2));
        } catch (DimensionesIncompatibles ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Multiplicar matrices
        Matriz m3 = new Matriz(3, 2, true);
        System.out.println(m3);
        Matriz m4 = new Matriz(2, 5, true);
        System.out.println(m4);
        try {
            System.out.println(Matriz.multiplicarDosMatrices(m3, m4));
        } catch (DimensionesIncompatibles ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        //Trasponer matriz
        Matriz m5 = new Matriz(4, 3, true);
        System.out.println(m5);
        try {
            System.out.println(Matriz.trasponerMatriz(m5));
        } catch (Exception ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
