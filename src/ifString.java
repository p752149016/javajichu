
public class ifString {
	public static void main(String[] args){
		String name = "asd";
		String names = new String("asd");
		String namea = "asd";
		String nameb = new String("asd");
		
		System.out.println(name == namea);
		System.out.println(name == names);
		System.out.println(names == nameb);
		System.out.println(name.equals(nameb));
		
	}
}
