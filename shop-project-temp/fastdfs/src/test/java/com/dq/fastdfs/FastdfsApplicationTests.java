package com.dq.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsApplicationTests {

	@Autowired
	private FastFileStorageClient fastFileStorageClient;

	@Test
	public void test() {
		File file = new File("D:/img/a.jpg");
		try {
			StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
					new FileInputStream(file), file.length(), "JPG", null);
			System.out.println(storePath.getFullPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
