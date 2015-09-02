package java8.defaultmth;

public class InterfaceImpl implements InterfaceDefaultMethod {

	@Override
	public void method1() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		InterfaceImpl m = new InterfaceImpl();
		m.method1();
		m.methodDefault();
	}
}
