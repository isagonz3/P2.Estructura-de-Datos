package EL2a;


/*Creamos una clase para poder guardar los valores de 0 a 128 de forma aleatoria.
 *Para no usar .shuffle (de la biblioteca Collections de Java), voy a implementar
 *el algoritmo de Fisher-Yates, barajando los números de un array.*/

import java.util.Random;

public class FishYat {

    public static int[] aleatorio(int n){
        int[] arr = new int[n];
        Random r = new Random();
        for(int i = 0; i < n; i++){
            arr[i] = i;
        }

        Random ran = new Random();
        for(int i = n-1; i > 0; i--){
            int index = ran.nextInt(i+1);
            int aux = arr[i];
            arr[i] = arr[index];
            arr[index] = aux;
        }
        return arr;
    }
}
