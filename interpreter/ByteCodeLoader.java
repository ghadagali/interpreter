package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import interpreter.bytecode.ByteCode;

public class ByteCodeLoader {
	private String codeFile;
	private Program program;

	public ByteCodeLoader(String byteCodeFile) throws IOException {
		codeFile = byteCodeFile;
		program = new Program();
	}

	public Program loadCodes() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(codeFile));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				String code[] = line.split(" ");
				String codeClass = CodeTable.get(code[0]);
				String name = "interpreter.bytecode." + codeClass;
				ByteCode bytecode =	(ByteCode)(Class.forName(name)	.newInstance());
				if (code.length > 1) {
					bytecode.setArgs(Arrays.asList(code[1]));
				}
				program.addCode(bytecode);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return program;
	}
}