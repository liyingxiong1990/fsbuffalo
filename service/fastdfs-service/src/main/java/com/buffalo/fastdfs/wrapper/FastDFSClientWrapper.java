package com.buffalo.fastdfs.wrapper;

import com.buffalo.util.JsonUtil;
import com.buffalo.util.UUIDUtil;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;


/**
 * <p>Description: FastDFS文件上传下载包装类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 *
 */
@Component
public class FastDFSClientWrapper {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Environment env;

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file, String metaDataJson) throws IOException {
        Map<String,Object> metaDataMap = JsonUtil.json2Object(metaDataJson,Map.class);
        String fileName = file.getName();
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        String key = UUIDUtil.getUUID();
        metaDataMap.put("key",key);
        metaDataMap.put("fileName",fileName);
        metaDataMap.put("storePath",getResAccessUrl(storePath));
        metaDataMap.put("downloadUrl",getResAccessUrl(storePath)+"?attname="+fileName);
        redisTemplate.opsForValue().set(key,metaDataMap);
        return key;
    }

    /**
     * 获取文件信息
     * @param key
     * @return
     */
    public Map<String,Object> getFileData(String key){
        Map<String,Object> fileDataMap = (Map<String, Object>) redisTemplate.opsForValue().get(key);
        return fileDataMap;
    }

    /**
     * 删除文件
     * @param key
     * @return
     */
    public Boolean deleteFile(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        try {
            Map<String,Object> fileDataMap = getFileData(key);
            String fileUrl = (String)fileDataMap.get("storePath");
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            redisTemplate.delete(key);
            return true;
        } catch (FdfsUnsupportStorePathException e) {
            logger.warn(e.getMessage());
        }
        return false;
    }

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String trackerIP = env.getProperty("fdfs.trackerList");
        String fileUrl = "http://192.168.16.26/" + storePath.getFullPath();
        return fileUrl;
    }
}
