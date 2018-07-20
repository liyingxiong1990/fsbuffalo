
package com.buffalo.gateway.authentication.controller;

import com.buffalo.gateway.authentication.mapper.AuthPermissionMapper;
import com.buffalo.gateway.authentication.model.Menu;
import com.buffalo.gateway.authentication.model.Permission;
import com.buffalo.gateway.authentication.model.User;
import com.buffalo.gateway.authentication.service.AuthUserService;
import com.buffalo.gateway.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/oauth")
public class AuthController {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthUserService authUserService;
	@Autowired
	private AuthPermissionMapper permissionMapper;
	/**
	 * 根据用户名查询用户信息
	 * @param <T>
	 * @return
	 */
	@RequestMapping(value="/info/{login_name}", method = RequestMethod.GET)
	public <T> Map<String, T> getInfoByLoginName(@PathVariable String login_name) {
		try {
			Map<String, Object> result = new HashMap<>();
			User user = authUserService.getByLoginName(login_name);
			user.setPwd("");
			Set<Permission> permissionList = authUserService.getPermissionsByUserId(user.getUser_id());
			Set<Menu> menuList = authUserService.getMenusByUserId(user.getUser_id());
			result.put("user",user);
			result.put("permissionList",permissionList);
			result.put("allPermissionList",permissionMapper.list());
			result.put("menuList",menuList);
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.OK, "根据用户名查询用户信息成功", result);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return  (Map<String, T>) ResponseUtil.result(HttpStatus.INTERNAL_SERVER_ERROR, "根据用户名查询用户信息失败. Info:" + e.getMessage());
		}
	}

	/**
	 * 上传接口
	 *
	 * @param file
	 * @return
	 */
	@PostMapping("/upload")
	@CrossOrigin(origins = "*", maxAge = 3600)
	@ResponseBody
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			//String path = fastDFSClientWrapper.uploadFile(file);
			return ResponseEntity.status(HttpStatus.OK).body("");
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

}
