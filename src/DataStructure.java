
public class DataStructure { // c конструктором для создания и заполнения массива и методом для вывода элементов с четными  индексами.
	private final static int SIZE = 15; //создание константы размера масива
    private int[] arrayOfInts = new int[SIZE];//создание массива
 
    public DataStructure() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i; // заполняем массив
        }
    }
 
    public void printEven() { //метод для вывода елементов с четными цифрами
 
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
 
    
    //Внутренний класс EvenIterator, который реализует интерфейс DataStructureIterator, который наследует интерфейс Iterator<Integer>. 
    //Итераторы используются для доступа к элементам различных структур данных.
    //Они обычно имеют методы для проверки последнего элемента, получения текущего и следующего элементов.
    
    interface DataStructureIterator extends java.util.Iterator<Integer> { } 
    private class EvenIterator implements DataStructureIterator {
    	
        private int nextIndex = 0;
        
        public boolean hasNext() {
 
            return (nextIndex <= SIZE - 1);
        }
 
        public Integer next() {
 
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);  //класс EvenIterator напрямую обращается к полю arrayOfInts класса DataStructure.
 
            nextIndex += 2;
            return retValue;
        }
    }
 
    public static void main(String s[]) {
 
        DataStructure ds = new DataStructure(); //новый обьект
        ds.printEven(); //печатет методом  для вызова елементов с четными числами
    }
}
//The type DataStructure.EvenIterator must implement the inherited abstract method Iterator<Integer>.remove()