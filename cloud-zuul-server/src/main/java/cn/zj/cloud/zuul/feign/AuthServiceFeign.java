package cn.zj.cloud.zuul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import cn.zj.cloud.model.Response;
import cn.zj.cloud.zuul.feign.model.AuthInfo;


@FeignClient(value="cloud-auth-server")
public interface AuthServiceFeign {
	@PostMapping("/token")
	public ResponseEntity<Response> issueToken(@RequestBody AuthInfo request);
	
	@GetMapping("/token")
	public ResponseEntity<Response> verify(@RequestHeader("tkn") String token);
}
