package CrackInterviewCode;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This Class contains questions and solutions regarding stacks and queues.
 */
public class StacksAndQueues {
    /**
     * <h5>
     *     Describe how you could use a single array to implement three stacks.
     * </h5>
     * <p>
     *     A straightforward way is to divide the array by three evenly, each stacks uses its own space, like three
     *     standalone stacks. However, this is not optimized to use the space. For example, if the user is keep pushing
     *     to one of the arrays, when it is full, you have to dynamically grow the array, while you could use the space
     *     of other two arrays.<br/>
     *     Another way is to store node in the array one by one no matter which stack it is. But maintain three linked list
     *     to remember which array element belongs to which stack. But when a stack pops an element out, a hole could be
     *     left in the array, to overcome this, we can use a free list to keep track of the available array cell. This sounds
     *     clever, however, maintaining these linked list and free list are costing more space.<br/>
     *     Neither of them are actually good at space. Here we'll implement a third way. We put one stack at the head, another
     *     at the tail, they are growing towards the middle. And the put the third at the position 1/3 of the array, and
     *     growing to the tail. When they're about to overlap, we shift the whole middle array to a position where same
     *     space are left to the first and second array. This is very space efficient as they share the storage, but there
     *     are time cost when it requires moving the middle array. For simplicity, we'll omit the code when it requires
     *     growing/shrinking the backend array.
     * </p>
     */
    public static class ThreeStacks {
        private int[] position;
        private int[] size = {0,0,0};
        private int[] data;

        public ThreeStacks(int capacity) {
            this.position = new int[]{ 0, capacity / 3, capacity - 1 };
            this.data = new int[capacity];
        }

        public ThreeStacks() {
            this(30);
        }

        public boolean empty(int stackNum) {
            if ( stackNum < 0 || stackNum > 2 ) throw new IndexOutOfBoundsException();
            return size[stackNum] == 0;
        }

        public int peek( int stackNum ) {
            if ( empty(stackNum) ) {
                throw new EmptyStackException();
            } else {
                if ( stackNum == 0 || stackNum == 1 ) {
                    return data[ position[stackNum] + size[stackNum] - 1 ];
                } else {
                    return data[ position[stackNum] - (size[stackNum] - 1) ];
                }

            }
        }

        public int pop( int stackNum ) {
            if ( empty(stackNum) ) {
                throw new EmptyStackException();
            } else {
                int element;
                if ( stackNum == 0 || stackNum == 1 ) {
                    element = data[ position[stackNum] + size[stackNum] - 1 ];
                    data[ position[stackNum] + size[stackNum] - 1 ] = 0; // this is not necessary, just for pretty print later for test
                } else {
                    element = data[ position[stackNum] - (size[stackNum] - 1) ];
                    data[ position[stackNum] - (size[stackNum] - 1) ] = 0; // this is not necessary, just for pretty print later for test
                }
                size[stackNum]--;
                return element;
            }
        }

        public int push( int stackNum, int element ) {
            shiftMiddle();
            switch ( stackNum ) {
                case 0:
                case 1:
                    data[ position[stackNum] + size[stackNum]] = element;
                    break;
                case 2:
                    data[ position[stackNum] - size[stackNum]] = element;
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }
            size[stackNum]++;
            return element;
        }

        public void printMe() {
            for( int i : data ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }

        private void shiftMiddle( ) {
            int leftFree = position[1] - ( position[0] + size[0] - 1) - 1;
            int rightFree = (position[2] - (size[2] - 1)) - (position[1] + size[1] - 1) - 1;
            int number = ( leftFree + rightFree ) / 2 + 1;
            if ( leftFree > 0 && rightFree > 0 ) return;
            if ( leftFree == 0 && rightFree == 0 ) {
                // needs to grow the backend array, we'll omit and simply throw out
                throw new UnsupportedOperationException("array is full");
            } else if ( leftFree == 0 ) { // shift right
                for( int i = position[1] + size[1] - 1; i >= position[1]; i--  ) {
                    data[ i + number ] = data[i];
                }
                for( int i = position[1]; i < position[1]+number; i++ ) {// this is not necessary, just for pretty print later for test
                    data[i] = 0;
                }
                position[1] += number;

            } else if ( rightFree == 0 ) {//shift left
                for( int i = position[1]; i < position[1] + size[1]; i++  ) {
                    data[ i - number ] = data[i];
                }
                for( int i = position[1] + size[1] - 1; i > position[1] + size[1] - 1 - number; i-- ) {// this is not necessary, just for pretty print later for test
                    data[i] = 0;
                }
                position[1] -= number;
            }

        }
    }

    /**
     * <h5>
     *     How would you design a stack which, in addition to push and pop, also has a function min which
     *     returns the minimum element? Push, pop and min should all operate in O(1) time.
     * </h5>
     * <p>
     *     To provide O(1) time access means we need to access simple variables or access some data structures which provide
     *     O(1) access to its internal storage.<br/>
     *     We can use an instance variable to keep track of the minimum value. We update it during push and pop. Push works
     *     as it only needs to compare the new element with the exist min value, but pop requires re-calculating the min
     *     from all the elements, thus it needs O(n) time. Unless if we keep track of all the historical min value and then
     *     we can just restore the previous min one. <br/>
     *     We will do in this way, we use another stack to store the min values, during push, when the new element is less
     *     than or equal to the top of the stack, we push it the min stack. We don't have to push it to the min stack if the
     *     new element is bigger than the top of the stack, this will save some space. During pop, if the popped value is
     *     equal to the min one, pop one element out of the min stack.<br/>
     *     The previous solution is good, it provides O(1) access. Just want to mention one data structure, heap, which is
     *     a good candidate for min/max values in other interview questions.
     * </p>
     */
    public static class StackWithMin {
        Stack<Integer> data;
        Stack<Integer> min;

        public StackWithMin() {
            data = new Stack<Integer>();
            min = new Stack<Integer>();
        }

        public int push(int value) {
            data.push(value);
            if (min.empty()) {
                min.push(value);
            } else {
                if ( value <= min.peek() ) min.push(value);
            }
            return value;
        }

        public int pop() {
            int value = data.pop();
            if ( value == min.peek() ) min.pop();
            return value;
        }

        public int min() {
            return min.peek();
        }


        public void printMe() {
            System.out.print("Data: ");
            for( int i : data ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();

            System.out.print("Min: ");
            for( int i : min ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * <h5>
     *     Implement the game Towers of Hanoi with three stacks.
     * </h5>
     * <p>
     *     It is easier to use recursion, move from tower 1 to tower 3 means, move n - 1 disks to tower 2, then move disk n to
     *     tower 3, then move n - 1 disk to tower 3.
     * </p>
     */
    public static class TowersOfHanoi {
        Stack<Integer> t1;
        Stack<Integer> t2;
        Stack<Integer> t3;


        public TowersOfHanoi(int n) {
            this.t1 = new Stack<Integer>();
            this.t2 = new Stack<Integer>();
            this.t3 = new Stack<Integer>();
            for( int i = n; i > 0; i-- ) {
                t1.push(i);
            }
        }

        private void move(int n, Stack<Integer> source, Stack<Integer> target, Stack<Integer> buffer) {
            if ( n == 2 ) {
                buffer.push(source.pop());
                printMe();
                target.push(source.pop());
                printMe();
                target.push(buffer.pop());
                printMe();
            } else if ( n > 2 ) {
                move( n-1, source, buffer, target );
                target.push(source.pop());
                printMe();
                move( n-1, buffer, target, source );
            }

        }

        public void play() {
            printMe();
            move( t1.size(), t1, t3, t2 );
        }

        public void printMe() {
            System.out.print("t1: ");
            for( Object i : t1 ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            System.out.print("t2: ");
            for( Object i : t2 ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            System.out.print("t3: ");
            for( Object i : t3 ) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * <h5>
     *     Implement a MyQueue class which implements a queue using two stacks.
     * </h5>
     * <p>
     *     For enqueue, we keep pushing them into stack 1, for dequeue, we pop all elements out of stack 1 and push them into
     *     stack 2, and now stack 2 has the right order for queue and when stack 2 is empty, get the elements from stack 1 again.
     * </p>
     */
    public static class MyQueue {
        Stack<Integer> s1;
        Stack<Integer> s2;

        public MyQueue() {
            s1 = new Stack<Integer>();
            s2 = new Stack<Integer>();
        }

        public boolean add(int value) {
            s1.push(value);
            return true;
        }

        public Integer poll() {
            if ( s2.empty() ) {
                while ( !s1.empty() ) {
                    s2.push(s1.pop());
                }
            }
            if (s2.empty()) return null;
            return s2.pop();
        }
    }

    /**
     * <h5>
     *     Write a program to sort a stack in ascending order You should not make any assumptions about how the stack
     *     is implemented The following are the only functions that should be used to write this program:
     *     push | pop | peek | isEmpty
     * </h5>
     * <p>
     *     We need another stack, then we pop one out from the original, put it to the new one. And then pop another out,
     *     compare with the one in new one, if not in the right order, we need to pop it out to original one, then push the
     *     second element and get the one back. It is like insertion sort.<br/>
     *     This requires doubling the space and O(n2) running time.
     * </p>
     * @param s the stack to be sorted
     * @return a new sorted stack
     */
    public static Stack<Integer> sortStack(Stack<Integer> s) {
        Stack<Integer> n = new Stack<Integer>();
        while ( ! s.empty() ) {
            int value = s.pop();
            while ( !n.empty() && value > n.peek() ) {
                s.push(n.pop());
            }
            n.push(value);
        }
        return n;
    }


}
