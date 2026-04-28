package Carolina.arbol.arbol_iterativo;

import Carolina.estructuras_necesarias.ListSE;
import Carolina.estructuras_necesarias.Queue;
import Carolina.estructuras_necesarias.Stack;

public class ArbolBusquedaBinaria<T extends Comparable<T>> {

    //Atributos
    protected NodoArbol<T> raiz; //se utiliza protected porque así lo podemos utilizar en la subclase para los enteros que hereda de esta


    //Constructores
    public ArbolBusquedaBinaria() { //constructor por defecto que inicializa la raiz del árbol
        raiz = null;
    }


    //Métodos
    //Para poder añadir un elemento al árbol
    public void add(T dato) {
        NodoArbol<T> nuevo = new NodoArbol<>(dato); //crea un nodo auxiliar para añadir al árbol
        if (raiz == null) { //si no hay raíz entonces el nodo se añade al comienzo
            raiz = nuevo;
            return;
        }

        NodoArbol<T> actual = raiz; //se establece el puntero inicial en la raíz del árbol
        NodoArbol<T> padre = null;

        while (actual != null) { //mientras que el nodo actual no sea nulo se actualiza el nodo a nodo padre para no perder la referencia
            padre = actual;
            int comparacion = dato.compareTo(actual.dato); //comparo el dato que quiero insertar con el dato del nodo en el que estoy

            if (comparacion < 0) { //si el dato a insertar es menor que el dato en el nodo actual, va a pertener al subarbol izquierdo
                actual = actual.hijoIzquierdo;
            }
            else { //si el dato a insertar es mayor que el dato en el nodo actual, va a pertenecer al subarbol derecho
                actual = actual.hijoDerecho;
            }
        }

        if (dato.compareTo(padre.dato) < 0) { //como hemos llegado al último nodo utilizamos la referencia que tenemos con el nodo padre para colocarlo a un lado u otro
            padre.hijoIzquierdo = nuevo;
        }
        else {
            padre.hijoDerecho = nuevo;
        }
    }


    //Para poder leer la altura de los nodos utilizamos un getter y un metodo auxiliar que solo debe ser modificable desde dentro de la clase
    public int getAltura() {
        return altura(raiz);
    }

    private int altura(NodoArbol<T> nodo) {
        if (nodo == null) { //si el nodo es nulo no tiene altura
            return 0;
        }

        int alturaIzquierda = altura(nodo.hijoIzquierdo); //calcula la altura del subarbol izquierdo
        int alturaDerecha = altura(nodo.hijoDerecho); //calcula la altura del subarbol derecho

        int alturaMayor; //establece cual es la altura mayor

        if (alturaIzquierda > alturaDerecha) {
            alturaMayor = alturaIzquierda;
        }
        else {
            alturaMayor = alturaDerecha;
        }

        return alturaMayor + 1; //la altura del nodo actual es la altura mayor añadiendo 1 porque la raíz tiene altura 1
    }


    //Para poder leer el grado de los nodos utilizamos un getter y un metodo auxiliar que solo debe ser modificable desde dentro de la clase
    public int getGrado() {
        return grado(raiz);
    }

    private int grado(NodoArbol<T> nodo) {
        if (nodo == null) { //si el nodo es nulo no tiene grado
            return 0;
        }

        int cantidadHijos = 0; //inicializamos un contador para acumular el número de nodos del subarbol del nodo actual

        if (nodo.hijoIzquierdo != null) { //mientras existan nodos en el subarbol de la izquierda del nodo actual se añade +1 al contador
            cantidadHijos = cantidadHijos + 1;
        }
        if (nodo.hijoDerecho != null) { //mientras existan nodos en el subarbol de la derecha del nodo actual se añade +1 al contador
            cantidadHijos = cantidadHijos + 1;
        }

        int gradoIzquierdo = grado(nodo.hijoIzquierdo); //calculamos el grado del subarbol de la izquierda
        int gradoDerecho = grado(nodo.hijoDerecho); //calculamos el grado del subarbol de la derecha

        int gradoMayorSub; //determina el grado que es mayor

        if (gradoIzquierdo > gradoDerecho) { //establece cuál es el subarbol de mayor grado
            gradoMayorSub = gradoIzquierdo;
        } else {
            gradoMayorSub = gradoDerecho;
        }

        int gradoFinal; //determina el grado final del surbarbol

        if (cantidadHijos > gradoMayorSub) { //establece el grado final
            gradoFinal = cantidadHijos;
        } else {
            gradoFinal = gradoMayorSub;
        }
        return gradoFinal;
    }


    //Para ordenarlo de manera que la lista sea Nodo->Izq->Der usamos una pila que funciona de manera LIFO
    public ListSE<T> getListaPreOrden() {
        ListSE<T> lista = new ListSE<>(); //crea una lista donde se almacena el resultado
        Stack<NodoArbol<T>> pila = new Stack<>(); //crea una pila auxiliar

        if (raiz != null) { //si el primer nodo es distinto de nulo se añade a la pila
            pila.push(raiz);
        }

        while (!pila.isEmpty()) { //si la pila no está vacía
            NodoArbol<T> nodo = pila.pop(); //se selecciona el nodo que está en la cima de la pila
            lista.addLast(nodo.dato); //se añade el nodo a la lista

            if (nodo.hijoDerecho != null) { //si el nodo hijo de la derecha no es nulo se añade a la pila
                pila.push(nodo.hijoDerecho);
            }
            if (nodo.hijoIzquierdo != null) { //si el nodo hijo de la izquierda no es nulo se añade a la pila
                pila.push(nodo.hijoIzquierdo);
            }
        }

        return lista;
    }


    //Para ordenarlo de manera que la lista sea Izq->Nodo->Der usamos una pila que funciona de manera LIFO
    public ListSE<T> getListaOrdenCentral() {
        ListSE<T> lista = new ListSE<>(); //crea una lista donde se almacena el resultado
        Stack<NodoArbol<T>> pila = new Stack<>(); //crea una pila auxiliar

        NodoArbol<T> actual = raiz; //inicializa el puntero para recorrer el árbol

        while (actual != null || !pila.isEmpty()) { //si el puntero no ha llegado al final o aún quedan nodos

            while (actual != null) { //si el puntero no ha llegado al final
                pila.push(actual); //añade a la pila el nodo en el que se encuentra
                actual = actual.hijoIzquierdo; //actualiza el puntero al siguiente nodo a la izquierda
            }

            actual = pila.pop(); //se añade a la pila el nodo de más abajo a la izquierda
            lista.addLast(actual.dato);

            actual = actual.hijoDerecho; //como ya se ha recorrido el subarbol de la izquierda y el nodo, se pasa al subarbol de la derecha y hace lo mismo
        }

        return lista;
    }


    //Para ordenarlo de manera que la lista sea Izq->Der->Nodo usamos una pila que funciona de manera LIFO
    public ListSE<T> getListaPostOrden() {
        ListSE<T> lista = new ListSE<>(); //crea una lista donde se almacena el resultado
        Stack<NodoArbol<T>> pila = new Stack<>(); //crea una pila auxiliar

        NodoArbol<T> actual = raiz; //inicializa el puntero para recorrer el árbol
        NodoArbol<T> ultimoVisitado = null; //inicializa el puntero para saber los nodos que se han visitado

        while (actual != null || !pila.isEmpty()) { //si el puntero que recorre el árbol no ha llegado al final o aún quedan nodos

            while (actual != null) { //si el puntero que recorre no ha llegado al final
                pila.push(actual); //añade a la pila el nodo en el que se encuentra
                actual = actual.hijoIzquierdo; //actualiza el puntero que recorre al siguiente nodo a la izquierda
            }

            NodoArbol<T> tope = pila.peek(); //permite ver el último nodo sin sacarlo de la pila para decidir si ir al subarbol derecho o al nodo

            if (tope.hijoDerecho != null && ultimoVisitado != tope.hijoDerecho) { //si el subarbol derecho existe y no es el último visitado se recorre
                actual = tope.hijoDerecho;
            } else { //si no hay subarbol derecho o ya se ha procesado se pasa al nodo
                lista.addLast(tope.dato);
                ultimoVisitado = pila.pop(); //se actualiza para saber que el nodo ya ha sido visitado junto con sus hijos
            }
        }
        return lista;
    }


    //Para obtener una lista con el camino que tenemos que recorrer
    public ListSE<T> getCamino(T dato) {
        ListSE<T> camino = new ListSE<>(); //crea una lista donde se almacena el resultado
        NodoArbol<T> actual = raiz; //inicializa el puntero actual en la raíz del árbol

        boolean encontrado = false; //permite recorrer el camino buscando el dato seleccionado

        while (actual != null && !encontrado) { //mientras el puntero no sea nulo y no se haya encontrado el dato
            camino.addLast(actual.dato); //se añade a la lista el nodo donde nos encontremos

            int comparacion = dato.compareTo(actual.dato); //compara el dato que tenemos que encontrar con el nodo en el que nos encontramos

            if (comparacion == 0) { //si el resultado es 0 entonces el dato buscado y el dato del nodo donde nos encontramos coinciden
                encontrado = true;
            }
            else if (comparacion < 0) { //si el resultado es menor que cero el dato dle nodo donde nos encontramos es menor que el buscado
                actual = actual.hijoIzquierdo; //el puntero se actualiza el puntero al subarbol de la izquierda
            }
            else {
                actual = actual.hijoDerecho; //como el dato que buscamos es mayor que el del nodo donde estamos se actualiza el puntero al subarbol de la derecha
            }
        }

        return camino;
    }


    //Para devolver una lista con los nodos que están en el nivel que se pasa por parámetro
    public ListSE<T> getListaDatosNivel(int nivelBuscado) {
        ListSE<T> lista = new ListSE<>(); //crea una lista donde se guarda el resultado

        if (raiz == null){ //si el árbol está vacío no hay nada que recorrer
            return lista;
        }

        Stack<NodoNivel<T>> pila = new Stack<>(); //pila auxiliar para recorrer el árbol con el nivel de cada nodo
        pila.push(new NodoNivel<>(raiz, 0)); //se mete la raíz con nivel 0

        while (!pila.isEmpty()) { //mientras queden nodos por procesar
            NodoNivel<T> actual = pila.pop(); //saca el último nodo añadido junto con su nivel

            if (actual.nivel == nivelBuscado) { //si el nivel coincide con el que buscamos
                lista.addLast(actual.nodo.dato); //añadimos el dato a la lista
            } else if (actual.nivel < nivelBuscado) { //si todavía no hemos llegado al nivel buscado metemos primero el derecho para que el izquierdo salga antes de la pila
                if (actual.nodo.hijoDerecho != null)
                    pila.push(new NodoNivel<>(actual.nodo.hijoDerecho, actual.nivel + 1)); //bajamos un nivel al hijo derecho

                if (actual.nodo.hijoIzquierdo != null)
                    pila.push(new NodoNivel<>(actual.nodo.hijoIzquierdo, actual.nivel + 1)); //bajamos un nivel al hijo izquierdo
            }
        }

        return lista; //devolvemos la lista con los nodos del nivel pedido
    }

    private static class NodoNivel<T> { //estructura auxiliar para poder guardar un nodo junto con su nivel
        NodoArbol<T> nodo; //nodo del árbol
        int nivel; //nivel en el que está ese nodo

        NodoNivel(NodoArbol<T> nodo, int nivel) { //constructor que inicializa nodo y nivel
            this.nodo = nodo;
            this.nivel = nivel;
        }
    }


    //Para comprobar si todos los nodos tienen el mismo número de hijos
    public boolean isArbolHomogeneoIterativo() {
        if (raiz == null) return true; //si el árbol está vacío se considera homogéneo

        int hijosEsperados = contarHijos(raiz); //guardamos el número de hijos que tiene la raíz como referencia

        Stack<NodoArbol<T>> pila = new Stack<>(); //pila para recorrer el árbol
        pila.push(raiz); //empezamos desde la raíz

        while (!pila.isEmpty()) { //mientras haya nodos por revisar
            NodoArbol<T> actual = pila.pop(); //sacamos el nodo actual

            int hijosNodo = contarHijos(actual); //contamos los hijos del nodo actual

            //si el nodo no es hoja y tiene distinto número de hijos que la raíz, ya no es homogéneo
            if (hijosNodo != 0 && hijosNodo != hijosEsperados) {
                return false;
            }

            if (actual.hijoIzquierdo != null) pila.push(actual.hijoIzquierdo); //metemos hijo izquierdo si existe
            if (actual.hijoDerecho != null) pila.push(actual.hijoDerecho); //metemos hijo derecho si existe
        }

        return true; //si no se ha roto la condición, es homogéneo
    }

    private int contarHijos(NodoArbol<T> nodo) { //cuenta cuántos hijos tiene un nodo
        int hijos = 0; //contador de hijos

        if (nodo.hijoIzquierdo != null) hijos++; //si tiene hijo izquierdo sumamos 1
        if (nodo.hijoDerecho != null) hijos++; //si tiene hijo derecho sumamos 1

        return hijos; //devolvemos el total
    }

    private boolean comprobarHomogeneoRec(NodoArbol<T> nodo, int hijosEsperados) { //versión recursiva de la comprobación de homogeneidad
        if (nodo == null) return true; //si el nodo es nulo no afecta

        int hijosNodo = contarHijos(nodo); //contamos hijos del nodo actual

        if (hijosNodo != 0 && hijosNodo != hijosEsperados) return false; //si no es hoja y no coincide con los hijos esperados, falla

        return comprobarHomogeneoRec(nodo.hijoIzquierdo, hijosEsperados) && comprobarHomogeneoRec(nodo.hijoDerecho, hijosEsperados); //comprobamos recursivamente izquierda y derecha
    }


    //Para comprobar que el árbol sea casi hueco el proceso es Nodo->Hijos->Hijos de los hijos... y usamos una cola que funciona de manera FIFO
    public boolean isArbolCasiCompleto() {
        if (raiz == null) return true; //si el árbol es vacío se considera completo

        Queue<NodoArbol<T>> cola = new Queue<>(); //se crea una cola auxiliar para recorrer los niveles
        cola.enqueue(raiz); //la cola comienza en el nodo raíz

        boolean encontradoHueco = false; //una vez que cambie a true ya no podrá aparecer ningún hueco más o no será casi completo

        while (!cola.isEmpty()) { //mientras la cola aún no esté vacía
            NodoArbol<T> actual = cola.dequeue(); //devuelve el nodo en entrar en la cola

            if (actual == null) { //si el nodo es vacío entonces ya encontramos el huevo
                encontradoHueco = true;
            }
            else {
                if (encontradoHueco==true){ //si ya habíamos encontrado un hueco y después encontramos otro nodo entonces no es casi completo
                    return false;
                }
                cola.enqueue(actual.hijoIzquierdo); //añade a la cola el nodo de la izquierda y después el de la derecha
                cola.enqueue(actual.hijoDerecho);
            }
        }

        return true;
    }
}