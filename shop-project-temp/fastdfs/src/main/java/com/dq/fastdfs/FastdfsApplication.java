package com.dq.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Import(FdfsClientConfig.class)
@SpringBootApplication
public class FastdfsApplication {

	@Autowired
	private  FastFileStorageClient fastFileStorageClient;

	public void test() {
		File file = new File("D:/img/a.jpg");
		try {
			StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
					new FileInputStream(file), file.length(), "JPG", null);
			System.out.println(storePath.getFullPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}
}
