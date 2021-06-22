package systemdesign;

public class CustomArrayList {

	private static final int SIZE_FACTOR = 5;
	private Object data[];
	private int index;
	private int size;

	public CustomArrayList() {
		this.data = new Object[SIZE_FACTOR];
		this.size = SIZE_FACTOR;
	}

	public void add(Object obj) {
		System.out.println("index:" + this.index + "size:" + this.size + "data size:" + this.data.length);
		if (this.index == this.size - 1) {
			// we need to increase the size of data[]
			increaseSizeAndReallocate();
		}
		data[this.index] = obj;
		this.index++;

	}

	private void increaseSizeAndReallocate() {
		this.size = this.size + SIZE_FACTOR;
		Object newData[] = new Object[this.size];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		this.data = newData;
		System.out.println("***index:" + this.index + "size:" + this.size + "data size:" + this.data.length);
	}

	public Object get(int i) throws Exception {
		if (i > this.index - 1) {
			throw new Exception("ArrayIndexOutOfBound");
		}
		if (i < 0) {
			throw new Exception("Negative Value");
		}
		return this.data[i];

	}

	public void remove(int i) throws Exception {
		if (i > this.index - 1) {
			throw new Exception("ArrayIndexOutOfBound");
		}
		if (i < 0) {
			throw new Exception("Negative Value");
		}
		System.out.println("Object getting removed:" + this.data[i]);
		for (int x = i; x < this.data.length - 1; x++) {
			data[x] = data[x + 1];
		}
		this.index--;
	}

	public static void main(String[] args) throws Exception {
		CustomArrayList list = new CustomArrayList();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");

		list.remove(5);
		System.out.println(list.get(7));
	}

}