package com.zero.fast.dfs.controller;

import com.zero.fast.dfs.common.util.FastDFSClientWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fastdfs")
public class FastDFSController {

    @Resource
    private FastDFSClientWrapper dfsClient;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String fileUrl = dfsClient.uploadFile(file);
        map.put("file_url", fileUrl);
        return ResponseEntity.ok(map);

    }

}