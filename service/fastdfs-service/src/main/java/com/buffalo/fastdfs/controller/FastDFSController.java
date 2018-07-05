
package com.buffalo.fastdfs.controller;

import com.buffalo.fastdfs.wrapper.FastDFSClientWrapper;
import com.buffalo.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/fastdfs")
public class FastDFSController {

	@Autowired
	private FastDFSClientWrapper fastDFSClientWrapper;

	/**
	 * 上传接口
	 *
	 * @param file
	 * @param metaDataJson
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") CommonsMultipartFile file,@RequestParam("metaDataJson") String metaDataJson) {
		try {
			String key = fastDFSClientWrapper.uploadFile(file,metaDataJson);
			return ResponseEntity.status(HttpStatus.OK).body(key);
		} catch (IOException  ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	/**
	 * 获取文件
	 * @param key
	 * @return
	 */
	@GetMapping("/download/{key}")
	public <T> Map<String, T> handleFileDownload(@PathVariable String key) {
		try {
			Map<String,Object> resultMap = fastDFSClientWrapper.getFileData(key);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "获取文件成功", resultMap);
		} catch (Exception  e) {
			e.printStackTrace();
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "获取文件失败. :" + e.getMessage());
		}
	}

	/**
	 * 删除文件
	 * @param key
	 * @return
	 */
	@DeleteMapping("/delete/{key}")
	public <T> Map<String, T>  deleteFile(@PathVariable String key) {
		try {
			if(fastDFSClientWrapper.deleteFile(key)){
				return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "删除文件成功");
			}else{
				return (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除文件失败");
			}

		} catch (Exception e) {
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "删除文件失败.:" + e.getMessage());
		}
	}
}