package com.shincha.ddh.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.One_day_blood_sugar;
import com.shincha.ddh.model.dto.One_day_exercise;
import com.shincha.ddh.model.dto.Users;
import com.shincha.ddh.model.service.UserService;
//import com.shincha.ddh.util.JwtUtil;

@RestController
@RequestMapping("/api-user")
public class UserRestController {

	@Autowired
	private UserService userService;

	// resource 경로를 가져오기위해 ResourcesLoader를 주입받는다.
	@Autowired
	ResourceLoader resLoader;

//	@Autowired
//	private JwtUtil jwtUtil;


	// 1.회원가입
	@PostMapping("/registUser")
	public ResponseEntity<?> doRegist(@RequestBody Users user) throws IllegalStateException, IOException {

		// DB에 user 정보 등록
		int result = userService.insertUser(user);

		if (result > 0)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);

	}

	// 2. 로그인
	@PostMapping("/loginUser")
	public ResponseEntity<?> doLogin(@RequestBody Users user) {

		Users user_check = userService.selectById(user.getId(), user.getPassword());// 아이디 검토

		if (user_check != null) {
			System.out.println("정상");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			System.out.println("비정상");
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// 3. 특정 유저 정보 반환
	@GetMapping("/userInfo/{id}")
	public ResponseEntity<?> doInfo(@PathVariable String id) {
		Users userInfo = userService.selectById2(id);

		if (userInfo != null)
			return new ResponseEntity<Users>(userInfo, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 4. 특정 회원의 데일리 당 기록 -> 하루에 한 번
	@PostMapping("/sugar_blood_chart")
	public ResponseEntity<?> dosugar_blood(@RequestBody One_day_blood_sugar blood) {
		int result = userService.insertBloodSugar(blood);

		if (result > 0)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 5. 차트 정보를 위한 특정 회원의 당 기록 가져오기
	@GetMapping("/sugar_blood_chart/{userId}")
	public ResponseEntity<?> dosugar_blood_chart(@PathVariable String userId) {
		List<One_day_blood_sugar> sugar = userService.selectSugarById(userId);

		if (sugar != null && sugar.size() > 0)
			return new ResponseEntity<List<One_day_blood_sugar>>(sugar, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 6. 나의 챌린지 가져오기
	@GetMapping("/myChallenge/{userId}")
	public ResponseEntity<?> doMychallenge(@PathVariable String userId) {
		List<Challenge> challengeList = userService.selectMyChallenge(userId);
		if (challengeList != null && challengeList.size() > 0) {
			return new ResponseEntity<List<Challenge>>(challengeList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 7. 데일리 운동 기록
	@PostMapping("/daily")
	public ResponseEntity<?> dodDaily(@RequestBody One_day_exercise time) {
		int result = userService.insertExerciseTime(time);
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 8. 데일리 운동 기록 불러오기
	@GetMapping("/daily/{userId}")
	public ResponseEntity<?> dodDailyList(@PathVariable String userId) {
	
		List<One_day_exercise> exerciseList = userService.selectExerciseById(userId);
		if (exerciseList != null && exerciseList.size() > 0) {
			return new ResponseEntity<List<One_day_exercise>>(exerciseList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 8. 데일리 운동 기록 불러오기(최신순)
		@GetMapping("/dailyNew/{userId}")
		public ResponseEntity<?> doDailyListNew(@PathVariable String userId) {
//			System.out.println("daily exercise user ID = " + userId);
			List<One_day_exercise> exerciseList = userService.selectExerciseByIdNew(userId);
//			System.out.println(exerciseList);
			if (exerciseList != null && exerciseList.size() > 0) {
				return new ResponseEntity<List<One_day_exercise>>(exerciseList, HttpStatus.OK);
			} else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	// 9. 회원 정보 수정
	@PutMapping("/userUpdate")
	public ResponseEntity<?> doUserUpdate(@RequestBody Users user) {
		int result = userService.updateUser(user);
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}

	// 10. 혈당 추가
//	@PostMapping("/blood")
//	public ResponseEntity<?> doBlood(@RequestBody One_day_blood_sugar blood) {
//		int result = userService.insertBloodSugar(blood);
//		if(result>0) {
//			return new ResponseEntity<Void>(HttpStatus.OK);
//		}
//		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}

	// 11. 랭킹 매기기
	@GetMapping("/rank")
	public ResponseEntity<?> rank() {
		List<Users> userList = userService.selectAll();
		if (userList != null && userList.size() > 0) {
			return new ResponseEntity<List<Users>>(userList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 12. 랭킹 등급 변경 요청 -> 프론트에서 옴
	@PutMapping("/rank")
	public ResponseEntity<?> rankRate(@RequestBody Users user) {
		int result = userService.updateRank(user);
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
