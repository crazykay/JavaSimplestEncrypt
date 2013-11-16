import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encrypt {
	/**
	 * @author crazykay
	 * @see "在异或运算中，对于两个变量 x,y 满足 (x^y^y)==x"
	 */

	// 加密数值（可随意设定）
	private static final int PASSWORD = 01234567;
	// 设置加密文件后缀（可随意设定）
	private static final String SUFFIX = ".enc";

	// path指定加密文件路径
	public void doEncrypt(String path) {
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			int tmp = 0;
			File inFile = new File(path);
			in = new FileInputStream(inFile);
			File outFile = new File(path + SUFFIX);
			out = new FileOutputStream(outFile);

			while ((tmp = in.read()) != -1) {
				// 加密处理
				out.write(tmp ^ PASSWORD);
			}
		} catch (FileNotFoundException e) {
			System.err.println("文件不存在！！");
		} catch (IOException e) {
			System.err.println("文件读取异常！！");
		} finally {
			// finally块关闭流操作
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.err.println("关闭流失败！！");
			}
		}

	}

	// path指定解密文件路径
	public void doDecrypt(String path) {
		int index = path.lastIndexOf(SUFFIX);
		if (index != path.length() - SUFFIX.length()) {
			System.err.println("文件类型不正确！！");
			return;
		}
		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			int tmp = 0;
			File inFile = new File(path);
			in = new FileInputStream(inFile);
			String name = path.substring(0, index);
			File outFile = new File(name);
			out = new FileOutputStream(outFile);

			while ((tmp = in.read()) != -1) {
				// 再次异或PASSWORD实现解密
				out.write(tmp ^ PASSWORD);
			}
		} catch (FileNotFoundException e) {
			System.err.println("文件不存在！！");
		} catch (IOException e) {
			System.err.println("文件读取异常！！");
		} finally {
			// finally块关闭流操作
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.err.println("关闭流失败！！");
			}
		}
	}

	public static void main(String[] args) {
		Encrypt encrypt = new Encrypt();
		// 第一个参数指定操作（加密/解密）
		// 第二个参数制定文件所在路径
		if (args.length != 2) {
			System.err.println("参数设置不正确");
			System.out.println();
			System.out.println("java Encrypt [OPTION] [FILE PATH]");
			System.out.println("\tencrypt\t-e\t加密\n\tdecrypt\t-d\t解密");
			return;
		}
		switch (args[0]) {
		case "encrypt":
			encrypt.doEncrypt(args[1]);
			break;
		case "-e":
			encrypt.doEncrypt(args[1]);
			break;
		case "decrypt":
			encrypt.doDecrypt(args[1]);
			break;
		case "-d":
			encrypt.doDecrypt(args[1]);
			break;

		default:
			System.err.println("参数设置不正确");
			System.out.println();
			System.out.println("java Encrypt [OPTION] [FILE PATH]");
			System.out.println("\tencrypt\t-e\t加密\n\tdecrypt\t-d\t解密");
			break;
		}

	}

}
