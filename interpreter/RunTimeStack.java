package interpreter;

import java.util.Stack;
import java.util.Vector;
import interpreter.errors.StackUnderflowException;

public class RunTimeStack {

	private static final Exception StackUnderflowException = null;
	private Stack<Integer> framePointers = new Stack<Integer>();
	// This may not be the right parameterized type!!
	private Vector<Integer> runStack = new Vector<Integer>();

	public RunTimeStack() {
		newFrameAt(0);
	}

	/**
	 * The purpose of this function is to dump the RunTimeStack for the purpose of
	 * debugging.
	 */
	public void dump() {
		int size = runStack.size();
		System.out.println("------- ");
		for (int i = 0; i < size; i++) {
			System.out.println("rts " + i + " = " + runStack.get(i));
		}
		if (framePointers.size() > 0) {
			System.out.println("Frame boundary " + framePointers.peek());
		}

	}

	/**
	 * Returns the top item on the runtime stack.
	 */
	public int peek() throws StackUnderflowException {
		int size = runStack.size();
		if (size <= 0) {
			throw new StackUnderflowException();
		}
		return runStack.get(size - 1);
	}

	/**
	 * Pops the top item from the runtime stack, returning the item.
	 */
	public int pop() throws StackUnderflowException {
		int size = runStack.size();
		if (size <= 0) {
			throw new StackUnderflowException();
		}
		if (framePointers.size() > 0) {
			int frameBoundary = framePointers.peek();
			if (size <= frameBoundary) {
				throw new StackUnderflowException();
			}
		}
		return runStack.remove(size - 1);
	}

	/**
	 * Push an item on to the runtime stack, returning the item that was just
	 * pushed.
	 */
	public int push(int item) {
		runStack.add(item);
		return item;
	}

	/**
	 * This second form with an Integer parameter is used to load literals onto the
	 * stack.
	 */
	public Integer push(Integer i) {
		runStack.add(i);
		return i;
	}

	/**
	 * Start a new frame, where the parameter offset is the number of slots down
	 * from the top of the RunTimeStack for starting the new frame.
	 */
	public void newFrameAt(int offset) {
		framePointers.push(offset);
	}

	/**
	 * We pop the top frame when we return from a function; before popping, the
	 * functions’ return value is at the top of the stack so we’ll save the
	 * value, pop the top frame, and then push the return value.
	 */
	public void popFrame() throws StackUnderflowException {
		int retval = pop();
		int frameBoundary = framePointers.peek();
		int size = runStack.size();
		for (int i = size - 1; i > frameBoundary; i--) {
			pop();
		}
		framePointers.pop();
		push(retval);
	}

	/**
	 * Used to store into variables.
	 */
	public int store(int offset) throws StackUnderflowException {
		int storeval = pop();
		// store into offset
		return runStack.set(offset, storeval);
	}

	/**
	 * Used to load variables onto the stack.
	 */
	public int load(int offset) {
		// load from offset and push
		int loadval = runStack.get(offset);
		return push(loadval);
	}
}