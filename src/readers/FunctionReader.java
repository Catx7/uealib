package readers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import usablefunctions.Function;

public class FunctionReader {
	public static Function getFunction(String name) 
		throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
			URL url = new File("bin/").toURI().toURL();
			URLClassLoader classLoader = new URLClassLoader(new URL[] {url});
			return (Function)classLoader.loadClass("usablefunctions."+name).newInstance();
	}
}
