package interpreter.bytecode;



import java.util.Scanner;

import interpreter.VirtualMachine;

public class ReadCode extends ByteCode {

	@Override
	public void execute(VirtualMachine vm) {
		Scanner input = new Scanner( System.in );
		System.out.print( "Enter integer: " );
		int a = input.nextInt();
		vm.pushRunStack(a);
	}

}
