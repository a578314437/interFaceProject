package cn.lixing.Interface.uilt;

import java.io.Closeable;
import java.io.IOException;

public class FileCloseUilt {
	public static void close(Closeable...io) {
		for(Closeable temp:io) {
			if(temp!=null) {
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
