import java.util.Base64;
import java.io.File;
import java.nio.file.Files;
import java.lang.reflect.Method;  
import java.lang.reflect.InvocationTargetException;    

public class HelloClassLoader extends ClassLoader {
	public static void main(String[] args){
		//new Hello().hello();
		try {
			Class<?> c = new HelloClassLoader().findClass("Hello");
			Method m = c.getDeclaredMethod("hello");
			m.invoke(c.newInstance());
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch(InstantiationException e){
			e.printStackTrace();
		} catch (NoSuchMethodException e){
			e.printStackTrace();
		} catch (InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file = new File("Hello.xlass");
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			for(int i = 0; i < fileContent.length; i++){
				int a = byte2int(fileContent[i]);
				int result = 255-a;
				
				fileContent[i] = int2byte(result);
			}
			
			return defineClass(name, fileContent, 0, fileContent.length);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static int byte2int(byte b) {
        return ((int) b & 0x000000ff);
    }

    public static byte int2byte(int i) {
        return (byte) (i & 0x000000ff);
    }
	
}