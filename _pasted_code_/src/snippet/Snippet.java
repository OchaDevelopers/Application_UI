package snippet;

public class Snippet {
	public void makeDIR(String source) {
			source ="C:\\Users\\mrhjs\\Downloads\\ambari-trunk";
			try {
				File file = new File(source+"/test.txt");
				if(file.isFile()) {
					System.out.println("file is exist!\n");
					System.out.println(file.getCanonicalPath().toString());
				} else {
					System.out.println("file isn't exist\n");
				}
				File dir = new File(source);
				if(dir.isDirectory()) {
					System.out.println("dir is exist\n");
					System.out.println(dir.getCanonicalPath().toString());
				} else {
					System.out.println("dir isn't exist\n");
				}
			} catch(IOException E) {}
		}
}

