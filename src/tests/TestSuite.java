package tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import matrices.Matriz;
import matrices.DimensionesIncompatibles;

public class TestSuite {
	//Operación multiplicar (Exception):
    @Test
	void multiplicarMatricesExcepcion() {
		Matriz m1 = new Matriz(3, 2, true);
		Matriz m2 = new Matriz(3, 4, true);
		Assertions.assertThrows(
			DimensionesIncompatibles.class,
			() -> Matriz.multiplicarDosMatrices(m1, m2)
		);
	}

	//Operación multiplicar (NoException):
	@Test
	void multiplicarMatricesNoExcepcion() {
		Matriz m1 = new Matriz(3, 3, false);
		Matriz m2 = new Matriz(3, 3, false);

		int[][] datos_m1 = {{0,1,2},{3,4,5},{6,7,8}};
		m1.setDatos(datos_m1);
		int[][] datos_m2 = {{9,8,7},{6,5,4},{3,2,1}};
		m2.setDatos(datos_m2);

		Matriz expectedResult = new Matriz(3, 3, false);
		int[][] datos_expResult = {{66,90,114},{39,54,69},{12,18,24}};
		expectedResult.setDatos(datos_expResult);

		Matriz result = null;
		try {
            result = Matriz.multiplicarDosMatrices(m1, m2);
        } catch (DimensionesIncompatibles ex) {
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }

		int[][] result_datos = result.getDatos();
		int[][] expResult_datos = expectedResult.getDatos();
		for (int j = 0; j < 3; j++) { 
            for (int i = 0; i < 3; i++) { 
				Assertions.assertEquals(
					result_datos[i][j], expResult_datos[i][j]
				);
			}
		}

		System.out.println(result);
	}

	//Operación trasponer:
	@Test
	void trasponerMatrizNoExcepcion() {
		Matriz m1 = new Matriz(3, 3, false);
		int[][] datos_m1 = {{0,1,2},{3,4,5},{6,7,8}};
		m1.setDatos(datos_m1);

		Matriz expectedResult = new Matriz(3, 3, false);
		int[][] datos_expResult = {{0,3,6},{1,4,7},{2,5,8}};
		expectedResult.setDatos(datos_expResult);

		Matriz result = null;
		try {
            result = Matriz.trasponerMatriz(m1);
        } catch (Exception ex) {
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }

		int[][] result_datos = result.getDatos();
		int[][] expResult_datos = expectedResult.getDatos();
		for (int j = 0; j < 3; j++) { 
            for (int i = 0; i < 3; i++) { 
				Assertions.assertEquals(
					result_datos[i][j], expResult_datos[i][j]
				);
			}
		}

		System.out.println(result);
	}
	
	//Operaciones compuestas (1. (Z^T)^T = Z):
	@Test
	void trasponerMatrizDosVecesNoExcepcion() {
		Matriz m1 = new Matriz(3, 3, true);

		Matriz result = null;
		try {
            result = Matriz.trasponerMatriz(m1);
			result = Matriz.trasponerMatriz(result);
        } catch (Exception ex) {
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }

		int[][] result_datos = result.getDatos();
		int[][] m1_datos = m1.getDatos();
		for (int j = 0; j < 3; j++) { 
            for (int i = 0; i < 3; i++) { 
				Assertions.assertEquals(
					result_datos[i][j], m1_datos[i][j]
				);
			}
		}

		System.out.println(result);
	}

	//Operaciones compuestas (2. (Z·Q)^T = Q^T·Z^T):
	@Test
	void multiplicarMatricesTraspuestasNoExcepcion() {
		Matriz m1 = new Matriz(3, 3, false);
		Matriz m2 = new Matriz(3, 3, false);

		int[][] datos_m1 = {{0,1,2},{3,4,5},{6,7,8}};
		m1.setDatos(datos_m1);
		int[][] datos_m2 = {{9,8,7},{6,5,4},{3,2,1}};
		m2.setDatos(datos_m2);

		Matriz resultMultiplicacionTraspuesta = null;
		try {
            resultMultiplicacionTraspuesta = Matriz.multiplicarDosMatrices(m1, m2);
			resultMultiplicacionTraspuesta = Matriz.trasponerMatriz(resultMultiplicacionTraspuesta);
        } catch (DimensionesIncompatibles ex) {
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }

		Matriz resultTrasuestasMultiplicadas = null;
		try {
            Matriz t1 = Matriz.trasponerMatriz(m1);
			Matriz t2 = Matriz.trasponerMatriz(m2);
			resultTrasuestasMultiplicadas = Matriz.multiplicarDosMatrices(t2, t1);
        } catch (DimensionesIncompatibles ex) {
            Logger.getLogger(TestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }

		int[][] resultMultiplicacionTraspuesta_datos = resultMultiplicacionTraspuesta.getDatos();
		int[][] expResult_resultTrasuestasMultiplicadas_datos = resultTrasuestasMultiplicadas.getDatos();
		for (int j = 0; j < 3; j++) { 
            for (int i = 0; i < 3; i++) {
				Assertions.assertEquals(
					resultMultiplicacionTraspuesta_datos[i][j], expResult_resultTrasuestasMultiplicadas_datos[i][j]
				);
			}
		}

		System.out.println(resultTrasuestasMultiplicadas);
	}

}
