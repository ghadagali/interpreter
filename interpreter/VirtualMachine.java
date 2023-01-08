/**
 * DO NOT provide a method that returns components contained WITHIN the VM (this
 * is the exact situation that will break encapsulation) - you should request
 * that the VM performs operations on its components. This implies that the VM
 * owns the components and is free to change them, as needed, without breaking
 * clients' code (e.g., suppose I decide to change the name of the variable that
 * holds my runtime stack - if your code had referenced that variable then your
 * code would break. This is not an unusual situation - you can consider the
 * names of methods in the Java libraries that have been deprecated).
 *
 * Consider that the VM calls the individual ByteCodes' execute method and
 * passes itself as a parameter. For the ByteCode to execute, it must invoke
 * one or more methods in the runStack. It can do this by executing
 * VM.runStack.pop(); however, this does break encapsulation. To avoid this,
 * you'll need to have a corresponding set of methods within the VM that do
 * nothing more than pass the call to the runStack. e.g., you would want to
 * define a VM method:
 *     public int popRunStack() {
 *       return runStack.pop();
 *     }
 * called by, e.g.,
 *     int temp = VM.popRunStack();
 */
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.errors.StackUnderflowException;

import java.util.Stack;

public class VirtualMachine {

	private int pc;
	private RunTimeStack runTimeStack;
	// This may not be the right parameterized type!!
	private Stack<Integer> returnAddresses = new Stack<Integer>();
	private boolean isRunning;
	private boolean isOutputting;
	private Program program;

	public VirtualMachine(Program program) {
		this.program = program;
	}

	public void executeProgram() {
		pc = 0;
		runTimeStack = new RunTimeStack();
		returnAddresses = new Stack<>();
		isRunning = true;

		while (isRunning) {
			ByteCode code = program.getCode(pc);
			code.execute(this);
			if (isOutputting == true) {
				runTimeStack.dump(); // check that the operation is correct
			}
			pc++;
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void newFrame(int offset) {
		runTimeStack.newFrameAt(offset);
	}

	public void popFrame() throws StackUnderflowException {
		runTimeStack.popFrame();
	}

	public int pushRunStack(Integer i) {
		return runTimeStack.push(i);
	}

	public int popRunStack() {
		try {
			return runTimeStack.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int loadRunStack(Integer offset) {
		return runTimeStack.load(offset);
	}

	public int storeRunStack(Integer offset) {
		try {
			return runTimeStack.store(offset);
		} catch (StackUnderflowException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public boolean isOutputting() {
		return isOutputting;
	}

	public void setOutputting(boolean isOutputting) {
		this.isOutputting = isOutputting;
	}

	public void pushReturnAddress(int pc) {
		returnAddresses.push(pc);
	}

	public int popReturnAddress() {
		return returnAddresses.pop();
	}

	public int peekRunStack() {
		try {
			return runTimeStack.peek();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public void write(int val) {
		System.out.println(val);
	}

}
