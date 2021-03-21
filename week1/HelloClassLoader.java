import java.util.Base64;
import java.io.File;
import java.nio.file.Files;

public class HelloClassLoader extends ClassLoader {
	public static void main(String[] args){
		//new jvm.Hello();
		try {
			new HelloClassLoader().findClass("Hello").newInstance();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch(InstantiationException e){
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
			//System.out.println(fileContent);
			
			//byte[] bytes = decode(helloBase64);
			
			return defineClass(name, fileContent, 0, fileContent.length);
		} catch (Exception e){
			System.out.println(file.toPath());
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
	
	public byte[] decode(String base64){
		return Base64.getDecoder().decode(base64);
	}
	
}