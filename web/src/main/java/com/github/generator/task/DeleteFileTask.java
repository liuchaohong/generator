package com.github.generator.task;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.generator.controller.GeneratorController;


@Component
@EnableScheduling
public class DeleteFileTask {

	private static Logger logger = LoggerFactory.getLogger(DeleteFileTask. class);
	
    @Scheduled(cron = "0 0 2 * * ?")
    public void run() throws IOException {//定时删除生成的文件
           logger.info("delete file => {}", GeneratorController.outputRootDir);
           FileUtils.deleteDirectory(new File(GeneratorController.outputRootDir));
    }

}
