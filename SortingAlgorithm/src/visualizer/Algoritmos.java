package visualizer;

public class Algoritmos {

    public static void bubbleSort(int[] arr, PainelDesenho p) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                p.atualizar(j, j + 1);

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr, PainelDesenho p) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                p.atualizar(min, j);

                if (arr[j] < arr[min]) min = j;
            }

            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr, PainelDesenho p) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                p.atualizar(j, i);
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void quickSort(int[] arr, int low, int high, PainelDesenho p) {
        if (low < high) {
            int pi = partition(arr, low, high, p);
            quickSort(arr, low, pi - 1, p);
            quickSort(arr, pi + 1, high, p);
        }
    }

    private static int partition(int[] arr, int low, int high, PainelDesenho p) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            p.atualizar(j, high);

            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void mergeSort(int[] arr, int l, int r, PainelDesenho p) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m, p);
            mergeSort(arr, m + 1, r, p);
            merge(arr, l, m, r, p);
        }
    }

    private static void merge(int[] arr, int l, int m, int r, PainelDesenho p) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            p.atualizar(k, l);

            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}